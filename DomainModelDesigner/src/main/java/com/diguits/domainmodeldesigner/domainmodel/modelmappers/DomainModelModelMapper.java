package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import java.util.Optional;
import java.util.UUID;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldefinition.definitions.DataType;
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
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipPartDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FillModelOwnersVisitor;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainObjectDefModel;
import com.google.inject.Inject;

import javafx.collections.FXCollections;

public class DomainModelModelMapper implements IDomainModelModelMapper {

	@Inject
	private IMapperProvider mapperProvider;

	private CustomFieldDefToCustomFieldDefModelMapper customFieldDefToCustomFieldDefModelMapper;
	private LocaleDefToLocaleDefModelMapper localeDefToLocaleDefModelMapper;

	@Inject
	public DomainModelModelMapper() {
		super();
	}

	public CustomFieldDefToCustomFieldDefModelMapper getCustomFieldDefToCustomFieldDefModelMapper() {
		if (customFieldDefToCustomFieldDefModelMapper == null)
			customFieldDefToCustomFieldDefModelMapper = mapperProvider
					.getMapper(CustomFieldDefToCustomFieldDefModelMapper.class);
		return customFieldDefToCustomFieldDefModelMapper;
	}

	public LocaleDefToLocaleDefModelMapper getLocaleDefToLocaleDefModelMapper() {
		if (localeDefToLocaleDefModelMapper == null)
			localeDefToLocaleDefModelMapper = mapperProvider.getMapper(LocaleDefToLocaleDefModelMapper.class);
		return localeDefToLocaleDefModelMapper;
	}

	@Override
	public DomainModelDefModel map(DomainModelDef source) {
		DomainModelDefToDomainModelDefModelMapper domainModelMapper = new DomainModelDefToDomainModelDefModelMapper(mapperProvider);
		BaseDefModelMappingContext baseDefContext = CreateContext(source);
		DomainModelDefModel domainModelDefModel = domainModelMapper.map(source, baseDefContext);
		initialize(source, domainModelDefModel, baseDefContext);
		return innerMap(source, domainModelDefModel, baseDefContext);
	}

	private void initialize(DomainModelDef source, DomainModelDefModel target, BaseDefModelMappingContext baseDefContext) {
		target.getLocales().addAll(baseDefContext.getLocaleModelList());
		if (source.getDefaultLocale() != null) {
			Optional<LocaleDefModel> defaultLocale = baseDefContext.getLocaleModelList().stream()
					.filter(cf -> cf.getId().equals(source.getDefaultLocale().getId())).findAny();
			if(defaultLocale.isPresent())
				target.setDefaultLocale(defaultLocale.get());
		}
		target.getCustomFields().addAll(baseDefContext.getCustomFieldModelList());
	}

	private BaseDefModelMappingContext CreateContext(DomainModelDef domainModelDef) {
		BaseDefModelMappingContext result = new BaseDefModelMappingContext();
		for (LocaleDef item : domainModelDef.getLocales()) {
			result.register(getLocaleDefToLocaleDefModelMapper().map(item, result));
		}
		for (CustomFieldDef item : domainModelDef.getCustomFields()) {
			result.register(getCustomFieldDefToCustomFieldDefModelMapper().map(item, result));
		}
		return result;
	}

	private BaseDefModelMappingContext CreateContext(DomainModelDefModel domainModelDefModel) {
		BaseDefModelMappingContext result = new BaseDefModelMappingContext();
		for (LocaleDefModel item : domainModelDefModel.getLocales()) {
			result.register(getLocaleDefToLocaleDefModelMapper().mapBack(item, result));
		}
		for (CustomFieldDefModel item : domainModelDefModel.getCustomFields()) {
			result.register(getCustomFieldDefToCustomFieldDefModelMapper().mapBack(item, result));
		}
		return result;
	}

