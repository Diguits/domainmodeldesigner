package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.FieldSubgroupDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.google.inject.Inject;

public class FieldSubgroupDefEditorController extends BaseDefEditorController<FieldSubgroupDefEditorView, FieldSubgroupDefModel> {

	@Inject
	public FieldSubgroupDefEditorController(FieldSubgroupDefEditorView view) {
		super(view);
	}

}
