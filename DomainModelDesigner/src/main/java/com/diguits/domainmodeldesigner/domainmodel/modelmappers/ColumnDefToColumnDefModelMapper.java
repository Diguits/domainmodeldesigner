package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.ColumnDef;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class ColumnDefToColumnDefModelMapper extends BaseDefMapperToBaseDefModelMapper<ColumnDef, ColumnDefModel> {

	public ColumnDefToColumnDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void map(ColumnDef source, ColumnDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setPath(source.getPath());
		target.setWidthWeight(source.getWidthWeight());
		target.setVisible(source.getVisible());
		target.setCanFilter(source.getCanFilter());
		target.setCanOrder(source.getCanOrder());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(ColumnDefModel source, ColumnDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setPath(source.getPath());
		target.setWidthWeight(source.getWidthWeight());
		target.setVisible(source.getVisible());
		target.setCanFilter(source.getCanFilter());
		target.setCanOrder(source.getCanOrder());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<ColumnDefModel> getToClass() {
		return ColumnDefModel.class;
	}

	@Override
	protected Class<ColumnDef> getFromClass() {
		return ColumnDef.class;
	}
}
