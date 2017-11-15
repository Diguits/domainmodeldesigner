package com.diguits.javafx.settings.controllers;

import com.diguits.javafx.controllers.ViewControllerBase;
import com.diguits.javafx.views.IView;

public abstract class SetttingItemControllerBase<TView extends IView<?>> extends ViewControllerBase<TView> {

	public SetttingItemControllerBase(TView view) {
		super(view);
	}
}
