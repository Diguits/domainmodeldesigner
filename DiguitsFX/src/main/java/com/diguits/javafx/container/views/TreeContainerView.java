package com.diguits.javafx.container.views;

import com.diguits.javafx.model.NamedModelBase;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public abstract class TreeContainerView<TModel extends NamedModelBase> extends ContainerView<TModel>
		implements ITreeContainerView<BorderPane, TModel> {

	protected TreeView<TModel> treeView;

	@Override
	public TreeView<TModel> getTreeView() {
		return treeView;
	}

	@Override
	protected Node buildContentView() {
		treeView = new TreeView<>();
		return treeView;
	}

	@Override
	protected void bindFieldsToModel() {

	}

	public void renameSelectedModel() {
		TreeItem<TModel> selectedItem = treeView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			treeView.setEditable(true);
			treeView.edit(selectedItem);
		}
	}

	public void collapse() {
		if (treeView != null) {
			TreeItem<TModel> root = treeView.getRoot();
			if (root != null) {
				for (TreeItem<TModel> item : root.getChildren()) {
					item.setExpanded(false);
				}
			}
		}
	}

	public void expand() {
		if (treeView != null) {
			TreeItem<TModel> root = treeView.getRoot();
			if (root != null) {
				expand(root);
			}
		}
	}

	private void expand(TreeItem<TModel> item) {
		if (item != null) {
			for (TreeItem<TModel> child : item.getChildren()) {
				child.setExpanded(true);
				expand(child);
			}
		}
	}
}
