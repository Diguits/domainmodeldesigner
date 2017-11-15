package com.diguits.domainmodeldesigner.domainmodel.modeldescriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

public class EntityDefProvider extends ModelProviderBase<EntityDef, EntityDefModel>{

	public EntityDefProvider(IEntityDefinitionService entityDefinitionService) {
		super(entityDefinitionService);
	}

	@Override
	public List<EntityDefModel> getModels(DomainModelDefModel domainModelDef) {
		return new ArrayList<EntityDefModel>(domainModelDef.getEntities());
	}

	@Override
	public EntityDef getDefFromId(UUID modelId) {
		return entityDefinitionService.getEntity(modelId);
	}

	@Override
	public List<? extends EntityDef> getModelDefs(DomainModelDef domainModelDef) {
		return new ArrayList<EntityDef>(domainModelDef.getEntities());
	}
}
