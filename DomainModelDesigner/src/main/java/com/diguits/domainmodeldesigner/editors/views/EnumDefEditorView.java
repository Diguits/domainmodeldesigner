package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class EnumDefEditorView extends BaseDefEditorView<EnumDefModel> {

	private CheckBox chbUseIcon;
	private CheckBox chbUseTitle;
	private CheckBox chbUseColor;

	TableModelEditorView<EnumValueDefModel> valuesView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%enum");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		chbUseIcon = nodeFactory.createCheckBoxInsideGrid(gridPane, "%use_icons");
		chbUseTitle = nodeFactory.createCheckBoxInsideGrid(gridPane, "%use_title");
		chbUseColor = nodeFactory.createCheckBoxInsideGrid(gridPane, "%use_color");
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(gridPane);
		nodeFactory.fitToAnchorPane(gridPane);
		tab.setContent(anchorPane);

		valuesView = createTableEditorViewInNewTab(tabPane, "%values" , EnumValueDefModel.class, tvf -> tvf
			.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
			.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
			.buildTable());

		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(chbUseIcon.selectedProperty(), model.useIconProperty());
			bindBidirectional(chbUseColor.selectedProperty(), model.useColorProperty());
			bindBidirectional(chbUseTitle.selectedProperty(), model.useTitleProperty());
			valuesView.setModel(getModel().valuesProperty());
		}
	}

	public TableModelEditorView<EnumValueDefModel> getValuesView() {
		return valuesView;
	}
}
