package com.diguits.domainmodeldefinition.serialization.mappers;

import java.util.Optional;
import java.util.UUID;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.domainmodeldefinition.definitions.RelationshipPartDef;
import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldefinition.definitions.FillOwnersVisitor;
import com.diguits.domainmodeldefinition.definitions.IndexDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.RelationOverrideDef;
import com.diguits.domainmodeldefinition.definitions.ValueObjectDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.definitions.DomainObjectDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ApplicationDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.CustomFieldDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EntityDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EnumDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationshipDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationshipPartDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.IndexDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.LocaleDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.BoundedContextDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.NamedDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationOverrideDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ValueObjectDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.DomainModelDefDTO;
import com.google.inject.Inject;

public class DomainModelDefMapper implements IDomainModelDefMapper {

	@Inject
	private IMapperProvider mapperProvider;

	private CustomFieldDefToCustomFieldDefDTOMapper customFieldDefToCustomFieldDefDTOMapper;
	private LocaleDefToLocaleDefDTOMapper localeDefToLocaleDefDTOMapper;

	@Inject
	public DomainModelDefMapper() {
		super();
	}

	public CustomFieldDefToCustomFieldDefDTOMapper getCustomFieldDefToCustomFieldDefDTOMapper() {
		if (customFieldDefToCustomFieldDefDTOMapper == null)
			customFieldDefToCustomFieldDefDTOMapper = mapperProvider
					.getMapper(CustomFieldDefToCustomFieldDefDTOMapper.class);
		return customFieldDefToCustomFieldDefDTOMapper;
	}

	public LocaleDefToLocaleDefDTOMapper getLocaleDefToLocaleDefDTOMapper() {
		if (localeDefToLocaleDefDTOMapper == null)
			localeDefToLocaleDefDTOMapper = mapperProvider.getMapper(LocaleDefToLocaleDefDTOMapper.class);
		return localeDefToLocaleDefDTOMapper;
	}

	@Override
	public DomainModelDefDTO map(DomainModelDef source) {
		DomainModelDefToDomainModelDefDTOMapper domainModelMapper = new DomainModelDefToDomainModelDefDTOMapper(
				mapperProvider);
		MappingContext context = null;
		DomainModelDefDTO domainModelDefDTO = domainModelMapper.map(source, context);
		return innerMap(source, domainModelDefDTO);
	}

	private DomainModelDefDTO innerMap(DomainModelDef source, DomainModelDefDTO target) {
		for (EntityDef entityDef : source.getEntities()) {
			EntityDefDTO entityDefDTO = target.getEntityById(entityDef.getId());
			entityDefDTO.getPrimaryKey().clear();
			for (FieldDef fieldDef : entityDef.getPrimaryKey()) {
				entityDefDTO.getPrimaryKey().add(fieldDef.getId());
			}
		}
		for (CustomFieldDefDTO customFieldDefDTO : target.getCustomFields()) {
			CustomFieldDef customFieldDef = source.getCustomFieldById(customFieldDefDTO.getId());
			if (customFieldDef.getOverDefClass() != null) {
				customFieldDefDTO.setOverDefClass(customFieldDef.getOverDefClass().getName());
			}
		}
		return target;
	}

	@Override
	public void map(DomainModelDef source, DomainModelDefDTO target) {
		DomainModelDefToDomainModelDefDTOMapper domainModelMapper = new DomainModelDefToDomainModelDefDTOMapper(
				mapperProvider);
		MappingContext context = null;
		domainModelMapper.map(source, target, context);
		innerMap(source, target);
	}

	@Override
	public DomainModelDef mapBack(DomainModelDefDTO source) {
		DomainModelDefToDomainModelDefDTOMapper domainModelMapper = new DomainModelDefToDomainModelDefDTOMapper(
				mapperProvider);
		BaseDefDTOMappingContext context = CreateContext(source);
		DomainModelDef domainModelDef = domainModelMapper.mapBack(source, context);
		initializeBack(source, domainModelDef, context);
		return innerMapBack(source, domainModelDef, context);
	}

