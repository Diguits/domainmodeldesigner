package com.diguits.javafx.views;

import javafx.scene.Node;

public interface IModelView<TNode extends Node, TModel> extends IView<TNode> {

	TModel getModel();

	void setModel(TModel model);

	PropertyChangeEventListener getPropertyChangeEventListener();

	void setPropertyChangeEventListener(PropertyChangeEventListener propertyChangeEventListener);
}
