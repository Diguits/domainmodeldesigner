package com.diguits.javafx.editor.controllers;

import java.util.HashMap;
import java.util.Map;

public abstract class EditorControllerProviderBase implements IEditorControllerProvider {

	private Map<Class<?>, Class<? extends IEditorController<?, ?>>> controllers;

	public EditorControllerProviderBase() {
		super();
		controllers = new HashMap<Class<?>, Class<? extends IEditorController<?, ?>>>();
		registerControllers();
	}

	protected void registerControler(Class<?> modelClass, Class<? extends IEditorController<?, ?>> controllerClass) {
		controllers.put(modelClass, controllerClass);
	}

	protected abstract void registerControllers();

	@Override
	public Class<? extends IEditorController<?, ?>> getEditorControllerData(Class<?> modelClass) {
		if (controllers.containsKey(modelClass))
			return controllers.get(modelClass);
		return null;
	}

}
