package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.model.IHierarchialModel;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TreeTableColumnInfo<TModel extends IHierarchialModel<TModel>, TDataType> extends TableColumnInfoBase<TModel, TDataType>{

	private Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory;
	private Callback<TreeTableColumn<TModel, TDataType>, TreeTableCell<TModel, TDataType>> cellFactory;

	public TreeTableColumnInfo(Class<TDataType> dataType, String captionResource) {
		super(dataType, captionResource);
	}

	public TreeTableColumnInfo(Class<TDataType> clazz, String captionResource, int width,
			Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory) {
		super(clazz, captionResource, width);
		this.valueFactory = valueFactory;
	}

	public TreeTableColumnInfo(Class<TDataType> clazz, String captionResource, int width,
			Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory,
			Callback<TreeTableColumn<TModel, TDataType>, TreeTableCell<TModel, TDataType>> cellFactory) {
		this(clazz, captionResource, width, valueFactory);
		this.cellFactory = cellFactory;
	}

	public void setValueFactory(Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory) {
		this.valueFactory = valueFactory;
	}

	public Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> getValueFactory() {
		return valueFactory;
	}

	public Callback<TreeTableColumn<TModel, TDataType>, TreeTableCell<TModel, TDataType>> getCellFactory() {
		return cellFactory;
	}

	public void setCellFactory(Callback<TreeTableColumn<TModel, TDataType>, TreeTableCell<TModel, TDataType>> cellFactory) {
		this.cellFactory = cellFactory;
	}
}
