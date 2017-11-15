package com.diguits.templateengine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templateengine.antlr.CodeBuilderVisitorBase;
import com.diguits.templateengine.antlr.TemplateLexer;
import com.diguits.templateengine.antlr.TemplateParser;
import com.diguits.templateengine.antlr.TemplateParser.TemplateContext;
import com.diguits.templateengine.contract.Diagnostic;
import com.diguits.templateengine.contract.Diagnostic.Kind;
import com.diguits.templateengine.contract.Diagnostic.Operation;
import com.diguits.templateengine.contract.ICodeBuilder;

public abstract class CodeBuilderBase implements ICodeBuilder {
	public static final String PACKAGENAME = "com.diguits.templateengine";

	@InjectLogger
	Logger logger;

	protected CodeBuilderVisitorBase visitor;

	public CodeBuilderBase() {
		super();
	}

	@Override
	public synchronized List<Diagnostic> build(UUID templateId, String templateName, String[] modelClassNames, InputStream templateData,
			OutputStream templateCode, String context, String contextErrorMessage) {
		ANTLRInputStream input;
		List<Diagnostic> result = new ArrayList<Diagnostic>();
		try {
			input = new ANTLRInputStream(templateData);
			TemplateLexer templateLexer = new TemplateLexer(input);
			TokenStream tokens = new CommonTokenStream(templateLexer);
			TemplateParser parser = new TemplateParser(tokens);
			parser.addErrorListener(new BaseErrorListener() {
				@Override
				public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
						int charPositionInLine, String msg, RecognitionException e) {
					super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
					if(contextErrorMessage!=null)
						msg = contextErrorMessage+ msg;
					result.add(new Diagnostic(templateId, TemplateDef.class.getName(), Kind.ERROR, getOperation(), context, line, charPositionInLine, msg));
				}
			});
			TemplateContext template = parser.template();
			String className = getClassName(templateName);
			CodeBuilderVisitorBase visitor = getVisitor(className, modelClassNames, templateCode);
			visitor.visit(template);
			visitor.getWriter().flush();
			visitor.getWriter().close();
		} catch (IOException e) {
			logger.error("Error building template »"+templateName+"("+templateId+")« while creating ANTLRInputStream from TemplateData", e);
		}
		return result;
	}

	public String getClassName(String templateName) {
		return templateName;
	}

	private CodeBuilderVisitorBase getVisitor(String templateClassName, String[] modelClassNames,
			OutputStream templateCode) {
		if (visitor == null) {
			String[] defaultImports = new String[] { PACKAGENAME + ".*" };
			createVisitor(templateClassName, modelClassNames, defaultImports);
		} else {
			visitor.setClassName(templateClassName);
			visitor.setModelClassNames(modelClassNames);
		}
		visitor.setWriter(new OutputStreamWriter(templateCode));
		return visitor;
	}

	protected abstract void createVisitor(String templateClassName, String[] modelClassNames, String[] defaultImports);

	protected abstract Operation getOperation();

}
