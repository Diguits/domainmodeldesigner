package com.diguits.domainmodeldesigner.editors.views.common;

import java.util.ArrayList;
import java.util.List;

import com.diguits.javafx.undo.changes.PropertyChange;
import com.diguits.javafx.views.INodeFactoryHelper;
import com.diguits.javafx.views.PropertyChangeEventListener;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class TableModelEditorViewBuilder {

	public static <TTableModel> TableModelEditorView<TTableModel> createTableEditorViewInNewTab(TabPane tabPane,
			String tabCaptionResource, Class<TTableModel> clazz,
			Callback<TableInfoBuilder<TTableModel>, TableInfo<TTableModel>> callback, INodeFactoryHelper nodeFactory,
			PropertyChangeEventListener propertyChangeEventListener) {
		Tab tab = nodeFactory.createAndAddTab(tabPane, tabCaptionResource);
		TableModelEditorView<TTableModel> resultView = createTableEditorView(clazz, callback, nodeFactory,
				propertyChangeEventListener);
		AnchorPane anchorPane = resultView.getNodeView();
		tab.setContent(anchorPane);
		return resultView;
	}

	public static <TTableModel> TableModelEditorView<TTableModel> createTableEditorView(Class<TTableModel> clazz,
			Callback<TableInfoBuilder<TTableModel>, TableInfo<TTableModel>> callback, INodeFactoryHelper nodeFactory,
			PropertyChangeEventListener propertyChangeEventListener) {
		TableInfo<TTableModel> tableInfo = callback.call(new TableInfoBuilder<TTableModel>());
		return createTableView(tableInfo, nodeFactory, propertyChangeEventListener);
	}

	public static <TTableModel> TableModelEditorView<TTableModel> createTableView(TableInfo<TTableModel> tableInfo,
			INodeFactoryHelper nodeFactory, PropertyChangeEventListener propertyChangeEventListener) {
		TableModelEditorView<TTableModel> resultView = new TableModelEditorView<TTableModel>(nodeFactory) {
			List<TableColumn<TTableModel, ?>> columns = new ArrayList<>();

			@Override
			protected void createColumns() {
				for (TableColumnInfo<TTableModel, ?> columnInfo : tableInfo.getColumns()) {
					columns.add(nodeFactory.createAndAddTableColumn(tblData, columnInfo.getCaptionResource(),
							columnInfo.getWidth()));
				}
			}

			@Override
			protected void bindColumns() {
				for (int i = 0; i < tableInfo.getColumns().size(); i++) {
					bindColumn(i);
				}
			}

			private <TDataType> void bindColumn(int i) {
				@SuppressWarnings("unchecked")
				TableColumnInfo<TTableModel, TDataType> columnInfo = (TableColumnInfo<TTableModel, TDataType>) tableInfo
						.getColumns().get(i);
				@SuppressWarnings("unchecked")
				TableColumn<TTableModel, TDataType> tableColumn = (TableColumn<TTableModel, TDataType>) columns.get(i);

				tableColumn.setCellValueFactory(columnInfo.getValueFactory());
				Callback<TableColumn<TTableModel, TDataType>, TableCell<TTableModel, TDataType>> cellFactory = null;
				if (columnInfo.getCellFactory() != null) {
					cellFactory = columnInfo.getCellFactory();
					tableColumn.setCellFactory(cellFactory);
				} else if (columnInfo.isUseDefaultCellValueFactory()) {
					createAndSetDefaultCellFactory(columnInfo, tableColumn);
					cellFactory = tableColumn.getCellFactory();
				}
				if (cellFactory != null) {

					tableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TTableModel, TDataType>>() {

						@Override
						public void handle(CellEditEvent<TTableModel, TDataType> event) {
							if (propertyChangeEventListener != null) {
								ObservableValue<TDataType> cellObservableValue = event.getTableColumn()
										.getCellObservableValue(event.getRowValue());
								if (cellObservableValue instanceof Property) {
									Property<TDataType> property = (Property<TDataType>) cellObservableValue;
									TDataType oldValue = event.getOldValue();
									TDataType newValue = event.getNewValue();
									property.setValue(newValue);
									propertyChangeEventListener
											.onPropertyChange(
													new PropertyChange<TDataType>(oldValue, newValue, property));
								}
							}
						}
					});
				}
			}
		};
		resultView.setPropertyChangeEventListener(propertyChangeEventListener);
		return resultView;
	}

	private static <TTableModel, TDataType> void createAndSetDefaultCellFactory(
			TableColumnInfo<TTableModel, TDataType> columnInfo, TableColumn<TTableModel, TDataType> tableColumn) {
		if(columnInfo.getDataType() == String.class){
			@SuppressWarnings("unchecked")
			TableColumn<TTableModel, String> stringTableColumn = (TableColumn<TTableModel, String>)tableColumn;
			stringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		}
		if(columnInfo.getDataType() == Boolean.class){
			@SuppressWarnings("unchecked")
			TableColumn<TTableModel, Boolean> booleanTableColumn = (TableColumn<TTableModel, Boolean>)tableColumn;
			Callback<TableColumn<TTableModel, Boolean>, TableCell<TTableModel, Boolean>> forTableColumn = CheckBoxTableCell.forTableColumn(booleanTableColumn);
			booleanTableColumn.setCellFactory(forTableColumn);
		}
		if(columnInfo.getDataType() == Integer.class){
			@SuppressWarnings("unchecked")
			TableColumn<TTableModel, Integer> integerTableColumn = (TableColumn<TTableModel, Integer>)tableColumn;
			integerTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		}
	}

}
