package com.diguits.javafx.navigation.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.diguits.javafx.controls.RichTextListCell;
import com.diguits.javafx.navigation.controllers.ModelSelectorNavigationController.ModelPositions;
import com.diguits.javafx.navigation.controllers.ModelSelectorNavigationController.Position;
import com.diguits.javafx.views.ViewBase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public abstract class ModelSelectorNavigationView extends ViewBase<VBox> {

    private ListView<ModelPositions> lstModels;
    private ObjectProperty<ModelPositions> selectedModel;
    private Label lblSelectedElement;
    private Label lblSelectedElementType;
    private TextField txfFilter;

    public class ModelCellFactory implements Callback<ListView<ModelPositions>, ListCell<ModelPositions>> {
        @Override
        public RichTextListCell<ModelPositions> call(ListView<ModelPositions> listView) {
            return new RichTextListCell<ModelPositions>() {
                @Override
                protected Image getIcon(ModelPositions value) {
                    return ModelSelectorNavigationView.this.getImage(value);
                }

                @Override
                protected List<Text> getTexts(ModelPositions value) {
                    return ModelSelectorNavigationView.this.getCellTexts(value);
                }
            };
        }
    }

    @Override
    protected VBox buildView() {
        VBox vbxModel = new VBox();
        lblSelectedElement = nodeFactory.createLabel("%models");
        lblSelectedElement = nodeFactory.createLabel("");
        lblSelectedElement.setStyle("-fx-font-size: 17; -fx-padding:0 2 0 2; -fx-font-weight:bold");
        vbxModel.getChildren().add(lblSelectedElement);
        lblSelectedElementType = nodeFactory.createLabel("");
        lblSelectedElementType.setStyle("-fx-font-size: 12; -fx-padding:0 2 2 2");
        vbxModel.getChildren().add(lblSelectedElementType);

        txfFilter = new TextField();
        VBox.setMargin(txfFilter, new Insets(0, 0, 5, 0));
        vbxModel.getChildren().add(txfFilter);

        Label lblEditorsTitle = nodeFactory.createLabel("%editors");
        lblEditorsTitle.setStyle("-fx-font-size: 12; -fx-padding:3 2 0 2; -fx-font-weight:bold");
        lstModels = nodeFactory.createListView();
        lstModels.setStyle("-fx-background-color:transparent;");
        lstModels.setCellFactory(new ModelCellFactory());
        vbxModel.getChildren().add(lstModels);
        VBox.setVgrow(lstModels, Priority.ALWAYS);

        selectedModelProperty().bind(lstModels.getSelectionModel().selectedItemProperty());

        lstModels.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                if (lstModels.getSelectionModel().getSelectedIndex() <= 0)
                    txfFilter.requestFocus();
            } else if (e.getCode() == KeyCode.TAB) {
                if (lstModels.getItems().size() > 0) {
                    moveSelectedOnTap(e, lstModels);
                }
            }

        });

        txfFilter.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DOWN) {
                if (lstModels.getItems().size() > 0) {
                    lstModels.getSelectionModel().select(0);
                    lstModels.requestFocus();
                }
            }
        });

        lstModels.focusedProperty().addListener((v, o, n) -> {
            if (n) {
                selectedModel.bind(lstModels.getSelectionModel().selectedItemProperty());
            }
        });

        lstModels.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && getDialogContainer() != null) {
                getDialogContainer().setResult(ButtonType.OK);
                getDialogContainer().close();
            }
        });

        selectedModelProperty().addListener((v, o, n) -> {
            if (n != null) {
                lblSelectedElement.setText(n.getModel().getName());
                lblSelectedElement.setGraphic(getImageView(n));
            } else {
                lblSelectedElement.setText("");
                lblSelectedElementType.setText("");
            }
        });

        lstModels.requestFocus();

        return vbxModel;
    }

    protected List<Text> getCellTexts(ModelPositions value) {
        List<Text> result = new ArrayList<Text>();
        List<Position> positions = value.getPositions();

        if (value.getModel() == null)
            return null;

        String str = value.getModel().getFullName();

        if (positions == null || positions.size() == 0) {
            int index = str.indexOf("(");
            if (index > 0) {
                result.add(new Text(str.substring(0, index)));
                Text text = new Text(str.substring(index));
                text.setStyle("-fx-font-style: italic;-fx-font-size: 14px;-fx-fill: #0055aa;");
                result.add(text);
            }
        } else {
            if (positions.get(0).getBegin() > 0) {
                result.add(new Text(str.substring(0, positions.get(0).getBegin())));
            }
            for (int i = 0; i < positions.size(); i++) {
                Position position = positions.get(i);
                Text text = new Text(str.substring(position.getBegin(), position.getEnd()));
                text.setStyle("-fx-font-weight:bolder;");
                result.add(text);

                if (i < positions.size() - 1) {
                    result.add(new Text(str.substring(positions.get(i).getEnd(), positions.get(i + 1).getBegin())));
                }
            }
            if (positions.get(positions.size() - 1).getEnd() < str.length()) {
                int index = str.indexOf("(");
                if (index > 0) {
                    result.add(new Text(str.substring(positions.get(positions.size() - 1).getEnd(), index)));
                    Text text = new Text(str.substring(index));
                    text.setStyle("-fx-font-style: italic;-fx-font-size: 14px;-fx-fill: #0055aa;");
                    result.add(text);
                } else {
                    result.add(new Text(str.substring(positions.get(positions.size() - 1).getEnd(), str.length())));
                }
            }
        }
        return result;
    }

    protected ImageView getImageView(ModelPositions n) {
        return new ImageView(getImage(n));
    }

    protected abstract Image getImage(ModelPositions n);

    private void moveSelectedOnTap(KeyEvent e, ListView<ModelPositions> fromListView) {
        int nextIndex = fromListView.getSelectionModel().getSelectedIndex();
        if (e.isShiftDown())
            nextIndex -= 1;
        else
            nextIndex += 1;
        int size = fromListView.getItems().size();
        fromListView.getSelectionModel().select(((nextIndex % size) + size) % size);
    }

    public void setModels(ObservableList<ModelPositions> models) {
        lstModels.setItems(models);
        lstModels.getSelectionModel().select(0);
    }

    public void setModels(Stream<ModelPositions> models) {
        lstModels.setItems(FXCollections.observableArrayList());
        models.forEach(m -> lstModels.getItems().add(m));
        lstModels.getSelectionModel().select(0);
    }

    public void setModels(List<ModelPositions> models) {
        lstModels.setItems(FXCollections.observableArrayList());
        lstModels.getItems().addAll(models);
        lstModels.getSelectionModel().select(0);
    }

    public ModelPositions getSelectedModel() {
        if (selectedModel != null)
            return selectedModel.get();
        return null;
    }

    public void setSelectedModel(ModelPositions selectedModel) {
        if (this.selectedModel != null || selectedModel != null) {
            selectedModelProperty().set(selectedModel);
        }
    }

    public ObjectProperty<ModelPositions> selectedModelProperty() {
        if (selectedModel == null) {
            selectedModel = new SimpleObjectProperty<ModelPositions>(this, "selectedModel", null);
        }
        return selectedModel;
    }

    public TextField getTxfFilter() {
        return txfFilter;
    }

    public void setTxfFilter(TextField txfFilter) {
        this.txfFilter = txfFilter;
    }
}
