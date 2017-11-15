package com.diguits.javafx.application;

import java.io.IOException;
import java.util.ResourceBundle;

//import com.diguits.javafx.controllers.ViewControllerBase;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class LoaderHelper {

	private static LoaderHelper instance;

	protected LoaderHelper() {

	}

	public static LoaderHelper getInstance() {
		if (instance == null)
			instance = new LoaderHelper();
		return instance;
	}

	public Node load(String viewName) throws IOException {
		return load(viewName, viewName);
	}

	public Node load(String viewName, String resourceName) throws IOException {
		return load(viewName, null, viewName);
	}

	public Node load(String viewName, Object controller) throws IOException {
		return load(viewName, controller, viewName);
	}

	public Node load(String viewName, Object controller, String resourceName) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/" + viewName + ".fxml"));
		loader.setResources(ResourceBundle.getBundle("bundles/" + resourceName));
		if (controller == null)
			loader.setControllerFactory(clazz -> InjectorHelper.getInstance().getInjector().getInstance(clazz));
		loader.setController(controller);
		Node view = loader.load();
		controller = loader.getController();
		/*
		 * if(controller!=null&&controller instanceof ViewControllerBase)
		 * ((ViewControllerBase)controller).setView(view);
		 */
		return view;
	}

}
