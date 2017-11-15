package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;
import com.diguits.domainmodeldefinition.definitions.FilterOperator;
import com.diguits.domainmodeldefinition.services.ValueConverter;
import com.diguits.javafx.model.IHierarchialModel;

public class FilterValueDefModel extends PathDefBaseModel implements IHierarchialModel<FilterValueDefModel> {

	public FilterValueDefModel() {
		super();
		setLogicalOperator(FilterLogicalOperator.And);
		ownerProperty().addListener((v, o, n) -> {
			if (o == null) {
				pathChange(null, getPath());
			}
		});
	}

	@Override
	protected void pathChange(String o, String n) {
		super.pathChange(o, n);
		FieldDefModel newField = getField();
		if (newField != null) {
			newField.dataTypeProperty().addListener((value, oldDataType, newDataType) -> {
				setValue(ValueConverter.tryToConver(getValue(), newDataType));
				setValue2(ValueConverter.tryToConver(getValue2(), newDataType));
			});
		}
		if (newField != null) {
			setValue(ValueConverter.tryToConver(getValue(), newField.getDataType()));
			setValue2(ValueConverter.tryToConver(getValue2(), newField.getDataType()));
		}
	}

	private ListProperty<FilterValueDefModel> filterValues;
	private ObjectProperty<FilterLogicalOperator> logicalOperator;
	private ObjectProperty<FilterOperator> operator;
	private ObjectProperty<Object> value;
	private ObjectProperty<Object> value2;

	public FilterLogicalOperator getLogicalOperator() {
		if (logicalOperator != null)
			return logicalOperator.get();
		return null;
	}

	public void setLogicalOperator(FilterLogicalOperator logicalOperator) {
		if (this.logicalOperator != null || logicalOperator != null) {
			logicalOperatorProperty().set(logicalOperator);
		}
	}

	public ObjectProperty<FilterLogicalOperator> logicalOperatorProperty() {
		if (logicalOperator == null) {
			logicalOperator = new SimpleObjectProperty<FilterLogicalOperator>(this, "logicalOperator", null);
		}
		return logicalOperator;
	}

	public FilterOperator getOperator() {
		if (operator != null)
			return operator.get();
		return null;
	}

	public void setOperator(FilterOperator operator) {
		if (this.operator != null || operator != null) {
			operatorProperty().set(operator);
		}
	}

	public ObjectProperty<FilterOperator> operatorProperty() {
		if (operator == null) {
			operator = new SimpleObjectProperty<FilterOperator>(this, "operator", null);
		}
		return operator;
	}

	public Object getValue() {
		if (value != null)
			return value.get();
		return null;
	}

	public void setValue(Object value) {
		if (this.value != null || value != null) {
			valueProperty().set(value);
		}
	}

	public ObjectProperty<Object> valueProperty() {
		if (value == null) {
			value = new SimpleObjectProperty<Object>(this, "value", null);
		}
		return value;
	}

	public Object getValue2() {
		if (value2 != null)
			return value2.get();
		return null;
	}

	public void setValue2(Object value2) {
		if (this.value2 != null || value2 != null) {
			value2Property().set(value2);
		}
	}

	public ObjectProperty<Object> value2Property() {
		if (value2 == null) {
			value2 = new SimpleObjectProperty<Object>(this, "value2", null);
		}
		return value2;
	}

	public ObservableList<FilterValueDefModel> getFilterValues() {
		return filterValuesProperty().get();
	}

	public void setFilterValues(ObservableList<FilterValueDefModel> filterValues) {
		if (this.filterValues != null || filterValues != null) {
			filterValuesProperty().set(filterValues);
		}
	}

	public ListProperty<FilterValueDefModel> filterValuesProperty() {
		if (filterValues == null) {
			filterValues = new SimpleListProperty<FilterValueDefModel>(this, "filterValues", null);
			filterValues.set(FXCollections.observableArrayList());
		}
		return filterValues;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for (FilterValueDefModel filterDef : getFilterValues()) {
			filterDef.accept(visitor, this);
		}
		visitor.visitFilterValueDefModel(this, owner);
	}

	@Override
	public ObservableList<FilterValueDefModel> getChildren() {
		return getFilterValues();
	}

	@Override
	public boolean isLeaf() {
		return getFilterValues().size() == 0;
	}

	@Override
	public EntityDefModel getEntity() {
		if (getOwner() != null) {
			if (getOwner() instanceof EntityDefModel)
				return (EntityDefModel) getOwner();
			else if (getOwner() instanceof FieldRelationshipDataDefModel) {
				FieldRelationshipDataDefModel relationData = (FieldRelationshipDataDefModel) getOwner();
				if (relationData != null && relationData.getRelationshipPart() != null && relationData
						.getRelationshipPart().getToRelationshipPart().getDomainObject() instanceof EntityDefModel)
					return (EntityDefModel) relationData.getRelationshipPart().getToRelationshipPart()
							.getDomainObject();
			} else if (getOwner() != null && getOwner() instanceof FilterValueDefModel)
				return ((FilterValueDefModel) getOwner()).getEntity();
		}
		return null;
	}

}
