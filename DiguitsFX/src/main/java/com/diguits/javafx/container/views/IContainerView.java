package com.diguits.javafx.container.views;

import java.util.List;

import com.diguits.javafx.views.IModelView;

import javafx.scene.Node;
import javafx.scene.control.ToolBar;

public interface IContainerView<TNode extends Node, TModel> extends IModelView<TNode, TModel> {
	void setToolBarOptions(List<Node> toolBarOptions);

	ToolBar getToolBar();

	boolean getUseToolBar();

	void setUseToolBar(boolean useToolBar);
}
