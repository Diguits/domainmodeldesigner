package com.diguits.domainmodeldesigner.domainmodelstructure.models;

import com.diguits.javafx.model.NamedModelBase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DomainModelStructureItem extends NamedModelBase {

    protected ObjectProperty<DomainModelStructureItem> parent;

    public DomainModelStructureItem() {
        super();
    }

    public DomainModelStructureItem(String name, String description, DomainModelStructureItem parent) {
        this();
        setName(name);
        setDescription(description);
        setParent(parent);
    }

    public String getCode() {
        return getCodeR();
    }

    private String getCodeR() {
        String result = getInnerCode();
        if (getParent() != null)
            if (result != "")
                result = getParent().getCodeR() + "." + result;
            else
                result = getParent().getCodeR();
        return result;
    }

    protected String getInnerCode() {
        return "";
    }

    public DomainModelStructureItem getParent() {
        if (parent != null)
            return parent.get();
        return null;
    }

    public void setParent(DomainModelStructureItem class1) {
        if (this.parent != null || class1 != null) {
            ((SimpleObjectProperty<DomainModelStructureItem>) parentProperty()).set(class1);
        }
    }

    public ObjectProperty<DomainModelStructureItem> parentProperty() {
        if (parent == null) {
            parent = new SimpleObjectProperty<DomainModelStructureItem>(this, "parent", null);
        }
        return parent;
    }

    @Override
    protected NamedModelBase getOwner() {
        return getParent();
    }
}
