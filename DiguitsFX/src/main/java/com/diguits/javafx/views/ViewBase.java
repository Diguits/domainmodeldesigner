package com.diguits.javafx.views;

import java.util.ResourceBundle;

import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;

public abstract class ViewBase<TNode extends Node> implements IView<TNode> {

	protected TNode nodeView;
	protected ResourceBundle resources;
	protected ObjectProperty<Dialog<ButtonType>> dialogContainer;

	@Inject
	protected INodeFactoryHelper nodeFactory;

	@Override
	public TNode getNodeView() {
		initialize();
		return nodeView;
	}

	protected void SetNodeView(TNode nodeView) {
		this.nodeView = nodeView;
	}

	protected abstract TNode buildView();

	@Override
	public void initialize() {
		if (nodeView == null)
			nodeView = buildView();
	}

	protected void fillToParentAnchor(Node node) {
		AnchorPane.setRightAnchor(node, 0.0);
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
	}

	@Override
	public void setResource(ResourceBundle resources) {
		this.resources = resources;
		nodeFactory.setResource(resources);
	}

	@Override
	public Dialog<ButtonType> getDialogContainer() {
		if (dialogContainer != null) {
			return dialogContainer.get();
		}
		return null;
	}

	@Override
	public void setDialogContainer(Dialog<ButtonType> dialogContainer) {
		if (this.dialogContainer != null || dialogContainer != null) {
			dialogContainerProperty().set(dialogContainer);
		}
	}

	public ObjectProperty<Dialog<ButtonType>> dialogContainerProperty() {
		if (dialogContainer == null) {
			dialogContainer = new SimpleObjectProperty<Dialog<ButtonType>>(this, "dialogContainer", null);
		}
		return dialogContainer;
	}

}
