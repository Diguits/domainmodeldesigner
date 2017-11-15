package com.diguits.javafx.views.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LabelHelper {

	public static Label createLabel(String text) {
		return new Label(text);
	}

	public static  Label createLabelInsideGrid(GridPane gridPane, String text) {
		int rowIndex = gridPane.getRowConstraints().size();
		return createLabelInsideGrid(gridPane, text, 0, rowIndex);
	}

	public static Label createLabelInsideGrid(GridPane gridPane, String text, int columnIndex, int rowIndex) {
		Label label = createLabel(text);
		GridPane.setMargin(label, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size() + 1);
		gridPane.add(label, columnIndex, rowIndex);
		return label;
	}

	public static Label createLabelInsideGrid(GridPane gridPane, String text, int rowIndex) {
		return createLabelInsideGrid(gridPane, text, 0, rowIndex);
	}

	public static Label createLabelInsideGrid(GridPane gridPane, String text, int columnIndex, int rowIndex,
			String labeltext) {
		createLabelInsideGrid(gridPane, labeltext, columnIndex - 1, rowIndex);
		return createLabelInsideGrid(gridPane, text, columnIndex, rowIndex);
	}

	public static Label createLabelInsideGrid(GridPane gridPane, String text, String labeltext) {
		return createLabelInsideGrid(gridPane, text, 1, gridPane.getRowConstraints().size(), labeltext);
	}
}
