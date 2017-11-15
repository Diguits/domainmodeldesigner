package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FilterDefEditorView extends BaseDefEditorView<FilterDefModel> {

	TableModelEditorView<FilterDefModel> innerFiltersView;
	private TextField txfPath;
	private Button btnEditPath;
	//private ChoiceBox<FilterType> choFilterType;
	private ChoiceBox<FilterLogicalOperator> choLogicalOperator;
	private CheckBox chbUseOperator;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%filter");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		choLogicalOperator = nodeFactory.createChoiceBoxInsideGrid(gridPane, "%logical_operator");
		nodeFactory.createLabelInsideGrid(gridPane, "%path", 1);

		HBox hBox = new HBox();
		txfPath = nodeFactory.createTextField();
		txfPath.setPrefWidth(500);
		HBox.setMargin(txfPath, new Insets(2.0, 2.0, 2.0, 2.0));
		btnEditPath = nodeFactory.createButton16x16("images/search_tree.png");
		HBox.setMargin(btnEditPath, new Insets(2.0, 2.0, 2.0, 2.0));
		hBox.getChildren().addAll(txfPath, btnEditPath);
		gridPane.add(hBox, 1, 1);

		//choFilterType = nodeFactory.createChoiceBoxInsideGrid(gridPane, 1, 1, "%filter_type");
		chbUseOperator = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, 2, "%use_operator");

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(gridPane);
		nodeFactory.fitToAnchorPane(gridPane);
		tab.setContent(anchorPane);

		/*tab = nodeFactory.createAndAddTab(tabPane, "%inner_filters");

		innerFiltersView = new TableModelEditorView<FilterDefModel>(nodeFactory) {
			private TableColumn<FilterDefModel, String> colInnerFilterName;
			private TableColumn<FilterDefModel, String> colInnerFilterDescription;
			private TableColumn<FilterDefModel, String> colInnerFilterPath;

			@Override
			protected void createColumns() {

				colInnerFilterName = nodeFactory.createAndAddTableColumn(tblData, "%name", 90);
				colInnerFilterDescription = nodeFactory.createAndAddTableColumn(tblData, "%description", 250);
				colInnerFilterPath = nodeFactory.createAndAddTableColumn(tblData, "%description", 250);
			}

			@Override
			protected void bindColumns() {
				colInnerFilterName.setCellValueFactory(cd -> cd.getValue().nameProperty());
				colInnerFilterName.setCellFactory(TextFieldTableCell.forTableColumn());

				colInnerFilterDescription.setCellValueFactory(cd -> cd.getValue().descriptionProperty());
				colInnerFilterDescription.setCellFactory(TextFieldTableCell.forTableColumn());

				colInnerFilterPath.setCellValueFactory(cd -> cd.getValue().pathProperty());
			}
		};
		anchorPane = innerFiltersView.getNodeView();
		tab.setContent(anchorPane);*/

		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(txfPath.textProperty(),model.pathProperty());

			//choFilterType.setItems(FXCollections.observableArrayList(FilterType.values()));
			//choFilterType.valueProperty(),model.filterTypeProperty());

			choLogicalOperator.setItems(FXCollections.observableArrayList(FilterLogicalOperator.values()));
			bindBidirectional(choLogicalOperator.valueProperty(),model.logicalOperatorProperty());

			bindBidirectional(chbUseOperator.selectedProperty(),model.useOperatorProperty());
			//innerFiltersView.setModel(getModel().filtersProperty());
		}
	}

	public TableModelEditorView<FilterDefModel> getInnerFiltersView() {
		return innerFiltersView;
	}

	public Button getBtnEditPath() {
		return btnEditPath;
	}



}
