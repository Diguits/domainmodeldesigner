package com.diguits.javafx.undo.impl;

import com.diguits.javafx.undo.changes.Change;

public class FixedSizeChangeQueue extends ChangeQueueBase {

	private int maxChangesCount;

	public FixedSizeChangeQueue(int maxChangesCount) {
		super();
		if (maxChangesCount <= 0)
			throw new IllegalArgumentException("maxChangesCount");
		this.maxChangesCount = maxChangesCount;
	}

	@Override
	public void push(Change change) {
		super.push(change);
		if (prevStack.size() > maxChangesCount)
			prevStack.remove(prevStack.lastElement());
	}
}
