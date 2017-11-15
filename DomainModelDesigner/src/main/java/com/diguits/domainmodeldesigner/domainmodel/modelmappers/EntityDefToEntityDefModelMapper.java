package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.FilterDef;
import com.diguits.domainmodeldefinition.definitions.ColumnDef;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterDefModel;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class EntityDefToEntityDefModelMapper extends DomainObjectDefToDomainObjectDefModelMapper<EntityDef, EntityDefModel> {

	FilterDefToFilterDefModelMapper filterDefToFilterDefModelMapper;
	ColumnDefToColumnDefModelMapper columnDefToColumnDefModelMapper;

	public EntityDefToEntityDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FilterDefToFilterDefModelMapper getFilterDefToFilterDefModelMapper() {
		if(filterDefToFilterDefModelMapper==null)
			filterDefToFilterDefModelMapper = mapperProvider.getMapper(FilterDefToFilterDefModelMapper.class);
		return filterDefToFilterDefModelMapper;
	}
	public ColumnDefToColumnDefModelMapper getColumnDefToColumnDefModelMapper() {
		if(columnDefToColumnDefModelMapper==null)
			columnDefToColumnDefModelMapper = mapperProvider.getMapper(ColumnDefToColumnDefModelMapper.class);
		return columnDefToColumnDefModelMapper;
	}

	@Override
	public void map(EntityDef source, EntityDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setIsMasterDetail(source.getIsMasterDetail());
		target.setUseVisualInterface(source.getUseVisualInterface());
		List<ColumnDefModel> DefaultColumnsList = target.getDefaultColumns();
		for (ColumnDef item : source.getDefaultColumns()) {
			DefaultColumnsList.add( getColumnDefToColumnDefModelMapper().map(item, context));
		}
		target.setIsHierarchial(source.getIsHierarchial());
		List<FilterDefModel> DefaultFiltersList = target.getDefaultFilters();
		for (FilterDef item : source.getDefaultFilters()) {
			DefaultFiltersList.add( getFilterDefToFilterDefModelMapper().map(item, context));
		}
		target.setUseService(source.getUseService());
		target.setIsAbstract(source.getIsAbstract());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(EntityDefModel source, EntityDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setIsMasterDetail(source.getIsMasterDetail());
		target.setUseVisualInterface(source.getUseVisualInterface());
		List<ColumnDef> DefaultColumnsList = target.getDefaultColumns();
		for (ColumnDefModel item : source.getDefaultColumns()) {
			DefaultColumnsList.add( getColumnDefToColumnDefModelMapper().mapBack(item, context));
		}
		target.setIsHierarchial(source.getIsHierarchial());
		List<FilterDef> DefaultFiltersList = target.getDefaultFilters();
		for (FilterDefModel item : source.getDefaultFilters()) {
			DefaultFiltersList.add( getFilterDefToFilterDefModelMapper().mapBack(item, context));
		}
		target.setUseService(source.getUseService());
		target.setIsAbstract(source.getIsAbstract());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<EntityDefModel> getToClass() {
		return EntityDefModel.class;
	}

	@Override
	protected Class<EntityDef> getFromClass() {
		return EntityDef.class;
	}
}
