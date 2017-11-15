package com.diguits.templateengine.serialization.dtomodel;

import java.util.UUID;

public abstract class TemplateProjectApplyConfigItemDTO{
	private UUID id;
	private String name;
	private String description;
	private UUID ownerId;

	public TemplateProjectApplyConfigItemDTO() {
		super();
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
}
