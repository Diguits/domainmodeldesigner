package com.diguits.domainmodeldesigner.template.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class TemplateProjectDefModel extends TemplateProjectItemDefModel {

	public TemplateProjectDefModel() {
		super();
	}

	private ListProperty<TemplateGroupDefModel> groups;

	public ObservableList<TemplateGroupDefModel> getGroups() {
		return groupsProperty().get();
	}

	public void setGroups(ObservableList<TemplateGroupDefModel> groups) {
		if (this.groups != null || groups != null) {
			groupsProperty().set(groups);
		}
	}

	public ListProperty<TemplateGroupDefModel> groupsProperty() {
		if (groups == null) {
			groups = new SimpleListProperty<TemplateGroupDefModel>(this, "groups", null);
			groups.set(FXCollections.observableArrayList());
		}
		return groups;
	}

	public List<TemplateDefModel> getTemplates() {
		ArrayList<TemplateDefModel> templates = new ArrayList<TemplateDefModel>();
		for (TemplateGroupDefModel group : getGroups()) {
			for (TemplateDefModel template : group.getTemplates()) {
				templates.add(template);
			}
		}
		return templates;
	}
}
