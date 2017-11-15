package com.diguits.domainmodeldefinition.definitions;

public class EnumValueDef extends BaseDef {
	public EnumValueDef(EnumDef owner) {
		super(owner);
	}

	public EnumValueDef() {
		super();
	}

	public EnumDef getOwnerEnum() {
		return (EnumDef) getOwner();
	}

	public void setOwnerEnum(EnumDef enumDef) {
		setOwner(enumDef);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitEnumValueDef(this, owner);
	}
}