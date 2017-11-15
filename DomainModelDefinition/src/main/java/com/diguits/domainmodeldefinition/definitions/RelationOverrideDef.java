package com.diguits.domainmodeldefinition.definitions;

public class RelationOverrideDef extends BaseDef {
	private EntityDef forEntity;
	private EntityDef relationshipEntity;

	public RelationOverrideDef(RelationshipPartDef owner) {
		super(owner);
	}

	public RelationOverrideDef() {
		super();
	}

	public EntityDef getForEntity() {
		return forEntity;
	}

	public void setForEntity(EntityDef forEntity) {
		this.forEntity = forEntity;
	}

	public EntityDef getRelationshipEntity() {
		return relationshipEntity;
	}

	public void setRelationshipEntity(EntityDef relationshipEntity) {
		this.relationshipEntity = relationshipEntity;
	}

	public RelationshipPartDef getOwnerRelationPart() {
		return (RelationshipPartDef) getOwner();
	}

	public void setOwnerRelationPart(RelationshipPartDef relationPart) {
		setOwner(relationPart);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitRelationOverrideDef(this, owner);
	}
}
