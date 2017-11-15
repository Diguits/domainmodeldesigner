package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public class BoundedContextDefProvider extends ModelProviderBase<BoundedContextDef, BoundedContextDefModel>{

	public BoundedContextDefProvider(IEntityDefinitionService entityDefinitionService) {
		super(entityDefinitionService);
	}

	@Override
	public List<BoundedContextDefModel> getModels(DomainModelDefModel domainModelDef) {
		return new ArrayList<BoundedContextDefModel>(domainModelDef.getBoundedContexts());
	}

	@Override
	public BoundedContextDef getDefFromId(UUID modelId) {
		return entityDefinitionService.getBoundedContext(modelId);
	}

	@Override
	public List<? extends BoundedContextDef> getModelDefs(DomainModelDef domainModelDef) {
		return new ArrayList<BoundedContextDef>(domainModelDef.getBoundedContexts());
	}
}
