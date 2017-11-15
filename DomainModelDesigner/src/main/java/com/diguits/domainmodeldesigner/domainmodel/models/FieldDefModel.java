package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldefinition.definitions.RelationshipType;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class FieldDefModel extends BaseDefModel {

	private ObjectProperty<FieldSubgroupDefModel> fieldSubgroup;
	private BooleanProperty includeMinValue;
	private BooleanProperty mapToDatabase;
	private BooleanProperty dateShowTime;
	private BooleanProperty isPassword;
	private StringProperty regularExpression;
	private ObjectProperty<Object> defaultValue;
	private ObjectProperty<Object> maxValue;
	private ObjectProperty<DataType> dataType;
	private IntegerProperty minLength;
	private IntegerProperty precision;
	private IntegerProperty scale;
	private ObjectProperty<EnumDefModel> enumDef;
	private BooleanProperty readOnly;
	private ObjectProperty<FieldGroupDefModel> fieldGroup;
	private BooleanProperty includeMaxValue;
	private ObjectProperty<Object> minValue;
	private BooleanProperty hasVisualRepresentation;
	private BooleanProperty isCurrency;
	private BooleanProperty isRepresentative;
	private BooleanProperty allowNull;
	private StringProperty dataBaseName;
	private IntegerProperty maxLength;
	private ObjectProperty<FieldRelationshipDataDefModel> relationshipData;
	private BooleanProperty multiline;
	private ObjectProperty<ValueObjectDefModel> valueObject;
	private StringProperty valueObjectTableName;

	public FieldDefModel() {
		super();
		relationshipData = new SimpleObjectProperty<FieldRelationshipDataDefModel>(this, "relationshipData", null);
		relationshipData.set(new FieldRelationshipDataDefModel(this));

		ownerProperty().addListener((v, o, n) -> {
			if (n != null && this.getName() != null)
				getRelationshipData().setName(n.getName() + "_" + this.getName() + "_FK");
		});

		dataTypeProperty().addListener((v, o, n) -> {
			updateRelationshipPart(o, n);
		});
	}

	public void updateRelationshipPart(DataType oldDataType, DataType newDataType) {
		if (getOwner() != null) {
			if (!(oldDataType == DataType.Entity || oldDataType == DataType.EntityList)
					&& (newDataType == DataType.Entity || newDataType == DataType.EntityList)) {
				RelationshipDefModel relation = new RelationshipDefModel();
				relation.getFromPart().setField(this);
				relation.getFromPart().setDomainObject((DomainObjectDefModel) this.getOwner());
				getRelationshipData().setRelationshipPart(relation.getFromPart());
				getDomainObjectOwner().getDomainModelOwner().getRelations().add(relation);
			}
			if (!(newDataType == DataType.Entity || newDataType == DataType.EntityList)
					&& (oldDataType == DataType.Entity || oldDataType == DataType.EntityList)) {

				RelationshipPartDefModel relationPart = getRelationshipPart();
				if (relationPart != null) {
					relationPart.setField(null);
					if (relationPart.getToRelationshipPart().getField() == null)
						getDomainObjectOwner().getDomainModelOwner().getRelations().remove(relationPart.getOwner());
				}
				getRelationshipData().setRelationshipPart(null);
			}
			updateRealtionType(newDataType, getRelationshipData().getRelationshipPart());
		}
	}

	private void updateRealtionType(DataType dataType, RelationshipPartDefModel relationPart) {
		if (relationPart != null)
			if (dataType == DataType.EntityList)
				relationPart.setRelationType(RelationshipType.Many);
			else
				relationPart.setRelationType(RelationshipType.One);
	}

	public FieldSubgroupDefModel getFieldSubgroup() {
		if (fieldSubgroup != null)
			return fieldSubgroup.get();
		return null;
	}

	public void setFieldSubgroup(FieldSubgroupDefModel fieldSubgroup) {
		if (this.fieldSubgroup != null || fieldSubgroup != null) {
			fieldSubgroupProperty().set(fieldSubgroup);
		}
	}

	public ObjectProperty<FieldSubgroupDefModel> fieldSubgroupProperty() {
		if (fieldSubgroup == null) {
			fieldSubgroup = new SimpleObjectProperty<FieldSubgroupDefModel>(this, "fieldSubgroup", null);
		}
		return fieldSubgroup;
	}

	public boolean getIncludeMinValue() {
		if (includeMinValue != null)
			return includeMinValue.get();
		return false;
	}

	public void setIncludeMinValue(boolean includeMinValue) {
		if (this.includeMinValue != null || includeMinValue != false) {
			includeMinValueProperty().set(includeMinValue);
		}
	}

	public BooleanProperty includeMinValueProperty() {
		if (includeMinValue == null) {
			includeMinValue = new SimpleBooleanProperty(this, "includeMinValue", false);
		}
		return includeMinValue;
	}

	public boolean getMapToDatabase() {
		if (mapToDatabase != null)
			return mapToDatabase.get();
		return false;
	}

	public void setMapToDatabase(boolean mapToDatabase) {
		if (this.mapToDatabase != null || mapToDatabase != false) {
			mapToDatabaseProperty().set(mapToDatabase);
		}
	}

	public BooleanProperty mapToDatabaseProperty() {
		if (mapToDatabase == null) {
			mapToDatabase = new SimpleBooleanProperty(this, "mapToDatabase", false);
		}
		return mapToDatabase;
	}

	public boolean getDateShowTime() {
		if (dateShowTime != null)
			return dateShowTime.get();
		return false;
	}

	public void setDateShowTime(boolean dateShowTime) {
		if (this.dateShowTime != null || dateShowTime != false) {
			dateShowTimeProperty().set(dateShowTime);
		}
	}

	public BooleanProperty dateShowTimeProperty() {
		if (dateShowTime == null) {
			dateShowTime = new SimpleBooleanProperty(this, "dateShowTime", false);
		}
		return dateShowTime;
	}

	public boolean getIsPassword() {
		if (isPassword != null)
			return isPassword.get();
		return false;
	}

	public void setIsPassword(boolean isPassword) {
		if (this.isPassword != null || isPassword != false) {
			isPasswordProperty().set(isPassword);
		}
	}

	public BooleanProperty isPasswordProperty() {
		if (isPassword == null) {
			isPassword = new SimpleBooleanProperty(this, "isPassword", false);
		}
		return isPassword;
	}

	public String getRegularExpression() {
		if (regularExpression != null)
			return regularExpression.get();
		return "";
	}

	public void setRegularExpression(String regularExpression) {
		if (this.regularExpression != null || regularExpression == null || !regularExpression.equals("")) {
			regularExpressionProperty().set(regularExpression);
		}
	}

	public StringProperty regularExpressionProperty() {
		if (regularExpression == null) {
			regularExpression = new SimpleStringProperty(this, "regularExpression", "");
		}
		return regularExpression;
	}

	public Object getDefaultValue() {
		if (defaultValue != null)
			return defaultValue.get();
		return null;
	}

	public void setDefaultValue(Object defaultValue) {
		if (this.defaultValue != null || defaultValue != null) {
			defaultValueProperty().set(defaultValue);
		}
	}

	public ObjectProperty<Object> defaultValueProperty() {
		if (defaultValue == null) {
			defaultValue = new SimpleObjectProperty<Object>(this, "defaultValue", null);
		}
		return defaultValue;
	}

	public Object getMaxValue() {
		if (maxValue != null)
			return maxValue.get();
		return null;
	}

	public void setMaxValue(Object maxValue) {
		if (this.maxValue != null || maxValue != null) {
			maxValueProperty().set(maxValue);
		}
	}

	public ObjectProperty<Object> maxValueProperty() {
		if (maxValue == null) {
			maxValue = new SimpleObjectProperty<Object>(this, "maxValue", null);
		}
		return maxValue;
	}

	public DataType getDataType() {
		if (dataType != null)
			return dataType.get();
		return null;
	}

	public void setDataType(DataType dataType) {
		if (this.dataType != null || dataType != null) {
			dataTypeProperty().set(dataType);
		}
	}

	public ObjectProperty<DataType> dataTypeProperty() {
		if (dataType == null) {
			dataType = new SimpleObjectProperty<DataType>(this, "dataType", null);
		}
		return dataType;
	}

	public int getMinLength() {
		if (minLength != null)
			return minLength.get();
		return 0;
	}

	public void setMinLength(int minLength) {
		if (this.minLength != null || minLength != 0) {
			minLengthProperty().set(minLength);
		}
	}

	public IntegerProperty minLengthProperty() {
		if (minLength == null) {
			minLength = new SimpleIntegerProperty(this, "minLength", 0);
		}
		return minLength;
	}

	public int getPrecision() {
		if (precision != null)
			return precision.get();
		return 0;
	}

	public void setPrecision(int precision) {
		if (this.precision != null || precision != 0) {
			precisionProperty().set(precision);
		}
	}

	public IntegerProperty precisionProperty() {
		if (precision == null) {
			precision = new SimpleIntegerProperty(this, "precision", 0);
		}
		return precision;
	}

	public int getScale() {
		if (scale != null)
			return scale.get();
		return 0;
	}

	public void setScale(int scale) {
		if (this.scale != null || scale != 0) {
			scaleProperty().set(scale);
		}
	}

	public IntegerProperty scaleProperty() {
		if (scale == null) {
			scale = new SimpleIntegerProperty(this, "scale", 0);
		}
		return scale;
	}

	public EnumDefModel getEnumDef() {
		if (enumDef != null)
			return enumDef.get();
		return null;
	}

	public void setEnumDef(EnumDefModel enumDef) {
		if (this.enumDef != null || enumDef != null) {
			enumDefProperty().set(enumDef);
		}
	}

	public ObjectProperty<EnumDefModel> enumDefProperty() {
		if (enumDef == null) {
			enumDef = new SimpleObjectProperty<EnumDefModel>(this, "enumDef", null);
		}
		return enumDef;
	}

	public boolean getReadOnly() {
		if (readOnly != null)
			return readOnly.get();
		return false;
	}

	public void setReadOnly(boolean readOnly) {
		if (this.readOnly != null || readOnly != false) {
			readOnlyProperty().set(readOnly);
		}
	}

	public BooleanProperty readOnlyProperty() {
		if (readOnly == null) {
			readOnly = new SimpleBooleanProperty(this, "readOnly", false);
		}
		return readOnly;
	}

	public FieldGroupDefModel getFieldGroup() {
		if (fieldGroup != null)
			return fieldGroup.get();
		return null;
	}

	public void setFieldGroup(FieldGroupDefModel fieldGroup) {
		if (this.fieldGroup != null || fieldGroup != null) {
			fieldGroupProperty().set(fieldGroup);
		}
	}

	public ObjectProperty<FieldGroupDefModel> fieldGroupProperty() {
		if (fieldGroup == null) {
			fieldGroup = new SimpleObjectProperty<FieldGroupDefModel>(this, "fieldGroup", null);
		}
		return fieldGroup;
	}

	public boolean getIncludeMaxValue() {
		if (includeMaxValue != null)
			return includeMaxValue.get();
		return false;
	}

	public void setIncludeMaxValue(boolean includeMaxValue) {
		if (this.includeMaxValue != null || includeMaxValue != false) {
			includeMaxValueProperty().set(includeMaxValue);
		}
	}

	public BooleanProperty includeMaxValueProperty() {
		if (includeMaxValue == null) {
			includeMaxValue = new SimpleBooleanProperty(this, "includeMaxValue", false);
		}
		return includeMaxValue;
	}

	public Object getMinValue() {
		if (minValue != null)
			return minValue.get();
		return null;
	}

	public void setMinValue(Object minValue) {
		if (this.minValue != null || minValue != null) {
			minValueProperty().set(minValue);
		}
	}

	public ObjectProperty<Object> minValueProperty() {
		if (minValue == null) {
			minValue = new SimpleObjectProperty<Object>(this, "minValue", null);
		}
		return minValue;
	}

	public boolean getHasVisualRepresentation() {
		if (hasVisualRepresentation != null)
			return hasVisualRepresentation.get();
		return false;
	}

	public void setHasVisualRepresentation(boolean hasVisualRepresentation) {
		if (this.hasVisualRepresentation != null || hasVisualRepresentation != false) {
			hasVisualRepresentationProperty().set(hasVisualRepresentation);
		}
	}

	public BooleanProperty hasVisualRepresentationProperty() {
		if (hasVisualRepresentation == null) {
			hasVisualRepresentation = new SimpleBooleanProperty(this, "hasVisualRepresentation", false);
		}
		return hasVisualRepresentation;
	}

	public boolean getIsCurrency() {
		if (isCurrency != null)
			return isCurrency.get();
		return false;
	}

	public void setIsCurrency(boolean isCurrency) {
		if (this.isCurrency != null || isCurrency != false) {
			isCurrencyProperty().set(isCurrency);
		}
	}

	public BooleanProperty isCurrencyProperty() {
		if (isCurrency == null) {
			isCurrency = new SimpleBooleanProperty(this, "isCurrency", false);
		}
		return isCurrency;
	}

	public boolean getIsRepresentative() {
		if (isRepresentative != null)
			return isRepresentative.get();
		return false;
	}

	public void setIsRepresentative(boolean isRepresentative) {
		if (this.isRepresentative != null || isRepresentative != false) {
			isRepresentativeProperty().set(isRepresentative);
		}
	}

	public BooleanProperty isRepresentativeProperty() {
		if (isRepresentative == null) {
			isRepresentative = new SimpleBooleanProperty(this, "isRepresentative", false);
		}
		return isRepresentative;
	}

	public boolean getAllowNull() {
		if (allowNull != null)
			return allowNull.get();
		return false;
	}

	public void setAllowNull(boolean allowNull) {
		if (this.allowNull != null || allowNull != false) {
			allowNullProperty().set(allowNull);
		}
	}

	public BooleanProperty allowNullProperty() {
		if (allowNull == null) {
			allowNull = new SimpleBooleanProperty(this, "allowNull", false);
		}
		return allowNull;
	}

	public String getDataBaseName() {
		if (dataBaseName != null)
			return dataBaseName.get();
		return "";
	}

	public void setDataBaseName(String dataBaseName) {
		if (this.dataBaseName != null || dataBaseName == null || !dataBaseName.equals("")) {
			dataBaseNameProperty().set(dataBaseName);
		}
	}

	public StringProperty dataBaseNameProperty() {
		if (dataBaseName == null) {
			dataBaseName = new SimpleStringProperty(this, "dataBaseName", "");
		}
		return dataBaseName;
	}

	public int getMaxLength() {
		if (maxLength != null)
			return maxLength.get();
		return 0;
	}

	public void setMaxLength(int maxLength) {
		if (this.maxLength != null || maxLength != 0) {
			maxLengthProperty().set(maxLength);
		}
	}

	public IntegerProperty maxLengthProperty() {
		if (maxLength == null) {
			maxLength = new SimpleIntegerProperty(this, "maxLength", 0);
		}
		return maxLength;
	}

	public FieldRelationshipDataDefModel getRelationshipData() {
		return relationshipData.get();
	}

	public ReadOnlyObjectProperty<FieldRelationshipDataDefModel> relationshipDataProperty() {
		return relationshipData;
	}

	public boolean getMultiline() {
		if (multiline != null)
			return multiline.get();
		return false;
	}

	public void setMultiline(boolean multiline) {
		if (this.multiline != null || multiline != false) {
			multilineProperty().set(multiline);
		}
	}

	public BooleanProperty multilineProperty() {
		if (multiline == null) {
			multiline = new SimpleBooleanProperty(this, "multiline", false);
		}
		return multiline;
	}

	public ValueObjectDefModel getValueObject() {
		if (valueObject != null)
			return valueObject.get();
		return null;
	}

	public void setValueObject(ValueObjectDefModel valueObject) {
		if (this.valueObject != null || valueObject != null) {
			valueObjectProperty().set(valueObject);
		}
	}

	public ObjectProperty<ValueObjectDefModel> valueObjectProperty() {
		if (valueObject == null) {
			valueObject = new SimpleObjectProperty<ValueObjectDefModel>(this, "valueObject", null);
		}
		return valueObject;
	}

	public String getValueObjectTableName() {
		if (valueObjectTableName != null)
			return valueObjectTableName.get();
		return "";
	}

	public void setValueObjectTableName(String valueObjectTableName) {
		if (this.valueObjectTableName != null || valueObjectTableName == null || !valueObjectTableName.equals("")) {
			valueObjectTableNameProperty().set(valueObjectTableName);
		}
	}

	public StringProperty valueObjectTableNameProperty() {
		if (valueObjectTableName == null) {
			valueObjectTableName = new SimpleStringProperty(this, "valueObjectTableName", "");
		}
		return valueObjectTableName;
	}

	public RelationshipPartDefModel getRelationshipPart() {
		return getRelationshipData() != null
				? getRelationshipData().getRelationshipPart()
				: null;
	}

	public void setRelationshipPart(RelationshipPartDefModel relationshipPart) {
		if(getRelationshipData() != null)
			getRelationshipData().setRelationshipPart(relationshipPart);
	}

	public RelationshipPartDefModel getToRelationshipPart() {
		return getRelationshipPart() != null
				? getRelationshipPart().getToRelationshipPart()
				: null;
	}

	public FieldDefModel getToField() {
		return getToRelationshipPart() != null
				? getToRelationshipPart().getField()
				: null;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		getRelationshipData().accept(visitor, this);
		visitor.visitFieldDefModel(this, owner);
	}

	public DomainObjectDefModel getDomainObjectOwner() {
		return (DomainObjectDefModel) getOwner();
	}
}
