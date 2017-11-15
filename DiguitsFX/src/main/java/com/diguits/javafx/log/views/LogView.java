package com.diguits.javafx.log.views;

import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.util.Date;

import com.diguits.javafx.container.views.ContainerView;
import com.diguits.javafx.log.controllers.LogItem;
import com.diguits.javafx.log.controllers.LogItem.LogLevel;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public final class LogView extends ContainerView<LogItem> {

	private TableView<LogItem> tblLogs;
	private TableColumn<LogItem, LogItem.LogLevel> colLevel;
	private TableColumn<LogItem, String> colMessage;
	private TableColumn<LogItem, Date> colDate;

	public LogView() {

	}

	@Override
	protected Node buildContentView() {
		AnchorPane anchorPane = new AnchorPane();
		tblLogs = nodeFactory.createTableView();
		colLevel = nodeFactory.createAndAddTableColumn(tblLogs, "%level", 50.0);
		colDate = nodeFactory.createAndAddTableColumn(tblLogs, "%date", 200.0);
		colMessage = nodeFactory.createAndAddTableColumn(tblLogs, "%message", 800.0);
		anchorPane.getChildren().add(tblLogs);

		colLevel.setCellValueFactory(cd -> cd.getValue().levelProperty());
		colLevel.setCellFactory(new Callback<TableColumn<LogItem, LogLevel>, TableCell<LogItem, LogLevel>>() {

			@Override
			public TableCell<LogItem, LogLevel> call(TableColumn<LogItem, LogLevel> param) {
				TableCell<LogItem, LogLevel> tableCell = new TableCell<LogItem, LogLevel>() {
					protected void updateItem(LogLevel item, boolean empty) {
						if (item != null) {
							String imageName = null;
							switch (item) {
							case DEBUG:
								imageName = "bug";
								break;
							case ERROR:
								imageName = "cancel";
								break;
							case FATAL:
								imageName = "cancel";
							case INFO:
								imageName = "information";
								break;
							case TRACE:
								imageName = "zoom_selection";
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
		colDate.setCellValueFactory(cd -> cd.getValue().dateProperty());
		colMessage.setCellValueFactory(cd -> cd.getValue().messageProperty());
		fillToParentAnchor(tblLogs);
		return anchorPane;
	}

	@Override
	protected void bindFieldsToModel() {

	}

	public TableView<LogItem> getLogsTable() {
		return tblLogs;
	}

	public void scrollToLast() {
		if (tblLogs.getItems().size() > 0)
			tblLogs.scrollTo(tblLogs.getItems().size() - 1);
	}
}
