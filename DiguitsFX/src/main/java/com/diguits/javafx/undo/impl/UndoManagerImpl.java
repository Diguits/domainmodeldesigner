package com.diguits.javafx.undo.impl;

import java.util.Calendar;
import java.util.Stack;
import com.diguits.javafx.undo.ChangeQueue;
import com.diguits.javafx.undo.UndoManager;
import com.diguits.javafx.undo.changes.Change;
import com.diguits.javafx.undo.changes.ChangeGroup;
import com.google.inject.Inject;

import javafx.beans.property.ReadOnlyBooleanProperty;

public class UndoManagerImpl implements UndoManager {

	private static final double TIME_TO_MERGE = 2000;
	ChangeQueue changeQueue;
	Stack<ChangeGroup> changeGroupStack;
	private boolean applyingRedo = false;
	private boolean applyingUndo = false;
	double lastChangeAdded;

	@Inject
	public UndoManagerImpl() {
		super();
		changeQueue = new FixedSizeChangeQueue(20);
		changeGroupStack = new Stack<>();
	}

	@Override
	public boolean undo() {
		Change prev = changeQueue.prev();
		if (prev != null) {
			applyingUndo = true;
			boolean undo = false;
			try {
				undo = prev.undo();
			} finally {
				applyingUndo = false;
			}
			return undo;
		}
		return false;
	}

	@Override
	public boolean redo() {
		Change next = changeQueue.next();
		if (next != null) {
			applyingRedo = true;
			boolean redo = false;
			try {
				redo = next.redo();
			} finally {
				applyingRedo = false;
			}
			return redo;
		}
		return false;
	}

	@Override
	public void addChange(Change change) {
		if (!applyingRedo && !applyingUndo) {
			if (changeGroupStack.size() > 0) {
				ChangeGroup currentChangeGroup = changeGroupStack.lastElement();
				currentChangeGroup.appenChange(change);
			} else {
				boolean add = false;
				double now = Calendar.getInstance().getTimeInMillis();
				if (now - lastChangeAdded < TIME_TO_MERGE) {
					Change current = changeQueue.getCurrent();
					if (current != null) {
						if (current.undone(change)) {
							changeQueue.popPrev();
						} else if (!current.merge(change)) {
							add = true;
						}
					} else {
						add = true;
					}
				} else {
					add = true;
				}
				if (add) {
					changeQueue.push(change);
					lastChangeAdded = Calendar.getInstance().getTimeInMillis();
				}
			}
		}
	}

	@Override
	public ReadOnlyBooleanProperty canUndoProperty() {
		return changeQueue.hasPrevProperty();
	}

	@Override
	public boolean getCanUndo() {
		return changeQueue.hasPrev();
	}

	@Override
	public ReadOnlyBooleanProperty canRedoProperty() {
		return changeQueue.hasNextProperty();
	}

	@Override
	public boolean getCanRedo() {
		return changeQueue.hasNext();
	}

	@Override
	public void startChangeGruop() {
		changeGroupStack.push(new ChangeGroup());

	}

	@Override
	public void applyChangeGruop() {
		if (changeGroupStack.size() > 0) {
			ChangeGroup currentChangeGroup = changeGroupStack.pop();
			addChange(currentChangeGroup);
		}
	}

	@Override
	public void cancelChangeGruop() {
		changeGroupStack.pop();
	}

}
