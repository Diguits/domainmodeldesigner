package com.diguits.domainmodeldesigner.editors.views.common;

import java.util.ArrayList;
import java.util.List;

public class TableInfo<TModel> {
	private List<TableColumnInfo<TModel,?>> columns;

	public TableInfo() {
		super();
		columns = new ArrayList<TableColumnInfo<TModel,?>>();
	}

	public List<TableColumnInfo<TModel, ?>> getColumns() {
		return columns;
	}
}
