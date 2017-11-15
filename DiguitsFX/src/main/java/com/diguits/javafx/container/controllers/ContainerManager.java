package com.diguits.javafx.container.controllers;

import java.util.ArrayList;
import java.util.List;

import com.diguits.common.log.InjectLogger;
import com.diguits.javafx.application.InjectorHelper;
import com.diguits.javafx.container.controllers.ContainerControllerBase.ContainerPos;
import com.diguits.javafx.container.decorators.IContainerDecorator;
import com.diguits.javafx.container.decorators.IDecorator;
import com.diguits.javafx.container.decorators.IDecoratorProvider;
import com.diguits.javafx.container.decorators.IOptionToNodeFactory;
import com.diguits.javafx.container.decorators.OptionBase;
import com.diguits.javafx.container.decorators.OptionGroup;
import com.diguits.javafx.container.views.MainView;
import com.google.inject.Inject;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.slf4j.Logger;

public class ContainerManager {

	@InjectLogger
	Logger logger;

	@Inject
	IDecoratorProvider decoratorProvider;

	private ListProperty<IContainerController<?, ?>> activeContainers;

	private HoleControllerBase<?> leftHoleController;

	private HoleControllerBase<?> centerHoleController;

	private HoleControllerBase<?> rightHoleController;

	private HoleControllerBase<?> bottomHoleController;

	@Inject
	private IOptionToNodeFactory optionToNodeFactory;

	public void loadContainers(MainView mainView, HoleControllerBase<?> leftHoleController,
			HoleControllerBase<?> centerHoleController, HoleControllerBase<?> rightHoleController,
			HoleControllerBase<?> bottomHoleController) {
		this.leftHoleController = leftHoleController;
		this.centerHoleController = centerHoleController;
		this.rightHoleController = rightHoleController;
		this.bottomHoleController = bottomHoleController;

		List<IDecorator> decorators = getDecorators();
		List<OptionBase> mainMenuOptions = new ArrayList<OptionBase>();
		List<OptionBase> mainToolbarOptions = new ArrayList<OptionBase>();

		for (IDecorator decorator : decorators) {
			if (decorator instanceof IContainerDecorator<?, ?>) {
				IContainerController<?, ?> container = ((IContainerDecorator<?, ?>) decorator).getController();
				getActiveContainers().add(container);
				showContainer(container);
			}

			loadContainerDecoration(decorator, mainMenuOptions, mainToolbarOptions);
		}
		applyContainerDecoration(mainView, mainMenuOptions, mainToolbarOptions);
	}

	private void showContainer(IContainerController<?, ?> container) {
		if (container.getPosition() == ContainerPos.CENTER)
			centerHoleController.addContainer(container);
		if (container.getPosition() == ContainerPos.LEFT)
			leftHoleController.addContainer(container);
		if (container.getPosition() == ContainerPos.RIGTH)
			rightHoleController.addContainer(container);
		if (container.getPosition() == ContainerPos.BOTTOM)
			bottomHoleController.addContainer(container);
	}

	private void applyContainerDecoration(MainView mainView, List<OptionBase> mainMenuOptions,
			List<OptionBase> mainToolbarOptions) {
		int i = mainMenuOptions.size()-1;
		while (i >= 0) {
			OptionBase option = mainMenuOptions.get(i);
			if (option.getGroupPath() != null && !option.getGroupPath().isEmpty()) {
				OptionGroup optionGroup = findPath(mainMenuOptions, option.getGroupPath());
				if (optionGroup != null) {
					optionGroup.add(option);
				} else {
					logger.error("The option »{}« was not loaded because path »{}« was not found",
							option.toString(), option.getGroupPath());
				}
				mainMenuOptions.remove(i);
			}
			i--;
		}

		mainMenuOptions.sort((o1, o2) -> {
			return Integer.compare(o1.getPriority(), o2.getPriority());
		});
		mainToolbarOptions.sort((o1, o2) -> {
			return Integer.compare(o1.getPriority(), o2.getPriority());
		});
		;
		mainView.addToolBarOptions(optionToNodeFactory.createToolBarOptions(mainToolbarOptions));
		mainView.addMainMenuItems(optionToNodeFactory.createMainMenuItems(mainMenuOptions));
	}

	private OptionGroup findPath(List<OptionBase> options, String pathStr) {
		String[] path = pathStr.split("\\.");
		int i = 0;
		while (i < path.length) {
			int j = 0;
			while (j < options.size()) {
				OptionBase option = options.get(j);
				if (option instanceof OptionGroup && ((OptionGroup) option).getName().equals(path[i])) {
					if (i == path.length - 1) {
						return ((OptionGroup) option);
					} else {
						options = ((OptionGroup) option).getOptions();
						i++;
						break;
					}
				}
				j++;
			}
			if (j >= options.size())
				return null;
		}
		return null;
	}

	private void loadContainerDecoration(IDecorator decorator, List<OptionBase> mainMenuOptions,
			List<OptionBase> mainToolbarOptions) {
		mainMenuOptions.addAll(decorator.getMainMenuOptions());
		mainToolbarOptions.addAll(decorator.getMainToolBarOptions());

	}

	public List<IDecorator> getDecorators() {
		List<Class<? extends IDecorator>> decoratorClasses = decoratorProvider.getDecorators();
		List<IDecorator> decorators = new ArrayList<IDecorator>();
		for (Class<? extends IDecorator> decoratorClass : decoratorClasses) {
			IDecorator decorator = InjectorHelper.getInstance().getInjector().getInstance(decoratorClass);
			decorator.Initialize();
			decorators.add(decorator);
		}
		return decorators;
	}

	public ObservableList<IContainerController<?, ?>> getActiveContainers() {
		return activeContainersProperty().get();
	}

	public void setActiveContainers(ObservableList<IContainerController<?, ?>> activeContainers) {
		if (this.activeContainers != null || activeContainers != null) {
			activeContainersProperty().set(activeContainers);
		}
	}

	public ListProperty<IContainerController<?, ?>> activeContainersProperty() {
		if (activeContainers == null) {
			activeContainers = new SimpleListProperty<IContainerController<?, ?>>(this, "activeContainers", null);
			activeContainers.set(FXCollections.observableArrayList());
		}
		return activeContainers;
	}

	public void setFocusTo(IContainerController<?, ?> container) {
		boolean visible = true;
		if (container instanceof EditorControllerBase) {
			if (!centerHoleController.selectContainer(container)
					&& (!leftHoleController.selectContainer(container))
					&& (!rightHoleController.selectContainer(container))
					&& (!rightHoleController.selectContainer(container)))
				visible = false;
		} else {
			if ((!leftHoleController.selectContainer(container))
					&& (!rightHoleController.selectContainer(container))
					&& (!bottomHoleController.selectContainer(container))
					&& (!centerHoleController.selectContainer(container)))
				visible = false;
		}
		if (!visible)
			showContainer(container);
	}
}
