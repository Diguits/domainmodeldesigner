package com.diguits.domainmodeldefinition.serialization.dtomodel;

public class ValueObjectDefDTO extends DomainObjectDefDTO {
	boolean persistAsEntity;

	public boolean getPersistAsEntity() {
		return persistAsEntity;
	}

	public void setPersistAsEntity(boolean persistAsEntity) {
		this.persistAsEntity = persistAsEntity;
	}
}
