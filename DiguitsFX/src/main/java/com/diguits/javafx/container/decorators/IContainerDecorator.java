package com.diguits.javafx.container.decorators;

import java.util.List;

import com.diguits.javafx.container.controllers.IDecoratedContainerController;
import com.diguits.javafx.model.ModelBase;

import javafx.scene.image.ImageView;

public interface IContainerDecorator<TModel extends ModelBase, TController extends IDecoratedContainerController<?, TModel>>
		extends IControllerDecorator<TController> {

	String getImageName();

	String getTitleResource();

	List<OptionBase> getCustomToolBarOptions();

	List<OptionBase> getContextMenuOptions(TModel model);

	ImageView getImageForModel(TModel item);

}
