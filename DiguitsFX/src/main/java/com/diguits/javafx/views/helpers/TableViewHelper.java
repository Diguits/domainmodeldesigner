package com.diguits.javafx.views.helpers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewHelper {

	public static <T> TableView<T> createTableView() {
		TableView<T> tableView = new TableView<T>();
		tableView.setEditable(true);
		return tableView;
	}

	public static  <T, V> TableColumn<T, V> createAndAddTableColumn(TableView<T> tableView, String text, double width) {
		TableColumn<T, V> column = createTableColumn(text, width);
		tableView.getColumns().add(column);
		return column;
	}

	public static <T, V> TableColumn<T, V> createTableColumn(String text, double width) {
		TableColumn<T, V> column = new TableColumn<T, V>(text);
		column.setSortable(false);
		column.setPrefWidth(width);
		return column;
	}
}
