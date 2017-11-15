package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.DomainModelDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.google.inject.Inject;

public class DomainModelDefEditorController extends BaseDefEditorController<DomainModelDefEditorView, DomainModelDefModel> {

	@Inject
	public DomainModelDefEditorController(DomainModelDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			configureTableModelViewActions(getView().getLocalesView(), LocaleDefModel.class);
			configureTableModelViewActions(getView().getCustomFieldsView(), CustomFieldDefModel.class);
		}
	}

}
