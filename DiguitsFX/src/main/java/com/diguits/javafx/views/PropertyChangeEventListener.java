package com.diguits.javafx.views;

import com.diguits.javafx.undo.changes.PropertyChange;

public interface PropertyChangeEventListener{

	void onPropertyChange(PropertyChange<?> propertyChange);
}
