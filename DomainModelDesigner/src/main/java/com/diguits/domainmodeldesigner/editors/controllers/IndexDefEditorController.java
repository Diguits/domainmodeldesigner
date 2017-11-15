package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.IndexDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;
import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IndexDefEditorController extends BaseDefEditorController<IndexDefEditorView, IndexDefModel> {

	ObservableList<FieldDefModel> fieldToAdd;

	@Inject
	public IndexDefEditorController(IndexDefEditorView view) {
		super(view);
	}

    @Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			fieldToAdd = FXCollections.observableArrayList();
			EntityDefModel entity = (EntityDefModel) getModel().getOwner();
			fieldToAdd.addAll(entity.fieldsProperty().filtered(f -> !getModel().fieldsProperty().contains(f)));
			configureSwapModelViewActions(getView().getFieldsSwapView(), fieldToAdd.sorted());
			getView().getFieldsSwapView().setFromModel(fieldToAdd);

		}
	}
}
