package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class IndexDefModel extends BaseDefModel  {

	public IndexDefModel() {
		super();
	}
	private BooleanProperty unique;
	private ListProperty<FieldDefModel> fields;

	public boolean getUnique() {
		if (unique!= null)
			return unique.get();
		return false;
	}

	public void setUnique(boolean unique) {
		if (this.unique!= null || unique != false) {
			uniqueProperty().set(unique);
		}
	}

	public BooleanProperty uniqueProperty() {
		if(unique == null){
			unique = new SimpleBooleanProperty(this, "unique", false);
		}
		return unique;
	}

	public ObservableList<FieldDefModel> getFields() {
		return fieldsProperty().get();
	}

	public void setFields(ObservableList<FieldDefModel> fields) {
		if (this.fields!= null || fields != null) {
			fieldsProperty().set(fields);
		}
	}

	public ListProperty<FieldDefModel> fieldsProperty() {
		if(fields == null){
			fields = new SimpleListProperty<FieldDefModel>(this, "fields", null);
		fields.set(FXCollections.observableArrayList());
		}
		return fields;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitIndexDefModel(this, owner);
	}

	public EntityDefModel getEntityOwner(){
		return (EntityDefModel) getOwner();
	}
}
