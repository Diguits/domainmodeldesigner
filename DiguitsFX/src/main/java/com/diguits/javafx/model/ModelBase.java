package com.diguits.javafx.model;

import java.util.UUID;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelBase {
	public ModelBase() {
		setId(UUID.randomUUID());
	}

	private ObjectProperty<UUID> id;
	private StringProperty tag;
	protected BooleanProperty isDirty;

	public UUID getId() {
		if (id != null)
			return id.get();
		return null;
	}

	public void setId(UUID id) {
		if (this.id != null || id != null) {
			idProperty().set(id);
		}
	}

	public ObjectProperty<UUID> idProperty() {
		if (id == null) {
			id = new SimpleObjectProperty<UUID>(this, "id", null);
		}
		return id;
	}

	public String getTag() {
		if (tag != null)
			return tag.get();
		return "";
	}

	public void setTag(String tag) {
		if (this.tag != null || tag == null || !tag.equals("")) {
			tagProperty().set(tag);
		}
	}

	public StringProperty tagProperty() {
		if (tag == null) {
			tag = new SimpleStringProperty(this, "tag", "");
		}
		return tag;
	}

	public boolean getIsDirty() {
		if (isDirty != null)
			return isDirty.get();
		return false;
	}

	public void setIsDirty(boolean isDirty) {
		if (this.isDirty != null || isDirty != false) {
			isDirtyProperty().set(isDirty);
		}
	}

	public BooleanProperty isDirtyProperty() {
		if (isDirty == null) {
			isDirty = new SimpleBooleanProperty(this, "isDirty", false);
		}
		return isDirty;
	}
}