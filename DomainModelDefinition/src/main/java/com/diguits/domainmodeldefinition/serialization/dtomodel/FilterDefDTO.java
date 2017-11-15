package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;
import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;
import com.diguits.domainmodeldefinition.definitions.FilterType;

@Default(required = false)
public class FilterDefDTO extends BaseDefDTO
{
	private FilterLogicalOperator logicalOperator;
	private FilterType filterType;
	private Boolean useOperator;
	private String path;
	private List<FilterDefDTO> filters;

    public FilterDefDTO()
    {
        filters = new ArrayList<FilterDefDTO>();
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FilterDefDTO> getFilters() {
		return filters;
	}

	public void setFilters(List<FilterDefDTO> filters) {
		this.filters = filters;
	}
}