package com.diguits.javafx.model;

import javafx.collections.ObservableList;

public interface IHierarchialModel<TModel> {
	ObservableList<TModel> getChildren();

	boolean isLeaf();
}
