package com.diguits.domainmodeldesigner.domainmodelstructure.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.*;
import com.diguits.domainmodeldesigner.domainmodelstructure.models.DomainModelStructureItem;
import com.diguits.domainmodeldesigner.domainmodelstructure.views.DomainModelStructureTreeView;
import com.diguits.javafx.container.controllers.TreeContainerController;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;

public class DomainModelStructureTreeController extends TreeContainerController<DomainModelStructureTreeView, DomainModelStructureItem> {

	@Inject
	private DomainModelStructureTreeBuilder treeBuilder;

	@Inject
	public DomainModelStructureTreeController(DomainModelStructureTreeView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		loadDomainModelStructure();
	}

	private void loadDomainModelStructure() {
		fill(treeBuilder,
				new Class<?>[] { ApplicationDef.class, BaseDef.class, ColumnDef.class, DataType.class,
						EntityDef.class, RelationshipDef.class, RelationshipPartDef.class, EnumDef.class,
						EnumValueDef.class, FieldDef.class, FieldGroupDef.class, FieldSubgroupDef.class,
						FilterDef.class, FilterLogicalOperator.class, FilterType.class, IndexDef.class,
						BoundedContextDef.class, PathDefBase.class, RelationOverrideDef.class, RelationshipType.class,
						ModuleDef.class, DomainModelDef.class });

	}

	@Override
	protected ContainerPos getDefaultPosition() {
		return ContainerPos.LEFT;
	}

	@Override
	public boolean isTreeItemContainer(DomainModelStructureItem item) {
		return item.getClass() == DomainModelStructureItem.class;
	}

	@Override
	protected NamedModelBase findModel(UUID modelId) {
		return null;
	}

	@Override
	protected List<Class<?>> getModelClasses() {
		List<Class<?>> result = new ArrayList<Class<?>>();
		result.add(DomainModelStructureItem.class);
		return result;
	}

	public void copySelectedName() {
		getView().copySelectedName();
	}
}
