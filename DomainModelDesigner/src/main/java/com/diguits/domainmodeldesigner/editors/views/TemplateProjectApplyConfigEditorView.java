package com.diguits.domainmodeldesigner.editors.views;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;

import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;

public final class TemplateProjectApplyConfigEditorView
		extends TemplateProjectApplyConfigItemEditorView<TemplateProjectApplyConfigModel> {

	private TextField txfOutputPath;
	private Button btnEditOutputPath;
	private TableColumn<TemplateApplyConfigModel, String> colTemplateName;
	private TableColumn<TemplateApplyConfigModel, String> colTemplateOutputPath;
	private TableColumn<TemplateApplyConfigModel, String> colTemplateFilename;
	private TableView<TemplateApplyConfigModel> tblTemplates;
	private Button btnAddTemplate;
	private Button btnDeleteTemplate;
	private Button btnUpTemplate;
	private Button btnDownTemplate;
	private BorderPane bpaConfig;

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(txfOutputPath.textProperty(),getModel().outputPathProperty());
			tblTemplates.setItems(getModel().getTemplatesConfig());
		} else{
			tblTemplates.setItems(null);
			txfOutputPath.setText("");
		}
	}

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();

		Tab tabConfig = nodeFactory.createAndAddTab(tabPane, "%config");
		bpaConfig = new BorderPane();
		GridPane gridConfig = nodeFactory.createGridPaneForEdit();
		nodeFactory.createLabelInsideGrid(gridConfig, "%root_directory", 0);

		HBox hBox = new HBox();
		txfOutputPath = nodeFactory.createTextField();
		HBox.setMargin(txfOutputPath, new Insets(2.0, 2.0, 2.0, 2.0));
		HBox.setHgrow(txfOutputPath, Priority.SOMETIMES);
		btnEditOutputPath = nodeFactory.createButton16x16("images/folder_explorer.png");
		btnEditOutputPath.setOnAction(e -> {
			DirectoryChooser directoryChooser = nodeFactory.createDirectoryChooser(txfOutputPath.getText());
			File selectedFile = directoryChooser.showDialog(null);
			if (selectedFile != null)
				txfOutputPath.setText(selectedFile.getAbsolutePath());
		});
		HBox.setMargin(btnEditOutputPath, new Insets(2.0, 2.0, 2.0, 2.0));
		hBox.getChildren().addAll(txfOutputPath, btnEditOutputPath);
		gridConfig.add(hBox, 1, 0);

		nodeFactory.createLabelInsideGrid(gridConfig, "%templates", 1);
		tabConfig.setContent(nodeFactory.wrapInScrollPane(gridConfig));

		bpaConfig.setTop(gridConfig);

		HBox hbxTemplates = new HBox();
		tblTemplates = nodeFactory.createTableView();
		colTemplateName = nodeFactory.createAndAddTableColumn(tblTemplates, "%name", 150.0);
		colTemplateOutputPath = nodeFactory.createAndAddTableColumn(tblTemplates, "%output_path", 400.0);
		colTemplateFilename = nodeFactory.createAndAddTableColumn(tblTemplates, "%output_filename", 200.0);

		VBox vbxBottons = new VBox();
		btnAddTemplate = nodeFactory.createButton16x16("images/add.png");
		VBox.setMargin(btnAddTemplate, new Insets(2));
		btnDeleteTemplate = nodeFactory.createButton16x16("images/delete.png");
		VBox.setMargin(btnDeleteTemplate, new Insets(2));
		btnUpTemplate = nodeFactory.createButton16x16("images/arrow_up.png");
		VBox.setMargin(btnUpTemplate, new Insets(2));
		btnDownTemplate = nodeFactory.createButton16x16("images/arrow_down.png");
		VBox.setMargin(btnDownTemplate, new Insets(2));
		vbxBottons.getChildren().addAll(btnAddTemplate, btnDeleteTemplate, btnUpTemplate, btnDownTemplate);
		HBox.setHgrow(tblTemplates, Priority.SOMETIMES);
		hbxTemplates.getChildren().addAll(tblTemplates, vbxBottons);

		btnDeleteTemplate.disableProperty().bind(tblTemplates.getSelectionModel().selectedItemProperty().isNull());

		btnUpTemplate.disableProperty().bind(tblTemplates.getSelectionModel().selectedItemProperty().isNull()
				.or(tblTemplates.getSelectionModel().selectedIndexProperty().isEqualTo(0)));

		// este binding no funciono
		/*
		 * btnDownTemplate.disableProperty(),tblTemplates.getSelectionModel
		 * ().selectedItemProperty().isNull().or(tblTemplates
		 * .getSelectionModel().selectedIndexProperty().isEqualTo(Bindings.size(
		 * tblTemplates.getItems()).subtract(1))));
		 */
		btnDownTemplate.setDisable(true);
		tblTemplates.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
			btnDownTemplate.setDisable(n == null
					|| tblTemplates.getSelectionModel().getSelectedIndex() == tblTemplates.getItems().size() - 1);
		});

		bpaConfig.setCenter(hbxTemplates);
		tabConfig.setContent(nodeFactory.addAndFitToAnchorPane(bpaConfig));

		colTemplateName.setCellValueFactory(cd -> cd.getValue().getTemplate().nameProperty());
		colTemplateOutputPath.setCellValueFactory(cd -> cd.getValue().outputPathExpressionProperty());
		colTemplateFilename.setCellValueFactory(cd -> cd.getValue().outputFilenameExpressionProperty());

		return contentView;
	}

	public void setTemplateApplyView(TemplateApplyConfigEditorView view) {
		bpaConfig.setBottom(view.getNodeView());

	}

	public Button getBtnUpTemplate() {
		return btnUpTemplate;
	}

	public Button getBtnDownTemplate() {
		return btnDownTemplate;
	}

	public TableView<TemplateApplyConfigModel> getTblTemplates() {
		return tblTemplates;
	}

	public TextField getTxfOutputPath() {
		return txfOutputPath;
	}

	public Button getBtnAddTemplate() {
		return btnAddTemplate;
	}

	public Button getBtnDeleteTemplate() {
		return btnDeleteTemplate;
	}

}
