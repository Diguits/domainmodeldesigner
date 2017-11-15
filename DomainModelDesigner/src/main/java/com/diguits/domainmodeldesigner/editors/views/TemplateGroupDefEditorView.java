package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class TemplateGroupDefEditorView extends TemplateProjectItemEditorView<TemplateGroupDefModel>{

	private CheckBox active;

	TableModelEditorView<TemplateDefModel> templatesView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		active = nodeFactory.createCheckBoxInsideGrid(generalGridPane, "%active");

		templatesView = createTableEditorViewInNewTab(tabPane, "%templates" , TemplateDefModel.class, tvf -> tvf
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
			templatesView.setModel(getModel().templatesProperty());
		}
	}

	public TableModelEditorView<TemplateDefModel> getTemplatesView() {
		return templatesView;
	}
}