	@Override
	public void map(DomainModelDef source, DomainModelDefModel target) {
		DomainModelDefToDomainModelDefModelMapper domainModelMapper = new DomainModelDefToDomainModelDefModelMapper(mapperProvider);
		BaseDefModelMappingContext baseDefContext = CreateContext(source);
		domainModelMapper.map(source, target, baseDefContext);
		innerMap(source, target, baseDefContext);
		initialize(source, target, baseDefContext);
	}

	private DomainModelDefModel innerMap(DomainModelDef domainModelDef, DomainModelDefModel domainModelDefModel, BaseDefModelMappingContext context) {
		domainModelDefModel.setLocales(FXCollections.observableList(context.getLocaleModelList()));
		for (ApplicationDef application : domainModelDef.getApplications()) {
			ApplicationDefModel applicationDefModel = findApplicationModel(domainModelDefModel, application.getId());
			applicationDefModel.getBoundedContexts().clear();
			for (BoundedContextDef boundedContext : application.getBoundedContexts()) {
				BoundedContextDefModel boundedContextDefModel = findBoundedContextModel(domainModelDefModel, boundedContext.getId());
				applicationDefModel.getBoundedContexts().add(boundedContextDefModel);
			}
		}

		for (BoundedContextDef boundedContext : domainModelDef.getBoundedContexts()) {
			BoundedContextDefModel boundedContextDefModel = findBoundedContextModel(domainModelDefModel, boundedContext.getId());
			for (BoundedContextDef dependency : boundedContext.getDependencies()) {
				BoundedContextDefModel boundedContextDepDefModel = findBoundedContextModel(domainModelDefModel, dependency.getId());
				boundedContextDefModel.getDependencies().add(boundedContextDepDefModel);
			}
		}

		for (EntityDef entity : domainModelDef.getEntities()) {
			UUID entityId = entity.getId();
			EntityDefModel entityDefModel = findEntityModelById(domainModelDefModel, entityId);
			if (entity.getModule() != null)
				entityDefModel.setModule(findModuleModelById(domainModelDefModel, entity.getModule().getId()));

			if (entity.getParentEntity() != null)
				entityDefModel.setParentEntity(findEntityModelById(domainModelDefModel, entity.getParentEntity().getId()));

			if (entity.getAggregateEntity() != null)
				entityDefModel.setAggregateEntity(findEntityModelById(domainModelDefModel, entity.getAggregateEntity().getId()));

			for (FieldDef field : entity.getFields()) {
				FieldDefModel fieldDefModel = findFieldModelById(entityDefModel, field.getId());
				if (field.getEnumDef() != null)
					fieldDefModel.setEnumDef(findEnumModelById(domainModelDefModel, field.getEnumDef().getId()));
				if (field.getFieldGroup() != null) {
					fieldDefModel.setFieldGroup(findFieldGroupModelById(entityDefModel, field.getFieldGroup().getId()));
					if (field.getFieldSubgroup() != null)
						fieldDefModel.setFieldSubgroup(findFieldSubgroupModelById(fieldDefModel.getFieldGroup(),
								field.getFieldSubgroup().getId()));
				}
				for (int i = 0; i < fieldDefModel.getRelationshipData().getOverrides().size(); i++) {
					RelationOverrideDefModel overrideModel = fieldDefModel.getRelationshipData().getOverrides().get(i);
					RelationOverrideDef override = field.getRelationshipData().getOverrides().get(i);
					overrideModel.setForEntity(findEntityModelById(domainModelDefModel, override.getForEntity().getId()));
					overrideModel.setRelationshipEntity(
							findEntityModelById(domainModelDefModel, override.getRelationshipEntity().getId()));
				}
				if (field.getValueObject() != null)
					fieldDefModel.setValueObject(findValueObjectModelById(domainModelDefModel, field.getValueObject().getId()));
			}

			for (IndexDef index : entity.getIndexes()) {
				IndexDefModel indexDefModel = findIndexModelById(entityDefModel, index.getId());
				indexDefModel.getFields().clear();
				for (FieldDef field : index.getFields()) {
					indexDefModel.getFields().add(findFieldModelById(entityDefModel, field.getId()));
				}
			}

			entityDefModel.getPrimaryKey().clear();
			for (FieldDef field : entity.getPrimaryKey()) {
				entityDefModel.getPrimaryKey().add(findFieldModelById(entityDefModel, field.getId()));
			}
		}

		for (ValueObjectDef valueObject : domainModelDef.getValueObjects()) {
			UUID valueObjectId = valueObject.getId();
			ValueObjectDefModel valueObjectModel = findValueObjectModelById(domainModelDefModel, valueObjectId);
			if (valueObject.getModule() != null)
				valueObjectModel.setModule(findModuleModelById(domainModelDefModel, valueObject.getModule().getId()));
		}

		for (EnumDef enumDef : domainModelDef.getEnums()) {
			UUID enumId = enumDef.getId();
			EnumDefModel enumDefModel = findEnumModelById(domainModelDefModel, enumId);
			if (enumDef.getModule() != null)
				enumDefModel.setModule(findModuleModelById(domainModelDefModel, enumDef.getModule().getId()));
		}

		for (RelationshipDef relationship : domainModelDef.getRelations()) {
			RelationshipDefModel relationDefModel = findRelationModelById(domainModelDefModel, relationship.getId());
			if (relationship.getFromPart() != null) {
				// relationDefModel.setFromRelationshipPart(new
				// RelationshipPartDefModel());
				relationDefModel.getFromPart().setToRelationshipPart(relationDefModel.getToPart());
				mapRelationshipPartModel(relationship.getFromPart(), relationDefModel.getFromPart(), domainModelDefModel);

			}
			if (relationship.getToPart() != null) {
				// relationDefModel.setToRelationshipPart(new
				// RelationshipPartDefModel());
				relationDefModel.getToPart().setToRelationshipPart(relationDefModel.getFromPart());
				mapRelationshipPartModel(relationship.getToPart(), relationDefModel.getToPart(), domainModelDefModel);
			}
		}
		FillModelOwnersVisitor fillModelOwnersVisitor = new FillModelOwnersVisitor();
		domainModelDefModel.accept(fillModelOwnersVisitor, null);

		for (EntityDefModel entityDefModel : domainModelDefModel.getEntities()) {
			for (FieldDefModel fieldDefModel : entityDefModel.getFields()) {
				if (fieldDefModel.getDataType() == DataType.EntityList
						|| fieldDefModel.getDataType() == DataType.Entity) {
					if (fieldDefModel.getRelationshipData().getRelationshipPart() == null) {
						fieldDefModel.updateRelationshipPart(null, fieldDefModel.getDataType());
					}
				}
			}
		}
		domainModelDefModel.accept(fillModelOwnersVisitor, null);
		return domainModelDefModel;
	}

