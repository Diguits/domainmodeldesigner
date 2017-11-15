package com.diguits.javafx.container.controllers;

import com.diguits.javafx.container.views.ITreeContainerView;
import com.diguits.javafx.model.NamedModelBase;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;

public interface ITreeContainerController<TView extends ITreeContainerView<?, TModel>, TModel extends NamedModelBase>
		extends IDecoratedContainerController<TView, TModel> {
	public TreeItem<TModel> findTreeItemByModel(TModel model);
	void openEditorForSelectedModel();
	void renameSelectedModel();
	void collapse();
	void expand();
	ObservableValue<? extends Boolean> getCanCollapse();
	ObservableValue<? extends Boolean> getCanExpand();
	boolean isTreeItemContainer(TModel item);
}
