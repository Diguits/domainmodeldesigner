package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.javafx.views.ViewBase;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class PathEditorView extends ViewBase<GridPane> {

	private TreeView<FieldDefModel> treePathTree;

	private TextField txfPathEditor;

	@Override
	protected GridPane buildView() {

		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		txfPathEditor = nodeFactory.createTextFieldInsideGrid(gridPane, "%path");
		Label treeLabel = nodeFactory.createLabelInsideGrid(gridPane, "%path_tree", 1);
		GridPane.setValignment(treeLabel, VPos.TOP);
		gridPane.getRowConstraints().get(1).setVgrow(Priority.SOMETIMES);
		treePathTree = nodeFactory.createTree();
		GridPane.setMargin(treePathTree, new Insets(2.0, 2.0, 2.0, 2.0));
		gridPane.add(treePathTree, 1, 1);
		treePathTree.setCellFactory((TreeView<FieldDefModel> tv) -> {
			TextFieldTreeCell<FieldDefModel> treeCell = new TextFieldTreeCell<FieldDefModel>() {
				@Override
				public void updateItem(FieldDefModel item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						String text = item.toString();
						if (item.getDataType() != null)
							text += " (" + item.getDataType() + ")";
						this.setText(text);
					}
				}
			};
			return treeCell;
		});
		return gridPane;
	}

	public TreeView<FieldDefModel> getPathTree() {
		return treePathTree;
	}

	public TextInputControl getPathEditor() {
		return txfPathEditor;
	}

}
