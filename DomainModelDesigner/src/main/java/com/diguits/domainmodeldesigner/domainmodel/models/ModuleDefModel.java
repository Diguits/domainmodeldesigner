package com.diguits.domainmodeldesigner.domainmodel.models;


public class ModuleDefModel extends BaseDefModel  {

	public ModuleDefModel() {
		super();
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitEntityGroupDefModel(this, owner);
	}

	public BoundedContextDefModel getBoundedContextOwner(){
		return (BoundedContextDefModel) getOwner();
	}
}
