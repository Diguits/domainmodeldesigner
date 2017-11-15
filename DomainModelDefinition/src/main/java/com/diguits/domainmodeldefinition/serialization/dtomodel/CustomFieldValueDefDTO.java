package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class CustomFieldValueDefDTO extends BaseDefDTO {
	UUID customFieldId;
	Object value;

	public UUID getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(UUID customFieldId) {
		this.customFieldId = customFieldId;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
