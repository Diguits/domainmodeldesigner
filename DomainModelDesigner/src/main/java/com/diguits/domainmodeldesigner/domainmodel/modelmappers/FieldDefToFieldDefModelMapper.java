package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class FieldDefToFieldDefModelMapper extends BaseDefMapperToBaseDefModelMapper<FieldDef, FieldDefModel> {

	FieldSubgroupDefToFieldSubgroupDefModelMapper fieldSubgroupDefToFieldSubgroupDefModelMapper;
	FieldRelationshipDataDefToFieldRelationshipDataDefModelMapper fieldRelationshipDataDefToFieldRelationshipDataDefModelMapper;

	public FieldDefToFieldDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FieldSubgroupDefToFieldSubgroupDefModelMapper getFieldSubgroupDefToFieldSubgroupDefModelMapper() {
		if(fieldSubgroupDefToFieldSubgroupDefModelMapper==null)
			fieldSubgroupDefToFieldSubgroupDefModelMapper = mapperProvider.getMapper(FieldSubgroupDefToFieldSubgroupDefModelMapper.class);
		return fieldSubgroupDefToFieldSubgroupDefModelMapper;
	}

	public FieldRelationshipDataDefToFieldRelationshipDataDefModelMapper getFieldRelationshipDataDefToFieldRelationshipDataDefModelMapper() {
		if(fieldRelationshipDataDefToFieldRelationshipDataDefModelMapper==null)
			fieldRelationshipDataDefToFieldRelationshipDataDefModelMapper = mapperProvider.getMapper(FieldRelationshipDataDefToFieldRelationshipDataDefModelMapper.class);
		return fieldRelationshipDataDefToFieldRelationshipDataDefModelMapper;
	}

	@Override
	public void map(FieldDef source, FieldDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setFieldSubgroup(getFieldSubgroupDefToFieldSubgroupDefModelMapper().map(source.getFieldSubgroup(), context));
		target.setIncludeMaxValue(source.getIncludeMaxValue());
		target.setAllowNull(source.getAllowNull());
		target.setMaxLength(source.getMaxLength());
		target.setDefaultValue(source.getDefaultValue());
		target.setIsPassword(source.getIsPassword());
		target.setMinValue(source.getMinValue());
		target.setDataType(source.getDataType());
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		target.setDateShowTime(source.getDateShowTime());
		target.setIncludeMinValue(source.getIncludeMinValue());
		target.setReadOnly(source.getReadOnly());
		target.setRegularExpression(source.getRegularExpression());
		target.setMinLength(source.getMinLength());
		target.setScale(source.getScale());
		target.setMaxValue(source.getMaxValue());
		target.setIsCurrency(source.getIsCurrency());
		target.setIsRepresentative(source.getIsRepresentative());
		target.setDataBaseName(source.getDataBaseName());
		target.setPrecision(source.getPrecision());
		target.setMapToDatabase(source.getMapToDatabase());
		target.setMultiline(source.getMultiline());
		getFieldRelationshipDataDefToFieldRelationshipDataDefModelMapper().map(source.getRelationshipData(),  target.getRelationshipData(), context);
		target.setValueObjectTableName(source.getValueObjectTableName());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(FieldDefModel source, FieldDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setFieldSubgroup(getFieldSubgroupDefToFieldSubgroupDefModelMapper().mapBack(source.getFieldSubgroup(), context));
		target.setIncludeMaxValue(source.getIncludeMaxValue());
		target.setAllowNull(source.getAllowNull());
		target.setMaxLength(source.getMaxLength());
		target.setDefaultValue(source.getDefaultValue());
		target.setIsPassword(source.getIsPassword());
		target.setMinValue(source.getMinValue());
		target.setDataType(source.getDataType());
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		target.setDateShowTime(source.getDateShowTime());
		target.setIncludeMinValue(source.getIncludeMinValue());
		target.setReadOnly(source.getReadOnly());
		target.setRegularExpression(source.getRegularExpression());
		target.setMinLength(source.getMinLength());
		target.setScale(source.getScale());
		target.setMaxValue(source.getMaxValue());
		target.setIsCurrency(source.getIsCurrency());
		target.setIsRepresentative(source.getIsRepresentative());
		target.setDataBaseName(source.getDataBaseName());
		target.setPrecision(source.getPrecision());
		target.setMapToDatabase(source.getMapToDatabase());
		target.setMultiline(source.getMultiline());
		target.setRelationshipData(getFieldRelationshipDataDefToFieldRelationshipDataDefModelMapper().mapBack(source.getRelationshipData(), context));
		target.setValueObjectTableName(source.getValueObjectTableName());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldDefModel> getToClass() {
		return FieldDefModel.class;
	}

	@Override
	protected Class<FieldDef> getFromClass() {
		return FieldDef.class;
	}
}
