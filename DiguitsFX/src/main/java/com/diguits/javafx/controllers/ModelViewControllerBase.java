package com.diguits.javafx.controllers;

import com.diguits.javafx.undo.UndoManager;
import com.diguits.javafx.undo.changes.PropertyChange;
import com.diguits.javafx.views.IModelView;
import com.diguits.javafx.views.PropertyChangeEventListener;
import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class ModelViewControllerBase<TView extends IModelView<?, TModel>, TModel>
		extends ViewControllerBase<TView> implements IModelController<TModel>, PropertyChangeEventListener {

	@Inject
	protected UndoManager undoManager;

	protected ObjectProperty<TModel> model;

	public ModelViewControllerBase(TView view) {
		super(view);
		view.setPropertyChangeEventListener(this);
		modelProperty().addListener((v, o, n) -> {
			if (!getInitialized())
				initialize();
			modelUpdated(o, n);
			if (getView() != null) {
				setModelToView(n);
			}
		});
	}

	protected void setModelToView(TModel n) {
		getView().setModel(n);
	}

	protected void modelUpdated(TModel o, TModel n) {

	}

	@Override
	public TModel getModel() {
		if (model != null)
			return model.get();
		return null;
	}

	@Override
	public void setModel(TModel model) {
		if (this.model != null || model != null) {
			modelProperty().set(model);
		}
	}

	@Override
	public ObjectProperty<TModel> modelProperty() {
		if (model == null) {
			model = new SimpleObjectProperty<TModel>(this, "model", null);
		}
		return model;
	}

	public void onPropertyChange(PropertyChange<?> propertyChange){
		undoManager.addChange(propertyChange);
	}
}
