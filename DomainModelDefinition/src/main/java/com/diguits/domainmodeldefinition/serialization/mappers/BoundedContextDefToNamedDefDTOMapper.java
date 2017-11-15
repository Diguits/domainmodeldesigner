package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.NamedDefDTO;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MapperBase;

public class BoundedContextDefToNamedDefDTOMapper extends MapperBase<BoundedContextDef, NamedDefDTO> {

	public BoundedContextDefToNamedDefDTOMapper(IMapperProvider mapperProvider) {
	}

	public void  map(BoundedContextDef source, NamedDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setId(source.getId());
		target.setName(source.getName());
	}

	public void mapBack(NamedDefDTO source, BoundedContextDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setName(source.getName());
		target.setId(source.getId());
	}

	@Override
	protected Class<NamedDefDTO> getToClass() {
		return NamedDefDTO.class;
	}

	@Override
	protected Class<BoundedContextDef> getFromClass() {
		return BoundedContextDef.class;
	}
}
