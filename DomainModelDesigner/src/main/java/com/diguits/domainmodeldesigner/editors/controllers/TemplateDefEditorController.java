package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.TemplateDefEditorView;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.javafx.problem.controllers.Position;
import com.google.inject.Inject;

public class TemplateDefEditorController extends TemplateProjectItemEditorController<TemplateDefEditorView, TemplateDefModel> {
	public static final String SOURCECODE = "SourceCode";
	public static final String GENERATEDCODE = "GenerartedCode";
	public static final String FILENAME = "Filename";

	@Inject
	TemplateProjectClientService templateClientService;

	@Inject
	public TemplateDefEditorController(TemplateDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			getView().getBtnRefreshGeneratedCode().setOnAction(e -> refreshGeneratedCode());
			configureTableModelViewActions(getView().getTemplateParametersView(), TemplateParameterDefModel.class);
		}
	}

	@Override
	protected void setModelToView(TemplateDefModel model) {
		super.setModelToView(model);
		if(model!=null)
			getView().setApplyTypes(templateClientService.getModelClassesName());
	}

	private void refreshGeneratedCode() {
		String generatedCode = getGeneratedCode();
		getView().refreshGeneratedCode(generatedCode);
	}

	private String getGeneratedCode() {
		return templateClientService.loadTemplateGenerateCode(getModel());
	}

	@Override
	public boolean goToPosition(Position position) {
		if (position.getFieldName() == SOURCECODE) {
			getView().goToSourceCode(position);
			return true;
		} else  if (position.getFieldName() == GENERATEDCODE) {
			refreshGeneratedCode();
			getView().goToGeneratedCode(position);
			return true;
		} else if (position.getFieldName() == FILENAME) {
			getView().goToFileName(position);
			return true;
		} else
			return super.goToPosition(position);
	}
}
