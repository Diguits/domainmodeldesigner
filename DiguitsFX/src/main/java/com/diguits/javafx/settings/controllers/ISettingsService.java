package com.diguits.javafx.settings.controllers;

public interface ISettingsService {
	void registerSettingItem(String name, String caption, String hint, ISettingItemController<?> controller);

	void registerSettingItem(String name, String caption, String hint, ISettingItemController<?> controller,
			String parentName);

	<R> R getSettingValue(String itemName, String fieldName);

	Settings loadSettings();

	void saveSettings();

	Settings getSettings();
}
