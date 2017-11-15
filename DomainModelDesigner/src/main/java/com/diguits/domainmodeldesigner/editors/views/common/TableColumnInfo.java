package com.diguits.domainmodeldesigner.editors.views.common;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TableColumnInfo<TModel, TDataType> extends TableColumnInfoBase<TModel, TDataType>{

	private Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory;
	private Callback<TableColumn<TModel, TDataType>, TableCell<TModel, TDataType>> cellFactory;

	public TableColumnInfo(Class<TDataType> dataType, String captionResource) {
		super(dataType, captionResource);
	}

	public TableColumnInfo(Class<TDataType> clazz, String captionResource, int width,
			Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory) {
		super(clazz, captionResource, width);
		this.valueFactory = valueFactory;
	}

	public TableColumnInfo(Class<TDataType> clazz, String captionResource, int width,
			Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory,
			Callback<TableColumn<TModel, TDataType>, TableCell<TModel, TDataType>> cellFactory) {
		this(clazz, captionResource, width, valueFactory);
		this.cellFactory = cellFactory;
	}

	public void setValueFactory(Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory) {
		this.valueFactory = valueFactory;
	}

	public Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> getValueFactory() {
		return valueFactory;
	}

	public Callback<TableColumn<TModel, TDataType>, TableCell<TModel, TDataType>> getCellFactory() {
		return cellFactory;
	}

	public void setCellFactory(Callback<TableColumn<TModel, TDataType>, TableCell<TModel, TDataType>> cellFactory) {
		this.cellFactory = cellFactory;
	}
}
