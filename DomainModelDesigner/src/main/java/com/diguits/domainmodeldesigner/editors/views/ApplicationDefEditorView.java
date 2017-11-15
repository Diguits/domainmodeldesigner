package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.SwapModelEditorView;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class ApplicationDefEditorView extends BaseDefEditorView<ApplicationDefModel>{

	private SwapModelEditorView<BoundedContextDefModel> boundedContextsSwapView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%boundedContexts");
		boundedContextsSwapView = new SwapModelEditorView<BoundedContextDefModel>("%boundedContext_to_add", "%boundedContexts", nodeFactory);
		AnchorPane anchorPane = boundedContextsSwapView.getNodeView();
		tab.setContent(anchorPane);
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		boundedContextsSwapView.setModel(getModel().boundedContextsProperty());
	}

	public SwapModelEditorView<BoundedContextDefModel> getBoundedContextsSwapView() {
		return boundedContextsSwapView;
	}
}
