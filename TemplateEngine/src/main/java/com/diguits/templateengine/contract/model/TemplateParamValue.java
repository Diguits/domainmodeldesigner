package com.diguits.templateengine.contract.model;

import java.util.UUID;

public class TemplateParamValue {
	private UUID templateParameter;
	private Object value;

	public UUID getTemplateParameter() {
		return templateParameter;
	}

	public void setTemplateParameter(UUID templateParameter) {
		this.templateParameter = templateParameter;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
