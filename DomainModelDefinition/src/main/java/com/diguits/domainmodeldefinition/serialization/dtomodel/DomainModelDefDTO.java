package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class DomainModelDefDTO extends BaseDefDTO {
	private boolean requireLogin;
	private List<ApplicationDefDTO> applications;
	private List<BoundedContextDefDTO> boundedContexts;
	private List<EntityDefDTO> entities;
	private List<ValueObjectDefDTO> valueObjects;
	private List<EnumDefDTO> enums;
	private List<RelationshipDefDTO> relations;
	private List<LocaleDefDTO> locales;
	private List<CustomFieldDefDTO> customFields;
	private UUID defaultLocaleId;

	public DomainModelDefDTO() {
		applications = new ArrayList<ApplicationDefDTO>();
		boundedContexts = new ArrayList<BoundedContextDefDTO>();
		entities = new ArrayList<EntityDefDTO>();
		valueObjects = new ArrayList<ValueObjectDefDTO>();
		enums = new ArrayList<EnumDefDTO>();
		relations = new ArrayList<RelationshipDefDTO>();
		locales = new ArrayList<LocaleDefDTO>();
		customFields = new ArrayList<CustomFieldDefDTO>();
	}

	public boolean getRequireLogin() {
		return requireLogin;
	}

	public void setRequireLogin(boolean requireLogin) {
		this.requireLogin = requireLogin;
	}

	public List<ApplicationDefDTO> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationDefDTO> applications) {
		this.applications = applications;
	}

	public List<BoundedContextDefDTO> getBoundedContexts() {
		return boundedContexts;
	}

	public void setBoundedContexts(List<BoundedContextDefDTO> boundedContexts) {
		this.boundedContexts = boundedContexts;
	}

	public List<EntityDefDTO> getEntities() {
		return entities;
	}

	public void setEntities(List<EntityDefDTO> entities) {
		this.entities = entities;
	}

	public List<ValueObjectDefDTO> getValueObjects() {
		return valueObjects;
	}

	public void setValueObjects(List<ValueObjectDefDTO> valueObjects) {
		this.valueObjects = valueObjects;
	}

	public List<EnumDefDTO> getEnums() {
		return enums;
	}

	public void setEnums(List<EnumDefDTO> enums) {
		this.enums = enums;
	}

	public List<RelationshipDefDTO> getRelations() {
		return relations;
	}

	public void setRelations(List<RelationshipDefDTO> relations) {
		this.relations = relations;
	}

	public List<LocaleDefDTO> getLocales() {
		return locales;
	}

	public void setLocales(List<LocaleDefDTO> locales) {
		this.locales = locales;
	}

	public List<CustomFieldDefDTO> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomFieldDefDTO> customFields) {
		this.customFields = customFields;
	}

	public UUID getDefaultLocaleId() {
		return defaultLocaleId;
	}

	public void setDefaultLocaleId(UUID defaultLocaleId) {
		this.defaultLocaleId = defaultLocaleId;
	}

	public EntityDefDTO getEntityById(UUID entityId) {
		int i = 0;
		while (i < entities.size() && !entities.get(i).getId().equals(entityId))
			i++;
		return i < entities.size() ? entities.get(i) : null;
	}
}
