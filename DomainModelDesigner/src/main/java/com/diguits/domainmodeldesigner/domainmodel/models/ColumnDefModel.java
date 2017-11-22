package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public class ColumnDefModel extends PathDefBaseModel {

	public ColumnDefModel() {
		super();
	}

	private BooleanProperty visible;
	private IntegerProperty widthWeight;
	private BooleanProperty canFilter;
	private BooleanProperty canOrder;

	public boolean getVisible() {
		if (visible != null)
			return visible.get();
		return false;
	}

	public void setVisible(boolean visible) {
		if (this.visible != null || visible != true) {
			visibleProperty().set(visible);
		}
	}

	public BooleanProperty visibleProperty() {
		if (visible == null) {
			visible = new SimpleBooleanProperty(this, "visible", true);
		}
		return visible;
	}

	public int getWidthWeight() {
		if (widthWeight != null)
			return widthWeight.get();
		return 0;
	}

	public void setWidthWeight(int widthWeight) {
		if (this.widthWeight != null || widthWeight != 100) {
			widthWeightProperty().set(widthWeight);
		}
	}

	public IntegerProperty widthWeightProperty() {
		if (widthWeight == null) {
			widthWeight = new SimpleIntegerProperty(this, "widthWeight", 100);
		}
		return widthWeight;
	}

	public boolean getCanFilter() {
		if (canFilter != null)
			return canFilter.get();
		return false;
	}

	public void setCanFilter(boolean canFilter) {
		if (this.canFilter != null || canFilter != true) {
			canFilterProperty().set(canFilter);
		}
	}

	public BooleanProperty canFilterProperty() {
		if (canFilter == null) {
			canFilter = new SimpleBooleanProperty(this, "canFilter", true);
		}
		return canFilter;
	}

	public boolean getCanOrder() {
		if (canOrder != null)
			return canOrder.get();
		return false;
	}

	public void setCanOrder(boolean canOrder) {
		if (this.canOrder != null || canOrder != true) {
			canOrderProperty().set(canOrder);
		}
	}

	public BooleanProperty canOrderProperty() {
		if (canOrder == null) {
			canOrder = new SimpleBooleanProperty(this, "canOrder", true);
		}
		return canOrder;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitColumnDefModel(this, owner);
	}

	@Override
	public EntityDefModel getEntity() {
		if (getOwner() != null) {
			if (getOwner() instanceof EntityDefModel)
				return (EntityDefModel) getOwner();
			else if (getOwner() instanceof FieldRelationshipDataDefModel) {
				FieldRelationshipDataDefModel relationData = (FieldRelationshipDataDefModel) getOwner();
				if (relationData != null && relationData.getRelationshipPart() != null && relationData
						.getRelationshipPart().getToRelationshipPart().getDomainObject() instanceof EntityDefModel)
					return (EntityDefModel) relationData.getRelationshipPart().getToRelationshipPart()
							.getDomainObject();
			}
		}
		return null;
	}
}
