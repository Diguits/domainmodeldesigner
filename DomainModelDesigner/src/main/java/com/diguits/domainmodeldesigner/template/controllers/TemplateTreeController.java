package com.diguits.domainmodeldesigner.template.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldesigner.editors.controllers.TemplateDefEditorController;
import com.diguits.domainmodeldesigner.services.TemplateProjectApplyConfigClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.services.tasks.TemplateListCompileTask;
import com.diguits.domainmodeldesigner.services.tasks.TemplateListGenerateCodeTask;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectItemDefModel;
import com.diguits.domainmodeldesigner.template.views.TemplateTreeView;
import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.problem.controllers.ProblemsController;
import com.diguits.javafx.problem.controllers.ProblemItem.Kind;
import com.diguits.javafx.container.controllers.TreeContainerController;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.diguits.templateengine.TemplateEngine;
import com.diguits.templateengine.contract.Diagnostic;
import com.diguits.templateengine.contract.Diagnostic.Operation;
import com.google.inject.Inject;

import javafx.collections.ObservableList;

public class TemplateTreeController extends TreeContainerController<TemplateTreeView, TemplateProjectItemDefModel> {

	@Inject
	private TemplateProjectClientService templateClientService;

	@Inject
	TemplateProjectApplyConfigClientService templateApplyConfigClientService;

	@Inject
	private TemplateTreeBuilder treeBuilder;

	@Inject
	private TemplateApplyController templateApplyController;

	@Inject
	private TemplateProjectApplyController templateProjectApplyController;

	@Inject
	private TemplateListOperationController templateListOperationController;

	@Inject
	public TemplateTreeController(TemplateTreeView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		loadTemplateProjects();
	}

	@Override
	protected ContainerPos getDefaultPosition() {
		return ContainerPos.RIGTH;
	}

	public void loadTemplateProjects() {
		ObservableList<TemplateProjectDefModel> projects = templateClientService.loadProjects();
		fill(treeBuilder, projects);
	}

	public void saveTemplateProjects() {
		templateClientService.saveTemplateProjects();
	}

	public void addProject() {
		templateClientService.addProject();
	}

