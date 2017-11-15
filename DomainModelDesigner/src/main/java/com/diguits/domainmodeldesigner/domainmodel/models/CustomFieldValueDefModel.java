package com.diguits.domainmodeldesigner.domainmodel.models;

import com.diguits.domainmodeldefinition.services.ValueConverter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CustomFieldValueDefModel extends BaseDefModel {

	public CustomFieldValueDefModel() {
		super();
		customFieldProperty().addListener((v, o, n) -> {
			if (o != null && n != null && o.getDataType() != n.getDataType() && getValue() != null) {
				setValue(ValueConverter.tryToConver(getValue(), n.getDataType()));
			}
			if (n != null) {
				n.dataTypeProperty().addListener((value, oldDataType, newDataType) -> {
					if (getValue() != null)
						setValue(ValueConverter.tryToConver(getValue(), newDataType));
				});
			}
		});
	}

	private ObjectProperty<CustomFieldDefModel> customField;
	private ObjectProperty<Object> value;

	public CustomFieldDefModel getCustomField() {
		if (customField != null)
			return customField.get();
		return null;
	}

	public void setCustomField(CustomFieldDefModel customField) {
		if (this.customField != null || customField != null) {
			customFieldProperty().set(customField);
		}
	}

	public ObjectProperty<CustomFieldDefModel> customFieldProperty() {
		if (customField == null) {
			customField = new SimpleObjectProperty<CustomFieldDefModel>(this, "customField", null);
		}
		return customField;
	}

	public Object getValue() {
		if (value != null)
			return value.get();
		return null;
	}

	public void setValue(Object value) {
		if (this.value != null || value != null) {
			valueProperty().set(value);
		}
	}

	public ObjectProperty<Object> valueProperty() {
		if (value == null) {
			value = new SimpleObjectProperty<Object>(this, "value", null);
		}
		return value;
	}

	public DomainModelDefModel getDomainModelOwner() {
		return (DomainModelDefModel) getOwner();
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitCustomFieldValueDefModel(this, owner);
	}
}
