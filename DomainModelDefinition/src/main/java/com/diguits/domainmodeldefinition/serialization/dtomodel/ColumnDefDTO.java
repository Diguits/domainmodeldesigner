package com.diguits.domainmodeldefinition.serialization.dtomodel;

import org.simpleframework.xml.Default;

@Default(required = false)
public class ColumnDefDTO extends BaseDefDTO {
	private boolean canOrder;
	private boolean canFilter;
	private int widthWeight;
	private boolean visible;
	private String path;

	public ColumnDefDTO() {
		widthWeight = 100;
		visible = true;
		canFilter = true;
		canOrder = true;
	}
	public int getWidthWeight() {
		return widthWeight;
	}

	public void setWidthWeight(int widthWeight) {
		this.widthWeight = widthWeight;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean getCanOrder() {
		return canOrder;
	}

	public void setCanOrder(boolean canOrder) {
		this.canOrder = canOrder;
	}

	public boolean getCanFilter() {
		return canFilter;
	}

	public void setCanFilter(boolean canFilter) {
		this.canFilter = canFilter;
	}
}