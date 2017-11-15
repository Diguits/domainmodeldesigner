package com.diguits.domainmodeldesigner.editors.controllers;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.domainmodeldesigner.editors.views.NamedModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.StringListEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.SwapModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TreeTableModelEditorView;
import com.diguits.javafx.container.controllers.EditorControllerBase;
import com.diguits.javafx.model.IHierarchialModel;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;

public abstract class NamedModelEditorController<TView extends NamedModelEditorView<TModel>, TModel extends NamedModelBase>
		extends EditorControllerBase<TView, TModel> {

	@InjectLogger
	Logger logger;

	@Inject
	public NamedModelEditorController(TView view) {
		super(view);
	}

	protected <M extends NamedModelBase> void configureTableModelViewActions(TableModelEditorView<M> tableView,
			Class<M> modelClass) {
		Button btnAdd = tableView.getBtnAdd();
		if (btnAdd != null)
			btnAdd.setOnAction(e -> {
				try {
					M newModel = modelClass.newInstance();
					add(newModel, tableView.getModel().getValue());
				} catch (Exception e1) {
					if(modelClass!=null)
						logger.error("Error creating model of type »" + modelClass.getName() + "«", e1);
					else
						logger.error("Error creating model of type because class is null", e1);
				}
			});
		Button btnEdit = tableView.getBtnEdit();
		if (btnEdit != null)
			btnEdit.setOnAction(e -> {
				edit(tableView.getTblData(), tableView.getModel().getValue());
			});
		Button btnDelete = tableView.getBtnDelete();
		if (btnDelete != null)
			btnDelete.setOnAction(e -> {
				delete(tableView.getTblData(), tableView.getModel().getValue());
			});
		Button btnUp = tableView.getBtnUp();
		if (btnUp != null)
			btnUp.setOnAction(e -> {
				up(tableView.getTblData(), tableView.getModel().getValue());
			});
		Button btnDown = tableView.getBtnDown();
		if (btnDown != null)
			btnDown.setOnAction(e -> {
				down(tableView.getTblData(), tableView.getModel().getValue());
			});

		if (tableView.canEdit())
			tableView.setOnTableDoubleClick(e -> edit(tableView.getTblData(), tableView.getModel().getValue()));
	}

	protected <M extends NamedModelBase & IHierarchialModel<M>> void configureTreeTableModelViewActions(
			TreeTableModelEditorView<M> treeTableView, Class<M> modelClass) {
		Button btnAdd = treeTableView.getBtnAdd();
		if (btnAdd != null)
			btnAdd.setOnAction(e -> {
				try {
					M newModel = modelClass.newInstance();
					add(newModel, treeTableView.getModel().getValue());
				} catch (Exception e1) {
					logger.error("Error creating model of type »" + modelClass.getName() + "«", e1);
				}
			});
		Button btnAddChild = treeTableView.getBtnAddChild();
		if (btnAddChild != null)
			btnAddChild.setOnAction(e -> {
				try {
					M newModel = modelClass.newInstance();
					ObservableList<M> list = null;
					if (treeTableView.getTtbData().getSelectionModel().getSelectedItem() != null
							&& treeTableView.getTtbData().getSelectionModel().getSelectedItem().getValue() != null)
						list = treeTableView.getTtbData().getSelectionModel().getSelectedItem().getValue()
								.getChildren();
					else
						list = treeTableView.getModel().getValue();

					add(newModel, list);
				} catch (Exception e1) {
					logger.error("Error creating model of type »" + modelClass.getName() + "«", e1);
				}
			});

		Button btnEdit = treeTableView.getBtnEdit();
		if (btnEdit != null)
			btnEdit.setOnAction(e -> {
				ObservableList<M> list = getList(treeTableView);
				edit(treeTableView.getTtbData(), list);
			});
		Button btnDelete = treeTableView.getBtnDelete();
		if (btnDelete != null)
			btnDelete.setOnAction(e -> {
				ObservableList<M> list = getList(treeTableView);
				delete(treeTableView.getTtbData(), list);
			});

		if (treeTableView.canEdit())
			treeTableView.setOnTableDoubleClick(e -> {
				ObservableList<M> list = getList(treeTableView);
				edit(treeTableView.getTtbData(), list);
			});
	}

	private <M extends NamedModelBase & IHierarchialModel<M>> ObservableList<M> getList(
			TreeTableModelEditorView<M> treeTableView) {
		ObservableList<M> list;
		if (treeTableView.getTtbData().getSelectionModel().getSelectedItem() != null
				&& treeTableView.getTtbData().getSelectionModel().getSelectedItem().getParent() != null
				&& treeTableView.getTtbData().getSelectionModel().getSelectedItem().getParent().getValue() != null)
			list = treeTableView.getTtbData().getSelectionModel().getSelectedItem().getParent().getValue()
					.getChildren();
		else
			list = treeTableView.getModel().getValue();

		return list;
	}

	protected <M> void configureSwapModelViewActions(SwapModelEditorView<M> swapView, ObservableList<M> source) {
		swapView.getBtnRight().setOnAction(e -> {
			M selected = swapView.getLstFrom().getSelectionModel().getSelectedItem();
			if (selected != null) {
				swapView.getModel().getValue().add(selected);
				if (source instanceof SortedList<?>)
					((SortedList<M>) source).getSource().remove(selected);
				else
					source.remove(selected);
			}
		});

		swapView.getBtnLeft().setOnAction(e -> {
			M selected = swapView.getLstTo().getSelectionModel().getSelectedItem();
			if (selected != null) {
				swapView.getModel().getValue().remove(selected);
				if (source instanceof SortedList<?>) {
					@SuppressWarnings("unchecked")
					ObservableList<M> innerSource = (ObservableList<M>) ((SortedList<M>) source).getSource();
					innerSource.add(selected);
				} else
					source.add(selected);
			}
		});

		swapView.getBtnUp().setOnAction(e -> {
			up(swapView.getLstTo(), source);
		});

		swapView.getBtnUp().setOnAction(e -> {
			down(swapView.getLstTo(), source);
		});

	}

	protected void configureStringListViewActions(StringListEditorView stringList, String confirmTitleResourceName) {
		stringList.getBtnAdd().setOnAction(e -> {
			if (!stringList.getTxfStringToAdd().getText().isEmpty()) {
				stringList.getModel().getValue().add(stringList.getTxfStringToAdd().getText());
				stringList.getTxfStringToAdd().setText("");
				stringList.getTxfStringToAdd().requestFocus();
			}
		});

		stringList.getBtnDelete().setOnAction(e -> {
			String operation = stringList.getLstStringList().getSelectionModel().getSelectedItem();
			if (confirmDelete(confirmTitleResourceName, operation)) {
				stringList.getModel().getValue().remove(operation);
			}
		});
		stringList.getBtnUp().setOnAction(e -> {
			up(stringList.getLstStringList(), stringList.getModel().getValue());
		});
		stringList.getBtnDown().setOnAction(e -> {
			down(stringList.getLstStringList(), stringList.getModel().getValue());
		});
	}

	@Override
	protected String getBundleName() {
		return "EditorsBundle";
	}
}
