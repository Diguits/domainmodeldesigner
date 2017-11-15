package com.diguits.javafx.container.controllers;

import com.diguits.javafx.model.NamedModelBase;

import java.util.List;
import java.util.UUID;

import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.undo.UndoManager;
import com.diguits.javafx.container.views.IContainerView;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.IViewController;
import com.diguits.javafx.editor.controllers.IEditorController;
import com.diguits.javafx.editor.controllers.IEditorControllerFactory;
import com.google.inject.Inject;

import javafx.beans.binding.StringExpression;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;

public abstract class EditorControllerBase<TView extends IContainerView<?, TModel>, TModel extends NamedModelBase>
		extends ContainerControllerBase<TView, TModel> implements IEditorController<TView, TModel> {

	@Inject
	IEditorControllerFactory editorFactory;

	@Inject
	UndoManager undoManager;

	public EditorControllerBase(TView view) {
		super(view);
		selectedModelProperty().bind(modelProperty());
	}

	@Override
	protected void modelUpdated(TModel o, TModel n) {
		super.modelUpdated(o, n);
		if (n != null)
			titleProperty().bind(new When(modelProperty().isNotNull().and(n.isDirtyProperty())).then("*").otherwise("")
					.concat(modelProperty().get().nameProperty()));
		else
			titleProperty().unbind();
	}

	protected boolean showEditorDialog(NamedModelBase model) {
		if (model != null) {
			IViewController<?> editor = createAndInitializeEditorToShow(model);
			if (editor != null) {
				StringExpression titleProperty = new SimpleStringProperty(
						"Editing " + model.getClass().getSimpleName() + " definition: ").concat(model.nameProperty());
				undoManager.startChangeGruop();
				boolean dialogResult = DialogHelper.showOKCancelDialog(editor, titleProperty);
				if(dialogResult){
					undoManager.applyChangeGruop();
				} else {
					undoManager.cancelChangeGruop();
				}
				return dialogResult;
			}
		}
		return false;
	}

	protected IViewController<?> createAndInitializeEditorToShow(NamedModelBase model) {
		return (IViewController<?>) editorFactory.create(model);
	}

	protected boolean confirmDelete(NamedModelBase model) {
		if (model != null) {
			String deleteClassName = model.getClass().getSimpleName();
			String deleteModelName = model.getName();
			int index = deleteClassName.indexOf("DefModel");
			if (index > 0)
				deleteClassName = deleteClassName.substring(0, index);
			return confirmDelete(deleteClassName, deleteModelName);
		}
		return false;
	}

	protected <M extends NamedModelBase> M add(M model, ObservableList<M> list) {
		return innerAdd(model, list);
	}

	protected <M extends NamedModelBase> M innerAdd(M model, ObservableList<M> list) {
		if (model != null) {
			if (showEditorDialog(model))
				list.add(model);
		}
		return model;
	}

	protected <M extends NamedModelBase> M edit(TableView<M> tableView, ObservableList<M> list) {
		M model = tableView.getSelectionModel().getSelectedItem();
		return edit(model, list);
	}

	protected <M extends NamedModelBase> M edit(TreeTableView<M> treetableView, ObservableList<M> list) {
		M model = treetableView.getSelectionModel().getSelectedItem().getValue();
		return edit(model, list);
	}

	protected <M extends NamedModelBase> M edit(M model, ObservableList<M> list) {
		if (model != null) {
			showEditorDialog(model);
		}
		return model;
	}

	protected <M extends NamedModelBase> boolean delete(TableView<M> tableView, ObservableList<M> list) {
		MultipleSelectionModel<M> selectionModel = tableView.getSelectionModel();
		return delete(list, selectionModel);
	}

	protected <M extends NamedModelBase> boolean delete(TreeTableView<M> treetableView, ObservableList<M> list) {
		M selectionModel = treetableView.getSelectionModel().getSelectedItem().getValue();
		return delete(selectionModel, list);
	}

	protected <M extends NamedModelBase> boolean delete(ListView<M> listView, ObservableList<M> list) {
		MultipleSelectionModel<M> selectionModel = listView.getSelectionModel();
		return delete(list, selectionModel);
	}

	private <M extends NamedModelBase> boolean delete(ObservableList<M> list,
			MultipleSelectionModel<M> selectionModel) {
		M model = selectionModel.getSelectedItem();
		return delete(model, list);
	}

	protected <M extends NamedModelBase> boolean delete(M model, ObservableList<M> list) {
		boolean confirm = false;
		confirm = confirmDelete(model);
		if (confirm) {
			list.remove(model);
			return true;
		}
		return false;
	}

	protected boolean confirmDelete(String deleteClassName, String deleteModelName) {
		return alertHelper.confirmDelete(deleteClassName, deleteModelName);
	}

	@Override
	protected NamedModelBase findModel(UUID modelId) {
		return null;
	}

	@Override
	protected List<Class<?>> getModelClasses() {
		return null;
	}

	@Override
	public boolean goToPosition(Position position) {
		if (getModel() != null && getModel().getId() == position.getModelId()) {
			// TODO buscar el control que tiene binding con el campo de position
			// y darle el foco
			return true;
		}
		return false;
	}

	@Override
	public boolean allowTreeItemSelecion() {
		return true;
	}

}
