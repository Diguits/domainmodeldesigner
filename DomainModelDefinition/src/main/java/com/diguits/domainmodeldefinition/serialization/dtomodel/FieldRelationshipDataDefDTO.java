package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class FieldRelationshipDataDefDTO extends BaseDefDTO {

	private UUID relationshipPartId;
	private List<FilterValueDefDTO> filterValues;
	private List<ColumnDefDTO> extraColumns;
	private List<RelationOverrideDefDTO> overrides;

	public FieldRelationshipDataDefDTO() {
		filterValues = new ArrayList<FilterValueDefDTO>();
		extraColumns = new ArrayList<ColumnDefDTO>();
		overrides = new ArrayList<RelationOverrideDefDTO>();
	}

	public List<FilterValueDefDTO> getFilterValues() {
		return filterValues;
	}

	public void setFilterValues(List<FilterValueDefDTO> filterValues) {
		this.filterValues = filterValues;
	}

	public List<ColumnDefDTO> getExtraColumns() {
		return extraColumns;
	}

	public void setExtraColumns(List<ColumnDefDTO> extraColumns) {
		this.extraColumns = extraColumns;
	}

	public List<RelationOverrideDefDTO> getOverrides() {
		return overrides;
	}

	public void setOverrides(List<RelationOverrideDefDTO> overrides) {
		this.overrides = overrides;
	}

	public UUID getRelationshipPartId() {
		return relationshipPartId;
	}

	public void setRelationshipPartId(UUID relationshipPartId) {
		this.relationshipPartId = relationshipPartId;
	}

}