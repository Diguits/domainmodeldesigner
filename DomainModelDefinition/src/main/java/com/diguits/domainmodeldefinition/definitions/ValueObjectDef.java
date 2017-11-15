package com.diguits.domainmodeldefinition.definitions;

public class ValueObjectDef extends DomainObjectDef{

	boolean persistAsEntity;

	public boolean getPersistAsEntity() {
		return persistAsEntity;
	}

	public void setPersistAsEntity(boolean persistAsEntity) {
		this.persistAsEntity = persistAsEntity;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitValueObjectDef(this, owner);
	}
}
