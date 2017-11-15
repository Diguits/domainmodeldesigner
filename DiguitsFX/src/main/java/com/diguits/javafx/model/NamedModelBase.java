package com.diguits.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NamedModelBase extends ModelBase {
	private StringProperty name;
	private StringProperty description;

	public String getName() {
		if (name != null)
			return name.get();
		return "";
	}

	public void setName(String name) {
		if (this.name != null || name == null || !name.equals("")) {
			nameProperty().set(name);
		}
	}

	public StringProperty nameProperty() {
		if (name == null) {
			name = new SimpleStringProperty(this, "name", "");
		}
		return name;
	}

	public String getDescription() {
		if (description != null)
			return description.get();
		return "";
	}

	public void setDescription(String description) {
		if (this.description != null || description == null || !description.equals("")) {
			descriptionProperty().set(description);
		}
	}

	public StringProperty descriptionProperty() {
		if (description == null) {
			description = new SimpleStringProperty(this, "description", "");
		}
		return description;
	}

	@Override
	public String toString() {
		if (name != null)
			return name.get();
		return super.toString();
	}
}
