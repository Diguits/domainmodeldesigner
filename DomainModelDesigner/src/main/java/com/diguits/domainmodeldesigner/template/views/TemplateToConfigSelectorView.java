package com.diguits.domainmodeldesigner.template.views;

import com.diguits.domainmodeldesigner.template.controllers.TemplateTreeBuilder;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectItemDefModel;
import com.diguits.javafx.views.ModelView;
import com.google.inject.Inject;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TemplateToConfigSelectorView extends ModelView<AnchorPane, ObservableList<TemplateDefModel>> {

	@Inject
	TemplateTreeBuilder templateTreeBuilder;

	protected TreeView<TemplateProjectItemDefModel> treFrom;
	private TableColumn<TemplateDefModel, String> colToName;
	private TableColumn<TemplateDefModel, String> colToDescription;
	private TableView<TemplateDefModel> tblTo;

	protected Button btnRight;
	protected Button btnLeft;
	protected Button btnUp;
	protected Button btnDown;

	@Override
	protected AnchorPane buildView() {
		GridPane gridPane = nodeFactory.createGridPaneForSwap();
		nodeFactory.addRowConstraints(gridPane, 2);
		gridPane.getRowConstraints().get(gridPane.getRowConstraints().size() - 1).setVgrow(Priority.SOMETIMES);
		GridPane.setColumnSpan(nodeFactory.createLabelInsideGrid(gridPane, "%template_projects", 0, 0), 2);
		GridPane.setColumnSpan(nodeFactory.createLabelInsideGrid(gridPane, "%selected_templates", 2, 0), 2);

		treFrom = nodeFactory.createTree();
		gridPane.add(treFrom, 0, 1);

		tblTo = nodeFactory.createTableView();
		colToName = nodeFactory.createAndAddTableColumn(tblTo, "%name", 100.0);
		colToDescription = nodeFactory.createAndAddTableColumn(tblTo, "%description", 350.0);
		gridPane.add(tblTo, 2, 1);
		colToName.setCellValueFactory(cd -> cd.getValue().nameProperty());
		colToDescription.setCellValueFactory(cd -> cd.getValue().descriptionProperty());

		VBox fromVB = new VBox();
		btnRight = nodeFactory.createButton16x16("/images/arrow_right.png");
		btnRight.setDisable(true);
		VBox.setMargin(btnRight, new Insets(2, 2, 2, 2));
		btnLeft = nodeFactory.createButton16x16("/images/arrow_left.png");
		VBox.setMargin(btnLeft, new Insets(2, 2, 2, 2));
		fromVB.getChildren().addAll(btnRight, btnLeft);
		gridPane.add(fromVB, 1, 1);
		GridPane.setMargin(fromVB, new Insets(4, 2, 2, 2));

		VBox toVB = new VBox();
		btnUp = nodeFactory.createButton16x16("/images/arrow_up.png");
		VBox.setMargin(btnUp, new Insets(2, 2, 2, 2));
		btnDown = nodeFactory.createButton16x16("/images/arrow_down.png");
		VBox.setMargin(btnDown, new Insets(2, 2, 2, 2));
		toVB.getChildren().addAll(btnUp, btnDown);
		gridPane.add(toVB, 3, 1);
		GridPane.setMargin(toVB, new Insets(4, 2, 2, 2));

		return nodeFactory.addAndFitToAnchorPane(gridPane);
	}

	@Override
	protected void bindFieldsToModel() {
		tblTo.setItems(getModel());

		bind(btnLeft.disableProperty(),tblTo.getSelectionModel().selectedItemProperty().isNull());
		bind(btnRight.disableProperty(),treFrom.getSelectionModel().selectedItemProperty().isNull());

		bind(btnUp.disableProperty(),tblTo.getSelectionModel().selectedItemProperty().isNull()
				.or(tblTo.getSelectionModel().selectedIndexProperty().isEqualTo(0)));
		bind(btnDown.disableProperty(),tblTo.getSelectionModel().selectedItemProperty().isNull().or(tblTo
				.getSelectionModel().selectedIndexProperty().isEqualTo(Bindings.size(tblTo.getItems()).subtract(1))));
	}

	public TreeView<TemplateProjectItemDefModel> getTreFrom() {
		return treFrom;
	}

	public TableView<TemplateDefModel> getTblTo() {
		return tblTo;
	}

	public Button getBtnRight() {
		return btnRight;
	}

	public Button getBtnLeft() {
		return btnLeft;
	}

	public Button getBtnUp() {
		return btnUp;
	}

	public Button getBtnDown() {
		return btnDown;
	}

	public void setTemplateProjects(ObservableList<TemplateProjectDefModel> templateProjects) {
		templateTreeBuilder.fillTreeForEditor(templateProjects, treFrom);

	}

}
