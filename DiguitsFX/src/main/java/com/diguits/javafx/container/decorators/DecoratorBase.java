package com.diguits.javafx.container.decorators;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class DecoratorBase implements IDecorator {

	protected ResourceBundle resources;
	protected List<OptionBase> mainToolBarOptions;
	protected List<OptionBase> mainMenuOptions;

	protected abstract String getMenuBundleName();

	public DecoratorBase() {
		super();
		resources = createResourceBundle();
	}

	public void Initialize() {
		createActions();
	};

	protected abstract void createActions();

	private ResourceBundle createResourceBundle() {
		return ResourceBundle.getBundle("bundles/" + getMenuBundleName());
	}

	@Override
	public final List<OptionBase> getMainToolBarOptions() {
		if (mainToolBarOptions == null) {
			mainToolBarOptions = new ArrayList<OptionBase>();
			buildMainToolBarOptions(new OptionListBilder(mainToolBarOptions));
		}
		return mainToolBarOptions;
	}

	@Override
	public final List<OptionBase> getMainMenuOptions() {
		if (mainMenuOptions == null) {
			mainMenuOptions = new ArrayList<OptionBase>();
			buildMainMenuOptions(new OptionListBilder(mainMenuOptions));
		}
		return mainMenuOptions;
	}

	protected abstract void buildMainToolBarOptions(OptionListBilder builder);

	protected abstract void buildMainMenuOptions(OptionListBilder builder);

}
