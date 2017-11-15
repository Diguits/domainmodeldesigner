package com.diguits.javafx.container.views;

import javafx.scene.control.TabPane;

public class HoleView extends HoleViewBase<TabPane> {

	@Override
	protected TabPane buildView() {
		super.buildView();
		return tabPane;
	}

}
