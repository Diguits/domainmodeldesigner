package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class FieldRelationshipDataDef extends BaseDef {

	private RelationshipPartDef relationshipPart;
	private List<FilterValueDef> filterValues;
	private List<ColumnDef> extraColumns;
	private List<RelationOverrideDef> overrides;

	public FieldRelationshipDataDef(FieldDef owner) {
		super(owner);
		initialize();
	}

	public FieldRelationshipDataDef() {
		initialize();
	}

	private void initialize() {
		filterValues = new ArrayList<FilterValueDef>();
		extraColumns = new ArrayList<ColumnDef>();
		overrides = new ArrayList<RelationOverrideDef>();
	}

	public List<FilterValueDef> getFilterValues() {
		return filterValues;
	}

	public void setFilterValues(List<FilterValueDef> filters) {
		this.filterValues = filters;
	}

	public List<ColumnDef> getExtraColumns() {
		return extraColumns;
	}

	public void setExtraColumns(List<ColumnDef> extraColumns) {
		this.extraColumns = extraColumns;
	}

	public List<RelationOverrideDef> getOverrides() {
		return overrides;
	}

	public void setOverrides(List<RelationOverrideDef> overrides) {
		this.overrides = overrides;
	}

	public FieldDef getOwnerRelationship() {
		return (FieldDef) getOwner();
	}

	public void setOwnerRelationship(RelationshipDef Relationship) {
		setOwner(Relationship);
	}

	public RelationshipPartDef getRelationshipPart() {
		return relationshipPart;
	}

	public void setRelationshipPart(RelationshipPartDef relationshipPart) {
		this.relationshipPart = relationshipPart;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		for (RelationOverrideDef overrideDef : overrides) {
			visitor.visitRelationOverrideDef(overrideDef, this);
		}

		for (FilterValueDef filterDef : filterValues) {
			visitor.visitFilterValueDef(filterDef, this);
		}

		for (ColumnDef extraColumnDef : extraColumns) {
			visitor.visitColumnDef(extraColumnDef, this);
		}

		visitor.visitFieldRelationDataDef(this, owner);
	}
}
