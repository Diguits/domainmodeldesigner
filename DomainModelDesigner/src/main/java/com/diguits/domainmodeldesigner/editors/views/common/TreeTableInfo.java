package com.diguits.domainmodeldesigner.editors.views.common;

import java.util.ArrayList;
import java.util.List;

import com.diguits.javafx.model.IHierarchialModel;

public class TreeTableInfo<TModel extends IHierarchialModel<TModel>> {
	private List<TreeTableColumnInfo<TModel,?>> columns;

	public TreeTableInfo() {
		super();
		columns = new ArrayList<TreeTableColumnInfo<TModel,?>>();
	}

	public List<TreeTableColumnInfo<TModel, ?>> getColumns() {
		return columns;
	}
}
