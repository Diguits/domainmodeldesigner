package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.ColumnDefEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldRelationshipDataDefModel;
import com.google.inject.Inject;

public class ColumnDefEditorController extends BaseDefEditorController<ColumnDefEditorView, ColumnDefModel> {

	@Inject
	private PathEditorController pathEditorController;

	@Inject
	public ColumnDefEditorController(ColumnDefEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		getView().getBtnEditPath().setOnAction(e -> editPath());
	}

	private void editPath() {
		if (getModel() != null) {
			EntityDefModel entity = getEntity(getModel());
			if (entity != null) {
				String path = pathEditorController.editPath(entity, getModel().getPath());
				if (path != null)
					getModel().setPath(path);
			}
		}
	}

	private EntityDefModel getEntity(ColumnDefModel column) {
		if (column.getOwner() != null) {
			if (column.getOwner() instanceof EntityDefModel)
				return (EntityDefModel) column.getOwner();
			else if (column.getOwner() instanceof FieldRelationshipDataDefModel) {
				FieldRelationshipDataDefModel relationData = (FieldRelationshipDataDefModel) column.getOwner();
				if (relationData.getRelationshipPart() != null && relationData.getRelationshipPart()
						.getToRelationshipPart().getDomainObject() instanceof EntityDefModel)
					return (EntityDefModel) relationData.getRelationshipPart().getToRelationshipPart()
							.getDomainObject();
			}
		}
		return null;
	}

}
