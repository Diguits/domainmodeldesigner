package com.diguits.javafx.undo.changes;

import java.util.ArrayList;
import java.util.List;

public class ChangeGroup implements Change{

	List<Change> changes;

	public ChangeGroup() {
		super();
		changes = new ArrayList<Change>();
	}

	public void appenChange(Change change){
		changes.add(change);
	}

	@Override
	public boolean undo() {
		boolean result = true;
		for (int i = changes.size()-1; i >=0; i--) {
			result = result & changes.get(i).undo();
		}
		return result;
	}

	@Override
	public boolean redo() {
		boolean result = true;
		for (int i = 0; i < changes.size()-1; i++) {
			result = result & changes.get(i).redo();
		}
		return result;
	}

	@Override
	public boolean merge(Change change) {
		return false;
	}

	@Override
	public boolean undone(Change change) {
		return false;
	}

}
