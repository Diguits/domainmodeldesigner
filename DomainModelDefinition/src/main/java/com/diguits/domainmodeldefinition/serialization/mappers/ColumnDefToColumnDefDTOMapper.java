package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.ColumnDefDTO;
import com.diguits.domainmodeldefinition.definitions.ColumnDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class ColumnDefToColumnDefDTOMapper extends BaseDefToBaseDefDTOMapper<ColumnDef, ColumnDefDTO> {
	public ColumnDefToColumnDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(ColumnDef source, ColumnDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setPath(source.getPath());
		target.setWidthWeight(source.getWidthWeight());
		target.setVisible(source.getVisible());
		target.setCanFilter(source.getCanFilter());
		target.setCanOrder(source.getCanOrder());
		super.map(source, target, context);
	}

	public void mapBack(ColumnDefDTO source, ColumnDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setPath(source.getPath());
		target.setWidthWeight(source.getWidthWeight());
		target.setVisible(source.getVisible());
		target.setCanFilter(source.getCanFilter());
		target.setCanOrder(source.getCanOrder());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<ColumnDefDTO> getToClass() {
		return ColumnDefDTO.class;
	}

	@Override
	protected Class<ColumnDef> getFromClass() {
		return ColumnDef.class;
	}
}
