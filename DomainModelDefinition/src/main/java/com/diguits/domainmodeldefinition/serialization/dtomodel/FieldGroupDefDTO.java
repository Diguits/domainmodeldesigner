package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default(required = false)
public class FieldGroupDefDTO extends BaseDefDTO
{
	private boolean hasVisualRepresentation;
	private List<FieldSubgroupDefDTO> subgroups;

    public FieldGroupDefDTO()
    {
        subgroups = new ArrayList<FieldSubgroupDefDTO>();
    }

	public boolean getHasVisualRepresentation() {
		return hasVisualRepresentation;
	}

	public void setHasVisualRepresentation(boolean hasVisualRepresentation) {
		this.hasVisualRepresentation = hasVisualRepresentation;
	}

	public List<FieldSubgroupDefDTO> getSubgroups() {
		return subgroups;
	}

	public void setSubgroups(List<FieldSubgroupDefDTO> subgroups) {
		this.subgroups = subgroups;
	}

}