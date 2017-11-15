package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public class ModuleDefProvider extends ModelProviderBase<ModuleDef, ModuleDefModel>{

	public ModuleDefProvider(IEntityDefinitionService entityDefinitionService) {
		super(entityDefinitionService);
	}

	@Override
	public List<ModuleDefModel> getModels(DomainModelDefModel domainModelDef) {
		return new ArrayList<ModuleDefModel>(domainModelDef.getAllModules());
	}

	@Override
	public ModuleDef getDefFromId(UUID modelId) {
		return entityDefinitionService.getModule(modelId);
	}

	@Override
	public List<? extends ModuleDef> getModelDefs(DomainModelDef domainModelDef) {
		return new ArrayList<ModuleDef>(domainModelDef.getAllModules());
	}
}
