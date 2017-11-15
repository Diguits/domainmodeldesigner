package com.diguits.javafx.container.controllers;

import com.diguits.javafx.model.ModelBase;
import com.diguits.javafx.container.decorators.IContainerDecorator;
import com.diguits.javafx.container.views.IContainerView;

public interface IDecoratedContainerController<TView extends IContainerView<?, TModel>, TModel extends ModelBase>
		extends IContainerController<TView, TModel> {
	void setDecorator(IContainerDecorator<TModel, ?> decorator);

	IContainerDecorator<TModel, ?> getDecorator();
}