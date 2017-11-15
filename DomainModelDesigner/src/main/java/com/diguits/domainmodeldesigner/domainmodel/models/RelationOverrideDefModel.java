package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RelationOverrideDefModel extends BaseDefModel  {

	public RelationOverrideDefModel() {
		super();
	}
	private ObjectProperty<EntityDefModel> forEntity;
	private ObjectProperty<EntityDefModel> relationshipEntity;

	public EntityDefModel getForEntity() {
		if (forEntity!= null)
			return forEntity.get();
		return null;
	}

	public void setForEntity(EntityDefModel forEntity) {
		if (this.forEntity!= null || forEntity != null) {
			forEntityProperty().set(forEntity);
		}
	}

	public ObjectProperty<EntityDefModel> forEntityProperty() {
		if(forEntity == null){
			forEntity = new SimpleObjectProperty<EntityDefModel>(this, "forEntity", null);
		}
		return forEntity;
	}

	public EntityDefModel getRelationshipEntity() {
		if (relationshipEntity!= null)
			return relationshipEntity.get();
		return null;
	}

	public FieldRelationshipDataDefModel getDomainModelOwner(){
		return (FieldRelationshipDataDefModel) getOwner();
	}

	public void setRelationshipEntity(EntityDefModel relationshipEntity) {
		if (this.relationshipEntity!= null || relationshipEntity != null) {
			relationshipEntityProperty().set(relationshipEntity);
		}
	}

	public ObjectProperty<EntityDefModel> relationshipEntityProperty() {
		if(relationshipEntity == null){
			relationshipEntity = new SimpleObjectProperty<EntityDefModel>(this, "relationshipEntity", null);
		}
		return relationshipEntity;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitRelationOverrideDefModel(this, owner);
	}

}
