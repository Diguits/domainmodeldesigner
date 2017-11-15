package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.TemplateParameterDefEditorView;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.google.inject.Inject;

public class TemplateParameterDefEditorController extends TemplateProjectItemEditorController<TemplateParameterDefEditorView, TemplateParameterDefModel> {

	@Inject
	public TemplateParameterDefEditorController(TemplateParameterDefEditorView view) {
		super(view);
	}

	@Override
	protected void setModelToView(TemplateParameterDefModel n) {
		super.setModelToView(n);
	}
}
