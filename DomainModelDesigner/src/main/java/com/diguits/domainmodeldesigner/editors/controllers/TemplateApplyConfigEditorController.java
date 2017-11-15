package com.diguits.domainmodeldesigner.editors.controllers;

import java.io.File;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.editors.views.TemplateApplyConfigEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectApplyConfigClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateParamValueModel;
import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;

public class TemplateApplyConfigEditorController extends
		TemplateProjectApplyConfigItemEditorController<TemplateApplyConfigEditorView, TemplateApplyConfigModel> {

	protected ObjectProperty<TemplateApplyConfigModel> templateApplyConfig;

	@Inject
	private TemplateProjectClientService templateClientService;

	@Inject
	private DomainModelClientService domainModelClientService;

	@Inject
	private TemplateProjectApplyConfigClientService templateApplyConfigClientService;

	@Inject
	public TemplateApplyConfigEditorController(TemplateApplyConfigEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		domainModelClientService.domainModelDefProperty().addListener((v, o, n) -> setModels());
		getView().getBtnEditOutputPath().setOnAction(e -> {
			DirectoryChooser directoryChooser = createDirectoryChooser();

			File selectedFile = directoryChooser.showDialog(null);
			if (selectedFile != null) {
				String relative = templateApplyConfigClientService.getRelativeTemplateOutputPath(getModel(),
						selectedFile.getAbsolutePath());
				getModel().setOutputPathExpression(relative);
			}
		});

		if (getModel() != null && getModel().getTemplate() != null)
			getModel().getTemplate().getParameters().addListener(new ListChangeListener<TemplateParameterDefModel>() {
				@Override
				public void onChanged(
						javafx.collections.ListChangeListener.Change<? extends TemplateParameterDefModel> c) {
					while (c.next()) {
						List<? extends TemplateParameterDefModel> added = c.getAddedSubList();
						for (TemplateParameterDefModel customField : added) {
							addRemoveTemplateParamValue(getModel(), customField);
						}
						List<? extends TemplateParameterDefModel> removed = c.getRemoved();
						for (TemplateParameterDefModel customField : removed) {
							removeTemplateParamValue(customField);
						}
					}
				}
			});
	}

	private DirectoryChooser createDirectoryChooser() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = new File(templateApplyConfigClientService.getTemplateOutputPath(getModel()));
		if(file.exists())
			directoryChooser.setInitialDirectory(file);
		return directoryChooser;
	}

	@Override
	protected void setModelToView(TemplateApplyConfigModel n) {
		super.setModelToView(n);
		fillTemplateParams(n);
		setModels();
	}

	private void fillTemplateParams(TemplateApplyConfigModel config) {
		if(config!=null && config.getTemplate()!=null){
			fillTemplateParams(config, config.getTemplate().getParameters());
		}
	}

	private void setModels() {
		List<List<? extends BaseDefModel>> models = null;
		ObservableList<UUID> modelId = null;
		if (getModel() != null) {
			models = templateClientService.getModelsForTemplate(getModel().getTemplate());
			if (models != null && models.size() > 0) {
				modelId = getModel().getModelIds();
			}
		}
		getView().setModels(models, modelId);
	}

	ChangeListener<? super DataType> dataTypeListener = (v, o, n) -> {
		getView().updateItemList();
	};

	protected void fillTemplateParams(TemplateApplyConfigModel model,
			ObservableList<TemplateParameterDefModel> parameters) {
		for (TemplateParameterDefModel parameter : parameters) {
			addRemoveTemplateParamValue(model, parameter);
		}

		for (TemplateParamValueModel parameterValue : getModel().getTemplateParamValues()) {
			parameterValue.getTemplateParameter().dataTypeProperty().addListener(dataTypeListener);
		}
	}

	protected void addRemoveTemplateParamValue(TemplateApplyConfigModel model, TemplateParameterDefModel parameter) {
		if (!model.getTemplateParamValues().stream().anyMatch(cf -> cf.getTemplateParameter() == parameter)) {
			innerAddTemplateParamValue(model, parameter);
		}
	}

	private void innerAddTemplateParamValue(TemplateApplyConfigModel model, TemplateParameterDefModel parameter) {
		TemplateParamValueModel parameterValue = new TemplateParamValueModel();
		parameterValue.setTemplateParameter(parameter);
		model.getTemplateParamValues().add(parameterValue);
	}

	protected void removeTemplateParamValue(TemplateParameterDefModel parameter) {

		if (parameter != null)
			parameter.dataTypeProperty().removeListener(dataTypeListener);

		TemplateParamValueModel toDelete = null;
		for (TemplateParamValueModel parameterValue : getModel().getTemplateParamValues()) {
			if (parameterValue.getTemplateParameter() == parameter)
				toDelete = parameterValue;
		}
		if (toDelete != null)
			getModel().getTemplateParamValues().remove(toDelete);
	}

}