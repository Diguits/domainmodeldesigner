package com.diguits.javafx.editor.controllers;

public interface IEditorControllerProvider {
	Class<? extends IEditorController<?, ?>> getEditorControllerData(Class<?> modelClass);
}
