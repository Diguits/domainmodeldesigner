package com.diguits.javafx.navigation.views;

import com.diguits.javafx.container.controllers.EditorControllerBase;
import com.diguits.javafx.container.controllers.IContainerController;
import com.diguits.javafx.views.ViewBase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ContainerNavigationView extends ViewBase<BorderPane> {

	private ListView<IContainerController<?, ?>> lstEditors;
	private ListView<IContainerController<?, ?>> lstContainer;
	private ObjectProperty<IContainerController<?, ?>> selectedContainer;
	private Label lblSelectedElement;
	private Label lblSelectedElementType;

	public class ContainerCellFactory
			implements Callback<ListView<IContainerController<?, ?>>, ListCell<IContainerController<?, ?>>> {
		@Override
		public ListCell<IContainerController<?, ?>> call(ListView<IContainerController<?, ?>> listview) {
			return new TextFieldListCell<IContainerController<?, ?>>() {
				@Override
				public void updateItem(IContainerController<?, ?> item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						setText(item.getTitle());
						setGraphic(new ImageView(item.getImage().getImage()));
					}
				}

			};
		}
	}

	@Override
	protected BorderPane buildView() {
		BorderPane borderPane = new BorderPane();

		VBox vbxHeader = new VBox();
		lblSelectedElement = nodeFactory.createLabel("%containers");
		lblSelectedElement = nodeFactory.createLabel("");
		lblSelectedElement.setStyle("-fx-font-size: 17; -fx-padding:0 2 0 2; -fx-font-weight:bold");
		vbxHeader.getChildren().add(lblSelectedElement);
		lblSelectedElementType = nodeFactory.createLabel("");
		lblSelectedElementType.setStyle("-fx-font-size: 12; -fx-padding:0 2 2 2");
		vbxHeader.getChildren().add(lblSelectedElementType);
		borderPane.setTop(vbxHeader);

		GridPane grdContainer = nodeFactory.createGridPaneTwoSameColumns();
		RowConstraints rocTitles = nodeFactory.createRowConstraints(20);
		rocTitles.setVgrow(null);
		grdContainer.getRowConstraints().add(rocTitles);
		RowConstraints rocContainers = nodeFactory.createRowConstraints();
		rocContainers.setVgrow(Priority.ALWAYS);
		grdContainer.getRowConstraints().add(rocContainers);

		Label lblEditorsTitle = nodeFactory.createLabelInsideGrid(grdContainer, "%editors", 1, 0);
		lblEditorsTitle.setStyle("-fx-font-size: 12; -fx-padding:3 2 0 2; -fx-font-weight:bold");
		lstEditors = nodeFactory.createListView();
		lstEditors.setStyle("-fx-background-color:transparent;");
		lstEditors.setCellFactory(new ContainerCellFactory());
		grdContainer.add(lstEditors, 1, 1);
		GridPane.setVgrow(lstEditors, Priority.ALWAYS);
		borderPane.setCenter(grdContainer);

		Label lblContainersTitle = nodeFactory.createLabelInsideGrid(grdContainer, "%containers", 0, 0);
		lblContainersTitle.setStyle("-fx-font-size: 12; -fx-padding:3 2 2 2; -fx-font-weight:bold");
		lstContainer = nodeFactory.createListView();
		lstContainer.setStyle("-fx-background-color:transparent;");
		lstContainer.setCellFactory(new ContainerCellFactory());
		grdContainer.add(lstContainer, 0, 1);
		GridPane.setVgrow(lstContainer, Priority.ALWAYS);

		lstEditors.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.LEFT) {
				if (lstContainer.getSelectionModel().getSelectedIndex() < 0)
					lstContainer.getSelectionModel().select(0);
				lstContainer.requestFocus();
			} else if (e.getCode() == KeyCode.TAB) {
				if (lstEditors.getItems().size() > 0) {
					moveSelectedOnTap(e, lstEditors, lstContainer);
				}
			}
		});

		lstContainer.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.RIGHT) {
				if (lstEditors.getSelectionModel().getSelectedIndex() < 0)
					lstEditors.getSelectionModel().select(0);
				lstEditors.requestFocus();
			} else if (e.getCode() == KeyCode.TAB) {
				moveSelectedOnTap(e, lstContainer, lstEditors);
			}
		});

		lstEditors.focusedProperty().addListener((v, o, n) -> {
			if (n) {
				if (lstEditors.getItems().size() == 0)
					lstContainer.requestFocus();
				else {
					selectedContainer.unbind();
					selectedContainerProperty().bind(lstEditors.getSelectionModel().selectedItemProperty());
				}
			}
		});

		lstContainer.focusedProperty().addListener((v, o, n) -> {
			if (n) {
				selectedContainer.unbind();
				selectedContainerProperty().bind(lstContainer.getSelectionModel().selectedItemProperty());
			}
		});

		lstEditors.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2 && getDialogContainer() != null) {
				getDialogContainer().setResult(ButtonType.OK);
				getDialogContainer().close();
			}
		});

		lstContainer.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2 && getDialogContainer() != null) {
				getDialogContainer().setResult(ButtonType.OK);
				getDialogContainer().close();
			}
		});
		selectedContainerProperty().addListener((v, o, n) -> {
			if (n != null) {
				lblSelectedElement.setText(n.getTitle());
				lblSelectedElement.setGraphic(new ImageView(n.getImage().getImage()));
				if (n instanceof EditorControllerBase)
					lblSelectedElementType.setText(n.getModelTypeName());
				else
					lblSelectedElementType.setText("");
			} else {
				lblSelectedElement.setText("");
				lblSelectedElementType.setText("");
			}
		});

		lstEditors.requestFocus();

		return borderPane;
	}

	private void moveSelectedOnTap(KeyEvent e, ListView<IContainerController<?, ?>> fromListView,
			ListView<IContainerController<?, ?>> toListView) {
		int nextIndex = fromListView.getSelectionModel().getSelectedIndex();
		if (e.isShiftDown())
			nextIndex -= 1;
		else
			nextIndex += 1;
		if (nextIndex < 0 && toListView.getItems().size() > 0){
			toListView.requestFocus();
			toListView.getSelectionModel().select(toListView.getItems().size() - 1);
		} else if (nextIndex >= fromListView.getItems().size() && toListView.getItems().size() > 0) {
			toListView.requestFocus();
			toListView.getSelectionModel().select(0);
		} else {
			int size = fromListView.getItems().size();
			fromListView.getSelectionModel().select(((nextIndex % size)+size)%size);
		}
	}

	public void setEditorContainers(ObservableList<IContainerController<?, ?>> editorContainers) {
		lstEditors.setItems(editorContainers);
		lstEditors.getSelectionModel().select(0);
		if (lstEditors.getItems().size() == 0) {
			lstContainer.requestFocus();
		}

	}

	public void setContainers(ObservableList<IContainerController<?, ?>> activeContainers) {
		lstContainer.setItems(activeContainers.sorted());
		lstContainer.getSelectionModel().select(0);
	}

	public IContainerController<?, ?> getSelectedContainer() {
		if (selectedContainer != null)
			return selectedContainer.get();
		return null;
	}

	public void setSelectedContainer(IContainerController<?, ?> selectedContainer) {
		if (this.selectedContainer != null || selectedContainer != null) {
			selectedContainerProperty().set(selectedContainer);
		}
	}

	public ObjectProperty<IContainerController<?, ?>> selectedContainerProperty() {
		if (selectedContainer == null) {
			selectedContainer = new SimpleObjectProperty<IContainerController<?, ?>>(this, "selectedContainer", null);
		}
		return selectedContainer;
	}

}
