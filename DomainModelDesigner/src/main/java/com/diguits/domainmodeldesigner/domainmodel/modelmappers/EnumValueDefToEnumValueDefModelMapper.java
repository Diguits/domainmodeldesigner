package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.EnumValueDef;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.common.mapping.IMapperProvider;

public class EnumValueDefToEnumValueDefModelMapper extends BaseDefMapperToBaseDefModelMapper<EnumValueDef, EnumValueDefModel> {

	public EnumValueDefToEnumValueDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	protected Class<EnumValueDefModel> getToClass() {
		return EnumValueDefModel.class;
	}

	@Override
	protected Class<EnumValueDef> getFromClass() {
		return EnumValueDef.class;
	}
}
