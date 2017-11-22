package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldRelationshipDataDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class RelationOverrideDefEditorView extends BaseDefEditorView<RelationOverrideDefModel> {
	private ComboBox<EntityDefModel> forEntity;
	private ComboBox<EntityDefModel> relationshipEntity;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%enum");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		forEntity = nodeFactory.createComboBoxInsideGrid(gridPane, "%for_entity");
		relationshipEntity = nodeFactory.createComboBoxInsideGrid(gridPane, "%relationship_entity");
		tab.setContent(nodeFactory.wrapInScrollPane(gridPane));
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
		}
	}

	public void setForEntities(ObservableList<EntityDefModel> entities) {
		FieldRelationshipDataDefModel realtionPart = (FieldRelationshipDataDefModel) getModel().getOwner();
		if (realtionPart != null) {
			forEntity.setItems(entities);
			forEntity.getSelectionModel().select(getModel().getForEntity());
			getModel().forEntityProperty().bind(forEntity.getSelectionModel().selectedItemProperty());
		}
	}

	public void setOverrideEntities(ObservableList<EntityDefModel> entities) {
		FieldRelationshipDataDefModel realtionData = (FieldRelationshipDataDefModel) getModel().getOwner();
		if (realtionData != null) {
			if (realtionData.getRelationshipPart() != null) {
				relationshipEntity.setItems(FXCollections.observableArrayList(entities));
				relationshipEntity.getSelectionModel().select(getModel().getRelationshipEntity());
				getModel().relationshipEntityProperty().bind(relationshipEntity.getSelectionModel().selectedItemProperty());
			}
		}

	}
}
