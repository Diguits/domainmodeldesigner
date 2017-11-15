package com.diguits.javafx.controls;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.controlsfx.control.PropertySheet.Item;
import org.controlsfx.property.editor.AbstractPropertyEditor;
import org.controlsfx.property.editor.PropertyEditor;

import com.diguits.javafx.controls.TimeSpinner;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class PropertySheetEditors {
	public static final PropertyEditor<?> createDateEditor(Item property) {
		return new AbstractPropertyEditor<Date, DatePicker>(property, new DatePicker()) {

			ObjectProperty<Date> date;

			@Override
			public Date getValue() {
				return super.getValue();
			}

			@Override
			public DatePicker getEditor() {
				DatePicker editor = super.getEditor();
				editor.valueProperty().addListener((v, o, n) -> {
					if (n != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.set(n.getYear(), n.getMonth().getValue(), n.getDayOfMonth());
						date.set(calendar.getTime());
					} else
						date.set(null);
				});
				return editor;
			}

			@Override
			protected ObservableValue<Date> getObservableValue() {
				if (date == null)
					date = new SimpleObjectProperty<>(this, "date", null);
				return date;
			}

			@Override
			public void setValue(Date value) {
				if (value != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(value);
					getEditor().setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH)));
				} else
					getEditor().setValue(null);
			}
		};
	}

	public static final PropertyEditor<?> createTimeEditor(Item property) {
		return new AbstractPropertyEditor<Date, TimeSpinner>(property, new TimeSpinner()) {
			ObjectProperty<Date> date;

			@Override
			public TimeSpinner getEditor() {
				TimeSpinner editor = super.getEditor();
				editor.valueProperty().addListener((v, o, n) -> {
					if (n != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.set(0, 0, 0, n.getHour(), n.getMinute(), n.getSecond());
						date.set(calendar.getTime());
					} else
						date.set(null);
				});
				return editor;
			}

			@Override
			public void setValue(Date value) {
				if (value != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(value);
					getEditor().getValueFactory().setValue(LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
							calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));
				} else
					getEditor().getValueFactory().setValue(null);
			}

			@Override
			protected ObservableValue<Date> getObservableValue() {
				if (date == null)
					date = new SimpleObjectProperty<Date>(this, "date", null);
				return date;
			}

		};
	}

	public static final PropertyEditor<?> createUUIDEditor(Item property) {
		return new AbstractPropertyEditor<UUID, TextField>(property, new TextField()) {
			ObjectProperty<UUID> uuid;

			@Override
			public TextField getEditor() {
				TextField editor = super.getEditor();
				editor.textProperty().addListener((v, o, n) -> {
					UUID uuidValue = null;
					try {
						uuidValue = UUID.fromString(n);
					} catch (Exception e) {

					}
					uuid.set(uuidValue);
				});
				return editor;
			}

			@Override
			public void setValue(UUID value) {
				if (value != null) {
					getEditor().setText(value.toString());
				}
				;
			}

			@Override
			protected ObservableValue<UUID> getObservableValue() {
				if (uuid == null)
					uuid = new SimpleObjectProperty<UUID>(this, "uuid", null);
				return uuid;
			}

		};
	}

	public static final <T> PropertyEditor<?> createComboBoxEditor( Item property, final ObservableList<T> choices ) {

        return new AbstractPropertyEditor<T, ComboBox<T>>(property, new ComboBox<T>()) {

            { getEditor().setItems(choices); }

            @Override protected ObservableValue<T> getObservableValue() {
                return getEditor().getSelectionModel().selectedItemProperty();
            }

            @Override public void setValue(T value) {
                getEditor().getSelectionModel().select(value);
            }
        };
    }

    public static final PropertyEditor<?> createTextAreaEditor( Item property ) {

        TextArea textArea = new TextArea();
        textArea.setPrefHeight(100);
		return new AbstractPropertyEditor<String, TextArea>(property, textArea) {

            { enableAutoSelectAll(getEditor()); }

            @Override protected StringProperty getObservableValue() {
                return getEditor().textProperty();
            }

            @Override public void setValue(String value) {
                getEditor().setText(value);
            }
        };
    }

    private static void enableAutoSelectAll(final TextInputControl control) {
        control.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                Platform.runLater(() -> {
                    control.selectAll();
                });
            }
        });
    }

}
