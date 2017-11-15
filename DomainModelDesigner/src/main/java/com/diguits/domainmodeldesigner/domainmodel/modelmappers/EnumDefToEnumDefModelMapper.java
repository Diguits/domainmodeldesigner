package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.EnumValueDef;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class EnumDefToEnumDefModelMapper extends SimpleDomainObjectDefToSimpleDomainObjectDefModelMapper<EnumDef, EnumDefModel> {

	EnumValueDefToEnumValueDefModelMapper enumValueDefToEnumValueDefModelMapper;

	public EnumDefToEnumDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public EnumValueDefToEnumValueDefModelMapper getEnumValueDefToEnumValueDefModelMapper() {
		if(enumValueDefToEnumValueDefModelMapper==null)
			enumValueDefToEnumValueDefModelMapper = mapperProvider.getMapper(EnumValueDefToEnumValueDefModelMapper.class);
		return enumValueDefToEnumValueDefModelMapper;
	}

	public void  map(EnumDef source, EnumDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setUseIcon(source.getUseIcon());
		target.setUseColor(source.getUseColor());
		List<EnumValueDefModel> ValuesList = target.getValues();
		for (EnumValueDef item : source.getValues()) {
			ValuesList.add( getEnumValueDefToEnumValueDefModelMapper().map(item, context));
		}
		target.setUseTitle(source.getUseTitle());
		super.map(source, target, context);
	}

	public void mapBack(EnumDefModel source, EnumDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setUseIcon(source.getUseIcon());
		target.setUseColor(source.getUseColor());
		List<EnumValueDef> ValuesList = target.getValues();
		for (EnumValueDefModel item : source.getValues()) {
			ValuesList.add( getEnumValueDefToEnumValueDefModelMapper().mapBack(item, context));
		}
		target.setUseTitle(source.getUseTitle());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<EnumDefModel> getToClass() {
		return EnumDefModel.class;
	}

	@Override
	protected Class<EnumDef> getFromClass() {
		return EnumDef.class;
	}
}
