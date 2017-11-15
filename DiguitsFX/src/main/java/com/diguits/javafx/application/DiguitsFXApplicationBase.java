package com.diguits.javafx.application;

import com.diguits.javafx.application.DiguitsFXModule;
import com.diguits.javafx.application.InjectorHelper;
import com.diguits.javafx.container.controllers.MainController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public abstract class DiguitsFXApplicationBase extends Application {
	@Override
	public final void start(Stage primaryStage) {
		try {
			addModules();
			MainController mainController = InjectorHelper.getInstance().getInjector().getInstance(MainController.class);
			BorderPane rootLayout = mainController.viewProperty().get().getNodeView();
			Scene scene = new Scene(rootLayout, 800, 600);
			mainController.initialize();
			scene.getStylesheets().add(getClass().getResource(getDefaultCSS()).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setTitle(getApplicationTitle());
			primaryStage.getIcons().add(new Image(getClass().getResource(getApplicationIcon()).toExternalForm()));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract String getApplicationIcon();

	protected abstract String getApplicationTitle();

	protected abstract String getDefaultCSS();

	protected void addModules() {
		InjectorHelper.getInstance().getModules().add(new DiguitsFXModule());
	}
}
