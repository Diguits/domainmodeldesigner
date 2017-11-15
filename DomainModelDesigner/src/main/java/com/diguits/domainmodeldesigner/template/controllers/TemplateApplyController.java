package com.diguits.domainmodeldesigner.template.controllers;

import com.diguits.domainmodeldesigner.editors.controllers.TemplateApplyConfigEditorController;
import com.diguits.domainmodeldesigner.services.TemplateProjectApplyConfigClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.services.tasks.TemplateApplyTask;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.views.TemplateApplyView;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ModelViewControllerBase;
import com.google.inject.Inject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TemplateApplyController extends ModelViewControllerBase<TemplateApplyView, TemplateApplyConfigModel> {

	protected ObjectProperty<TemplateProjectApplyConfigModel> selectedProjectApplyConfigProperty = new SimpleObjectProperty<TemplateProjectApplyConfigModel>();

	@Inject
	TemplateProjectApplyConfigClientService templateApplyConfigClientService;

	@Inject
	private TemplateApplyConfigEditorController templateApplyConfigEditorController;

	@Inject
	private TemplateProjectClientService templateClientService;

	BooleanProperty canApplyProperty = new SimpleBooleanProperty();
	private TemplateDefModel templateDefModel;

	@Inject
	private TemplateApplyingController templateApplyingController;

	@Inject
	public TemplateApplyController(TemplateApplyView view) {
		super(view);
		canApplyProperty = new SimpleBooleanProperty();
		canApplyProperty.set(false);
	}

	@Override
	protected void modelUpdated(TemplateApplyConfigModel o, TemplateApplyConfigModel n) {
		super.modelUpdated(o, n);
		canApplyProperty.unbind();
		if (n != null)
			canApplyProperty.bind(
					templateApplyConfigEditorController.getView().getChbForSpecificModel().selectedProperty()
							.and(getModel().modelIdsProperty().isNull()).or(getModel().inMemoryProperty().not()
									.and(getModel().outputPathExpressionProperty().isEmpty()
											.or(getModel().outputFilenameExpressionProperty().isEmpty())))
							.not());
		else
			canApplyProperty.set(false);

	}

	@Override
	public void initialize() {
		super.initialize();
		getView().setTemplateApplyConfigEditorView(templateApplyConfigEditorController.getView());
		selectedProjectApplyConfigProperty
				.bind(getView().getCbxApplyProjectConfig().getSelectionModel().selectedItemProperty());
		selectedProjectApplyConfigProperty.addListener((v, o, n) -> {
			if (o != null) {
				getView().getTxfProjectOutputPath().textProperty().unbindBidirectional(o.outputPathProperty());
			}
			if (n != null) {
				getView().getTxfProjectOutputPath().textProperty().bindBidirectional(n.outputPathProperty());
			}
			selectApplyConfig();
		});

		modelProperty().bind(getView().getCbxApplyConfig().getSelectionModel().selectedItemProperty());

		modelProperty().addListener((v, o, n) -> {
			templateApplyConfigEditorController.setModel(n);
		});
		ListProperty<TemplateProjectApplyConfigModel> configs = templateApplyConfigClientService.configsProperty();
		getView().setProjectConfigs(configs);
		selectApplyProjectConfig(configs);

		getView().getBtnAddProjectConfig().setOnAction(e -> {
			TemplateProjectApplyConfigModel config = templateApplyConfigClientService.addTemplateProjectApplyCongig();
			getView().selectProjectConfig(config);
		});
		getView().getBtnAddConfig().setOnAction(e -> {
			TemplateProjectApplyConfigModel selectedApplyProjectConfig = selectedProjectApplyConfigProperty.get();
			if (selectedApplyProjectConfig != null) {
				TemplateApplyConfigModel config = templateApplyConfigClientService
						.addTemplate(selectedApplyProjectConfig, templateDefModel);
				getView().selectConfig(config);
			}
		});
	}

	private void selectApplyConfig() {
		TemplateProjectApplyConfigModel selectedApplyProjectConfig = selectedProjectApplyConfigProperty.get();
		if (selectedApplyProjectConfig != null) {
			getView().setConfigs(
					selectedApplyProjectConfig.getTemplatesConfig().filtered(c -> c.getTemplate() == templateDefModel));
		}
	}

	private void selectApplyProjectConfig(ListProperty<TemplateProjectApplyConfigModel> configs) {
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

	public void applyTemplateModal(TemplateDefModel templateDefModel) {
		this.templateDefModel = templateDefModel;
		if (getInitialized())
			selectApplyConfig();
		if (DialogHelper.showOKCancelDialog(this, new SimpleStringProperty(resources.getString("applying_template")),
				canApplyProperty.not())) {
			TemplateApplyTask task = templateClientService.getApplyTemplateTask(getModel());
			templateApplyingController.startModal(task);
		}
	}
}