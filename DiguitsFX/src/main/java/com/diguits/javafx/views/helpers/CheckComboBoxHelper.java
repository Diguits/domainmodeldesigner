package com.diguits.javafx.views.helpers;

import org.controlsfx.control.CheckComboBox;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class CheckComboBoxHelper {

	public static <T> CheckComboBox<T> createCheckComboBox() {
		return new CheckComboBox<>();
	}

	public static <T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		CheckComboBox<T> checkComboBox = createCheckComboBox();
		GridPane.setMargin(checkComboBox, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size());
		gridPane.add(checkComboBox, columnIndex, rowIndex);
		return checkComboBox;
	}

	public static <T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
			String text) {
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return createCheckComboBoxInsideGrid(gridPane, columnIndex, rowIndex);
	}

	public static <T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, String text) {
		LabelHelper.createLabelInsideGrid(gridPane, text);
		return createCheckComboBoxInsideGrid(gridPane, 1, gridPane.getRowConstraints().size());
	}
}
