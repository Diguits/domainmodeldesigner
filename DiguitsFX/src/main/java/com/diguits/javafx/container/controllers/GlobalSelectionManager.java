package com.diguits.javafx.container.controllers;

import com.diguits.javafx.model.ModelBase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;

public class GlobalSelectionManager {

	private ObjectProperty<ModelBase> selectedModel;
	private ObjectProperty<TreeItem<?>> selectedTreeItem;
	private ReadOnlyObjectProperty<Class<? extends ModelBase>> selectedModelClass;
	private ObjectProperty<HoleControllerBase<?>> selectedHoleController;

	public GlobalSelectionManager() {
		super();
		selectedModelProperty().addListener(new ChangeListener<ModelBase>() {

			@Override
			public void changed(ObservableValue<? extends ModelBase> observable, ModelBase oldValue,
					ModelBase newValue) {
				if (newValue != null)
					setSelectedModelClass(newValue.getClass());
				else
					setSelectedModelClass(null);
			}
		});
	}

	public TreeItem<?> getSelectedTreeItem() {
		if (selectedTreeItem != null)
			return selectedTreeItem.get();
		return null;
	}

	public void setSelectedTreeItem(TreeItem<?> treeItem) {
		if (this.selectedTreeItem != null || treeItem != null) {
			selectedTreeItemProperty().set(treeItem);
		}
	}

	public ObjectProperty<TreeItem<?>> selectedTreeItemProperty() {
		if (selectedTreeItem == null) {
			selectedTreeItem = new SimpleObjectProperty<TreeItem<?>>(this, "selectedTreeItem", null);
		}
		return selectedTreeItem;
	}

	public ModelBase getSelectedModel() {
		if (selectedModel != null)
			return selectedModel.get();
		return null;
	}

	public void setSelectedModel(ModelBase model) {
		if (this.selectedModel != null || model != null) {
			selectedModelProperty().set(model);
		}
	}

	public ObjectProperty<ModelBase> selectedModelProperty() {
		if (selectedModel == null) {
			selectedModel = new SimpleObjectProperty<ModelBase>(this, "selectedModel", null);
		}
		return selectedModel;
	}

	public HoleControllerBase<?> getSelectedHoleController() {
		if (selectedHoleController != null)
			return selectedHoleController.get();
		return null;
	}

	public void setSelectedHoleController(HoleControllerBase<?> model) {
		if (this.selectedHoleController != null || model != null) {
			selectedHoleControllerProperty().set(model);
		}
	}

	public ObjectProperty<HoleControllerBase<?>> selectedHoleControllerProperty() {
		if (selectedHoleController == null) {
			selectedHoleController = new SimpleObjectProperty<HoleControllerBase<?>>(this, "selectedHoleController", null);
		}
		return selectedHoleController;
	}

	public void select(ModelBase model) {
		setSelectedModel(model);
	}

	public void select(ModelBase model, TreeItem<?> treeItem) {
		setSelectedModel(model);
		setSelectedTreeItem(treeItem);
	}

	public Class<? extends ModelBase> getSelectedModelClass() {
		if (selectedModelClass != null)
			return selectedModelClass.get();
		return null;
	}

	private void setSelectedModelClass(Class<? extends ModelBase> class1) {
		if (this.selectedModelClass != null || class1 != null) {
			((SimpleObjectProperty<Class<? extends ModelBase>>) selectedModelClassProperty()).set(class1);
		}
	}

	public ReadOnlyObjectProperty<Class<? extends ModelBase>> selectedModelClassProperty() {
		if (selectedModelClass == null) {
			selectedModelClass = new SimpleObjectProperty<Class<? extends ModelBase>>(this, "selectedItemClass", null);
		}
		return selectedModelClass;
	}
}
