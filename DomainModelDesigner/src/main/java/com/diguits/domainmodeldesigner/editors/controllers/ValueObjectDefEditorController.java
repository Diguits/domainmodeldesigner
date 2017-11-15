package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.ValueObjectDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.google.inject.Inject;

public class ValueObjectDefEditorController extends DomainObjectDefEditorController<ValueObjectDefEditorView, ValueObjectDefModel> {

	@Inject
	DomainModelClientService domainModelClientService;

	@Inject
	public ValueObjectDefEditorController(ValueObjectDefEditorView view) {
		super(view);
	}
}
