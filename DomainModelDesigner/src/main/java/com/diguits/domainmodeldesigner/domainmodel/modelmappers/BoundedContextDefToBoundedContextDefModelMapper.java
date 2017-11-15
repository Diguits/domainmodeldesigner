package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class BoundedContextDefToBoundedContextDefModelMapper extends BaseDefMapperToBaseDefModelMapper<BoundedContextDef, BoundedContextDefModel> {

	ModuleDefToModuleDefModelMapper moduleDefToModuleDefModelMapper;

	public BoundedContextDefToBoundedContextDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public ModuleDefToModuleDefModelMapper getModuleDefToModuleDefModelMapper() {
		if(moduleDefToModuleDefModelMapper==null)
			moduleDefToModuleDefModelMapper = mapperProvider.getMapper(ModuleDefToModuleDefModelMapper.class);
		return moduleDefToModuleDefModelMapper;
	}

	@Override
	public void  map(BoundedContextDef source, BoundedContextDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setIncludedInLicense(source.getIncludedInLicense());
		target.setPrefix(source.getPrefix());
		target.setMandatoryInLoad(source.getMandatoryInLoad());
		List<ModuleDefModel> ModulesList = target.getModules();
		for (ModuleDef item : source.getModules()) {
			ModulesList.add( getModuleDefToModuleDefModelMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	@Override
	public void mapBack(BoundedContextDefModel source, BoundedContextDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setIncludedInLicense(source.getIncludedInLicense());
		target.setPrefix(source.getPrefix());
		target.setMandatoryInLoad(source.getMandatoryInLoad());
		List<ModuleDef> ModulesList = target.getModules();
		for (ModuleDefModel item : source.getModules()) {
			ModulesList.add( getModuleDefToModuleDefModelMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<BoundedContextDefModel> getToClass() {
		return BoundedContextDefModel.class;
	}

	@Override
	protected Class<BoundedContextDef> getFromClass() {
		return BoundedContextDef.class;
	}
}
