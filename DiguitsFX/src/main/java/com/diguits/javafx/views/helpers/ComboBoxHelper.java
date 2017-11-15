package com.diguits.javafx.views.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class ComboBoxHelper {

	public static <T> ComboBox<T> createComboBox() {
		ComboBox<T> comboBox = new ComboBox<T>();
		comboBox.setPrefWidth(250.0);
		return comboBox;
	}

	public static <T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		ComboBox<T> comboBox = createComboBox();
		GridPane.setMargin(comboBox, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size());
		gridPane.add(comboBox, columnIndex, rowIndex);
		return comboBox;
	}

	public static <T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
			String text) {
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return createComboBoxInsideGrid(gridPane, columnIndex, rowIndex);
	}

	public static <T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, String text) {
		return createComboBoxInsideGrid(gridPane, 1, gridPane.getRowConstraints().size(), text);
	}


}
