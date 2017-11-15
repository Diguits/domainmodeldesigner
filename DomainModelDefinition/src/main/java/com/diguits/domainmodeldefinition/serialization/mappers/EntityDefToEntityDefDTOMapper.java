package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.FilterDef;
import com.diguits.domainmodeldefinition.definitions.ColumnDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ColumnDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FilterDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EntityDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class EntityDefToEntityDefDTOMapper extends DomainObjectDefToDomainObjectDefDTOMapper<EntityDef, EntityDefDTO> {

	ColumnDefToColumnDefDTOMapper columnDefToColumnDefDTOMapper;
	FilterDefToFilterDefDTOMapper filterDefToFilterDefDTOMapper;

	public EntityDefToEntityDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public ColumnDefToColumnDefDTOMapper getColumnDefToColumnDefDTOMapper() {
		if(columnDefToColumnDefDTOMapper==null)
			columnDefToColumnDefDTOMapper = mapperProvider.getMapper(ColumnDefToColumnDefDTOMapper.class);
		return columnDefToColumnDefDTOMapper;
	}
	public FilterDefToFilterDefDTOMapper getFilterDefToFilterDefDTOMapper() {
		if(filterDefToFilterDefDTOMapper==null)
			filterDefToFilterDefDTOMapper = mapperProvider.getMapper(FilterDefToFilterDefDTOMapper.class);
		return filterDefToFilterDefDTOMapper;
	}


	public void  map(EntityDef source, EntityDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setIsMasterDetail(source.getIsMasterDetail());
		if(source.getParentEntity()!=null)
			target.setParentEntityId(source.getParentEntity().getId());
		target.setUseVisualInterface(source.getUseVisualInterface());
		target.setAdditionalOperations(source.getAdditionalOperations());
		List<ColumnDefDTO> DefaultColumnsList = target.getDefaultColumns();
		for (ColumnDef item : source.getDefaultColumns()) {
			DefaultColumnsList.add( getColumnDefToColumnDefDTOMapper().map(item, context));
		}
		target.setIsHierarchial(source.getIsHierarchial());
		List<FilterDefDTO> DefaultFiltersList = target.getDefaultFilters();
		for (FilterDef item : source.getDefaultFilters()) {
			DefaultFiltersList.add( getFilterDefToFilterDefDTOMapper().map(item, context));
		}
		if(source.getParentEntity()!=null)
			target.setParentEntityName(source.getParentEntity().getName());
		if(source.getAggregateEntity()!=null)
			target.setAggregateEntityId(source.getAggregateEntity().getId());
		if(source.getAggregateEntity()!=null)
			target.setAggregateEntityName(source.getAggregateEntity().getName());
		target.setUseService(source.getUseService());
		target.setIsAbstract(source.getIsAbstract());
		super.map(source, target, context);
	}

	public void mapBack(EntityDefDTO source, EntityDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setIsMasterDetail(source.getIsMasterDetail());
		target.setUseVisualInterface(source.getUseVisualInterface());
		target.setAdditionalOperations(source.getAdditionalOperations());
		List<ColumnDef> DefaultColumnsList = target.getDefaultColumns();
		for (ColumnDefDTO item : source.getDefaultColumns()) {
			DefaultColumnsList.add( getColumnDefToColumnDefDTOMapper().mapBack(item, context));
		}
		target.setIsHierarchial(source.getIsHierarchial());
		List<FilterDef> DefaultFiltersList = target.getDefaultFilters();
		for (FilterDefDTO item : source.getDefaultFilters()) {
			DefaultFiltersList.add( getFilterDefToFilterDefDTOMapper().mapBack(item, context));
		}
		target.setUseService(source.getUseService());
		target.setIsAbstract(source.getIsAbstract());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<EntityDefDTO> getToClass() {
		return EntityDefDTO.class;
	}

	@Override
	protected Class<EntityDef> getFromClass() {
		return EntityDef.class;
	}
}
