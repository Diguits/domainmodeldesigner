package com.diguits.domainmodeldefinition.definitions;

public class ModuleDef extends BaseDef {
	public ModuleDef(BoundedContextDef owner) {
		super(owner);
	}

	public ModuleDef() {
		super();
	}

	public BoundedContextDef getOwnerBoundedContext() {
		return (BoundedContextDef) getOwner();
	}

	public void setOwnerBoundedContext(BoundedContextDef boundedContext) {
		setOwner(boundedContext);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitEntityGroupDef(this, owner);
	}
}