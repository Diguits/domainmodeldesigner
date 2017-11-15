package com.diguits.javafx.container.controllers;

import java.util.Optional;
import java.util.UUID;

import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.container.views.HoleViewBase;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.diguits.javafx.model.ModelBase;
import com.google.inject.Inject;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;

public class HoleControllerBase<TView extends HoleViewBase<?>> extends ViewControllerBase<TView>
		implements ChangeListener<Node> {

	@Inject
	GlobalSelectionManager globalSelectionManager;

	ListProperty<IContainerController<?, ? extends ModelBase>> containers;

	ListProperty<IContainerController<?, ? extends ModelBase>> orderedContainers;

	@Inject
	public HoleControllerBase(TView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		getView().getNodeView().parentProperty().addListener(this);
		getView().getTabPane().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				updateGlobalSelectedBinding(newValue);
				updateOrderedContainers(newValue);
			}
		});

		getView().getTabPane().getTabs().addListener(new ListChangeListener<Tab>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Tab> c) {
				while (c.next()) {
					for (Tab tab : c.getRemoved()) {
						getContainers().remove(tab.getUserData());
						getOrderedContainers().remove(tab.getUserData());
					}
					for (Tab tab : c.getAddedSubList()) {
						if (tab.getUserData() instanceof IContainerController<?, ?>) {
							getContainers().add((IContainerController<?, ?>) tab.getUserData());
							updateOrderedContainers(tab);
						}
					}
				}
			}

		});
	}

	public IContainerController<?, ?> findContainerByModelId(UUID modelId) {
		Optional<IContainerController<?, ?>> container = getContainers().stream()
				.filter(c -> c.getSelectedModel() != null && c.getSelectedModel().getId() == modelId).findAny();
		if (container.isPresent())
			return container.get();
		return null;
	}

	private void updateOrderedContainers(Tab tab) {
		if (tab != null && tab.getUserData() instanceof IContainerController<?, ?>
				&& (getOrderedContainers().size() == 0
						|| getOrderedContainers().get(0) != ((IContainerController<?, ?>) tab.getUserData()))) {
			if (getOrderedContainers().contains(tab.getUserData())) {
				getOrderedContainers().remove(tab.getUserData());
			}
			if (getOrderedContainers().size() == 0)
				getOrderedContainers().add((IContainerController<?, ?>) tab.getUserData());
			else
				getOrderedContainers().add(0, (IContainerController<?, ?>) tab.getUserData());
		}
	}

	public boolean containsContainer(IContainerController<?, ?> container) {
		return getContainers().stream().anyMatch(c -> c != null && c.equals(container));
	}

	public boolean selectContainer(IContainerController<?, ?> container) {
		if (containsContainer(container)) {
			selectAndCreateTab(container);
			return true;
		}
		return false;
	}

	public boolean addContainer(IContainerController<?, ?> container) {
		if (!getContainers().contains(container)) {
			addContainerToView(container);
			return true;
		} else {
			selectAndCreateTab(container);
			return false;
		}
	}

	private Tab addContainerToView(IContainerController<?, ?> container) {
		return getView().addTab(container.titleProperty(), container.getImage(), container.getView().getNodeView(), container);

	}

	public void selectAndCreateTab(IContainerController<?, ?> container) {
		Tab tab = findTab(container);
		if (tab == null)
			tab = addContainerToView(container);
		if (tab != null)
			getView().getTabPane().getSelectionModel().select(tab);
	}

	public void selectTab(IContainerController<?, ?> container) {
		Tab tab = findTab(container);
		if (tab != null)
			getView().getTabPane().getSelectionModel().select(tab);
	}

	private Tab findTab(IContainerController<?, ?> container) {
		for (Tab tab : getView().getTabPane().getTabs()) {
			if (tab.getUserData() == container)
				return tab;
		}
		return null;
	}

	public void removeContainer(IContainerController<?, ?> container) {
		Tab tab = findTab(container);
		if (tab != null) {
			getView().getTabPane().getTabs().remove(tab);
		}
		containers.remove(container);
		container.terminate();
	}

	public void removeContainer(UUID modelId) {
		IContainerController<?, ?> container = findContainerByModelId(modelId);
		if (container != null) {
			removeContainer(container);
		}
	}

	public void removeAll(){
		getView().getTabPane().getTabs().clear();
		for (IContainerController<?, ? extends ModelBase> container : containers) {
			container.terminate();
		}
		containers.clear();
	}

	public void removeAllOfModelClass(Class<?> clazz){
		int i= containers.size()-1;
		while(i>=0){
			IContainerController<?, ? extends ModelBase> container = containers.get(i);
			if(container.getModel()!=null && clazz.isAssignableFrom(container.getModel().getClass()))
				removeContainer(container);
			i--;
		}
	}

	@Override
	public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
		if (newValue != null) {
			observable.removeListener(this);
			Scene scene = null;
			Node node = newValue;
			Node root = null;
			while (node != null && node.getScene() == null) {
				root = node;
				node = node.getParent();
			}
			if (node != null && node.getScene() != null)
				scene = node.getScene();
			if (scene == null) {
				root.parentProperty().addListener(this);
			} else {
				scene.focusOwnerProperty().addListener(new ChangeListener<Node>() {

					@Override
					public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
						if (newValue != null) {
							Node node = newValue;
							while (node != null && node != getView().getNodeView())
								node = node.getParent();
							setFocused(node == getView().getNodeView());
						}
					}
				});
			}
		}
	}

	protected void setFocused(boolean focused) {
		Tab selectedTab = getView().getTabPane().getSelectionModel().getSelectedItem();
		if (selectedTab != null) {
			if (focused) {
				updateGlobalSelectedBinding(selectedTab);
			}
		}
	}

	private void updateGlobalSelectedBinding(Tab selectedTab) {
		globalSelectionManager.selectedModelProperty().unbind();
		if (selectedTab != null) {
			IContainerController<?, ?> container = (IContainerController<?, ?>) selectedTab.getUserData();
			globalSelectionManager.selectedModelProperty().bind(container.selectedModelProperty());
			if (container.allowTreeItemSelecion())
				globalSelectionManager.selectedTreeItemProperty().bind(container.selectedTreeItemProperty());
		}
		globalSelectionManager.setSelectedHoleController(this);
	}

	public IContainerController<?, ?> getSelectedContainer() {
		Tab selectedTab = getView().getTabPane().getSelectionModel().getSelectedItem();
		if (selectedTab != null)
			return (IContainerController<?, ?>) selectedTab.getUserData();
		return null;
	}

	public boolean goToPosition(Position position) {
		for (IContainerController<?, ?> container : getContainers()) {
			if (container.goToPosition(position))
				return true;
		}
		return false;
	}

	public void selectFirst() {
		if (getView().getTabPane().getTabs().size() > 0)
			getView().getTabPane().getSelectionModel().selectFirst();
	}

	public ObservableList<IContainerController<?, ?>> getContainers() {
		return containersProperty().get();
	}

	public void setContainers(ObservableList<IContainerController<?, ?>> containers) {
		if (this.containers != null || containers != null) {
			containersProperty().set(containers);
		}
	}

	public ListProperty<IContainerController<?, ?>> containersProperty() {
		if (containers == null) {
			containers = new SimpleListProperty<IContainerController<?, ?>>(this, "containers", null);
			containers.set(FXCollections.observableArrayList());
		}
		return containers;
	}

	public ObservableList<IContainerController<?, ?>> getOrderedContainers() {
		return orderedOrderedContainersProperty().get();
	}

	public void setOrderedContainers(ObservableList<IContainerController<?, ?>> orderedOrderedContainers) {
		if (this.orderedContainers != null || orderedOrderedContainers != null) {
			orderedOrderedContainersProperty().set(orderedOrderedContainers);
		}
	}

	public ListProperty<IContainerController<?, ?>> orderedOrderedContainersProperty() {
		if (orderedContainers == null) {
			orderedContainers = new SimpleListProperty<IContainerController<?, ?>>(this, "orderedContainers", null);
			orderedContainers.set(FXCollections.observableArrayList());
		}
		return orderedContainers;
	}
}
