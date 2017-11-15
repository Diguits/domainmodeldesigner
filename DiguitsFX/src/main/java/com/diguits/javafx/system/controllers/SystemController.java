package com.diguits.javafx.system.controllers;

import com.diguits.javafx.controllers.ControllerBase;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SystemController extends ControllerBase {

	BooleanProperty canClose;
	BooleanProperty closing;

	public boolean getCanClose() {
		if (canClose != null)
			return canClose.get();
		return false;
	}

	public void setCanClose(boolean canClose) {
		if (this.canClose != null || canClose != false) {
			canCloseProperty().set(canClose);
		}
	}

	public BooleanProperty canCloseProperty() {
		if (canClose == null) {
			canClose = new SimpleBooleanProperty(this, "canClose", false);
		}
		return canClose;
	}

	public boolean getClosing() {
		if (closing != null)
			return closing.get();
		return false;
	}

	public void setClosing(boolean closing) {
		if (this.closing != null || closing != false) {
			closingProperty().set(closing);
		}
	}

	public BooleanProperty closingProperty() {
		if (closing == null) {
			closing = new SimpleBooleanProperty(this, "closing", false);
		}
		return closing;
	}

	public void exit(){
		setCanClose(true);
		setClosing(true);
		if(getCanClose())
			Platform.exit();
	}
}
