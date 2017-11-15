package com.diguits.javafx.settings.controllers;

import java.util.ArrayList;
import java.util.List;

public class SettingsItem {

	private String name;
	private String caption;
	private String hint;
	private List<SettingsItem> children;
	private ISettingItemController<?> controller;

	public SettingsItem() {
		children = new ArrayList<SettingsItem>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public List<SettingsItem> getChildren() {
		return children;
	}

	public void setChildren(List<SettingsItem> children) {
		this.children = children;
	}

	public ISettingItemController<?> getController() {
		return controller;
	}

	public void setController(ISettingItemController<?> controller) {
		this.controller = controller;
	}
}
