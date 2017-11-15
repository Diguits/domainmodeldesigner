package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RelationshipDefModel extends BaseDefModel  {

	public RelationshipDefModel() {
		super();
		RelationshipPartDefModel fromPart = new RelationshipPartDefModel();
		fromPart.setOwner(this);
		setFromPart(fromPart);

		RelationshipPartDefModel toPart = new RelationshipPartDefModel();
		toPart.setOwner(this);
		setToPart(toPart);

		fromPart.setToRelationshipPart(toPart);
		toPart.setToRelationshipPart(fromPart);
	}

	private ObjectProperty<RelationshipPartDefModel> fromPart;
	private ObjectProperty<RelationshipPartDefModel> toPart;

	private StringProperty manyToManyEntityName;
	private BooleanProperty fromSideIsPrincipal;
	private BooleanProperty cascadeDelete;


	public String getManyToManyEntityName() {
		if (manyToManyEntityName!= null)
			return manyToManyEntityName.get();
		return "";
	}

	public void setManyToManyEntityName(String manyToManyEntityName) {
		if (this.manyToManyEntityName!= null || manyToManyEntityName == null || !manyToManyEntityName.equals("")) {
			manyToManyEntityNameProperty().set(manyToManyEntityName);
		}
	}

	public StringProperty manyToManyEntityNameProperty() {
		if(manyToManyEntityName == null){
			manyToManyEntityName = new SimpleStringProperty(this, "manyToManyEntityName", "");
		}
		return manyToManyEntityName;
	}

	public boolean getFromSideIsPrincipal() {
		if (fromSideIsPrincipal != null)
			return fromSideIsPrincipal.get();
		return false;
	}

	public void setFromSideIsPrincipal(boolean fromSideIsPrincipal) {
		if (this.fromSideIsPrincipal != null || fromSideIsPrincipal != false) {
			fromSideIsPrincipalProperty().set(fromSideIsPrincipal);
		}
	}

	public BooleanProperty fromSideIsPrincipalProperty() {
		if (fromSideIsPrincipal == null) {
			fromSideIsPrincipal = new SimpleBooleanProperty(this, "fromSideIsPrincipal", false);
		}
		return fromSideIsPrincipal;
	}

	public boolean getCascadeDelete() {
		if (cascadeDelete != null)
			return cascadeDelete.get();
		return false;
	}

	public void setCascadeDelete(boolean cascadeDelete) {
		if (this.cascadeDelete != null || cascadeDelete != false) {
			cascadeDeleteProperty().set(cascadeDelete);
		}
	}

	public BooleanProperty cascadeDeleteProperty() {
		if (cascadeDelete == null) {
			cascadeDelete = new SimpleBooleanProperty(this, "cascadeDelete", false);
		}
		return cascadeDelete;
	}

	public RelationshipPartDefModel getToPart() {
		if (toPart != null)
			return toPart.get();
		return null;
	}

	public void setToPart(RelationshipPartDefModel toPart) {
		if (this.toPart != null || toPart != null) {
			toPartProperty().set(toPart);
		}
	}

	public ObjectProperty<RelationshipPartDefModel> toPartProperty() {
		if (toPart == null) {
			toPart = new SimpleObjectProperty<RelationshipPartDefModel>(this, "toPart", null);
		}
		return toPart;
	}

	public RelationshipPartDefModel getFromPart() {
		if (fromPart != null)
			return fromPart.get();
		return null;
	}

	public void setFromPart(RelationshipPartDefModel fromPart) {
		if (this.fromPart != null || fromPart != null) {
			fromPartProperty().set(fromPart);
		}
	}

	public ObjectProperty<RelationshipPartDefModel> fromPartProperty() {
		if (fromPart == null) {
			fromPart = new SimpleObjectProperty<RelationshipPartDefModel>(this, "fromPart", null);
		}
		return fromPart;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		getFromPart().accept(visitor, this);
		getToPart().accept(visitor, this);
		visitor.visitRelationshipDefModel(this, owner);
	}

	public DomainModelDefModel getDomainModelOwner(){
		return (DomainModelDefModel) getOwner();
	}
}
