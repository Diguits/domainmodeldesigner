package com.diguits.domainmodeldefinition.definitions;

public class RelationshipPartDef extends BaseDef {

	private RelationshipType relationType;
	private DomainObjectDef domainObject;
	private FieldDef field;

	private RelationshipPartDef toRelationshipPart;

	public RelationshipPartDef(RelationshipDef owner) {
		super(owner);
	}

	public RelationshipPartDef() {
	}

	public DomainObjectDef getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(DomainObjectDef domainObject) {
		this.domainObject = domainObject;
	}

	public FieldDef getField() {
		return field;
	}

	public void setField(FieldDef field) {
		this.field = field;
		if (field != null)
			domainObject = field.getOwnerDomainObject();
	}

	public RelationshipType getRelationType() {
		if (field != null)
			return field.getDataType() == DataType.Entity ? RelationshipType.One : RelationshipType.Many;
		return relationType;
	}

	public void setRelationType(RelationshipType relationType) {
		this.relationType = relationType;
	}

	public boolean getIsPrincipalSide() {
		return ((getOwnerRelationship().getFromPart() == this
				&& getOwnerRelationship().getFromSideIsPrincipal())||
				(getOwnerRelationship().getToPart() == this
				&& !getOwnerRelationship().getFromSideIsPrincipal()));
	}

	public void setIsPrincipalSide(boolean isPrincipalSide) {
		getOwnerRelationship().setFromSideIsPrincipal(
				getOwnerRelationship().getFromPart() == this ? isPrincipalSide : !isPrincipalSide);
	}

	public RelationshipPartDef getToRelationshipPart() {
		return toRelationshipPart;
	}

	public void setToRelationshipPart(RelationshipPartDef relationshipPart) {
		toRelationshipPart = relationshipPart;
		if (toRelationshipPart != null && toRelationshipPart.getToRelationshipPart() != this)
			toRelationshipPart.setToRelationshipPart(this);
	}

	public RelationshipDef getOwnerRelationship() {
		return (RelationshipDef) getOwner();
	}

	public void setOwnerRelationship(RelationshipDef Relationship) {
		setOwner(Relationship);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitRelationshipPartDef(this, owner);
	}
}
