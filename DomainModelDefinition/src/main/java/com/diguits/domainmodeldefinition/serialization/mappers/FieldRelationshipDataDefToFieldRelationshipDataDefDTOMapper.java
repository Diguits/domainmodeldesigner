package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.ColumnDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.LocalizedDataDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FilterValueDefDTO;
import com.diguits.domainmodeldefinition.definitions.RelationOverrideDef;
import com.diguits.domainmodeldefinition.definitions.FieldRelationshipDataDef;
import com.diguits.domainmodeldefinition.definitions.FilterValueDef;
import com.diguits.domainmodeldefinition.definitions.ColumnDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.RelationOverrideDefDTO;
import com.diguits.domainmodeldefinition.definitions.LocalizedDataDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldRelationshipDataDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper extends BaseDefToBaseDefDTOMapper<FieldRelationshipDataDef, FieldRelationshipDataDefDTO> {

	RelationOverrideDefToRelationOverrideDefDTOMapper relationOverrideDefToRelationOverrideDefDTOMapper;
	ColumnDefToColumnDefDTOMapper columnDefToColumnDefDTOMapper;
	FilterValueDefToFilterValueDefDTOMapper filterValueDefToFilterValueDefDTOMapper;

	public FieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public RelationOverrideDefToRelationOverrideDefDTOMapper getRelationOverrideDefToRelationOverrideDefDTOMapper() {
		if(relationOverrideDefToRelationOverrideDefDTOMapper==null)
			relationOverrideDefToRelationOverrideDefDTOMapper = mapperProvider.getMapper(RelationOverrideDefToRelationOverrideDefDTOMapper.class);
		return relationOverrideDefToRelationOverrideDefDTOMapper;
	}
	public ColumnDefToColumnDefDTOMapper getColumnDefToColumnDefDTOMapper() {
		if(columnDefToColumnDefDTOMapper==null)
			columnDefToColumnDefDTOMapper = mapperProvider.getMapper(ColumnDefToColumnDefDTOMapper.class);
		return columnDefToColumnDefDTOMapper;
	}
	public FilterValueDefToFilterValueDefDTOMapper getFilterValueDefToFilterValueDefDTOMapper() {
		if(filterValueDefToFilterValueDefDTOMapper==null)
			filterValueDefToFilterValueDefDTOMapper = mapperProvider.getMapper(FilterValueDefToFilterValueDefDTOMapper.class);
		return filterValueDefToFilterValueDefDTOMapper;
	}

	public void  map(FieldRelationshipDataDef source, FieldRelationshipDataDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		List<ColumnDefDTO> ExtraColumnsList = target.getExtraColumns();
		for (ColumnDef item : source.getExtraColumns()) {
			ExtraColumnsList.add( getColumnDefToColumnDefDTOMapper().map(item, context));
		}
		List<FilterValueDefDTO> FiltersList = target.getFilterValues();
		for (FilterValueDef item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefDTOMapper().map(item, context));
		}
		List<RelationOverrideDefDTO> OverridesList = target.getOverrides();
		for (RelationOverrideDef item : source.getOverrides()) {
			OverridesList.add( getRelationOverrideDefToRelationOverrideDefDTOMapper().map(item, context));
		}
		if(source.getRelationshipPart()!=null)
			target.setRelationshipPartId(source.getRelationshipPart().getId());
		super.map(source, target, context);
	}

	public void mapBack(FieldRelationshipDataDefDTO source, FieldRelationshipDataDef target, MappingContext context) {
		if(source == null || target == null) return;
		List<LocalizedDataDef> LocalizedDatasList = target.getLocalizedDatas();
		for (LocalizedDataDefDTO item : source.getLocalizedDatas()) {
			LocalizedDatasList.add( getLocalizedDataDefToLocalizedDataDefDTOMapper().mapBack(item, context));
		}
		List<ColumnDef> ExtraColumnsList = target.getExtraColumns();
		for (ColumnDefDTO item : source.getExtraColumns()) {
			ExtraColumnsList.add( getColumnDefToColumnDefDTOMapper().mapBack(item, context));
		}
		List<FilterValueDef> FiltersList = target.getFilterValues();
		for (FilterValueDefDTO item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefDTOMapper().mapBack(item, context));
		}
		List<RelationOverrideDef> OverridesList = target.getOverrides();
		for (RelationOverrideDefDTO item : source.getOverrides()) {
			OverridesList.add( getRelationOverrideDefToRelationOverrideDefDTOMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldRelationshipDataDefDTO> getToClass() {
		return FieldRelationshipDataDefDTO.class;
	}

	@Override
	protected Class<FieldRelationshipDataDef> getFromClass() {
		return FieldRelationshipDataDef.class;
	}
}
