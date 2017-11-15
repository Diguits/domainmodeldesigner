package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class FilterValueDef extends PathDefBase {
	private List<FilterValueDef> filterValues;
	protected FilterLogicalOperator logicalOperator;
	private FilterOperator operator;
	private Object value;
	private Object value2;

	public FilterValueDef(BaseDef owner) {
		super(owner);
		Initialize();
	}

	public FilterValueDef() {
		super();
		Initialize();
	}

	private void Initialize() {
		filterValues = new ArrayList<FilterValueDef>();
	}

	public List<FilterValueDef> getFilterValues() {
		return filterValues;
	}

	public void setFilterValues(List<FilterValueDef> filterValues) {
		this.filterValues = filterValues;
	}

	public FilterLogicalOperator getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(FilterLogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public FilterOperator getOperator() {
		return operator;
	}

	public void setOperator(FilterOperator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue2() {
		return value2;
	}

	public void setValue2(Object value2) {
		this.value2 = value2;
	}


}
