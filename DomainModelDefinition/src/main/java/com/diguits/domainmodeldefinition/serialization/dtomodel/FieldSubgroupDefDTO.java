package com.diguits.domainmodeldefinition.serialization.dtomodel;

import org.simpleframework.xml.Default;

@Default(required = false)
public class FieldSubgroupDefDTO extends BaseDefDTO
{
	private boolean hasVisualRepresentation;

	public boolean getHasVisualRepresentation() {
		return hasVisualRepresentation;
	}

	public void setHasVisualRepresentation(boolean hasVisualRepresentation) {
		this.hasVisualRepresentation = hasVisualRepresentation;
	}


}