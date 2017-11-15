package com.diguits.javafx.log.controllers;

import java.util.UUID;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Position {
	private ObjectProperty<UUID> modelId;
	private StringProperty modelClass;
	private StringProperty fieldName;
	private LongProperty lineNumber;
	private LongProperty columnNumber;

	public Position(UUID modelId, String modelClass, String fieldName, long lineNumber, long columnNumber) {
		super();
		setModelId(modelId);
		setModelClass(modelClass);
		setFieldName(fieldName);
		setLineNumber(lineNumber);
		setColumnNumber(columnNumber);
	}

	public UUID getModelId() {
		if (modelId != null)
			return modelId.get();
		return null;
	}

	public void setModelId(UUID modelId) {
		if (this.modelId != null || modelId != null) {
			modelIdProperty().set(modelId);
		}
	}

	public ObjectProperty<UUID> modelIdProperty() {
		if (modelId == null) {
			modelId = new SimpleObjectProperty<UUID>(this, "modelId", null);
		}
		return modelId;
	}

	public String getModelClass() {
		if (modelClass != null)
			return modelClass.get();
		return null;
	}

	public void setModelClass(String modelClass) {
		if (this.modelClass != null || modelClass != null) {
			modelClassProperty().set(modelClass);
		}
	}

	public StringProperty modelClassProperty() {
		if (modelClass == null) {
			modelClass = new SimpleStringProperty(this, "modelClass", null);
		}
		return modelClass;
	}

	public String getFieldName() {
		if (fieldName != null)
			return fieldName.get();
		return null;
	}

	public void setFieldName(String fieldName) {
		if (this.fieldName != null || fieldName != null) {
			fieldNameProperty().set(fieldName);
		}
	}

	public StringProperty fieldNameProperty() {
		if (fieldName == null) {
			fieldName = new SimpleStringProperty(this, "fieldName", null);
		}
		return fieldName;
	}

	public Long getLineNumber() {
		if (lineNumber != null)
			return lineNumber.get();
		return null;
	}

	public void setLineNumber(Long lineNumber) {
		if (this.lineNumber != null || lineNumber != null) {
			lineNumberProperty().set(lineNumber);
		}
	}

	public LongProperty lineNumberProperty() {
		if (lineNumber == null) {
			lineNumber = new SimpleLongProperty(this, "lineNumber");
		}
		return lineNumber;
	}

	public Long getColumnNumber() {
		if (columnNumber != null)
			return columnNumber.get();
		return null;
	}

	public void setColumnNumber(Long columnNumber) {
		if (this.columnNumber != null || columnNumber != null) {
			columnNumberProperty().set(columnNumber);
		}
	}

	public LongProperty columnNumberProperty() {
		if (columnNumber == null) {
			columnNumber = new SimpleLongProperty(this, "columnNumber");
		}
		return columnNumber;
	}

}
