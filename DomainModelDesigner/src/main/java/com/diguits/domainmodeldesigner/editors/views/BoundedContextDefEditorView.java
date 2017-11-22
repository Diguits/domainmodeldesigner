package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.SwapModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class BoundedContextDefEditorView extends BaseDefEditorView<BoundedContextDefModel> {

    private CheckBox chbMandatoryOnLoad;
    private TextField txfPrefix;
    private CheckBox chbIncludeInLicence;


    TableModelEditorView<ModuleDefModel> modulesView;
    private TableModelEditorView<EnumDefModel> enumsView;
    private SwapModelEditorView<BoundedContextDefModel> dependenciesSwapView;

    @Override
    protected Node buildContentView() {
        Node contentView = super.buildContentView();
        Tab tab = nodeFactory.createAndAddTab(tabPane, "%boundedContext");
        GridPane gridPane = nodeFactory.createGridPaneForEdit();
        chbMandatoryOnLoad = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, 0, "%mandatory_on_load");
        txfPrefix = nodeFactory.createTextFieldInsideGrid(gridPane, 1, 1, "%prefix");
        chbIncludeInLicence = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, 2, "%include_in_licence");
        tab.setContent(nodeFactory.wrapInScrollPane(gridPane));

        modulesView = createTableEditorViewInNewTab(tabPane, "%modules", ModuleDefModel.class, tvf -> tvf
                .add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
                .add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
                .buildTable());

        enumsView = createTableEditorViewInNewTab(tabPane, "%enums", EnumDefModel.class, tvf -> tvf
                .add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
                .add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
                .add(String.class, "%values").width(250).valueFactory(cd -> cd.getValue().valuesProperty().asString()).buildColumn()
                .buildTable());

        tab = nodeFactory.createAndAddTab(tabPane, "%dependencies");
        dependenciesSwapView = new SwapModelEditorView<BoundedContextDefModel>("%boundedContext_to_add", "%boundedContexts", nodeFactory);
        AnchorPane anchorPane = dependenciesSwapView.getNodeView();
        tab.setContent(anchorPane);

        return contentView;
    }

    @Override
    protected void bindFieldsToModel() {
        super.bindFieldsToModel();
        if (getModel() != null) {
            bindBidirectional(chbMandatoryOnLoad.selectedProperty(), getModel().mandatoryInLoadProperty());
            bindBidirectional(txfPrefix.textProperty(), getModel().prefixProperty());
            bindBidirectional(chbIncludeInLicence.selectedProperty(), getModel().includedInLicenseProperty());
            modulesView.setModel(getModel().modulesProperty());
            //enumsView.setModel(getModel().enumsProperty());
            dependenciesSwapView.setModel(getModel().dependenciesProperty());
        }
    }

    public TableModelEditorView<ModuleDefModel> getModulesView() {
        return modulesView;
    }

    public TableModelEditorView<EnumDefModel> getEnumsView() {
        return enumsView;
    }

    public SwapModelEditorView<BoundedContextDefModel> getDependenciesSwapView() {
        return dependenciesSwapView;
    }
}
