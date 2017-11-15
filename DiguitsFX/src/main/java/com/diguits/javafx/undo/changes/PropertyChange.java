package com.diguits.javafx.undo.changes;

import javafx.beans.property.Property;

public class PropertyChange<TValue> extends ChangeBase {
	TValue oldValue;
	TValue newValue;
	Property<TValue> property;

	public PropertyChange(TValue oldValue, TValue newValue, Property<TValue> property) {
		super();
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.property = property;
	}

	@Override
	public boolean undo() {
		property.setValue(oldValue);
		return true;
	}

	@Override
	public boolean redo() {
		property.setValue(newValue);
		return true;
	}

	public TValue getOldValue() {
		return oldValue;
	}

	public TValue getNewValue() {
		return newValue;
	}

	public Property<TValue> getProperty() {
		return property;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean merge(Change change) {
		if(change instanceof PropertyChange){
			PropertyChange newChange = ((PropertyChange)change);
			if(newChange.getProperty()==property){
				newValue=(TValue) newChange.newValue;
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public boolean undone(Change change) {
		if(change instanceof PropertyChange){
			return (((PropertyChange)change).getNewValue().equals(oldValue));
		}
		return false;
	}
}
