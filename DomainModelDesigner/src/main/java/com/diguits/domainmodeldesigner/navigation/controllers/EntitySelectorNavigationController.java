package com.diguits.domainmodeldesigner.navigation.controllers;

import com.diguits.domainmodeldesigner.navigation.view.EntitySelectorNavigationView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.controllers.DomainModelTreeController;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.navigation.controllers.ModelSelectorNavigationController;
import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class EntitySelectorNavigationController extends ModelSelectorNavigationController<EntitySelectorNavigationView>{

	@Inject
	DomainModelClientService  domainModelClientService;

	@Inject
	DomainModelTreeController  domainModelTreeController;

	@Inject
	public EntitySelectorNavigationController(EntitySelectorNavigationView view) {
		super(view);
	}

	@Override
	protected ObservableList<NamedModelBase> getModels() {
		ObservableList<NamedModelBase> result = FXCollections.observableArrayList();
		if(domainModelClientService.getDomainModelDef()!=null)
			result = FXCollections.observableArrayList(domainModelClientService.getDomainModelDef().entitiesProperty());
		return result;
	}

	@Override
	protected TreeItem<?> getTreeItem(NamedModelBase selectedModel) {
		return domainModelTreeController.findTreeItemByModel((BaseDefModel) selectedModel);
	}

}
