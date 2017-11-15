package com.diguits.javafx.controllers;

import javafx.beans.property.ObjectProperty;

public interface IModelController<TModel> {
	public TModel getModel();

	public void setModel(TModel model);

	public ObjectProperty<TModel> modelProperty();
}
