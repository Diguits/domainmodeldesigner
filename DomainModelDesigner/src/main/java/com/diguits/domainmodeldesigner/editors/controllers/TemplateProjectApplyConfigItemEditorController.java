package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.TemplateProjectApplyConfigItemEditorView;
import com.diguits.domainmodeldesigner.templateapplyconfig.controllers.TemplateApplyConfigTreeDecorator;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigItemModel;
import com.google.inject.Inject;

import javafx.scene.image.ImageView;

public class TemplateProjectApplyConfigItemEditorController<TView extends TemplateProjectApplyConfigItemEditorView<TModel>,TModel extends TemplateProjectApplyConfigItemModel> extends NamedModelEditorController<TView, TModel>{

	@Inject
	public TemplateProjectApplyConfigItemEditorController(TView view) {
		super(view);
	}

	@Override
	public ImageView getImage() {
		if(getModel()!=null)
			return TemplateApplyConfigTreeDecorator.getImageForModel(getModel().getClass());
		return null;
	}
}
