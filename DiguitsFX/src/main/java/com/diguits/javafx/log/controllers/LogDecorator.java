package com.diguits.javafx.log.controllers;

import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.ContainerDecoratorBase;
import com.diguits.javafx.container.decorators.OptionListBilder;
import com.google.inject.Inject;

import javafx.scene.image.ImageView;

public class LogDecorator extends ContainerDecoratorBase<LogItem, LogController> {

	private Action clearAction;

	@Inject
	public LogDecorator(LogController controller) {
		super(controller);
	}

	@Override
	protected String getMenuBundleName() {
		return "LogBundle";
	}

	@Override
	protected void createActions() {
		clearAction = Action.builder(resources)
				.image("delete_croos")
				.toolTipKey("delete_croos_tool_tip")
				.action(e -> controller.clear())
				.build();
	}

	@Override
	public String getImageName() {
		return "action_log";
	}

	@Override
	public String getTitleResource() {
		return "logs";
	}

	@Override
	public ImageView getImageForModel(LogItem item) {
		return null;
	}

	@Override
	protected void buildCustomToolBarOptions(OptionListBilder builder) {
		builder.addOption()
		.action(clearAction)
		.priority(100);
	}

	@Override
	protected void buildContextMenuOptions(LogItem model, OptionListBilder builder) {
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBilder builder) {

	}

	@Override
	protected void buildMainMenuOptions(OptionListBilder builder) {

	}
}
