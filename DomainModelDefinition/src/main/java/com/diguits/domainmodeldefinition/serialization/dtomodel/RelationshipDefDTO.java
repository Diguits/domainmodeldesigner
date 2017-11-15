package com.diguits.domainmodeldefinition.serialization.dtomodel;

import org.simpleframework.xml.Default;

@Default(required = false)
public class RelationshipDefDTO extends BaseDefDTO {
	private RelationshipPartDefDTO fromRelationshipPart;
	private RelationshipPartDefDTO toRelationshipPart;
	private boolean fromSideIsPrincipal;
	private String manyToManyEntityName;
	private boolean cascadeDelete;

	public RelationshipPartDefDTO getFromRelationshipPart() {
		return fromRelationshipPart;
	}

	public void setFromRelationshipPart(RelationshipPartDefDTO fromRelationshipPart) {
		this.fromRelationshipPart = fromRelationshipPart;
	}

	public RelationshipPartDefDTO getToRelationshipPart() {
		return toRelationshipPart;
	}

	public void setToRelationshipPart(RelationshipPartDefDTO toRelationshipPart) {
		this.toRelationshipPart = toRelationshipPart;
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

	public boolean getFromSideIsPrincipal() {
		return fromSideIsPrincipal;
	}

	public void setFromSideIsPrincipal(boolean fromSideIsPrincipal) {
		this.fromSideIsPrincipal = fromSideIsPrincipal;
	}

}