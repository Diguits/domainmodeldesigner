package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.model.IHierarchialModel;
import com.diguits.javafx.views.INodeFactoryHelper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

public abstract class TreeTableModelEditorView<TModel extends IHierarchialModel<TModel>>
		extends TableModelEditorViewBase<TModel> {

	protected Button btnAddChild;
	protected TreeTableView<TModel> ttbData;

	public TreeTableModelEditorView(INodeFactoryHelper nodeFactory) {
		super(nodeFactory);
	}

	@Override
	protected void bindFieldsToModel() {
		fillTreeTable();

		bindColumns();

		ttbData.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2 && ttbData.getSelectionModel().getSelectedItem() != null
					&& onTableDoubleClick != null)
				onTableDoubleClick.handle(e);
		});
		if (btnEdit != null)
			bind(btnEdit.disableProperty(), ttbData.getSelectionModel().selectedItemProperty().isNull());
		if (btnDelete != null)
			bind(btnDelete.disableProperty(), ttbData.getSelectionModel().selectedItemProperty().isNull());

	}

	private void fillTreeTable() {
		ttbData.setShowRoot(false);
		TreeItem<TModel> root = new TreeItem<TModel>(null);
		for (TModel model : getModel().getValue()) {
			root.getChildren().add(new ModelTreeItem(model));
		}
		ttbData.setRoot(root);

		getModel().getValue().addListener(new ListChangeListener<TModel>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TModel> c) {
				while (c.next()) {
					for (TModel removed : c.getRemoved()) {
						TreeItem<TModel> child = findTreeItemByModel(root, removed);
						if (child != null)
							root.getChildren().remove(child);
					}
					for (TModel added : c.getAddedSubList()) {
						root.getChildren().add(new ModelTreeItem(added));
					}
				}
			}

		});
	}

	private TreeItem<TModel> findTreeItemByModel(TreeItem<TModel> parent, TModel model) {
		for (TreeItem<TModel> child : parent.getChildren()) {
			if (child.getValue() == model)
				return child;
		}
		return null;
	}

	public class ModelTreeItem extends TreeItem<TModel> implements ListChangeListener<TModel> {
		private boolean childrenLoaded = false;
		private ObservableList<TModel> children;

		public ModelTreeItem(TModel model) {
			super(model);
			children = getChildrenFor(model);
			children.addListener(this);
		}

		@Override
		public ObservableList<TreeItem<TModel>> getChildren() {
			if (!childrenLoaded) {
				childrenLoaded = true;
				populateChildren(this);
			}
			return super.getChildren();
		}

		private void populateChildren(TreeTableModelEditorView<TModel>.ModelTreeItem modelTreeItem) {
			if (children != null)
				for (TModel model : children) {
					modelTreeItem.getChildren().add(new ModelTreeItem(model));
				}
		}

		@Override
		public boolean isLeaf() {
			return children == null || children.size() == 0;
		}

		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends TModel> c) {
			while (c.next()) {
				for (TModel removed : c.getRemoved()) {
					TreeItem<TModel> child = findTreeItemByModel(ModelTreeItem.this, removed);
					if (child != null)
						getChildren().remove(child);
				}
				for (TModel added : c.getAddedSubList()) {
					if (childrenLoaded)
						getChildren().add(new ModelTreeItem(added));
					else if (getChildren().size() == 1)
						ttbData.requestLayout();
					setExpanded(true);
				}
			}
		}
	}

	public ObservableList<TModel> getChildrenFor(TModel parent) {
		if (parent != null)
			return parent.getChildren();
		return FXCollections.observableArrayList();
	}

	@Override
	protected AnchorPane buildView() {
		AnchorPane anchorPane = super.buildView();
		if (canAdd()) {
			btnAddChild = nodeFactory.createButton16x16("/images/child_add.png");
			toolBar.getItems().add(1, btnAddChild);
		}
		return anchorPane;
	}

	@Override
	protected Control createTable() {
		ttbData = nodeFactory.createTreeTableView();
		return ttbData;
	}

	public Button getBtnAddChild() {
		return btnAddChild;
	}

	public TreeTableView<TModel> getTtbData() {
		return ttbData;
	}
}
