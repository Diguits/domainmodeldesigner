package com.diguits.javafx.undo;

import com.diguits.javafx.undo.changes.Change;

import javafx.beans.property.ReadOnlyBooleanProperty;

public interface ChangeQueue {

	ReadOnlyBooleanProperty hasNextProperty();

	boolean hasNext();

	ReadOnlyBooleanProperty hasPrevProperty();

	boolean hasPrev();

	Change next();

	Change prev();

	Change popPrev();

	void push(Change change);

	Change getCurrent();

	void clean();
}
