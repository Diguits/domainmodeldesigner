package com.diguits.templateengine.contract;

import java.util.UUID;

public class Diagnostic {

	public Diagnostic(UUID modelId, String modelClass, Kind kind, Operation operation, String context, long lineNumber, long columnNumber, String message) {
		super();
		this.modelId = modelId;
		this.modelClass = modelClass;
		this.kind = kind;
		this.operation = operation;
		this.context = context;
		this.lineNumber = lineNumber;
		this.columnNumber = columnNumber;
		this.message = message;
	}

	public enum Kind {
		ERROR, WARNING
	}

	public enum Operation{
		GENERATING,
		COMPILING,
		APPLYING,
		EVALUATING
	}

	UUID modelId;
	String modelClass;
	Kind kind;
	long lineNumber;
	long columnNumber;
	String message;
	Operation operation;
	String context;

	public UUID getModelId() {
		return modelId;
	}

	public String getModelClass() {
		return modelClass;
	}

	public Kind getKind() {
		return kind;
	}

	public long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public long getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(long columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Operation getOperation() {
		return operation;
	}

	public String getContext() {
		return context;
	}
}
