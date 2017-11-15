package com.diguits.javafx.views.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class ListViewHelper {

	public static <T> ListView<T> createListView() {
		ListView<T> listView = new ListView<T>();
		return listView;
	}

	public static <T> ListView<T> createListViewInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		ListView<T> listView = createListView();
		GridPane.setMargin(listView, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size());
		gridPane.add(listView, columnIndex, rowIndex);
		return listView;
	}
}
