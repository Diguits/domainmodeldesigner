package com.diguits.javafx.container.decorators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diguits.javafx.container.controllers.IDecoratedContainerController;
import com.diguits.javafx.model.ModelBase;
import javafx.scene.image.ImageView;

public abstract class ContainerDecoratorBase<TModel extends ModelBase, TController extends IDecoratedContainerController<?, TModel>>
		extends ControllerDecoratorBase<TController> implements IContainerDecorator<TModel, TController> {

	protected Map<Object, List<OptionBase>> contextMenusOptions;
	protected List<OptionBase> customToolBarOptions;

	public ContainerDecoratorBase(TController controller) {
		super(controller);
		controller.setDecorator(this);
		contextMenusOptions = new HashMap<Object, List<OptionBase>>();
	}

	@Override
	public final List<OptionBase> getCustomToolBarOptions() {
		if (customToolBarOptions == null) {
			customToolBarOptions = new ArrayList<OptionBase>();
			buildCustomToolBarOptions(new OptionListBilder(customToolBarOptions));
		}
		return customToolBarOptions;
	}

	@Override
	public List<OptionBase> getContextMenuOptions(TModel model) {
		if (model != null) {
			if (!contextMenusOptions.containsKey(getKeyForContextMenuCache(model))) {
				List<OptionBase> contexMenuOptions = new ArrayList<OptionBase>();
				buildContextMenuOptions(model, new OptionListBilder(contexMenuOptions));
				contextMenusOptions.put(getKeyForContextMenuCache(model), contexMenuOptions);
			}
			return contextMenusOptions.get(getKeyForContextMenuCache(model));
		}
		return null;
	}

	protected Object getKeyForContextMenuCache(TModel model) {
		return model.getClass();
	}

	protected abstract void buildCustomToolBarOptions(OptionListBilder builder);

	protected abstract void buildContextMenuOptions(TModel model, OptionListBilder builder);

	public abstract ImageView getImageForModel(TModel item);

}
