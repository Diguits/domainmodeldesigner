package com.diguits.domainmodeldesigner.template.views;

import javafx.beans.property.ListProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import com.diguits.domainmodeldesigner.editors.views.TemplateProjectApplyConfigEditorView;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.views.ModelView;

import javafx.scene.layout.*;
import javafx.util.StringConverter;

public final class TemplateProjectApplyView extends ModelView<BorderPane, TemplateProjectApplyConfigModel> {

    private ComboBox<TemplateProjectApplyConfigModel> cbxApplyProjectConfig;
    private Button btnAddProjectConfig;
    private Node content;

    @Override
    protected void bindFieldsToModel() {

    }

    @Override
    protected BorderPane buildView() {
        BorderPane borderPane = new BorderPane();

        GridPane gridPane = nodeFactory.createGridPaneForEdit();

        nodeFactory.createLabelInsideGrid(gridPane, "%apply_project_config", 0);
        HBox hBox = new HBox();
        cbxApplyProjectConfig = nodeFactory.createComboBox();
        btnAddProjectConfig = nodeFactory.createButton16x16("images/add.png");
        HBox.setMargin(btnAddProjectConfig, new Insets(0, 2, 0, 2));
        hBox.getChildren().addAll(cbxApplyProjectConfig, btnAddProjectConfig);
        hBox.setAlignment(Pos.CENTER_LEFT);
        gridPane.add(hBox, 1, 0);
        GridPane.setMargin(hBox, new Insets(2));
        borderPane.setTop(gridPane);
        cbxApplyProjectConfig.setEditable(true);

        cbxApplyProjectConfig.setConverter(new StringConverter<TemplateProjectApplyConfigModel>() {

            @Override
            public String toString(TemplateProjectApplyConfigModel model) {
                return model == null ? null : model.getName();
            }

            @Override
            public TemplateProjectApplyConfigModel fromString(String string) {
                return cbxApplyProjectConfig.getItems().stream().filter(m -> m.getName().equals(string)).findFirst().get();
            }
        });

        /*cbxApplyProjectConfig.getEditor().textProperty().addListener((v, o, n) -> {
            TemplateProjectApplyConfigModel result = cbxApplyProjectConfig.getSelectionModel().getSelectedItem();
            if (result != null)
                result.setName(n);
        });*/

        return borderPane;
    }

    public void setTemplateProjectApplyConfigEditorView(TemplateProjectApplyConfigEditorView view) {
        if (content != null)
            content.disableProperty().unbind();
        content = view.getNodeView();
        content.disableProperty().bind(cbxApplyProjectConfig.getSelectionModel().selectedItemProperty().isNull());
        getNodeView().setCenter(content);
    }

    public ComboBox<TemplateProjectApplyConfigModel> getCbxApplyProjectConfig() {
        return cbxApplyProjectConfig;
    }

    public void setProjectConfigs(ListProperty<TemplateProjectApplyConfigModel> configsProperty) {
        cbxApplyProjectConfig.itemsProperty().bind(configsProperty);
    }

    public void selectProjectConfig(TemplateProjectApplyConfigModel config) {
        cbxApplyProjectConfig.getSelectionModel().select(config);
    }

    public Button getBtnAddProjectConfig() {
        return btnAddProjectConfig;
    }
}
