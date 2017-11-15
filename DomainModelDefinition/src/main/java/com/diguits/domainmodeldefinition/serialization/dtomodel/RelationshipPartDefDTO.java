package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.UUID;

import org.simpleframework.xml.Default;
import com.diguits.domainmodeldefinition.definitions.RelationshipType;

@Default(required = false)
public class RelationshipPartDefDTO extends BaseDefDTO {

	private UUID fieldId;
	private UUID domainObjectId;
	private boolean domainObjectIsValueObject;
	private RelationshipType relationType;
	private UUID toRelationshipPartId;

	public UUID getFieldId() {
		return fieldId;
	}

	public void setFieldId(UUID fieldId) {
		this.fieldId = fieldId;
	}

	public UUID getDomainObjectId() {
		return domainObjectId;
	}

	public boolean getDomainObjectIsValueObject() {
		return domainObjectIsValueObject;
	}

	public void setDomainObjectIsValueObject(boolean domainObjectIsValueObject) {
		this.domainObjectIsValueObject = domainObjectIsValueObject;
	}

	public void setDomainObjectId(UUID domainObjectId) {
		this.domainObjectId = domainObjectId;
	}

	public RelationshipType getRelationType() {
		return relationType;
	}

	public void setRelationType(RelationshipType relationType) {
		this.relationType = relationType;
	}

	public UUID getToRelationshipPartId() {
		return toRelationshipPartId;
	}

	public void setToRelationshipPartId(UUID toRelationshipPartId) {
		this.toRelationshipPartId = toRelationshipPartId;
	}

}