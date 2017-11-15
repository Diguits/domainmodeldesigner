package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.RelationOverrideDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldRelationshipDataDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;
import com.google.inject.Inject;

import javafx.collections.FXCollections;

public class RelationOverrideDefEditorController
		extends BaseDefEditorController<RelationOverrideDefEditorView, RelationOverrideDefModel> {

	@Inject
	DomainModelClientService domainModelClientService;

	@Inject
	public RelationOverrideDefEditorController(RelationOverrideDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			FieldRelationshipDataDefModel realtionData = (FieldRelationshipDataDefModel) getModel().getOwner();
			if (realtionData != null && realtionData.getDomainObject() instanceof EntityDefModel) {
				getView().setForEntities(FXCollections.observableArrayList(
						domainModelClientService.getInheritance((EntityDefModel) realtionData.getDomainObject())));
				if (realtionData.getRelationshipPart() != null
						&& realtionData.getRelationshipPart().getToRelationshipPart() != null &&
						realtionData.getRelationshipPart().getToRelationshipPart()
								.getDomainObject() instanceof EntityDefModel) {
					getView().setOverrideEntities(
							FXCollections.observableArrayList(domainModelClientService.getInheritance(
									(EntityDefModel) realtionData.getRelationshipPart().getToRelationshipPart().getDomainObject())));
				}
			}
		}
	}
}
