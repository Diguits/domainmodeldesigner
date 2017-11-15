package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.ApplicationDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ApplicationDefEditorController
		extends BaseDefEditorController<ApplicationDefEditorView, ApplicationDefModel> {

	@Inject
	DomainModelClientService domainModelClientService;

	ObservableList<BoundedContextDefModel> boundedContextToAdd;

	@Inject
	public ApplicationDefEditorController(ApplicationDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {

			boundedContextToAdd = FXCollections.observableArrayList();
			DomainModelDefModel domainModelDef = domainModelClientService.getDomainModelDef();
			boundedContextToAdd.addAll(domainModelDef.boundedContextsProperty().filtered(f -> !getModel().boundedContextsProperty().contains(f)));
			domainModelDef.getBoundedContexts().addListener(new ListChangeListener<BoundedContextDefModel>() {

				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends BoundedContextDefModel> change) {
					while (change.next()) {
						boundedContextToAdd.removeAll(change.getRemoved());
						// TODO borrar esta linea y poner esta dependencia en el
						// modelo
						getModel().getBoundedContexts().removeAll(change.getRemoved());
						boundedContextToAdd.addAll(change.getAddedSubList());
					}
				}
			});
			getView().getBoundedContextsSwapView().setFromModel(boundedContextToAdd);
			configureSwapModelViewActions(getView().getBoundedContextsSwapView(), boundedContextToAdd.sorted());
		}
	}
}
