package com.diguits.domainmodeldesigner.domainmodel.models;

import java.util.UUID;

public class FindModelVisitor extends DomainModelDefinitionModelVisitorBase {

	private UUID idToFind;
	private BaseDefModel result;

	public FindModelVisitor(UUID idToFind) {
		super();
		this.idToFind = idToFind;
	}

	@Override
	protected void visitBaseDefModel(BaseDefModel baseDefModel, BaseDefModel owner) {
		if(baseDefModel.getId()==idToFind)
			result = baseDefModel;
		super.visitBaseDefModel(baseDefModel, owner);
	}

	public UUID getIdToFind() {
		return idToFind;
	}

	public void setIdToFind(UUID idToFind) {
		this.idToFind = idToFind;
	}

	public BaseDefModel getResult() {
		return result;
	}

	public void setResult(BaseDefModel result) {
		this.result = result;
	}
}
