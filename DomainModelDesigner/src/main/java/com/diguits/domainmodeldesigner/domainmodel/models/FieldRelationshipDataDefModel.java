package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class FieldRelationshipDataDefModel extends BaseDefModel {

	public FieldRelationshipDataDefModel() {
		super();
	}

	public FieldRelationshipDataDefModel(FieldDefModel field) {
		super();
		this.setOwner(field);
		relatedField = new SimpleObjectProperty<FieldDefModel>(this, "relatedField", null);
		relatedDomainObject = new SimpleObjectProperty<DomainObjectDefModel>(this, "relatedDomainObject", null);
		relationshipPartProperty().addListener((v, o, n)->{
			if(n!=null){
				relatedField.bind(n.getToRelationshipPart().fieldProperty());
				relatedDomainObject.bind(n.getToRelationshipPart().domainObjectProperty());
			}
			else{
				relatedField.unbind();
				relatedDomainObject.unbind();
			}
		});
	}

	private ObjectProperty<RelationshipPartDefModel> relationshipPart;
	private ListProperty<FilterValueDefModel> filterValues;
	private ListProperty<RelationOverrideDefModel> overrides;
	private ListProperty<ColumnDefModel> extraColumns;

	private ObjectProperty<FieldDefModel> relatedField;
	private ObjectProperty<DomainObjectDefModel> relatedDomainObject;


	public RelationshipPartDefModel getRelationshipPart() {
		if (relationshipPart != null)
			return relationshipPart.get();
		return null;
	}

	public void setRelationshipPart(RelationshipPartDefModel relationshipPart) {
		if (this.relationshipPart != null || relationshipPart != null) {
			relationshipPartProperty().set(relationshipPart);
		}
	}

	public ObjectProperty<RelationshipPartDefModel> relationshipPartProperty() {
		if (relationshipPart == null) {
			relationshipPart = new SimpleObjectProperty<RelationshipPartDefModel>(this, "relationshipPart", null);
		}
		return relationshipPart;
	}

	public FieldDefModel getField() {
		return (FieldDefModel) getOwner();
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

	public ObservableList<RelationOverrideDefModel> getOverrides() {
		return overridesProperty().get();
	}

	public void setOverrides(ObservableList<RelationOverrideDefModel> overrides) {
		if (this.overrides != null || overrides != null) {
			overridesProperty().set(overrides);
		}
	}

	public ListProperty<RelationOverrideDefModel> overridesProperty() {
		if (overrides == null) {
			overrides = new SimpleListProperty<RelationOverrideDefModel>(this, "overrides", null);
			overrides.set(FXCollections.observableArrayList());
		}
		return overrides;
	}

	public ObservableList<ColumnDefModel> getExtraColumns() {
		return extraColumnsProperty().get();
	}

	public void setExtraColumns(ObservableList<ColumnDefModel> extraColumns) {
		if (this.extraColumns != null || extraColumns != null) {
			extraColumnsProperty().set(extraColumns);
		}
	}

	public ListProperty<ColumnDefModel> extraColumnsProperty() {
		if (extraColumns == null) {
			extraColumns = new SimpleListProperty<ColumnDefModel>(this, "extraColumns", null);
			extraColumns.set(FXCollections.observableArrayList());
		}
		return extraColumns;
	}

	public ReadOnlyObjectProperty<FieldDefModel> relatedFieldProperty() {
		return relatedField;
	}

	public FieldDefModel getRelatedField(){
		return relatedFieldProperty().get();
	}

	public ReadOnlyObjectProperty<DomainObjectDefModel> relatedDomainObjectProperty() {
		return relatedDomainObject;
	}

	public DomainObjectDefModel getRelatedDomainObject(){
		return relatedDomainObjectProperty().get();
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for (RelationOverrideDefModel overrideDef : getOverrides()) {
			overrideDef.accept(visitor, this);
		}

		for (FilterValueDefModel filterValueDef : getFilterValues()) {
			filterValueDef.accept(visitor, this);
		}

		for (ColumnDefModel extraColumnDef : getExtraColumns()) {
			extraColumnDef.accept(visitor, this);
		}

		visitor.visitFieldRelationshipDataDefModel(this, owner);
	}

	public DomainObjectDefModel getDomainObject() {
		return getFieldOwner().getDomainObjectOwner();
	}

	public FieldDefModel getFieldOwner(){
		return (FieldDefModel) getOwner();
	}

}
