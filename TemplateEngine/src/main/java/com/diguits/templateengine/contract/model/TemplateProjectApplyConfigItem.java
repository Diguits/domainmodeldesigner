package com.diguits.templateengine.contract.model;

import java.util.List;
import java.util.UUID;

public abstract class TemplateProjectApplyConfigItem{
	protected UUID id;
	protected String name;
	protected String description;
	protected TemplateProjectApplyConfigItem owner;

	public TemplateProjectApplyConfigItem() {
		super();
		id = UUID.randomUUID();
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

	public TemplateProjectApplyConfigItem getOwner() {
		return owner;
	}

	public abstract List<? extends TemplateProjectApplyConfigItem> getItems();
}
