package com.diguits.javafx.editor.controllers;

import com.diguits.javafx.model.ModelBase;
import com.diguits.javafx.container.controllers.IContainerController;
import com.diguits.javafx.container.views.IContainerView;

public interface IEditorController<TView extends IContainerView<?, TModel>, TModel extends ModelBase>
		extends IContainerController<TView, TModel> {

}