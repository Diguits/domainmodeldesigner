package com.diguits.domainmodeldefinition.definitions;

public class SimpleDomainObjectDef extends BaseDef {
	protected ModuleDef module;

	public SimpleDomainObjectDef(DomainModelDef owner) {
		super(owner);
		initialize();
	}

	public SimpleDomainObjectDef() {
		super();
		initialize();
	}

	private void initialize() {
	}

	public ModuleDef getModule() {
		return module;
	}

	public void setModule(ModuleDef module) {
		this.module = module;
	}

	public BoundedContextDef getBoundedContext() {
		return module != null ? module.getOwnerBoundedContext() : null;
	}

	public DomainModelDef getOwnerDomainModel() {
		return (DomainModelDef) getOwner();
	}

	public void setOwnerDomainModel(DomainModelDef domainModelDef) {
		setOwner(domainModelDef);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
	}
}