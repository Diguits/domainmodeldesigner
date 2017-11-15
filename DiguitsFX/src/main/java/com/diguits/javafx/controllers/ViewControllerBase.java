package com.diguits.javafx.controllers;

import java.util.ResourceBundle;

import com.diguits.javafx.views.IView;
import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableView;

public abstract class ViewControllerBase<TView extends IView<?>> extends ControllerBase
		implements IViewController<TView> {

	protected ResourceBundle resources;

	protected ObjectProperty<TView> view;

	protected ObjectProperty<Dialog<ButtonType>> dialogContainer;

	protected String getBundleName() {
		return getClass().getSimpleName().replace("Controller", "Bundle");
	}

	@Inject
	public ViewControllerBase(TView view) {
		super();
		resources = createResourceBundle();
		view.setResource(resources);
		viewProperty().addListener((v, o, n) -> {
			if (n != null) {
				n.setDialogContainer(getDialogContainer());
			}
			viewUpdated(o, n);
		});
		setView(view);
	}

	protected void viewUpdated(TView o, TView n) {

	}

	@Override
	public void initialize() {
		super.initialize();
		initializeView();
	}

	protected void initializeView() {
		getView().initialize();
		dialogContainerProperty().addListener((v, o, n) -> {
			if (getView() != null) {
				getView().setDialogContainer(n);
			}
		});
	}

	private ResourceBundle createResourceBundle() {
		return ResourceBundle.getBundle("bundles/" + getBundleName());
	}

	@Override
	public TView getView() {
		if (view != null) {
			if (!getInitialized())
				initialize();
			return view.get();
		}
		return null;
	}

	public void setView(TView view) {
		if (this.view != null || view != null) {
			viewProperty().set(view);
		}
	}

	public ObjectProperty<TView> viewProperty() {
		if (view == null) {
			view = new SimpleObjectProperty<TView>(this, "view", null);
		}
		return view;
	}

	protected <M> void up(TableView<M> tableView, ObservableList<M> list) {
		up(list, tableView.getSelectionModel());
	}

	protected <M> void up(ListView<M> listView, ObservableList<M> list) {
		up(list, listView.getSelectionModel());
	}

	protected <M> void up(ObservableList<M> list, MultipleSelectionModel<M> selectionModel) {
		int index = selectionModel.getSelectedIndex();
		if (index > 0) {
			M model = list.get(index);
			list.set(index, list.get(index - 1));
			list.set(index - 1, model);
			selectionModel.select(index - 1);
		}
	}

	protected <M> void down(TableView<M> tableView, ObservableList<M> list) {
		down(list, tableView.getSelectionModel());
	}

	protected <M> void down(ListView<M> listView, ObservableList<M> list) {
		down(list, listView.getSelectionModel());
	}

	protected <M> void down(ObservableList<M> list, MultipleSelectionModel<M> selectionModel) {
		int index = selectionModel.getSelectedIndex();
		if (index < list.size() - 1) {
			M model = list.get(index);
			list.set(index, list.get(index + 1));
			list.set(index + 1, model);
			selectionModel.select(index + 1);
		}
	}

	@Override
	public Dialog<ButtonType> getDialogContainer() {
		if (dialogContainer != null) {
			if (!getInitialized())
				initialize();
			return dialogContainer.get();
		}
		return null;
	}

	@Override
	public void setDialogContainer(Dialog<ButtonType> dialogContainer) {
		if (this.dialogContainer != null || dialogContainer != null) {
			dialogContainerProperty().set(dialogContainer);
		}
	}

	public ObjectProperty<Dialog<ButtonType>> dialogContainerProperty() {
		if (dialogContainer == null) {
			dialogContainer = new SimpleObjectProperty<Dialog<ButtonType>>(this, "dialogContainer", null);
		}
		return dialogContainer;
	}

}
