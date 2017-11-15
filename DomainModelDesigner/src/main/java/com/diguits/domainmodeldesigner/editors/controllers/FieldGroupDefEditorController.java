package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.FieldGroupDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.google.inject.Inject;

public class FieldGroupDefEditorController extends BaseDefEditorController<FieldGroupDefEditorView, FieldGroupDefModel> {

	@Inject
	public FieldGroupDefEditorController(FieldGroupDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			configureTableModelViewActions(getView().getSubgroupsView(), FieldSubgroupDefModel.class);
		}
	}
}
