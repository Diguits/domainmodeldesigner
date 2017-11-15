package com.diguits.domainmodeldesigner.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.services.tasks.TemplateApplyTask;
import com.diguits.domainmodeldesigner.services.tasks.TemplateListCompileTask;
import com.diguits.domainmodeldesigner.services.tasks.TemplateListGenerateCodeTask;
import com.diguits.domainmodeldesigner.template.modelmappers.ITemplateProjectModelMapper;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectItemDefModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers.TemplateApplyConfigToTemplateApplyConfigModelMapper;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.diguits.templatedefinition.services.ITemplateProjectService;
import com.diguits.templateengine.contract.Diagnostic;
import com.diguits.templateengine.contract.DiagnosticCollector;
import com.diguits.templateengine.contract.ITemplateEngineService;
import com.diguits.templateengine.contract.ParamValue;
import com.diguits.templateengine.contract.TemplateApplyOutput;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.google.inject.Inject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TemplateProjectClientService extends ClientServiceBase {
	private ListProperty<TemplateProjectDefModel> templateProjects;

	private BooleanProperty dirty;

	@Inject
	ITemplateProjectService projectService;

	@Inject
	ITemplateEngineService templateEngineService;

	@Inject
	ITemplateProjectModelMapper projectMapper;

	@Inject
	TemplateApplyConfigToTemplateApplyConfigModelMapper templateApplyConfigMapper;

	@Inject
	DomainModelClientService domainModelClientService;

	public ObservableList<TemplateProjectDefModel> loadProjects() {
		String absolutePath = getRootDir();
		if (absolutePath != null && !absolutePath.isEmpty()) {
			Iterable<TemplateProjectDef> projects = projectService.loadProjects(absolutePath);
			ObservableList<TemplateProjectDefModel> result = getTemplateProjects();
			for (TemplateProjectDef templateProjectDef : projects) {
				result.add(projectMapper.map(templateProjectDef));
			}
			return result;
		}
		return null;
	}

	public TemplateProjectDefModel addTemplateProject() {
		TemplateProjectDefModel templateProjectDefModel = new TemplateProjectDefModel();
		templateProjectDefModel.setName("Project1");
		templateProjects.add(templateProjectDefModel);
		return templateProjectDefModel;
	}

	public void saveTemplateProjects() {
		String absolutePath = getRootDir();
		if (absolutePath != null && !absolutePath.isEmpty()) {
			for (TemplateProjectDefModel templateProjectDefModel : getTemplateProjects()) {
				projectService.saveProject(absolutePath, projectMapper.mapBack(templateProjectDefModel));
			}
		}
	}

	public void saveTemplateProject(TemplateProjectDefModel project) {
		String absolutePath = getRootDir();
		if (absolutePath != null && !absolutePath.isEmpty()) {
			projectService.saveProject(absolutePath, projectMapper.mapBack(project));
		}
	}

	// Properties

	public ObservableList<TemplateProjectDefModel> getTemplateProjects() {
		return templateProjectsProperty().get();
	}

	public ListProperty<TemplateProjectDefModel> templateProjectsProperty() {
		if (templateProjects == null) {
			templateProjects = new SimpleListProperty<TemplateProjectDefModel>(this, "templateProjects", null);
			templateProjects.set(FXCollections.observableArrayList());
		}
		return templateProjects;
	}

	public void addProject() {
		TemplateProjectDefModel project = new TemplateProjectDefModel();
		project.setName(getNewNameForProject());
		templateProjects.add(project);
	}

	private String getNewNameForProject() {
		return "Project 1";
	}

	public void removeProject(TemplateProjectDefModel project) {
		if (project != null) {
			int i = project.getGroups().size() - 1;
			while (i >= 0) {
				TemplateGroupDefModel group = project.getGroups().get(i);
				innerRemoveGroup(group, project);
				i--;
			}
			templateProjects.remove(project);
		}
	}

	public void addGroup(TemplateDefModel template) {
		addGroup(template.getProject());
	}

	public void addGroup(TemplateGroupDefModel group) {
		addGroup(group.getProjectOwner());
	}

	public void addGroup(TemplateProjectDefModel project) {
		TemplateGroupDefModel group = new TemplateGroupDefModel();
		group.setProjectOwner(project);
		group.setName(getNewNameForGroup());
		project.getGroups().add(group);
	}

	private String getNewNameForGroup() {
		return "Group 1";
	}

	public void removeGroup(TemplateGroupDefModel group, TemplateProjectDefModel project) {
		if (group != null && project != null) {
			innerRemoveGroup(group, project);
		}
	}

	public void removeGroup(TemplateGroupDefModel group) {
		if (group != null) {
			removeGroup(group, group.getProjectOwner());
		}
	}

	private void innerRemoveGroup(TemplateGroupDefModel group, TemplateProjectDefModel project) {
		if (group != null) {
			int i = group.getTemplates().size() - 1;
			while (i >= 0) {
				TemplateDefModel template = group.getTemplates().get(i);
				innerRemovegetTemplate(template, group);
				i--;
			}
			project.getGroups().remove(group);
		}
	}

	public void addTemplate(TemplateDefModel template) {
		addTemplate(template.getGroupOwner());
	}

	public void addTemplate(TemplateGroupDefModel group) {
		TemplateDefModel template = new TemplateDefModel();
		template.setGroupOwner(group);
		template.setName(getNewNameForTemplate());
		group.getTemplates().add(template);
	}

	private String getNewNameForTemplate() {
		return "Template 1";
	}

	public void removeTemplate(TemplateDefModel template) {
		if (template != null) {
			removeTemplate(template, template.getGroupOwner());
		}
	}

	public void removeTemplate(TemplateDefModel template, TemplateGroupDefModel group) {
		if (template != null && group != null) {
			innerRemovegetTemplate(template, group);
		}
	}

	private void innerRemovegetTemplate(TemplateDefModel template, TemplateGroupDefModel group) {
		group.getTemplates().remove(template);
	}

	public String loadTemplateGenerateCode(TemplateDefModel template) {
		String absolutePath = getRootDir();
		if (absolutePath != null && !absolutePath.isEmpty()) {
			TemplateProjectDefModel project = template.getProject();
			if (project != null)
				return projectService.loadTemplateCode(absolutePath, project.getId(), template.getId());
		}
		return null;
	}

	public TemplateDefModel findTemplate(UUID templateId) {
		for (TemplateProjectDefModel project : templateProjects) {
			for (TemplateGroupDefModel group : project.getGroups()) {
				for (TemplateDefModel template : group.getTemplates()) {
					if (template.getId().equals(templateId)) {
						return template;
					}
				}
			}
		}
		return null;
	}

	public boolean getDirty() {
		if (dirty != null)
			return dirty.get();
		return false;
	}

	public void setDirty(boolean dirty) {
		if (this.dirty != null || dirty != false)
			dirtyProperty().set(dirty);
	}

	public BooleanProperty dirtyProperty() {
		if (dirty == null)
			dirty = new SimpleBooleanProperty(this, "dirty", false);
		return dirty;
	}

	public List<Diagnostic> genrateCode(TemplateDefModel template) {
		if (template != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				TemplateProjectDefModel project = template.getProject();
				saveTemplateProject(project);
				DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
				templateEngineService.generateCode(absolutePath, project.getId(), template.getId(), template.getName(),
						template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]),
						diagnosticCollector);
				return diagnosticCollector.getDiagnostics();
			}
		}
		return null;
	}

	public List<Diagnostic> compile(TemplateDefModel template) {
		if (template != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				TemplateProjectDefModel project = template.getProject();
				saveTemplateProject(project);
				DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
				templateEngineService.compileCode(absolutePath, project.getId(), template.getId(), template.getName(),
						template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]),
						diagnosticCollector);
				return diagnosticCollector.getDiagnostics();
			}
		}
		return null;
	}

	public static class ApplyResult {
		List<Diagnostic> diagnostics;
		List<TemplateApplyOutput> applyOutputs;

		public ApplyResult() {
			super();
			applyOutputs = new ArrayList<TemplateApplyOutput>();
		}

		public List<Diagnostic> getDiagnostics() {
			return diagnostics;
		}

		public void setDiagnostics(List<Diagnostic> diagnostics) {
			this.diagnostics = diagnostics;
		}

		public List<TemplateApplyOutput> getApplyOutputs() {
			return applyOutputs;
		}
	}

	public static List<ParamValue> getParamValues(TemplateDefModel template, TemplateApplyConfig config) {
		List<ParamValue> paramValues = new ArrayList<ParamValue>();
		for (TemplateParameterDefModel param : template.getParameters()) {
			Object value = null;
			int i = 0;
			while (i < config.getParamValues().size()
					&& config.getParamValues().get(i).getTemplateParameter() != param.getId())
				i++;
			if (i < config.getParamValues().size())
				value = config.getParamValues().get(i).getValue();
			paramValues.add(new ParamValue(param.getName(), value));
		}
		return paramValues;
	}

	public TemplateApplyTask getApplyTemplateTask(TemplateApplyConfigModel configModel) {
		TemplateDefModel template = configModel.getTemplate();
		List<UUID> modelIds = configModel.getModelIds();
		if (template != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				TemplateProjectDefModel project = template.getProject();
				saveTemplateProject(project);
				List<List<? extends BaseDef>> models = getModelDefs(template, modelIds);
				String outputRootDir = configModel.getOwner() != null
						? configModel.getProjectOwner().getOutputPath()
						: "";
				return new TemplateApplyTask(template, models, absolutePath, outputRootDir,
						templateApplyConfigMapper.mapBack(configModel, null), templateEngineService);
			}
		}
		return null;
	}

	public TemplateListGenerateCodeTask getGenerateCodeTemplateTask(TemplateDefModel template) {
		if (template != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				TemplateProjectDefModel project = template.getProject();
				saveTemplateProject(project);
				ArrayList<TemplateDefModel> templates = new ArrayList<TemplateDefModel>();
				templates.add(template);
				return new TemplateListGenerateCodeTask(templates, absolutePath, templateEngineService);
			}
		}
		return null;
	}

	public TemplateListCompileTask getCompileTemplateTask(TemplateDefModel template) {
		if (template != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				TemplateProjectDefModel project = template.getProject();
				saveTemplateProject(project);
				ArrayList<TemplateDefModel> templates = new ArrayList<TemplateDefModel>();
				templates.add(template);
				return new TemplateListCompileTask(templates, absolutePath, templateEngineService);
			}
		}
		return null;
	}

	public TemplateListGenerateCodeTask getGenerateCodeTemplateTask(TemplateProjectDefModel project) {
		if (project != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				saveTemplateProject(project);
				return new TemplateListGenerateCodeTask(project.getTemplates(), absolutePath, templateEngineService);
			}
		}
		return null;
	}

	public TemplateListCompileTask getCompileTemplateTask(TemplateProjectDefModel project) {
		if (project != null) {
			String absolutePath = getRootDir();
			if (absolutePath != null && !absolutePath.isEmpty()) {
				saveTemplateProject(project);
				return new TemplateListCompileTask(project.getTemplates(), absolutePath, templateEngineService);
			}
		}
		return null;
	}

	private List<List<? extends BaseDef>> getModelDefs(TemplateDefModel template, List<UUID> modelsId) {
		if (modelsId == null || modelsId.size() == 0 || modelsId.stream().anyMatch(id -> id == null)
				|| modelsId.size() < template.getClassNamesToApplyOver().size()) {
			return getModelDefsForTemplate(template);
		} else {
			BaseDef[] modelDef = getDefFromId(modelsId, template.getClassNamesToApplyOver());
			List<List<? extends BaseDef>> result = new ArrayList<List<? extends BaseDef>>();
			for (int i = 0; i < modelDef.length; i++) {
				List<BaseDef> models = new ArrayList<BaseDef>();
				models.add(modelDef[i]);
				result.add(models);
			}
			return result;
		}
	}

	public List<List<? extends BaseDef>> getModelDefsForTemplate(TemplateDefModel template) {
		if (template != null) {
			return domainModelClientService.getModelDefsByClass(
					template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]));
		}
		return null;
	}

	public TemplateProjectItemDefModel find(UUID modelId) {
		for (TemplateProjectDefModel project : templateProjects) {
			if (project.getId() == modelId)
				return project;
			for (TemplateGroupDefModel group : project.getGroups()) {
				if (group.getId() == modelId)
					return group;
				for (TemplateDefModel template : group.getTemplates()) {
					if (template.getId() == modelId)
						return template;
				}
			}
		}
		return null;
	}

	public List<String> getModelClassesName() {
		return domainModelClientService.getModelClassesName();
	}

	public List<List<? extends BaseDefModel>> getModelsForTemplate(TemplateDefModel template) {
		if (template != null) {
			return domainModelClientService.getModelsByClass(
					template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]));
		}
		return null;
	}

	public BaseDef[] getDefFromId(List<UUID> modelIds, List<String> modelClassNames) {
		if (modelIds != null && modelClassNames != null) {
			return domainModelClientService.getDefFromId(modelIds.toArray(new UUID[modelIds.size()]),
					modelClassNames.toArray(new String[modelClassNames.size()]));
		}
		return null;
	}
}
