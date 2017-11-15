package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public class ValueObjectDefEditorView extends DomainObjectDefEditorView<ValueObjectDefModel> {

	private CheckBox persistAsEntity;

	@Override
	protected void includeAditionalDomainObjectProperties(GridPane gridPane) {
		persistAsEntity = nodeFactory.createCheckBoxInsideGrid(gridPane, "%persist_as_entity");
	}

	@Override
	protected String getTabCaption() {
		return resources.getString("value_object");
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(persistAsEntity.selectedProperty(),model.persistAsEntityProperty());
		}
	}
}
