package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FieldGroupDef extends BaseDef {
	private boolean hasVisualRepresentation;
	private List<FieldSubgroupDef> subgroups;

	public FieldGroupDef(EntityDef owner) {
		super(owner);
		initialize();
	}

	public FieldGroupDef() {
		initialize();
	}

	private void initialize() {
		hasVisualRepresentation = true;
		subgroups = new ArrayList<FieldSubgroupDef>();
	}

	public boolean getHasVisualRepresentation() {
		return hasVisualRepresentation;
	}

	public void setHasVisualRepresentation(boolean hasVisualRepresentation) {
		this.hasVisualRepresentation = hasVisualRepresentation;
	}

	public List<FieldSubgroupDef> getSubgroups() {
		return subgroups;
	}

	public void setSubgroups(List<FieldSubgroupDef> subgroups) {
		this.subgroups = subgroups;
	}

	public DomainObjectDef getOwnerDomainObject() {
		return (DomainObjectDef) getOwner();
	}

	public void setOwnerDomainObject(DomainObjectDef domainObject) {
		setOwner(domainObject);
	}

	public FieldSubgroupDef getFieldSubgroupById(UUID subgroupById) {
		int i = 0;
		while (i < subgroups.size() && !subgroups.get(i).getId().equals(subgroupById))
			i++;
		return i < subgroups.size() ? subgroups.get(i) : null;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		for (FieldSubgroupDef fieldSubgroupDef : subgroups) {
			fieldSubgroupDef.accept(visitor, this);
		}
		visitor.visitFieldGroupDef(this, owner);
	}
}