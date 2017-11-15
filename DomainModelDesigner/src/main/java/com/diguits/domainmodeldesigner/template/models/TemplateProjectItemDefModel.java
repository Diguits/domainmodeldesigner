package com.diguits.domainmodeldesigner.template.models;

import com.diguits.javafx.model.NamedModelBase;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TemplateProjectItemDefModel extends NamedModelBase {

	public TemplateProjectItemDefModel() {
		super();
	}

	protected BooleanProperty active;
	protected ObjectProperty<TemplateProjectItemDefModel> owner;

	public boolean getActive() {
		if (active != null)
			return active.get();
		return false;
	}

	public void setActive(boolean active) {
		if (this.active != null || active != false) {
			activeProperty().set(active);
		}
	}

	public BooleanProperty activeProperty() {
		if (active == null) {
			active = new SimpleBooleanProperty(this, "active", false);
		}
		return active;
	}

	public TemplateProjectItemDefModel getOwner() {
		if (owner != null)
			return owner.get();
		return null;
	}

	public ObjectProperty<TemplateProjectItemDefModel> ownerProperty() {
		if (owner == null) {
			owner = new SimpleObjectProperty<TemplateProjectItemDefModel>(this, "owner", null);
		}
		return owner;
	}
}
