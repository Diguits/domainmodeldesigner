package com.diguits.domainmodeldesigner.editors.views.common;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TableInfoBuilder<TModel>{

	TableInfo<TModel> tableInfo;

	public TableInfoBuilder() {
		super();
		tableInfo  = new TableInfo<>();
	}

	public <TDataType> ColumnBuilder<TModel, TDataType> add(Class<TDataType> clazz, String captionResource) {
		return new ColumnBuilderImpl<TDataType>(clazz, captionResource);
	}

	public TableInfo<TModel> buildTable() {
		return tableInfo;
	}


	public interface ColumnBuilder<TModel, TDataType>{
		ColumnBuilder<TModel, TDataType> width(int width);
		ColumnBuilder<TModel, TDataType> valueFactory(Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory);
		ColumnBuilder<TModel, TDataType> cellFactory(Callback<TableColumn<TModel, TDataType>, TableCell<TModel, TDataType>> cellFactory);
		ColumnBuilder<TModel, TDataType> useDefaultCellValueFactory();
		TableInfoBuilder<TModel> buildColumn();
	}

	private class ColumnBuilderImpl<TDataType> implements ColumnBuilder<TModel, TDataType> {

		TableColumnInfo<TModel, TDataType> columnInfo;

		public ColumnBuilderImpl(Class<TDataType> dataType, String captionResource) {
			super();
			columnInfo = new TableColumnInfo<TModel, TDataType>(dataType, captionResource);
		}

		@Override
		public ColumnBuilder<TModel, TDataType> width(int width) {
			columnInfo.setWidth(width);
			return this;
		}

		@Override
		public ColumnBuilder<TModel, TDataType> valueFactory(
				Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory) {
			columnInfo.setValueFactory(valueFactory);
			return this;
		}

		@Override
		public ColumnBuilder<TModel, TDataType> cellFactory(
				Callback<TableColumn<TModel, TDataType>, TableCell<TModel, TDataType>> cellFactory) {
			columnInfo.setCellFactory(cellFactory);
			return this;
		}

		@Override
		public ColumnBuilder<TModel, TDataType> useDefaultCellValueFactory() {
			columnInfo.setUseDefaultCellValueFactory(true);
			return this;
		}

		@Override
		public TableInfoBuilder<TModel> buildColumn() {
			tableInfo.getColumns().add(columnInfo);
			return TableInfoBuilder.this;
		}

	}

}
