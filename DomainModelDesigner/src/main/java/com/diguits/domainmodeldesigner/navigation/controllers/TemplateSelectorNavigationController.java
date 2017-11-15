package com.diguits.domainmodeldesigner.navigation.controllers;

import com.diguits.domainmodeldesigner.navigation.view.EntitySelectorNavigationView;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.template.controllers.TemplateTreeController;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.navigation.controllers.ModelSelectorNavigationController;
import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class TemplateSelectorNavigationController extends ModelSelectorNavigationController<EntitySelectorNavigationView>{


	@Inject
	TemplateProjectClientService templateProjectClientService;

	@Inject
	TemplateTreeController templateTreeController;

	@Inject
	public TemplateSelectorNavigationController(EntitySelectorNavigationView view) {
		super(view);
	}

	@Override
	protected ObservableList<NamedModelBase> getModels() {
		ObservableList<NamedModelBase> result = FXCollections.observableArrayList();
		result = FXCollections.observableArrayList();
		ObservableList<TemplateProjectDefModel> projects = templateProjectClientService.getTemplateProjects();
		if(projects!=null){
			for (TemplateProjectDefModel project : projects) {
				ObservableList<TemplateGroupDefModel> groups = project.getGroups();
				for (TemplateGroupDefModel group : groups) {
					result.addAll(group.getTemplates());
				}
			}
		}
		return result;
	}

	@Override
	protected TreeItem<?> getTreeItem(NamedModelBase selectedModel) {
		return templateTreeController.findTreeItemByModel((TemplateDefModel) selectedModel);
	}

}
