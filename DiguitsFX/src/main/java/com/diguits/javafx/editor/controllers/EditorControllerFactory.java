package com.diguits.javafx.editor.controllers;

import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.application.InjectorHelper;
import com.diguits.javafx.editor.controllers.IEditorController;
import com.google.inject.Inject;

public class EditorControllerFactory implements IEditorControllerFactory {

	@Inject
	IEditorControllerProvider editorControllerProvider;

	@SuppressWarnings("unchecked")
	@Override
	public <TModel extends NamedModelBase> IEditorController<?, TModel> create(TModel model) {
		if (model != null) {
			return (IEditorController<?, TModel>) create((Class<TModel>) model.getClass(), model);
		}
		return null;
	}

	@Override
	public <TModel extends NamedModelBase> IEditorController<?, TModel> create(Class<TModel> modelClass) {
		if (modelClass != null) {
			return (IEditorController<?, TModel>) create(modelClass, null);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <TModel extends NamedModelBase> IEditorController<?, TModel> create(Class<TModel> modelClass, TModel model) {
		if (modelClass != null) {

			Class<? extends IEditorController<?, ?>> controllerClass = editorControllerProvider
					.getEditorControllerData(modelClass);
			if (controllerClass != null)
				return (IEditorController<?, TModel>) innerCreate(
						(Class<? extends IEditorController<?, TModel>>) controllerClass, model);
		}
		return null;
	}

	private <TModel extends NamedModelBase> IEditorController<?, ? extends TModel> innerCreate(
			Class<? extends IEditorController<?, TModel>> editorClass, TModel model) {
		try {
			IEditorController<?, TModel> controller = InjectorHelper.getInstance().getInjector()
					.getInstance(editorClass);
			controller.setModel((TModel) model);
			return controller;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
