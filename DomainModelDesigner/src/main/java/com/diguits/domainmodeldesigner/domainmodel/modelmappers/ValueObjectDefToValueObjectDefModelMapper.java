package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldefinition.definitions.ValueObjectDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class ValueObjectDefToValueObjectDefModelMapper extends DomainObjectDefToDomainObjectDefModelMapper<ValueObjectDef, ValueObjectDefModel> {

	public ValueObjectDefToValueObjectDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void map(ValueObjectDef source, ValueObjectDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		super.map(source, target, context);
		target.setPersistAsEntity(source.getPersistAsEntity());
	}

	@Override
	public void mapBack(ValueObjectDefModel source, ValueObjectDef target, MappingContext context) {
		if(source == null || target == null) return;
		super.mapBack(source, target, context);
		target.setPersistAsEntity(source.getPersistAsEntity());
	}

	@Override
	protected Class<ValueObjectDefModel> getToClass() {
		return ValueObjectDefModel.class;
	}

	@Override
	protected Class<ValueObjectDef> getFromClass() {
		return ValueObjectDef.class;
	}
}
