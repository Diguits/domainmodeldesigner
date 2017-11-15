package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.ValueObjectDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ValueObjectDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class ValueObjectDefToValueObjectDefDTOMapper extends DomainObjectDefToDomainObjectDefDTOMapper<ValueObjectDef, ValueObjectDefDTO> {

	public ValueObjectDefToValueObjectDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}
	public void  map(ValueObjectDef source, ValueObjectDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		super.map(source, target, context);
		target.setPersistAsEntity(source.getPersistAsEntity());
	}

	public void mapBack(ValueObjectDefDTO source, ValueObjectDef target, MappingContext context) {
		if(source == null || target == null) return;
		super.mapBack(source, target, context);
		target.setPersistAsEntity(source.getPersistAsEntity());
	}

	@Override
	protected Class<ValueObjectDefDTO> getToClass() {
		return ValueObjectDefDTO.class;
	}

	@Override
	protected Class<ValueObjectDef> getFromClass() {
		return ValueObjectDef.class;
	}
}
