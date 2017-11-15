package com.diguits.domainmodeldesigner.domainmodel.models;


public class EnumValueDefModel extends BaseDefModel  {

	public EnumValueDefModel() {
		super();
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitEnumValueDefModel(this, owner);
	}

	public EnumDefModel getEnumOwner(){
		return (EnumDefModel) getOwner();
	}
}
