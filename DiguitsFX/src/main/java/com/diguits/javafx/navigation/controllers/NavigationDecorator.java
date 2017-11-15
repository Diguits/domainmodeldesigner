package com.diguits.javafx.navigation.controllers;

import com.diguits.javafx.container.controllers.EditorsHoleController;
import com.diguits.javafx.container.controllers.MainController;
import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.DecoratorBase;
import com.diguits.javafx.container.decorators.OptionGroup;
import com.diguits.javafx.container.decorators.OptionListBilder;
import com.google.inject.Inject;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class NavigationDecorator extends DecoratorBase {

	public static final String NAVIGATE = "navigate";
	Action navigateGroupAction;
	Action goFirstAction;
	Action backAction;
	Action forwardAction;
	Action goLastAction;
	Action navigateToAction;

	@Inject
	ContainerNavigationController navigationController;

	@Inject
	EditorsHoleController editorsHoleController;

	@Override
	protected String getMenuBundleName() {
		return "NavigateBundle";
	}

	@Inject
	public NavigationDecorator(MainController mainController) {
		super();
		mainController.getView().getNodeView().getScene().addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.TAB && e.isControlDown()) {
					navigationController.show();
					e.consume();
				}
			}
		});
	}

	@Override
	protected void createActions() {
		BooleanBinding backDisable = editorsHoleController.currentIndexProperty().lessThanOrEqualTo(0);
		BooleanBinding forwardDisable = editorsHoleController.currentIndexProperty()
				.greaterThanOrEqualTo(Bindings.size(editorsHoleController.getModelStack()).subtract(1))
				.or(editorsHoleController.currentIndexProperty().isEqualTo(-1));

		navigateGroupAction = Action.builder(resources)
				.textKey("navigate")
				.build();

		goFirstAction = Action.builder(resources)
				.image("go_first")
				.textKey("go_first")
				.toolTipKey("go_first_tool_tip")
				.action(e -> editorsHoleController.goFirst())
				.disableWhen(backDisable)
				.keyCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN)
				.build();
		backAction = Action.builder(resources)
				.image("back")
				.textKey("back")
				.toolTipKey("back_tool_tip")
				.action(e -> editorsHoleController.back())
				.disableWhen(backDisable)
				.keyCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN)
				.build();
		forwardAction = Action.builder(resources)
				.image("forward")
				.textKey("forward")
				.toolTipKey("forward_tool_tip")
				.action(e -> editorsHoleController.forward())
				.disableWhen(forwardDisable)
				.keyCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN)
				.build();
		goLastAction = Action.builder(resources)
				.image("go_last")
				.textKey("go_last")
				.toolTipKey("go_last_tool_tip")
				.action(e -> editorsHoleController.goLast())
				.disableWhen(forwardDisable)
				.keyCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN)
				.build();
		navigateToAction = Action.builder(resources)
				.image("compass")
				.textKey("navigate_to")
				.toolTipKey("navigate_to_tool_tip")
				.action(e -> navigationController.show())
				.keyCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN)
				.build();
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBilder builder) {
		builder.addOption().action(goFirstAction).priority(10);
		builder.addOption().action(backAction).priority(20);
		builder.addOption().action(forwardAction).priority(30);
		builder.addOption().action(goLastAction).priority(40);
	}

	@Override
	protected void buildMainMenuOptions(OptionListBilder builder) {
		OptionGroup navigateOptionGroup = builder.addOptionGroup(NAVIGATE)
				.action(navigateGroupAction)
				.priority(500)
				.get();

		navigateOptionGroup.addOption().action(goFirstAction).priority(100);
		navigateOptionGroup.addOption().action(backAction).priority(200);
		navigateOptionGroup.addOption().action(forwardAction).priority(300);
		navigateOptionGroup.addOption().action(goLastAction).priority(400);
		navigateOptionGroup.addOption().action(navigateToAction).priority(500);

	}

}
