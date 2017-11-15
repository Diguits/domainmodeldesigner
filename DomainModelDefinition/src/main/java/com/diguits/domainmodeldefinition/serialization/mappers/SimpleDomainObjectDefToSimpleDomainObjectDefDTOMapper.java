package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.SimpleDomainObjectDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.SimpleDomainObjectDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public abstract class SimpleDomainObjectDefToSimpleDomainObjectDefDTOMapper<TFrom extends SimpleDomainObjectDef, TTo extends SimpleDomainObjectDefDTO> extends BaseDefToBaseDefDTOMapper<TFrom, TTo> {

	public SimpleDomainObjectDefToSimpleDomainObjectDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(TFrom source, TTo target, MappingContext context) {
		if(source==null || target == null) return;
		if(source.getModule()!=null)
			target.setModuleName(source.getModule().getName());
		if(source.getModule()!=null)
			target.setModuleId(source.getModule().getId());
		super.map(source, target, context);
	}

	public void mapBack(TTo source, TFrom target, MappingContext context) {
		if(source == null || target == null) return;
		super.mapBack(source, target, context);
	}
}

