package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.RelationshipPartDef;
import com.diguits.domainmodeldefinition.definitions.ValueObjectDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationshipPartDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class RelationshipPartDefToRelationshipPartDefDTOMapper extends BaseDefToBaseDefDTOMapper<RelationshipPartDef, RelationshipPartDefDTO> {

	RelationOverrideDefToRelationOverrideDefDTOMapper relationOverrideDefToRelationOverrideDefDTOMapper;
	ColumnDefToColumnDefDTOMapper columnDefToColumnDefDTOMapper;
	FilterDefToFilterDefDTOMapper filterDefToFilterDefDTOMapper;

	public RelationshipPartDefToRelationshipPartDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(RelationshipPartDef source, RelationshipPartDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		if(source.getDomainObject() !=null){
			target.setDomainObjectId(source.getDomainObject().getId());
			if(source.getDomainObject() instanceof ValueObjectDef)
				target.setDomainObjectIsValueObject(true);
			else
				target.setDomainObjectIsValueObject(false);
		}

		if(source.getOwner()!=null)
			target.setOwnerId(source.getOwner().getId());
		if(source.getField()!=null)
			target.setFieldId(source.getField().getId());
		target.setRelationType(source.getRelationType());
		if(source.getToRelationshipPart()!=null)
			target.setToRelationshipPartId(source.getToRelationshipPart().getId());
		super.map(source, target, context);
	}

	public void mapBack(RelationshipPartDefDTO source, RelationshipPartDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setRelationType(source.getRelationType());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<RelationshipPartDefDTO> getToClass() {
		return RelationshipPartDefDTO.class;
	}

	@Override
	protected Class<RelationshipPartDef> getFromClass() {
		return RelationshipPartDef.class;
	}
}
