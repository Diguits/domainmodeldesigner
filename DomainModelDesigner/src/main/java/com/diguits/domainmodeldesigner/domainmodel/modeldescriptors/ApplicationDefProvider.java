package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public class ApplicationDefProvider extends ModelProviderBase<ApplicationDef, ApplicationDefModel>{

	public ApplicationDefProvider(IEntityDefinitionService entityDefinitionService) {
		super(entityDefinitionService);
	}

	@Override
	public List<ApplicationDefModel> getModels(DomainModelDefModel domainModelDef) {
		return new ArrayList<ApplicationDefModel>(domainModelDef.getApplications());
	}

	@Override
	public ApplicationDef getDefFromId(UUID modelId) {
		return entityDefinitionService.getApplication(modelId);
	}

	@Override
	public List<? extends ApplicationDef> getModelDefs(DomainModelDef domainModelDef) {
		return new ArrayList<ApplicationDef>(domainModelDef.getApplications());
	}
}
