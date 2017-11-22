package com.diguits.javafx.container.decorators;

import com.diguits.javafx.container.controllers.ITreeContainerController;
import com.diguits.javafx.model.NamedModelBase;

public abstract class TreeContainerDecorator<TModel extends NamedModelBase, TController extends ITreeContainerController<?, TModel>>
		extends ContainerDecoratorBase<TModel, TController> {

	protected Action openEditorAction;
	protected Action renameAction;
	protected Action collapseAction;
	protected Action expandAction;

	public TreeContainerDecorator(TController controller) {
		super(controller);
	}

	@Override
	protected void createActions() {
		openEditorAction = Action.builder(resources)
				.textKey("open_editor")
				.image("tab_add")
				.action(e -> controller.openEditorForSelectedModel())
				.build();

		renameAction = Action.builder(resources)
				.textKey("rename")
				.image("textfield_rename")
				.action(e -> controller.renameSelectedModel())
				.build();

		collapseAction = Action.builder(resources)
				.image("collapse_blue")
				.toolTipKey("collapse_tree_tool_tip")
				.action(e -> controller.collapse())
				.disableWhen(controller.getCanCollapse())
				.build();

		expandAction = Action.builder(resources)
				.image("expand_blue")
				.toolTipKey("expand_tree_tool_tip")
				.action(e -> controller.expand())
				.disableWhen(controller.getCanExpand())
				.build();
	}

	@Override
	protected void buildContextMenuOptions(TModel model, OptionListBuilder builder) {
		if (!controller.isTreeItemContainer(model))
			buildDefaultContextMenuOptions(builder);
	}

	protected void buildDefaultContextMenuOptions(OptionListBuilder builder) {
		builder.addOption()
				.action(openEditorAction)
				.priority(100);
		builder.addOption()
				.action(renameAction)
				.priority(200);
	}

	@Override
	protected void buildCustomToolBarOptions(OptionListBuilder builder) {
		builder.addOption()
				.priority(100)
				.action(collapseAction);
		builder.addOption()
				.priority(100)
				.action(expandAction);
	}

}