	private void mapRelationshipPartModel(RelationshipPartDef part, RelationshipPartDefModel partModel,
			DomainModelDefModel domainModelDefModel) {

		if (part != null && part.getDomainObject() != null) {
			DomainObjectDefModel domainObject = null;
			if(part.getDomainObject() instanceof EntityDef)
				domainObject = findEntityModelById(domainModelDefModel, part.getDomainObject().getId());
			else
				domainObject = findValueObjectModelById(domainModelDefModel, part.getDomainObject().getId());

			partModel.setDomainObject(domainObject);
			if (domainObject != null) {
				if (part.getField() != null)
					partModel.setField(findFieldModelById(domainObject, part.getField().getId()));

				if (partModel.getField() != null)
					partModel.getField().getRelationshipData().setRelationshipPart(partModel);
			}
		}

	}

	private RelationshipDefModel findRelationModelById(DomainModelDefModel domainModelDefModel, UUID relationId) {
		for (RelationshipDefModel relationDefModel : domainModelDefModel.getRelations()) {
			if (relationDefModel.getId() == relationId)
				return relationDefModel;
		}
		return null;
	}

	private IndexDefModel findIndexModelById(EntityDefModel entityDefModel, UUID indexId) {
		for (IndexDefModel indexDefModel : entityDefModel.getIndexes()) {
			if (indexDefModel.getId() == indexId)
				return indexDefModel;
		}
		return null;
	}

