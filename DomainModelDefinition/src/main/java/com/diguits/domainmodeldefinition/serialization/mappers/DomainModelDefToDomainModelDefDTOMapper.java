package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.ApplicationDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.CustomFieldDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.LocaleDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ValueObjectDefDTO;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.definitions.ValueObjectDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.BoundedContextDefDTO;
import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EntityDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.DomainModelDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationshipDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EnumDefDTO;
import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MappingContext;

import java.util.List;

public class DomainModelDefToDomainModelDefDTOMapper extends BaseDefToBaseDefDTOMapper<DomainModelDef, DomainModelDefDTO> {

	EntityDefToEntityDefDTOMapper entityDefToEntityDefDTOMapper;
	ValueObjectDefToValueObjectDefDTOMapper valueObjectDefToValueObjectDefDTOMapper;
	EnumDefToEnumDefDTOMapper enumDefToEnumDefDTOMapper;
	BoundedContextDefToBoundedContextDefDTOMapper boundedContextDefToBoundedContextDefDTOMapper;
	ApplicationDefToApplicationDefDTOMapper applicationDefToApplicationDefDTOMapper;
	RelationshipDefToRelationshipDefDTOMapper RelationshipDefToRelationshipDefDTOMapper;
	LocaleDefToLocaleDefDTOMapper localeDefToLocaleDefDTOMapper;
	CustomFieldDefToCustomFieldDefDTOMapper customFieldDefToCustomFieldDefDTOMapper;

	public DomainModelDefToDomainModelDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public EntityDefToEntityDefDTOMapper getEntityDefToEntityDefDTOMapper() {
		if(entityDefToEntityDefDTOMapper==null)
			entityDefToEntityDefDTOMapper = mapperProvider.getMapper(EntityDefToEntityDefDTOMapper.class);
		return entityDefToEntityDefDTOMapper;
	}
	public ValueObjectDefToValueObjectDefDTOMapper getValueObjectDefToValueObjectDefDTOMapper() {
		if(valueObjectDefToValueObjectDefDTOMapper==null)
			valueObjectDefToValueObjectDefDTOMapper = mapperProvider.getMapper(ValueObjectDefToValueObjectDefDTOMapper.class);
		return valueObjectDefToValueObjectDefDTOMapper;
	}
	public EnumDefToEnumDefDTOMapper getEnumDefToEnumDefDTOMapper() {
		if(enumDefToEnumDefDTOMapper==null)
			enumDefToEnumDefDTOMapper = mapperProvider.getMapper(EnumDefToEnumDefDTOMapper.class);
		return enumDefToEnumDefDTOMapper;
	}
	public BoundedContextDefToBoundedContextDefDTOMapper getBoundedContextDefToBoundedContextDefDTOMapper() {
		if(boundedContextDefToBoundedContextDefDTOMapper==null)
			boundedContextDefToBoundedContextDefDTOMapper = mapperProvider.getMapper(BoundedContextDefToBoundedContextDefDTOMapper.class);
		return boundedContextDefToBoundedContextDefDTOMapper;
	}
	public ApplicationDefToApplicationDefDTOMapper getApplicationDefToApplicationDefDTOMapper() {
		if(applicationDefToApplicationDefDTOMapper==null)
			applicationDefToApplicationDefDTOMapper = mapperProvider.getMapper(ApplicationDefToApplicationDefDTOMapper.class);
		return applicationDefToApplicationDefDTOMapper;
	}
	public RelationshipDefToRelationshipDefDTOMapper getRelationshipDefToRelationshipDefDTOMapper() {
		if(RelationshipDefToRelationshipDefDTOMapper==null)
			RelationshipDefToRelationshipDefDTOMapper = mapperProvider.getMapper(RelationshipDefToRelationshipDefDTOMapper.class);
		return RelationshipDefToRelationshipDefDTOMapper;
	}
	public LocaleDefToLocaleDefDTOMapper getLocaleDefToLocaleDefDTOMapper() {
		if(localeDefToLocaleDefDTOMapper==null)
			localeDefToLocaleDefDTOMapper = mapperProvider.getMapper(LocaleDefToLocaleDefDTOMapper.class);
		return localeDefToLocaleDefDTOMapper;
	}
	public CustomFieldDefToCustomFieldDefDTOMapper getCustomFieldDefToCustomFieldDefDTOMapper() {
		if(customFieldDefToCustomFieldDefDTOMapper==null)
			customFieldDefToCustomFieldDefDTOMapper = mapperProvider.getMapper(CustomFieldDefToCustomFieldDefDTOMapper.class);
		return customFieldDefToCustomFieldDefDTOMapper;
	}

