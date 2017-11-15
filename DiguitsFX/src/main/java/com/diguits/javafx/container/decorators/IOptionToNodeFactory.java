package com.diguits.javafx.container.decorators;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;

public interface IOptionToNodeFactory {
	public List<Menu> createMainMenuItems(List<OptionBase> options);
	public ContextMenu createContextMenu(List<OptionBase> options);
	public List<Node> createToolBarOptions(List<OptionBase> options);
}
