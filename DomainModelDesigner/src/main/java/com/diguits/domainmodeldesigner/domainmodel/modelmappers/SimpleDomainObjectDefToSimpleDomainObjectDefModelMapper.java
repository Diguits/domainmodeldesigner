package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.SimpleDomainObjectDef;
import com.diguits.domainmodeldesigner.domainmodel.models.SimpleDomainObjectDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public abstract class SimpleDomainObjectDefToSimpleDomainObjectDefModelMapper<TFrom extends SimpleDomainObjectDef, TTo extends SimpleDomainObjectDefModel> extends BaseDefMapperToBaseDefModelMapper<TFrom, TTo> {

	public SimpleDomainObjectDefToSimpleDomainObjectDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void map(TFrom source, TTo target, MappingContext context) {
		if(source==null || target == null) return;
		super.map(source, target, context);
	}

	@Override
	public void mapBack(TTo source, TFrom target, MappingContext context) {
		if(source == null || target == null) return;
		super.mapBack(source, target, context);
	}
}
