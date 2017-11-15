package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.google.inject.Inject;

public abstract class ModelProviderBase<TBaseDef extends BaseDef, TBaseDefModel extends BaseDefModel> implements ModelProvider<TBaseDef, TBaseDefModel>{

	@Inject
	protected IEntityDefinitionService entityDefinitionService;

	public ModelProviderBase(IEntityDefinitionService entityDefinitionService) {
		super();
		this.entityDefinitionService = entityDefinitionService;
	}

}
