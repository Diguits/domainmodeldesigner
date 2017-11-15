package com.diguits.templateengine.contract.model;

import java.util.ArrayList;
import java.util.List;

public class TemplateProjectApplyConfig extends TemplateProjectApplyConfigItem{
	private boolean isDefault;

	private String outputPath;

	private List<TemplateApplyConfig> templatesConfig;

	public TemplateProjectApplyConfig() {
		super();
		templatesConfig = new ArrayList<TemplateApplyConfig>();
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public List<TemplateApplyConfig> getTemplatesConfig() {
		return templatesConfig;
	}

	public void setTemplatesConfig(List<TemplateApplyConfig> templatesConfig) {
		this.templatesConfig = templatesConfig;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public List<? extends TemplateProjectApplyConfigItem> getItems() {
		return templatesConfig;
	}
}
