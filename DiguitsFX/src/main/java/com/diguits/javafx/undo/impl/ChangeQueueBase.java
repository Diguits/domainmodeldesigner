package com.diguits.javafx.undo.impl;

import java.util.Stack;

import com.diguits.javafx.undo.ChangeQueue;
import com.diguits.javafx.undo.changes.Change;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ChangeQueueBase implements ChangeQueue {

	protected Stack<Change> prevStack;
	protected Stack<Change> nextStack;
	protected SimpleBooleanProperty hasNext;
	protected SimpleBooleanProperty hasPrev;

	public ChangeQueueBase() {
		super();
		prevStack = new Stack<>();
		nextStack = new Stack<>();
	}

	@Override
	public ReadOnlyBooleanProperty hasNextProperty() {
		return getHasNextProperty();
	}

	private SimpleBooleanProperty getHasNextProperty() {
		if (hasNext == null) {
			hasNext = new SimpleBooleanProperty(this, "hasNext", false);
		}
		return hasNext;
	}

	@Override
	public boolean hasNext() {
		if (hasNext != null)
			return hasNext.get();
		return false;
	}

	private void setHasNext(Boolean value){
		if (this.hasNext != null || value != false) {
			getHasNextProperty().set(value);
		}
	}

	@Override
	public ReadOnlyBooleanProperty hasPrevProperty() {
		return getHasPrevProperty();
	}

	private SimpleBooleanProperty getHasPrevProperty() {
		if (hasPrev == null) {
			hasPrev = new SimpleBooleanProperty(this, "hasPrev", false);
		}
		return hasPrev;
	}

	@Override
	public boolean hasPrev() {
		if (hasPrev != null)
			return hasPrev.get();
		return false;
	}

	private void setHasPrev(Boolean value){
		if (this.hasPrev != null || value != false) {
			getHasPrevProperty().set(value);
		}
	}

	private void updateHasProperties(){
		setHasNext(!nextStack.empty());
		setHasPrev(!prevStack.empty());
	}

	@Override
	public Change next() {
		if (hasNext()) {
			Change nexChange = nextStack.pop();
			prevStack.push(nexChange);
			updateHasProperties();
			return nexChange;
		}
		return null;
	}

	@Override
	public Change prev() {
		if (hasPrev()) {
			Change prevChange = prevStack.pop();
			nextStack.push(prevChange);
			updateHasProperties();
			return prevChange;
		}
		return null;
	}

	@Override
	public Change popPrev() {
		if (hasPrev()) {
			Change prevChange = prevStack.pop();
			updateHasProperties();
			return prevChange;
		}
		return null;
	}

	@Override
	public void push(Change change) {
		nextStack.clear();
		prevStack.push(change);
		updateHasProperties();
	}

	@Override
	public Change getCurrent() {
		return prevStack.lastElement();
	}

	@Override
	public void clean() {
		prevStack.clear();
		nextStack.clear();
		updateHasProperties();
	}
}
