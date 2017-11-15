package com.diguits.javafx.views;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public interface IView<TNode extends Node> {

	TNode getNodeView();

	void initialize();

	void setResource(ResourceBundle resources);

	void setDialogContainer(Dialog<ButtonType> dialog);

	Dialog<ButtonType> getDialogContainer();
}