	public void removeProject() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof TemplateProjectDefModel) {
			TemplateProjectDefModel project = (TemplateProjectDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("Template Project", project.getName()))
				templateClientService.removeProject(project);
		}
	}

	public void compileProject() {
		TemplateProjectDefModel project = getSelectedProject();
		if (project != null) {
			TemplateListCompileTask task = templateClientService.getCompileTemplateTask(project);
			templateListOperationController.startModal(task, resources.getString("compiling_template"));
		}
	}

	public void generateProjectCode() {
		TemplateProjectDefModel project = getSelectedProject();
		if (project != null) {
			TemplateListGenerateCodeTask task = templateClientService.getGenerateCodeTemplateTask(project);
			templateListOperationController.startModal(task, resources.getString("generating_code_template"));
		}
	}

	private TemplateProjectDefModel getSelectedProject() {
		TemplateProjectDefModel project = null;
		if (getGlobalSelectedModel() != null) {
			if (getGlobalSelectedModel() instanceof TemplateProjectDefModel)
				project = (TemplateProjectDefModel) getGlobalSelectedModel();
			else if (getGlobalSelectedModel() instanceof TemplateGroupDefModel)
				project = ((TemplateGroupDefModel) getGlobalSelectedModel()).getProjectOwner();
			else if (getGlobalSelectedModel() instanceof TemplateDefModel)
				project = ((TemplateDefModel) getGlobalSelectedModel()).getProject();
		}
		return project;
	}

	public void applyProject() {
		templateProjectApplyController.applyTemplateProject();
	}

	public void addGroup() {
		if (getGlobalSelectedModel() != null) {
			if (getGlobalSelectedModel() instanceof TemplateProjectDefModel)
				templateClientService.addGroup((TemplateProjectDefModel) getGlobalSelectedModel());
			else if (getGlobalSelectedModel() instanceof TemplateGroupDefModel)
				templateClientService.addGroup((TemplateGroupDefModel) getGlobalSelectedModel());
			else if (getGlobalSelectedModel() instanceof TemplateDefModel)
				templateClientService.addGroup((TemplateDefModel) getGlobalSelectedModel());
		}
	}

	public void removeGroup() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof TemplateGroupDefModel) {
			TemplateGroupDefModel group = (TemplateGroupDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("Template Project", group.getName()))
				templateClientService.removeGroup(group);
		}
	}

	public void addTemplate() {
		if (getGlobalSelectedModel() != null) {
			if (getGlobalSelectedModel() instanceof TemplateGroupDefModel)
				templateClientService.addTemplate((TemplateGroupDefModel) getGlobalSelectedModel());
			else if (getGlobalSelectedModel() instanceof TemplateDefModel)
				templateClientService.addTemplate((TemplateDefModel) getGlobalSelectedModel());
		}
	}

	public void removeTemplate() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof TemplateDefModel) {
			TemplateDefModel template = (TemplateDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("Template", template.getName()))
				templateClientService.removeTemplate(template);
		}
	}

	public void generateTemplateCode() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof TemplateDefModel) {
			TemplateDefModel template = (TemplateDefModel) getGlobalSelectedModel();
			TemplateListGenerateCodeTask task = templateClientService.getGenerateCodeTemplateTask(template);
			templateListOperationController.startModal(task, resources.getString("generating_code_template"));
		}
	}

	public static void addToProblems(ProblemsController problemsController, List<Diagnostic> diagnostics) {
		if (diagnostics != null)
			for (Diagnostic diagnostic : diagnostics) {
				String fieldName = "";
				if (diagnostic.getContext() != null) {
					if (diagnostic.getContext().equals(TemplateEngine.TEMPLATECODE)) {
						fieldName = diagnostic.getOperation() == Operation.GENERATING
								? TemplateDefEditorController.SOURCECODE : TemplateDefEditorController.GENERATEDCODE;
						/*
						 * } else if
						 * (diagnostic.getContext().equals(TemplateEngine.
						 * TEMPLATEOUTPUTPATH)) { fieldName =
						 * TemplateDefEditorController.OUTPUTPATH;
						 */} else if (diagnostic.getContext().equals(TemplateEngine.TEMPLATEFILENAME)) {
						fieldName = TemplateDefEditorController.FILENAME;
						/*
						 * } else if
						 * (diagnostic.getContext().equals(TemplateEngine.
						 * TEMPLATECONDITION)) { fieldName =
						 * TemplateDefEditorController.CONDITION;
						 */}
				}
				Position podition = new Position(diagnostic.getModelId(), diagnostic.getModelClass(), fieldName,
						diagnostic.getLineNumber(), diagnostic.getColumnNumber());

				problemsController
						.addProblem(diagnostic.getKind() == com.diguits.templateengine.contract.Diagnostic.Kind.ERROR
								? Kind.ERROR : Kind.WARN, diagnostic.getMessage(), podition);
			}
	}

	public void compileTemplate() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof TemplateDefModel) {
			TemplateDefModel template = (TemplateDefModel) getGlobalSelectedModel();
			TemplateListCompileTask task = templateClientService.getCompileTemplateTask(template);
			templateListOperationController.startModal(task, resources.getString("compiling_template"));
		}
	}

	public void applyTemplate() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof TemplateDefModel) {
			templateApplyController.applyTemplateModal((TemplateDefModel) getGlobalSelectedModel());
		}
	}

	@Override
	public boolean isTreeItemContainer(TemplateProjectItemDefModel item) {
		return item.getClass() == TemplateProjectItemDefModel.class;
	}

	@Override
	protected NamedModelBase findModel(UUID modelId) {
		return templateClientService.find(modelId);
	}

	@Override
	protected List<Class<?>> getModelClasses() {
		List<Class<?>> result = new ArrayList<Class<?>>();
		result.add(TemplateProjectDefModel.class);
		result.add(TemplateGroupDefModel.class);
		result.add(TemplateDefModel.class);
		result.add(TemplateProjectDef.class);
		result.add(TemplateGroupDef.class);
		result.add(TemplateDef.class);
		return result;
	}
}
