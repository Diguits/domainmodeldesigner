package com.diguits.javafx.container.controllers;

import com.diguits.javafx.container.decorators.IContainerDecorator;
import com.diguits.javafx.container.decorators.IOptionToNodeFactory;
import com.diguits.javafx.container.views.IContainerView;
import com.diguits.javafx.model.ModelBase;
import com.google.inject.Inject;

import javafx.scene.image.ImageView;

public abstract class DecoratedContainerController<TView extends IContainerView<?, TModel>, TModel extends ModelBase>
		extends ContainerControllerBase<TView, TModel> implements IDecoratedContainerController<TView, TModel> {

	protected IContainerDecorator<TModel, ?> decorator;
	@Inject
	protected IOptionToNodeFactory optionToNodeFactory;

	public DecoratedContainerController(TView view) {
		super(view);
	}

	@Override
	public void setDecorator(IContainerDecorator<TModel, ?> decorator) {
		this.decorator = decorator;
	}

	public IContainerDecorator<TModel, ?> getDecorator() {
		return decorator;
	}

	@Override
	public ImageView getImage() {
		if (this.decorator != null) {
			try {
				return new ImageView("/images/" + decorator.getImageName() + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public void initialize() {
		super.initialize();
		if (this.decorator != null) {
			String titleResource = decorator.getTitleResource();
			if (titleResource != null && !titleResource.trim().isEmpty())
				setTitle(resources.getString(titleResource));
			getView().setToolBarOptions(
					optionToNodeFactory.createToolBarOptions(this.decorator.getCustomToolBarOptions()));
		}
	}
}
