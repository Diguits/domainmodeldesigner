package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.RelationOverrideDef;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldRelationshipDataDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;
import com.diguits.domainmodeldefinition.definitions.FieldRelationshipDataDef;
import com.diguits.domainmodeldefinition.definitions.FilterValueDef;
import com.diguits.domainmodeldefinition.definitions.ColumnDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FieldRelationshipDataDefToFieldRelationshipDataDefModelMapper extends BaseDefMapperToBaseDefModelMapper<FieldRelationshipDataDef, FieldRelationshipDataDefModel> {

	RelationOverrideDefToRelationOverrideDefModelMapper relationOverrideDefToRelationOverrideDefModelMapper;
	ColumnDefToColumnDefModelMapper columnDefToColumnDefModelMapper;
	FilterValueDefToFilterValueDefModelMapper filterValueDefToFilterValueDefModelMapper;

	public FieldRelationshipDataDefToFieldRelationshipDataDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public RelationOverrideDefToRelationOverrideDefModelMapper getRelationOverrideDefToRelationOverrideDefModelMapper() {
		if(relationOverrideDefToRelationOverrideDefModelMapper==null)
			relationOverrideDefToRelationOverrideDefModelMapper = mapperProvider.getMapper(RelationOverrideDefToRelationOverrideDefModelMapper.class);
		return relationOverrideDefToRelationOverrideDefModelMapper;
	}

	public ColumnDefToColumnDefModelMapper getColumnDefToColumnDefModelMapper() {
		if(columnDefToColumnDefModelMapper==null)
			columnDefToColumnDefModelMapper = mapperProvider.getMapper(ColumnDefToColumnDefModelMapper.class);
		return columnDefToColumnDefModelMapper;
	}

	public FilterValueDefToFilterValueDefModelMapper getFilterValueDefToFilterValueDefModelMapper() {
		if(filterValueDefToFilterValueDefModelMapper==null)
			filterValueDefToFilterValueDefModelMapper = mapperProvider.getMapper(FilterValueDefToFilterValueDefModelMapper.class);
		return filterValueDefToFilterValueDefModelMapper;
	}

	@Override
	public void map(FieldRelationshipDataDef source, FieldRelationshipDataDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		List<ColumnDefModel> ExtraColumnsList = target.getExtraColumns();
		for (ColumnDef item : source.getExtraColumns()) {
			ExtraColumnsList.add( getColumnDefToColumnDefModelMapper().map(item, context));
		}
		List<FilterValueDefModel> FiltersList = target.getFilterValues();
		for (FilterValueDef item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefModelMapper().map(item, context));
		}
		List<RelationOverrideDefModel> OverridesList = target.getOverrides();
		for (RelationOverrideDef item : source.getOverrides()) {
			OverridesList.add( getRelationOverrideDefToRelationOverrideDefModelMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	@Override
	public void mapBack(FieldRelationshipDataDefModel source, FieldRelationshipDataDef target, MappingContext context) {
		if(source == null || target == null) return;
		List<ColumnDef> ExtraColumnsList = target.getExtraColumns();
		for (ColumnDefModel item : source.getExtraColumns()) {
			ExtraColumnsList.add( getColumnDefToColumnDefModelMapper().mapBack(item, context));
		}
		List<FilterValueDef> FiltersList = target.getFilterValues();
		for (FilterValueDefModel item : source.getFilterValues()) {
			FiltersList.add( getFilterValueDefToFilterValueDefModelMapper().mapBack(item, context));
		}
		List<RelationOverrideDef> OverridesList = target.getOverrides();
		for (RelationOverrideDefModel item : source.getOverrides()) {
			OverridesList.add( getRelationOverrideDefToRelationOverrideDefModelMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldRelationshipDataDefModel> getToClass() {
		return FieldRelationshipDataDefModel.class;
	}

	@Override
	protected Class<FieldRelationshipDataDef> getFromClass() {
		return FieldRelationshipDataDef.class;
	}
}
