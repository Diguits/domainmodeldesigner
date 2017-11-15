package com.diguits.javafx.problem.views;

import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import com.diguits.javafx.problem.controllers.ProblemItem;
import com.diguits.javafx.problem.controllers.ProblemItem.Kind;
import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.container.views.ContainerView;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public final class ProblemsView extends ContainerView<ProblemItem> {

	private TableView<ProblemItem> problemItems;
	private TableColumn<ProblemItem, ProblemItem.Kind> colKind;
	private TableColumn<ProblemItem, String> colMessage;
	private TableColumn<ProblemItem, Position> colPosition;

	public ProblemsView() {

	}

	@Override
	protected Node buildContentView() {
		AnchorPane anchorPane = new AnchorPane();
		problemItems = nodeFactory.createTableView();
		colKind = nodeFactory.createAndAddTableColumn(problemItems, "%level", 50.0);
		colMessage = nodeFactory.createAndAddTableColumn(problemItems, "%message", 800.0);
		colPosition = nodeFactory.createAndAddTableColumn(problemItems, "%position", 200.0);
		anchorPane.getChildren().add(problemItems);

		colKind.setCellValueFactory(cd -> cd.getValue().kindProperty());
		colKind.setCellFactory(new Callback<TableColumn<ProblemItem, Kind>, TableCell<ProblemItem, Kind>>() {

			@Override
			public TableCell<ProblemItem, Kind> call(TableColumn<ProblemItem, Kind> param) {
				TableCell<ProblemItem, Kind> tableCell = new TableCell<ProblemItem, Kind>() {
					protected void updateItem(Kind item, boolean empty) {
						if (item != null) {
							String imageName = null;
							switch (item) {
							case ERROR:
								imageName = "cancel";
								break;
							case INFO:
								imageName = "information";
								break;
							case WARN:
								imageName = "error";
								break;
							default:
								break;
							}
							if (imageName != null)
								this.setGraphic(new ImageView("images/" + imageName + ".png"));
						} else {
							this.setGraphic(null);
						}

					};
				};
				tableCell.setAlignment(Pos.CENTER);
				return tableCell;
			}
		});
		colPosition.setCellValueFactory(cd -> cd.getValue().positionProperty());
		colPosition.setCellFactory(new Callback<TableColumn<ProblemItem,Position>, TableCell<ProblemItem,Position>>() {

			@Override
			public TableCell<ProblemItem, Position> call(TableColumn<ProblemItem, Position> param) {
				return new TableCell<ProblemItem, Position>(){
					protected void updateItem(Position position, boolean empty) {
						if (position != null) {
							String text="";
								if (position.getLineNumber() >= 0)
									text += resources.getString("line")+" : " + position.getLineNumber() + " ";
							if (position.getLineNumber() >= 0)
								text += resources.getString("column")+" : " + position.getColumnNumber() + " ";

							setText(text);
						} else
							setText(null);
					};
				};
			}
		});

		colMessage.setCellValueFactory(cd -> cd.getValue().messageProperty());
		fillToParentAnchor(problemItems);
		return anchorPane;
	}

	@Override
	protected void bindFieldsToModel() {

	}

	public TableView<ProblemItem> getProblemItems() {
		return problemItems;
	}

}
