package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipPartDefModel;
import com.diguits.domainmodeldefinition.definitions.RelationshipPartDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class RelationshipPartDefToRelationshipPartDefModelMapper extends BaseDefMapperToBaseDefModelMapper<RelationshipPartDef, RelationshipPartDefModel> {

	public RelationshipPartDefToRelationshipPartDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void map(RelationshipPartDef source, RelationshipPartDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setRelationType(source.getRelationType());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(RelationshipPartDefModel source, RelationshipPartDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setRelationType(source.getRelationType());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<RelationshipPartDefModel> getToClass() {
		return RelationshipPartDefModel.class;
	}

	@Override
	protected Class<RelationshipPartDef> getFromClass() {
		return RelationshipPartDef.class;
	}
}
