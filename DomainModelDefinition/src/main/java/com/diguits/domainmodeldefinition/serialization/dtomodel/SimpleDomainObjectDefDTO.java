package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class SimpleDomainObjectDefDTO extends BaseDefDTO{
	protected UUID moduleId;
	protected String moduleName;

	public SimpleDomainObjectDefDTO() {
	}

	public UUID getModuleId() {
		return moduleId;
	}

	public void setModuleId(UUID moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
