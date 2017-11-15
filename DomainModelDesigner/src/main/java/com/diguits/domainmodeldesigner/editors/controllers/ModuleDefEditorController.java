package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.ModuleDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.google.inject.Inject;

public class ModuleDefEditorController extends BaseDefEditorController<ModuleDefEditorView, ModuleDefModel> {

	@Inject
	public ModuleDefEditorController(ModuleDefEditorView view) {
		super(view);
	}

}
