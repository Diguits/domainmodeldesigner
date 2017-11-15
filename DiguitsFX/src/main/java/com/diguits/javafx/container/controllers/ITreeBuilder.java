package com.diguits.javafx.container.controllers;

import com.diguits.javafx.model.NamedModelBase;

import javafx.scene.control.TreeView;

public interface ITreeBuilder<TModelTree, TModel extends NamedModelBase> {
	public abstract void fillTree(TModelTree modelTree, TreeView<TModel> treeView,
			ITreeContainerController<?, TModel> container);

}
