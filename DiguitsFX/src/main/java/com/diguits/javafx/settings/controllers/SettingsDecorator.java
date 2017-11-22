package com.diguits.javafx.settings.controllers;

import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.DecoratorBase;
import com.diguits.javafx.container.decorators.OptionListBuilder;
import com.diguits.javafx.undo.controllers.UndoDecorator;

public class SettingsDecorator extends DecoratorBase {

	Action openSettings;

	public SettingsDecorator() {
		super();
	}

	@Override
	protected void createActions() {
		openSettings = Action.builder(resources)
				.textKey("settings")
				.image("setting_tools")
				.toolTipKey("open_settings_tool_tip")
				.action(e -> {
				})
				.build();

	}

	@Override
	protected String getMenuBundleName() {
		return "SettingsBundle";
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBuilder builder) {
		builder.addOption()
				.action(openSettings)
				.priority(10000);
	}

	@Override
	protected void buildMainMenuOptions(OptionListBuilder builder) {
		builder.addSeparator(UndoDecorator.EDIT)
		.priority(1000);
		builder.addOption(UndoDecorator.EDIT)
				.action(openSettings)
				.priority(1100);

	}

}
