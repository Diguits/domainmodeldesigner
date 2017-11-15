package com.diguits.domainmodeldesigner.editors.views;

import java.util.List;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.controls.CodeEditor;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.StringConverter;

public class TemplateDefEditorView extends TemplateProjectItemEditorView<TemplateDefModel> {
	private TextField path;
	private ChoiceBox<String> applyOver;
	private ChoiceBox<String> applyOverCombinig;
	private TextField outputFilename;
	private CheckBox active;
	private CodeEditor sourceCodeEditor;
	private CodeEditor codeCodeEditor;
	private Button btnRefreshGeneratedCode;
	private Tab sourceTab;
	private Tab codeTab;
	private TableModelEditorView<TemplateParameterDefModel> templateParametersView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		path = nodeFactory.createTextFieldInsideGrid(generalGridPane, "%path");
		StringConverter<String> valueConverter = new StringConverter<String>() {

			@Override
			public String toString(String object) {
				if(object!=null){
					return object.substring(object.lastIndexOf(".")+1).replace("Def", "");
				}
				return null;
			}

			@Override
			public String fromString(String string) {
				if(string!=null)
					return string + "Def";
				return null;
			}
		};
		applyOver = nodeFactory.createChoiceBoxInsideGrid(generalGridPane, "%apply_over");
		applyOver.setConverter(valueConverter);
		applyOverCombinig = nodeFactory.createChoiceBoxInsideGrid(generalGridPane, "%apply_over_combining");
		applyOverCombinig.disableProperty().bind(applyOver.getSelectionModel().selectedIndexProperty().lessThan(0));
		applyOverCombinig.setConverter(valueConverter);
		outputFilename = nodeFactory.createTextFieldInsideGrid(generalGridPane, "%output_filename");
		active = nodeFactory.createCheckBoxInsideGrid(generalGridPane, "%active");


		templateParametersView = createTableEditorViewInNewTab(tabPane, "%parameters" , TemplateParameterDefModel.class, tvf -> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(DataType.class, "%has_visual_representation").width(90).valueFactory(cd -> cd.getValue().dataTypeProperty()).buildColumn()
				.buildTable());

		sourceTab = nodeFactory.createAndAddTab(tabPane, "%code_editor");
		sourceCodeEditor = new CodeEditor("");
		sourceTab.setContent(nodeFactory.addAndFitToAnchorPane(sourceCodeEditor));
		sourceCodeEditor.setOnDragOver(e -> {
			// If drag board has a string, let the event know that
			// the target accepts copy and move transfer modes
			Dragboard dragboard = e.getDragboard();
			if (dragboard.hasString()) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			e.consume();
		});

		codeTab = nodeFactory.createAndAddTab(tabPane, "%generated_code");
		codeCodeEditor = new CodeEditor("", true);
		codeTab.setContent(nodeFactory.addAndFitToAnchorPane(codeCodeEditor));
		btnRefreshGeneratedCode = new Button(null, new ImageView("/images/arrow_refresh.png"));

		codeCodeEditor.addCustomToolBarItems(new Node[] { btnRefreshGeneratedCode });
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(active.selectedProperty(), model.activeProperty());
			bind(path.textProperty(), model.pathProperty());
			bindBidirectional(outputFilename.textProperty(), model.outputFileNameExpressionProperty());
			bindBidirectional(sourceCodeEditor.editingCodeProperty(), model.sourceProperty());
			templateParametersView.setModel(getModel().parametersProperty());

		}
	}

	public Button getBtnRefreshGeneratedCode() {
		return btnRefreshGeneratedCode;
	}

	public void refreshGeneratedCode(String generatedCode) {
		codeCodeEditor.setEditingCode(generatedCode);
	}

	public void setApplyTypes(List<String> applyTypes) {
		applyOver.setItems(FXCollections.observableArrayList(applyTypes));
		ObservableList<String> list = FXCollections.observableArrayList(applyTypes);
		list.add(0, null);
		applyOverCombinig.setItems(list);
		if (getModel() != null) {
			bindBidirectional(applyOver.valueProperty(), model.applyClassNameProperty());
			bindBidirectional(applyOverCombinig.valueProperty(), model.applyClassName1Property());
		}
	}

	public void goToSourceCode(Position position) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tabPane.getSelectionModel().select(sourceTab);
				sourceCodeEditor.requestFocus();
				sourceCodeEditor.goTo(position.getLineNumber(), position.getColumnNumber());
			}
		});
	}

	public void goToGeneratedCode(Position position) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tabPane.getSelectionModel().select(codeTab);
				codeCodeEditor.requestFocus();
				codeCodeEditor.goTo(position.getLineNumber(), position.getColumnNumber());
			}
		});
	}

	public void goToFileName(Position position) {
		tabPane.getSelectionModel().select(0);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				outputFilename.requestFocus();
				outputFilename.positionCaret((int) (long) position.getColumnNumber());
			}
		});
	}

	public TableModelEditorView<TemplateParameterDefModel> getTemplateParametersView() {
		return templateParametersView;
	}

}
