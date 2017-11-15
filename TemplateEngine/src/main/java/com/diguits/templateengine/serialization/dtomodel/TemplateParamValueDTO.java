package com.diguits.templateengine.serialization.dtomodel;

import java.util.UUID;

public class TemplateParamValueDTO {
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
