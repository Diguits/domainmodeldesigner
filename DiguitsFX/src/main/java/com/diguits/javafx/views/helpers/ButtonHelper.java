package com.diguits.javafx.views.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonHelper {

	public static Button createButton16x16(String imageUrl) {
		Button btnEditOutputPath = new Button();
		btnEditOutputPath.setMnemonicParsing(false);
		ImageView imageView = createImageView16x16(imageUrl);
		btnEditOutputPath.setGraphic(imageView);
		btnEditOutputPath.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
		return btnEditOutputPath;
	}

	public static ImageView createImageView16x16(String imageUrl) {
		ImageView imageView = new ImageView();
		imageView.setPickOnBounds(true);
		imageView.setFitWidth(16.0);
		imageView.setFitHeight(16.0);
		imageView.setPreserveRatio(true);
		Image image = new Image(imageUrl);
		imageView.setImage(image);
		return imageView;
	}
}
