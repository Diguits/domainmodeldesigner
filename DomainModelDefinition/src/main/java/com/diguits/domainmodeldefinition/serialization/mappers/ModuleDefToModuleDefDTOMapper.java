package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ModuleDefDTO;
import com.diguits.common.mapping.IMapperProvider;

public class ModuleDefToModuleDefDTOMapper extends BaseDefToBaseDefDTOMapper<ModuleDef, ModuleDefDTO> {

	public ModuleDefToModuleDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	protected Class<ModuleDefDTO> getToClass() {
		return ModuleDefDTO.class;
	}

	@Override
	protected Class<ModuleDef> getFromClass() {
		return ModuleDef.class;
	}
}
