package com.diguits.javafx.settings.views;

import com.diguits.javafx.settings.controllers.SettingsItem;
import com.diguits.javafx.views.ViewBase;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

public class SettingsView extends ViewBase<SplitPane> {

	private TreeView<SettingsItem> settingsTree;
	private AnchorPane selectedSettingContainer;

	@Override
	protected SplitPane buildView() {
		SplitPane splitPane = new SplitPane();
		splitPane.setDividerPositions(0.95);
		TreeView<SettingsItem> treeView = new TreeView<SettingsItem>();
		settingsTree = treeView;
		AnchorPane anchorPane = new AnchorPane();
		selectedSettingContainer = anchorPane;
		splitPane.getItems().addAll(treeView, anchorPane);
		return splitPane;
	}

	public AnchorPane getSelectedSettingContainer() {
		return selectedSettingContainer;
	}

	public TreeView<SettingsItem> getSettingsTree() {
		return settingsTree;
	}

}
