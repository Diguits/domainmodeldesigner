package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public class DomainModelDefProvider extends ModelProviderBase<DomainModelDef, DomainModelDefModel>{

	public DomainModelDefProvider(IEntityDefinitionService entityDefinitionService) {
		super(entityDefinitionService);
	}

	@Override
	public List<DomainModelDefModel> getModels(DomainModelDefModel domainModelDef) {
		ArrayList<DomainModelDefModel> result = new ArrayList<DomainModelDefModel>(1);
		result.add(domainModelDef);
		return result;
	}

	@Override
	public DomainModelDef getDefFromId(UUID modelId) {
		return entityDefinitionService.getDomainModel();
	}

	@Override
	public List<? extends DomainModelDef> getModelDefs(DomainModelDef domainModelDef) {
		ArrayList<DomainModelDef> result = new ArrayList<DomainModelDef>(1);
		result.add(domainModelDef);
		return result;
	}
}
