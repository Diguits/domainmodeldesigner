package com.diguits.domainmodeldesigner.domainmodel.models;

public class FillModelOwnersVisitor extends DomainModelDefinitionModelVisitorBase {

	@Override
	protected void visitBaseDefModel(BaseDefModel baseDefModel, BaseDefModel owner) {
		baseDefModel.setOwner(owner);
	}
}
