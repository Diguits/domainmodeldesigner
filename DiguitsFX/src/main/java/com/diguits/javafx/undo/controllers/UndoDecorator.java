package com.diguits.javafx.undo.controllers;

import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.DecoratorBase;
import com.diguits.javafx.container.decorators.OptionGroup;
import com.diguits.javafx.container.decorators.OptionListBuilder;
import com.diguits.javafx.undo.UndoManager;
import com.google.inject.Inject;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;

public class UndoDecorator extends DecoratorBase {

	public static final String EDIT = "edit";
	Action editGroupAction;
	Action undoAction;
	Action redoAction;

	@Inject
	UndoManager undoManager;

	@Override
	protected String getMenuBundleName() {
		return "UndoBundle";
	}

	@Override
	protected void createActions() {
		BooleanBinding undoDisable = Bindings.not(undoManager.canUndoProperty());
		BooleanBinding redoDisable = Bindings.not(undoManager.canRedoProperty());

		editGroupAction = Action.builder(resources)
				.textKey("edit")
				.build();

		undoAction = Action.builder(resources)
				.image("undo")
				.textKey("undo")
				.toolTipKey("undo_tool_tip")
				.action(e->undoManager.undo())
				.disableWhen(undoDisable)
				.build();
		redoAction = Action.builder(resources)
				.image("redo")
				.textKey("redo")
				.toolTipKey("redo_tool_tip")
				.action(e->undoManager.redo())
				.disableWhen(redoDisable)
				.build();
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBuilder builder) {
		builder.addSeparator(44);
		builder.addOption().action(undoAction).priority(45);
		builder.addOption().action(redoAction).priority(46);
		builder.addSeparator(47);

	}

	@Override
	protected void buildMainMenuOptions(OptionListBuilder builder) {
		OptionGroup navigateOptionGroup = builder.addOptionGroup(EDIT)
				.action(editGroupAction)
				.priority(200)
				.get();

		navigateOptionGroup.addOption().action(undoAction).priority(100);
		navigateOptionGroup.addOption().action(redoAction).priority(200);
	}

}
