package com.diguits.javafx.container.decorators;

import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCombination.Modifier;

public class Action {
	String text;
	String imageName;
	String toolTip;
	ObservableValue<? extends Boolean> visible;
	ObservableValue<? extends Boolean> disable;
	KeyCombination combination;
	EventHandler<ActionEvent> action;

	public Action() {
		super();
	}

	public static ActionBuilder builder(ResourceBundle resources) {
		return new ActionBuilder(resources);
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public ObservableValue<? extends Boolean> getVisible() {
		return visible;
	}

	public void setVisible(ObservableValue<? extends Boolean> visible) {
		this.visible = visible;
	}

	public ObservableValue<? extends Boolean> getDisable() {
		return disable;
	}

	public void setDisable(ObservableValue<? extends Boolean> disable) {
		this.disable = disable;
	}

	public KeyCombination getCombination() {
		return combination;
	}

	public void setCombination(KeyCombination combination) {
		this.combination = combination;
	}

	public EventHandler<ActionEvent> getAction() {
		return action;
	}

	public void setAction(EventHandler<ActionEvent> action) {
		this.action = action;
	}

	String getText() {
		return text;
	}

	void setText(String text) {
		this.text = text;
	}

	String getToolTip() {
		return toolTip;
	}

	void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}


	public static class ActionBuilder {
		private Action action;
		private ResourceBundle resources;

		ActionBuilder(ResourceBundle resources) {
			action = new Action();
			this.resources = resources;
		}

		public Action build() {
			return action;
		}

		public ActionBuilder textKey(String textKey) {
			action.setText(getResourceString(textKey));
			return this;
		}

		public ActionBuilder image(String imageName) {
			action.setImageName(imageName);
			return this;
		}

		public ActionBuilder toolTipKey(String toolTipKey) {
			action.setToolTip(getResourceString(toolTipKey));
			return this;
		}

		public ActionBuilder visibleWhen(ObservableValue<? extends Boolean> visible) {
			action.setVisible(visible);
			return this;
		}

		public ActionBuilder disableWhen(ObservableValue<? extends Boolean> disable) {
			action.setDisable(disable);
			return this;
		}

		public ActionBuilder keyCombination(KeyCombination combinatio) {
			action.setCombination(combinatio);
			return this;
		}

		public ActionBuilder keyCombination(final KeyCode code,
				final Modifier... modifiers) {
			action.setCombination(new KeyCodeCombination(code, modifiers));
			return this;
		}

		public ActionBuilder action(EventHandler<ActionEvent> action) {
			this.action.setAction(action);
			return this;
		}

		protected String getResourceString(String key) {
			if (key != null && resources.containsKey(key))
				return resources.getString(key);
			return null;
		}
	}
}
