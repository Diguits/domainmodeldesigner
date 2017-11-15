package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.LocalizedDataDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;
import com.google.inject.Inject;

public class LocalizedDataDefEditorController extends BaseDefEditorController<LocalizedDataDefEditorView, LocalizedDataDefModel> {

	@Inject
	public LocalizedDataDefEditorController(LocalizedDataDefEditorView view) {
		super(view);
	}

}
