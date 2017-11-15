package com.diguits.javafx.container.controllers;

import java.util.Optional;
import java.util.ResourceBundle;

import com.diguits.javafx.model.NamedModelBase;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

public abstract class TreeBuilder<TModelTree, TModel extends NamedModelBase>
		implements ITreeBuilder<TModelTree, TModel> {

	protected ResourceBundle resources;

	public TreeBuilder() {
		super();
		resources = createResourceBundle();

	}

	private ResourceBundle createResourceBundle() {
		return ResourceBundle.getBundle("bundles/" + getBundleName());
	}

	protected abstract String getBundleName();

	public abstract void fillTree(TModelTree model, TreeView<TModel> treeView,
			ITreeContainerController<?, TModel> container);

	protected TreeItem<TModel> addModel(TreeView<TModel> treeView, TreeItem<TModel> item, TModel model,
			ITreeContainerController<?, TModel> container) {
		ImageView imageForModel = container.getDecorator().getImageForModel(model);
		return addModel(treeView, item, model, imageForModel);
	}

	protected TreeItem<TModel> addModel(TreeView<TModel> treeView, TreeItem<TModel> item, TModel model,
			ImageView imageForModel) {
		TreeItem<TModel> modelItem = createModelItem(model, treeView, imageForModel);
		item.getChildren().add(modelItem);
		treeView.getSelectionModel().select(modelItem);
		return modelItem;
	}

	protected TreeItem<TModel> createModelItem(TModel model, TreeView<TModel> treeView, Node imageForModel) {
		CustomTreeItem<TModel> modelItem = new CustomTreeItem<TModel>(treeView);
		modelItem.setValue(model);
		modelItem.setGraphic(imageForModel);
		modelItem.setExpanded(true);
		return modelItem;
	}

	protected void removeModel(TreeItem<TModel> item, TModel model) {
		if (item != null) {
			TreeItem<TModel> modelItem = findItem(item, model);
			if (modelItem != null)
				item.getChildren().remove(modelItem);
		}
	}

	protected TreeItem<TModel> findItem(TreeItem<TModel> item, TModel model) {
		Optional<TreeItem<TModel>> items = item.getChildren().stream().filter(i -> i.getValue() == model).findAny();
		if (items.isPresent())
			return items.get();
		return null;
	}

	protected ImageView getImage(String imageName) {
		try {
			return new ImageView("/images/" + imageName + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
