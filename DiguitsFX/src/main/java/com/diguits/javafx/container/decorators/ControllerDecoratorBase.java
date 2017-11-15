package com.diguits.javafx.container.decorators;

import com.diguits.javafx.controllers.IViewController;

public abstract class ControllerDecoratorBase<TController extends IViewController<?>> extends DecoratorBase
		implements IControllerDecorator<TController> {
	protected TController controller;

	public ControllerDecoratorBase(TController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public TController getController() {
		return controller;
	}
}
