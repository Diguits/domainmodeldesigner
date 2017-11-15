package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class EntityDefModel extends DomainObjectDefModel {

	public EntityDefModel() {
		super();
	}

	private ListProperty<String> additionalOperations;
	private ObjectProperty<EntityDefModel> parentEntity;
	private ObjectProperty<EntityDefModel> aggregateEntity;
	private BooleanProperty isMasterDetail;
	private BooleanProperty isAbstract;
	private BooleanProperty isHierarchial;
	private BooleanProperty useService;
	private ListProperty<ColumnDefModel> defaultColumns;
	private BooleanProperty useVisualInterface;
	private ListProperty<FilterDefModel> defaultFilters;

	public ObservableList<String> getAdditionalOperations() {
		return additionalOperationsProperty().get();
	}

	public void setAdditionalOperations(ObservableList<String> additionalOperations) {
		if (this.additionalOperations != null || additionalOperations != null) {
			additionalOperationsProperty().set(additionalOperations);
		}
	}

	public ListProperty<String> additionalOperationsProperty() {
		if (additionalOperations == null) {
			additionalOperations = new SimpleListProperty<String>(this, "additionalOperations", null);
			additionalOperations.set(FXCollections.observableArrayList());
		}
		return additionalOperations;
	}

	public EntityDefModel getParentEntity() {
		if (parentEntity != null)
			return parentEntity.get();
		return null;
	}

	public void setParentEntity(EntityDefModel parentEntity) {
		if (this.parentEntity != null || parentEntity != null) {
			parentEntityProperty().set(parentEntity);
		}
	}

	public ObjectProperty<EntityDefModel> parentEntityProperty() {
		if (parentEntity == null) {
			parentEntity = new SimpleObjectProperty<EntityDefModel>(this, "parentEntity", null);
		}
		return parentEntity;
	}

	public EntityDefModel getAggregateEntity() {
		if (aggregateEntity != null)
			return aggregateEntity.get();
		return null;
	}

	public void setAggregateEntity(EntityDefModel aggregateEntity) {
		if (this.aggregateEntity != null || aggregateEntity != null) {
			aggregateEntityProperty().set(aggregateEntity);
		}
	}

	public ObjectProperty<EntityDefModel> aggregateEntityProperty() {
		if (aggregateEntity == null) {
			aggregateEntity = new SimpleObjectProperty<EntityDefModel>(this, "aggregateEntity", null);
		}
		return aggregateEntity;
	}

	public boolean getIsMasterDetail() {
		if (isMasterDetail != null)
			return isMasterDetail.get();
		return false;
	}

	public void setIsMasterDetail(boolean isMasterDetail) {
		if (this.isMasterDetail != null || isMasterDetail != false) {
			isMasterDetailProperty().set(isMasterDetail);
		}
	}

	public BooleanProperty isMasterDetailProperty() {
		if (isMasterDetail == null) {
			isMasterDetail = new SimpleBooleanProperty(this, "isMasterDetail", false);
		}
		return isMasterDetail;
	}

	public boolean getIsAbstract() {
		if (isAbstract != null)
			return isAbstract.get();
		return false;
	}

	public void setIsAbstract(boolean isAbstract) {
		if (this.isAbstract != null || isAbstract != false) {
			isAbstractProperty().set(isAbstract);
		}
	}

	public BooleanProperty isAbstractProperty() {
		if (isAbstract == null) {
			isAbstract = new SimpleBooleanProperty(this, "isAbstract", false);
		}
		return isAbstract;
	}

	public boolean getIsHierarchial() {
		if (isHierarchial != null)
			return isHierarchial.get();
		return false;
	}

	public void setIsHierarchial(boolean isHierarchial) {
		if (this.isHierarchial != null || isHierarchial != false) {
			isHierarchialProperty().set(isHierarchial);
		}
	}

	public BooleanProperty isHierarchialProperty() {
		if (isHierarchial == null) {
			isHierarchial = new SimpleBooleanProperty(this, "isHierarchial", false);
		}
		return isHierarchial;
	}

	public boolean getUseService() {
		if (useService != null)
			return useService.get();
		return false;
	}

	public void setUseService(boolean useService) {
		if (this.useService != null || useService != false) {
			useServiceProperty().set(useService);
		}
	}

	public BooleanProperty useServiceProperty() {
		if (useService == null) {
			useService = new SimpleBooleanProperty(this, "useService", false);
		}
		return useService;
	}

	public ObservableList<ColumnDefModel> getDefaultColumns() {
		return defaultColumnsProperty().get();
	}

	public void setDefaultColumns(ObservableList<ColumnDefModel> defaultColumns) {
		if (this.defaultColumns != null || defaultColumns != null) {
			defaultColumnsProperty().set(defaultColumns);
		}
	}

	public ListProperty<ColumnDefModel> defaultColumnsProperty() {
		if (defaultColumns == null) {
			defaultColumns = new SimpleListProperty<ColumnDefModel>(this, "defaultColumns", null);
			defaultColumns.set(FXCollections.observableArrayList());
		}
		return defaultColumns;
	}

	public boolean getUseVisualInterface() {
		if (useVisualInterface != null)
			return useVisualInterface.get();
		return false;
	}

	public void setUseVisualInterface(boolean useVisualInterface) {
		if (this.useVisualInterface != null || useVisualInterface != false) {
			useVisualInterfaceProperty().set(useVisualInterface);
		}
	}

	public BooleanProperty useVisualInterfaceProperty() {
		if (useVisualInterface == null) {
			useVisualInterface = new SimpleBooleanProperty(this, "useVisualInterface", false);
		}
		return useVisualInterface;
	}

	public ObservableList<FilterDefModel> getDefaultFilters() {
		return defaultFiltersProperty().get();
	}

	public void setDefaultFilters(ObservableList<FilterDefModel> defaultFilters) {
		if (this.defaultFilters != null || defaultFilters != null) {
			defaultFiltersProperty().set(defaultFilters);
		}
	}

	public ListProperty<FilterDefModel> defaultFiltersProperty() {
		if (defaultFilters == null) {
			defaultFilters = new SimpleListProperty<FilterDefModel>(this, "defaultFilters", null);
			defaultFilters.set(FXCollections.observableArrayList());
		}
		return defaultFilters;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for (FieldGroupDefModel fieldGroupDef : getFieldGroups()) {
			fieldGroupDef.accept(visitor, this);
		}

		for (ColumnDefModel defaultColumn : defaultColumns) {
			defaultColumn.accept(visitor, this);
		}

		for (FilterDefModel defaultFilter : defaultFilters) {
			defaultFilter.accept(visitor, this);
		}

		visitor.visitEntityDefModel(this, owner);

	}

	public DomainModelDefModel getDomainModelOwner() {
		return (DomainModelDefModel) getOwner();
	}
}
