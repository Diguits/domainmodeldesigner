package com.diguits.javafx.settings.controllers;

import java.util.ArrayList;
import java.util.List;

public class Settings {

	private List<SettingsItem> children;

	public Settings() {
		super();
		children = new ArrayList<SettingsItem>();
	}

	public List<SettingsItem> getChildren() {
		return children;
	}

	public void setChildren(List<SettingsItem> children) {
		this.children = children;
	}

}
