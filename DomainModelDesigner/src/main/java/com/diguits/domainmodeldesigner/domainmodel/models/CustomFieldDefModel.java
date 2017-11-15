package com.diguits.domainmodeldesigner.domainmodel.models;

import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.DataType;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomFieldDefModel extends BaseDefModel {

	public CustomFieldDefModel() {
		super();
		overDefClassProperty().addListener((v, o, n) -> {
			if (n != null) {
				overDefClassNameProperty().set(n.getSimpleName().replace("Def", ""));
			} else {
				overDefClassNameProperty().set("");
			}
		});
	}

	private ObjectProperty<Class<? extends BaseDef>> overDefClass;
	private ObjectProperty<DataType> dataType;
	private StringProperty overDefClassName;
	private ObjectProperty<UUID> reference;
	private StringProperty data;
	private BooleanProperty multiline;

	public Class<? extends BaseDef> getOverDefClass() {
		if (overDefClass != null)
			return overDefClass.get();
		return null;
	}

	public void setOverDefClass(Class<? extends BaseDef> overDefClass) {
		if (this.overDefClass != null || overDefClass != null) {
			overDefClassProperty().set(overDefClass);
		}
	}

	public ObjectProperty<Class<? extends BaseDef>> overDefClassProperty() {
		if (overDefClass == null) {
			overDefClass = new SimpleObjectProperty<Class<? extends BaseDef>>(this, "overDefClass", null);
		}
		return overDefClass;
	}

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

	public DomainModelDefModel getDomainModelOwner() {
		return (DomainModelDefModel) getOwner();
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitCustomFieldDefModel(this, owner);
	}

	public StringProperty overDefClassNameProperty() {
		if (overDefClassName == null) {
			overDefClassName = new SimpleStringProperty(this, "overDefClassName", null);
		}
		return overDefClassName;
	}
}
