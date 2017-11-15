package com.diguits.templateengine.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TemplateProjectApplyConfigDTO extends TemplateProjectApplyConfigItemDTO{
	private UUID templateProjectId;
	private boolean isDefault;

	private String outputPath;
	private List<TemplateApplyConfigDTO> templatesConfig;

	public TemplateProjectApplyConfigDTO() {
		super();
		templatesConfig = new ArrayList<TemplateApplyConfigDTO>();
	}

	public UUID getTemplateProjectId() {
		return templateProjectId;
	}

	public void setTemplateProjectId(UUID templateProjectId) {
		this.templateProjectId = templateProjectId;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public List<TemplateApplyConfigDTO> getTemplatesConfig() {
		return templatesConfig;
	}

	public void setTemplatesConfig(List<TemplateApplyConfigDTO> templatesConfig) {
		this.templatesConfig = templatesConfig;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

}
