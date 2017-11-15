package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.UUID;

import org.simpleframework.xml.Default;
import com.diguits.domainmodeldefinition.definitions.DataType;

@Default(required = false)
public class FieldDefDTO extends BaseDefDTO {
	private String dataBaseName;
	private DataType dataType;
	private boolean allowNull;
	private boolean readOnly;
	private int maxLength;
	private int minLength;
	private int precision;
	private int scale;
	private boolean dateShowTime;
	private UUID enumId;
	private String enumName;
	private Object defaultValue;
	private Object maxValue;
	private Object minValue;
	private boolean includeMaxValue;
	private boolean includeMinValue;
	private boolean isPassword;
	private boolean isCurrency;
	private boolean isRepresentative;
	private String regularExpression;
	private UUID fieldGroupId;
	private String fieldGroupName;
	private UUID fieldSubgroupId;
	private String fieldSubgroupName;
	private boolean mapToDatabase;
	private boolean hasVisualRepresentation;
	private UUID relationshipPartId;
	private FieldRelationshipDataDefDTO relationshipData;
	private boolean multiline;
	private UUID valueObjectId;
	private String valueObjectTableName;

	public FieldDefDTO() {
		mapToDatabase = true;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public boolean getAllowNull() {
		return allowNull;
	}

	public void setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
	}

	public boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public boolean getDateShowTime() {
		return dateShowTime;
	}

	public void setDateShowTime(boolean dateShowTime) {
		this.dateShowTime = dateShowTime;
	}

	public UUID getEnumDefId() {
		return enumId;
	}

	public void setEnumDefId(UUID enumId) {
		this.enumId = enumId;
	}

	public String getEnumDefName() {
		return enumName;
	}

	public void setEnumDefName(String enumName) {
		this.enumName = enumName;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Object getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Object maxValue) {
		this.maxValue = maxValue;
	}

	public Object getMinValue() {
		return minValue;
	}

	public void setMinValue(Object minValue) {
		this.minValue = minValue;
	}

	public boolean getIncludeMaxValue() {
		return includeMaxValue;
	}

	public void setIncludeMaxValue(boolean includeMaxValue) {
		this.includeMaxValue = includeMaxValue;
	}

	public boolean getIncludeMinValue() {
		return includeMinValue;
	}

	public void setIncludeMinValue(boolean includeMinValue) {
		this.includeMinValue = includeMinValue;
	}

	public boolean getIsPassword() {
		return isPassword;
	}

	public void setIsPassword(boolean isPassword) {
		this.isPassword = isPassword;
	}

	public boolean getIsCurrency() {
		return isCurrency;
	}

	public void setIsCurrency(boolean isCurrency) {
		this.isCurrency = isCurrency;
	}

	public boolean getIsRepresentative() {
		return isRepresentative;
	}

	public void setIsRepresentative(boolean isRepresentative) {
		this.isRepresentative = isRepresentative;
	}

	public String getRegularExpression() {
		return regularExpression;
	}

	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}

	public UUID getFieldGroupId() {
		return fieldGroupId;
	}

	public void setFieldGroupId(UUID fieldGroupId) {
		this.fieldGroupId = fieldGroupId;
	}

	public String getFieldGroupName() {
		return fieldGroupName;
	}

	public void setFieldGroupName(String fieldGroupName) {
		this.fieldGroupName = fieldGroupName;
	}

	public UUID getFieldSubgroupId() {
		return fieldSubgroupId;
	}

	public void setFieldSubgroupId(UUID fieldSubgroupId) {
		this.fieldSubgroupId = fieldSubgroupId;
	}

	public String getFieldSubgroupName() {
		return fieldSubgroupName;
	}

	public void setFieldSubgroupName(String fieldSubgroupName) {
		this.fieldSubgroupName = fieldSubgroupName;
	}

	public boolean getMapToDatabase() {
		return mapToDatabase;
	}

	public void setMapToDatabase(boolean mapToDatabase) {
		this.mapToDatabase = mapToDatabase;
	}

	public boolean getHasVisualRepresentation() {
		return hasVisualRepresentation;
	}

	public void setHasVisualRepresentation(boolean hasVisualRepresentation) {
		this.hasVisualRepresentation = hasVisualRepresentation;
	}

	public UUID getRelationshipPartId() {
		return relationshipPartId;
	}

	public void setRelationshipPartId(UUID relationshipPartId) {
		this.relationshipPartId = relationshipPartId;
	}

	public FieldRelationshipDataDefDTO getRelationshipData() {
		return relationshipData;
	}

	public void setRelationshipData(FieldRelationshipDataDefDTO relationshipData) {
		this.relationshipData = relationshipData;
	}

	public boolean getMultiline() {
		return multiline;
	}

	public void setMultiline(boolean multiline) {
		this.multiline = multiline;
	}

	public UUID getValueObjectId() {
		return valueObjectId;
	}

	public void setValueObjectId(UUID valueObjectId) {
		this.valueObjectId = valueObjectId;
	}

	public String getValueObjectTableName() {
		return valueObjectTableName;
	}

	public void setValueObjectTableName(String valueObjectTableName) {
		this.valueObjectTableName = valueObjectTableName;
	}
}