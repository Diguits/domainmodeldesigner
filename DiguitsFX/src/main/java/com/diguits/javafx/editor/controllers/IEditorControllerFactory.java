package com.diguits.javafx.editor.controllers;

import com.diguits.javafx.model.NamedModelBase;

public interface IEditorControllerFactory {
	public <TModel extends NamedModelBase> IEditorController<?, TModel> create(TModel model);

	public <TModel extends NamedModelBase> IEditorController<?, TModel> create(Class<TModel> modelClass);
}
