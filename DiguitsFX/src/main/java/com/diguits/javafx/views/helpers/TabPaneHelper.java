package com.diguits.javafx.views.helpers;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabPaneHelper {

	public static TabPane createTabPane() {
		return new TabPane();
	}

	public static Tab createTab(String text) {
		Tab tab = new Tab(text);
		tab.setClosable(false);
		return tab;
	}

	public static Tab createAndAddTab(TabPane tabPane, String text) {
		Tab tab = createTab(text);
		tabPane.getTabs().add(tab);
		return tab;
	}
}
