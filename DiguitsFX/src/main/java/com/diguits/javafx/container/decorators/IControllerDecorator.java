package com.diguits.javafx.container.decorators;

import com.diguits.javafx.controllers.IViewController;

public interface IControllerDecorator<TController extends IViewController<?>> extends IDecorator {
	TController getController();
}
