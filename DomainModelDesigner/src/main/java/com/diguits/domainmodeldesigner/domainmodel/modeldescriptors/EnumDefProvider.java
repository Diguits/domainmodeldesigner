package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public class EnumDefProvider extends ModelProviderBase<EnumDef, EnumDefModel>{

	public EnumDefProvider(IEntityDefinitionService entityDefinitionService) {
		super(entityDefinitionService);
	}

	@Override
	public List<EnumDefModel> getModels(DomainModelDefModel domainModelDef) {
		return new ArrayList<EnumDefModel>(domainModelDef.getEnums());
	}

	@Override
	public EnumDef getDefFromId(UUID modelId) {
		return entityDefinitionService.getEnum(modelId);
	}

	@Override
	public List<? extends EnumDef> getModelDefs(DomainModelDef domainModelDef) {
		return new ArrayList<EnumDef>(domainModelDef.getEnums());
	}
}
