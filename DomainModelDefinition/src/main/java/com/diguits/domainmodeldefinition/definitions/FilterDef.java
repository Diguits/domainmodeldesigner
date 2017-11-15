package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class FilterDef extends PathDefBase {
	private FilterLogicalOperator logicalOperator;
	private FilterType filterType;
	private Boolean useOperator;
	private List<FilterDef> filters;

	public FilterDef(BaseDef owner) {
		super(owner);
		Initialize();
	}

	public FilterDef() {
		super();
		Initialize();
	}

	private void Initialize() {
		useOperator = true;
		filters = new ArrayList<FilterDef>();
	}

	public FilterLogicalOperator getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(FilterLogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public FilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(FilterType filterType) {
		this.filterType = filterType;
	}

	public Boolean getUseOperator() {
		return useOperator;
	}

	public void setUseOperator(Boolean useOperator) {
		this.useOperator = useOperator;
	}

	public List<FilterDef> getFilters() {
		return filters;
	}

	public void setFilters(List<FilterDef> filters) {
		this.filters = filters;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		for (FilterDef filterDef : filters) {
			filterDef.accept(visitor, this);
		}

		visitor.visitFilterDef(this, owner);
	}
}