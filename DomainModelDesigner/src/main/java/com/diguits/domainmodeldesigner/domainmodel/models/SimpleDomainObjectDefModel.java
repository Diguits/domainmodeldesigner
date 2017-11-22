package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;

import javafx.beans.property.SimpleObjectProperty;

public class SimpleDomainObjectDefModel extends BaseDefModel {

    public SimpleDomainObjectDefModel() {
        super();
    }

    protected ObjectProperty<ModuleDefModel> module;

    public ModuleDefModel getModule() {
        if (module != null)
            return module.get();
        return null;
    }

    public void setModule(ModuleDefModel module) {
        if (this.module != null || module != null) {
            moduleProperty().set(module);
        }
    }

    public ObjectProperty<ModuleDefModel> moduleProperty() {
        if (module == null) {
            module = new SimpleObjectProperty<ModuleDefModel>(this, "module", null);
        }
        return module;
    }

    @Override
    public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
        super.accept(visitor, owner);
    }

    public DomainModelDefModel getDomainModelOwner() {
        return (DomainModelDefModel) getOwner();
    }

    @Override
    public String getFullName() {
        if (getModule() != null) {
            String string = toString();
            String parentStr = getModule().toString();
            if (getModule().getBoundedContextOwner() != null) {
                parentStr = getModule().getBoundedContextOwner().toString() + "." + parentStr;
            }
            return string + " (" + parentStr + ")";
        } else {
            return super.getFullName();
        }
    }
}
