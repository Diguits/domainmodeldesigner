package com.diguits.domainmodeldefinition.definitions;

public class FieldSubgroupDef extends BaseDef {
	private boolean hasVisualRepresentation;

	public FieldSubgroupDef(FieldGroupDef owner) {
		super(owner);
		initialize();
	}

	public FieldSubgroupDef() {
		initialize();
	}

	private void initialize() {
		hasVisualRepresentation = true;
	}

	public boolean getHasVisualRepresentation() {
		return hasVisualRepresentation;
	}

	public void setHasVisualRepresentation(boolean hasVisualRepresentation) {
		this.hasVisualRepresentation = hasVisualRepresentation;
	}

	public FieldGroupDef getOwnerFieldGroup() {
		return (FieldGroupDef) getOwner();
	}

	public void setOwnerFieldGroup(FieldGroupDef fieldGroup) {
		setOwner(fieldGroup);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitFieldSubgroupDef(this, owner);
	}
}