package com.diguits.javafx.controllers;

import com.diguits.javafx.views.IView;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public interface IViewController<TView extends IView<?>> extends IController {
	public TView getView();
	void setDialogContainer(Dialog<ButtonType> dialog);
	Dialog<ButtonType> getDialogContainer();
}
