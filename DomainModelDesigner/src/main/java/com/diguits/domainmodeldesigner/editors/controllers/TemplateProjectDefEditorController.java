package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.TemplateProjectDefEditorView;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.google.inject.Inject;

public class TemplateProjectDefEditorController extends TemplateProjectItemEditorController<TemplateProjectDefEditorView, TemplateProjectDefModel> {

	@Inject
	public TemplateProjectDefEditorController(TemplateProjectDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			configureTableModelViewActions(getView().getGroupsView(), TemplateGroupDefModel.class);
		}
	}
}
