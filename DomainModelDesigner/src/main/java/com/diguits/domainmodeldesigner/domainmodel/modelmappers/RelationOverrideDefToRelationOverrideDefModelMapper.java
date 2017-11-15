package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.RelationOverrideDef;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;
import com.diguits.common.mapping.IMapperProvider;

public class RelationOverrideDefToRelationOverrideDefModelMapper extends BaseDefMapperToBaseDefModelMapper<RelationOverrideDef, RelationOverrideDefModel> {

	public RelationOverrideDefToRelationOverrideDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	protected Class<RelationOverrideDefModel> getToClass() {
		return RelationOverrideDefModel.class;
	}

	@Override
	protected Class<RelationOverrideDef> getFromClass() {
		return RelationOverrideDef.class;
	}
}
