package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class FieldDefToFieldDefDTOMapper extends BaseDefToBaseDefDTOMapper<FieldDef, FieldDefDTO> {

	FieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper fieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper;

	public FieldDefToFieldDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper getFieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper() {
		if(fieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper==null)
			fieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper = mapperProvider.getMapper(FieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper.class);
		return fieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper;
	}

	public void  map(FieldDef source, FieldDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setIncludeMaxValue(source.getIncludeMaxValue());
		if(source.getFieldSubgroup()!=null)
			target.setFieldSubgroupId(source.getFieldSubgroup().getId());
		if(source.getRelationshipPart()!=null)
			target.setRelationshipPartId(source.getRelationshipPart().getId());
		if(source.getEnumDef()!=null)
			target.setEnumDefId(source.getEnumDef().getId());
		target.setAllowNull(source.getAllowNull());
		target.setMaxLength(source.getMaxLength());
		if(source.getFieldSubgroup()!=null)
			target.setFieldSubgroupName(source.getFieldSubgroup().getName());
		target.setDefaultValue(source.getDefaultValue());
		if(source.getEnumDef()!=null)
			target.setEnumDefName(source.getEnumDef().getName());
		target.setIsPassword(source.getIsPassword());
		target.setMinValue(source.getMinValue());
		target.setDataType(source.getDataType());
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		target.setDateShowTime(source.getDateShowTime());
		target.setIncludeMinValue(source.getIncludeMinValue());
		if(source.getFieldGroup()!=null)
			target.setFieldGroupName(source.getFieldGroup().getName());
		target.setReadOnly(source.getReadOnly());
		target.setRegularExpression(source.getRegularExpression());
		if(source.getFieldGroup()!=null)
			target.setFieldGroupId(source.getFieldGroup().getId());
		target.setMinLength(source.getMinLength());
		target.setScale(source.getScale());
		target.setMaxValue(source.getMaxValue());
		target.setDataBaseName(source.getDataBaseName());
		target.setIsCurrency(source.getIsCurrency());
		target.setIsRepresentative(source.getIsRepresentative());
		target.setPrecision(source.getPrecision());
		if(source.getRelationshipData()!=null)
			target.setRelationshipData(getFieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper().map(source.getRelationshipData(), context));
		target.setMapToDatabase(source.getMapToDatabase());
		target.setMultiline(source.getMultiline());
		if(source.getValueObject()!=null)
			target.setValueObjectId(source.getValueObject().getId());
		target.setValueObjectTableName(source.getValueObjectTableName());
		super.map(source, target, context);
	}

	public void mapBack(FieldDefDTO source, FieldDef target, MappingContext context) {
		if(source == null || target == null) return;
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
		if(source.getRelationshipData()!=null)
			target.setRelationshipData(getFieldRelationshipDataDefToFieldRelationshipDataDefDTOMapper().mapBack(source.getRelationshipData(), context));
		target.setMapToDatabase(source.getMapToDatabase());
		target.setMultiline(source.getMultiline());
		target.setValueObjectTableName(source.getValueObjectTableName());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldDefDTO> getToClass() {
		return FieldDefDTO.class;
	}

	@Override
	protected Class<FieldDef> getFromClass() {
		return FieldDef.class;
	}
}
