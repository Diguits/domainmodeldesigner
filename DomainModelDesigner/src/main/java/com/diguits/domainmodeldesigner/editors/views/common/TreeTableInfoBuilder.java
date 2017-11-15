package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.model.IHierarchialModel;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TreeTableInfoBuilder<TModel extends IHierarchialModel<TModel>>{

	TreeTableInfo<TModel> tableInfo;

	public TreeTableInfoBuilder() {
		super();
		tableInfo  = new TreeTableInfo<>();
	}

	public <TDataType> ColumnBuilder<TModel, TDataType> add(Class<TDataType> clazz, String captionResource) {
		return new ColumnBuilderImpl<TDataType>(clazz, captionResource);
	}

	public TreeTableInfo<TModel> buildTreeTable() {
		return tableInfo;
	}


	public interface ColumnBuilder<TModel extends IHierarchialModel<TModel>, TDataType>{
		ColumnBuilder<TModel, TDataType> width(int width);
		ColumnBuilder<TModel, TDataType> valueFactory(Callback<CellDataFeatures<TModel, TDataType>, ObservableValue<TDataType>> valueFactory);
		ColumnBuilder<TModel, TDataType> cellFactory(Callback<TreeTableColumn<TModel, TDataType>, TreeTableCell<TModel, TDataType>> cellFactory);
		ColumnBuilder<TModel, TDataType> useDefaultCellValueFactory();
		TreeTableInfoBuilder<TModel> buildColumn();
	}

	private class ColumnBuilderImpl<TDataType> implements ColumnBuilder<TModel, TDataType> {

		TreeTableColumnInfo<TModel, TDataType> columnInfo;

		public ColumnBuilderImpl(Class<TDataType> dataType, String captionResource) {
			super();
			columnInfo = new TreeTableColumnInfo<TModel, TDataType>(dataType, captionResource);
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
				Callback<TreeTableColumn<TModel, TDataType>, TreeTableCell<TModel, TDataType>> cellFactory) {
			columnInfo.setCellFactory(cellFactory);
			return this;
		}

		@Override
		public ColumnBuilder<TModel, TDataType> useDefaultCellValueFactory() {
			columnInfo.setUseDefaultCellValueFactory(true);
			return this;
		}

		@Override
		public TreeTableInfoBuilder<TModel> buildColumn() {
			tableInfo.getColumns().add(columnInfo);
			return TreeTableInfoBuilder.this;
		}

	}

}
