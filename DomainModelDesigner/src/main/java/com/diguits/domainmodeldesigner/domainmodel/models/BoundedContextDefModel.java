package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleListProperty;

public class BoundedContextDefModel extends BaseDefModel  {

	public BoundedContextDefModel() {
		super();
	}
	private BooleanProperty includedInLicense;
	private StringProperty prefix;
	private ListProperty<ModuleDefModel> modules;
	private BooleanProperty mandatoryInLoad;
	private ListProperty<BoundedContextDefModel> dependencies;

	public boolean getIncludedInLicense() {
		if (includedInLicense!= null)
			return includedInLicense.get();
		return false;
	}

	public void setIncludedInLicense(boolean includedInLicense) {
		if (this.includedInLicense!= null || includedInLicense != false) {
			includedInLicenseProperty().set(includedInLicense);
		}
	}

	public BooleanProperty includedInLicenseProperty() {
		if(includedInLicense == null){
			includedInLicense = new SimpleBooleanProperty(this, "includedInLicense", false);
		}
		return includedInLicense;
	}

	public String getPrefix() {
		if (prefix!= null)
			return prefix.get();
		return "";
	}

	public void setPrefix(String prefix) {
		if (this.prefix!= null || prefix == null || !prefix.equals("")) {
			prefixProperty().set(prefix);
		}
	}

	public StringProperty prefixProperty() {
		if(prefix == null){
			prefix = new SimpleStringProperty(this, "prefix", "");
		}
		return prefix;
	}

	public ObservableList<ModuleDefModel> getModules() {
		return modulesProperty().get();
	}

	public void setModules(ObservableList<ModuleDefModel> modules) {
		if (this.modules!= null || modules != null) {
			modulesProperty().set(modules);
		}
	}

	public ListProperty<ModuleDefModel> modulesProperty() {
		if(modules == null){
			modules = new SimpleListProperty<ModuleDefModel>(this, "modules", null);
		modules.set(FXCollections.observableArrayList());
		}
		return modules;
	}

	public boolean getMandatoryInLoad() {
		if (mandatoryInLoad!= null)
			return mandatoryInLoad.get();
		return false;
	}

	public void setMandatoryInLoad(boolean mandatoryInLoad) {
		if (this.mandatoryInLoad!= null || mandatoryInLoad != false) {
			mandatoryInLoadProperty().set(mandatoryInLoad);
		}
	}

	public BooleanProperty mandatoryInLoadProperty() {
		if(mandatoryInLoad == null){
			mandatoryInLoad = new SimpleBooleanProperty(this, "mandatoryInLoad", false);
		}
		return mandatoryInLoad;
	}

	public ObservableList<BoundedContextDefModel> getDependencies() {
		return dependenciesProperty().get();
	}

	public void setDependencies(ObservableList<BoundedContextDefModel> dependencies) {
		if (this.dependencies!= null || dependencies != null) {
			dependenciesProperty().set(dependencies);
		}
	}

	public ListProperty<BoundedContextDefModel> dependenciesProperty() {
		if(dependencies == null){
			dependencies = new SimpleListProperty<BoundedContextDefModel>(this, "dependencies", null);
		dependencies.set(FXCollections.observableArrayList());
		}
		return dependencies;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for (ModuleDefModel moduleDef : getModules()){
            moduleDef.accept(visitor, this);
        }
        visitor.visitBoundedContextDefModel(this, owner);
	}

	public DomainModelDefModel getDomainModelOwner(){
		return (DomainModelDefModel) getOwner();
	}
}
