package com.diguits.javafx.views.helpers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TextFieldHelper {

	public static TextField createTextField() {
		TextField textField = new TextField();
		return textField;
	}

	public static TextField createTextFieldInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		TextField textField = createTextField();
		GridPane.setMargin(textField, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size());
		gridPane.add(textField, columnIndex, rowIndex);
		return textField;
	}

	public static TextField createTextFieldInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String text) {
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return createTextFieldInsideGrid(gridPane, columnIndex, rowIndex);
	}

	public static TextField createTextFieldInsideGrid(GridPane gridPane, String text) {
		return createTextFieldInsideGrid(gridPane, 1, gridPane.getRowConstraints().size(), text);
	}

	public static TextField createTextField(int maxLength) {
		TextField textField = createTextField();

		textField.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					// Check if the new character is greater than maxLength
					if (textField.getText().length() >= maxLength) {

						// if it's 11th character then just setText to previous
						// one
						textField.setText(textField.getText().substring(0, maxLength));
					}
				}
			}
		});
		return textField;
	}

}
