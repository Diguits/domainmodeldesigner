package com.diguits.domainmodeldesigner.domainmodel.models;

import com.diguits.domainmodeldefinition.definitions.RelationshipType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RelationshipPartDefModel extends BaseDefModel {

	private ObjectProperty<FieldDefModel> field;
	private ObjectProperty<DomainObjectDefModel> domainObject;
	private ObjectProperty<RelationshipType> relationType;
	private ObjectProperty<RelationshipPartDefModel> toRelationshipPart;

	public RelationshipPartDefModel() {
		super();
		fieldProperty().addListener((v, o, n) -> {
			if (n != null && n.getOwner() != null)
				setDomainObject(n.getDomainObjectOwner());
		});
	}

	public FieldDefModel getField() {
		if (field != null)
			return field.get();
		return null;
	}

	public void setField(FieldDefModel field) {
		if (this.field != null || field != null) {
			fieldProperty().set(field);
		}
	}

	public ObjectProperty<FieldDefModel> fieldProperty() {
		if (field == null) {
			field = new SimpleObjectProperty<FieldDefModel>(this, "field", null);
		}
		return field;
	}

	public DomainObjectDefModel getDomainObject() {
		if (domainObject != null)
			return domainObject.get();
		return null;
	}

	public void setDomainObject(DomainObjectDefModel domainObject) {
		if (this.domainObject != null || domainObject != null) {
			domainObjectProperty().set(domainObject);
		}
	}

	public ObjectProperty<DomainObjectDefModel> domainObjectProperty() {
		if (domainObject == null) {
			domainObject = new SimpleObjectProperty<DomainObjectDefModel>(this, "domainObject", null);
		}
		return domainObject;
	}

	public RelationshipType getRelationType() {
		if (relationType != null)
			return relationType.get();
		return null;
	}

	public void setRelationType(RelationshipType relationType) {
		if (this.relationType != null || relationType != null) {
			relationTypeProperty().set(relationType);
		}
	}

	public ObjectProperty<RelationshipType> relationTypeProperty() {
		if (relationType == null) {
			relationType = new SimpleObjectProperty<RelationshipType>(this, "relationType", null);
		}
		return relationType;
	}

	public RelationshipPartDefModel getToRelationshipPart() {
		if (toRelationshipPart != null)
			return toRelationshipPart.get();
		return null;
	}

	public void setToRelationshipPart(RelationshipPartDefModel toRelationshipPart) {
		if (this.toRelationshipPart != null || toRelationshipPart != null) {
			toRelationshipPartProperty().set(toRelationshipPart);
		}
	}

	public ObjectProperty<RelationshipPartDefModel> toRelationshipPartProperty() {
		if (toRelationshipPart == null) {
			toRelationshipPart = new SimpleObjectProperty<RelationshipPartDefModel>(this, "toRelationshipPart",
					null);
		}
		return toRelationshipPart;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitRelationshipPartDefModel(this, owner);
	}

	public RelationshipDefModel getRelationshipOwner() {
		return (RelationshipDefModel) getOwner();
	}
}
