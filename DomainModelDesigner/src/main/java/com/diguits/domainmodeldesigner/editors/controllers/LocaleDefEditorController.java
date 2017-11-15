package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.LocaleDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.google.inject.Inject;

public class LocaleDefEditorController extends BaseDefEditorController<LocaleDefEditorView, LocaleDefModel> {
	@Inject
	public LocaleDefEditorController(LocaleDefEditorView view) {
		super(view);
	}
}
