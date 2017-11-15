package com.diguits.templateengine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;

import com.diguits.common.crypt.HashGenerationException;
import com.diguits.common.crypt.HashGeneratorUtils;
import com.diguits.common.log.InjectLogger;
import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.services.ITemplateProjectService;
import com.diguits.templatedefinition.services.TemplateFileManager;
import com.diguits.templateengine.contract.Diagnostic;
import com.diguits.templateengine.contract.Diagnostic.Kind;
import com.diguits.templateengine.contract.Diagnostic.Operation;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.diguits.templateengine.contract.EvalExpressionResult;
import com.diguits.templateengine.contract.IDiagnosticCollector;
import com.diguits.templateengine.contract.IExpression;
import com.diguits.templateengine.contract.IExpressionClassLoader;
import com.diguits.templateengine.contract.IExpressionCodeBuilder;
import com.diguits.templateengine.contract.ITemplate;
import com.diguits.templateengine.contract.ITemplateEngine;
import com.diguits.templateengine.contract.ParamValue;
import com.diguits.templateengine.contract.TemplateApplyOutput;
import com.diguits.templateengine.contract.ITemplateClassLoader;
import com.diguits.templateengine.contract.ITemplateCodeBuilder;
import com.diguits.templateengine.contract.ITemplateCompiler;
import com.google.inject.Inject;

public class TemplateEngine implements ITemplateEngine {
	public static final String TEMPLATECODE = "TemplateCode";

	public static final String TEMPLATEOUTPUTPATH = "TemplateOutputPath";

	public static final String TEMPLATEFILENAME = "TemplateFilename";

	public static final String TEMPLATECONDITION = "TemplateCondition";

	@InjectLogger
	Logger logger;

	@Inject
	ITemplateProjectService templateProjectService;

	@Inject
	TemplateFileManager fileManeger;

	@Inject
	ITemplateCodeBuilder codeBuilder;

	@Inject
	IExpressionCodeBuilder expCodeBuilder;

	@Inject
	ITemplateCompiler compiler;

	@Inject
	protected ITemplateClassLoader classLoader;

	@Inject
	protected IExpressionClassLoader expClassLoader;

	Map<String, ExpressionWrapper> expressionCashe;

	private Lock lock = new ReentrantLock();

	public TemplateEngine() {
		super();
		expressionCashe = new HashMap<String, ExpressionWrapper>();
	}

	@Override
	public void generateCode(String directory, UUID projectId, UUID templateId, String templateName,
			String[] modelClassNames, IDiagnosticCollector diagnosticCollector) {
		InputStream templateSourceInputStream = templateProjectService.getTemplateSourceInputStream(directory,
				projectId, templateId, templateName);
		OutputStream templateCodeOutputStream = templateProjectService.getTemplateCodeOutputStream(directory, projectId,
				templateId, templateName);
		String error = "Error generating code for template »" + templateName + "« (" + templateId + ")\r\n";
		List<Diagnostic> diagnostics = codeBuilder.build(templateId, templateName, modelClassNames,
				templateSourceInputStream, templateCodeOutputStream, TEMPLATECODE, error);
		diagnosticCollector.report(diagnostics);
		try {
			templateSourceInputStream.close();
		} catch (IOException e) {
			logger.error("Error generating code for template »{}({})« while trying to close the template code input",
					templateName, templateId);
		}
		try {
			templateCodeOutputStream.flush();
			templateCodeOutputStream.close();
		} catch (IOException e) {
			logger.error(
					"Error generating code for template »{}({})« while trying to close the template generated code output",
					templateName, templateId);
		}
	}

	@Override
	public void compileCode(String directory, UUID projectId, UUID templateId, String templateName,
			String[] modelClassNames, IDiagnosticCollector diagnosticCollector) {
		if (fileManeger.templateNeedCodeGeneration(directory, projectId, templateId))
			generateCode(directory, projectId, templateId, templateName, modelClassNames, diagnosticCollector);
		if (diagnosticCollector.hasError())
			return;
		else {
			InputStream templateCodeInputStream = templateProjectService.getTemplateCodeInputStream(directory,
					projectId, templateId, templateName);
			OutputStream templateClassOutputStream = templateProjectService.getTemplateClassOutputStream(directory,
					projectId, templateId, templateName);
			String error = "Error compiling template »" + templateName + " (" + templateId + ")«\r\n";
			List<Diagnostic> diagnostics = compiler.compile(templateId, templateName, templateCodeInputStream,
					templateClassOutputStream, TEMPLATECODE, error);
			diagnosticCollector.report(diagnostics);
			try {
				templateCodeInputStream.close();
			} catch (IOException e) {
				logger.error(
						"Error compiling code for template »{}({})« while trying to close the template code generared input",
						templateName, templateId);
			}
			try {
				templateClassOutputStream.flush();
				templateClassOutputStream.close();
			} catch (IOException e) {
				logger.error(
						"Error compiling code for template »{}({})« while trying to close the compiled class output",
						templateName, templateId);
			}
		}
	}

