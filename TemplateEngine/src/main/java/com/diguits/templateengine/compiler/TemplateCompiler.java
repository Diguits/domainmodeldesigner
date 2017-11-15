package com.diguits.templateengine.compiler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import com.diguits.templateengine.contract.Diagnostic.Operation;
import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templateengine.contract.ITemplateCompiler;

public class TemplateCompiler implements ITemplateCompiler {

	// The compiler instance that this facade uses.
	private final JavaCompiler compiler;
	// The compiler options (such as "-target" "1.5").
	private List<String> options;

	public TemplateCompiler() {
		super();
		compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new IllegalStateException(
					"Cannot find the system Java compiler. " + "Check that your class path includes tools.jar");
		}
		this.options = new ArrayList<String>();
	}

	@Override
	public synchronized List<com.diguits.templateengine.contract.Diagnostic> compile(UUID templateId, String templateClassName, InputStream templateCode, OutputStream classStream,
			String context, String contextErrorMessage) {
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		FileManagerImpl javaFileManager = new FileManagerImpl(fileManager, templateCode, classStream);

		// Get a CompliationTask from the compiler and compile the sources
		JavaFileObjectImpl sourceFileObject = new JavaFileObjectImpl(templateClassName, templateCode);
		List<JavaFileObject> source = new ArrayList<JavaFileObject>();
		source.add(sourceFileObject);
		final CompilationTask task = compiler.getTask(null, javaFileManager, diagnostics, options, null, source);
		task.call();
		List<Diagnostic<? extends JavaFileObject>> callDiagnostics = diagnostics.getDiagnostics();
		if (callDiagnostics.size()>0) {
			List<com.diguits.templateengine.contract.Diagnostic> result = new ArrayList<com.diguits.templateengine.contract.Diagnostic>();
			for (Diagnostic<?> diagnostic : callDiagnostics) {
				String message = diagnostic.getMessage(null);
				if(contextErrorMessage!=null)
					message = contextErrorMessage+ message;
				result.add(new com.diguits.templateengine.contract.Diagnostic(templateId, TemplateDef.class.getName(),
						diagnostic.getKind() == Kind.ERROR ? com.diguits.templateengine.contract.Diagnostic.Kind.ERROR
								: com.diguits.templateengine.contract.Diagnostic.Kind.WARNING, Operation.COMPILING, context,
						diagnostic.getLineNumber(), diagnostic.getColumnNumber(), message));

			}
			return result;
		}
		return null;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
}
