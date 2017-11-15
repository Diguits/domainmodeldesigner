package com.diguits.templateengine.exceptions;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

public class TemplateCompilerException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * The fully qualified name of the class that was being compiled.
	 */
	private String className;
	// Unfortunately, Diagnostic and Collector are not Serializable, so we can't
	// serialize the collector.
	transient private DiagnosticCollector<JavaFileObject> diagnostics;

	public TemplateCompilerException(String message, String qualifiedClassName, Throwable cause,
			DiagnosticCollector<JavaFileObject> diagnostics) {
		super(message, cause);
		setClassNames(qualifiedClassName);
		setDiagnostics(diagnostics);
	}

	public TemplateCompilerException(String message, String qualifiedClassName,
			DiagnosticCollector<JavaFileObject> diagnostics) {
		super(message);
		setClassNames(qualifiedClassName);
		setDiagnostics(diagnostics);
	}

	public TemplateCompilerException(String qualifiedClassName, Throwable cause,
			DiagnosticCollector<JavaFileObject> diagnostics) {
		super(cause);
		setClassNames(qualifiedClassName);
		setDiagnostics(diagnostics);
	}

	private void setClassNames(String qualifiedClassName) {
		className = qualifiedClassName;
	}

	private void setDiagnostics(DiagnosticCollector<JavaFileObject> diagnostics) {
		this.diagnostics = diagnostics;
	}

	public String getClassName() {
		return className;
	}

	/**
	 * Gets the diagnostics collected by this exception.
	 *
	 * @return this exception's diagnostics
	 */
	public DiagnosticCollector<JavaFileObject> getDiagnostics() {
		return diagnostics;
	}
}
