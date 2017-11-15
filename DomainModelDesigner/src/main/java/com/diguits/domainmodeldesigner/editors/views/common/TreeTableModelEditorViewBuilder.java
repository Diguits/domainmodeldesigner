package com.diguits.domainmodeldesigner.editors.views.common;

import java.util.ArrayList;
import java.util.List;

import com.diguits.javafx.model.IHierarchialModel;
import com.diguits.javafx.undo.changes.PropertyChange;
import com.diguits.javafx.views.INodeFactoryHelper;
import com.diguits.javafx.views.PropertyChangeEventListener;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class TreeTableModelEditorViewBuilder {

	public static <TTreeTableModel extends IHierarchialModel<TTreeTableModel>> TreeTableModelEditorView<TTreeTableModel> createTreeTableEditorViewInNewTab(TabPane tabPane,
			String tabCaptionResource, Class<TTreeTableModel> clazz,
			Callback<TreeTableInfoBuilder<TTreeTableModel>, TreeTableInfo<TTreeTableModel>> callback, INodeFactoryHelper nodeFactory,
			PropertyChangeEventListener propertyChangeEventListener) {
		Tab tab = nodeFactory.createAndAddTab(tabPane, tabCaptionResource);
		TreeTableModelEditorView<TTreeTableModel> resultView = createTreeTableEditorView(clazz, callback, nodeFactory,
				propertyChangeEventListener);
		AnchorPane anchorPane = resultView.getNodeView();
		tab.setContent(anchorPane);
		return resultView;
	}

	public static <TTreeTableModel extends IHierarchialModel<TTreeTableModel>> TreeTableModelEditorView<TTreeTableModel> createTreeTableEditorView(Class<TTreeTableModel> clazz,
			Callback<TreeTableInfoBuilder<TTreeTableModel>, TreeTableInfo<TTreeTableModel>> callback, INodeFactoryHelper nodeFactory,
			PropertyChangeEventListener propertyChangeEventListener) {
		TreeTableInfo<TTreeTableModel> tableInfo = callback.call(new TreeTableInfoBuilder<TTreeTableModel>());
		return createTreeTableView(tableInfo, nodeFactory, propertyChangeEventListener);
	}

	public static <TTreeTableModel extends IHierarchialModel<TTreeTableModel>> TreeTableModelEditorView<TTreeTableModel> createTreeTableView(TreeTableInfo<TTreeTableModel> tableInfo,
			INodeFactoryHelper nodeFactory, PropertyChangeEventListener propertyChangeEventListener) {
		TreeTableModelEditorView<TTreeTableModel> resultView = new TreeTableModelEditorView<TTreeTableModel>(nodeFactory) {
			List<TreeTableColumn<TTreeTableModel, ?>> columns = new ArrayList<>();

			@Override
			protected void createColumns() {
				for (TreeTableColumnInfo<TTreeTableModel, ?> columnInfo : tableInfo.getColumns()) {
					columns.add(nodeFactory.createAndAddTreeTableColumn(ttbData, columnInfo.getCaptionResource(),
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
				TreeTableColumnInfo<TTreeTableModel, TDataType> columnInfo = (TreeTableColumnInfo<TTreeTableModel, TDataType>) tableInfo
						.getColumns().get(i);
				@SuppressWarnings("unchecked")
				TreeTableColumn<TTreeTableModel, TDataType> treeTreeTableColumn = (TreeTableColumn<TTreeTableModel, TDataType>) columns.get(i);

				treeTreeTableColumn.setCellValueFactory(columnInfo.getValueFactory());
				Callback<TreeTableColumn<TTreeTableModel, TDataType>, TreeTableCell<TTreeTableModel, TDataType>> cellFactory = null;
				if (columnInfo.getCellFactory() != null) {
					cellFactory = columnInfo.getCellFactory();
					treeTreeTableColumn.setCellFactory(cellFactory);
				} else if (columnInfo.isUseDefaultCellValueFactory()) {
					createAndSetDefaultCellFactory(columnInfo, treeTreeTableColumn);
					cellFactory = treeTreeTableColumn.getCellFactory();
				}
				if (cellFactory != null) {
					treeTreeTableColumn.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<TTreeTableModel, TDataType>>() {

						@Override
						public void handle(TreeTableColumn.CellEditEvent<TTreeTableModel, TDataType> event) {
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

	private static <TTreeTableModel extends IHierarchialModel<TTreeTableModel>, TDataType> void createAndSetDefaultCellFactory(
			TreeTableColumnInfo<TTreeTableModel, TDataType> columnInfo, TreeTableColumn<TTreeTableModel, TDataType> treeTreeTableColumn) {
		if(columnInfo.getDataType() == String.class){
			@SuppressWarnings("unchecked")
			TreeTableColumn<TTreeTableModel, String> stringTreeTableColumn = (TreeTableColumn<TTreeTableModel, String>)treeTreeTableColumn;
			stringTreeTableColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
		}
		if(columnInfo.getDataType() == Boolean.class){
			@SuppressWarnings("unchecked")
			TreeTableColumn<TTreeTableModel, Boolean> booleanTreeTableColumn = (TreeTableColumn<TTreeTableModel, Boolean>)treeTreeTableColumn;
			booleanTreeTableColumn.setCellFactory(CheckBoxTreeTableCell.forTreeTableColumn(booleanTreeTableColumn));
		}
		if(columnInfo.getDataType() == Integer.class){
			@SuppressWarnings("unchecked")
			TreeTableColumn<TTreeTableModel, Integer> integerTreeTableColumn = (TreeTableColumn<TTreeTableModel, Integer>)treeTreeTableColumn;
			integerTreeTableColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new IntegerStringConverter()));
		}
	}

}
