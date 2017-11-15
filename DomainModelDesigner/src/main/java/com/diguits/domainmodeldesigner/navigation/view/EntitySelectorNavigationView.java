package com.diguits.domainmodeldesigner.navigation.view;

import com.diguits.domainmodeldesigner.domainmodel.controllers.DomainModelTreeDecorator;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.javafx.navigation.controllers.ModelSelectorNavigationController.ModelPositions;
import com.diguits.javafx.navigation.views.ModelSelectorNavigationView;
import com.google.inject.Inject;

import javafx.scene.image.Image;

public class EntitySelectorNavigationView extends ModelSelectorNavigationView{

	@Inject
	DomainModelTreeDecorator  domainModelTreeDecorator;

	@Override
	protected Image getImage(ModelPositions n) {
		return DomainModelTreeDecorator.getImageForModel(EntityDefModel.class).getImage();
	}
}
