package com.diguits.javafx.container.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class CustomTreeItem<T> extends TreeItem<T> {

	public CustomTreeItem(TreeView<T> treeView) {
		super();
		setTreeView(treeView);
	}

	public CustomTreeItem(TreeView<T> treeView, T value) {
		super(value);
		setTreeView(treeView);
	}
	private ObjectProperty<TreeView<T>> treeView;

	public TreeView<T> getTreeView() {
		if (treeView != null)
			return treeView.get();
		return null;
	}

	public void setTreeView(TreeView<T> treeView) {
		if (this.treeView != null || treeView != null) {
			treeViewProperty().set(treeView);
		}
	}

	public ObjectProperty<TreeView<T>> treeViewProperty() {
		if (treeView == null) {
			treeView = new SimpleObjectProperty<TreeView<T>>(this, "treeView", null);
		}
		return treeView;
	}

}
