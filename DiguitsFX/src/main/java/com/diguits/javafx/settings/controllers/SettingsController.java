package com.diguits.javafx.settings.controllers;

import java.util.List;

import com.diguits.javafx.container.controllers.CustomTreeItem;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.diguits.javafx.settings.views.SettingsView;
import com.google.inject.Inject;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;

public class SettingsController extends ViewControllerBase<SettingsView> {

	public SettingsController(SettingsView view) {
		super(view);
	}

	@Inject
	ISettingsService settingsService;

	@Override
	public void initialize() {
		Settings settings = settingsService.getSettings();
		buildTree(settings);
		getView().getSettingsTree().setCellFactory((TreeView<SettingsItem> tv) -> {
			TextFieldTreeCell<SettingsItem> treeCell = new TextFieldTreeCell<SettingsItem>() {

			};
			treeCell.setOnMouseClicked((MouseEvent event) -> {
				if (event.getClickCount() >= 2) {
					openSettingForSelectedModel();
				}
			});
			return treeCell;
		});
	}

	private void openSettingForSelectedModel() {
		TreeItem<SettingsItem> selectedItem = getView().getSettingsTree().getSelectionModel().getSelectedItem();
		if (selectedItem != null && selectedItem.getValue() != null
				&& selectedItem.getValue().getController() != null) {
			ISettingItemController<?> controller = selectedItem.getValue().getController();
			if (controller.getView() != null) {
				getView().getSelectedSettingContainer().getChildren().clear();
				getView().getSelectedSettingContainer().getChildren().add(controller.getView().getNodeView());

			}
		}
	}

	private void buildTree(Settings settings) {
		TreeItem<SettingsItem> root = new CustomTreeItem<SettingsItem>(getView().getSettingsTree());
		List<SettingsItem> children = settings.getChildren();
		TreeItem<SettingsItem> parent = root;
		buildChildrenR(children, parent);
		getView().getSettingsTree().setRoot(root);
	}

	private void buildChildrenR(List<SettingsItem> children, TreeItem<SettingsItem> parent) {
		for (SettingsItem setting : children) {
			CustomTreeItem<SettingsItem> childItem = new CustomTreeItem<SettingsItem>(getView().getSettingsTree());
			childItem.setValue(setting);
			buildChildrenR(setting.getChildren(), childItem);
			parent.getChildren().add(childItem);
		}
	}

	@Override
	protected String getBundleName() {
		return "SettingsBundle";
	}
}
