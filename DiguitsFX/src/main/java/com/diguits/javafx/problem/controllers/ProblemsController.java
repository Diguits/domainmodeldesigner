package com.diguits.javafx.problem.controllers;

import java.util.List;
import java.util.UUID;

import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.problem.controllers.ProblemItem.Kind;
import com.diguits.javafx.problem.views.ProblemsView;
import com.diguits.javafx.container.controllers.DecoratedContainerController;
import com.diguits.javafx.container.controllers.MainController;
import com.google.inject.Inject;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ProblemsController extends DecoratedContainerController<ProblemsView, ProblemItem> {

	@Inject
	MainController mainController;

	private ListProperty<ProblemItem> items;

	@Inject
	public ProblemsController(ProblemsView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		TableView<ProblemItem> problemItems = getView().getProblemItems();
		problemItems.itemsProperty().bind(itemsProperty());
		problemItems.focusedProperty().addListener((v, o, n) -> {
			if (!n)
				problemItems.getSelectionModel().clearSelection();
		});
		problemItems.setOnMouseClicked(e -> {
			ProblemItem selectedItem = problemItems.getSelectionModel().getSelectedItem();
			if (e.getClickCount() == 2 && selectedItem != null && selectedItem.getPosition() != null) {
				mainController.goToPosition(selectedItem.getPosition());
			}
		});

	}

	public ObservableList<ProblemItem> getItems() {
		return itemsProperty().get();
	}

	public void setItems(ObservableList<ProblemItem> items) {
		if (this.items != null || items != null) {
			itemsProperty().set(items);
		}
	}

	public ListProperty<ProblemItem> itemsProperty() {
		if (items == null) {
			items = new SimpleListProperty<ProblemItem>(this, "items", null);
			items.set(FXCollections.observableArrayList());
		}
		return items;
	}

	public void addProblem(Kind kind, String message, Position position) {
		getItems().add(new ProblemItem(kind, message, position));
	}

	@Override
	protected NamedModelBase findModel(UUID modelId) {
		return null;
	}

	@Override
	protected List<Class<?>> getModelClasses() {
		return null;
	}

	public void clear() {
		items.clear();
	}
}
