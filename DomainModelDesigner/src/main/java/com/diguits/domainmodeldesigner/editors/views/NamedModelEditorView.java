package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.editors.views.common.TableInfo;
import com.diguits.domainmodeldesigner.editors.views.common.TableInfoBuilder;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorViewBuilder;
import com.diguits.domainmodeldesigner.editors.views.common.TreeTableInfo;
import com.diguits.domainmodeldesigner.editors.views.common.TreeTableInfoBuilder;
import com.diguits.domainmodeldesigner.editors.views.common.TreeTableModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TreeTableModelEditorViewBuilder;
import com.diguits.javafx.container.views.ContainerView;
import com.diguits.javafx.model.IHierarchialModel;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.views.INodeFactoryHelper;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class NamedModelEditorView<TModel extends NamedModelBase> extends ContainerView<TModel> {

	protected TabPane tabPane;
	protected TextField txfId;
	protected TextField txfName;
	protected TextArea txaDescription;
	protected Tab tabGeneral;
	protected GridPane generalGridPane;

	@Override
	protected Node buildContentView() {
		tabPane = nodeFactory.createTabPane();
		generalGridPane = nodeFactory.createGridPaneForEdit();
		txfId = nodeFactory.createTextFieldInsideGrid(generalGridPane, "%id");
		txfId.setEditable(false);
		txfName = nodeFactory.createTextFieldInsideGrid(generalGridPane, "%name");
		txaDescription = nodeFactory.createTextAreaInsideGrid(generalGridPane, "%description");

		tabGeneral = nodeFactory.createAndAddTab(tabPane, "%general");

		tabGeneral.setContent(nodeFactory.wrapInScrollPane(generalGridPane));
		return tabPane;
	}

	@Override
	protected void bindFieldsToModel() {
		if (getModel() != null) {
			bind(txfId.textProperty(), getModel().idProperty().asString());
			bindBidirectional(txfName.textProperty(), getModel().nameProperty());
			bindBidirectional(txaDescription.textProperty(), getModel().descriptionProperty());
		}
	}

	public <TTableModel> TableModelEditorView<TTableModel> createTableEditorViewInNewTab(TabPane tabPane, String tabCaptionResource, Class<TTableModel> clazz,
			Callback<TableInfoBuilder<TTableModel>, TableInfo<TTableModel>> callback){
		return TableModelEditorViewBuilder.createTableEditorViewInNewTab(tabPane, tabCaptionResource, clazz, callback, nodeFactory, propertyChangeEventListener);
	}

	public <TTableModel> TableModelEditorView<TTableModel> createTableEditorView(Class<TTableModel> clazz,
			Callback<TableInfoBuilder<TTableModel>, TableInfo<TTableModel>> callback) {
		return TableModelEditorViewBuilder.createTableEditorView(clazz, callback, nodeFactory, propertyChangeEventListener);
	}

	public <TTableModel> TableModelEditorView<TTableModel> createTableView(TableInfo<TTableModel> tableInfo,
			INodeFactoryHelper nodeFactory) {
		return TableModelEditorViewBuilder.createTableView(tableInfo, nodeFactory, propertyChangeEventListener);
	}

	public <TTreeTableModel  extends IHierarchialModel<TTreeTableModel>> TreeTableModelEditorView<TTreeTableModel> createTreeTableEditorViewInNewTab(TabPane tabPane, String tabCaptionResource, Class<TTreeTableModel> clazz,
			Callback<TreeTableInfoBuilder<TTreeTableModel>, TreeTableInfo<TTreeTableModel>> callback){
		return TreeTableModelEditorViewBuilder.createTreeTableEditorViewInNewTab(tabPane, tabCaptionResource, clazz, callback, nodeFactory, propertyChangeEventListener);
	}

	public <TTreeTableModel  extends IHierarchialModel<TTreeTableModel>> TreeTableModelEditorView<TTreeTableModel> createTreeTableEditorView(Class<TTreeTableModel> clazz,
			Callback<TreeTableInfoBuilder<TTreeTableModel>, TreeTableInfo<TTreeTableModel>> callback) {
		return TreeTableModelEditorViewBuilder.createTreeTableEditorView(clazz, callback, nodeFactory, propertyChangeEventListener);
	}

	public <TTreeTableModel  extends IHierarchialModel<TTreeTableModel>> TreeTableModelEditorView<TTreeTableModel> createTreeTableView(TreeTableInfo<TTreeTableModel> tableInfo,
			INodeFactoryHelper nodeFactory) {
		return TreeTableModelEditorViewBuilder.createTreeTableView(tableInfo, nodeFactory, propertyChangeEventListener);
	}
}
