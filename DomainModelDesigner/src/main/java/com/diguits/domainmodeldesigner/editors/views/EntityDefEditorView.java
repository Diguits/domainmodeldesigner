package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.StringListEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TreeTableModelEditorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.ChoiceBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class EntityDefEditorView extends DomainObjectDefEditorView<EntityDefModel> {

	private ComboBox<EntityDefModel> parentEntity;
	private ComboBox<EntityDefModel> aggregate;
	private CheckBox isAbstract;
	private CheckBox isHierarchial;
	private CheckBox isMasterDetail;
	private CheckBox useService;
	private CheckBox useVisualInterface;

	TreeTableModelEditorView<FilterDefModel> defaultFiltersView;
	TableModelEditorView<ColumnDefModel> defaultColumnsView;
	private StringListEditorView additionalOperationsView;

	@Override
	protected void includeAditionalDomainObjectProperties(GridPane gridPane) {
		parentEntity = nodeFactory.createComboBoxInsideGrid(gridPane, "%parent_entity");
		aggregate = nodeFactory.createComboBoxInsideGrid(gridPane, "%aggregate");
		isAbstract = nodeFactory.createCheckBoxInsideGrid(gridPane, "%is_abstract");
		isHierarchial = nodeFactory.createCheckBoxInsideGrid(gridPane, "%is_hierarchial");
		isMasterDetail = nodeFactory.createCheckBoxInsideGrid(gridPane, "%is_master_detail");
		useService = nodeFactory.createCheckBoxInsideGrid(gridPane, "%use_service");
		useVisualInterface = nodeFactory.createCheckBoxInsideGrid(gridPane, "%use_visual_interface");
	}

	@Override
	protected String getTabCaption() {
		return resources.getString("entity");
	}

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();

		defaultFiltersView = createTreeTableEditorViewInNewTab(tabPane, "%default_columns", FilterDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(new TreeItemPropertyValueFactory<>("name")).useDefaultCellValueFactory().buildColumn()
				.add(FilterLogicalOperator.class, "%logical_operator").width(250).valueFactory(new TreeItemPropertyValueFactory<>("logicalOperator")).cellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(FXCollections.observableArrayList(FilterLogicalOperator.values()))).buildColumn()
				.add(String.class, "%path").width(250).valueFactory(new TreeItemPropertyValueFactory<>("path")).useDefaultCellValueFactory().buildColumn()
				.add(Boolean.class, "%use_operator").width(100).valueFactory(new TreeItemPropertyValueFactory<>("useOperator")).useDefaultCellValueFactory().buildColumn()
				.buildTreeTable());

		defaultColumnsView = createTableEditorViewInNewTab(tabPane, "%default_columns", ColumnDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%path").width(250).valueFactory(cd -> cd.getValue().pathProperty()).buildColumn()
				.add(Integer.class, "%width_weight").width(75).valueFactory(cd -> cd.getValue().widthWeightProperty().asObject()).buildColumn()
				.add(Boolean.class, "%visible").width(75).valueFactory(cd -> cd.getValue().visibleProperty()).buildColumn()
				.add(Boolean.class, "%can_filter").width(75).valueFactory(cd -> cd.getValue().canFilterProperty()).buildColumn()
				.add(Boolean.class, "%can_order").width(75).valueFactory(cd -> cd.getValue().canOrderProperty()).buildColumn()
				.buildTable());

		Tab tab = nodeFactory.createAndAddTab(tabPane, "%additional_operations");
		additionalOperationsView = new StringListEditorView("%operation_to_add", "%operations", nodeFactory);
		AnchorPane anchorPane = additionalOperationsView.getNodeView();
		tab.setContent(anchorPane);
		return contentView;
	}


	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {

			bindBidirectional(parentEntity.valueProperty(), model.parentEntityProperty());
			bindBidirectional(aggregate.valueProperty(), model.aggregateEntityProperty());

			bindBidirectional(isAbstract.selectedProperty(),model.isAbstractProperty());
			bindBidirectional(isHierarchial.selectedProperty(),model.isHierarchialProperty());
			bindBidirectional(isMasterDetail.selectedProperty(),model.isMasterDetailProperty());
			bindBidirectional(useService.selectedProperty(),model.useServiceProperty());
			bindBidirectional(useVisualInterface.selectedProperty(),model.useVisualInterfaceProperty());

			defaultFiltersView.setModel(getModel().defaultFiltersProperty());
			defaultColumnsView.setModel(getModel().defaultColumnsProperty());
			additionalOperationsView.setModel(getModel().additionalOperationsProperty());

		}
	}

	public void setEntities(ObservableList<EntityDefModel> entities) {
		parentEntity
				.setItems(entities);
		aggregate.setItems(entities);

	}

	public TreeTableModelEditorView<FilterDefModel> getDefaultFiltersView() {
		return defaultFiltersView;
	}

	public TableModelEditorView<ColumnDefModel> getDefaultColumnsView() {
		return defaultColumnsView;
	}

	public StringListEditorView getAdditionalOperationsView() {
		return additionalOperationsView;
	}
}
