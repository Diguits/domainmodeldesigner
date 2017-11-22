package com.diguits.javafx.system.controllers;

import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.DecoratorBase;
import com.diguits.javafx.container.decorators.OptionGroup;
import com.diguits.javafx.container.decorators.OptionListBuilder;
import com.google.inject.Inject;

public class SystemDecorator extends DecoratorBase {
	public static final String FILE = "file";

	private Action fileAction;
	private Action exitAction;

	@Inject
	SystemController systemController;

	@Override
	protected String getMenuBundleName() {
		return "SystemBundle";
	}

	@Override
	protected void createActions() {
		fileAction = Action.builder(resources)
				.textKey("file")
				.build();
		exitAction = Action.builder(resources)
				.textKey("exit")
				.action(e -> systemController.exit())
				.build();
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBuilder builder) {

	}

	@Override
	protected void buildMainMenuOptions(OptionListBuilder builder) {
		OptionGroup fileOptionGroup = builder.addOptionGroup(SystemDecorator.FILE)
				.action(fileAction)
				.priority(100)
				.get();
		fileOptionGroup.addSeparator(100000);
		fileOptionGroup.addOption()
				.action(exitAction)
				.priority(100001);

	}

}
