package com.diguits.domainmodeldesigner.template.controllers;

import com.diguits.domainmodeldesigner.editors.controllers.TemplateProjectApplyConfigEditorController;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectApplyConfigClientService;
import com.diguits.domainmodeldesigner.template.views.TemplateProjectApplyView;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ModelViewControllerBase;
import com.google.inject.Inject;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleStringProperty;

public class TemplateProjectApplyController
		extends ModelViewControllerBase<TemplateProjectApplyView, TemplateProjectApplyConfigModel> {

	@Inject
	TemplateProjectApplyConfigClientService templateApplyConfigClientService;

	@Inject
	private TemplateProjectApplyConfigEditorController templateProjectApplyConfigEditorController;

	@Inject
	TemplateProjectApplyingController templateProjectApplyingController;

	@Inject
	DomainModelClientService domainModelClienteService;

	@Inject
	public TemplateProjectApplyController(TemplateProjectApplyView view) {
		super(view);
	}

	public void initialize() {
		super.initialize();
		templateProjectApplyConfigEditorController.setModel(getModel());
		getView().setTemplateProjectApplyConfigEditorView(templateProjectApplyConfigEditorController.getView());

		modelProperty().bind(getView().getCbxApplyProjectConfig().getSelectionModel().selectedItemProperty());

		modelProperty().addListener((v, o, n) -> {
			templateProjectApplyConfigEditorController.setModel(n);
		});

		ListProperty<TemplateProjectApplyConfigModel> configs = templateApplyConfigClientService.configsProperty();
		getView().setProjectConfigs(configs);
		selectConfig(configs);
		getView().getBtnAddProjectConfig().setOnAction(e->{
			TemplateProjectApplyConfigModel config = templateApplyConfigClientService.addTemplateProjectApplyCongig();
			getView().selectProjectConfig(config);
		});
	}

	private void selectConfig(ListProperty<TemplateProjectApplyConfigModel> configs) {
		boolean flag = false;
		for (TemplateProjectApplyConfigModel config : configs) {
			if (config.getIsDefault()) {
				getView().selectProjectConfig(config);
				flag = true;
				break;
			}
		}
		if (!flag && configs.size() > 0)
			getView().selectProjectConfig(configs.get(0));
	}

	public void applyTemplateProject() {
		ListProperty<TemplateProjectApplyConfigModel> configs = templateApplyConfigClientService.configsProperty();
		selectConfig(configs);
		if (DialogHelper.showOKCancelDialog(this,
				new SimpleStringProperty(resources.getString("applying_template_project")))) {
			templateProjectApplyingController.startApplying(getModel());
			templateApplyConfigClientService.saveConfig(getModel());
		}
	}
}
