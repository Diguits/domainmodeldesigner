package com.diguits.javafx.container.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.container.views.EditorsHoleView;
import com.diguits.javafx.editor.controllers.IEditorControllerFactory;
import com.google.inject.Inject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class EditorsHoleController extends HoleControllerBase<EditorsHoleView> {

	ListProperty<NamedModelBase> modelStack;
	ObjectProperty<NamedModelBase> currentModel;
	IntegerProperty currentIndex;

	@Inject
	IEditorControllerFactory editorFactory;

	@Inject
	public EditorsHoleController(EditorsHoleView view) {
		super(view);
		setCurrentIndex(-1);
		currentIndexProperty().addListener((v, o, n) -> {
			Integer newValue = (Integer) n;
			if (newValue >= 0 && newValue < getModelStack().size()) {
				setCurrentModel(getModelStack().get(newValue));
			} else
				setCurrentModel(null);
		});

		getModelStack().addListener(new ListChangeListener<NamedModelBase>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends NamedModelBase> c) {
				setCurrentIndex(c.getList().size() - 1);
			}
		});

		containersProperty().addListener(new ListChangeListener<IContainerController<?, ?>>() {

			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends IContainerController<?, ?>> c) {
				while (c.next()) {
					List<NamedModelBase> modelsToAdd = new ArrayList<NamedModelBase>();
					for (IContainerController<?, ?> containerController : c.getAddedSubList()) {
						if (containerController.getModel() instanceof NamedModelBase) {
							int index = getCurrentIndex();
							if (index < 0 || index >= getModelStack().size()
									|| !getCurrentModel().equals(getModelStack().get(index)))
								modelsToAdd.add((NamedModelBase) containerController.getModel());
						}
					}
					getModelStack().remove(getCurrentIndex() + 1, getModelStack().size());
					getModelStack().addAll(modelsToAdd);
				}
			}
		});

		getView().getTabPane().getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
			if (getCurrentModel() != null && getSelectedContainer() != null
					&& !getCurrentModel().equals(getSelectedContainer().getModel())) {
				int index = getCurrentIndex() - 1;
				if (index < 0 || index >= getModelStack().size()
						|| !getCurrentModel().equals(getModelStack().get(index)))
					getModelStack().remove(getCurrentIndex() + 1, getModelStack().size());
				getModelStack().add((NamedModelBase) getSelectedContainer().getModel());
			}
		});
	}

	@Override
	public void initialize() {
		super.initialize();
		getView().getTabPane().getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
			if (n != null)
				n.setStyle("-fx-border-color: transparent darkgrey darkgrey  transparent;");
		});
	}

	public void showCurrentEditor() {
		if (!getSelectedContainer().getModel().equals(getCurrentModel())
				&& getSelectedContainer().getModel() instanceof NamedModelBase) {
			if (getCurrentModel() != null)
				showEditor(getCurrentModel(), null);
		}
	}

	public void showEditor(NamedModelBase model, TreeItem<?> treeItem) {
		IContainerController<?, ?> editor = findContainerByModelId(model.getId());
		if (editor == null) {
			editor = (IContainerController<?, ?>) editorFactory.create(model);
			if (editor != null && treeItem != null)
				editor.setSelectedTreeItem(treeItem);
			if (editor != null) {
				addContainer(editor);
			}
		} else
			selectAndCreateTab(editor);
		getView().getNodeView().requestFocus();
	}

	public void hideEditor(UUID modelId) {
		removeContainer(modelId);
	}

	public void hideAll() {
		removeAll();
	}

	public void hideAllOfModelClass(Class<?> clazz) {
		removeAllOfModelClass(clazz);
	}

	public void goToPostion(NamedModelBase model, Position position) {
		showEditor(model, null);
		IContainerController<?, ?> selectedContainer = getSelectedContainer();
		if (selectedContainer != null) {
			selectedContainer.goToPosition(position);
		}
	}

	public boolean goFirst() {
		if (getCurrentIndex() > 0) {
			setCurrentIndex(0);
			showCurrentEditor();
			return true;
		}
		return false;
	}

	public boolean back() {
		if (getCurrentIndex() > 0) {
			setCurrentIndex(getCurrentIndex() - 1);
			showCurrentEditor();
			return true;
		}
		return false;
	}

	public boolean forward() {
		if (getCurrentIndex() < getModelStack().size() - 1) {
			setCurrentIndex(getCurrentIndex() + 1);
			showCurrentEditor();
			return true;
		}
		return false;
	}

	public boolean goLast() {
		if (getCurrentIndex() < getModelStack().size() - 1) {
			setCurrentIndex(getModelStack().size() - 1);
			showCurrentEditor();
			return true;
		}
		return false;
	}

	@Override
	public boolean selectContainer(IContainerController<?, ?> container) {
		if (container != null) {
			if (container.getModel() instanceof NamedModelBase){
				this.showEditor((NamedModelBase) container.getModel(), container.getSelectedTreeItem());
				return true;
			}
		}
		return false;
	}

	public ObservableList<NamedModelBase> getModelStack() {
		return modelStackProperty().get();
	}

	public void setModelStack(ObservableList<NamedModelBase> modelStack) {
		if (this.modelStack != null || modelStack != null) {
			modelStackProperty().set(modelStack);
		}
	}

	public ListProperty<NamedModelBase> modelStackProperty() {
		if (modelStack == null) {
			modelStack = new SimpleListProperty<NamedModelBase>(this, "modelStack", null);
			modelStack.set(FXCollections.observableArrayList());
		}
		return modelStack;
	}

	public NamedModelBase getCurrentModel() {
		if (currentModel != null)
			return currentModel.get();
		return null;
	}

	public void setCurrentModel(NamedModelBase currentModel) {
		if (this.currentModel != null || currentModel != null) {
			currentModelProperty().set(currentModel);
		}
	}

	public ObjectProperty<NamedModelBase> currentModelProperty() {
		if (currentModel == null) {
			currentModel = new SimpleObjectProperty<NamedModelBase>(this, "currentModel", null);
		}
		return currentModel;
	}

	public Integer getCurrentIndex() {
		if (currentIndex != null)
			return currentIndex.get();
		return null;
	}

	public void setCurrentIndex(Integer currentIndex) {
		if (this.currentIndex != null || currentIndex != null) {
			currentIndexProperty().set(currentIndex);
		}
	}

	public IntegerProperty currentIndexProperty() {
		if (currentIndex == null) {
			currentIndex = new SimpleIntegerProperty(this, "currentIndex");
		}
		return currentIndex;
	}
}
