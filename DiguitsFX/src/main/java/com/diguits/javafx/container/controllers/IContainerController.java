package com.diguits.javafx.container.controllers;

import com.diguits.javafx.model.ModelBase;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.container.controllers.ContainerControllerBase.ContainerPos;
import com.diguits.javafx.container.views.IContainerView;
import com.diguits.javafx.controllers.IModelController;
import com.diguits.javafx.controllers.IViewController;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public interface IContainerController<TView extends IContainerView<?, TModel>, TModel extends ModelBase>
		extends IModelController<TModel>, IViewController<TView> {

	String getTitle();

	StringProperty titleProperty();

	ImageView getImage();

	TModel getSelectedModel();

	void setSelectedModel(TModel model);

	ObjectProperty<TModel> selectedModelProperty();

	TreeItem<?> getSelectedTreeItem();

	void setSelectedTreeItem(TreeItem<?> model);

	ObjectProperty<TreeItem<?>> selectedTreeItemProperty();

	boolean allowTreeItemSelecion();

	Class<? extends ModelBase> getSelectedModelClass();

	ReadOnlyObjectProperty<Class<? extends ModelBase>> selectedModelClassProperty();

	boolean getFocused();

	ReadOnlyBooleanProperty focusedProperty();

	ContainerPos getPosition();

	void setPosition(ContainerPos position);

	void showEditor(NamedModelBase model);

	void showEditor(NamedModelBase model, TreeItem<TModel> treeItem);

	void hideEditor(TModel model);

	boolean goToPosition(Position position);

	String getModelTypeName();

}