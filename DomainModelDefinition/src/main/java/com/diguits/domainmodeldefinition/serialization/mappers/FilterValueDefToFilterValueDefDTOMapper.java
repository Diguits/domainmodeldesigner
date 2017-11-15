package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.FilterValueDefDTO;
import com.diguits.domainmodeldefinition.definitions.FilterValueDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FilterValueDefToFilterValueDefDTOMapper extends BaseDefToBaseDefDTOMapper<FilterValueDef, FilterValueDefDTO> {
	FilterValueDefToFilterValueDefDTOMapper filterDefToFilterValueDefDTOMapper;

	public FilterValueDefToFilterValueDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FilterValueDefToFilterValueDefDTOMapper getFilterValueDefToFilterValueDefDTOMapper() {
		if(filterDefToFilterValueDefDTOMapper==null)
			filterDefToFilterValueDefDTOMapper = mapperProvider.getMapper(FilterValueDefToFilterValueDefDTOMapper.class);
		return filterDefToFilterValueDefDTOMapper;
	}

	public void  map(FilterValueDef source, FilterValueDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		target.setOperator(source.getOperator());
		target.setValue(source.getValue());
		target.setValue2(source.getValue2());
		List<FilterValueDefDTO> FiltersList = target.getFilterValues();
		for (FilterValueDef item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefDTOMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	public void mapBack(FilterValueDefDTO source, FilterValueDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		target.setOperator(source.getOperator());
		target.setValue(source.getValue());
		target.setValue2(source.getValue2());
		List<FilterValueDef> FiltersList = target.getFilterValues();
		for (FilterValueDefDTO item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefDTOMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FilterValueDefDTO> getToClass() {
		return FilterValueDefDTO.class;
	}

	@Override
	protected Class<FilterValueDef> getFromClass() {
		return FilterValueDef.class;
	}
}
