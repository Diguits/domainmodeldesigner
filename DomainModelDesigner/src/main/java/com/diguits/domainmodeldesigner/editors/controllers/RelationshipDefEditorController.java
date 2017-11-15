package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.RelationshipDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.google.inject.Inject;

public class RelationshipDefEditorController extends BaseDefEditorController<RelationshipDefEditorView, RelationshipDefModel> {

	@Inject
	private DomainModelClientService domainModelClientService;


	@Inject
	public RelationshipDefEditorController(RelationshipDefEditorView view) {
		super(view);
	}

	@Override
	protected void setModelToView(RelationshipDefModel n) {
		super.setModelToView(n);
		getView().setDomainObjects(domainModelClientService.getDomainModelDef().getDomainObjects());
	}

	public void setFromDisable(boolean fromDisable) {
		getView().setFromDisable(fromDisable);

	}

	public void setToDisable(boolean toDisable) {
		getView().setToDisable(toDisable);
	}
}
