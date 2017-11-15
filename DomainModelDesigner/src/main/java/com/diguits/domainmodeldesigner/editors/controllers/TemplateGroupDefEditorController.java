package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.TemplateGroupDefEditorView;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.google.inject.Inject;

public class TemplateGroupDefEditorController extends TemplateProjectItemEditorController<TemplateGroupDefEditorView, TemplateGroupDefModel> {

	@Inject
	public TemplateGroupDefEditorController(TemplateGroupDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			configureTableModelViewActions(getView().getTemplatesView(), TemplateDefModel.class);
		}
	}
}
