package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.views.INodeFactoryHelper;
import com.diguits.javafx.views.ModelView;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SwapModelEditorView<TModel> extends ModelView<AnchorPane, Property<ObservableList<TModel>>> {

	protected ListView<TModel> lstFrom;
	protected ListView<TModel> lstTo;
	protected Button btnRight;
	protected Button btnLeft;
	protected Button btnUp;
	protected Button btnDown;
	private String sourceResourceName;
	private String targetResourceName;

	public SwapModelEditorView(String sourceResourceName, String targetResourceName, INodeFactoryHelper nodeFactory) {
		super();
		this.sourceResourceName = sourceResourceName;
		this.targetResourceName = targetResourceName;
		this.nodeFactory = nodeFactory;
	}

	@Override
	protected void bindFieldsToModel() {
		bindBidirectional(lstTo.itemsProperty(),getModel());
		bind(btnLeft.disableProperty(),lstTo.getSelectionModel().selectedItemProperty().isNull());
		bind(btnRight.disableProperty(),lstFrom.getSelectionModel().selectedItemProperty().isNull());

		bind(btnUp.disableProperty(),lstTo.getSelectionModel().selectedItemProperty().isNull()
				.or(lstTo.getSelectionModel().selectedIndexProperty().isEqualTo(0)));
		bind(btnDown.disableProperty(),lstTo.getSelectionModel().selectedItemProperty().isNull().or(lstTo
				.getSelectionModel().selectedIndexProperty().isEqualTo(Bindings.size(lstTo.getItems()).subtract(1))));
	}

	public void setFromModel(ObservableList<TModel> fromModel){
		lstFrom.setItems(fromModel);
	}

	@Override
	protected AnchorPane buildView() {
		GridPane gridPane = nodeFactory.createGridPaneForSwap();
		nodeFactory.addRowConstraints(gridPane, 2);
		gridPane.getRowConstraints().get(gridPane.getRowConstraints().size()-1).setVgrow(Priority.SOMETIMES);
		GridPane.setColumnSpan(nodeFactory.createLabelInsideGrid(gridPane, sourceResourceName, 0, 0), 2);
		GridPane.setColumnSpan(nodeFactory.createLabelInsideGrid(gridPane, targetResourceName, 2, 0), 2);

		lstFrom = nodeFactory.createListViewInsideGrid(gridPane, 0, 1);
		lstTo = nodeFactory.createListViewInsideGrid(gridPane, 2, 1);

		VBox fromVB = new VBox();
		btnRight = nodeFactory.createButton16x16("/images/arrow_right.png");
		VBox.setMargin(btnRight, new Insets(2, 2, 2, 2));
		btnLeft = nodeFactory.createButton16x16("/images/arrow_left.png");
		VBox.setMargin(btnLeft, new Insets(2, 2, 2, 2));
		fromVB.getChildren().addAll(btnRight, btnLeft);
		gridPane.add(fromVB, 1, 1);
		GridPane.setMargin(fromVB, new Insets(4, 2, 2, 2));

		VBox toVB = new VBox();
		btnUp = nodeFactory.createButton16x16("/images/arrow_up.png");
		VBox.setMargin(btnUp, new Insets(2, 2, 2, 2));
		btnDown = nodeFactory.createButton16x16("/images/arrow_down.png");
		VBox.setMargin(btnDown, new Insets(2, 2, 2, 2));
		toVB.getChildren().addAll(btnUp, btnDown);
		gridPane.add(toVB, 3, 1);
		GridPane.setMargin(toVB, new Insets(4, 2, 2, 2));

		return nodeFactory.addAndFitToAnchorPane(gridPane);
	}

	public ListView<TModel> getLstFrom() {
		return lstFrom;
	}

	public ListView<TModel> getLstTo() {
		return lstTo;
	}

	public Button getBtnRight() {
		return btnRight;
	}

	public Button getBtnLeft() {
		return btnLeft;
	}

	public Button getBtnUp() {
		return btnUp;
	}

	public Button getBtnDown() {
		return btnDown;
	}

}
