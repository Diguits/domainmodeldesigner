package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.FilterValueDef;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterValueDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FilterValueDefToFilterValueDefModelMapper extends BaseDefMapperToBaseDefModelMapper<FilterValueDef, FilterValueDefModel> {

	FilterValueDefToFilterValueDefModelMapper filterDefToFilterValueDefModelMapper;

	public FilterValueDefToFilterValueDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FilterValueDefToFilterValueDefModelMapper getFilterValueDefToFilterValueDefModelMapper() {
		if(filterDefToFilterValueDefModelMapper==null)
			filterDefToFilterValueDefModelMapper = mapperProvider.getMapper(FilterValueDefToFilterValueDefModelMapper.class);
		return filterDefToFilterValueDefModelMapper;
	}

	public void  map(FilterValueDef source, FilterValueDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		target.setOperator(source.getOperator());
		target.setValue(source.getValue());
		target.setValue2(source.getValue2());
		List<FilterValueDefModel> FiltersList = target.getFilterValues();
		for (FilterValueDef item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefModelMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	public void mapBack(FilterValueDefModel source, FilterValueDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setLogicalOperator(source.getLogicalOperator());
		target.setPath(source.getPath());
		target.setOperator(source.getOperator());
		target.setValue(source.getValue());
		target.setValue2(source.getValue2());
		List<FilterValueDef> FiltersList = target.getFilterValues();
		for (FilterValueDefModel item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefModelMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FilterValueDefModel> getToClass() {
		return FilterValueDefModel.class;
	}

	@Override
	protected Class<FilterValueDef> getFromClass() {
		return FilterValueDef.class;
	}
}
