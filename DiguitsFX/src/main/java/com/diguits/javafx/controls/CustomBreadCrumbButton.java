package com.diguits.javafx.controls;

import impl.org.controlsfx.skin.BreadCrumbBarSkin.BreadCrumbButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

public class CustomBreadCrumbButton extends BreadCrumbButton {
	private ObjectProperty<TreeItem<?>> crum;

	public CustomBreadCrumbButton(TreeItem<?> crum, String text) {
		super(text);
		setCrum(crum);
	}

	public CustomBreadCrumbButton(TreeItem<?> crum, String text, Node gfx) {
		super(text, gfx);
		setCrum(crum);
	}

	public TreeItem<?> getCrum() {
		if (crum != null)
			return crum.get();
		return null;
	}

	public void setCrum(TreeItem<?> crum) {
		if (this.crum != null || crum != null) {
			crumProperty().set(crum);
		}
	}

	public ObjectProperty<TreeItem<?>> crumProperty() {
		if (crum == null) {
			crum = new SimpleObjectProperty<TreeItem<?>>(this, "crum", null);
		}
		return crum;
	}

}
