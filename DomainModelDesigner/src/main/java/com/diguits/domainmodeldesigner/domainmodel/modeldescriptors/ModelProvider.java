package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public interface ModelProvider<TBaseDef extends BaseDef, TBaseDefModel extends BaseDefModel> {

	List<? extends TBaseDefModel> getModels(DomainModelDefModel domainModelDef);

	TBaseDef getDefFromId(UUID modelId);

	List<? extends TBaseDef> getModelDefs(DomainModelDef domainModelDef);
}
