package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class BaseDefDTO {
	protected UUID id;
	protected String name;
	protected String description;
	protected UUID ownerId;
	protected List<LocalizedDataDefDTO> localizedDataList;
	protected List<CustomFieldValueDefDTO> customFieldValues;

	public BaseDefDTO() {
		localizedDataList = new ArrayList<LocalizedDataDefDTO>();
		customFieldValues = new ArrayList<CustomFieldValueDefDTO>();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}

	public List<LocalizedDataDefDTO> getLocalizedDataList() {
		return localizedDataList;
	}

	public void setLocalizedDataList(List<LocalizedDataDefDTO> localizedDataList) {
		this.localizedDataList = localizedDataList;
	}

	public List<CustomFieldValueDefDTO> getCustomFieldValues() {
		return customFieldValues;
	}

	public void setCustomFieldValues(List<CustomFieldValueDefDTO> customFieldValues) {
		this.customFieldValues = customFieldValues;
	}
}