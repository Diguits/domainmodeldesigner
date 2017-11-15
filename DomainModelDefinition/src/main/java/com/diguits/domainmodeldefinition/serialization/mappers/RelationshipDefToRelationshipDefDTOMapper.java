package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationshipDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class RelationshipDefToRelationshipDefDTOMapper extends BaseDefToBaseDefDTOMapper<RelationshipDef, RelationshipDefDTO> {
	RelationshipPartDefToRelationshipPartDefDTOMapper RelationshipPartDefToRelationshipPartDefDTOMapper;

	public RelationshipDefToRelationshipDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public RelationshipPartDefToRelationshipPartDefDTOMapper getRelationshipPartDefToRelationshipPartDefDTOMapper() {
		if(RelationshipPartDefToRelationshipPartDefDTOMapper==null)
			RelationshipPartDefToRelationshipPartDefDTOMapper = mapperProvider.getMapper(RelationshipPartDefToRelationshipPartDefDTOMapper.class);
		return RelationshipPartDefToRelationshipPartDefDTOMapper;
	}

	public void  map(RelationshipDef source, RelationshipDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setFromRelationshipPart(getRelationshipPartDefToRelationshipPartDefDTOMapper().map(source.getFromPart(), context));
		target.setToRelationshipPart(getRelationshipPartDefToRelationshipPartDefDTOMapper().map(source.getToPart(), context));
		target.setCascadeDelete(source.getCascadeDelete());
		target.setManyToManyEntityName(source.getManyToManyEntityName());
		target.setFromSideIsPrincipal(source.getFromSideIsPrincipal());
		super.map(source, target, context);
	}

	public void mapBack(RelationshipDefDTO source, RelationshipDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setFromPart(getRelationshipPartDefToRelationshipPartDefDTOMapper().mapBack(source.getFromRelationshipPart(), context));
		target.setToPart(getRelationshipPartDefToRelationshipPartDefDTOMapper().mapBack(source.getToRelationshipPart(), context));
		target.setFromSideIsPrincipal(source.getFromSideIsPrincipal());
		target.setCascadeDelete(source.getCascadeDelete());
		target.setManyToManyEntityName(source.getManyToManyEntityName());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<RelationshipDefDTO> getToClass() {
		return RelationshipDefDTO.class;
	}

	@Override
	protected Class<RelationshipDef> getFromClass() {
		return RelationshipDef.class;
	}
}
