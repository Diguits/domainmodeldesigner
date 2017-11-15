package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default(required = false)
public class ApplicationDefDTO extends BaseDefDTO
{
	private List<NamedDefDTO> boundedContexts;

    public ApplicationDefDTO()
    {
        boundedContexts = new ArrayList<NamedDefDTO>();
    }

	public List<NamedDefDTO> getBoundedContexts() {
		return boundedContexts;
	}

	public void setBoundedContexts(List<NamedDefDTO> boundedContexts) {
		this.boundedContexts = boundedContexts;
	}



}
