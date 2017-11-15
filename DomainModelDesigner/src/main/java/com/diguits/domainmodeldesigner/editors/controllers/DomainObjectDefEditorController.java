package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.DomainObjectDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.IDomainObjectChangeListener;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;
import com.diguits.javafx.controllers.IViewController;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class DomainObjectDefEditorController<TView extends DomainObjectDefEditorView<TModel>, TModel extends DomainObjectDefModel> extends BaseDefEditorController<TView, TModel> implements IDomainObjectChangeListener{

	@Inject
	protected DomainModelClientService domainModelClientService;

	protected ObservableList<FieldDefModel> primaryKeyFieldToAdd;

	@Inject
	public DomainObjectDefEditorController(TView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		DomainObjectDefModel model = getModel();
		if (model != null) {
			configureTableModelViewActions(getView().getFieldsView(), FieldDefModel.class);
			configureTableModelViewActions(getView().getFieldGroupsView(), FieldGroupDefModel.class);
			configureTableModelViewActions(getView().getIndexesView(), IndexDefModel.class);
			configureTableModelViewActions(getView().getRelationsView(), RelationshipDefModel.class);
			domainModelClientService.getDomainModelDef().registerRelationshipDomainObjectChangeListener(this);
			// primary key
			primaryKeyFieldToAdd = FXCollections.observableArrayList();
			primaryKeyFieldToAdd.addAll(model.fieldsProperty().filtered(f -> !model.primaryKeyProperty().contains(f)));

			model.getFields().addListener(new ListChangeListener<FieldDefModel>() {

				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends FieldDefModel> change) {
					while (change.next()) {
						primaryKeyFieldToAdd.removeAll(change.getRemoved());
						// TODO borrar esta linea y poner esta dependencia en el
						// modelo
						model.getPrimaryKey().removeAll(change.getRemoved());
						primaryKeyFieldToAdd.addAll(change.getAddedSubList());
					}
				}

			});

			configureSwapModelViewActions(getView().getPrimaryKeySwapView(), primaryKeyFieldToAdd.sorted());
			getView().getPrimaryKeySwapView().setFromModel(primaryKeyFieldToAdd);
			getView().setBoundedContexts(domainModelClientService.getDomainModelDef().boundedContextsProperty().sorted());
			updateRelationshipList();
		}
	}

	@Override
	protected void modelUpdated(TModel o, TModel n) {
		super.modelUpdated(o, n);
		if(n==null)
			domainModelClientService.getDomainModelDef().unregisterRelationshipDomainObjectChangeListener(this);
	}
	
	private void updateRelationshipList() {
		getView().setRelations(new SimpleListProperty<RelationshipDefModel>(
				domainModelClientService.getDomainModelDef().relationsProperty().filtered(
						r -> (r.getFromPart().getDomainObject() == getModel()) || (r.getToPart().getDomainObject() == getModel()))));
	}

	@Override
	protected IViewController<?> createAndInitializeEditorToShow(NamedModelBase model) {
		IViewController<?> editor = super.createAndInitializeEditorToShow(model);
		if(editor instanceof RelationshipDefEditorController){
			if(model!=null && model instanceof RelationshipDefModel){
				//RelationshipDefModel relation = (RelationshipDefModel) model;
				//if(relation.getFromPart().getDomainObject()==getModel())
					((RelationshipDefEditorController)editor).setFromDisable(true);
				//if(relation.getToPart().getDomainObject()==getModel())
					((RelationshipDefEditorController)editor).setToDisable(true);
			}
		}
		return editor;
	}

	@Override
	public void Change(DomainObjectDefModel oldDomainObject, DomainObjectDefModel newDomainObject) {
		if(oldDomainObject==getModel() || newDomainObject == getModel())
			updateRelationshipList();
	}

	@Override
	public void terminate() {
		domainModelClientService.getDomainModelDef().unregisterRelationshipDomainObjectChangeListener(this);
		super.terminate();
	}
}
