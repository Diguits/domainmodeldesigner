package com.diguits.domainmodeldesigner.template.views;

import java.io.File;

import com.diguits.domainmodeldesigner.editors.views.TemplateApplyConfigEditorView;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.views.ModelView;

import javafx.beans.property.ListProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
	import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.util.StringConverter;

public final class TemplateApplyView extends ModelView<BorderPane, TemplateApplyConfigModel> {

	private ComboBox<TemplateProjectApplyConfigModel> cbxApplyProjectConfig;
	private Button btnAddProjectConfig;
	private TextField txfProjectOutputPath;
	private Button btnEditProjectOutputPath;


	private ComboBox<TemplateApplyConfigModel> cbxApplyConfig;
	private Button btnAddConfig;

	private Node content;


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

		nodeFactory.createLabelInsideGrid(gridPane, "%apply_project_root_directory", 1);

		hBox = new HBox();
		txfProjectOutputPath = nodeFactory.createTextField();
		HBox.setHgrow(txfProjectOutputPath, Priority.SOMETIMES);
		btnEditProjectOutputPath = nodeFactory.createButton16x16("images/folder_explorer.png");
		btnEditProjectOutputPath.setOnAction(e -> {
			DirectoryChooser directoryChooser = nodeFactory.createDirectoryChooser(txfProjectOutputPath.getText());
			File selectedFile = directoryChooser.showDialog(null);
			if (selectedFile != null)
				txfProjectOutputPath.setText(selectedFile.getAbsolutePath());
		});
		HBox.setMargin(btnEditProjectOutputPath, new Insets(0, 2, 0, 2));
		hBox.getChildren().addAll(txfProjectOutputPath, btnEditProjectOutputPath);
		gridPane.add(hBox, 1, 1);
		GridPane.setMargin(hBox, new Insets(2));

		nodeFactory.createLabelInsideGrid(gridPane, "%apply_config", 2);
		hBox = new HBox();
		cbxApplyConfig = nodeFactory.createComboBox();
		btnAddConfig = nodeFactory.createButton16x16("images/add.png");
		HBox.setMargin(btnAddConfig, new Insets(0, 2, 0, 2));
		hBox.getChildren().addAll(cbxApplyConfig, btnAddConfig);
		hBox.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(hBox, 1, 2);
		GridPane.setMargin(hBox, new Insets(2));
		borderPane.setTop(gridPane);
		BorderPane.setMargin(gridPane, new Insets(2));

		cbxApplyProjectConfig.setEditable(true);
		cbxApplyProjectConfig.setConverter(new StringConverter<TemplateProjectApplyConfigModel>() {

			@Override
			public String toString(TemplateProjectApplyConfigModel model) {
				return model == null ? null : model.getName();
			}

			@Override
			public TemplateProjectApplyConfigModel fromString(String string) {
				return null;
			}
		});

		cbxApplyProjectConfig.getEditor().textProperty().addListener((v, o, n) -> {
			TemplateProjectApplyConfigModel result = cbxApplyProjectConfig.getSelectionModel().getSelectedItem();
			if (result != null)
				result.setName(n);
		});

		cbxApplyConfig.setEditable(true);
		cbxApplyConfig.setConverter(new StringConverter<TemplateApplyConfigModel>() {

			@Override
			public String toString(TemplateApplyConfigModel model) {
				return model == null ? null : model.getName();
			}

			@Override
			public TemplateApplyConfigModel fromString(String string) {
				return null;
			}
		});

		cbxApplyConfig.getEditor().textProperty().addListener((v, o, n) -> {
			TemplateApplyConfigModel result = cbxApplyConfig.getSelectionModel().getSelectedItem();
			if (result != null)
				result.setName(n);
		});
		return borderPane;
	}

	@Override
	protected void bindFieldsToModel() {
	}

	public void setTemplateApplyConfigEditorView(TemplateApplyConfigEditorView view) {
		if (content != null)
			content.disableProperty().unbind();
		content = view.getNodeView();
		content.disableProperty().bind(cbxApplyConfig.getSelectionModel().selectedItemProperty().isNull());
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

	public ComboBox<TemplateApplyConfigModel> getCbxApplyConfig() {
		return cbxApplyConfig;
	}

	public void setConfigs(ObservableList<TemplateApplyConfigModel> configs) {
		cbxApplyConfig.getItems().clear();
		cbxApplyConfig.getItems().addAll(configs);
		if(cbxApplyConfig.getItems().size()>0)
			cbxApplyConfig.getSelectionModel().select(0);
		configs.addListener(new ListChangeListener<TemplateApplyConfigModel>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TemplateApplyConfigModel> c) {
				if(c.next()){
					for (TemplateApplyConfigModel templateApplyConfigModel : c.getAddedSubList()) {
						cbxApplyConfig.getItems().add(templateApplyConfigModel);
					}
					for (TemplateApplyConfigModel templateApplyConfigModel : c.getRemoved()) {
						cbxApplyConfig.getItems().remove(templateApplyConfigModel);
					}
				}
			}
		});
	}

	public void selectConfig(TemplateApplyConfigModel config) {
		cbxApplyConfig.getSelectionModel().select(config);
	}

	public Button getBtnAddConfig() {
		return btnAddConfig;
	}

	public TextField getTxfProjectOutputPath() {
		return txfProjectOutputPath;
	}
}