	public void  map(DomainModelDef source, DomainModelDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		if(source.getDefaultLocale()!=null)
			target.setDefaultLocaleId(source.getDefaultLocale().getId());
		List<ApplicationDefDTO> ApplicationsList = target.getApplications();
		for (ApplicationDef item : source.getApplications()) {
			ApplicationsList.add( getApplicationDefToApplicationDefDTOMapper().map(item, context));
		}
		target.setRequireLogin(source.getRequireLogin());
		List<BoundedContextDefDTO> BoundedContextsList = target.getBoundedContexts();
		for (BoundedContextDef item : source.getBoundedContexts()) {
			BoundedContextsList.add( getBoundedContextDefToBoundedContextDefDTOMapper().map(item, context));
		}
		List<RelationshipDefDTO> RelationsList = target.getRelations();
		for (RelationshipDef item : source.getRelations()) {
			RelationsList.add( getRelationshipDefToRelationshipDefDTOMapper().map(item, context));
		}
		List<LocaleDefDTO> LocalesList = target.getLocales();
		for (LocaleDef item : source.getLocales()) {
			LocalesList.add( getLocaleDefToLocaleDefDTOMapper().map(item, context));
		}
		List<EntityDefDTO> EntitiesList = target.getEntities();
		for (EntityDef item : source.getEntities()) {
			EntitiesList.add( getEntityDefToEntityDefDTOMapper().map(item, context));
		}
		List<ValueObjectDefDTO> ValueObjectsList = target.getValueObjects();
		for (ValueObjectDef item : source.getValueObjects()) {
			ValueObjectsList.add( getValueObjectDefToValueObjectDefDTOMapper().map(item, context));
		}
		List<EnumDefDTO> EnumsList = target.getEnums();
		for (EnumDef item : source.getEnums()) {
			EnumsList.add( getEnumDefToEnumDefDTOMapper().map(item, context));
		}
		List<CustomFieldDefDTO> CustomFieldsList = target.getCustomFields();
		for (CustomFieldDef item : source.getCustomFields()) {
			CustomFieldsList.add( getCustomFieldDefToCustomFieldDefDTOMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	public void mapBack(DomainModelDefDTO source, DomainModelDef target, MappingContext context) {
		if(source == null || target == null) return;
		List<ApplicationDef> ApplicationsList = target.getApplications();
		for (ApplicationDefDTO item : source.getApplications()) {
			ApplicationsList.add( getApplicationDefToApplicationDefDTOMapper().mapBack(item, context));
		}
		List<BoundedContextDef> BoundedContextsList = target.getBoundedContexts();
		for (BoundedContextDefDTO item : source.getBoundedContexts()) {
			BoundedContextsList.add( getBoundedContextDefToBoundedContextDefDTOMapper().mapBack(item, context));
		}
		List<RelationshipDef> RelationsList = target.getRelations();
		for (RelationshipDefDTO item : source.getRelations()) {
			RelationsList.add( getRelationshipDefToRelationshipDefDTOMapper().mapBack(item, context));
		}
		List<EntityDef> EntitiesList = target.getEntities();
		for (EntityDefDTO item : source.getEntities()) {
			EntitiesList.add( getEntityDefToEntityDefDTOMapper().mapBack(item, context));
		}
		List<ValueObjectDef> ValueObjectsList = target.getValueObjects();
		for (ValueObjectDefDTO item : source.getValueObjects()) {
			ValueObjectsList.add( getValueObjectDefToValueObjectDefDTOMapper().mapBack(item, context));
		}
		List<EnumDef> EnumsList = target.getEnums();
		for (EnumDefDTO item : source.getEnums()) {
			EnumsList.add( getEnumDefToEnumDefDTOMapper().mapBack(item, context));
		}
		target.setRequireLogin(source.getRequireLogin());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<DomainModelDefDTO> getToClass() {
		return DomainModelDefDTO.class;
	}

	@Override
	protected Class<DomainModelDef> getFromClass() {
		return DomainModelDef.class;
	}
}
