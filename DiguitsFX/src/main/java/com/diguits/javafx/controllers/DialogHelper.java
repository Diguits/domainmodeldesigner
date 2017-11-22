package com.diguits.javafx.controllers;

import java.util.Optional;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class DialogHelper {
	public static boolean showOKCancelDialog(IViewController<?> controller, StringExpression titleProperty,
			ObservableBooleanValue okDisable) {
		return showOKCancelDialog(controller, titleProperty, okDisable, 1024, 600);
	}

	public static boolean showOKCancelDialog(IViewController<?> controller, StringExpression titleProperty) {
		return showOKCancelDialog(controller, titleProperty, null);
	}

	public static boolean showOKCancelDialog(IViewController<?> controller, String title, ObservableBooleanValue okDisable) {
		return showOKCancelDialog(controller, title, okDisable, 1024, 600);
	}

	public static boolean showOKCancelDialog(IViewController<?> controller, String title) {
		return showOKCancelDialog(controller, title, null);
	}

	public static boolean showOKCancelDialog(IViewController<?> controller, StringExpression titleProperty,
			ObservableBooleanValue okDisable, int width, int height) {
		return showOKCancelDialog(controller, titleProperty, null, okDisable, width, height, StageStyle.DECORATED);
	}

	public static boolean showOKCancelDialog(IViewController<?> controller, StringExpression titleProperty,
			BooleanBinding okDisable, int width, int height, StageStyle style) {
		return showOKCancelDialog(controller, titleProperty, null, okDisable, width, height, style);
	}

	public static boolean showOKCancelDialog(IViewController<?> controller, String title, ObservableBooleanValue okDisable,
			int width, int height) {
		return showOKCancelDialog(controller, null, title, okDisable, width, height, StageStyle.DECORATED);
	}

	public static boolean showOKCancelDialog(IViewController<?> controller, String title, ObservableBooleanValue okDisable,
			int width, int height, StageStyle style) {
		return showOKCancelDialog(controller, null, title, okDisable, width, height, style);
	}

	public static boolean showNoButtonsDialog(IViewController<?> controller, StringExpression titleProperty) {
		return showNoButtonsDialog(controller, titleProperty, 1024, 600);
	}

	public static boolean showNoButtonsDialog(IViewController<?> controller, String title) {
		return showNoButtonsDialog(controller, title, 1024, 600);
	}

	public static boolean showNoButtonsDialog(IViewController<?> controller, StringExpression titleProperty, int width,
			int height) {
		return showNoButtonsDialog(controller, titleProperty, null, width, height, StageStyle.DECORATED);
	}

	public static boolean showNoButtonsDialog(IViewController<?> controller, StringExpression titleProperty, int width,
			int height, StageStyle style) {
		return showNoButtonsDialog(controller, titleProperty, null, width, height, style);
	}

	public static boolean showNoButtonsDialog(IViewController<?> controller, String title, int width, int height) {
		return showNoButtonsDialog(controller, null, title, width, height, StageStyle.DECORATED);
	}

	public static boolean showNoButtonsDialog(IViewController<?> controller, String title, int width, int height,
			StageStyle style) {
		return showNoButtonsDialog(controller, null, title, width, height, style);
	}

	public static boolean showNoButtonsDialog(Dialog<ButtonType> dialog, IViewController<?> controller,
			StringExpression titleProperty) {
		return showNoButtonsDialog(dialog, controller, titleProperty, 1024, 600);
	}

	public static boolean showNoButtonsDialog(Dialog<ButtonType> dialog, IViewController<?> controller, String title) {
		return showNoButtonsDialog(dialog, controller, title, 1024, 600);
	}

	public static boolean showNoButtonsDialog(Dialog<ButtonType> dialog, IViewController<?> controller,
			StringExpression titleProperty, int width, int height) {
		return showNoButtonsDialog(dialog, controller, titleProperty, null, width, height, StageStyle.DECORATED);
	}

	public static boolean showNoButtonsDialog(Dialog<ButtonType> dialog, IViewController<?> controller,
			StringExpression titleProperty, int width, int height, StageStyle style) {
		return showNoButtonsDialog(dialog, controller, titleProperty, null, width, height, style);
	}

	public static boolean showNoButtonsDialog(Dialog<ButtonType> dialog, IViewController<?> controller, String title, int width,
			int height) {
		return showNoButtonsDialog(dialog, controller, null, title, width, height, StageStyle.DECORATED);
	}

	public static boolean showNoButtonsDialog(Dialog<ButtonType> dialog, IViewController<?> controller, String title, int width,
			int height, StageStyle style) {
		return showNoButtonsDialog(dialog, controller, null, title, width, height, style);
	}

	protected static boolean showNoButtonsDialog(IViewController<?> controller, StringExpression titleProperty,
			String title, int width, int height, StageStyle style) {
		Dialog<ButtonType> dialog = createDialog(controller, titleProperty, title, width, height, style);
		dialog.getDialogPane().getButtonTypes().clear();
		Optional<?> modelEdited = dialog.showAndWait();
		return modelEdited.isPresent() && modelEdited.get() != ButtonType.CANCEL;
	}

	protected static boolean showNoButtonsDialog(Dialog<ButtonType> dialog, IViewController<?> controller,
			StringExpression titleProperty, String title, int width, int height, StageStyle style) {
		dialog.getDialogPane().getButtonTypes().clear();
		Optional<?> modelEdited = dialog.showAndWait();
		return modelEdited.isPresent() && modelEdited.get() != ButtonType.CANCEL;
	}

	protected static boolean showOKCancelDialog(IViewController<?> controller, StringExpression titleProperty,
			String title, ObservableBooleanValue okDisable, int width, int height, StageStyle style) {
		Dialog<ButtonType> dialog = createDialog(controller, titleProperty, title, width, height, style);

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		if (okDisable != null) {
			Node okButtom = dialog.getDialogPane().lookupButton(ButtonType.OK);
			okButtom.setDisable(true);
			okButtom.disableProperty().bind(okDisable);
		}
		Optional<?> modelEdited = dialog.showAndWait();
		return modelEdited.isPresent() && modelEdited.get() != ButtonType.CANCEL;
	}

	public static Dialog<ButtonType> createDialog(IViewController<?> controller, StringExpression titleProperty, int width,
			int height) {
		return createDialog(controller, titleProperty, null, width, height, StageStyle.DECORATED);
	}

	public static Dialog<ButtonType> createDialog(IViewController<?> controller, StringExpression titleProperty, int width,
			int height, StageStyle style) {
		return createDialog(controller, titleProperty, null, width, height, style);
	}

	public static Dialog<ButtonType> createDialog(IViewController<?> controller, String title, int width, int height) {
		return createDialog(controller, null, title, width, height, StageStyle.DECORATED);
	}

	public static Dialog<ButtonType> createDialog(IViewController<?> controller, String title, int width, int height,
			StageStyle style) {
		return createDialog(controller, null, title, width, height, style);
	}

	protected static Dialog<ButtonType> createDialog(IViewController<?> controller, StringExpression titleProperty, String title,
			int width, int height, StageStyle style) {
		Dialog<ButtonType> dialogToCreate = null;
		if (style == StageStyle.UNDECORATED) {
			dialogToCreate = new CustomDialog<ButtonType>();
		} else {
			dialogToCreate = new Dialog<ButtonType>();
		}
		final Dialog<ButtonType> dialog = dialogToCreate;

		if (style == StageStyle.UNDECORATED) {
			dialog.initModality(Modality.NONE);
			dialog.setOnShown(e -> {
				dialog.getDialogPane().getScene().getWindow().focusedProperty().addListener((v, o, n) -> {
					if (!n) {
						if (dialog.getResult() == null)
							dialog.setResult(ButtonType.CANCEL);
						dialog.close();
					}
				});
			});
			dialog.getDialogPane().getScene().addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e) {
					if (e.getCode() == KeyCode.ENTER) {
						dialog.setResult(ButtonType.OK);
						dialog.close();
					}
					if (e.getCode() == KeyCode.ESCAPE) {
						dialog.setResult(ButtonType.CANCEL);
						dialog.close();
					}
				}
			});
			final String cssDefault = "-fx-border-color: darkGrey;\n" + "-fx-border-insets: 0;\n"
					+ "-fx-border-width: 1;\n" + "-fx-border-radius: 5 5 5 5;\n" + "-fx-background-radius: 5 5 5 5;\n"
					+ "-fx-padding:5;";
			dialog.getDialogPane().setStyle(cssDefault);
		}

		dialog.initStyle(style);

		dialog.setResizable(true);
		dialog.getDialogPane().getStylesheets().add("/css/default.css");
		if (titleProperty != null)
			dialog.titleProperty().bind(titleProperty);
		else
			dialog.setTitle(title);
		((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons()
				.add(new Image(controller.getClass().getResource("/images/relationships.png").toExternalForm()));

		BorderPane borderPane = new BorderPane();
		if (width > 0) {
			//dialog.getDialogPane().setMaxWidth(width);
			dialog.getDialogPane().setPrefWidth(width);
		}
		if (height > 0) {
			//dialog.getDialogPane().setMinHeight(height);
			dialog.getDialogPane().setPrefHeight(height);
		}

		borderPane.setBottom(new Separator(Orientation.HORIZONTAL));
		borderPane.setPadding(new Insets(0, 0, 0, 0));

		//AnchorPane anchorPane = new AnchorPane();
		//ScrollPane scrollPane = new ScrollPane(anchorPane);
		//scrollPane.setFitToWidth(true);
		Node view = controller.getView().getNodeView();
		borderPane.setCenter(view);
		//anchorPane.getChildren().add(view);
		//AnchorPane.setRightAnchor(view, 0.0);
		//AnchorPane.setTopAnchor(view, 0.0);
		//AnchorPane.setLeftAnchor(view, 0.0);
		//AnchorPane.setBottomAnchor(view, 0.0);
		//Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		//stage.setMaximized(true);
		dialog.getDialogPane().setContent(borderPane);
		controller.setDialogContainer(dialog);
		dialog.setOnHidden(e->controller.terminate());
		return dialog;
	}

	static class CustomDialogPane extends DialogPane {
		@Override
		protected Node createButtonBar() {
			return null;
		}
	}

	static class CustomDialog<T> extends Dialog<T> {
		public CustomDialog() {
			super();
			setDialogPane(new CustomDialogPane());
		}
	}
}
