package com.diguits.javafx.views.helpers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class AnchorPaneHelper {

	public static AnchorPane createAnchorPane() {
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getStyleClass().add("anchor-pane");
		anchorPane.setMinHeight(0.0);
		anchorPane.setMinWidth(0.0);
		return anchorPane;
	}

	public static void fitToAnchorPane(Node node) {
		AnchorPane.setRightAnchor(node, 0.0);
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);

	}

	public static AnchorPane addAndFitToAnchorPane(Node node) {
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(node);
		fitToAnchorPane(node);
		return anchorPane;
	}
}
