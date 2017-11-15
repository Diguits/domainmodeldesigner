package com.diguits.javafx.container.controllers;

import com.diguits.javafx.container.views.HoleView;
import com.google.inject.Inject;

public class HoleController extends HoleControllerBase<HoleView> {

	@Inject
	public HoleController(HoleView view) {
		super(view);
	}

}
