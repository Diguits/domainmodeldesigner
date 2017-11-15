package com.diguits.domainmodeldesigner.templateapplyconfig.models;

import com.diguits.domainmodeldefinition.services.ValueConverter;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TemplateParamValueModel {
	private ObjectProperty<TemplateParameterDefModel> templateParameter;
	private ObjectProperty<Object> value;

	public TemplateParamValueModel() {
		super();
		templateParameterProperty().addListener((v, o, n) -> {
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

	public TemplateParameterDefModel getTemplateParameter() {
		if (templateParameter != null)
			return templateParameter.get();
		return null;
	}

	public void setTemplateParameter(TemplateParameterDefModel templateParameter) {
		if (this.templateParameter != null || templateParameter != null) {
			templateParameterProperty().set(templateParameter);
		}
	}

	public ObjectProperty<TemplateParameterDefModel> templateParameterProperty() {
		if (templateParameter == null) {
			templateParameter = new SimpleObjectProperty<TemplateParameterDefModel>(this, "templateParameter", null);
		}
		return templateParameter;
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
}
