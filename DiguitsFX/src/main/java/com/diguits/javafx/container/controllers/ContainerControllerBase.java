package com.diguits.javafx.container.controllers;

import java.util.List;
import java.util.UUID;

import com.diguits.javafx.model.ModelBase;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.container.views.IContainerView;
import com.diguits.javafx.controllers.ModelViewControllerBase;
import com.diguits.javafx.controls.AlertHelper;
import com.google.inject.Inject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;

public abstract class ContainerControllerBase<TView extends IContainerView<?, TModel>, TModel extends ModelBase>
		extends ModelViewControllerBase<TView, TModel> implements IContainerController<TView, TModel> {

	@Inject
	protected EditorsHoleController editorsController;

	@Inject
	protected AlertHelper alertHelper;

	@Inject
	protected GlobalSelectionManager globalSelectionManager;

	protected ContainerPos position;
	protected StringProperty title;
	protected BooleanProperty focused;
	protected ObjectProperty<TModel> selectedModel;
	protected ObjectProperty<TreeItem<?>> selectedTreeItem;

	protected ReadOnlyObjectProperty<Class<? extends ModelBase>> selectedModelClass;

	public ContainerControllerBase(TView view) {
		super(view);
		view.setUseToolBar(useToolBar());

		setPosition(getDefaultPosition());
		selectedModelProperty().addListener(new ChangeListener<TModel>() {

			@Override
			public void changed(ObservableValue<? extends TModel> observable, TModel oldValue, TModel newValue) {
				if (newValue != null)
					setSelectedModelClass(newValue.getClass());
				else
					setSelectedModelClass(null);
			}
		});
	}

	@Override
	public void initialize() {
		super.initialize();
		((BooleanProperty) focusedProperty()).bind(selectedModelProperty().isEqualTo(globalSelectedModelProperty()));
	}
	/*
	 * @FXML protected void initialize() { if (getDecorator() != null) { String
	 * titleResource = getTitleResource(); if (titleResource != null &&
	 * !titleResource.trim().isEmpty())
	 * setTitle(resources.getString(titleResource)); }
	 *
	 * ((BooleanProperty)
	 * focusedProperty()).bind(selectedModelProperty().isEqualTo(
	 * globalSelectedModelProperty())); }
	 */

	public enum ContainerPos {
		RIGTH, LEFT, BOTTOM, CENTER
	}

	protected ContainerPos getDefaultPosition() {
		return ContainerPos.BOTTOM;
	}

	protected boolean useToolBar() {
		return true;
	}

	@Override
	public String getTitle() {
		if (title != null)
			return title.get();
		return "";
	}

	protected void setTitle(String title) {
		if (this.title != null || title != null) {
			titleProperty().set(title);
		}
	}

	@Override
	public StringProperty titleProperty() {
		if (title == null) {
			title = new SimpleStringProperty(this, "title", "");
		}
		return title;
	}

	@Override
	public TModel getSelectedModel() {
		if (selectedModel != null)
			return selectedModel.get();
		return null;
	}

	@Override
	public void setSelectedModel(TModel model) {
		if (this.selectedModel != null || model != null) {
			selectedModelProperty().set(model);
		}
	}

	@Override
	public ObjectProperty<TModel> selectedModelProperty() {
		if (selectedModel == null) {
			selectedModel = new SimpleObjectProperty<TModel>(this, "selectedModel", null);
		}
		return selectedModel;
	}

	@Override
	public TreeItem<?> getSelectedTreeItem() {
		if (selectedTreeItem != null)
			return selectedTreeItem.get();
		return null;
	}

	@Override
	public void setSelectedTreeItem(TreeItem<?> treeItem) {
		if (this.selectedTreeItem != null || treeItem != null) {
			selectedTreeItemProperty().set(treeItem);
		}
	}

	@Override
	public ObjectProperty<TreeItem<?>> selectedTreeItemProperty() {
		if (selectedTreeItem == null) {
			selectedTreeItem = new SimpleObjectProperty<TreeItem<?>>(this, "selectedTreeItem", null);
		}
		return selectedTreeItem;
	}

	public boolean allowTreeItemSelecion() {
		return false;
	}

	@Override
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

	@Override
	public ReadOnlyObjectProperty<Class<? extends ModelBase>> selectedModelClassProperty() {
		if (selectedModelClass == null) {
			selectedModelClass = new SimpleObjectProperty<Class<? extends ModelBase>>(this, "selectedItemClass", null);
		}
		return selectedModelClass;
	}

	@Override
	public boolean getFocused() {
		if (focused != null)
			return focused.get();
		return false;
	}

	@Override
	public ReadOnlyBooleanProperty focusedProperty() {
		if (focused == null) {
			focused = new SimpleBooleanProperty(this, "focused", false);
		}
		return focused;
	}

	public ModelBase getGlobalSelectedModel() {
		return globalSelectionManager.getSelectedModel();
	}

	public void setGlobalSelectedModel(ModelBase model) {
		globalSelectionManager.select(model);
	}

	public void setGlobalSelection(ModelBase model, TreeItem<?> treeItem) {
		globalSelectionManager.select(model, treeItem);
	}

	public ObjectProperty<ModelBase> globalSelectedModelProperty() {
		return globalSelectionManager.selectedModelProperty();
	}

	public Class<? extends ModelBase> getGlobalSelectedModelClass() {
		return globalSelectionManager.getSelectedModelClass();
	}

	public ReadOnlyObjectProperty<Class<? extends ModelBase>> globalSelectedModelClassProperty() {
		return globalSelectionManager.selectedModelClassProperty();
	}

	@Override
	public ContainerPos getPosition() {
		return position;
	}

	@Override
	public void setPosition(ContainerPos position) {
		this.position = position;
	}

	@Override
	public void showEditor(NamedModelBase model) {
		editorsController.showEditor(model, null);
	}

	@Override
	public void showEditor(NamedModelBase model, TreeItem<TModel> treeItem) {
		editorsController.showEditor(model, (TreeItem<?>) treeItem);
	}

	@Override
	public void hideEditor(TModel model) {
		if (model != null)
			editorsController.hideEditor(model.getId());
	}

	public boolean goToPosition(Position position) {
		if (position != null) {
			List<Class<?>> modelClasses = getModelClasses();
			if (modelClasses != null
					&& modelClasses.stream().anyMatch(c -> c.getName().equals(position.getModelClass()))) {
				NamedModelBase model = findModel(position.getModelId());
				if (model != null) {
					editorsController.goToPostion(model, position);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getModelTypeName() {
		return getModel() != null ? getModel().getClass().getSimpleName().replace("DefModel", "") : "";
	}

	protected abstract NamedModelBase findModel(UUID modelId);

	protected abstract List<Class<?>> getModelClasses();

	@Override
	public String toString() {
		return getTitle();
	}
}
