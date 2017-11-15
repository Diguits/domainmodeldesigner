package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.diguits.domainmodeldefinition.definitions.FilterType;
import com.diguits.javafx.model.IHierarchialModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;

public class FilterDefModel extends PathDefBaseModel implements IHierarchialModel<FilterDefModel> {

	public FilterDefModel() {
		super();
		setLogicalOperator(FilterLogicalOperator.And);
	}

	private ObjectProperty<FilterLogicalOperator> logicalOperator;
	private ListProperty<FilterDefModel> filters;
	private ObjectProperty<FilterType> filterType;
	private BooleanProperty useOperator;

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

	public ObservableList<FilterDefModel> getFilters() {
		return filtersProperty().get();
	}

	public void setFilters(ObservableList<FilterDefModel> filters) {
		if (this.filters != null || filters != null) {
			filtersProperty().set(filters);
		}
	}

	public ListProperty<FilterDefModel> filtersProperty() {
		if (filters == null) {
			filters = new SimpleListProperty<FilterDefModel>(this, "filters", null);
			filters.set(FXCollections.observableArrayList());
		}
		return filters;
	}

	public FilterType getFilterType() {
		if (filterType != null)
			return filterType.get();
		return null;
	}

	public void setFilterType(FilterType filterType) {
		if (this.filterType != null || filterType != null) {
			filterTypeProperty().set(filterType);
		}
	}

	public ObjectProperty<FilterType> filterTypeProperty() {
		if (filterType == null) {
			filterType = new SimpleObjectProperty<FilterType>(this, "filterType", null);
		}
		return filterType;
	}

	public Boolean getUseOperator() {
		if (useOperator != null)
			return useOperator.get();
		return false;
	}

	public void setUseOperator(Boolean useOperator) {
		if (this.useOperator != null || useOperator != false) {
			useOperatorProperty().set(useOperator);
		}
	}

	public BooleanProperty useOperatorProperty() {
		if (useOperator == null) {
			useOperator = new SimpleBooleanProperty(this, "useOperator", false);
		}
		return useOperator;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for (FilterDefModel filterDef : getFilters()) {
			filterDef.accept(visitor, this);
		}
		visitor.visitFilterDefModel(this, owner);
	}

	@Override
	public EntityDefModel getEntity() {
		if (getOwner() != null) {
			if (getOwner() instanceof EntityDefModel)
				return (EntityDefModel) getOwner();
			else if (getOwner() instanceof FilterDefModel)
				return ((FilterDefModel) getOwner()).getEntity();

		}
		return null;
	}

	@Override
	public ObservableList<FilterDefModel> getChildren() {
		return getFilters();
	}

	@Override
	public boolean isLeaf() {
		return getFilters().size() == 0;
	}
}
