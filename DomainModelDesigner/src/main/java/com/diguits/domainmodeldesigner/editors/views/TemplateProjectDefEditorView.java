package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class TemplateProjectDefEditorView extends TemplateProjectItemEditorView<TemplateProjectDefModel>{

	private CheckBox active;
	TableModelEditorView<TemplateGroupDefModel> groupsView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();

		active = nodeFactory.createCheckBoxInsideGrid(generalGridPane, "%active");

		groupsView = createTableEditorViewInNewTab(tabPane, "%groups" , TemplateGroupDefModel.class, tvf -> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.buildTable());

		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(active.selectedProperty(),model.activeProperty());
			groupsView.setModel(getModel().groupsProperty());
		}
	}

	public TableModelEditorView<TemplateGroupDefModel> getGroupsView() {
		return groupsView;
	}
}
