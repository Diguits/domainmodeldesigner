package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.ValueObjectDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class DomainModelDefToDomainModelDefModelMapper extends BaseDefMapperToBaseDefModelMapper<DomainModelDef, DomainModelDefModel> {

	EntityDefToEntityDefModelMapper entityDefToEntityDefModelMapper;
	ApplicationDefToApplicationDefModelMapper applicationDefToApplicationDefModelMapper;
	RelationshipDefToRelationshipDefModelMapper RelationshipDefToRelationshipDefModelMapper;
	EnumDefToEnumDefModelMapper enumDefToEnumDefModelMapper;
	ValueObjectDefToValueObjectDefModelMapper entityDefToValueObjectDefModelMapper;
	BoundedContextDefToBoundedContextDefModelMapper boundedContextDefToBoundedContextDefModelMapper;

	public DomainModelDefToDomainModelDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public EntityDefToEntityDefModelMapper getEntityDefToEntityDefModelMapper() {
		if(entityDefToEntityDefModelMapper==null)
			entityDefToEntityDefModelMapper = mapperProvider.getMapper(EntityDefToEntityDefModelMapper.class);
		return entityDefToEntityDefModelMapper;
	}

	public ApplicationDefToApplicationDefModelMapper getApplicationDefToApplicationDefModelMapper() {
		if(applicationDefToApplicationDefModelMapper==null)
			applicationDefToApplicationDefModelMapper = mapperProvider.getMapper(ApplicationDefToApplicationDefModelMapper.class);
		return applicationDefToApplicationDefModelMapper;
	}

	public RelationshipDefToRelationshipDefModelMapper getRelationshipDefToRelationshipDefModelMapper() {
		if(RelationshipDefToRelationshipDefModelMapper==null)
			RelationshipDefToRelationshipDefModelMapper = mapperProvider.getMapper(RelationshipDefToRelationshipDefModelMapper.class);
		return RelationshipDefToRelationshipDefModelMapper;
	}

	public ValueObjectDefToValueObjectDefModelMapper getValueObjectDefToValueObjectDefModelMapper() {
		if(entityDefToValueObjectDefModelMapper==null)
			entityDefToValueObjectDefModelMapper = mapperProvider.getMapper(ValueObjectDefToValueObjectDefModelMapper.class);
		return entityDefToValueObjectDefModelMapper;
	}

	public EnumDefToEnumDefModelMapper getEnumDefToEnumDefModelMapper() {
		if(enumDefToEnumDefModelMapper==null)
			enumDefToEnumDefModelMapper = mapperProvider.getMapper(EnumDefToEnumDefModelMapper.class);
		return enumDefToEnumDefModelMapper;
	}

	public BoundedContextDefToBoundedContextDefModelMapper getBoundedContextDefToBoundedContextDefModelMapper() {
		if(boundedContextDefToBoundedContextDefModelMapper==null)
			boundedContextDefToBoundedContextDefModelMapper = mapperProvider.getMapper(BoundedContextDefToBoundedContextDefModelMapper.class);
		return boundedContextDefToBoundedContextDefModelMapper;
	}

	public DomainModelDefModel map(DomainModelDef source, MappingContext context) {
		if(source==null) return null;
		DomainModelDefModel target = new DomainModelDefModel();
		map(source, target, context);
		return target;
	}

	public void map(DomainModelDef source, DomainModelDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		List<ApplicationDefModel> ApplicationsList = target.getApplications();
		for (ApplicationDef item : source.getApplications()) {
			ApplicationsList.add( getApplicationDefToApplicationDefModelMapper().map(item, context));
		}
		List<BoundedContextDefModel> BoundedContextsList = target.getBoundedContexts();
		for (BoundedContextDef item : source.getBoundedContexts()) {
			BoundedContextsList.add( getBoundedContextDefToBoundedContextDefModelMapper().map(item, context));
		}
		List<RelationshipDefModel> RelationsList = target.getRelations();
		for (RelationshipDef item : source.getRelations()) {
			RelationsList.add( getRelationshipDefToRelationshipDefModelMapper().map(item, context));
		}
		List<EntityDefModel> EntitiesList = target.getEntities();
		for (EntityDef item : source.getEntities()) {
			EntitiesList.add( getEntityDefToEntityDefModelMapper().map(item, context));
		}
		List<ValueObjectDefModel> ValueObjectsList = target.getValueObjects();
		for (ValueObjectDef item : source.getValueObjects()) {
			ValueObjectsList.add( getValueObjectDefToValueObjectDefModelMapper().map(item, context));
		}
		List<EnumDefModel> EnumsList = target.getEnums();
		for (EnumDef item : source.getEnums()) {
			EnumsList.add( getEnumDefToEnumDefModelMapper().map(item, context));
		}
		target.setRequireLogin(source.getRequireLogin());
		super.map(source, target, context);
	}

	public void mapBack(DomainModelDefModel source, DomainModelDef target, MappingContext context) {
		if(source == null || target == null) return;
		List<ApplicationDef> ApplicationsList = target.getApplications();
		for (ApplicationDefModel item : source.getApplications()) {
			ApplicationsList.add( getApplicationDefToApplicationDefModelMapper().mapBack(item, context));
		}
		List<BoundedContextDef> BoundedContextsList = target.getBoundedContexts();
		for (BoundedContextDefModel item : source.getBoundedContexts()) {
			BoundedContextsList.add( getBoundedContextDefToBoundedContextDefModelMapper().mapBack(item, context));
		}
		List<RelationshipDef> RelationsList = target.getRelations();
		for (RelationshipDefModel item : source.getRelations()) {
			RelationsList.add( getRelationshipDefToRelationshipDefModelMapper().mapBack(item, context));
		}
		List<EntityDef> EntitiesList = target.getEntities();
		for (EntityDefModel item : source.getEntities()) {
			EntitiesList.add( getEntityDefToEntityDefModelMapper().mapBack(item, context));
		}
		List<ValueObjectDef> ValueObjectsList = target.getValueObjects();
		for (ValueObjectDefModel item : source.getValueObjects()) {
			ValueObjectsList.add( getValueObjectDefToValueObjectDefModelMapper().mapBack(item, context));
		}
		List<EnumDef> EnumsList = target.getEnums();
		for (EnumDefModel item : source.getEnums()) {
			EnumsList.add( getEnumDefToEnumDefModelMapper().mapBack(item, context));
		}
		target.setRequireLogin(source.getRequireLogin());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<DomainModelDefModel> getToClass() {
		return DomainModelDefModel.class;
	}

	@Override
	protected Class<DomainModelDef> getFromClass() {
		return DomainModelDef.class;
	}
}
