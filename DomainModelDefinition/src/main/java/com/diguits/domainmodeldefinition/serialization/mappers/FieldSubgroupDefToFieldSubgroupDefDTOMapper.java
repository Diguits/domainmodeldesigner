package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.FieldSubgroupDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldSubgroupDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class FieldSubgroupDefToFieldSubgroupDefDTOMapper extends BaseDefToBaseDefDTOMapper<FieldSubgroupDef, FieldSubgroupDefDTO> {

	public FieldSubgroupDefToFieldSubgroupDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(FieldSubgroupDef source, FieldSubgroupDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		super.map(source, target, context);
	}

	public void mapBack(FieldSubgroupDefDTO source, FieldSubgroupDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldSubgroupDefDTO> getToClass() {
		return FieldSubgroupDefDTO.class;
	}

	@Override
	protected Class<FieldSubgroupDef> getFromClass() {
		return FieldSubgroupDef.class;
	}
}
