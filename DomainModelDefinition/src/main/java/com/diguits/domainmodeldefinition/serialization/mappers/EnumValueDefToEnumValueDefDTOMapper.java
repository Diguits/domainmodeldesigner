package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.EnumValueDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EnumValueDefDTO;
import com.diguits.common.mapping.IMapperProvider;

public class EnumValueDefToEnumValueDefDTOMapper extends BaseDefToBaseDefDTOMapper<EnumValueDef, EnumValueDefDTO> {

	public EnumValueDefToEnumValueDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	protected Class<EnumValueDefDTO> getToClass() {
		return EnumValueDefDTO.class;
	}

	@Override
	protected Class<EnumValueDef> getFromClass() {
		return EnumValueDef.class;
	}
}
