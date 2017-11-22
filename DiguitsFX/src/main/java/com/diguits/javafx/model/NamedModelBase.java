package com.diguits.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class NamedModelBase extends ModelBase {
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

    public String getFullName() {
        String string = toString();
        String parentStr = "";
        NamedModelBase parent = getOwner();
        while (parent != null) {
            if (parentStr.length() > 0) {
                parentStr = "." + parentStr;
            }
            parentStr = parent.getName() + parentStr;
            parent = parent.getOwner();
        }
        if (parentStr.length() > 0) {
            string += " (" + parentStr + ")";
        }
        return string;
    }

    protected abstract NamedModelBase getOwner();

    @Override
    public String toString() {
        if (name != null)
            return name.get();
        return super.toString();
    }
}
