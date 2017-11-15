package com.diguits.javafx.container.views;

import com.diguits.javafx.model.NamedModelBase;

import javafx.scene.Node;
import javafx.scene.control.TreeView;

public interface ITreeContainerView<TNode extends Node, TModel extends NamedModelBase>
		extends IContainerView<TNode, TModel> {
	TreeView<TModel> getTreeView();

	void renameSelectedModel();

	void collapse();

	void expand();
}