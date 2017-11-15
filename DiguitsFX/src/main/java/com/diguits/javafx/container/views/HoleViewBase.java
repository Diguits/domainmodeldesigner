package com.diguits.javafx.container.views;

import com.diguits.javafx.container.controllers.IContainerController;
import com.diguits.javafx.views.ViewBase;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;

public class HoleViewBase<TNode extends Node> extends ViewBase<TNode> {

	protected TabPane tabPane;

	@Override
	protected TNode buildView() {
		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		return null;
	}

	public Tab addTab(StringProperty title, ImageView image, Node view, IContainerController<?, ?> container) {
		Tab tab = new Tab();
		tab.setClosable(true);
		tab.textProperty().bind(title);
		if (image != null)
			tab.setGraphic(image);

		ScrollPane scrollPane = new ScrollPane(nodeFactory.addAndFitToAnchorPane(view));
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		tab.setContent(scrollPane);

		tab.setUserData(container);

		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);
		return tab;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}
}
