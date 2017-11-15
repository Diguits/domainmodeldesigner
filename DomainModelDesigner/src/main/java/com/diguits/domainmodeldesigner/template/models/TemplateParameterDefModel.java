package com.diguits.domainmodeldesigner.template.models;

import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DataType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TemplateParameterDefModel extends TemplateProjectItemDefModel {

	public TemplateParameterDefModel() {
		super();
	}

	private ObjectProperty<DataType> dataType;
	private ObjectProperty<UUID> reference;
	private StringProperty data;
	private BooleanProperty multiline;

	public DataType getDataType() {
		if (dataType != null)
			return dataType.get();
		return null;
	}

	public void setDataType(DataType dataType) {
		if (this.dataType != null || dataType != null) {
			dataTypeProperty().set(dataType);
		}
	}

	public ObjectProperty<DataType> dataTypeProperty() {
		if (dataType == null) {
			dataType = new SimpleObjectProperty<DataType>(this, "dataType", null);
		}
		return dataType;
	}

	public UUID getReference() {
		if (reference != null)
			return reference.get();
		return null;
	}

	public void setReference(UUID reference) {
		if (this.reference != null || reference != null) {
			referenceProperty().set(reference);
		}
	}

	public ObjectProperty<UUID> referenceProperty() {
		if (reference == null) {
			reference = new SimpleObjectProperty<UUID>(this, "reference", null);
		}
		return reference;
	}

	public String getData() {
		if (data != null)
			return data.get();
		return null;
	}

	public void setData(String data) {
		if (this.data != null || data != null) {
			dataProperty().set(data);
		}
	}

	public StringProperty dataProperty() {
		if (data == null) {
			data = new SimpleStringProperty(this, "data", null);
		}
		return data;
	}

	public boolean getMultiline() {
		if (multiline != null)
			return multiline.get();
		return false;
	}

	public void setMultiline(boolean multiline) {
		if (this.multiline != null || multiline != false) {
			multilineProperty().set(multiline);
		}
	}

	public BooleanProperty multilineProperty() {
		if (multiline == null) {
			multiline = new SimpleBooleanProperty(this, "multiline", false);
		}
		return multiline;
	}

	public TemplateDefModel getTemplateOwner() {
		if (owner != null)
			return (TemplateDefModel) owner.get();
		return null;
	}

	public void setTemplateOwner(TemplateDefModel owner) {
		if (this.owner != null || owner != null) {
			ownerProperty().set(owner);
		}
	}
}
