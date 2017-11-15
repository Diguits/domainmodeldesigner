package com.diguits.domainmodeldesigner.template.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class TemplateGroupDefModel extends TemplateProjectItemDefModel  {

	public TemplateGroupDefModel() {
		super();
	}
	private ListProperty<TemplateDefModel> templates;

	public ObservableList<TemplateDefModel> getTemplates() {
		return templatesProperty().get();
	}

	public void setTemplates(ObservableList<TemplateDefModel> templates) {
		if (this.templates!= null || templates != null) {
			templatesProperty().set(templates);
		}
	}

	public TemplateProjectDefModel getProjectOwner() {
		if (owner != null)
			return (TemplateProjectDefModel) owner.get();
		return null;
	}

	public void setProjectOwner(TemplateProjectDefModel owner) {
		if (this.owner != null || owner != null) {
			ownerProperty().set(owner);
		}
	}

	public ListProperty<TemplateDefModel> templatesProperty() {
		if(templates == null){
			templates = new SimpleListProperty<TemplateDefModel>(this, "templates", null);
		templates.set(FXCollections.observableArrayList());
		}
		return templates;
	}

}
