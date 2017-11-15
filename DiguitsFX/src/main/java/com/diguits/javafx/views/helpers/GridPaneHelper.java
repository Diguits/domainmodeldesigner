package com.diguits.javafx.views.helpers;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GridPaneHelper {

	public static GridPane createGridPaneForEdit() {
		GridPane gridPane = createGridPane();
		addEditColumnsToGridPane(gridPane);
		return gridPane;
	}

	private static GridPane createGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.getStyleClass().add("grid-pane");
		gridPane.setVgap(5);
		return gridPane;
	}

	private static void addEditColumnsToGridPane(GridPane gridPane) {
		ColumnConstraints colLabels = new ColumnConstraints();
		colLabels.setMinWidth(10.0);
		colLabels.setHalignment(HPos.RIGHT);
		ColumnConstraints colsField = new ColumnConstraints();
		colsField.setHgrow(Priority.SOMETIMES);
		colsField.setPrefWidth(100.0);
		colsField.setMinWidth(10.0);
		colsField.setHalignment(HPos.LEFT);
		gridPane.getColumnConstraints().addAll(colLabels, colsField);
	}

	public static GridPane createGridPaneForEditFourColumns() {
		GridPane gridPane = createGridPane();
		addEditColumnsToGridPane(gridPane);
		addEditColumnsToGridPane(gridPane);
		return gridPane;
	}

	public static GridPane createGridPaneForSwap() {
		GridPane gridPane = createGridPane();
		ColumnConstraints colFrom = new ColumnConstraints();
		colFrom.setHgrow(Priority.SOMETIMES);
		colFrom.setMinWidth(10.0);
		ColumnConstraints colFromButtons = new ColumnConstraints();
		colFromButtons.setMinWidth(10.0);
		ColumnConstraints colTo = new ColumnConstraints();
		colTo.setHgrow(Priority.SOMETIMES);
		colTo.setMinWidth(10.0);
		ColumnConstraints colToButtons = new ColumnConstraints();
		colToButtons.setMinWidth(10.0);
		gridPane.getColumnConstraints().addAll(colFrom, colFromButtons, colTo, colToButtons);
		return gridPane;
	}

	public static GridPane createGridPaneTwoSameColumns() {
		GridPane gridPane = createGridPane();
		ColumnConstraints colsFirst = new ColumnConstraints();
		colsFirst.setHgrow(Priority.ALWAYS);
		colsFirst.setMinWidth(10.0);
		ColumnConstraints colsSecond = new ColumnConstraints();
		colsSecond.setHgrow(Priority.ALWAYS);
		colsSecond.setMinWidth(10.0);
		gridPane.getColumnConstraints().addAll(colsFirst, colsSecond);
		return gridPane;
	}

	public static void addRowConstraints(GridPane gridPane, int count) {
		RowConstraints createRowConstraints = null;
		for (int i = 0; i < count; i++) {
			createRowConstraints = createRowConstraints();
			gridPane.getRowConstraints().add(createRowConstraints);
		}
	}

	public static void addRowConstraints(GridPane gridPane, int count, double prefHeight) {
		RowConstraints createRowConstraints = null;
		for (int i = 0; i < count-1; i++) {
			createRowConstraints = createRowConstraints();
			gridPane.getRowConstraints().add(createRowConstraints);
		}
		createRowConstraints = createRowConstraints(prefHeight);
		gridPane.getRowConstraints().add(createRowConstraints);
	}

	public static RowConstraints createRowConstraints() {
		double prefHeight = 30.0;
		return createRowConstraints(prefHeight);
	}

	public static RowConstraints createRowConstraints(double prefHeight) {
		RowConstraints rowConstraints = new RowConstraints();
		rowConstraints.setMinHeight(10.0);
		rowConstraints.setPrefHeight(prefHeight);
		rowConstraints.setFillHeight(true);
		return rowConstraints;
	}

}
