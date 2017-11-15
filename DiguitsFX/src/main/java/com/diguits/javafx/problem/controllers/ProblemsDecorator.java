package com.diguits.javafx.problem.controllers;

import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.ContainerDecoratorBase;
import com.diguits.javafx.container.decorators.OptionListBilder;
import com.google.inject.Inject;

import javafx.scene.image.ImageView;

public class ProblemsDecorator extends ContainerDecoratorBase<ProblemItem, ProblemsController> {

	private Action clearAction;

	@Inject
	public ProblemsDecorator(ProblemsController controller) {
		super(controller);
	}

	@Override
	protected String getMenuBundleName() {
		return "ProblemsBundle";
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
		return "monitor_error";
	}

	@Override
	public String getTitleResource() {
		return "problems";
	}

	@Override
	public ImageView getImageForModel(ProblemItem item) {
		return null;
	}

	@Override
	protected void buildCustomToolBarOptions(OptionListBilder builder) {
		builder.addOption()
				.action(clearAction)
				.priority(100);
	}

	@Override
	protected void buildContextMenuOptions(ProblemItem model, OptionListBilder builder) {

	}

	@Override
	protected void buildMainToolBarOptions(OptionListBilder builder) {

	}

	@Override
	protected void buildMainMenuOptions(OptionListBilder builder) {

	}
}
