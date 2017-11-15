package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.FieldSubgroupDef;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class FieldSubgroupDefToFieldSubgroupDefModelMapper extends BaseDefMapperToBaseDefModelMapper<FieldSubgroupDef, FieldSubgroupDefModel> {

	public FieldSubgroupDefToFieldSubgroupDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void map(FieldSubgroupDef source, FieldSubgroupDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(FieldSubgroupDefModel source, FieldSubgroupDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldSubgroupDefModel> getToClass() {
		return FieldSubgroupDefModel.class;
	}

	@Override
	protected Class<FieldSubgroupDef> getFromClass() {
		return FieldSubgroupDef.class;
	}
}
