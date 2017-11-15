package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class RelationshipDefToRelationshipDefModelMapper extends BaseDefMapperToBaseDefModelMapper<RelationshipDef, RelationshipDefModel> {

	RelationshipPartDefToRelationshipPartDefModelMapper RelationshipPartDefToRelationshipPartDefModelMapper;

	public RelationshipDefToRelationshipDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public RelationshipPartDefToRelationshipPartDefModelMapper getRelationshipPartDefToRelationshipPartDefModelMapper() {
		if(RelationshipPartDefToRelationshipPartDefModelMapper==null)
			RelationshipPartDefToRelationshipPartDefModelMapper = mapperProvider.getMapper(RelationshipPartDefToRelationshipPartDefModelMapper.class);
		return RelationshipPartDefToRelationshipPartDefModelMapper;
	}

	@Override
	public void map(RelationshipDef source, RelationshipDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setToPart(getRelationshipPartDefToRelationshipPartDefModelMapper().map(source.getToPart(), context));
		target.setFromPart(getRelationshipPartDefToRelationshipPartDefModelMapper().map(source.getFromPart(), context));
		target.setFromSideIsPrincipal(source.getFromSideIsPrincipal());
		target.setCascadeDelete(source.getCascadeDelete());
		target.setManyToManyEntityName(source.getManyToManyEntityName());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(RelationshipDefModel source, RelationshipDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setFromPart(getRelationshipPartDefToRelationshipPartDefModelMapper().mapBack(source.getFromPart(), context));
		target.setToPart(getRelationshipPartDefToRelationshipPartDefModelMapper().mapBack(source.getToPart(), context));
		target.setFromSideIsPrincipal(source.getFromSideIsPrincipal());
		target.setCascadeDelete(source.getCascadeDelete());
		target.setManyToManyEntityName(source.getManyToManyEntityName());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<RelationshipDefModel> getToClass() {
		return RelationshipDefModel.class;
	}

	@Override
	protected Class<RelationshipDef> getFromClass() {
		return RelationshipDef.class;
	}
}
