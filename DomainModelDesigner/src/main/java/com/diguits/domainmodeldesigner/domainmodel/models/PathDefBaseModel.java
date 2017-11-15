package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.StringProperty;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public abstract class PathDefBaseModel extends BaseDefModel {

	public PathDefBaseModel() {
		super();
		pathProperty().addListener((v, o, n) -> {
			pathChange(o, n);
		});
	}

	protected void pathChange(String o, String n) {
		if (pathFieldChain != null)
			pathFieldChain.set(null);
	}

	private StringProperty path;
	private ListProperty<FieldDefModel> pathFieldChain;

	public String getPath() {
		if (path != null)
			return path.get();
		return "";
	}

	public void setPath(String path) {
		if (this.path != null || path == null || !path.equals("")) {
			pathProperty().set(path);
		}
	}

	public StringProperty pathProperty() {
		if (path == null) {
			path = new SimpleStringProperty(this, "path", "");
		}
		return path;
	}

	public ObservableList<FieldDefModel> getPathFieldChain() {
		return pathFieldChainProperty().get();
	}

	public ListProperty<FieldDefModel> pathFieldChainProperty() {
		if (pathFieldChain == null) {
			pathFieldChain = new SimpleListProperty<FieldDefModel>(this, "pathFieldChain", null);
		}
		if (pathFieldChain.get() == null)
			fillPathFieldChain();
		return pathFieldChain;
	}

	private void fillPathFieldChain() {
		if ((pathFieldChain.get() == null) && getPath() != null && !getPath().trim().isEmpty()) {
			EntityDefModel entity = getEntity();
			if (entity != null) {
				pathFieldChain.set(FXCollections.observableArrayList());
				String[] fields = getPath().split("[.]");

				ObservableList<FieldDefModel> entityFields = entity.getFields();
				int j = 0;
				while (j < entityFields.size() && !entityFields.get(j).getName().equals(fields[0]))
					j++;

				FieldDefModel selected = j < entityFields.size() ? entityFields.get(j) : null;
				if (selected != null) {
					pathFieldChain.add(selected);
				}
				int i = 1;
				while (i < fields.length && selected != null
						&& selected.getRelationshipData().getRelatedDomainObject() != null) {
					entityFields = selected.getRelationshipData().getRelatedDomainObject().getFields();
					j = 0;
					while (j < entityFields.size() && entityFields.get(j).getName() != fields[i])
						j++;

					selected = j < entityFields.size() ? entityFields.get(j) : null;

					if (selected != null) {
						pathFieldChain.add(selected);
					} else {
						pathFieldChain.set(null);
						;
						break;
					}
					i++;
				}
			}
		}
	}

	public FieldDefModel getField() {
		ObservableList<FieldDefModel> pathFields = getPathFieldChain();
		if (pathFields != null && pathFields.size() > 0)
			return pathFields.get(pathFields.size() - 1);
		return null;
	}

	public abstract EntityDefModel getEntity();
}
