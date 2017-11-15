package com.diguits.javafx.concurrent.views;

import com.diguits.javafx.concurrent.TaskBase;
import com.diguits.javafx.views.ModelView;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ExecuteTaskView<TModel extends TaskBase<?>> extends ModelView<AnchorPane,TModel> {

	private Label lblOperation;
	private Label lblOver;
	private Label lblWorkDone;
	private Label lblTotalWork;
	private ProgressBar prgProgress;


	@Override
	protected void bindFieldsToModel() {
		bind(prgProgress.progressProperty(),getModel().progressProperty());
		bind(lblOver.textProperty(),getModel().messageProperty());
		bind(lblWorkDone.textProperty(),getModel().workDoneProperty().asString("%.0f"));
		bind(lblTotalWork.textProperty(),getModel().totalWorkProperty().asString("%.0f"));
	}

	@Override
	protected AnchorPane buildView() {
		GridPane gridPane = nodeFactory.createGridPaneForEditFourColumns();
		nodeFactory.createLabelInsideGrid(gridPane, "%executing", 0);
		lblOperation = nodeFactory.createLabelInsideGrid(gridPane, "", 1, 0);

		Font font = Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, Font.getDefault().getSize());
		lblOperation.setFont(font);
		nodeFactory.createLabelInsideGrid(gridPane, "%over", 1);
		lblOver = nodeFactory.createLabelInsideGrid(gridPane, "", 1, 1);
		lblOver.setFont(font);

		Label lblProcessedModelLabel = nodeFactory.createLabelInsideGrid(gridPane, "%processed", 2, 0);
		GridPane.setRowSpan(lblProcessedModelLabel, 2);
		HBox hBox = new HBox();

		hBox.setAlignment(Pos.CENTER);
		lblWorkDone = nodeFactory.createLabel("");
		lblWorkDone.setFont(new Font(23.0));
		Label lblSeparator = nodeFactory.createLabel("/");
		lblTotalWork = nodeFactory.createLabel("");
		lblTotalWork.setFont(font);
		hBox.getChildren().addAll(lblWorkDone, lblSeparator, lblTotalWork);
		GridPane.setMargin(hBox, new Insets(0, 5, 0, 5));
		gridPane.add(hBox, 3, 0);
		GridPane.setRowSpan(hBox, 2);

		AnchorPane ancProgress = new AnchorPane();
		prgProgress = new ProgressBar();
		prgProgress.setProgress(0.0);
		nodeFactory.fitToAnchorPane(prgProgress);

		ancProgress.getChildren().addAll(prgProgress);
		ancProgress.setPadding(new Insets(2.0, 2.0, 2.0, 2.0));
		gridPane.add(ancProgress, 0, 3);
		GridPane.setColumnSpan(ancProgress, 4);

		for (RowConstraints row : gridPane.getRowConstraints()){
			row.setVgrow(null);
			row.setPrefHeight(Region.USE_COMPUTED_SIZE);
		}

		return nodeFactory.addAndFitToAnchorPane(gridPane);
	}

	public void setOperationName(ObservableValue<? extends String> operationNameProperty) {
		lblOperation.textProperty().bind(operationNameProperty);
	}
}
