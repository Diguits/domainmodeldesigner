package com.diguits.javafx.container.decorators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class OptionToNodeFactory implements IOptionToNodeFactory {

	@InjectLogger
	Logger logger;

	@Override
	public List<Menu> createMainMenuItems(List<OptionBase> options) {
		List<Menu> result = new ArrayList<Menu>();
		for (OptionBase option : options) {
			if (option instanceof OptionGroup) {
				Menu item = createMenu((OptionGroup) option);
				if (item != null)
					result.add(item);
				else
					logger.error("The Menu for the option group »{}« could not be created",
						option.toString());
			}
		}
		return result;
	}

	private MenuItem createMenuItem(OptionBase option) {
		MenuItem item = null;
		if (option instanceof Option)
			item = createMenuItem((Option) option);
		if (option instanceof OptionGroup)
			item = createMenu((OptionGroup) option);
		else if (option instanceof OptionSeparator)
			item = createMenuSeparator((OptionSeparator) option);
		return item;
	}

	public List<MenuItem> createMenuItems(List<OptionBase> options) {
		List<MenuItem> result = new ArrayList<MenuItem>();
		for (OptionBase option : options) {
			MenuItem item = createMenuItem(option);
			if (item != null)
				result.add(item);
			else
				logger.error("The Menu Item for the option »{}« could not be created",
						option.toString());
		}
		return result;
	}

	@Override
	public ContextMenu createContextMenu(List<OptionBase> options) {
		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(createMenuItems(options));
		return contextMenu;
	}

	private Menu createMenu(OptionGroup option) {
		Menu menu = (Menu) createMenuItem(option, true);
		if (menu != null) {
			List<OptionBase> options = option.getOptions();
			options.sort((o1, o2) -> {
				return Integer.compare(o1.getPriority(), o2.getPriority());
			});
			menu.getItems().addAll(createMenuItems(options));
		}
		return menu;

	}

	public MenuItem createMenuItem(Option option) {
		return createMenuItem(option, false);
	}

	public MenuItem createMenuItem(Option option, boolean group) {
		Action action = option.getAction();
		if (action != null) {
			String text = action.getText();
			String imageName = action.getImageName();
			ImageView image = null;
			if (imageName != null && !imageName.isEmpty())
				image = buildImageView(imageName);
			MenuItem item = createItem(text, image, group);
			item.setOnAction(action.getAction());
			item.setMnemonicParsing(true);
			if (action.getCombination() != null)
				item.setAccelerator(action.getCombination());
			if (action.getDisable() != null)
				item.disableProperty().bind(action.getDisable());
			if (action.getVisible() != null)
				item.visibleProperty().bind(action.getVisible());
			item.setUserData(option);
			return item;
		}
		return null;
	}

	private MenuItem createItem(String text, ImageView image, boolean group) {
		if (group)
			return new Menu(text, image);
		else
			return new MenuItem(text, image);
	}

	public SeparatorMenuItem createMenuSeparator(OptionSeparator option) {
		SeparatorMenuItem separator = new SeparatorMenuItem();
		separator.setUserData(option);
		return separator;
	}

	protected ImageView buildImageView(String imageName) {
		if (imageName != null && !imageName.isEmpty())
			try {
				return new ImageView("/images/" + imageName + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<Node> createToolBarOptions(List<OptionBase> options) {
		List<Node> result = new ArrayList<Node>();
		for (OptionBase option : options) {
			if (option instanceof Option) {
				Button button = createButton((Option) option);
				if(button!=null)
					result.add(button);
				else
					logger.error("The button for the option »{}« could not be created",
							option.toString());
			} else if (option instanceof OptionSeparator) {
				Separator separator = createSeparator((OptionSeparator) option);
				if(separator!=null)
					result.add(separator);
				else
					logger.error("The separator for the option »{}« could not be created",
							option.toString());
			}
		}
		return result;
	}

	public Button createButton(Option option) {
		Action action = option.getAction();
		if (action != null) {
			String text = null;//action.getText();
			String imageName = action.getImageName();
			ImageView image = null;
			if (imageName != null && !imageName.isEmpty())
				image = buildImageView(imageName);
			Button button = new Button(text, image);
			String toolTip = action.getToolTip();
			if (toolTip != null && !toolTip.isEmpty())
				button.setTooltip(new Tooltip(toolTip));
			button.setOnAction(action.getAction());
			button.setFocusTraversable(false);
			if (action.getDisable() != null)
				button.disableProperty().bind(action.getDisable());
			if (action.getVisible() != null)
				button.visibleProperty().bind(action.getVisible());
			button.setUserData(option);
			return button;
		}
		return null;
	}

	public Separator createSeparator(OptionSeparator option) {
		Separator separator = new Separator(Orientation.VERTICAL);
		separator.setPrefWidth(4);
		separator.setUserData(option);
		return separator;
	}

}
