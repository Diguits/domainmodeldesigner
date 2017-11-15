package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.views.INodeFactoryHelper;
import com.diguits.javafx.views.ModelView;

import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public abstract class TableModelEditorViewBase<TModel> extends ModelView<AnchorPane, Property<ObservableList<TModel>>> {

	protected Button btnAdd;
	protected Button btnEdit;
	protected Button btnDelete;
	protected EventHandler<MouseEvent> onTableDoubleClick;

	protected boolean canAdd = true;
	protected boolean canEdit = true;
	protected boolean canDelete = true;
	protected boolean showToolBar = true;
	protected ToolBar toolBar;

	public TableModelEditorViewBase(INodeFactoryHelper nodeFactory) {
		super();
		this.nodeFactory = nodeFactory;
	}

	public boolean canAdd() {
		return canAdd;
	}

	public boolean canEdit() {
		return canEdit;
	}

	public boolean canDelete() {
		return canDelete;
	}


	protected abstract void bindColumns();

	@Override
	protected AnchorPane buildView() {
		BorderPane borderPane = new BorderPane();
		toolBar = nodeFactory.createToolBar();
		toolBar.setVisible(showToolBar);
		if (canAdd()) {
			btnAdd = nodeFactory.createButton16x16("/images/add.png");
			toolBar.getItems().add(btnAdd);
		}
		if (canEdit()) {
			btnEdit = nodeFactory.createButton16x16("/images/pencil.png");
			toolBar.getItems().add(btnEdit);
		}
		if (canDelete()) {
			btnDelete = nodeFactory.createButton16x16("/images/delete.png");
			toolBar.getItems().add(btnDelete);
		}

		borderPane.setTop(toolBar);
		Control table = createTable();
		createColumns();
		borderPane.setCenter(table);

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(borderPane);
		nodeFactory.fitToAnchorPane(borderPane);
		return anchorPane;
	}

	protected abstract Control createTable();

	protected abstract void createColumns();

	public EventHandler<MouseEvent> getOnTableDoubleClick() {
		return onTableDoubleClick;
	}

	public void setOnTableDoubleClick(EventHandler<MouseEvent> onTableDoubleClick) {
		this.onTableDoubleClick = onTableDoubleClick;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public Button getBtnEdit() {
		return btnEdit;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}


	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public boolean isShowToolBar() {
		return showToolBar;
	}

	public void setShowToolBar(boolean showToolBar) {
		this.showToolBar = showToolBar;
		if(toolBar!=null){
			toolBar.setVisible(showToolBar);
		}
	}
}
