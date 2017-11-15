package com.diguits.javafx.views.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class ChoiceBoxHelper {

	public static  <T> ChoiceBox<T> createChoiceBox() {
		ChoiceBox<T> choiceBox = new ChoiceBox<T>();
		choiceBox.setPrefWidth(250.0);
		return choiceBox;
	}

	public static <T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		ChoiceBox<T> choiceBox = createChoiceBox();
		GridPane.setMargin(choiceBox, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size());
		gridPane.add(choiceBox, columnIndex, rowIndex);
		return choiceBox;
	}

	public static <T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
			String text) {
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return createChoiceBoxInsideGrid(gridPane, columnIndex, rowIndex);
	}

	public static <T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, String text) {
		return createChoiceBoxInsideGrid(gridPane, 1, gridPane.getRowConstraints().size(), text);
	}

}
