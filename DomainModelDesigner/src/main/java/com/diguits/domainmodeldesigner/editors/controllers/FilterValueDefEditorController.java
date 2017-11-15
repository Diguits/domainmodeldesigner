package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.FilterValueDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterValueDefModel;
import com.google.inject.Inject;

public class FilterValueDefEditorController
		extends BaseDefEditorController<FilterValueDefEditorView, FilterValueDefModel> {

	@Inject
	private PathEditorController pathEditorController;

	@Inject
	public FilterValueDefEditorController(FilterValueDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			getView().getBtnEditPath().setOnAction(e -> editPath());
			getModel().pathProperty().addListener((v, o, n) -> {
				getView().updateValuesNodes();
			});
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
