package com.diguits.javafx.container.controllers;

import com.diguits.javafx.container.views.ITreeContainerView;
import com.diguits.javafx.model.NamedModelBase;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import javafx.util.StringConverter;

public abstract class TreeContainerController<TView extends ITreeContainerView<?, TModel>, TModel extends NamedModelBase>
		extends DecoratedContainerController<TView, TModel> implements ITreeContainerController<TView, TModel> {

	protected class DefaultCellFactory implements Callback<TreeView<TModel>, TreeCell<TModel>> {

		protected TreeView<TModel> treeView;

		public DefaultCellFactory(TreeView<TModel> treeView) {
			this.treeView = treeView;
		}
		@Override
		public TreeCell<TModel> call(TreeView<TModel> tv) {
			TextFieldTreeCell<TModel> treeCell = new TextFieldTreeCell<TModel>() {
					@Override
					public void updateItem(TModel item, boolean empty) {
						super.updateItem(item, empty);
					}

					@Override
					public void commitEdit(TModel newValue) {
						treeView.setEditable(false);
						super.commitEdit(newValue);
					}

					@Override
					public void cancelEdit() {
						super.cancelEdit();
						treeView.setEditable(false);
					}
				};

				treeCell.itemProperty().addListener(new ChangeListener<TModel>() {

					private ChangeListener<? super String> nameChangeListener = new ChangeListener<String>() {

						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue,
								String newValue) {
							treeCell.setText(treeCell.getItem().toString());
						}
					};

					@Override
					public void changed(ObservableValue<? extends TModel> observable, TModel oldValue,
							TModel newValue) {
						if (oldValue != null && oldValue != newValue)
							oldValue.nameProperty().removeListener(nameChangeListener);
						if (newValue != null) {
							newValue.nameProperty().addListener(nameChangeListener);
							configureTreeCell(treeCell, newValue);
						}
					}
				});

				treeCell.setOnMouseClicked((MouseEvent event) -> {
					if (event.getClickCount() >= 2) {
						openEditorForSelectedModel();
					}
				});
				treeCell.setConverter(new StringConverter<TModel>() {

					@Override
					public String toString(TModel object) {
						return object.toString();
					}

					@Override
					public TModel fromString(String string) {
						TreeItem<TModel> selectedItem = treeView.getSelectionModel().getSelectedItem();
						TModel result = null;
						if (selectedItem != null) {
							result = selectedItem.getValue();
							result.setName(string);
						}
						return result;
					}
				});
				return treeCell;
		}

	}

	BooleanProperty canCollapse = new SimpleBooleanProperty();
	BooleanProperty canExpand = new SimpleBooleanProperty();

	public TreeContainerController(TView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getView() != null) {
			configureTreeView();
			canCollapse.bind(getView().getTreeView().rootProperty().isNull());
			canExpand.bind(getView().getTreeView().rootProperty().isNull());

		}
	}

	public void configureTreeView(){
		TreeView<TModel> treeView = getView().getTreeView();
		if (treeView == null)
			return;
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<TModel>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<TModel>> observable, TreeItem<TModel> oldValue,
					TreeItem<TModel> newValue) {
				if (newValue != null) {
					// This call is very important because the breadcrumbar
					// need the children of the selected created
					newValue.getChildren();
					setSelectedModel(newValue.getValue());
					if (decorator != null) {
						ContextMenu menu =
								optionToNodeFactory.createContextMenu(decorator.getContextMenuOptions(newValue.getValue()));
						treeView.setContextMenu(menu);
					}
					setSelectedTreeItem(newValue);
				}
				if (newValue == null /*|| isTreeItemContainer(newValue.getValue())*/)
					treeView.setContextMenu(null);
			}
		});

		treeView.setCellFactory(getCellFactory(treeView));
	}

	protected TreeContainerController<TView, TModel>.DefaultCellFactory getCellFactory(TreeView<TModel> treeView) {
		return new DefaultCellFactory(treeView);
	}

	protected void configureTreeCell(TextFieldTreeCell<TModel> treeCell, TModel newValue) {
		Font font = treeCell.getFont();
		if (isTreeItemContainer(newValue)) {
			treeCell.setFont(Font.font(font.getFamily(), FontWeight.BOLD, font.getSize()));
		} else {
			if (font.getStyle() != "Regular")
				treeCell.setFont(Font.font(font.getFamily(), FontWeight.NORMAL, font.getSize()));
		}
	}

	public <TModelTree> void fill(TreeBuilder<TModelTree, TModel> builder, TModelTree modelTree) {
		builder.fillTree(modelTree, getView().getTreeView(), this);
	}

	protected ImageView getImageForModel(TModel item) {
		if (decorator != null)
			return decorator.getImageForModel(item);
		return null;
	}

	public void openEditorForSelectedModel() {
		if (getSelectedModel() != null && !isTreeItemContainer(getSelectedModel()))
			showEditor(getSelectedModel(), getView().getTreeView().getSelectionModel().getSelectedItem());
	}

	public void renameSelectedModel() {
		if (getSelectedModel() != null && getView() != null) {
			getView().renameSelectedModel();
		}
	}

	public void collapse() {
		getView().collapse();
	}

	public void expand() {
		getView().expand();
	}

	public ObservableValue<? extends Boolean> getCanCollapse(){
		return canCollapse;
	}

	@Override
	public ObservableValue<? extends Boolean> getCanExpand() {
		return canExpand;
	}

	public boolean allowTreeItemSelecion() {
		return true;
	}

	public TreeItem<TModel> findTreeItemByModel(TModel model) {
		return findTreeItemByModelR(model, getView().getTreeView().getRoot());
	}

	TreeItem<TModel> findTreeItemByModelR(TModel model, TreeItem<TModel> parent) {
		if (parent != null) {
			if (parent.getValue().equals(model))
				return parent;
			else {
				for (TreeItem<TModel> child : parent.getChildren()) {
					TreeItem<TModel> result = findTreeItemByModelR(model, child);
					if (result != null)
						return result;
				}
			}
		}
		return null;
	}

}