	private FieldSubgroupDefModel findFieldSubgroupModelById(FieldGroupDefModel fieldGroup, UUID fieldSubgroupId) {
		for (FieldSubgroupDefModel fieldSubgroupDefModel : fieldGroup.getSubgroups()) {
			if (fieldSubgroupDefModel.getId() == fieldSubgroupId)
				return fieldSubgroupDefModel;
		}
		return null;
	}

	private FieldGroupDefModel findFieldGroupModelById(EntityDefModel entityDefModel, UUID fieldGroupId) {
		for (FieldGroupDefModel fieldGroupDefModel : entityDefModel.getFieldGroups()) {
			if (fieldGroupDefModel.getId() == fieldGroupId)
				return fieldGroupDefModel;
		}
		return null;
	}

	private FieldDefModel findFieldModelById(DomainObjectDefModel domainObject, UUID fieldId) {
		for (FieldDefModel fieldDefModel : domainObject.getFields()) {
			if (fieldDefModel.getId() == fieldId)
				return fieldDefModel;
		}
		return null;
	}

	private EntityDefModel findEntityModelById(DomainModelDefModel domainModelDefModel, UUID entityId) {
		for (EntityDefModel entityDefModel : domainModelDefModel.getEntities()) {
			if (entityDefModel.getId() == entityId)
				return entityDefModel;
		}
		return null;
	}

	private ValueObjectDefModel findValueObjectModelById(DomainModelDefModel domainModelDefModel, UUID valueObjectId) {
		for (ValueObjectDefModel valueObjectDefModel : domainModelDefModel.getValueObjects()) {
			if (valueObjectDefModel.getId() == valueObjectId)
				return valueObjectDefModel;
		}
		return null;
	}

	private EnumDefModel findEnumModelById(DomainModelDefModel domainModelDefModel, UUID enumId) {
		for (EnumDefModel enumDefModel : domainModelDefModel.getEnums()) {
			if (enumDefModel.getId() == enumId)
				return enumDefModel;
		}
		return null;
	}

	private BoundedContextDefModel findBoundedContextModel(DomainModelDefModel domainModelDefModel, UUID boundedContextId) {
		for (BoundedContextDefModel boundedContextDefModel : domainModelDefModel.getBoundedContexts()) {
			if (boundedContextDefModel.getId() == boundedContextId)
				return boundedContextDefModel;
		}
		return null;
	}

	private ApplicationDefModel findApplicationModel(DomainModelDefModel domainModelDefModel, UUID applicationId) {
		for (ApplicationDefModel applicationDefModel : domainModelDefModel.getApplications()) {
			if (applicationDefModel.getId() == applicationId)
				return applicationDefModel;
		}
		return null;
	}

	private ModuleDefModel findModuleModelById(DomainModelDefModel domainModelDefModel, UUID id) {
		for (BoundedContextDefModel boundedContextDefModel : domainModelDefModel.getBoundedContexts()) {
			for (ModuleDefModel moduleDefModel : boundedContextDefModel.getModules()) {
				if (moduleDefModel.getId() == id)
					return moduleDefModel;
			}
		}
		return null;
	}

	@Override
	public DomainModelDef mapBack(DomainModelDefModel source) {
		DomainModelDefToDomainModelDefModelMapper domainModelMapper = new DomainModelDefToDomainModelDefModelMapper(mapperProvider);
		BaseDefModelMappingContext baseDefContext = CreateContext(source);
		DomainModelDef domainModelDef = domainModelMapper.mapBack(source, baseDefContext);
		initializeBack(source, domainModelDef, baseDefContext);
		return innerMapBack(source, domainModelDef, baseDefContext);
	}

