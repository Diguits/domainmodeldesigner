package com.diguits.domainmodeldesigner.templateapplyconfig.controllers;

import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigItemModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.container.controllers.CustomTreeItem;
import com.diguits.javafx.container.controllers.ITreeContainerController;
import com.diguits.javafx.container.controllers.TreeBuilder;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

public class TemplateApplyConfigTreeBuilder extends TreeBuilder<ObservableList<TemplateProjectApplyConfigModel>, TemplateProjectApplyConfigItemModel>{

	@Override
	protected String getBundleName() {
		return "TemplateApplyConfigTreeBundle";
	}

	@Override
	public void fillTree(ObservableList<TemplateProjectApplyConfigModel> configs,
			TreeView<TemplateProjectApplyConfigItemModel> treeView,
			ITreeContainerController<?, TemplateProjectApplyConfigItemModel> container) {
		if (configs != null && treeView != null) {
			configs.addListener(new ListChangeListener<TemplateProjectApplyConfigModel>() {

				@Override
				public void onChanged(Change<? extends TemplateProjectApplyConfigModel> c) {
					if (c.next()) {
						for (TemplateProjectApplyConfigModel config : c.getAddedSubList()) {
							TreeItem<TemplateProjectApplyConfigItemModel> item = addTemplateProjectApplyConfig(treeView, config, container);
							container.showEditor(config, item);
						}
						for (TemplateProjectApplyConfigModel config : c.getRemoved()) {
							removeTemplateProjectApplyConfig(treeView, config);
							container.hideEditor(config);
						}
					}
				}
			});
			CustomTreeItem<TemplateProjectApplyConfigItemModel> root = new CustomTreeItem<TemplateProjectApplyConfigItemModel>(treeView);
			TemplateProjectApplyConfigItemModel rootModel = new TemplateProjectApplyConfigItemModel();
			rootModel.setName(resources.getString("apply_configs"));
			root.setValue(rootModel);
			root.setGraphic(new ImageView("images/folder_clipboard.png"));
			root.setExpanded(true);
			for (TemplateProjectApplyConfigModel config : configs) {
				addTemplateProjectApplyConfig(treeView, root, config, container);
			}
			treeView.setRoot(root);
			treeView.setShowRoot(false);
		}

	}

	private void removeTemplateProjectApplyConfig(TreeView<TemplateProjectApplyConfigItemModel> treeView, TemplateProjectApplyConfigModel project) {
		removeModel(treeView.getRoot(), project);
	}

	private TreeItem<TemplateProjectApplyConfigItemModel> addTemplateProjectApplyConfig(TreeView<TemplateProjectApplyConfigItemModel> treeView, TemplateProjectApplyConfigModel config, ITreeContainerController<?, TemplateProjectApplyConfigItemModel> container) {
		return addTemplateProjectApplyConfig(treeView, treeView.getRoot(), config, container);
	}

	private TreeItem<TemplateProjectApplyConfigItemModel> addTemplateProjectApplyConfig(TreeView<TemplateProjectApplyConfigItemModel> treeView, TreeItem<TemplateProjectApplyConfigItemModel> root,
			TemplateProjectApplyConfigModel config, ITreeContainerController<?, TemplateProjectApplyConfigItemModel> container) {
		TreeItem<TemplateProjectApplyConfigItemModel> configItem = addModel(treeView, root, config, container);
		config.isDefaultProperty().addListener((v, o, n)->{
			ImageView imageForModel = container.getDecorator().getImageForModel(config);
			configItem.setGraphic(imageForModel);
		});
		for (TemplateApplyConfigModel templateConfig : config.getTemplatesConfig()) {
			addTemplateApplyConfig(treeView, configItem, templateConfig, container);
		}
		config.getTemplatesConfig().addListener(new ListChangeListener<TemplateApplyConfigModel>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TemplateApplyConfigModel> c) {
				if (c.next()) {
					for (TemplateApplyConfigModel tc : c.getAddedSubList()) {
						TreeItem<TemplateProjectApplyConfigItemModel> item = addTemplateApplyConfig(treeView, tc, config, container);
						container.showEditor(tc, item);
					}
					for (TemplateApplyConfigModel tc : c.getRemoved()) {
						removeTemplateApplyConfig(treeView, tc, config);
						container.hideEditor(tc);
					}
				}
			}

		});
		return configItem;
	}

	private void removeTemplateApplyConfig(TreeView<TemplateProjectApplyConfigItemModel> treeView, TemplateApplyConfigModel group, TemplateProjectApplyConfigModel config) {
		TreeItem<TemplateProjectApplyConfigItemModel> configItem = findItem(treeView.getRoot(), config);
		removeModel(configItem, group);
	}

	private TreeItem<TemplateProjectApplyConfigItemModel> addTemplateApplyConfig(TreeView<TemplateProjectApplyConfigItemModel> treeView, TemplateApplyConfigModel templateConfig, TemplateProjectApplyConfigModel config, ITreeContainerController<?, TemplateProjectApplyConfigItemModel> container) {
		TreeItem<TemplateProjectApplyConfigItemModel> configItem = findItem(treeView.getRoot(), config);
		if (configItem != null) {
			return addTemplateApplyConfig(treeView, configItem, templateConfig, container);
		}
		return null;
	}

	private TreeItem<TemplateProjectApplyConfigItemModel> addTemplateApplyConfig(TreeView<TemplateProjectApplyConfigItemModel> treeView, TreeItem<TemplateProjectApplyConfigItemModel> configItem,
			TemplateApplyConfigModel templateConfig, ITreeContainerController<?, TemplateProjectApplyConfigItemModel> container) {
		return addModel(treeView, configItem, templateConfig, container);
	}
}
