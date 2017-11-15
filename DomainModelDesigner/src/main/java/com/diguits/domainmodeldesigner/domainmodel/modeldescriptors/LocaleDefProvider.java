package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public class LocaleDefProvider extends ModelProviderBase<LocaleDef, LocaleDefModel>{

	public LocaleDefProvider(IEntityDefinitionService entityDefinitionService) {
		super(entityDefinitionService);
	}

	@Override
	public List<LocaleDefModel> getModels(DomainModelDefModel domainModelDef) {
		return new ArrayList<LocaleDefModel>(domainModelDef.getLocales());
	}

	@Override
	public LocaleDef getDefFromId(UUID modelId) {
		return entityDefinitionService.getLocale(modelId);
	}

	@Override
	public List<? extends LocaleDef> getModelDefs(DomainModelDef domainModelDef) {
		return new ArrayList<LocaleDef>(domainModelDef.getLocales());
	}
}
