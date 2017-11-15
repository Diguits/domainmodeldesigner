package com.diguits.javafx.undo;

import com.diguits.javafx.undo.changes.Change;

import javafx.beans.property.ReadOnlyBooleanProperty;

public interface UndoManager {

	boolean undo();

	boolean redo();

	void addChange(Change change);

	void startChangeGruop();

	void applyChangeGruop();

	void cancelChangeGruop();

	ReadOnlyBooleanProperty canUndoProperty();

	boolean getCanUndo();

	ReadOnlyBooleanProperty canRedoProperty();

	boolean getCanRedo();
}
