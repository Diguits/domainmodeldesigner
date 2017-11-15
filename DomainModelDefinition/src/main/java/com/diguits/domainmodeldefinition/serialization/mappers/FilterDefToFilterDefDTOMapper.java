package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.FilterDefDTO;
import com.diguits.domainmodeldefinition.definitions.FilterDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FilterDefToFilterDefDTOMapper extends BaseDefToBaseDefDTOMapper<FilterDef, FilterDefDTO> {

	FilterDefToFilterDefDTOMapper filterDefToFilterDefDTOMapper;

	public FilterDefToFilterDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FilterDefToFilterDefDTOMapper getFilterDefToFilterDefDTOMapper() {
		if(filterDefToFilterDefDTOMapper==null)
			filterDefToFilterDefDTOMapper = mapperProvider.getMapper(FilterDefToFilterDefDTOMapper.class);
		return filterDefToFilterDefDTOMapper;
	}

	public void  map(FilterDef source, FilterDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		target.setFilterType(source.getFilterType());
		List<FilterDefDTO> FiltersList = target.getFilters();
		for (FilterDef item : source.getFilters()) {
			FiltersList.add( getFilterDefToFilterDefDTOMapper().map(item, context));
		}
		target.setUseOperator(source.getUseOperator());
		super.map(source, target, context);
	}

	public void mapBack(FilterDefDTO source, FilterDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		target.setFilterType(source.getFilterType());
		List<FilterDef> FiltersList = target.getFilters();
		for (FilterDefDTO item : source.getFilters()) {
			FiltersList.add( getFilterDefToFilterDefDTOMapper().mapBack(item, context));
		}
		target.setUseOperator(source.getUseOperator());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FilterDefDTO> getToClass() {
		return FilterDefDTO.class;
	}

	@Override
	protected Class<FilterDef> getFromClass() {
		return FilterDef.class;
	}
}