	@Override
	public TemplateApplyOutput applyTemplate(String directory, UUID projectId, UUID templateId, String templateName,
			String[] modelClassNames, Object[] models, String outputRootDir, TemplateApplyConfig config,
			List<ParamValue> paramValues,
			IDiagnosticCollector diagnosticCollector) {
		TemplateApplyOutput result = new TemplateApplyOutput();
		Writer writer = null;
		boolean needCompilation = true;
		InputStream templateClassInputStream = null;
		result.setTemplateId(templateId);
		result.setTemplateName(templateName);
		UUID[] modelsId = new UUID[models.length];
		String[] modelsName = new String[models.length];
		for (int i = 0; i < models.length; i++) {
			modelsId[i] = getModelId(models[i]);
			modelsName[i] = getModelName(models[i]);
		}
		result.setModelsId(modelsId);
		result.setModelsName(modelsName);
		lock.lock();
		try {
			if (!fulfillCondition(templateId, templateName, config.getConditionExpression(), modelClassNames, models,
					diagnosticCollector))
				return result;
			needCompilation = fileManeger.templateNeedCompilation(directory, projectId, templateId);
			if (needCompilation)
				compileCode(directory, projectId, templateId, templateName, modelClassNames, diagnosticCollector);
			if (diagnosticCollector.hasError()) {
				return result;
			}
			templateClassInputStream = templateProjectService.getTemplateClassInputStream(directory, projectId,
					templateId, templateName);
			writer = null;
			if (config.getInMemory())
				writer = new StringWriter();
			else {
				File outputFile = getOutputFile(templateId, templateName, modelClassNames, models, outputRootDir,
						config, diagnosticCollector);
				if (outputFile != null) {
					result.setOutputFullPath(outputFile.getPath());
					try {
						FileOutputStream stream = new FileOutputStream(outputFile);
						writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
					} catch (IOException e) {
						String error = getApplyError(templateId, templateName, models);
						diagnosticCollector.report(new Diagnostic(templateId, TemplateDef.class.getName(), Kind.ERROR,
								Operation.APPLYING, TEMPLATECODE, 0, 0, error + e.getMessage()));
						return result;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		if (writer != null && templateClassInputStream != null) {
			String fullClassName = codeBuilder.getFullClassName(templateName);
			ITemplate template = classLoader.load(fullClassName, templateClassInputStream, needCompilation);
			try {
				template.apply(writer, models, paramValues, logger);
				writer.flush();
				if (config.getInMemory())
					result.setOutput(((StringWriter) writer).toString());
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
				String error = getApplyError(templateId, templateName, models);
				Diagnostic diagnostic = getDiagnosticFromException(templateId, fullClassName, e, Operation.APPLYING,
						TEMPLATECODE);
				diagnostic.setMessage(error + diagnostic.getMessage());
				diagnosticCollector.report(diagnostic);
			}
		}

		return result;
	}

	private String getApplyError(UUID templateId, String templateName, Object model) {
		String error = "Error applying template »" + templateName + "« (" + templateId + ") over model »"
				+ model.toString() + "«\r\n";
		return error;
	}

	private Diagnostic getDiagnosticFromException(UUID templateId, String fullClassName, Exception e,
			Operation operation, String context) {
		StackTraceElement classElement = null;
		for (StackTraceElement element : e.getStackTrace()) {
			if (element.getClassName().equals(fullClassName)) {
				classElement = element;
				break;
			}
		}
		int lineNumber = classElement != null ? classElement.getLineNumber() : 0;
		return new Diagnostic(templateId, TemplateDef.class.getName(), Kind.ERROR, operation, context, lineNumber, 0,
				e.toString());
	}

	private String getModelName(Object model) {
		if (model != null) {
			Method method;
			try {
				method = model.getClass().getMethod("getName");
				if (method.getReturnType() == String.class)
					return (String) method.invoke(model);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private UUID getModelId(Object model) {
		if (model != null) {
			Method method;
			try {
				method = model.getClass().getMethod("getId");
				if (method.getReturnType() == UUID.class)
					return (UUID) method.invoke(model);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private File getOutputFile(UUID templateId, String templateName, String[] modelClassNames, Object[] models,
			String outputRootDir, TemplateApplyConfig config,
			IDiagnosticCollector diagnosticCollector) {
		String outputPath = config.getOutputPathExpression();
		if (outputPath == null || outputPath.isEmpty()) {
			String error = "Error applying template »" + templateName + "« (" + templateId
					+ "), the output path is empty";
			diagnosticCollector.report(new Diagnostic(templateId, TemplateDef.class.getName(), Kind.ERROR,
					Operation.APPLYING, TEMPLATECODE, 0, 0, error));
			return null;
		}
		if (outputPath.contains("@(")) {
			EvalExpressionResult outputPathResult = evalExpression(config.getTemplateId(), templateName, outputPath,
					modelClassNames,
					models, true, diagnosticCollector, TEMPLATEOUTPUTPATH);
			outputPath = outputPathResult.getResult() != null ? outputPathResult.getResult().toString() : "";
		}
		File directoryFile = new File(outputPath);
		if (!directoryFile.isAbsolute()) {
			File outputRootDirFile = new File(outputRootDir);
			directoryFile = new File(outputRootDirFile, outputPath);
		}

		String fileName = config.getOutputFilenameExpression();
		if (fileName == null || fileName.isEmpty()) {
			String error = "Error applying template »" + templateName + "« (" + templateId
					+ "), the output file name is empty";
			diagnosticCollector.report(new Diagnostic(templateId, TemplateDef.class.getName(), Kind.ERROR,
					Operation.APPLYING, TEMPLATECODE, 0, 0, error));
			return null;
		}
		if (fileName.contains("@(")) {
			EvalExpressionResult fileNameResult = evalExpression(config.getTemplateId(), templateName, fileName,
					modelClassNames, models,
					true, diagnosticCollector, TEMPLATEFILENAME);
			fileName = fileNameResult.getResult() != null ? fileNameResult.getResult().toString() : "";
		}
		if (outputPath == null || outputPath.trim().isEmpty() || fileName == null || fileName.trim().isEmpty())
			return null;

		if (!directoryFile.exists())
			directoryFile.mkdirs();
		File file = new File(directoryFile, fileName);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("Error applying template »{}({})« in model »{}« while trying to create the file »{}«",
						templateName, templateId, models.toString(), file.getAbsolutePath());
			}
		return file;
	}

	private boolean fulfillCondition(UUID templateId, String templateName, String condition, String[] modelClassNames,
			Object[] models,
			IDiagnosticCollector diagnosticCollector) {
		if (condition == null)
			return true;
		condition = condition.trim();
		if (condition.isEmpty())
			return true;

		EvalExpressionResult outputPathResult = evalExpression(templateId, templateName, condition, modelClassNames,
				models, false,
				diagnosticCollector, TEMPLATECONDITION);
		if (outputPathResult.getResult() instanceof Boolean)
			return (Boolean) outputPathResult.getResult();
		else {
			String error = "Warning evaluating  »" + TEMPLATECONDITION + "«";
			if (templateName != null)
				error += " on template »" + templateName + "« (" + templateId + ")";
			error += " over model »" + models.toString() + "«\r\n";
			error += " The expression does not return boolean value. Template not applied.";
			diagnosticCollector.report(
					new Diagnostic(templateId, "", Kind.WARNING, Operation.APPLYING, TEMPLATECONDITION, 0, 0, error));
		}
		return false;
	}

	private static class ExpressionWrapper {
		IExpression expression;
		String code;

		public ExpressionWrapper(IExpression expression, String code) {
			super();
			this.expression = expression;
			this.code = code;
		}

		public IExpression getExpression() {
			return expression;
		}

		public String getCode() {
			return code;
		}
	}

	@Override
	public EvalExpressionResult evalExpression(String expression, String[] modelClassNames, Object[] models,
			boolean isStringExpresion,
			IDiagnosticCollector diagnosticCollector, String context) {
		return evalExpression(null, null, expression, modelClassNames, models, isStringExpresion, diagnosticCollector,
				context);
	}

	public EvalExpressionResult evalExpression(UUID templateId, String templateName, String expression,
			String[] modelClassNames, Object[] models,
			boolean isStringExpresion, IDiagnosticCollector diagnosticCollector, String context) {
		expression = expression.trim();

		IExpression exp = null;
		String key = expression + String.join(",", modelClassNames);
		EvalExpressionResult result = new EvalExpressionResult();
		boolean addedTwoChars = false;
		if (!isStringExpresion && !expression.startsWith("@(")) {
			expression = "@(" + expression + ")@";
			addedTwoChars = true;
		}
		if (expressionCashe.containsKey(key)) {
			ExpressionWrapper expWrapper = expressionCashe.get(key);
			exp = expWrapper.getExpression();
			result.setCode(expWrapper.getCode());
		} else {
			String expClassName = "Expression";
			try {
				expClassName += HashGeneratorUtils.generateMD5(key);
			} catch (HashGenerationException e1) {
				logger.error(
						"Error evaluating expression »{}« in template »{}({})« while trying to generate hash for the expression class name",
						expression, templateName, templateId);
			}
			try {
				ByteArrayInputStream expInputStream = new ByteArrayInputStream(expression.getBytes("UTF-8"));
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				expCodeBuilder.setStringExpresion(isStringExpresion);
				String expressionName = "";
				if (context != null)
					expressionName = context.toLowerCase();
				String error = "Error generating code for expression »" + expressionName + "«";
				if (templateName != null)
					error += " on template »" + templateName + "« (" + templateId + ")";
				error += "\r\n";
				List<Diagnostic> diagnostics = expCodeBuilder.build(templateId, expClassName, modelClassNames,
						expInputStream, outputStream, context, error);
				if (diagnostics != null) {
					for (Diagnostic diagnostic : diagnostics) {
						diagnostic.setLineNumber(0);
						if (addedTwoChars)
							diagnostic.setColumnNumber(diagnostic.getColumnNumber() - 2);
					}
					diagnosticCollector.report(diagnostics);
				}
				if (diagnostics == null || !diagnostics.stream().anyMatch(d -> d.getKind() == Kind.ERROR)) {
					ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
					if (outputStream.size() > 0) {
						String code = new String(outputStream.toByteArray(), "UTF-8");
						result.setCode(code);
						outputStream.reset();
						if (context != null)
							expressionName = context.toLowerCase();
						error = "Error compiling expression »" + expressionName + "«";
						if (templateName != null)
							error += " on template »" + templateName + "« (" + templateId + ")";
						error += "\r\n";

						diagnostics = compiler.compile(templateId, expClassName, inputStream, outputStream, context,
								error);
						if (diagnostics != null) {
							for (Diagnostic diagnostic : diagnostics) {
								diagnostic.setMessage(diagnostic.getMessage() + "\r\n Code: \r\n" + code);
							}
							diagnosticCollector.report(diagnostics);
						}
						if (diagnostics == null || !diagnostics.stream().anyMatch(d -> d.getKind() == Kind.ERROR)) {
							if (outputStream.size() > 0) {
								inputStream.reset();
								inputStream = new ByteArrayInputStream(outputStream.toByteArray());
								String fullClassName = codeBuilder.getFullClassName(expClassName);
								exp = expClassLoader.load(fullClassName, inputStream, true);
								if (exp != null)
									expressionCashe.put(key, new ExpressionWrapper(exp, code));
							}
						}
					}
				}
			} catch (UnsupportedEncodingException e) {
				logger.error(
						"Error evaluating expression »{}« in template »{}({})« while trying to convert expression to UTF-8 byte[]",
						expression, templateName, templateId);
			}
		}
		try {
			if (exp != null)
				result.setResult(exp.evaluate(models));
		} catch (Exception e) {
			String expressionName = "";
			if (context != null)
				expressionName = context.toLowerCase();
			String error = "Error evaluating  »" + expressionName + "«";
			if (templateName != null)
				error += " on template »" + templateName + "« (" + templateId + ")";
			error += " over model »" + models.toString() + "«\r\n";
			error += "Expression : »" + expression + "«\r\n";
			Diagnostic diagnostic = getDiagnosticFromException(templateId, exp.getClass().getName(), e,
					Operation.EVALUATING, context);
			diagnostic.setMessage(error + diagnostic.getMessage());
			diagnosticCollector.report(diagnostic);
		}

		return result;
	}

}
