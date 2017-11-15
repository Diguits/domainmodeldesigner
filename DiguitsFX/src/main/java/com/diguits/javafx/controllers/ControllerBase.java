package com.diguits.javafx.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ControllerBase implements IController {

	protected BooleanProperty initialized;

	@Override
	public void initialize() {
		setInitialized(true);
	}

	public boolean getInitialized() {
		if (initialized != null)
			return initialized.get();
		return false;
	}

	public void setInitialized(boolean initialized) {
		if (this.initialized != null || initialized != false) {
			initializedProperty().set(initialized);
		}
	}

	public BooleanProperty initializedProperty() {
		if (initialized == null) {
			initialized = new SimpleBooleanProperty(this, "initialized", false);
		}
		return initialized;
	}

	@Override
	public void terminate(){

	}
}