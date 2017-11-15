package com.diguits.javafx.navigation.controllers;

import com.diguits.javafx.container.controllers.ContainerManager;
import com.diguits.javafx.container.controllers.EditorsHoleController;
import com.diguits.javafx.container.controllers.IContainerController;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.diguits.javafx.navigation.views.ContainerNavigationView;
import com.google.inject.Inject;

import javafx.stage.StageStyle;

public class ContainerNavigationController extends ViewControllerBase<ContainerNavigationView> {

	@Inject
	EditorsHoleController editors;

	@Inject
	ContainerManager containerManager;

	@Inject
	public ContainerNavigationController(ContainerNavigationView view) {
		super(view);
	}

	public void show() {
		getView().setEditorContainers(editors.orderedOrderedContainersProperty().get());
		getView().setContainers(containerManager.activeContainersProperty().get());
		if(DialogHelper.showNoButtonsDialog(this, resources.getString("navigate_to"), 450, 500, StageStyle.UNDECORATED)){
			IContainerController<?, ?> selectedContainer = getView().getSelectedContainer();
			if(selectedContainer !=null)
				containerManager.setFocusTo(selectedContainer);
		}
	}

}
