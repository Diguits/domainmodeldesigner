package com.diguits.javafx.views.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class TextAreaHelper {

	public static TextArea createTextArea() {
		TextArea textArea = new TextArea();
		return textArea;
	}

	public static TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		TextArea textArea = createTextArea();
		GridPane.setMargin(textArea, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size(), 100);
		gridPane.add(textArea, columnIndex, rowIndex);
		return textArea;
	}

	public static TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String text) {
		TextArea result = createTextAreaInsideGrid(gridPane, columnIndex, rowIndex);
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return result;
	}

	public static TextArea createTextAreaInsideGrid(GridPane gridPane, String text) {
		return createTextAreaInsideGrid(gridPane, 1, gridPane.getRowConstraints().size(), text);
	}

}
