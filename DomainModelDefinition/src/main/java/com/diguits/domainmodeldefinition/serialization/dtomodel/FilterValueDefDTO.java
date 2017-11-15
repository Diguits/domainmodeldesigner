package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;
import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;
import com.diguits.domainmodeldefinition.definitions.FilterOperator;

@Default(required = false)
public class FilterValueDefDTO extends BaseDefDTO
{
	private FilterLogicalOperator logicalOperator;
	private String path;
	private List<FilterValueDefDTO> filterValues;
	private FilterOperator operator;
	private Object value;
	private Object value2;

    public FilterValueDefDTO()
    {
        filterValues = new ArrayList<FilterValueDefDTO>();
    }

	public FilterLogicalOperator getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(FilterLogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FilterValueDefDTO> getFilterValues() {
		return filterValues;
	}

	public void setFilterValues(List<FilterValueDefDTO> filterValues) {
		this.filterValues = filterValues;
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