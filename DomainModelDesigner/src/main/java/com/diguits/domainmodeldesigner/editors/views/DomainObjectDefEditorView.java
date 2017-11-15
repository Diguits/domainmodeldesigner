package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.SwapModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public abstract class DomainObjectDefEditorView<TModel extends DomainObjectDefModel> extends BaseDefEditorView<TModel> {

	private TextField tableName;
	private ComboBox<BoundedContextDefModel> boundedContext;
	private ComboBox<ModuleDefModel> module;

	private TableModelEditorView<FieldDefModel> fieldsView;
	private TableModelEditorView<FieldGroupDefModel> fieldGroupsView;
	private TableModelEditorView<IndexDefModel> indexesView;
	private SwapModelEditorView<FieldDefModel> primaryKeySwapView;
	private TableModelEditorView<RelationshipDefModel> relationsView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, getTabCaption());
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		tableName = nodeFactory.createTextFieldInsideGrid(gridPane, "%table_name");
		boundedContext = nodeFactory.createComboBoxInsideGrid(gridPane, "%boundedContext");
		module = nodeFactory.createComboBoxInsideGrid(gridPane, "%module");
		includeAditionalDomainObjectProperties(gridPane);
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(gridPane);
		nodeFactory.fitToAnchorPane(gridPane);
		tab.setContent(anchorPane);

		fieldsView = createTableEditorViewInNewTab(tabPane, "%fields", FieldDefModel.class, tvf-> tvf
			.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
			.add(DataType.class, "%data_type").width(75).valueFactory(cd -> cd.getValue().dataTypeProperty()).cellFactory(ChoiceBoxTableCell.forTableColumn(DataType.values())).buildColumn()
			.add(FieldGroupDefModel.class, "%field_group").width(90).valueFactory(cd -> cd.getValue().fieldGroupProperty()).buildColumn()//cellFactory(ComboBoxTableCell.forTableColumn(DomainObjectDefEditorView.this.getModel().getFieldGroups())).buildColumn()
			.add(FieldSubgroupDefModel.class, "%field_sub_group").width(75).valueFactory(cd -> cd.getValue().fieldSubgroupProperty()).buildColumn()
			.add(Integer.class, "%precision").width(50).valueFactory(cd -> cd.getValue().precisionProperty().asObject()).useDefaultCellValueFactory().buildColumn()
			.add(Integer.class, "%scale").width(50).valueFactory(cd -> cd.getValue().scaleProperty().asObject()).useDefaultCellValueFactory().buildColumn()
			.add(Integer.class, "%max_length").width(50).valueFactory(cd -> cd.getValue().maxLengthProperty().asObject()).useDefaultCellValueFactory().buildColumn()
			.add(Integer.class, "%min_length").width(50).valueFactory(cd -> cd.getValue().minLengthProperty().asObject()).useDefaultCellValueFactory().buildColumn()
			.add(Boolean.class, "%allow_null").width(50).valueFactory(cd -> cd.getValue().allowNullProperty().asObject()).useDefaultCellValueFactory().buildColumn()
			.add(EnumDefModel.class, "%enum").width(80).valueFactory(cd -> cd.getValue().enumDefProperty()).buildColumn()
			.add(DomainObjectDefModel.class, "%related_domain_object").width(80).valueFactory(cd -> cd.getValue().getRelationshipData().relatedDomainObjectProperty()).buildColumn()
			.add(FieldDefModel.class, "related_field").width(80).valueFactory(cd -> cd.getValue().getRelationshipData().relatedFieldProperty()).buildColumn()
			.add(ValueObjectDefModel.class, "%value_object").width(80).valueFactory(cd -> cd.getValue().valueObjectProperty()).buildColumn()
			.buildTable());

		fieldGroupsView = createTableEditorViewInNewTab(tabPane, "%field_groups", FieldGroupDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%subgroups").width(250).valueFactory(cd -> cd.getValue().subgroupsProperty().asString()).buildColumn()
				.buildTable());

		indexesView = createTableEditorViewInNewTab(tabPane, "%indexes", IndexDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(Boolean.class, "%is_unique").width(50).valueFactory(cd -> cd.getValue().uniqueProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%fields").width(300).valueFactory(cd -> cd.getValue().fieldsProperty().asString()).buildColumn()
				.buildTable());

		tab = nodeFactory.createAndAddTab(tabPane, "%primary_key");
		primaryKeySwapView = new SwapModelEditorView<FieldDefModel>("%field_to_add", "%fields", nodeFactory);
		anchorPane = primaryKeySwapView.getNodeView();
		tab.setContent(anchorPane);

		relationsView = createTableEditorViewInNewTab(tabPane, "%relations", RelationshipDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(DomainObjectDefModel.class, "%from_domain_object").width(150).valueFactory(cd -> Bindings.select(cd.getValue().fromPartProperty(), "domainObject")).buildColumn()
				.add(FieldDefModel.class, "%from_field").width(90).valueFactory(cd -> Bindings.select(cd.getValue().fromPartProperty(), "field")).buildColumn()
				.add(DomainObjectDefModel.class, "%to_domain_object").width(150).valueFactory(cd -> Bindings.select(cd.getValue().toPartProperty(), "domainObject")).buildColumn()
				.add(FieldDefModel.class, "%to_field").width(90).valueFactory(cd -> Bindings.select(cd.getValue().toPartProperty(), "field")).buildColumn()
				.buildTable());

		relationsView.setCanAdd(false);
		relationsView.setCanDelete(false);
		relationsView.setCanOrder(false);
		return contentView;
	}

	protected abstract void includeAditionalDomainObjectProperties(GridPane gridPane);

	protected abstract String getTabCaption();

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(tableName.textProperty(), model.tableNameProperty());
			if (model.getModule() != null && model.getModule().getOwner() != null)
				boundedContext.getSelectionModel().select((BoundedContextDefModel) model.getModule().getOwner());

			bind(module.itemsProperty()
					,new When(boundedContext.selectionModelProperty().isNotNull()
							.and(boundedContext.selectionModelProperty().getValue().selectedItemProperty().isNotNull()))
									.then((ObservableList<ModuleDefModel>) boundedContext.getSelectionModel()
											.getSelectedItem().getModules().sorted())
									.otherwise(FXCollections.observableArrayList()));

			module.getSelectionModel().select(model.getModule());

			bind(model.moduleProperty(), module.getSelectionModel().selectedItemProperty());
			indexesView.setModel(getModel().indexesProperty());
			primaryKeySwapView.setModel(getModel().primaryKeyProperty());
			fieldsView.setModel(getModel().fieldsProperty());
			fieldGroupsView.setModel(getModel().fieldGroupsProperty());

		}
	}

	public void setRelations(Property<ObservableList<RelationshipDefModel>> relations) {
		relationsView.setModel(relations);
	}

	public void setBoundedContexts(ObservableList<BoundedContextDefModel> boundedContexts) {
		boundedContext.setItems(boundedContexts);
	}

	public TableModelEditorView<FieldDefModel> getFieldsView() {
		return fieldsView;
	}

	public TableModelEditorView<FieldGroupDefModel> getFieldGroupsView() {
		return fieldGroupsView;
	}

	public TableModelEditorView<IndexDefModel> getIndexesView() {
		return indexesView;
	}

	public SwapModelEditorView<FieldDefModel> getPrimaryKeySwapView() {
		return primaryKeySwapView;
	}

	public TableModelEditorView<RelationshipDefModel> getRelationsView() {
		return relationsView;
	}
}
