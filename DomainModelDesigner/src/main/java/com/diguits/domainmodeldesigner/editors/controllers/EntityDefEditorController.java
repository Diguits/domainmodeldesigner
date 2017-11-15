package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.EntityDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterDefModel;
import com.google.inject.Inject;

public class EntityDefEditorController extends DomainObjectDefEditorController<EntityDefEditorView, EntityDefModel> {

	@Inject
	DomainModelClientService domainModelClientService;

	@Inject
	public EntityDefEditorController(EntityDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		EntityDefModel model = getModel();
		if (model != null) {
			configureTreeTableModelViewActions(getView().getDefaultFiltersView(), FilterDefModel.class);
			configureTableModelViewActions(getView().getDefaultColumnsView(), ColumnDefModel.class);
			configureStringListViewActions(getView().getAdditionalOperationsView(), "%operation");
			getView().setEntities(
					domainModelClientService.getDomainModelDef().entitiesProperty().filtered(e -> e != model).sorted());
			}
	}
}
