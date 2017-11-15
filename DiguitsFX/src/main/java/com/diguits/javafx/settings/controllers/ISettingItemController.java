package com.diguits.javafx.settings.controllers;

import java.io.File;

import com.diguits.javafx.controllers.IViewController;
import com.diguits.javafx.views.IView;

public interface ISettingItemController<TView extends IView<?>> extends IViewController<TView> {
	void loadData(File file);

	void saveData(File file);

	<R> R getConfigValue(String fieldName);

	String getViewName();
}
