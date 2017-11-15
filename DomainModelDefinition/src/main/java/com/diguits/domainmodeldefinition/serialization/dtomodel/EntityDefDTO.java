package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class EntityDefDTO extends DomainObjectDefDTO {

	private UUID parentEntityId;
	private String parentEntityName;
	private boolean isAbstract;
	private boolean isHierarchial;
	private boolean isMasterDetail;
	private boolean useService;
	private boolean useVisualInterface;
	private UUID aggregateEntityId;
	private String aggregateEntityName;
	private List<String> additionalOperations;

	private List<FilterDefDTO> defaultFilters;
	private List<ColumnDefDTO> defaultColumns;

	public EntityDefDTO() {
		super();
		additionalOperations = new ArrayList<String>();
		defaultColumns = new ArrayList<ColumnDefDTO>();
		defaultFilters = new ArrayList<FilterDefDTO>();
		useService = true;
		useVisualInterface = true;
	}

	public UUID getParentEntityId() {
		return parentEntityId;
	}

	public void setParentEntityId(UUID parentEntityId) {
		this.parentEntityId = parentEntityId;
	}

	public String getParentEntityName() {
		return parentEntityName;
	}

	public void setParentEntityName(String parentEntityName) {
		this.parentEntityName = parentEntityName;
	}

	public boolean getIsAbstract() {
		return isAbstract;
	}

	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean getIsHierarchial() {
		return isHierarchial;
	}

	public void setIsHierarchial(boolean isHierarchial) {
		this.isHierarchial = isHierarchial;
	}

	public boolean getIsMasterDetail() {
		return isMasterDetail;
	}

	public void setIsMasterDetail(boolean isMasterDetail) {
		this.isMasterDetail = isMasterDetail;
	}

	public boolean getUseService() {
		return useService;
	}

	public void setUseService(boolean useService) {
		this.useService = useService;
	}

	public boolean getUseVisualInterface() {
		return useVisualInterface;
	}

	public void setUseVisualInterface(boolean useVisualInterface) {
		this.useVisualInterface = useVisualInterface;
	}

	public UUID getAggregateEntityId() {
		return aggregateEntityId;
	}

	public void setAggregateEntityId(UUID aggregateEntityId) {
		this.aggregateEntityId = aggregateEntityId;
	}

	public String getAggregateEntityName() {
		return aggregateEntityName;
	}

	public void setAggregateEntityName(String aggregateEntityName) {
		this.aggregateEntityName = aggregateEntityName;
	}

	public List<String> getAdditionalOperations() {
		return additionalOperations;
	}

	public void setAdditionalOperations(List<String> additionalOperations) {
		this.additionalOperations = additionalOperations;
	}

	public List<FilterDefDTO> getDefaultFilters() {
		return defaultFilters;
	}

	public void setDefaultFilters(List<FilterDefDTO> defaultFilters) {
		this.defaultFilters = defaultFilters;
	}

	public List<ColumnDefDTO> getDefaultColumns() {
		return defaultColumns;
	}

	public void setDefaultColumns(List<ColumnDefDTO> defaultColumns) {
		this.defaultColumns = defaultColumns;
	}

}