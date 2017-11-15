package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.BoundedContextDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ModuleDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class BoundedContextDefToBoundedContextDefDTOMapper extends BaseDefToBaseDefDTOMapper<BoundedContextDef, BoundedContextDefDTO> {

	ModuleDefToModuleDefDTOMapper moduleDefToModuleDefDTOMapper;

	public BoundedContextDefToBoundedContextDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public ModuleDefToModuleDefDTOMapper getModuleDefToModuleDefDTOMapper() {
		if(moduleDefToModuleDefDTOMapper==null)
			moduleDefToModuleDefDTOMapper = mapperProvider.getMapper(ModuleDefToModuleDefDTOMapper.class);
		return moduleDefToModuleDefDTOMapper;
	}


	public void  map(BoundedContextDef source, BoundedContextDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setMandatoryInLoad(source.getMandatoryInLoad());
		List<ModuleDefDTO> ModulesList = target.getModules();
		for (ModuleDef item : source.getModules()) {
			ModulesList.add( getModuleDefToModuleDefDTOMapper().map(item, context));
		}
		target.setIncludedInLicense(source.getIncludedInLicense());
		target.setPrefix(source.getPrefix());
		super.map(source, target, context);
	}

	public void mapBack(BoundedContextDefDTO source, BoundedContextDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setIncludedInLicense(source.getIncludedInLicense());
		target.setPrefix(source.getPrefix());
		target.setMandatoryInLoad(source.getMandatoryInLoad());
		List<ModuleDef> ModulesList = target.getModules();
		for (ModuleDefDTO item : source.getModules()) {
			ModulesList.add( getModuleDefToModuleDefDTOMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<BoundedContextDefDTO> getToClass() {
		return BoundedContextDefDTO.class;
	}

	@Override
	protected Class<BoundedContextDef> getFromClass() {
		return BoundedContextDef.class;
	}
}
