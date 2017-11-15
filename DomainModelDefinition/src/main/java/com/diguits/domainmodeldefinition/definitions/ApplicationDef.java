package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDef extends BaseDef {

	private List<BoundedContextDef> boundedContexts;

	public ApplicationDef(DomainModelDef owner) {
		super(owner);
		initilize();
	}

	public ApplicationDef() {
		super();
		initilize();
	}

	private void initilize() {
		boundedContexts = new ArrayList<BoundedContextDef>();
	}

	public List<BoundedContextDef> getBoundedContexts() {
		return boundedContexts;
	}

	public void setBoundedContexts(List<BoundedContextDef> boundedContexts) {
		this.boundedContexts = boundedContexts;
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
		visitor.visitApplicationDef(this, owner);
	}
}
