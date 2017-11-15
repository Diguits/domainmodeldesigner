package com.diguits.templateengine.contract.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TemplateApplyConfig extends TemplateProjectApplyConfigItem{
	private UUID templateId;
	private boolean inMemory;

	private boolean deleteOutputDirectoryFiles;
	private String deleteOutputFilters;
	private String conditionExpression;

	private String rootOutputDir;
	private String outputPathExpression;
	private String outputFilenameExpression;
	private boolean waitForBefore;
	private List<UUID> modelIds;

	List<TemplateParamValue> paramValues;


	public TemplateApplyConfig() {
		super();
		paramValues = new ArrayList<TemplateParamValue>();
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

	public boolean getDeleteOutputDirectoryFiles() {
		return deleteOutputDirectoryFiles;
	}

	public void setDeleteOutputDirectoryFiles(boolean deleteOutputDirectoryFiles) {
		this.deleteOutputDirectoryFiles = deleteOutputDirectoryFiles;
	}

	public String getDeleteOutputFilters() {
		return deleteOutputFilters;
	}

	public void setDeleteOutputFilters(String deleteOutputFilters) {
		this.deleteOutputFilters = deleteOutputFilters;
	}

	public List<TemplateParamValue> getParamValues() {
		return paramValues;
	}

	public void setParamValues(List<TemplateParamValue> paramValues) {
		this.paramValues = paramValues;
	}

	public TemplateProjectApplyConfig getProjectOwner(){
		return (TemplateProjectApplyConfig) owner;
	}

	public void setProjectOwner(TemplateProjectApplyConfig owner){
		this.owner = owner;
	}

	@Override
	public List<? extends TemplateProjectApplyConfigItem> getItems() {
		return null;
	}
}
