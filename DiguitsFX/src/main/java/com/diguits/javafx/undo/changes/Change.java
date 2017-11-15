package com.diguits.javafx.undo.changes;

public interface Change {

	boolean undo();

	boolean redo();

	boolean merge(Change change);

	boolean undone(Change change);
}
