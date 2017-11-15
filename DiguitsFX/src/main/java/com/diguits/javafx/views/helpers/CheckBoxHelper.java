package com.diguits.javafx.views.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public class CheckBoxHelper {

	public static CheckBox createCheckBox() {
		CheckBox checkBox = new CheckBox();
		checkBox.setMnemonicParsing(false);
		return checkBox;
	}

	public static CheckBox createCheckBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		CheckBox checkBox = new CheckBox();
		checkBox.setMnemonicParsing(false);
		checkBox.setSelected(true);
		GridPane.setMargin(checkBox, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size());
		gridPane.add(checkBox, columnIndex, rowIndex);
		return checkBox;
	}

	public static CheckBox createCheckBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String text) {
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return createCheckBoxInsideGrid(gridPane, columnIndex, rowIndex);
	}

	public static CheckBox createCheckBoxInsideGrid(GridPane gridPane, String text) {
		return createCheckBoxInsideGrid(gridPane, 1, gridPane.getRowConstraints().size(), text);
	}
}
