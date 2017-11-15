package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.BoundedContextDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class BoundedContextDefEditorController extends BaseDefEditorController<BoundedContextDefEditorView, BoundedContextDefModel> {

	@Inject
	DomainModelClientService domainModelClientService;

	@Inject
	public BoundedContextDefEditorController(BoundedContextDefEditorView view) {
		super(view);
	}

	ObservableList<BoundedContextDefModel> dependenciesBoundedContextsToAdd;

	@Override
	public void initialize() {
		super.initialize();
		BoundedContextDefModel model = getModel();
		if (model != null) {
			configureTableModelViewActions(getView().getModulesView(), ModuleDefModel.class);
			configureTableModelViewActions(getView().getEnumsView(), EnumDefModel.class);
			// dependencies
			dependenciesBoundedContextsToAdd = FXCollections.observableArrayList();
			DomainModelDefModel domainModelDef = domainModelClientService.getDomainModelDef();
			dependenciesBoundedContextsToAdd.addAll(domainModelDef.boundedContextsProperty().filtered(m -> m != model));
			domainModelDef.getBoundedContexts().addListener(new ListChangeListener<BoundedContextDefModel>() {

				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends BoundedContextDefModel> change) {
					while (change.next()) {
						dependenciesBoundedContextsToAdd.removeAll(change.getRemoved());
						// TODO borrar esta linea y poner esta dependencia en el
						// modelo
						model.getDependencies().removeAll(change.getRemoved());
						dependenciesBoundedContextsToAdd.addAll(change.getAddedSubList());
					}
				}
			});
			getView().getDependenciesSwapView().setFromModel(dependenciesBoundedContextsToAdd.sorted());
			configureSwapModelViewActions(getView().getDependenciesSwapView(), dependenciesBoundedContextsToAdd.sorted());
		}
	}
}
