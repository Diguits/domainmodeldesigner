package com.diguits.domainmodeldefinition.definitions;

import java.util.List;

public class FieldDef extends BaseDef {
	private String dataBaseName;
	private DataType dataType;
	private boolean allowNull;
	private boolean readOnly;
	private int maxLength;
	private int minLength;
	private int precision;
	private int scale;
	private boolean dateShowTime;
	private EnumDef enumDef;
	private Object defaultValue;
	private Object maxValue;
	private Object minValue;
	private boolean includeMaxValue;
	private boolean includeMinValue;
	private boolean isPassword;
	private boolean isCurrency;
	private boolean isRepresentative;
	private String regularExpression;
	private FieldGroupDef fieldGroup;
	private FieldSubgroupDef fieldSubgroup;
	private boolean mapToDatabase;
	private boolean hasVisualRepresentation;
	private FieldRelationshipDataDef relationshipData;
	private boolean multiline;
	private ValueObjectDef valueObject;
	private String valueObjectTableName;

	public FieldDef(EntityDef owner) {
		super(owner);
		initialize();
	}

	public FieldDef() {
		super();
		initialize();
	}

	private void initialize() {
		mapToDatabase = true;
		hasVisualRepresentation = true;
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

	public EnumDef getEnumDef() {
		return enumDef;
	}

	public void setEnumDef(EnumDef enumDef) {
		this.enumDef = enumDef;
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

	public FieldGroupDef getFieldGroup() {
		return fieldGroup;
	}

	public void setFieldGroup(FieldGroupDef fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	public FieldSubgroupDef getFieldSubgroup() {
		return fieldSubgroup;
	}

	public void setFieldSubgroup(FieldSubgroupDef fieldSubgroup) {
		this.fieldSubgroup = fieldSubgroup;
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

	public FieldRelationshipDataDef getRelationshipData() {
		return relationshipData;
	}

	public void setRelationshipData(FieldRelationshipDataDef relationData) {
		this.relationshipData = relationData;
	}

	public RelationshipPartDef getRelationshipPart() {
		return relationshipData != null ? relationshipData.getRelationshipPart() : null;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public boolean getMultiline() {
		return multiline;
	}

	public void setMultiline(boolean multiline) {
		this.multiline = multiline;
	}

	public ValueObjectDef getValueObject() {
		return valueObject;
	}

	public void setValueObject(ValueObjectDef valueObject) {
		this.valueObject = valueObject;
	}

	public String getValueObjectTableName() {
		return valueObjectTableName;
	}

	public void setValueObjectTableName(String valueObjectTableName) {
		this.valueObjectTableName = valueObjectTableName;
	}

	public RelationshipDef getRelationship() {
		return getRelationshipPart() != null ? getRelationshipPart().getOwnerRelationship() : null;
	}

	public boolean relateDomainObjectIsEntity(){
		return getRelationshipPart() != null && getRelationshipPart().getToRelationshipPart() != null
				&& getRelationshipPart().getToRelationshipPart().getDomainObject() instanceof EntityDef;
	}

	public EntityDef getRelatedEntity() {
		return relateDomainObjectIsEntity()
						? (EntityDef) getRelationshipPart().getToRelationshipPart().getDomainObject() : null;
	}

	public FieldDef getRelatedField() {
		return getRelationshipPart() != null && getRelationshipPart().getToRelationshipPart() != null
				? getRelationshipPart().getToRelationshipPart().getField() : null;
	}

	public RelationshipType getRelatedRelationType() {
		return (getRelationshipPart() != null) ? getRelationshipPart().getToRelationshipPart().getRelationType()
				: RelationshipType.One;
	}

	public boolean getIsPrincipalSide() {
		return getRelationshipPart() != null && getRelationshipPart().getIsPrincipalSide();
	}

	public List<FilterValueDef> getFilterValues() {
		return getRelationshipData() != null ? getRelationshipData().getFilterValues() : null;
	}

	public List<ColumnDef> getExtraColumns() {
		return getRelationshipPart() != null ? getRelationshipData().getExtraColumns() : null;
	}

	public List<RelationOverrideDef> getOverrides() {
		return getRelationshipPart() != null ? getRelationshipData().getOverrides() : null;
	}

	public DomainObjectDef getOwnerDomainObject() {
		return (DomainObjectDef) getOwner();
	}

	public void setOwnerDomainObject(DomainObjectDef domainObject) {
		setOwner(domainObject);
	}

	@Override
	protected String getUniqueName() {
		String typeName = dataType != null ? dataType.toString() : "";
		return typeName.substring(0, typeName.length() - 3) + getOwnerDomainObject().getName() + "_" + name;
	}

	public boolean getIsNumeric() {
		return dataType == DataType.Byte || dataType == DataType.Decimal || dataType == DataType.Double
				|| dataType == DataType.Int || dataType == DataType.Long || dataType == DataType.Short
				|| dataType == DataType.Single;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		if (relationshipData != null)
			relationshipData.accept(visitor, this);
		visitor.visitFieldDef(this, owner);
	}

	public EntityDef getRealRelatedEntity() {
		if (getRelatedEntity() == null)
			return null;
		List<RelationOverrideDef> overrides = getRelationshipData().getOverrides();
		int i = 0;
		while (i < overrides.size() && overrides.get(i).getForEntity() != getOwnerDomainObject())
			i++;
		return i < overrides.size() ? getRelatedEntity() : overrides.get(i).getRelationshipEntity();
	}

	public String getDataBaseNameOrName() {
		return (dataBaseName == null || dataBaseName.trim().isEmpty()) ? name : dataBaseName;
	}
}