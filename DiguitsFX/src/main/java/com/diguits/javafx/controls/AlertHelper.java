package com.diguits.javafx.controls;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AlertHelper {

	private ResourceBundle resources;

	public AlertHelper() {
		super();
		resources = ResourceBundle.getBundle("bundles/Alerts");
	}

	public boolean confirmDelete(String deleteClassName, String deleteModelName) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(resources.getString("delete_confirmation"));
		String str = resources.getString("delete_quetion");
		alert.setContentText(String.format(str, deleteClassName, deleteModelName));
		alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		Optional<ButtonType> showResult = alert.showAndWait();
		return showResult.isPresent() && showResult.get() == ButtonType.OK;
	}
}
