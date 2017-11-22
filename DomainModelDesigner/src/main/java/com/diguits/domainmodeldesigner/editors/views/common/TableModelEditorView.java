package com.diguits.domainmodeldesigner.editors.views.common;

import com.diguits.javafx.views.INodeFactoryHelper;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public abstract class TableModelEditorView<TModel> extends TableModelEditorViewBase<TModel> {

    protected Button btnUp;
    protected Button btnDown;
    protected TableView<TModel> tblData;
    private boolean canOrder = true;
    private boolean autoHeight;

    public TableModelEditorView(INodeFactoryHelper nodeFactory) {
        super(nodeFactory);
    }

    public boolean canOrder() {
        return canOrder;
    }

    @Override
    protected void bindFieldsToModel() {
        bindBidirectional(tblData.itemsProperty(), getModel());
        if(autoHeight) {
            tblData.setFixedCellSize(35);
            tblData.prefHeightProperty().bind(tblData.fixedCellSizeProperty().multiply(Bindings.size(tblData.getItems())).add(36));
        }
        bindColumns();

        tblData.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && tblData.getSelectionModel().getSelectedItem() != null
                    && onTableDoubleClick != null)
                onTableDoubleClick.handle(e);
        });
        if (btnEdit != null)
            bind(btnEdit.disableProperty(), tblData.getSelectionModel().selectedItemProperty().isNull());
        if (btnDelete != null)
            bind(btnDelete.disableProperty(), tblData.getSelectionModel().selectedItemProperty().isNull());
        if (btnUp != null)
            bind(btnUp.disableProperty(), tblData.getSelectionModel().selectedItemProperty().isNull()
                    .or(tblData.getSelectionModel().selectedIndexProperty().isEqualTo(0)));
        if (btnDown != null)
            bind(btnDown.disableProperty(),
                    tblData.getSelectionModel().selectedItemProperty().isNull().or(tblData.getSelectionModel()
                            .selectedIndexProperty().isEqualTo(Bindings.size(tblData.getItems()).subtract(1))));

    }

    protected abstract void bindColumns();

    @Override
    protected AnchorPane buildView() {
        AnchorPane anchorPane = super.buildView();

        if (canOrder()) {
            btnUp = nodeFactory.createButton16x16("/images/arrow_up.png");
            btnDown = nodeFactory.createButton16x16("/images/arrow_down.png");
            toolBar.getItems().addAll(btnUp, btnDown);
        }
        return anchorPane;
    }

    @Override
    protected Control createTable() {
        tblData = nodeFactory.createTableView();
        tblData.getFocusModel().focusedCellProperty().addListener(
                (ObservableValue<? extends TablePosition> observable, TablePosition oldValue, TablePosition newValue ) ->
                {
                    if ( newValue != null )
                    {
                        Platform.runLater( () ->
                        {
                            tblData.edit( newValue.getRow(), newValue.getTableColumn() );
                        } );
                    }
                }
        );
        return tblData;
    }

    public Button getBtnUp() {
        return btnUp;
    }

    public Button getBtnDown() {
        return btnDown;
    }

    public TableView<TModel> getTblData() {
        return tblData;
    }

    public void setCanOrder(boolean canOrder) {
        this.canOrder = canOrder;
    }

    public void setAutoHeight(boolean autoHeight) {
        this.autoHeight = autoHeight;
    }

    public boolean isAutoHeight() {
        return autoHeight;
    }
}
