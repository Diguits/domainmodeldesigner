package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.views.INodeFactoryHelper;
import com.diguits.javafx.views.ModelView;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class StringListEditorView extends ModelView<AnchorPane, Property<ObservableList<String>>> {

	protected Button btnAdd;
	protected Button btnDelete;
	protected Button btnUp;
	protected Button btnDown;
	TextField txfStringToAdd;
	ListView<String> lstStringList;

	private String toAddResourceName;
	private String listResourceName;

	public StringListEditorView(String toAddResourceName, String listResourceName, INodeFactoryHelper nodeFactory) {
		super();
		this.toAddResourceName = toAddResourceName;
		this.listResourceName = listResourceName;
		this.nodeFactory = nodeFactory;
	}

	@Override
	protected void bindFieldsToModel() {
		bindBidirectional(lstStringList.itemsProperty(),getModel());
		lstStringList.setCellFactory(TextFieldListCell.forListView());

		bind(btnAdd.disableProperty(),txfStringToAdd.textProperty().isEmpty());
		bind(btnDelete.disableProperty(),lstStringList.getSelectionModel().selectedItemProperty().isNull());
		bind(btnUp.disableProperty(),lstStringList.getSelectionModel().selectedItemProperty().isNull()
				.or(lstStringList.getSelectionModel().selectedIndexProperty().isEqualTo(0)));
		bind(btnDown.disableProperty()
				,lstStringList.getSelectionModel().selectedItemProperty().isNull()
						.or(lstStringList.getSelectionModel().selectedIndexProperty()
								.isEqualTo(Bindings.size(lstStringList.getItems()).subtract(1))));
	}

	@Override
	protected AnchorPane buildView() {
		BorderPane borderPane = new BorderPane();
		ToolBar toolBar = nodeFactory.createToolBar();
		btnAdd = nodeFactory.createButton16x16("/images/add.png");
		btnDelete = nodeFactory.createButton16x16("/images/delete.png");
		btnUp = nodeFactory.createButton16x16("/images/arrow_up.png");
		btnDown = nodeFactory.createButton16x16("/images/arrow_down.png");
		toolBar.getItems().addAll(btnAdd, btnDelete, btnUp, btnDown);
		borderPane.setTop(toolBar);

		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		nodeFactory.addRowConstraints(gridPane, 2);
		gridPane.getRowConstraints().get(gridPane.getRowConstraints().size() - 1).setVgrow(Priority.SOMETIMES);
		nodeFactory.createLabelInsideGrid(gridPane, toAddResourceName, 0);
		txfStringToAdd = nodeFactory.createTextFieldInsideGrid(gridPane, 1, 0);
		GridPane.setValignment(nodeFactory.createLabelInsideGrid(gridPane, listResourceName, 1), VPos.TOP);
		lstStringList = nodeFactory.createListViewInsideGrid(gridPane, 1, 1);
		borderPane.setCenter(gridPane);

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(borderPane);
		nodeFactory.fitToAnchorPane(borderPane);
		return anchorPane;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public Button getBtnUp() {
		return btnUp;
	}

	public Button getBtnDown() {
		return btnDown;
	}

	public TextField getTxfStringToAdd() {
		return txfStringToAdd;
	}

	public ListView<String> getLstStringList() {
		return lstStringList;
	}
}
