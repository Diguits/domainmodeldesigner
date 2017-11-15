package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FieldSubgroupDefModel extends BaseDefModel  {

	public FieldSubgroupDefModel() {
		super();
	}
	private BooleanProperty hasVisualRepresentation;

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
		visitor.visitFieldSubgroupDefModel(this, owner);
	}

	public FieldGroupDefModel getFieldGroupOwner(){
		return (FieldGroupDefModel) getOwner();
	}
}