	private void initializeBack(DomainModelDefModel source, DomainModelDef target, BaseDefModelMappingContext baseDefContext) {
		target.getLocales().addAll(baseDefContext.getLocaleList());
		if (source.getDefaultLocale() != null) {
			Optional<LocaleDef> defaultLocale = baseDefContext.getLocaleList().stream()
					.filter(cf -> cf.getId().equals(source.getDefaultLocale().getId())).findAny();
			if(defaultLocale.isPresent())
				target.setDefaultLocale(defaultLocale.get());
		}
		target.getCustomFields().addAll(baseDefContext.getCustomFieldList());
	}

	@Override
	public void mapBack(DomainModelDefModel source, DomainModelDef target) {
		DomainModelDefToDomainModelDefModelMapper domainModelMapper = new DomainModelDefToDomainModelDefModelMapper(mapperProvider);
		BaseDefModelMappingContext baseDefContext = CreateContext(source);
		domainModelMapper.mapBack(source, target, baseDefContext);
		innerMapBack(source, target, baseDefContext);
		initializeBack(source, target, baseDefContext);
	}

	private DomainModelDef innerMapBack(DomainModelDefModel domainModelDefModel, DomainModelDef domainModelDef, BaseDefModelMappingContext context) {
		domainModelDef.setLocales(context.getLocaleList());
		for (ApplicationDefModel application : domainModelDefModel.getApplications()) {
			ApplicationDef applicationDef = domainModelDef.getApplicationById(application.getId());
			applicationDef.getBoundedContexts().clear();
			for (BoundedContextDefModel boundedContext : application.getBoundedContexts()) {
				BoundedContextDef boundedContextDef = domainModelDef.getBoundedContextById(boundedContext.getId());
				applicationDef.getBoundedContexts().add(boundedContextDef);
			}
		}
		for (BoundedContextDefModel boundedContextDefModel : domainModelDefModel.getBoundedContexts()) {
			BoundedContextDef boundedContextDef = domainModelDef.getBoundedContextById(boundedContextDefModel.getId());
			for (BoundedContextDefModel dependency : boundedContextDefModel.getDependencies()) {
				BoundedContextDef boundedContextDepDef = domainModelDef.getBoundedContextById(dependency.getId());
				boundedContextDef.getDependencies().add(boundedContextDepDef);
			}
		}

		for (EntityDefModel entityDefModel : domainModelDefModel.getEntities()) {
			EntityDef entity = domainModelDef.getEntityById(entityDefModel.getId());
			if (entityDefModel.getModule() != null)
				entity.setModule(domainModelDef.getModuleById(entityDefModel.getModule().getId()));
			if (entityDefModel.getParentEntity() != null)
				entity.setParentEntity(domainModelDef.getEntityById(entityDefModel.getParentEntity().getId()));
			if (entityDefModel.getAggregateEntity() != null)
				entity.setAggregateEntity(domainModelDef.getEntityById(entityDefModel.getAggregateEntity().getId()));
			for (FieldDefModel fieldDefModel : entityDefModel.getFields()) {
				FieldDef fieldDef = entity.getFieldById(fieldDefModel.getId());
				if (fieldDefModel.getEnumDef() != null)
					fieldDef.setEnumDef(domainModelDef.getEnumById(fieldDefModel.getEnumDef().getId()));
				if (fieldDefModel.getFieldGroup() != null)
					fieldDef.setFieldGroup(entity.getFieldGroupById(fieldDefModel.getFieldGroup().getId()));
				if (fieldDef.getFieldGroup() != null && fieldDefModel.getFieldSubgroup() != null)
					fieldDef.setFieldSubgroup(
							fieldDef.getFieldGroup().getFieldSubgroupById(fieldDefModel.getFieldSubgroup().getId()));
				for (int i = 0; i < fieldDef.getRelationshipData().getOverrides().size(); i++) {
					RelationOverrideDef overrideDef = fieldDef.getRelationshipData().getOverrides().get(i);
					RelationOverrideDefModel overrideModel = fieldDefModel.getRelationshipData().getOverrides().get(i);
					overrideDef.setForEntity(domainModelDef.getEntityById(overrideModel.getForEntity().getId()));
					overrideDef.setRelationshipEntity(
							domainModelDef.getEntityById(overrideModel.getRelationshipEntity().getId()));
				}
				if (fieldDefModel.getValueObject() != null)
					fieldDef.setValueObject(domainModelDef.getValueObjectById(fieldDefModel.getValueObject().getId()));
			}

			for (IndexDefModel indexDefModel : entityDefModel.getIndexes()) {
				IndexDef indexDef = entity.getIndexById(indexDefModel.getId());
				indexDef.getFields().clear();
				for (FieldDefModel fieldModel : indexDefModel.getFields()) {
					indexDef.getFields().add(entity.getFieldById(fieldModel.getId()));
				}
			}

			entity.getPrimaryKey().clear();
			for (FieldDefModel fieldDefModel : entityDefModel.getPrimaryKey()) {
				entity.getPrimaryKey().add(entity.getFieldById(fieldDefModel.getId()));
			}

		}

		for (ValueObjectDefModel valueObjectDefModel : domainModelDefModel.getValueObjects()) {
			ValueObjectDef valueObject = domainModelDef.getValueObjectById(valueObjectDefModel.getId());
			if (valueObjectDefModel.getModule() != null)
				valueObject.setModule(domainModelDef.getModuleById(valueObjectDefModel.getModule().getId()));
		}

		for (EnumDefModel enumDefDefModel : domainModelDefModel.getEnums()) {
			EnumDef enumDef = domainModelDef.getEnumById(enumDefDefModel.getId());
			if (enumDefDefModel.getModule() != null)
				enumDef.setModule(domainModelDef.getModuleById(enumDefDefModel.getModule().getId()));
		}

		for (RelationshipDefModel relationshipDefModel : domainModelDefModel.getRelations()) {
			RelationshipDef relationDef = domainModelDef.getRelationById(relationshipDefModel.getId());
			if (relationshipDefModel.getFromPart() != null) {
				// relationDef.setFromRelationshipPart(new
				// RelationshipPartDef(domainModelDef));
				mapRelationshipPart(relationshipDefModel.getFromPart(), relationDef.getFromPart(), domainModelDef);
				relationDef.getFromPart().setToRelationshipPart(relationDef.getToPart());

			}
			if (relationshipDefModel.getToPart() != null) {
				// relationDef.setToRelationshipPart(new
				// RelationshipPartDef(domainModelDef));
				mapRelationshipPart(relationshipDefModel.getToPart(), relationDef.getToPart(), domainModelDef);
				relationDef.getToPart().setToRelationshipPart(relationDef.getFromPart());
			}
		}
		FillOwnersVisitor fillOwnersVisitor = new FillOwnersVisitor();
		domainModelDef.accept(fillOwnersVisitor);
		return domainModelDef;
	}

	private static void mapRelationshipPart(RelationshipPartDefModel partModel, RelationshipPartDef partDef,
			DomainModelDef domainModelDef) {

		if (partModel != null && partModel.getDomainObject() != null) {
			DomainObjectDef domainObject = null;

			if(partModel.getDomainObject() instanceof EntityDefModel)
				domainObject = domainModelDef.getEntityById(partModel.getDomainObject().getId());
			else
				domainObject = domainModelDef.getValueObjectById(partModel.getDomainObject().getId());

			if (domainObject != null) {
				if (partModel.getField() != null)
					partDef.setField(domainObject.getFieldById(partModel.getField().getId()));
				if (partDef.getField() != null)
					partDef.getField().getRelationshipData().setRelationshipPart(partDef);
			}
			partDef.setDomainObject(domainObject);
		}
	}
}
