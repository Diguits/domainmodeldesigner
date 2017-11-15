package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;

import javafx.scene.Node;

public class FieldGroupDefEditorView extends BaseDefEditorView<FieldGroupDefModel>{

	TableModelEditorView<FieldSubgroupDefModel> subgroupsView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		subgroupsView = createTableEditorViewInNewTab(tabPane, "%subgroups" , FieldSubgroupDefModel.class, tvf -> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(Boolean.class, "%has_visual_representation").width(100).valueFactory(cd -> cd.getValue().hasVisualRepresentationProperty()).useDefaultCellValueFactory().buildColumn()
				.buildTable());
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		subgroupsView.setModel(getModel().subgroupsProperty());
	}

	public TableModelEditorView<FieldSubgroupDefModel> getSubgroupsView() {
		return subgroupsView;
	}


}
