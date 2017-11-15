package com.diguits.domainmodeldesigner.template.views;
import javafx.beans.binding.IntegerExpression;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import com.diguits.javafx.views.ViewBase;

import javafx.scene.layout.*;

public final class TemplateProjectApplyingView  extends ViewBase<BorderPane>{
	private Label lblTemplatesApplied;
	private Label lblTotalTemplates;
	private ProgressBar prgProgress;
	private VBox vbxTemplateApplyingContainer;

	@Override
	protected BorderPane buildView() {
		BorderPane borderPane = new BorderPane();
		Font font = Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, Font.getDefault().getSize());

		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		nodeFactory.addRowConstraints(gridPane, 1);
		Label lblTemplatesAppliedCaption = nodeFactory.createLabelInsideGrid(gridPane, "%templates_applied", 0);
		lblTemplatesAppliedCaption.setFont(font);

		//General indicators total templates\templates applied
		HBox hBox = new HBox();

		AnchorPane ancProcessedModel = new AnchorPane();
		hBox.setAlignment(Pos.CENTER);
		lblTemplatesApplied = nodeFactory.createLabel("");
		lblTemplatesApplied.setFont(new Font(23.0));
		ancProcessedModel.getChildren().addAll(lblTemplatesApplied);
		Label lblSeparator = nodeFactory.createLabel("/");

		lblTotalTemplates = nodeFactory.createLabel("");
		lblTotalTemplates.setFont(font);
		hBox.getChildren().addAll(ancProcessedModel, lblSeparator, lblTotalTemplates);
		GridPane.setMargin(hBox, new Insets(0, 5, 0, 5));


		gridPane.add(hBox, 1, 0);


		AnchorPane ancProgress = new AnchorPane();
		prgProgress = new ProgressBar();
		prgProgress.setProgress(0.0);
		nodeFactory.fitToAnchorPane(prgProgress);

		ancProgress.getChildren().addAll(prgProgress);
		ancProgress.setPadding(new Insets(2.0, 2.0, 2.0, 2.0));
		gridPane.add(ancProgress, 0, 1);
		GridPane.setColumnSpan(ancProgress, 2);

		Separator sepHeaderSeparator = new Separator(Orientation.HORIZONTAL);
		gridPane.add(sepHeaderSeparator, 0, 2);
		GridPane.setColumnSpan(sepHeaderSeparator, 2);

		borderPane.setTop(gridPane);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		vbxTemplateApplyingContainer = new VBox();
		scrollPane.setContent(vbxTemplateApplyingContainer);
		borderPane.setCenter(scrollPane);
		return borderPane;
	}

	public void addTemplateApplying(Node nodeView) {
		vbxTemplateApplyingContainer.getChildren().add(nodeView);
	}

	public void updateProgress(double p) {
		prgProgress.setProgress(p);
	}

	public void initialize(int templateCount, IntegerExpression templatesApplied){
		vbxTemplateApplyingContainer.getChildren().clear();
		lblTotalTemplates.setText(Integer.toString(templateCount));
		lblTemplatesApplied.textProperty().bind(templatesApplied.asString());
	}
}
