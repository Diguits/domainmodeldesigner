package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class ApplicationDefModel extends BaseDefModel  {

	public ApplicationDefModel() {
		super();
	}

	private ListProperty<BoundedContextDefModel> boundedContexts;

	public ObservableList<BoundedContextDefModel> getBoundedContexts() {
		return boundedContextsProperty().get();
	}

	public void setBoundedContexts(ObservableList<BoundedContextDefModel> boundedContexts) {
		if (this.boundedContexts!= null || boundedContexts != null) {
			boundedContextsProperty().set(boundedContexts);
		}
	}

	public ListProperty<BoundedContextDefModel> boundedContextsProperty() {
		if(boundedContexts == null){
			boundedContexts = new SimpleListProperty<BoundedContextDefModel>(this, "boundedContexts", null);
		boundedContexts.set(FXCollections.observableArrayList());
		}
		return boundedContexts;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitApplicationDefModel(this, owner);
	}

	public DomainModelDefModel getDomainModelOwner(){
		return (DomainModelDefModel) getOwner();
	}
}
