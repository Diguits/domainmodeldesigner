package com.diguits.domainmodeldefinition.definitions;

public class ColumnDef extends PathDefBase {
	private boolean canOrder;
	private boolean canFilter;
	private int widthWeight;
	private boolean visible;

	public ColumnDef(BaseDef owner) {
		super(owner);
		initialize();
	}

	public ColumnDef() {
		initialize();
	}

	private void initialize() {
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

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitColumnDef(this, owner);
	}
}