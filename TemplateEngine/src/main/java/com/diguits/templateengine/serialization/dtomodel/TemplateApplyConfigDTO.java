package com.diguits.templateengine.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TemplateApplyConfigDTO extends TemplateProjectApplyConfigItemDTO {
	private UUID templateId;
	private boolean inMemory;
	private String conditionExpression;
	private String rootOutputDir;
	private String outputPathExpression;
	private String outputFilenameExpression;
	private boolean waitForBefore;
	private List<UUID> modelIds;

	List<TemplateParamValueDTO> paramValues;

	public TemplateApplyConfigDTO() {
		super();
		paramValues = new ArrayList<TemplateParamValueDTO>();
		modelIds = new ArrayList<UUID>();
	}

	public UUID getTemplateId() {
		return templateId;
	}

	public void setTemplateId(UUID templateId) {
		this.templateId = templateId;
	}

	public boolean getInMemory() {
		return inMemory;
	}

	public void setInMemory(boolean inMemory) {
		this.inMemory = inMemory;
	}

	public String getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionExpression(String conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	public String getRootOutputDir() {
		return rootOutputDir;
	}

	public void setRootOutputDir(String rootOutputDir) {
		this.rootOutputDir = rootOutputDir;
	}

	public String getOutputPathExpression() {
		return outputPathExpression;
	}

	public void setOutputPathExpression(String outputPathExpression) {
		this.outputPathExpression = outputPathExpression;
	}

	public String getOutputFilenameExpression() {
		return outputFilenameExpression;
	}

	public void setOutputFilenameExpression(String outputFilenameExpression) {
		this.outputFilenameExpression = outputFilenameExpression;
	}

	public boolean getWaitForBefore() {
		return waitForBefore;
	}

	public void setWaitForBefore(boolean waitForBefore) {
		this.waitForBefore = waitForBefore;
	}

	public List<UUID> getModelIds() {
		return modelIds;
	}

	public void setModelIds(List<UUID> modelIds) {
		this.modelIds = modelIds;
	}

	public List<TemplateParamValueDTO> getParamValues() {
		return paramValues;
	}

	public void setParamValues(List<TemplateParamValueDTO> paramValues) {
		this.paramValues = paramValues;
	}
}
