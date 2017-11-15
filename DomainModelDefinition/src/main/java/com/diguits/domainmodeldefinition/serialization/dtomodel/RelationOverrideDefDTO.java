package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class RelationOverrideDefDTO extends BaseDefDTO {
	private UUID forEntityId;
	private String forEntityName;
	private UUID relationshipEntityId;
	private String relationshipEntityName;

	public UUID getForEntityId() {
		return forEntityId;
	}

	public void setForEntityId(UUID forEntityId) {
		this.forEntityId = forEntityId;
	}

	public String getForEntityName() {
		return forEntityName;
	}

	public void setForEntityName(String forEntityName) {
		this.forEntityName = forEntityName;
	}

	public UUID getRelationshipEntityId() {
		return relationshipEntityId;
	}

	public void setRelationshipEntityId(UUID relationshipEntityId) {
		this.relationshipEntityId = relationshipEntityId;
	}

	public String getRelationshipEntityName() {
		return relationshipEntityName;
	}

	public void setRelationshipEntityName(String relationshipEntityName) {
		this.relationshipEntityName = relationshipEntityName;
	}

}