package com.diguits.templateengine.contract;

import java.util.UUID;

public class TemplateApplyOutput {
	private UUID templateId;
	private String templateName;

	private UUID[] modelsId;
	private String[] modelsName;
	private boolean applied;

	private String output;
	private String condition;
	private String outputFullPath;

	public TemplateApplyOutput() {
		super();
	}

	public UUID getTemplateId() {
		return templateId;
	}

	public void setTemplateId(UUID templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public UUID[] getModelsId() {
		return modelsId;
	}

	public void setModelsId(UUID[] modelsId) {
		this.modelsId = modelsId;
	}

	public String[] getModelsName() {
		return modelsName;
	}

	public void setModelsName(String[] modelsName) {
		this.modelsName = modelsName;
	}

	public boolean isApplied() {
		return applied;
	}

	public void setApplied(boolean applied) {
		this.applied = applied;
	}


	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOutputFullPath() {
		return outputFullPath;
	}

	public void setOutputFullPath(String outputFullPath) {
		this.outputFullPath = outputFullPath;
	}
}
