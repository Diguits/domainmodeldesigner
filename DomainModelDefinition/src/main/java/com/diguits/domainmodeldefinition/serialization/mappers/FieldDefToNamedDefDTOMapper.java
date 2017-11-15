package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.NamedDefDTO;
import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MapperBase;

public class FieldDefToNamedDefDTOMapper extends MapperBase<FieldDef, NamedDefDTO> {

	public FieldDefToNamedDefDTOMapper(IMapperProvider mapperProvider) {
	}

	public void  map(FieldDef source, NamedDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setId(source.getId());
		target.setName(source.getName());
	}

	public void mapBack(NamedDefDTO source, FieldDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setName(source.getName());
		target.setId(source.getId());
	}

	@Override
	protected Class<NamedDefDTO> getToClass() {
		return NamedDefDTO.class;
	}

	@Override
	protected Class<FieldDef> getFromClass() {
		return FieldDef.class;
	}
}
