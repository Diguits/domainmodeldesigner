package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.RelationshipType;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RelationshipDefEditorView extends BaseDefEditorView<RelationshipDefModel> {

	private ComboBox<DomainObjectDefModel> fromDomainObject;
	private ComboBox<DomainObjectDefModel> toDomainObject;
	private ComboBox<FieldDefModel> fromField;
	private ComboBox<FieldDefModel> toField;
	private ChoiceBox<RelationshipType> fromRelationType;
	private ChoiceBox<RelationshipType> toRelationType;
	private CheckBox cascadeDelete;
	private TextField manyToManyEntiyName;
	private CheckBox fromIsPrincipal;
	private BooleanProperty fromDisable;
	private BooleanProperty toDisable;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%relation");
		GridPane gridPane = nodeFactory.createGridPaneForEditFourColumns();
		fromDomainObject = nodeFactory.createComboBoxInsideGrid(gridPane, 1, 0, "%from_domain_object");
		fromDomainObject.disableProperty().bind(fromDisableProperty());

		toDomainObject = nodeFactory.createComboBoxInsideGrid(gridPane, 3, 0, "%to_domain_object");
		toDomainObject.disableProperty().bind(toDisableProperty());

		fromField = nodeFactory.createComboBoxInsideGrid(gridPane, 1, 1, "%from_field");
		fromField.disableProperty().bind(fromDisableProperty());

		toField = nodeFactory.createComboBoxInsideGrid(gridPane, 3, 1, "%to_field");
		toField.disableProperty().bind(toDisableProperty());

		fromRelationType = nodeFactory.createChoiceBoxInsideGrid(gridPane, 1, 2, "%from_relation_type");
		fromRelationType.setItems(FXCollections.observableArrayList(RelationshipType.values()));
		fromRelationType.disableProperty().bind(fromDisableProperty());

		toRelationType = nodeFactory.createChoiceBoxInsideGrid(gridPane, 3, 2, "%to_relation_type");
		toRelationType.setItems(FXCollections.observableArrayList(RelationshipType.values()));
		toRelationType.disableProperty().bind(toDisableProperty());

		manyToManyEntiyName = nodeFactory.createTextFieldInsideGrid(gridPane, 1, 3, "%many_to_many_entiy_name");
		fromIsPrincipal = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, 4, "%from_is_principal");
		cascadeDelete = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, 5, "%cascade_delete");
		tab.setContent(nodeFactory.addAndFitToAnchorPane(gridPane));
		return contentView;
	}

	@Override

	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(cascadeDelete.selectedProperty(), model.cascadeDeleteProperty());
			bindBidirectional(fromIsPrincipal.selectedProperty(), model.fromSideIsPrincipalProperty());

			bind(fromIsPrincipal.disableProperty(),
					model.getFromPart().relationTypeProperty().isNotEqualTo(RelationshipType.One)
							.or(model.getToPart().relationTypeProperty().isNotEqualTo(RelationshipType.One)));

			bindBidirectional(manyToManyEntiyName.textProperty(), model.manyToManyEntityNameProperty());
			bind(manyToManyEntiyName.disableProperty(),
					model.getFromPart().relationTypeProperty().isNotEqualTo(RelationshipType.Many)
							.or(model.getToPart().relationTypeProperty().isNotEqualTo(RelationshipType.Many)));

			bind(fromField.itemsProperty(), Bindings.select(model.getFromPart().domainObjectProperty(), "Fields"));
			bindBidirectional(fromField.valueProperty(), model.getFromPart().fieldProperty());

			bind(toField.itemsProperty(), Bindings.select(model.getToPart().domainObjectProperty(), "Fields"));
			bindBidirectional(toField.valueProperty(), model.getToPart().fieldProperty());

			bindBidirectional(fromRelationType.valueProperty(), model.getFromPart().relationTypeProperty());
			bind(fromRelationType.disableProperty(),model.getFromPart().fieldProperty().isNotNull());

			bindBidirectional(toRelationType.valueProperty(), model.getToPart().relationTypeProperty());
			bind(toRelationType.disableProperty(), model.getToPart().fieldProperty().isNotNull());

		}
	}

	public void setDomainObjects(ObservableList<DomainObjectDefModel> entities) {
		fromDomainObject.setItems(entities);
		bindBidirectional(fromDomainObject.valueProperty(), model.getFromPart().domainObjectProperty());

		toDomainObject.setItems(entities);
		bindBidirectional(toDomainObject.valueProperty(), model.getToPart().domainObjectProperty());

	}

	public boolean getFromDisable() {
		if (fromDisable != null)
			return fromDisable.get();
		return false;
	}

	public void setFromDisable(boolean fromDisable) {
		if (this.fromDisable != null || fromDisable != false) {
			fromDisableProperty().set(fromDisable);
		}
	}

	public BooleanProperty fromDisableProperty() {
		if (fromDisable == null) {
			fromDisable = new SimpleBooleanProperty(this, "fromDisable", false);
		}
		return fromDisable;
	}

	public boolean getToDisable() {
		if (toDisable != null)
			return toDisable.get();
		return false;
	}

	public void setToDisable(boolean toDisable) {
		if (this.toDisable != null || toDisable != false) {
			toDisableProperty().set(toDisable);
		}
	}

	public BooleanProperty toDisableProperty() {
		if (toDisable == null) {
			toDisable = new SimpleBooleanProperty(this, "toDisable", false);
		}
		return toDisable;
	}

}
