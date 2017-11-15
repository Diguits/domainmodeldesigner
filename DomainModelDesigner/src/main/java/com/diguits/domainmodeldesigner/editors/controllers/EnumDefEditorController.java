package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.EnumDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.google.inject.Inject;

public class EnumDefEditorController extends BaseDefEditorController<EnumDefEditorView, EnumDefModel> {

	@Inject
	public EnumDefEditorController(EnumDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			configureTableModelViewActions(getView().getValuesView(), EnumValueDefModel.class);
		}
	}
}
