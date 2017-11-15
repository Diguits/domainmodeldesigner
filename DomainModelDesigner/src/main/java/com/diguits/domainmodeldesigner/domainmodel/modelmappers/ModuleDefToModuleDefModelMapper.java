package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.common.mapping.IMapperProvider;

public class ModuleDefToModuleDefModelMapper extends BaseDefMapperToBaseDefModelMapper<ModuleDef, ModuleDefModel> {

	public ModuleDefToModuleDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	protected Class<ModuleDefModel> getToClass() {
		return ModuleDefModel.class;
	}

	@Override
	protected Class<ModuleDef> getFromClass() {
		return ModuleDef.class;
	}
}