	private void initializeBack(DomainModelDefDTO source, DomainModelDef target,
			BaseDefDTOMappingContext baseDefContext) {
		target.getLocales().addAll(baseDefContext.getLocaleList());
		if (source.getDefaultLocaleId() != null) {
			Optional<LocaleDef> defaultLocale = baseDefContext.getLocaleList().stream()
					.filter(cf -> cf.getId().equals(source.getDefaultLocaleId())).findAny();
			if (defaultLocale.isPresent())
				target.setDefaultLocale(defaultLocale.get());
		}
		target.getCustomFields().addAll(baseDefContext.getCustomFieldList());
	}

	private BaseDefDTOMappingContext CreateContext(DomainModelDefDTO domainModelDef) {
		BaseDefDTOMappingContext result = new BaseDefDTOMappingContext();
		for (LocaleDefDTO item : domainModelDef.getLocales()) {
			result.register(getLocaleDefToLocaleDefDTOMapper().mapBack(item, result));
		}
		for (CustomFieldDefDTO item : domainModelDef.getCustomFields()) {
			result.register(getCustomFieldDefToCustomFieldDefDTOMapper().mapBack(item, result));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private DomainModelDef innerMapBack(DomainModelDefDTO source, DomainModelDef domainModelDef,
			BaseDefDTOMappingContext context) {
		domainModelDef.setLocales(context.getLocaleList());
		domainModelDef.setCustomFields(context.getCustomFieldList());
		for (ApplicationDefDTO application : source.getApplications()) {
			ApplicationDef applicationDef = domainModelDef.getApplicationById(application.getId());
			applicationDef.getBoundedContexts().clear();
			for (NamedDefDTO boundedContext : application.getBoundedContexts()) {
				BoundedContextDef boundedContextDef = domainModelDef.getBoundedContextById(boundedContext.getId());
				applicationDef.getBoundedContexts().add(boundedContextDef);
			}
		}
		for (BoundedContextDefDTO boundedContextDefDTO : source.getBoundedContexts()) {
			BoundedContextDef boundedContextDef = domainModelDef.getBoundedContextById(boundedContextDefDTO.getId());
			for (UUID dependency : boundedContextDefDTO.getDependencies()) {
				BoundedContextDef boundedContextDepDef = domainModelDef.getBoundedContextById(dependency);
				boundedContextDef.getDependencies().add(boundedContextDepDef);
			}
		}
		for (EntityDefDTO entityDefDTO : source.getEntities()) {
			EntityDef entity = domainModelDef.getEntityById(entityDefDTO.getId());
			entity.setModule(domainModelDef.getModuleById(entityDefDTO.getModuleId()));
			entity.setParentEntity(domainModelDef.getEntityById(entityDefDTO.getParentEntityId()));
			entity.setAggregateEntity(domainModelDef.getEntityById(entityDefDTO.getAggregateEntityId()));
			for (FieldDefDTO fieldDefDTO : entityDefDTO.getFields()) {
				FieldDef fieldDef = entity.getFieldById(fieldDefDTO.getId());
				fieldDef.setEnumDef(domainModelDef.getEnumById(fieldDefDTO.getEnumDefId()));
				fieldDef.setFieldGroup(entity.getFieldGroupById(fieldDefDTO.getFieldGroupId()));
				if (fieldDef.getFieldGroup() != null)
					fieldDef.setFieldSubgroup(
							fieldDef.getFieldGroup().getFieldSubgroupById(fieldDefDTO.getFieldSubgroupId()));
				if (fieldDef.getRelationshipData() != null) {
					for (int i = 0; i < fieldDef.getRelationshipData().getOverrides().size(); i++) {
						RelationOverrideDef overrideDef = fieldDef.getRelationshipData().getOverrides().get(i);
						RelationOverrideDefDTO overrideDTO = fieldDefDTO.getRelationshipData().getOverrides().get(i);
						overrideDef.setForEntity(domainModelDef.getEntityById(overrideDTO.getForEntityId()));
						overrideDef
								.setRelationshipEntity(
										domainModelDef.getEntityById(overrideDTO.getRelationshipEntityId()));
					}
				}
				if(fieldDefDTO.getValueObjectId()!=null){
					fieldDef.setValueObject(domainModelDef.getValueObjectById(fieldDefDTO.getValueObjectId()));
				}
			}

			for (IndexDefDTO indexDefDTO : entityDefDTO.getIndexes()) {
				IndexDef indexDef = entity.getIndexById(indexDefDTO.getId());
				indexDef.getFields().clear();
				for (NamedDefDTO fieldId : indexDefDTO.getFields()) {
					indexDef.getFields().add(entity.getFieldById(fieldId.getId()));
				}
			}

			entity.getPrimaryKey().clear();
			for (UUID fieldId : entityDefDTO.getPrimaryKey()) {
				entity.getPrimaryKey().add(entity.getFieldById(fieldId));
			}
		}

		for (EnumDefDTO enumDefDTO : source.getEnums()) {
			EnumDef enumDef = domainModelDef.getEnumById(enumDefDTO.getId());
			enumDef.setModule(domainModelDef.getModuleById(enumDefDTO.getModuleId()));
		}

		for (ValueObjectDefDTO valueObjectDefDTO : source.getValueObjects()) {
			ValueObjectDef valueObject = domainModelDef.getValueObjectById(valueObjectDefDTO.getId());
			valueObject.setModule(domainModelDef.getModuleById(valueObjectDefDTO.getModuleId()));
		}

		for (RelationshipDefDTO relationshipDefDTO : source.getRelations()) {
			RelationshipDef relationDef = domainModelDef.getRelationById(relationshipDefDTO.getId());
			mapRelationshipPart(relationshipDefDTO.getFromRelationshipPart(), relationDef.getFromPart(),
					domainModelDef);
			mapRelationshipPart(relationshipDefDTO.getToRelationshipPart(), relationDef.getToPart(), domainModelDef);
		}

		for (CustomFieldDefDTO customFieldDefDTO : source.getCustomFields()) {
			CustomFieldDef customFieldDef = domainModelDef.getCustomFieldById(customFieldDefDTO.getId());
			if (customFieldDefDTO.getOverDefClass() != null && !customFieldDefDTO.getOverDefClass().isEmpty()) {
				Class<? extends BaseDef> overDefClass = null;
				try {
					overDefClass = (Class<? extends BaseDef>) Class.forName(customFieldDefDTO.getOverDefClass());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				customFieldDef.setOverDefClass((Class<? extends BaseDef>) overDefClass);
			}
		}
		FillOwnersVisitor fillOwnersVisitor = new FillOwnersVisitor();
		domainModelDef.accept(fillOwnersVisitor);
		return domainModelDef;
	}

	@Override
	public void mapBack(DomainModelDefDTO source, DomainModelDef target) {
		DomainModelDefToDomainModelDefDTOMapper domainModelMapper = new DomainModelDefToDomainModelDefDTOMapper(
				mapperProvider);
		BaseDefDTOMappingContext context = CreateContext(source);
		domainModelMapper.mapBack(source, target, context);
		innerMapBack(source, target, context);
		initializeBack(source, target, context);
	}

	private static void mapRelationshipPart(RelationshipPartDefDTO partDtO, RelationshipPartDef partDef,
			DomainModelDef domainModelDef) {
		if (partDtO != null && partDtO.getDomainObjectId() != null) {
			DomainObjectDef domainObject = null;
			if (!partDtO.getDomainObjectIsValueObject())
				domainObject = domainModelDef.getEntityById(partDtO.getDomainObjectId());
			else
				domainObject = domainModelDef.getValueObjectById(partDtO.getDomainObjectId());

			if (domainObject != null) {
				partDef.setField(domainObject.getFieldById(partDtO.getFieldId()));
				if (partDef.getField() != null)
					partDef.getField().getRelationshipData().setRelationshipPart(partDef);
			}
			partDef.setDomainObject(domainObject);
		}
	}
}
