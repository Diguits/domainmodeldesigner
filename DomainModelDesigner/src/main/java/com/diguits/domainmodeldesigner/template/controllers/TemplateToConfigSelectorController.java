package com.diguits.domainmodeldesigner.template.controllers;

import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectItemDefModel;
import com.diguits.domainmodeldesigner.template.views.TemplateToConfigSelectorView;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ModelViewControllerBase;
import com.google.inject.Inject;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class TemplateToConfigSelectorController
		extends ModelViewControllerBase<TemplateToConfigSelectorView, ObservableList<TemplateDefModel>> {

	@Inject
	TemplateProjectClientService templateProjectClientService;

	TemplateDefModel selectedTemplete;
	TemplateDefModel selectedTempleteToAdd;

	@Inject
	public TemplateToConfigSelectorController(TemplateToConfigSelectorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			getView().getBtnRight().setOnAction(e -> addTemplate());
			getView().getBtnLeft().setOnAction(e -> deleteTemplate());
			getView().getBtnUp().setOnAction(e -> upTemplate());
			getView().getBtnDown().setOnAction(e -> downTemplate());
		}
	}

	TemplateProjectItemDefModel getSelectedTemplateProjectItemToAdd() {
		if (getView() != null) {
			TreeItem<TemplateProjectItemDefModel> selectedItem = getView().getTreFrom().getSelectionModel()
					.getSelectedItem();
			if (selectedItem != null)
				return selectedItem.getValue();
		}
		return null;
	}

	TemplateDefModel getSelectedTemplate() {
		if (getView() != null) {
			return getView().getTblTo().getSelectionModel().getSelectedItem();
		}
		return null;
	}

	private void addTemplate() {
		TemplateProjectItemDefModel selectedTemplateProjectItem = getSelectedTemplateProjectItemToAdd();
		if (selectedTemplateProjectItem != null && selectedTemplateProjectItem instanceof TemplateProjectDefModel) {
			TemplateProjectDefModel templateProject = (TemplateProjectDefModel) selectedTemplateProjectItem;
			for (TemplateGroupDefModel templateGroup : templateProject.getGroups()) {
				for (TemplateDefModel template : templateGroup.getTemplates()) {
					getModel().add(template);
				}
			}
		}
		if (selectedTemplateProjectItem != null && selectedTemplateProjectItem instanceof TemplateGroupDefModel) {
			TemplateGroupDefModel templateGroup = (TemplateGroupDefModel) selectedTemplateProjectItem;
			for (TemplateDefModel template : templateGroup.getTemplates()) {
				getModel().add(template);
			}
		}
		if (selectedTemplateProjectItem != null && selectedTemplateProjectItem instanceof TemplateDefModel) {
			getModel().add((TemplateDefModel) selectedTemplateProjectItem);
		}
	}

	protected void deleteTemplate() {
		TemplateDefModel selectedTemplate = getSelectedTemplate();
		if (selectedTemplate != null)
			getModel().remove(selectedTemplate);
	}

	private void upTemplate() {
		up(getView().getTblTo(), getModel());
	}

	private void downTemplate() {
		down(getView().getTblTo(), getModel());
	}

	public boolean show(ObservableList<TemplateDefModel> templates) {
		setModel(templates);
		getView().setTemplateProjects(templateProjectClientService.getTemplateProjects());
		return DialogHelper.showOKCancelDialog(this, resources.getString("select_templates"));
	}
}
