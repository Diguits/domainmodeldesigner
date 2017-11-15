package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.EnumValueDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.google.inject.Inject;

public class EnumValueDefEditorController extends BaseDefEditorController<EnumValueDefEditorView, EnumValueDefModel> {

	@Inject
	public EnumValueDefEditorController(EnumValueDefEditorView view) {
		super(view);
	}

}
