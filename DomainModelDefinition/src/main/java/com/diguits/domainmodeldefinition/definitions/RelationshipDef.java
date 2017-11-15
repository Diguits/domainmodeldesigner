package com.diguits.domainmodeldefinition.definitions;

public class RelationshipDef extends BaseDef {

	private RelationshipPartDef fromPart;
	private RelationshipPartDef toPart;
	private String manyToManyEntityName;
	private boolean cascadeDelete;
	private boolean fromSideIsPrincipal;

	public RelationshipDef(DomainModelDef owner) {
		super(owner);
	}

	public RelationshipDef() {
	}

	public RelationshipPartDef getFromPart() {
		return fromPart;
	}

	public void setFromPart(RelationshipPartDef Relationship) {
		fromPart = Relationship;
		if (toPart != null)
			toPart.setToRelationshipPart(fromPart);
	}

	public RelationshipPartDef getToPart() {
		return toPart;
	}

	public void setToPart(RelationshipPartDef Relationship) {
		{
			toPart = Relationship;
			if (fromPart != null)
				fromPart.setToRelationshipPart(toPart);
		}
	}

	public String getManyToManyEntityName() {
		return manyToManyEntityName;
	}

	public void setManyToManyEntityName(String manyToManyEntityName) {
		this.manyToManyEntityName = manyToManyEntityName;
	}

	public boolean getCascadeDelete() {
		return cascadeDelete;
	}

	public void setCascadeDelete(boolean cascadeDelete) {
		this.cascadeDelete = cascadeDelete;
	}

	public FieldDef getFromField() {
		return fromPart != null ? fromPart.getField() : null;
	}

	public FieldDef getToField() {
		return toPart != null ? toPart.getField() : null;
	}

	public RelationshipType getFromRelationType() {
		return fromPart != null ? fromPart.getRelationType() : RelationshipType.One;
	}

	public RelationshipType getToRelationType() {
		return toPart != null ? toPart.getRelationType() : RelationshipType.One;
	}

	public boolean getFromSideIsPrincipal() {
		return fromSideIsPrincipal;
	}

	public void setFromSideIsPrincipal(boolean fromSideIsPrincipal) {
		this.fromSideIsPrincipal = fromSideIsPrincipal;
	}

	public DomainObjectDef getFromEntity() {
		FieldDef fromField = getFromField();
		return fromField != null ? fromField.getOwnerDomainObject() : null;
	}

	public DomainObjectDef getToEntity() {
		return getToField() != null ? getToField().getOwnerDomainObject() : null;
	}

	public DomainModelDef getOwnerDomainModel() {
		return (DomainModelDef) getOwner();
	}

	public void setOwnerDomainModel(DomainModelDef domainModel) {
		setOwner(domainModel);
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		if (fromPart != null)
			fromPart.accept(visitor, this);
		if (toPart != null)
			toPart.accept(visitor, this);
		visitor.visitRelationshipDef(this, owner);
	}
}
