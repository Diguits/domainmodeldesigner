package com.diguits.javafx.container.decorators;

import java.util.List;

public interface IDecoratorProvider {
	public List<Class<? extends IDecorator>> getDecorators();
}
