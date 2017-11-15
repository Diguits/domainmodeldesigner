package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldefinition.definitions.IndexDef;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;

public class IndexDefToIndexDefModelMapper extends BaseDefMapperToBaseDefModelMapper<IndexDef, IndexDefModel> {

	FieldDefToFieldDefModelMapper fieldDefToFieldDefModelMapper;

	public IndexDefToIndexDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(IndexDef source, IndexDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setUnique(source.getUnique());
		super.map(source, target, context);
	}

	public void mapBack(IndexDefModel source, IndexDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setUnique(source.getUnique());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<IndexDefModel> getToClass() {
		return IndexDefModel.class;
	}

	@Override
	protected Class<IndexDef> getFromClass() {
		return IndexDef.class;
	}
}
