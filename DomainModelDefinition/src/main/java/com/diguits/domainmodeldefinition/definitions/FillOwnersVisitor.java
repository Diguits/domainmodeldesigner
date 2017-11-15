package com.diguits.domainmodeldefinition.definitions;

public class FillOwnersVisitor extends DomainModelDefinitionVisitorBase {

	@Override
	protected void visitBaseDef(BaseDef baseDef, BaseDef owner) {
		super.visitBaseDef(baseDef, owner);
		baseDef.setOwner(owner);
	}
}
