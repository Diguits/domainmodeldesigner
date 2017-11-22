package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;
import com.diguits.domainmodeldefinition.definitions.FilterOperator;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterValueDefModel;
import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FilterValueDefEditorView extends BaseDefEditorView<FilterValueDefModel> {

	@Inject
	IEditorByDataTypeFactory editorByDataTypeFactory;

	private TextField txfPath;
	private Button btnEditPath;
	private ChoiceBox<FilterLogicalOperator> choLogicalOperator;
	private ChoiceBox<FilterOperator> choOperator;
	private GridPane gridPane;

	private Node valueNode;
	private Node value2Node;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%filter");
		gridPane = nodeFactory.createGridPaneForEdit();

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

		choOperator = nodeFactory.createChoiceBoxInsideGrid(gridPane, "%operator");
		nodeFactory.createLabelInsideGrid(gridPane, "%value", 3);
		nodeFactory.createLabelInsideGrid(gridPane, "%second_value", 4);

		tab.setContent(nodeFactory.wrapInScrollPane(gridPane));

		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(txfPath.textProperty(), model.pathProperty());

			choOperator.setItems(FXCollections.observableArrayList(FilterOperator.values()));
			bindBidirectional(choOperator.valueProperty(), model.operatorProperty());

			choLogicalOperator.setItems(FXCollections.observableArrayList(FilterLogicalOperator.values()));
			bindBidirectional(choLogicalOperator.valueProperty(), model.logicalOperatorProperty());

			updateValuesNodes();
		}
	}

	public void updateValuesNodes() {
		DataType dataType = getDataTypeByPath();
		if (valueNode != null)
			gridPane.getChildren().remove(valueNode);
		valueNode = editorByDataTypeFactory.create(dataType, null, null,
				getModel().valueProperty());
		if (valueNode != null) {
			GridPane.setMargin(valueNode, new Insets(2, 2, 2, 2));
			gridPane.add(valueNode, 1, 3);
		}
		EnumDefModel enumDef = getEnumDefByPath();
		updateValue(enumDef);

		if (value2Node != null)
			gridPane.getChildren().remove(value2Node);
		value2Node = editorByDataTypeFactory.create(dataType, null, null,
				getModel().value2Property());
		if (value2Node != null) {
			GridPane.setMargin(value2Node, new Insets(2, 2, 2, 2));
			gridPane.add(value2Node, 1, 4);
		}
		updateValue2(enumDef);
	}

	@SuppressWarnings("unchecked")
	private void updateValue(EnumDefModel enumDefModel) {
		if (enumDefModel != null && valueNode instanceof ComboBox) {
			((ComboBox<EnumValueDefModel>) valueNode).setItems(enumDefModel.getValues());
			for (EnumValueDefModel value : enumDefModel.getValues()) {
				if (value.getId().equals(getModel().getValue())) {
					((ComboBox<EnumValueDefModel>) valueNode).getSelectionModel().select(value);
					break;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void updateValue2(EnumDefModel enumDefModel) {
		if (enumDefModel != null && value2Node instanceof ComboBox) {
			((ComboBox<EnumValueDefModel>) value2Node).setItems(enumDefModel.getValues());
			for (EnumValueDefModel value : enumDefModel.getValues()) {
				if (value.getId().equals(getModel().getValue2())) {
					((ComboBox<EnumValueDefModel>) value2Node).getSelectionModel().select(value);
					break;
				}
			}
		}
	}

	private DataType getDataTypeByPath() {
		if (getModel() != null) {
			FieldDefModel field = getModel().getField();
			if (field != null)
				return field.getDataType();
		}
		return DataType.None;
	}

	private EnumDefModel getEnumDefByPath() {
		if (getModel() != null) {
			FieldDefModel field = getModel().getField();
			if (field != null)
				return field.getEnumDef();
		}
		return null;
	}

	public Button getBtnEditPath() {
		return btnEditPath;
	}

}
