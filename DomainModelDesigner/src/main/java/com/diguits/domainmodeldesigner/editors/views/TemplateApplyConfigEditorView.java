package com.diguits.domainmodeldesigner.editors.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.controlsfx.control.PropertySheet;

import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateParamValueModel;
import com.google.inject.Inject;

import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;

public final class TemplateApplyConfigEditorView extends
		TemplateProjectApplyConfigItemEditorView<TemplateApplyConfigModel> implements ChangeListener<BaseDefModel> {

	@Inject
	CustomPropertyEditorFactory customPropertyEditorFactory;

	private TextField txfTemplate;
	private CheckBox chbForSpecificModel;
	private ComboBox<BaseDefModel> cbxModel;
	private ComboBox<BaseDefModel> cbxModelCombinig;
	private TextField txfCondition;
	private CheckBox chbInMemory;
	private TextField txfOutputPath;
	private TextField txfOutputFilename;
	private CheckBox chbWaitForBefore;
	private Button btnEditOutputPath;
	private Tab customFieldValuesTab;
	private PropertySheet pshParamValues;

	@Override
	public void setModel(TemplateApplyConfigModel model) {
		if (getModel() != null) {
			cbxModel.getSelectionModel().selectedItemProperty().removeListener(this);
			cbxModelCombinig.getSelectionModel().selectedItemProperty().removeListener(this);
		}
		super.setModel(model);
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (model != null) {
			chbForSpecificModel.setSelected(getModel().getModelId() != null);
			chbForSpecificModel.selectedProperty().addListener((v, o, n) -> {
				if (!n && model != null)
					model.setModelId(null);
				cbxModel.getSelectionModel().select(null);
				cbxModelCombinig.getSelectionModel().select(null);
			});

			bind(txfTemplate.textProperty(), getModel().getTemplate().nameProperty());
			bindBidirectional(chbInMemory.selectedProperty(), getModel().inMemoryProperty());
			bindBidirectional(txfCondition.textProperty(), getModel().conditionExpressionProperty());
			bindBidirectional(txfOutputPath.textProperty(), getModel().outputPathExpressionProperty());
			bindBidirectional(txfOutputFilename.textProperty(), getModel().outputFilenameExpressionProperty());
			bindBidirectional(chbWaitForBefore.selectedProperty(), getModel().waitForBeforeProperty());

			cbxModel.getSelectionModel().selectedItemProperty().addListener(this);
			cbxModelCombinig.getSelectionModel().selectedItemProperty().addListener(this);

			tabPane.setDisable(false);

			updateItemList();
			getModel().getTemplateParamValues().addListener((ListChangeListener<TemplateParamValueModel>) c -> {
				updateItemList();
			});
		} else {
			tabPane.setDisable(true);
			chbForSpecificModel.setSelected(false);
		}
	}

	@Override
	protected void unBindFieldsToModel() {
		super.unBindFieldsToModel();
		cbxModel.getSelectionModel().selectedItemProperty().removeListener(this);
		cbxModelCombinig.getSelectionModel().selectedItemProperty().removeListener(this);
	}

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();

		Tab tabConfig = nodeFactory.createAndAddTab(tabPane, "%config");

		GridPane gridConfig = nodeFactory.createGridPaneForEdit();
		txfTemplate = nodeFactory.createTextFieldInsideGrid(gridConfig, "%template");
		txfTemplate.setDisable(true);
		chbForSpecificModel = nodeFactory.createCheckBoxInsideGrid(gridConfig, "%for_specific_model");
		cbxModel = nodeFactory.createComboBoxInsideGrid(gridConfig, "%model");
		cbxModelCombinig = nodeFactory.createComboBoxInsideGrid(gridConfig, "%model_combining_with");
		txfCondition = nodeFactory.createTextFieldInsideGrid(gridConfig, "%condition");
		chbInMemory = nodeFactory.createCheckBoxInsideGrid(gridConfig, "%in_memory");

		nodeFactory.createLabelInsideGrid(gridConfig, "%output_path", 6);

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
		gridConfig.add(hBox, 1, 6);

		txfOutputFilename = nodeFactory.createTextFieldInsideGrid(gridConfig, "%output_filename");
		chbWaitForBefore = nodeFactory.createCheckBoxInsideGrid(gridConfig, "%wait_for_before");

		tabConfig.setContent(nodeFactory.wrapInScrollPane(gridConfig));


		cbxModel.disableProperty().bind(chbForSpecificModel.selectedProperty().not());
		cbxModelCombinig.disableProperty().bind(chbForSpecificModel.selectedProperty().not());
		txfCondition.disableProperty().bind(chbForSpecificModel.selectedProperty());
		txfOutputPath.disableProperty().bind(chbInMemory.selectedProperty());
		btnEditOutputPath.disableProperty().bind(chbInMemory.selectedProperty());
		txfOutputFilename.disableProperty().bind(chbInMemory.selectedProperty());

		customFieldValuesTab = nodeFactory.createAndAddTab(tabPane, "%parameters");
		pshParamValues = new PropertySheet();
		pshParamValues.setPropertyEditorFactory(customPropertyEditorFactory);
		customFieldValuesTab.setContent(pshParamValues);

		return contentView;
	}

	public Button getBtnEditOutputPath() {
		return btnEditOutputPath;
	}

	public CheckBox getChbForSpecificModel() {
		return chbForSpecificModel;
	}

	public void setModels(List<List<? extends BaseDefModel>> models, List<UUID> modelIds) {
		cbxModel.getItems().clear();
		cbxModelCombinig.getItems().clear();
		if (models != null) {
			if (models.size() > 0 && models.get(0) != null) {
				cbxModel.getItems().addAll(models.get(0));
				if (modelIds != null && modelIds.size() > 0) {
					Optional<? extends BaseDefModel> selectedModel = models.get(0).stream()
							.filter(m -> m.getId().equals(modelIds.get(0))).findAny();
					if (selectedModel.isPresent())
						cbxModel.getSelectionModel().select(selectedModel.get());
				}
			}
			if (models.size() > 1 && models.get(1) != null) {
				cbxModelCombinig.getItems().addAll(models.get(1));
				if (modelIds != null && modelIds.size() > 1) {
					Optional<? extends BaseDefModel> selectedModel = models.get(1).stream()
							.filter(m -> m.getId().equals(modelIds.get(1))).findAny();
					if (selectedModel.isPresent())
						cbxModelCombinig.getSelectionModel().select(selectedModel.get());
				}
			}

		}
	}

	@Override
	public void changed(ObservableValue<? extends BaseDefModel> observable, BaseDefModel oldValue,
			BaseDefModel newValue) {
		UUID id = newValue != null ? newValue.getId() : null;
		if (observable == cbxModel.getSelectionModel().selectedItemProperty()) {
			getModel().setModelId(id);
		} else {
			getModel().setModelId1(id);
		}

	}

	public void updateItemList() {
		ObservableList<CustomDataValueItem> customFieldValueItems = FXCollections.observableArrayList();
		for (TemplateParamValueModel paramValue : getModel().getTemplateParamValues()) {
			if (paramValue.getTemplateParameter() != null)
				customFieldValueItems.add(new CustomDataValueItem(paramValue.getTemplateParameter().getName(),
						paramValue.getTemplateParameter().getDescription(),
						paramValue.getTemplateParameter().getDataType(),
						paramValue.getTemplateParameter().getReference(),
						paramValue.getTemplateParameter().getMultiline(), paramValue.valueProperty()));
		}
		pshParamValues.getItems().clear();
		pshParamValues.getItems().addAll(customFieldValueItems);
	}
}
