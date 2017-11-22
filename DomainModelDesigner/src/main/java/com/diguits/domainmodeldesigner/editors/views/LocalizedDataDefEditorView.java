package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LocalizedDataDefEditorView extends BaseDefEditorView<LocalizedDataDefModel> {

	protected TextField txfCaption;
	protected TextArea txaHint;
	protected TextField txfPlaceHolder;
	protected TextField txfFormat;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		tabPane.getTabs().clear();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%locale");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		txfCaption = nodeFactory.createTextFieldInsideGrid(gridPane, "%caption");
		txaHint = nodeFactory.createTextAreaInsideGrid(gridPane, "%hint");
		txfPlaceHolder = nodeFactory.createTextFieldInsideGrid(gridPane, "%place_holder");
		txfFormat = nodeFactory.createTextFieldInsideGrid(gridPane, "%format");
		tab.setContent(nodeFactory.wrapInScrollPane(gridPane));
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(txfCaption.textProperty(), getModel().captionProperty());
			bindBidirectional(txaHint.textProperty(), getModel().hintProperty());
			bindBidirectional(txfPlaceHolder.textProperty(), getModel().placeHolderProperty());
			bindBidirectional(txfFormat.textProperty(), getModel().formatProperty());
		}
	}

}
