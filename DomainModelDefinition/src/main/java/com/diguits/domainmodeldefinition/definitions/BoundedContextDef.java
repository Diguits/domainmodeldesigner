package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class BoundedContextDef extends BaseDef {

	private boolean includedInLicense;
	private boolean mandatoryInLoad;
	private String prefix;
	private List<BoundedContextDef> dependencies;
	private List<ModuleDef> modules;

	public BoundedContextDef(DomainModelDef owner) {
		super(owner);
		Initialize();
	}

	public BoundedContextDef() {
		super();
		Initialize();
	}

	private void Initialize() {
		dependencies = new ArrayList<BoundedContextDef>();
		modules = new ArrayList<ModuleDef>();
	}

	public boolean getIncludedInLicense() {
		return includedInLicense;
	}

	public void setIncludedInLicense(boolean includedInLicense) {
		this.includedInLicense = includedInLicense;
	}

	public boolean getMandatoryInLoad() {
		return mandatoryInLoad;
	}

	public void setMandatoryInLoad(boolean mandatoryInLoad) {
		this.mandatoryInLoad = mandatoryInLoad;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<BoundedContextDef> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<BoundedContextDef> dependencies) {
		this.dependencies = dependencies;
	}

	public List<ModuleDef> getModules() {
		return modules;
	}

	public void setModules(List<ModuleDef> modules) {
		this.modules = modules;
	}

	public DomainModelDef getOwnerDomainModel() {
		return (DomainModelDef) getOwner();
	}

	public void setOwnerDomainModel(DomainModelDef domainModel) {
		setOwner(domainModel);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		for (ModuleDef moduleDef : modules) {
			moduleDef.accept(visitor, this);
		}
		visitor.visitBoundedContextDef(this, owner);
	}
}