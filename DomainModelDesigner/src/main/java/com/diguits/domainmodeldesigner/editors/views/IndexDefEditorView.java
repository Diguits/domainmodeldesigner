package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.SwapModelEditorView;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class IndexDefEditorView extends BaseDefEditorView<IndexDefModel> {

	private CheckBox chbIsUnique;
	private SwapModelEditorView<FieldDefModel> fieldsSwapView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%fields");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		nodeFactory.addRowConstraints(gridPane, 2);
		gridPane.getRowConstraints().get(1).setVgrow(Priority.SOMETIMES);
		chbIsUnique = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, 0, "%is_unique");
		fieldsSwapView = new SwapModelEditorView<FieldDefModel>("%field_to_add", "%fields", nodeFactory);
		gridPane.add(fieldsSwapView.getNodeView(), 0, 1, 2, 1);
		tab.setContent(nodeFactory.wrapInScrollPane(gridPane));
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (model != null) {
			bindBidirectional(chbIsUnique.selectedProperty(),model.uniqueProperty());
			fieldsSwapView.setModel(getModel().fieldsProperty());
		}
	}

	public SwapModelEditorView<FieldDefModel> getFieldsSwapView() {
		return fieldsSwapView;
	}
}
