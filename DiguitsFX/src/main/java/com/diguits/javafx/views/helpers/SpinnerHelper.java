package com.diguits.javafx.views.helpers;

import com.diguits.javafx.controls.DoubleSpinnerValueFactory;
import com.diguits.javafx.controls.IntegerSpinnerValueFactory;
import com.diguits.javafx.controls.LongSpinnerValueFactory;
import com.diguits.javafx.controls.TimeSpinner;

import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

public class SpinnerHelper {

	public static Spinner<Integer> createIntSpinner() {
		Spinner<Integer> spinner = createIntSpinner(Integer.MIN_VALUE, Integer.MAX_VALUE);
		return configureSpinner(spinner);
	}

	private static <T> Spinner<T> configureSpinner(Spinner<T> spinner) {
		spinner.setEditable(true);
		spinner.focusedProperty().addListener((v, o, n) -> {
			if (!n && o)
				commitSpinnerEditorText(spinner);
		});
		return spinner;
	}

	private static <T> void commitSpinnerEditorText(Spinner<T> spinner) {
		String text = spinner.getEditor().getText();
		SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
		if (valueFactory != null) {
			StringConverter<T> converter = valueFactory.getConverter();
			if (converter != null) {
				T value = converter.fromString(text);
				valueFactory.setValue(value);
			}
		}
	}
	public static Spinner<Integer> createIntSpinner(int minValue, int maxValue) {
		Spinner<Integer> spinner = new Spinner<Integer>(new IntegerSpinnerValueFactory(minValue, maxValue));
		return configureSpinner(spinner);
	}

	public static Spinner<Long> createLongSpinner() {
		Spinner<Long> spinner = createLongSpinner(Long.MIN_VALUE, Long.MAX_VALUE);
		return configureSpinner(spinner);
	}

	public static Spinner<Long> createLongSpinner(long minValue, long maxValue) {
		Spinner<Long> spinner = new Spinner<Long>(new LongSpinnerValueFactory(minValue, maxValue));
		return configureSpinner(spinner);
	}

	public static Spinner<Double> createDoubleSpinner() {
		Spinner<Double> spinner = createDoubleSpinner(Double.MIN_VALUE, Double.MAX_VALUE);
		return configureSpinner(spinner);
	}

	public static Spinner<Double> createDoubleSpinner(Double minValue, Double maxValue) {
		Spinner<Double> spinner = new Spinner<Double>(new DoubleSpinnerValueFactory(minValue, maxValue));
		return configureSpinner(spinner);
	}

	public static DatePicker createDatePicker() {
		DatePicker datePicker = new DatePicker();
		return datePicker;
	}

	public static TimeSpinner createTimeSpinner() {
		TimeSpinner timeSpinner = new TimeSpinner(null);
		configureSpinner(timeSpinner);
		return timeSpinner;
	}

	public static <T> Spinner<T> createSpinner() {
		Spinner<T> spinner = new Spinner<T>();
		spinner.setEditable(true);
		return spinner;
	}

	public static <T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		Spinner<T> spinner = createSpinner();

		// useage in client code
		spinner.focusedProperty().addListener((s, ov, nv) -> {
			if (nv)
				return;
			// intuitive method on textField, has no effect, though
			// spinner.getEditor().commitValue();
			commitEditorText(spinner);
		});

		GridPane.setMargin(spinner, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size());
		gridPane.add(spinner, columnIndex, rowIndex);
		return spinner;
	}

	private static <T> void commitEditorText(Spinner<T> spinner) {
		if (!spinner.isEditable())
			return;
		String text = spinner.getEditor().getText();
		SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
		if (valueFactory != null) {
			StringConverter<T> converter = valueFactory.getConverter();
			if (converter != null) {
				T value = converter.fromString(text);
				valueFactory.setValue(value);
			}
		}
	}

	public static <T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
			String text) {
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return createSpinnerInsideGrid(gridPane, columnIndex, rowIndex);
	}

	public static <T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, String text) {
		return createSpinnerInsideGrid(gridPane, 1, gridPane.getRowConstraints().size(), text);
	}

}
