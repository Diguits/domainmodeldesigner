package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.FilterDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterDefModel;
import com.google.inject.Inject;

public class FilterDefEditorController extends BaseDefEditorController<FilterDefEditorView, FilterDefModel> {

	@Inject
	private PathEditorController pathEditorController;

	@Inject
	public FilterDefEditorController(FilterDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			//configureTableModelViewActions(getView().getInnerFiltersView(), FilterDefModel.class);
			getView().getBtnEditPath().setOnAction(e -> editPath());
		}
	}

	// Path
	// ******************************************************************************
	private void editPath() {
		if (getModel() != null) {
			EntityDefModel entity = getModel().getEntity();
			String path = pathEditorController.editPath(entity, getModel().getPath());
			if (path != null)
				getModel().setPath(path);
		}
	}

}
