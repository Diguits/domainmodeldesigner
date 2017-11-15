package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.FilterDef;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FilterDefToFilterDefModelMapper extends BaseDefMapperToBaseDefModelMapper<FilterDef, FilterDefModel> {

	FilterDefToFilterDefModelMapper filterDefToFilterDefModelMapper;

	public FilterDefToFilterDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FilterDefToFilterDefModelMapper getFilterDefToFilterDefModelMapper() {
		if(filterDefToFilterDefModelMapper==null)
			filterDefToFilterDefModelMapper = mapperProvider.getMapper(FilterDefToFilterDefModelMapper.class);
		return filterDefToFilterDefModelMapper;
	}

	public void  map(FilterDef source, FilterDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		List<FilterDefModel> FiltersList = target.getFilters();
		for (FilterDef item : source.getFilters()) {
			FiltersList.add( getFilterDefToFilterDefModelMapper().map(item, context));
		}
		target.setFilterType(source.getFilterType());
		target.setUseOperator(source.getUseOperator());
		super.map(source, target, context);
	}

	public void mapBack(FilterDefModel source, FilterDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		target.setFilterType(source.getFilterType());
		List<FilterDef> FiltersList = target.getFilters();
		for (FilterDefModel item : source.getFilters()) {
			FiltersList.add( getFilterDefToFilterDefModelMapper().mapBack(item, context));
		}
		target.setUseOperator(source.getUseOperator());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FilterDefModel> getToClass() {
		return FilterDefModel.class;
	}

	@Override
	protected Class<FilterDef> getFromClass() {
		return FilterDef.class;
	}
}
