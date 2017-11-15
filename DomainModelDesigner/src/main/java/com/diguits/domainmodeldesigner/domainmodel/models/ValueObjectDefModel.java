package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ValueObjectDefModel extends DomainObjectDefModel {

	private BooleanProperty persistAsEntity;

	public ValueObjectDefModel() {
		super();
	}

	public boolean getPersistAsEntity() {
		if (persistAsEntity != null)
			return persistAsEntity.get();
		return false;
	}

	public void setPersistAsEntity(boolean persistAsEntity) {
		if (this.persistAsEntity != null || persistAsEntity != false) {
			persistAsEntityProperty().set(persistAsEntity);
		}
	}

	public BooleanProperty persistAsEntityProperty() {
		if (persistAsEntity == null) {
			persistAsEntity = new SimpleBooleanProperty(this, "persistAsEntity", false);
		}
		return persistAsEntity;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitValueObjectDefModel(this, owner);
	}

	public DomainModelDefModel getDomainModelOwner() {
		return (DomainModelDefModel) getOwner();
	}
}
