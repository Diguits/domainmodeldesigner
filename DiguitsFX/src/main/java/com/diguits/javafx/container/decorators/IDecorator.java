package com.diguits.javafx.container.decorators;

import java.util.List;

public interface IDecorator {

	List<OptionBase> getMainToolBarOptions();

	List<OptionBase> getMainMenuOptions();

	void Initialize();
}