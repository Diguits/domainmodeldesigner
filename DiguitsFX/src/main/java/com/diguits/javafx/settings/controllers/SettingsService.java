package com.diguits.javafx.settings.controllers;

import java.io.File;

public class SettingsService implements ISettingsService {

	private static final String SETTING_EXT = "set";
	Settings setting;

	public SettingsService() {
		super();
		setting = new Settings();
	}

	@Override
	public void registerSettingItem(String name, String caption, String hint, ISettingItemController<?> controller) {
		registerSettingItem(name, caption, hint, controller, null);
	}

	@Override
	public void registerSettingItem(String name, String caption, String hint, ISettingItemController<?> controller,
			String parentName) {
		SettingsItem parent = findItem(parentName);
		SettingsItem item = new SettingsItem();
		item.setName(name);
		item.setCaption(caption);
		item.setHint(hint);
		item.setController(controller);
		if (parent != null)
			parent.getChildren().add(item);
		else
			setting.getChildren().add(item);

	}

	@Override
	public <R> R getSettingValue(String itemName, String fieldName) {
		SettingsItem item = findItem(itemName);
		if (item != null)
			return item.getController().getConfigValue(fieldName);
		return null;
	}

	private SettingsItem findItem(String itemName) {
		if (itemName == null)
			return null;
		for (SettingsItem child : setting.getChildren()) {
			if (child.getName().equals(itemName))
				return child;
			else {
				SettingsItem result = findItemR(child, itemName);
				if (result != null)
					return result;
			}
		}
		return null;
	}

	private SettingsItem findItemR(SettingsItem item, String itemName) {
		for (SettingsItem child : item.getChildren()) {
			if (child.getName().equals(itemName))
				return child;
			else {
				SettingsItem result = findItemR(child, itemName);
				if (result != null)
					return result;
			}
		}
		return null;
	}

	@Override
	public Settings loadSettings() {
		for (SettingsItem item : setting.getChildren()) {
			visiteTree(item, i -> {
				File file = getSettingsFile(i);
				i.getController().loadData(file);
			});
		}
		return setting;
	}

	@Override
	public void saveSettings() {
		for (SettingsItem item : setting.getChildren()) {
			visiteTree(item, i -> {
				File file = getSettingsFile(i);
				i.getController().saveData(file);
			});
		}
	}

	private File getSettingsFile(SettingsItem item) {
		File file = new File(getApplicationVersionString(), item.getName() + "." + SETTING_EXT);
		return file;
	}

	interface Visitor {
		void visite(SettingsItem item);
	}

	private void visiteTree(SettingsItem item, Visitor visitor) {
		visitor.visite(item);
		for (SettingsItem child : item.getChildren()) {
			visiteTree(child, visitor);
		}
	}

	public static String getUserDataDirectory() {
		return System.getProperty("user.home") + File.separator + ".domainmodeldesigner" + File.separator
				+ getApplicationVersionString() + File.separator;
	}

	private static String getApplicationVersionString() {
		return "1.0.0.0";
	}

	public Settings getSettings() {
		return setting;
	}

}
