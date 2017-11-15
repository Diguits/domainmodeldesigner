package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.views.INodeFactoryHelper;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class TableModelEditorView<TModel> extends TableModelEditorViewBase<TModel> {

	protected Button btnUp;
	protected Button btnDown;
	protected TableView<TModel> tblData;
	private boolean canOrder = true;

	public TableModelEditorView(INodeFactoryHelper nodeFactory) {
		super(nodeFactory);
	}

	public boolean canOrder() {
		return canOrder;
	}

	@Override
	protected void bindFieldsToModel() {
		bindBidirectional(tblData.itemsProperty(), getModel());

		bindColumns();

		tblData.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2 && tblData.getSelectionModel().getSelectedItem() != null
					&& onTableDoubleClick != null)
				onTableDoubleClick.handle(e);
		});
		if (btnEdit != null)
			bind(btnEdit.disableProperty(), tblData.getSelectionModel().selectedItemProperty().isNull());
		if (btnDelete != null)
			bind(btnDelete.disableProperty(), tblData.getSelectionModel().selectedItemProperty().isNull());
		if (btnUp != null)
			bind(btnUp.disableProperty(), tblData.getSelectionModel().selectedItemProperty().isNull()
					.or(tblData.getSelectionModel().selectedIndexProperty().isEqualTo(0)));
		if (btnDown != null)
			bind(btnDown.disableProperty(),
					tblData.getSelectionModel().selectedItemProperty().isNull().or(tblData.getSelectionModel()
							.selectedIndexProperty().isEqualTo(Bindings.size(tblData.getItems()).subtract(1))));

	}

	protected abstract void bindColumns();

	@Override
	protected AnchorPane buildView() {
		AnchorPane anchorPane = super.buildView();

		if (canOrder()) {
			btnUp = nodeFactory.createButton16x16("/images/arrow_up.png");
			btnDown = nodeFactory.createButton16x16("/images/arrow_down.png");
			toolBar.getItems().addAll(btnUp, btnDown);
		}
		return anchorPane;
	}

	@Override
	protected Control createTable() {
		tblData =  nodeFactory.createTableView();
		return tblData;
	}

	public Button getBtnUp() {
		return btnUp;
	}

	public Button getBtnDown() {
		return btnDown;
	}

	public TableView<TModel> getTblData() {
		return tblData;
	}

	public void setCanOrder(boolean canOrder) {
		this.canOrder = canOrder;
	}

}
