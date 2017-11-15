package com.diguits.javafx.container.decorators;

import java.util.ArrayList;
import java.util.List;

public abstract class DecoratorProviderBase implements IDecoratorProvider {
	private List<Class<? extends IDecorator>> decorators;

	public DecoratorProviderBase() {
		super();
		decorators = new ArrayList<Class<? extends IDecorator>>();
		registerDecorators();
	}

	protected void registerDecorator(Class<? extends IDecorator> decoratorClass) {
		decorators.add(decoratorClass);
	}

	protected abstract void registerDecorators();

	@Override
	public List<Class<? extends IDecorator>> getDecorators() {
		return decorators;
	}
}
