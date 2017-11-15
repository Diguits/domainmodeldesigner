package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.RelationOverrideDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationOverrideDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class RelationOverrideDefToRelationOverrideDefDTOMapper extends BaseDefToBaseDefDTOMapper<RelationOverrideDef, RelationOverrideDefDTO> {

	public RelationOverrideDefToRelationOverrideDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(RelationOverrideDef source, RelationOverrideDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		if(source.getRelationshipEntity()!=null)
			target.setRelationshipEntityName(source.getRelationshipEntity().getName());
		if(source.getForEntity()!=null)
			target.setForEntityName(source.getForEntity().getName());
		if(source.getRelationshipEntity()!=null)
			target.setRelationshipEntityId(source.getRelationshipEntity().getId());
		if(source.getForEntity()!=null)
			target.setForEntityId(source.getForEntity().getId());
		super.map(source, target, context);
	}

	public void mapBack(RelationOverrideDefDTO source, RelationOverrideDef target, MappingContext context) {
		if(source == null || target == null) return;
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<RelationOverrideDefDTO> getToClass() {
		return RelationOverrideDefDTO.class;
	}

	@Override
	protected Class<RelationOverrideDef> getFromClass() {
		return RelationOverrideDef.class;
	}
}
