package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class FieldGroupDefModel extends BaseDefModel  {

	public FieldGroupDefModel() {
		super();
	}
	private ListProperty<FieldSubgroupDefModel> subgroups;
	private BooleanProperty hasVisualRepresentation;

	public ObservableList<FieldSubgroupDefModel> getSubgroups() {
		return subgroupsProperty().get();
	}

	public void setSubgroups(ObservableList<FieldSubgroupDefModel> subgroups) {
		if (this.subgroups!= null || subgroups != null) {
			subgroupsProperty().set(subgroups);
		}
	}

	public ListProperty<FieldSubgroupDefModel> subgroupsProperty() {
		if(subgroups == null){
			subgroups = new SimpleListProperty<FieldSubgroupDefModel>(this, "subgroups", null);
		subgroups.set(FXCollections.observableArrayList());
		}
		return subgroups;
	}

	public boolean getHasVisualRepresentation() {
		if (hasVisualRepresentation!= null)
			return hasVisualRepresentation.get();
		return false;
	}

	public void setHasVisualRepresentation(boolean hasVisualRepresentation) {
		if (this.hasVisualRepresentation!= null || hasVisualRepresentation != false) {
			hasVisualRepresentationProperty().set(hasVisualRepresentation);
		}
	}

	public BooleanProperty hasVisualRepresentationProperty() {
		if(hasVisualRepresentation == null){
			hasVisualRepresentation = new SimpleBooleanProperty(this, "hasVisualRepresentation", false);
		}
		return hasVisualRepresentation;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for(FieldSubgroupDefModel fieldSubgroupDef : getSubgroups())
        {
            fieldSubgroupDef.accept(visitor, this);
        }
        visitor.visitFieldGroupDefModel(this, owner);
	}

	public EntityDefModel getEntityOwner(){
		return (EntityDefModel) getOwner();
	}
}
