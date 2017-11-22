package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class IndexDef extends BaseDef {
	private List<FieldDef> fields;
	private boolean unique;

	public IndexDef(EntityDef owner) {
		super(owner);
		initialize();
	}

	public IndexDef() {
		initialize();
	}

	private void initialize() {
		fields = new ArrayList<FieldDef>();
	}

	public List<FieldDef> getFields() {
		return fields;
	}

	public void setFields(List<FieldDef> fields) {
		this.fields = fields;
	}

	public boolean getUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public EntityDef getOwnerEntity() {
		return (EntityDef) getOwner();
	}

	public void setOwnerEntity(EntityDef entity) {
		setOwner(entity);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitIndexDef(this, owner);
	}
}