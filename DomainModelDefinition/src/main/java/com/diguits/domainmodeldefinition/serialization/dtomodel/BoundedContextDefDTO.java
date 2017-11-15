package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class BoundedContextDefDTO extends BaseDefDTO {

	private boolean includedInLicense;
	private boolean mandatoryInLoad;
	private String prefix;
	private List<UUID> dependencies;
	private List<ModuleDefDTO> modules;

	public BoundedContextDefDTO() {
		modules = new ArrayList<ModuleDefDTO>();
		dependencies = new ArrayList<UUID>();
	}

	public boolean getIncludedInLicense() {
		return includedInLicense;
	}

	public void setIncludedInLicense(boolean includedInLicense) {
		this.includedInLicense = includedInLicense;
	}

	public boolean getMandatoryInLoad() {
		return mandatoryInLoad;
	}

	public void setMandatoryInLoad(boolean mandatoryInLoad) {
		this.mandatoryInLoad = mandatoryInLoad;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<UUID> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<UUID> dependencies) {
		this.dependencies = dependencies;
	}

	public List<ModuleDefDTO> getModules() {
		return modules;
	}

	public void setModules(List<ModuleDefDTO> modules) {
		this.modules = modules;
	}
}