package com.diguits.javafx.views.helpers;

import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;

public class TreeViewHelper {

	public static <T> TreeTableView<T> createTreeTableView() {
		TreeTableView<T> treeTreeTableView = new TreeTableView<T>();
		treeTreeTableView.setEditable(true);
		return treeTreeTableView;
	}

	public static <T, V> TreeTableColumn<T, V> createAndAddTreeTableColumn(TreeTableView<T> treeTableView,
			String text, double width) {
		TreeTableColumn<T, V> column = createTreeTableColumn(text, width);
		treeTableView.getColumns().add(column);
		return column;
	}

	public static <T, V> TreeTableColumn<T, V> createTreeTableColumn(String text, double width) {
		TreeTableColumn<T, V> column = new TreeTableColumn<T, V>(text);
		column.setPrefWidth(width);
		return column;
	}

	public static <T> TreeView<T> createTree() {
		return new TreeView<T>();
	}
}
