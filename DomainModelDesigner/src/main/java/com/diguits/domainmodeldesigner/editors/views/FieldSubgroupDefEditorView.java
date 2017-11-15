package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FieldSubgroupDefEditorView extends BaseDefEditorView<FieldSubgroupDefModel>{

	private CheckBox chbHasVisualRepresentation;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%subgroup");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		chbHasVisualRepresentation = nodeFactory.createCheckBoxInsideGrid(gridPane, "%has_visual_representation");

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(gridPane);
		nodeFactory.fitToAnchorPane(gridPane);
		tab.setContent(anchorPane);
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(chbHasVisualRepresentation.selectedProperty(),getModel().hasVisualRepresentationProperty());
		}
	}

}
