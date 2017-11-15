package com.diguits.domainmodeldesigner.services;

import java.io.File;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers.ITemplateProjectApplyConfigModelMapper;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.templateengine.contract.ITemplateProjectApplyConfigService;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;
import com.google.inject.Inject;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TemplateProjectApplyConfigClientService extends ClientServiceBase {
	private ListProperty<TemplateProjectApplyConfigModel> configs;

	@Inject
	ITemplateProjectApplyConfigService projectApplyConfigService;

	@Inject
	ITemplateProjectApplyConfigModelMapper projectApplyConfigMapper;

	@Inject
	TemplateProjectClientService projectClientService;

	public ObservableList<TemplateProjectApplyConfigModel> loadConfigs() {
		String absolutePath = getRootDir();
		if (absolutePath != null && !absolutePath.isEmpty()) {
			Iterable<TemplateProjectApplyConfig> configs = projectApplyConfigService.loadConfig(absolutePath);
			ObservableList<TemplateProjectApplyConfigModel> result = getConfigs();
			result.clear();
			for (TemplateProjectApplyConfig config : configs) {
				result.add(projectApplyConfigMapper.map(config));
			}
			return result;
		}
		return null;
	}

	public void saveConfigs() {
		String absolutePath = getRootDir();
		if (absolutePath != null && !absolutePath.isEmpty()) {
			for (TemplateProjectApplyConfigModel config : getConfigs()) {
				saveConfig(absolutePath, config);
			}
		}
	}

	private void saveConfig(String absolutePath, TemplateProjectApplyConfigModel config) {
		projectApplyConfigService.saveConfig(absolutePath, projectApplyConfigMapper.mapBack(config));
	}

	public void saveConfig(TemplateProjectApplyConfigModel config) {
		if (config != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				saveConfig(absolutePath, config);
			}
		}
	}

	public ObservableList<TemplateProjectApplyConfigModel> getConfigs() {
		return configsProperty().get();
	}

	public ListProperty<TemplateProjectApplyConfigModel> configsProperty() {
		if (configs == null) {
			configs = new SimpleListProperty<TemplateProjectApplyConfigModel>(this, "configs", null);
			configs.set(FXCollections.observableArrayList());
		}
		return configs;
	}

	public String getTemplateOutputPath(TemplateApplyConfigModel templateConfig) {
		if (templateConfig != null) {
			String projectOutputPath = "";
			TemplateProjectApplyConfigModel projectConfig = templateConfig.getProjectOwner();
			if (projectConfig != null)
				projectOutputPath = projectConfig.getOutputPath();
			File file = null;
			if (templateConfig != null && templateConfig.getOutputPathExpression() != null
					&& !templateConfig.getOutputPathExpression().isEmpty()) {
				file = new File(templateConfig.getOutputPathExpression());
				if (!file.isAbsolute())
					file = new File(projectOutputPath, templateConfig.getOutputPathExpression());
				if (!file.exists())
					file = null;
			}
			if (file == null)
				file = new File(projectOutputPath);
			return file.getAbsolutePath();
		}
		return "";
	}

	public String getRelativeTemplateOutputPath(TemplateApplyConfigModel templateApplyConfig, String path) {
		TemplateProjectApplyConfigModel projectConfig = templateApplyConfig.getProjectOwner();
		String projectOutputPath = null;
		if (projectConfig != null)
			projectOutputPath = projectConfig.getOutputPath();
		if(projectOutputPath==null || projectOutputPath.isEmpty())
			return path;
		else
			return new File(projectOutputPath).toURI().relativize(new File(path).toURI()).getPath();
	}

	public void setDefault(TemplateProjectApplyConfigModel templateProjectApplyConfig) {
		if (!templateProjectApplyConfig.getIsDefault()) {
			for (TemplateProjectApplyConfigModel config : getConfigs()) {
				if (config != templateProjectApplyConfig)
					config.setIsDefault(false);
			}
			templateProjectApplyConfig.setIsDefault(true);
			saveConfigs();
		}
	}

	public void removeTemplateProjectApplyConfig(TemplateProjectApplyConfigModel templateProjectApplyConfig) {
		if (templateProjectApplyConfig != null) {
			int i = templateProjectApplyConfig.getTemplatesConfig().size() - 1;
			while (i > 0) {
				templateProjectApplyConfig.getTemplatesConfig().remove(i);
				i--;
			}
			configs.remove(templateProjectApplyConfig);
		}

	}

	public TemplateProjectApplyConfigModel addTemplateProjectApplyCongig() {
		TemplateProjectApplyConfigModel project = new TemplateProjectApplyConfigModel();
		project.setName(getNewNameForProject());
		configs.add(project);
		return project;
	}

	private String getNewNameForProject() {
		return "Project Apply 1";
	}

	public TemplateApplyConfigModel addTemplate(TemplateProjectApplyConfigModel project, TemplateDefModel template) {
		TemplateApplyConfigModel templateApplayConfig = createTemplateApplyConfigFromTemplate(template);
		templateApplayConfig.setProjectOwner(project);
		project.getTemplatesConfig().add(templateApplayConfig);
		return templateApplayConfig;
	}

	public TemplateApplyConfigModel getDefaultTemplateApplyConfigForTemplate(TemplateDefModel template) {
		return createTemplateApplyConfigFromTemplate(template);
	}

	private TemplateApplyConfigModel createTemplateApplyConfigFromTemplate(TemplateDefModel template) {
		TemplateApplyConfigModel result = new TemplateApplyConfigModel();
		result.setName(template.getName()+" Config");
		result.setOutputFilenameExpression(template.getOutputFileNameExpression());
		result.setTemplate(template);
		return result;
	}


	/*
	 * public String getTemplateOutputPath(TemplateDefModel template) { if
	 * (template != null) { String projectOutputPath = "";
	 * TemplateProjectDefModel project =
	 * findProjectByTemplateId(template.getId()); if (project != null)
	 * projectOutputPath = project.getOutputPath(); File file = null; if
	 * (template != null && template.getOutputPathExpression() != null &&
	 * !template.getOutputPathExpression().isEmpty()) { file = new
	 * File(template.getOutputPathExpression()); if (!file.isAbsolute()) file =
	 * new File(projectOutputPath, template.getOutputPathExpression()); if
	 * (!file.exists()) file = null; } if (file == null) file = new
	 * File(projectOutputPath); return file.getAbsolutePath(); } return ""; }
	 */

	/*
	 * public ObservableList<TemplateProjectApplyConfigModel>
	 * getConfigsForProjectTemplate(UUID templateProjectId) {
	 * ensureLoadConfig(); ObservableList<TemplateProjectApplyConfigModel>
	 * result = FXCollections.observableArrayList();
	 * ObservableList<TemplateProjectApplyConfigModel> configs = getConfigs();
	 * configs.stream().filter(c ->
	 * c.getTemplateProjectId().equals(templateProjectId)).forEach(c ->
	 * result.add(c)); return result; }
	 *
	 * public ObservableList<TemplateProjectApplyConfigModel>
	 * getConfigsForProjectTemplateOrDefault( TemplateProjectDefModel
	 * templateProject) { ObservableList<TemplateProjectApplyConfigModel> result
	 * = getConfigsForProjectTemplate(templateProject.getId()); if
	 * (result.size() == 0) { TemplateProjectApplyConfigModel defaultConfig =
	 * createTemplateProjectApplyConfigFromTemplate(templateProject);
	 * if(defaultConfig != null){ getConfigs().add(defaultConfig);
	 * result.add(defaultConfig); saveConfig(defaultConfig); return result; } }
	 * return result; }
	 *
	 * public TemplateProjectApplyConfigModel
	 * createTemplateProjectApplyConfigFromTemplate( TemplateProjectDefModel
	 * templateProject) { DomainModelDefModel domainModelDef =
	 * domainModelClientService.getDomainModelDef(); if (domainModelDef != null) {
	 * TemplateProjectApplyConfigModel result = new
	 * TemplateProjectApplyConfigModel(); result.setName("Default");
	 * result.setIsDefault(true);
	 * result.setOutputPath(templateProject.getOutputPath());
	 * result.setModelId(domainModelDef.getId()); for (TemplateGroupDefModel group :
	 * templateProject.getGroups()) { for (TemplateDefModel template :
	 * group.getTemplates()) {
	 * result.getTemplatesConfig().add(getDefaultTemplateApplyConfigForTemplate(
	 * template)); } } return result; } return null; }
	 */
}
