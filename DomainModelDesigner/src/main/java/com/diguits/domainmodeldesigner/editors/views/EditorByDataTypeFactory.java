package com.diguits.domainmodeldesigner.editors.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.javafx.controls.TimeSpinner;
import com.diguits.javafx.views.INodeFactoryHelper;
import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class EditorByDataTypeFactory implements IEditorByDataTypeFactory {
	@Inject
	INodeFactoryHelper nodeFactory;

	@Override
	public Node create(DataType dataType, Object minValue, Object maxValue, ObjectProperty<Object> binding) {
		switch (dataType) {
		case Boolean:
			CheckBox checkBox = nodeFactory.createCheckBox();
			checkBox.setAllowIndeterminate(true);
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Boolean) {
					checkBox.setSelected((Boolean) value);
				} else
					checkBox.setIndeterminate(true);
			} else
				checkBox.setIndeterminate(true);

			checkBox.selectedProperty().addListener((v, o, n) -> {
				if (n != null)
					binding.set(n);
				else
					binding.set(null);
			});
			checkBox.indeterminateProperty().addListener((v, o, n) -> {
				if (n != null && n)
					binding.set(null);
			});

			return checkBox;
		case Byte: {
			Byte byteMinValue = Byte.MIN_VALUE;
			Byte byteMaxValue = Byte.MAX_VALUE;
			if (minValue instanceof Byte) {
				byteMinValue = (Byte) minValue;
			}
			if (maxValue instanceof Byte) {
				byteMaxValue = (Byte) maxValue;
			}
			Spinner<Integer> spinner = nodeFactory.createIntSpinner(byteMinValue, byteMaxValue);
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Byte) {
					spinner.getValueFactory().setValue((int) ((Byte) value));
				} else
					spinner.getValueFactory().setValue(null);
			} else
				spinner.getValueFactory().setValue(null);
			spinner.valueProperty().addListener((v, o, n) -> {
				if (n != null)
					binding.set(new Byte((byte) ((int) n)));
				else
					binding.set(null);
			});
			return spinner;
		}
		case Int: {
			Integer intMinValue = Integer.MIN_VALUE;
			Integer intMaxValue = Integer.MAX_VALUE;

			if (minValue instanceof Integer) {
				intMinValue = (Integer) minValue;
			}
			if (maxValue instanceof Integer) {
				intMaxValue = (Integer) maxValue;
			}
			Spinner<Integer> spinner = nodeFactory.createIntSpinner(intMinValue, intMaxValue);
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Integer) {
					spinner.getValueFactory().setValue((Integer) value);
				} else
					spinner.getValueFactory().setValue(null);
			} else
				spinner.getValueFactory().setValue(null);
			spinner.valueProperty().addListener((v, o, n) -> {
				if (n != null)
					binding.set(n);
				else
					binding.set(null);
			});
			return spinner;
		}
		case Short: {
			Short shortMinValue = Short.MIN_VALUE;
			Short shortMaxValue = Short.MAX_VALUE;

			if (minValue instanceof Short) {
				shortMinValue = (Short) minValue;
			}
			if (maxValue instanceof Short) {
				shortMaxValue = (Short) maxValue;
			}
			Spinner<Integer> spinner = nodeFactory.createIntSpinner(shortMinValue, shortMaxValue);
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Short) {
					spinner.getValueFactory().setValue((int) ((Short) value));
				} else
					spinner.getValueFactory().setValue(null);
			} else
				spinner.getValueFactory().setValue(null);

			spinner.valueProperty().addListener((v, o, n) -> {
				if (n != null)
					binding.set(new Short((short) ((int) n)));
				else
					binding.set(null);
			});
			return spinner;
		}
		case Long: {
			Long longMinValue = Long.MIN_VALUE;
			Long longMaxValue = Long.MAX_VALUE;

			if (minValue instanceof Long) {
				longMinValue = (Long) minValue;
			}
			if (maxValue instanceof Long) {
				longMaxValue = (Long) maxValue;
			}
			Spinner<Long> spinner = nodeFactory.createLongSpinner(longMinValue, longMaxValue);
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Long) {
					spinner.getValueFactory().setValue((Long) value);
				} else
					spinner.getValueFactory().setValue(null);
			} else
				spinner.getValueFactory().setValue(null);
			spinner.valueProperty().addListener((v, o, n) -> {
				if (n != null)
					binding.set(n);
				else
					binding.set(null);
			});
			return spinner;
		}

		case Double:
		case Decimal: {
			Double doubleMinValue = Double.MIN_VALUE;
			Double doubleMaxValue = Double.MAX_VALUE;

			if (minValue instanceof Double) {
				doubleMinValue = (Double) minValue;
			}
			if (maxValue instanceof Double) {
				doubleMaxValue = (Double) maxValue;
			}
			Spinner<Double> spinner = nodeFactory.createDoubleSpinner(doubleMinValue, doubleMaxValue);
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Double) {
					spinner.getValueFactory().setValue((Double) value);
				} else
					spinner.getValueFactory().setValue(null);
			} else
				spinner.getValueFactory().setValue(null);
			spinner.valueProperty().addListener((v, o, n) -> {
				if (n != null)
					binding.set(n);
				else
					binding.set(null);
			});
			return spinner;
		}

		case Single: {
			Float doubleMinValue = Float.MIN_VALUE;
			Float doubleMaxValue = Float.MAX_VALUE;

			if (minValue instanceof Double) {
				doubleMinValue = (Float) minValue;
			}
			if (maxValue instanceof Double) {
				doubleMaxValue = (Float) maxValue;
			}
			Spinner<Double> spinner = nodeFactory.createDoubleSpinner((double) doubleMinValue, (double) doubleMaxValue);
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Float) {
					spinner.getValueFactory().setValue((double) ((Float) value));
				} else
					spinner.getValueFactory().setValue(null);
			} else
				spinner.getValueFactory().setValue(null);
			spinner.valueProperty().addListener((v, o, n) -> {
				if (n != null)
					binding.set(new Float(n));
				else
					binding.set(null);
			});
			return spinner;
		}

		case Date:
		case DateTime: {
			DatePicker datePicker = nodeFactory.createDatePicker();
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Date) {
					Date date = (Date) value;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					datePicker.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH)));
				} else
					datePicker.setValue(null);
			} else
				datePicker.setValue(null);
			datePicker.valueProperty().addListener((v, o, n) -> {
				if (n != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(n.getYear(), n.getMonth().getValue(), n.getDayOfMonth());
					binding.set(calendar.getTime());
				} else
					binding.set(null);
			});
			return datePicker;
		}
		case Time: {
			TimeSpinner timeSpinner = nodeFactory.createTimeSpinner();
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof Date) {
					Date date = (Date) value;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					timeSpinner.getValueFactory().setValue(LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
							calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));
				} else
					timeSpinner.getValueFactory().setValue(null);
			} else
				timeSpinner.getValueFactory().setValue(null);
			timeSpinner.valueProperty().addListener((v, o, n) -> {
				if (n != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(0, 0, 0, n.getHour(), n.getMinute(), n.getSecond());
					binding.set(calendar.getTime());
				} else
					binding.set(null);
			});
			return timeSpinner;
		}
		case String: {
			TextField textField = nodeFactory.createTextField();
			if (binding != null) {
				Object value = binding.get();
				if (value != null && value instanceof String) {
					textField.setText(value.toString());
				}
				textField.textProperty().addListener((v, o, n) -> {
					if (n != null && !n.isEmpty()) {
						binding.set(n);
					} else
						binding.set(null);
				});
			}
			return textField;
		}
		case Enum: {
			ComboBox<EnumValueDefModel> combobox = nodeFactory.createComboBox();
			if (binding != null) {
				combobox.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
					if (n != null) {
						binding.set(n.getId());
					} else {
						binding.set(null);
					}
				});
			}
			return combobox;
		}
		default:
			break;
		}
		return null;
	}
}
