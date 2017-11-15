package com.diguits.javafx.container.views;

import java.util.List;

import org.controlsfx.control.BreadCrumbBar;

import com.diguits.javafx.container.controllers.ContainerControllerBase.ContainerPos;
import com.diguits.javafx.model.ModelBase;
import com.diguits.javafx.views.ViewBase;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends ViewBase<BorderPane> {

	protected MenuBar mainMenuBar;
	protected ToolBar mainToolBar;

	protected AnchorPane leftHole;
	protected AnchorPane centerHole;
	protected AnchorPane rightHole;
	protected AnchorPane bottomHole;
	protected BreadCrumbBar<? extends ModelBase> breadCrumbBar;

	@Override
	protected BorderPane buildView() {
		BorderPane mainBorderPane = new BorderPane();
		mainBorderPane.setPrefHeight(600.0);
		mainBorderPane.setPrefWidth(800.0);
		mainMenuBar = new MenuBar();
		mainBorderPane.setTop(mainMenuBar);

		// StatusBar
		Separator statusBarSeparator = new Separator();
		HBox statusBar = new HBox();
		statusBar.setPrefHeight(24.0);
		VBox statusBarContainer = new VBox();
		statusBarContainer.setPrefHeight(24.0);
		statusBarContainer.getChildren().addAll(statusBarSeparator, statusBar);
		mainBorderPane.setBottom(statusBarContainer);

		BorderPane mainContainer = new BorderPane();
		mainContainer.setPrefHeight(200.0);
		mainToolBar = new ToolBar();
		mainContainer.setTop(mainToolBar);

		// Spliters
		SplitPane leftSpliter = new SplitPane();
		leftSpliter.setPrefHeight(160.0);
		leftSpliter.setPrefWidth(200.0);
		leftSpliter.setDividerPositions(0.3747);

		SplitPane rightSpliter = new SplitPane();
		rightSpliter.setPrefHeight(200.0);
		rightSpliter.setOrientation(Orientation.VERTICAL);
		rightSpliter.setPrefWidth(160.0);
		rightSpliter.setDividerPositions(0.5);

		SplitPane centerSpliter = new SplitPane();
		centerSpliter.setPrefHeight(200.0);
		centerSpliter.setPrefWidth(160.0);
		centerSpliter.setDividerPositions(0.340080971659919);

		// Holes
		leftHole = new AnchorPane();
		leftHole.setMinHeight(0.0);
		leftHole.setPrefHeight(160.0);
		leftHole.setPrefWidth(100.0);
		leftHole.setMinWidth(0.0);

		centerHole = new AnchorPane();
		centerHole.setMinHeight(0.0);
		centerHole.setMinWidth(0.0);

		BorderPane centerBorderPane = new BorderPane();
		centerBorderPane.setCenter(centerHole);
		AnchorPane anchor = new AnchorPane();
		anchor.setPrefHeight(24);
		anchor.setMaxHeight(24);
		breadCrumbBar = new BreadCrumbBar<>();
		anchor.getChildren().add(breadCrumbBar);
		nodeFactory.fitToAnchorPane(breadCrumbBar);
		centerBorderPane.setTop(anchor);
		anchor.setStyle("-fx-border-width: 0 0 0.5 0; -fx-border-color: darkgray;");
		breadCrumbBar.setAutoNavigationEnabled(false);

		rightHole = new AnchorPane();
		rightHole.setMinHeight(0.0);
		rightHole.setPrefHeight(160.0);
		rightHole.setPrefWidth(100.0);
		rightHole.setMinWidth(0.0);

		bottomHole = new AnchorPane();
		bottomHole.setPrefHeight(180.0);

		centerSpliter.getItems().addAll(centerBorderPane, rightHole);
		rightSpliter.getItems().addAll(centerSpliter, bottomHole);
		leftSpliter.getItems().addAll(leftHole, rightSpliter);

		mainContainer.setCenter(leftSpliter);
		mainBorderPane.setCenter(mainContainer);

		SplitPane.setResizableWithParent(leftHole, false);
		SplitPane.setResizableWithParent(rightHole, false);
		SplitPane.setResizableWithParent(bottomHole, false);

		return mainBorderPane;
	}

	public void putHole(HoleViewBase<?> holeview, ContainerPos position) {
		if (holeview != null) {

			AnchorPane hole = null;
			if (position == ContainerPos.CENTER)
				hole = centerHole;
			if (position == ContainerPos.LEFT)
				hole = leftHole;
			if (position == ContainerPos.RIGTH)
				hole = rightHole;
			if (position == ContainerPos.BOTTOM)
				hole = bottomHole;

			Node view = holeview.getNodeView();
			hole.getChildren().add(view);
			nodeFactory.fitToAnchorPane(view);
		}
	}

	public void addMainMenuItems(List<Menu> mainMenuItems) {
		mainMenuBar.getMenus().addAll(mainMenuItems);
	}

	public void addToolBarOptions(List<Node> toolBarOptions) {
		if (toolBarOptions != null && toolBarOptions.size() > 0)
			mainToolBar.getItems().addAll(toolBarOptions);
	}

	public BreadCrumbBar<?> getBreadCrumbBar() {
		return breadCrumbBar;
	}
}
