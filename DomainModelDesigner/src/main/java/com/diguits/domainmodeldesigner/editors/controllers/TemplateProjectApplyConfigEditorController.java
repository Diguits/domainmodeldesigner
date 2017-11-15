package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.TemplateProjectApplyConfigEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectApplyConfigClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.template.controllers.TemplateProjectApplyingController;
import com.diguits.domainmodeldesigner.template.controllers.TemplateToConfigSelectorController;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.controls.AlertHelper;
import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TemplateProjectApplyConfigEditorController
		extends TemplateProjectApplyConfigItemEditorController<TemplateProjectApplyConfigEditorView, TemplateProjectApplyConfigModel> {

	@Inject
	TemplateProjectClientService templateClientService;

	@Inject
	TemplateProjectApplyConfigClientService templateApplyConfigClientService;

	@Inject
	private TemplateApplyConfigEditorController templateApplyController;

	@Inject
	TemplateProjectApplyingController templateProjectApplyingController;

	@Inject
	DomainModelClientService domainModelClienteService;

	@Inject
	TemplateToConfigSelectorController templateSelector;

	@Inject
	protected AlertHelper alertHelper;

	@Inject
	public TemplateProjectApplyConfigEditorController(TemplateProjectApplyConfigEditorView view) {
		super(view);
	}

	public void initialize() {
		super.initialize();

		modelProperty().addListener((v, o, n) -> {
			if (n == null) {
				getView().getTxfOutputPath().textProperty().unbindBidirectional(o.outputPathProperty());
			}
		});

		templateApplyController.setModel(null);
		getView().setTemplateApplyView(templateApplyController.getView());

		getView().getTblTemplates().getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
			templateApplyController.setModel(n);
		});
		getView().getBtnUpTemplate().setOnAction(e -> up(getView().getTblTemplates(), getModel().getTemplatesConfig()));
		getView().getBtnDownTemplate()
				.setOnAction(e -> down(getView().getTblTemplates(), getModel().getTemplatesConfig()));
		getView().getBtnAddTemplate().setOnAction(e -> addTemplates());
		getView().getBtnDeleteTemplate().setOnAction(e->deleteTemplate());

	}

	private void addTemplates() {
		ObservableList<TemplateDefModel> templates = FXCollections.observableArrayList();
		if (templateSelector.show(templates)) {
			for (TemplateDefModel template : templates) {
				templateApplyConfigClientService.addTemplate(getModel(), template);
			}
		}
	}

	private void deleteTemplate() {
		TemplateApplyConfigModel selectedTemplate = getSelectedTemplate();
		if(selectedTemplate!=null && alertHelper.confirmDelete(selectedTemplate.getClass().getSimpleName(), selectedTemplate.getName()))
			getModel().getTemplatesConfig().remove(selectedTemplate);
	}

	private TemplateApplyConfigModel getSelectedTemplate() {
		return getView().getTblTemplates().getSelectionModel().getSelectedItem();

	}
}
