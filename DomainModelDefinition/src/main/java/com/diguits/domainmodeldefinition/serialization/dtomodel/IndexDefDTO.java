package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default(required = false)
public class IndexDefDTO extends BaseDefDTO
{
	private boolean isUnique;
	private List<NamedDefDTO> fields;

    public IndexDefDTO()
    {
        fields = new ArrayList<NamedDefDTO>();
    }

	public boolean getUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public List<NamedDefDTO> getFields() {
		return fields;
	}

	public void setFields(List<NamedDefDTO> fields) {
		this.fields = fields;
	}



}