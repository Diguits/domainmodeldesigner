package com.diguits.domainmodeldesigner.domainmodelstructure.controllers;

import com.diguits.domainmodeldesigner.domainmodelstructure.models.DomainModelStructureItem;
import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.OptionListBuilder;
import com.diguits.javafx.container.decorators.TreeContainerDecorator;
import com.google.inject.Inject;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

public class DomainModelStructureTreeDecorator
		extends TreeContainerDecorator<DomainModelStructureItem, DomainModelStructureTreeController> {

	private Action copyAction;

	@Inject
	public DomainModelStructureTreeDecorator(DomainModelStructureTreeController controller) {
		super(controller);
	}

	@Override
	protected void createActions() {
		super.createActions();
		copyAction = Action.builder(resources)
				.textKey("copy")
				.image("page_white_copy")
				.action(e -> controller.copySelectedPath())
				.disableWhen(controller.selectedModelProperty().isNull())
				.keyCombination(KeyCode.C, KeyCombination.CONTROL_ANY)
				.build();
	}

	@Override
	protected String getMenuBundleName() {
		return "DomainModelStructureTreeBundle";
	}

	@Override
	public String getImageName() {
		return "class_hierarchy";
	}

	@Override
	public String getTitleResource() {
		return "entity_domainModel_structure";
	}

	@Override
	protected void buildContextMenuOptions(DomainModelStructureItem model, OptionListBuilder builder) {
		builder.addOption()
				.action(copyAction)
				.priority(100);
	}

	@Override
	public ImageView getImageForModel(DomainModelStructureItem item) {
		return null;
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBuilder builder) {

	}

	@Override
	protected void buildMainMenuOptions(OptionListBuilder builder) {

	}
}
