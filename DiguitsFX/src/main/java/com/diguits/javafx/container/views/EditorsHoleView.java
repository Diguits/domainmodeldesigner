package com.diguits.javafx.container.views;

import java.util.ArrayList;
import java.util.List;

import com.diguits.javafx.container.controllers.IContainerController;
import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.IOptionToNodeFactory;
import com.diguits.javafx.container.decorators.OptionBase;
import com.diguits.javafx.container.decorators.OptionListBuilder;
import com.google.inject.Inject;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;

public class EditorsHoleView extends HoleViewBase<TabPane> {

	@Inject
	private IOptionToNodeFactory optionToNodeFactory;

	@Override
	protected TabPane buildView() {
		super.buildView();
		return tabPane;
	}

	@Override
	public Tab addTab(StringProperty title, ImageView image, Node view, IContainerController<?, ?> container) {
		Tab result = super.addTab(title, image, view, container);
		ContextMenu contexMenu = buildContextMenuItems(result);
		result.setContextMenu(contexMenu);
		return result;
	}

	private ContextMenu buildContextMenuItems(Tab tab) {
		Action closeTab = Action.builder(resources)
				.textKey("close")
				.image("tab_delete")
				.action(e -> tabPane.getTabs().remove(tab))
				.build();

		Action closeOtherTabs = Action.builder(resources)
				.textKey("close_others")
				.image("tab_content_others_delete")
				.action(e -> {
					int i = tabPane.getTabs().size() - 1;
					while (i >= 0) {
						if (tabPane.getTabs().get(i) != tab)
							tabPane.getTabs().remove(i);
						i--;
					}
				})
				.build();
		Action closeAllTabs = Action.builder(resources)
				.textKey("close_all")
				.image("tab_content_delete")
				.action(e ->tabPane.getTabs().removeAll(tabPane.getTabs()))
				.build();

		List<OptionBase> options = new ArrayList<OptionBase>();
		OptionListBuilder builder = new OptionListBuilder(options);
		builder.addOption().action(closeTab);
		builder.addOption().action(closeOtherTabs);
		builder.addOption().action(closeAllTabs);

		return optionToNodeFactory.createContextMenu(options);
	}

}
