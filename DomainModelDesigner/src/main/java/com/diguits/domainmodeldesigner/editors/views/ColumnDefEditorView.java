package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.javafx.controls.IntegerSpinnerValueFactory;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ColumnDefEditorView extends BaseDefEditorView<ColumnDefModel> {

    private Spinner<Integer> spnWidthWeight;
    private TextField txfPath;
    private CheckBox chbVisible;
    private CheckBox chbCanFilter;
    private CheckBox chbCanOrder;
    private Button btnEditPath;

    @Override
    protected Node buildContentView() {
        Node contentView = super.buildContentView();
        Tab tab = nodeFactory.createAndAddTab(tabPane, "%column");
        GridPane gridPane = nodeFactory.createGridPaneForEdit();
        nodeFactory.createLabelInsideGrid(gridPane, "%path", 0);

        HBox hBox = new HBox();
        txfPath = nodeFactory.createTextField();
        txfPath.setPrefWidth(500);
        HBox.setMargin(txfPath, new Insets(2.0, 2.0, 2.0, 2.0));
        btnEditPath = nodeFactory.createButton16x16("images/search_tree.png");
        HBox.setMargin(btnEditPath, new Insets(2.0, 2.0, 2.0, 2.0));
        hBox.getChildren().addAll(txfPath, btnEditPath);
        gridPane.add(hBox, 1, 0);

        spnWidthWeight = nodeFactory.createSpinnerInsideGrid(gridPane, "%width_weight");
        chbVisible = nodeFactory.createCheckBoxInsideGrid(gridPane, "%visible");
        chbCanFilter = nodeFactory.createCheckBoxInsideGrid(gridPane, "%can_order");
        chbCanOrder = nodeFactory.createCheckBoxInsideGrid(gridPane, "%can_filter");

        tab.setContent(nodeFactory.wrapInScrollPane(gridPane));
        return contentView;
    }

    @Override
    protected void bindFieldsToModel() {
        super.bindFieldsToModel();
        if (getModel() != null) {
            IntegerSpinnerValueFactory integerValueFactory = new IntegerSpinnerValueFactory(
                    1, Integer.MAX_VALUE);
            bindBidirectional(integerValueFactory.valueProperty(), getModel().widthWeightProperty().asObject());
            spnWidthWeight.setValueFactory(integerValueFactory);

            bindBidirectional(chbVisible.selectedProperty(), getModel().visibleProperty());
            bindBidirectional(chbCanFilter.selectedProperty(), getModel().canFilterProperty());
            bindBidirectional(chbCanOrder.selectedProperty(), getModel().canOrderProperty());

            bindBidirectional(txfPath.textProperty(), getModel().pathProperty());
        }
    }

    public Button getBtnEditPath() {
        return btnEditPath;
    }

}
