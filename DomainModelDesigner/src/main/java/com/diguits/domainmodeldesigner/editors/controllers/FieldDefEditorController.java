package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldefinition.services.ValueConverter;
import com.diguits.domainmodeldesigner.editors.views.FieldDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class FieldDefEditorController extends BaseDefEditorController<FieldDefEditorView, FieldDefModel> {

	@Inject
	DomainModelClientService domainModelClientService;
	private ObjectProperty<EntityDefModel> auxiliarToEntityProperty;
	private ObjectProperty<FieldDefModel> auxiliarToFieldProperty;

	@Inject
	public FieldDefEditorController(FieldDefEditorView view) {
		super(view);
		auxiliarToEntityProperty = new SimpleObjectProperty<EntityDefModel>(this, "auxiliarToEntityProperty", null);
		auxiliarToFieldProperty = new SimpleObjectProperty<FieldDefModel>(this, "auxiliarToFieldProperty", null);
		view.setAuxiliarToEntityProperty(auxiliarToEntityProperty);
		view.setAuxiliarToFieldProperty(auxiliarToFieldProperty);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (getModel() != null) {
			configureTreeTableModelViewActions(getView().getFilterValuesView(), FilterValueDefModel.class);
			configureTableModelViewActions(getView().getExtraColumnsView(), ColumnDefModel.class);
			configureTableModelViewActions(getView().getOverridesView(), RelationOverrideDefModel.class);
		}
		getView().setEntities(domainModelClientService.getDomainModelDef().entitiesProperty().sorted());
		getView().setValueObjects(domainModelClientService.getDomainModelDef().valueObjectsProperty().sorted());
		getView().setEnums(domainModelClientService.getDomainModelDef().allEnumsProperty().sorted());
	}

	@Override
	protected void modelUpdated(FieldDefModel oldField, FieldDefModel newField) {
		super.modelUpdated(oldField, newField);
		FieldDefModel model = getModel();
		if (model != null) {
			model.dataTypeProperty().addListener((v, o, n) -> {
				model.setMinValue(ValueConverter.tryToConver(model.getMinValue(), n));
				model.setMaxValue(ValueConverter.tryToConver(model.getMaxValue(), n));
				model.setDefaultValue(ValueConverter.tryToConver(model.getDefaultValue(), n));
			});

			if (getModel().getToRelationshipPart() != null)
				auxiliarToEntityProperty.set(
						(EntityDefModel) getModel().getToRelationshipPart().getDomainObject());
			auxiliarToFieldProperty.set(
					getModel().getToField());

			// When the ToEntity change the ToField is set to null and the new ToEntity is set to the ToRelationPart
			auxiliarToEntityProperty.addListener((v, o, n) -> {
				// The ToField is set to null and is evaluated the event auxiliarToFieldProperty change that is below
				auxiliarToFieldProperty.set(null);
				// The new ToEntity is set to the ToRelationPart
				model.getToRelationshipPart().setDomainObject(n);
				model.getDomainObjectOwner().getDomainModelOwner().notifyRelationshipDomainObjectChange(o, n);
			});

			auxiliarToFieldProperty.addListener((v, o, n) -> {
				// If the actual(old) ToField is not null
				if (model.getToField() != null) {
					// oldRelation store the actual(old) Relationship
					RelationshipDefModel oldRelation = model.getRelationshipPart().getRelationshipOwner();

					model.getRelationshipPart().setField(null);

					// If the new ToField is null or the ToField of the new ToField is not null
					// is needed to create a new Relationship
					if (n == null || n.getToField() != null) {
						// Create a new relationship
						RelationshipDefModel relation = new RelationshipDefModel();
						// Set owner to the relation
						relation.setOwner(model.getDomainObjectOwner().getDomainModelOwner());
						// Set the old Relationship CascadeDelete to the new Relationship
						relation.setCascadeDelete(oldRelation.getCascadeDelete());

						// Set the field(model) that is editing in the from of the relation
						// (remember that setting field will set the entity to the RealtionshipPart)
						relation.getFromPart().setField(model);
						// Set to the field(model) the fromRelationshipPart
						model.setRelationshipPart(relation.getFromPart());

						// If the new field is not null and has a relationshipPart
						if (n != null && n.getRelationshipPart() != null) {
							// Set null to the field of the RelationshipPart of the new field
							n.getRelationshipPart().setField(null);
						}
						// Set the new field to the ToRelatioshipPart
						// (remember that setting field will set the entity to the RealtionshipPart)
						relation.getToPart().setField(n);
						// If new field is not null
						if (n != null)
							// Set the ToRelationshipPart of the new Relationship to the new field
							n.setRelationshipPart(relation.getToPart());
						// Add relation to domainModel Relationship list
						model.getDomainObjectOwner().getDomainModelOwner().getRelations().add(relation);

					} else {
						// If the new field is not null and the ToField of the new field is null
						// Set the field(model) to the ToRelationshipPart of the new field
						// (remember that setting field will set the entity to the RealtionshipPart)
						n.getToRelationshipPart().setField(model);
						// Set the ToRelationshipPart of the new field Relationship to the field(model)
						model.setRelationshipPart(n.getToRelationshipPart());

					}
					// If the ToField of editing field(model) is null
				} else {
					// If the new field is not null and its RelationshipPart is not null
					if (n != null && n.getRelationshipPart() != null)
						// If the ToField of the new field is null
						if (n.getToField() == null)
							// Remove the relationship of the new field
							// TODO Merge this Relationship with model.getRelationshipPart().getRelationshipOwner()
							// before delete
							model.getDomainObjectOwner().getDomainModelOwner().getRelations()
									.remove(n.getRelationshipPart().getRelationshipOwner());
						else
							// If the ToField of the new field is not null set to null the field of the RelationshipPart
							// of the new field
							n.getRelationshipPart().setField(null);

					// Set the new field to the ToRelatioshipPart of the editing field(model)
					// (remember that setting field will set the entity to the RealtionshipPart)
					model.getToRelationshipPart().setField(n);
					// If new field is not null
					if (n != null)
						// set the ToRelationshipPart of editing field(model) to the new field
						n.setRelationshipPart(model.getToRelationshipPart());
				}
			});
		}
	}

	@Override
	protected <M extends NamedModelBase> BaseDefModel getAddingModelOwner(M model) {
		if (model != null && (model instanceof FilterValueDefModel || model instanceof RelationOverrideDefModel
				|| model instanceof ColumnDefModel))
			return getModel().getRelationshipData();
		else
			return super.getAddingModelOwner(model);
	}
}
