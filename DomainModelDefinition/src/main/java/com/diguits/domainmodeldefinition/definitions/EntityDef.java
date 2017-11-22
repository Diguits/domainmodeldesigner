package com.diguits.domainmodeldefinition.definitions;

import com.sun.org.glassfish.external.probe.provider.annotations.Probe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityDef extends DomainObjectDef {
	private EntityDef parentEntity;
	private boolean isAbstract;
	private boolean isHierarchial;
	private boolean useService;
	private boolean useVisualInterface;
	private boolean isMasterDetail;
	private EntityDef aggregateEntity;

	private List<String> additionalOperations;

	private List<FilterDef> defaultFilters;
	private List<ColumnDef> defaultColumns;

	public EntityDef(DomainModelDef owner) {
		super(owner);
	}

	public EntityDef() {
		super();
	}

	@Override
	protected void initialize() {
		super.initialize();
		fields = new ArrayList<FieldDef>();
		primaryKey = new ArrayList<FieldDef>();
		indexes = new ArrayList<IndexDef>();
		additionalOperations = new ArrayList<String>();
		defaultColumns = new ArrayList<ColumnDef>();
		defaultFilters = new ArrayList<FilterDef>();
		useService = true;
		useVisualInterface = true;
	}

	public void setParentEntity(EntityDef parentEntity) {
		this.parentEntity = parentEntity;
	}

	public EntityDef getParentEntity() {
		return parentEntity;
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

	public boolean getIsMasterDetail() {
		return isMasterDetail;
	}

	public void setIsMasterDetail(boolean isMasterDetail) {
		this.isMasterDetail = isMasterDetail;
	}

	public EntityDef getAggregateEntity() {
		return aggregateEntity;
	}

	public void setAggregateEntity(EntityDef aggregateEntity) {
		this.aggregateEntity = aggregateEntity;
	}

	public List<String> getAdditionalOperations() {
		return additionalOperations;
	}

	public void setAdditionalOperations(List<String> additionalOperations) {
		this.additionalOperations = additionalOperations;
	}

	public List<FilterDef> getDefaultFilters() {
		return defaultFilters;
	}

	public void setDefaultFilters(List<FilterDef> defaultFilters) {
		this.defaultFilters = defaultFilters;
	}

	public List<ColumnDef> getDefaultColumns() {
		return defaultColumns;
	}

	public void setDefaultColumns(List<ColumnDef> defaultColumns) {
		this.defaultColumns = defaultColumns;
	}

	public boolean getIsStateEntity() {
		FieldDef deleteField = getFieldByName("Deleted");
		return deleteField != null;
	}

	public boolean getIsAggregate() {
		return aggregateEntity == null;
	}

	public boolean getAggregateNotAbstract() {
		return aggregateEntity == null && !isAbstract;
	}

	public ColumnDef getColumnById(UUID columnId) {
		int i = 0;
		while (i < defaultColumns.size() && !defaultColumns.get(i).getId().equals(columnId))
			i++;
		return i < defaultColumns.size() ? defaultColumns.get(i) : null;
	}

	public FilterDef getFilterById(UUID filterId) {
		int i = 0;
		while (i < defaultFilters.size() && !defaultFilters.get(i).getId().equals(filterId))
			i++;
		return i < defaultFilters.size() ? defaultFilters.get(i) : null;
	}

	public List<FieldGroupDef> getAllFieldGroups() {
		List<FieldGroupDef> result = new ArrayList<FieldGroupDef>();
		if (parentEntity != null) {
			result.addAll(parentEntity.getAllFieldGroups());
		}
		result.addAll(fieldGroups);
		return result;
	}

	public List<FieldDef> getAllFields() {
		List<FieldDef> result = new ArrayList<FieldDef>();
		if (parentEntity != null) {
			result.addAll(parentEntity.getAllFields());
		}
		result.addAll(fields);
		return result;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		for (ColumnDef defaultColumn : defaultColumns) {
			defaultColumn.accept(visitor, this);
		}
		for (FilterDef defaultFilter : defaultFilters) {
			defaultFilter.accept(visitor, this);
		}

		visitor.visitEntityDef(this, owner);
	}

	public String getRealTableName() {
		return tableName == null || tableName.trim().isEmpty() ? name : tableName;
	}
}