package com.diguits.javafx.log.controllers;

import java.util.List;
import java.util.UUID;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.container.controllers.DecoratedContainerController;
import com.diguits.javafx.log.views.LogView;
import com.google.inject.Inject;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class LogController extends DecoratedContainerController<LogView, LogItem> {

	private ListProperty<LogItem> items;

	@Inject
	public LogController(LogView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		LogControllerAppender.setLogController(this);
		TableView<LogItem> logItems = getView().getLogsTable();
		logItems.itemsProperty().bind(itemsProperty());
		logItems.focusedProperty().addListener((v, o, n) -> {
			if (!n)
				logItems.getSelectionModel().clearSelection();
		});
	}

	public ObservableList<LogItem> getItems() {
		return itemsProperty().get();
	}

	public ListProperty<LogItem> itemsProperty() {
		if (items == null) {
			items = new SimpleListProperty<LogItem>(this, "items", null);
			items.set(FXCollections.observableArrayList());
		}
		return items;
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
		getItems().clear();
	}

	public void log(LogItem logItem) {
		getItems().add(logItem);
		getView().scrollToLast();
	}
}
