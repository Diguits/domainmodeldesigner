package com.diguits.domainmodeldesigner.editors.views;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.controlsfx.control.PropertySheet;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldefinition.definitions.EnumValueDef;
import com.diguits.domainmodeldefinition.serialization.services.DataTypeToJavaTypeConverter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;

public class CustomDataValueItem implements PropertySheet.Item {

	String name;
	String description;
	DataType dataType;
	ObjectProperty<Object> valueProperty;
	private UUID reference;
	private boolean multiline;

	public CustomDataValueItem(String name, String description, DataType dataType, UUID reference, boolean multiline,
			ObjectProperty<Object> valueProperty) {
		super();
		this.name = name;
		this.description = description;
		this.dataType = dataType;
		this.valueProperty = valueProperty;
		this.reference = reference;
		this.multiline = multiline;
	}

	@Override
	public Class<?> getType() {
		Class<?> result = DataTypeToJavaTypeConverter.getJavaType(dataType);
		if (dataType == DataType.Enum)
			return EnumValueDef.class;
		return result;
	}

	@Override
	public String getCategory() {
		return "";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Object getValue() {
		return valueProperty != null && valueProperty.getValue() != null ? valueProperty.getValue() : getDefaultValue();
	}

	@Override
	public void setValue(Object value) {
		if (valueProperty != null)
			valueProperty.setValue(value);
	}

	@Override
	public Optional<ObservableValue<? extends Object>> getObservableValue() {
		return Optional.of(valueProperty);
	}

	private Object getDefaultValue() {
		switch (dataType) {
		case Boolean:
			return false;
		case Byte:
			return 0;
		case Char:
			return '\u0000';
		case Decimal:
			return new BigDecimal(0);
		case Double:
			return 0.0d;
		case Int:
			return 0;
		case Long:
			return 0L;
		case Short:
			return 0;
		case Single:
			return 0.0f;
		case Date:
		case DateTime:
		case Time:
			return Calendar.getInstance().getTime();
		default:
			return null;
		}
	}

	public DataType getDataType() {
		return dataType;
	}

	public UUID getReference() {
		return reference;
	}

	public boolean getMultiline() {
		return multiline;
	}

}