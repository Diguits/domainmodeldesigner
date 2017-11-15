package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DomainModelDef extends BaseDef {
	private boolean requireLogin;
	private List<ApplicationDef> applications;
	private List<BoundedContextDef> boundedContexts;
	private List<EntityDef> entities;
	private List<ValueObjectDef> valueObjects;
	private List<EnumDef> enums;
	private List<RelationshipDef> relations;
	private List<LocaleDef> locales;
	private List<CustomFieldDef> customFields;
	private LocaleDef defaultLocale;

	public DomainModelDef() {
		super(null);
		requireLogin = true;
		applications = new ArrayList<ApplicationDef>();
		boundedContexts = new ArrayList<BoundedContextDef>();
		entities = new ArrayList<EntityDef>();
		valueObjects = new ArrayList<ValueObjectDef>();
		enums = new ArrayList<EnumDef>();
		relations = new ArrayList<RelationshipDef>();
		locales = new ArrayList<LocaleDef>();
		customFields = new ArrayList<CustomFieldDef>();
	}

	public boolean getRequireLogin() {
		return requireLogin;
	}

	public void setRequireLogin(boolean requireLogin) {
		this.requireLogin = requireLogin;
	}

	public List<ApplicationDef> getApplications() {
		return applications;
	}

	public List<BoundedContextDef> getBoundedContexts() {
		return boundedContexts;
	}

	public List<EntityDef> getEntities() {
		return entities;
	}

	public List<ValueObjectDef> getValueObjects() {
		return valueObjects;
	}

	public List<EnumDef> getEnums() {
		return enums;
	}

	public List<RelationshipDef> getRelations() {
		return relations;
	}

	public List<CustomFieldDef> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomFieldDef> customFields) {
		this.customFields = customFields;
	}

	public ApplicationDef getApplicationById(UUID applicationId) {
		int i = 0;
		while (i < applications.size() && !applications.get(i).getId().equals(applicationId))
			i++;
		return i < applications.size() ? applications.get(i) : null;
	}

	public ApplicationDef getApplicationByName(String name) {
		int i = 0;
		while (i < applications.size() && !applications.get(i).getName().equals(name))
			i++;
		return i < applications.size() ? applications.get(i) : null;
	}

	public EntityDef getEntityById(UUID entityId) {
		int i = 0;
		while (i < entities.size() && !entities.get(i).getId().equals(entityId))
			i++;
		return i < entities.size() ? entities.get(i) : null;
	}

	public EntityDef getEntityByName(String name) {
		int i = 0;
		while (i < entities.size() && !entities.get(i).getName().equals(name))
			i++;
		return i < entities.size() ? entities.get(i) : null;
	}

	public ValueObjectDef getValueObjectById(UUID valueObjectId) {
		int i = 0;
		while (i < valueObjects.size() && !valueObjects.get(i).getId().equals(valueObjectId))
			i++;
		return i < valueObjects.size() ? valueObjects.get(i) : null;
	}

	public ValueObjectDef getValueObjectByName(String name) {
		int i = 0;
		while (i < valueObjects.size() && !valueObjects.get(i).getName().equals(name))
			i++;
		return i < valueObjects.size() ? valueObjects.get(i) : null;
	}

	public EnumDef getEnumById(UUID enumId) {
		int i = 0;
		while (i < enums.size() && !enums.get(i).getId().equals(enumId))
			i++;
		return i < enums.size() ? enums.get(i) : null;
	}

	public EnumDef getEnumByName(String name) {
		int i = 0;
		while (i < enums.size() && !enums.get(i).getName().equals(name))
			i++;
		return i < enums.size() ? enums.get(i) : null;
	}

	public LocaleDef getLocaleById(UUID localeId) {
		int i = 0;
		while (i < locales.size() && !locales.get(i).getId().equals(localeId))
			i++;
		return i < locales.size() ? locales.get(i) : null;
	}

	public LocaleDef getLocaleByName(String name) {
		int i = 0;
		while (i < locales.size() && !locales.get(i).getName().equals(name))
			i++;
		return i < locales.size() ? locales.get(i) : null;
	}

	public RelationshipDef getRelationById(UUID relationId) {
		int i = 0;
		while (i < relations.size() && !relations.get(i).getId().equals(relationId))
			i++;
		return i < relations.size() ? relations.get(i) : null;
	}

	public RelationshipDef getRelationByName(String name) {
		int i = 0;
		while (i < relations.size() && !relations.get(i).getName().equals(name))
			i++;
		return i < relations.size() ? relations.get(i) : null;
	}

	public BoundedContextDef getBoundedContextById(UUID boundedContextId) {
		int i = 0;
		while (i < boundedContexts.size() && !boundedContexts.get(i).getId().equals(boundedContextId))
			i++;
		return i < boundedContexts.size() ? boundedContexts.get(i) : null;
	}

	public BoundedContextDef getBoundedContextById(String name) {
		int i = 0;
		while (i < boundedContexts.size() && !boundedContexts.get(i).getName().equals(name))
			i++;
		return i < boundedContexts.size() ? boundedContexts.get(i) : null;
	}

	public CustomFieldDef getCustomFieldById(UUID customFieldId) {
		int i = 0;
		while (i < customFields.size() && !customFields.get(i).getId().equals(customFieldId))
			i++;
		return i < customFields.size() ? customFields.get(i) : null;
	}

	public List<LocaleDef> getLocales() {
		return locales;
	}

	public void setLocales(List<LocaleDef> locales) {
		this.locales = locales;
	}

	public LocaleDef getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(LocaleDef defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public List<ModuleDef> getAllModules() {
		List<ModuleDef> result = new ArrayList<ModuleDef>();
		for (BoundedContextDef boundedContext : getBoundedContexts()) {
			result.addAll(boundedContext.getModules());
		}
		return result;
	}

	public ModuleDef getModuleById(UUID moduleId) {
		for (BoundedContextDef boundedContext : boundedContexts) {
			int i = 0;
			List<ModuleDef> modules = boundedContext.getModules();
			while (i < modules.size()) {
				if (modules.get(i).getId().equals(moduleId))
					return modules.get(i);
				i++;
			}
		}
		return null;
	}

	public ModuleDef getModuleByName(String name) {
		for (BoundedContextDef boundedContext : boundedContexts) {
			int i = 0;
			List<ModuleDef> modules = boundedContext.getModules();
			while (i < modules.size()) {
				if (modules.get(i).getName().equals(name))
					return modules.get(i);
				i++;
			}
		}
		return null;
	}

	public void accept(IEntityDefinitionVisitor visitor) {
		accept(visitor, null);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		for (ApplicationDef applicationDef : applications) {
			applicationDef.accept(visitor, this);
		}
		for (BoundedContextDef boundedContextDef : boundedContexts) {
			boundedContextDef.accept(visitor, this);
		}
		for (EntityDef entityDef : entities) {
			entityDef.accept(visitor, this);
		}
		for (RelationshipDef RelationshipDef : relations) {
			RelationshipDef.accept(visitor, this);
		}
		for (ValueObjectDef valueObject : valueObjects) {
			valueObject.accept(visitor, this);
		}
		for (EnumDef enumDef : enums) {
			enumDef.accept(visitor, this);
		}
		for (LocaleDef locale : locales) {
			locale.accept(visitor, this);
		}
		for (CustomFieldDef customField : customFields) {
			customField.accept(visitor, this);
		}
		visitor.visitDomainModelDef(this);
	}

	public List<EntityDef> getEntitiesOrderedByDependencies() throws Exception {
		List<EntityDef> source = new ArrayList<EntityDef>();
		for (int i = entities.size() - 1; i >= 0; i--)
			source.add(entities.get(i));

		List<EntityDef> target = new ArrayList<EntityDef>();
		boolean flag = true;
		while (source.size() > 0 && flag) {
			flag = false;
			for (int i = source.size() - 1; i >= 0; i--) {
				EntityDef entity = source.get(i);

				int j = 0;
				while (j < entity.getFields().size()) {
					FieldDef f = entity.getFields().get(j);
					if (f.getDataType() == DataType.Entity && !target.contains(f.getRelatedEntity())
							&& f.getIsPrincipalSide())
						break;
					j++;
				}
				if (j < entity.getFields().size())
					continue;
				flag = true;
				target.add(entity);
				source.remove(entity);
			}
		}
		if (source.size() > 0) {
			StringBuilder entities = new StringBuilder(source.get(0).getName());
			for (int i = 1; i < source.size(); i++) {
				entities.append(", ");
				entities.append(source.get(i).getName());
			}
			throw new Exception("Exists circular dependencies " + entities.toString());
		}
		return target;
	}
}
