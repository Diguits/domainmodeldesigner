package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.TemplateProjectItemEditorView;
import com.diguits.domainmodeldesigner.template.controllers.TemplateTreeDecorator;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectItemDefModel;
import com.google.inject.Inject;

import javafx.scene.image.ImageView;

public class TemplateProjectItemEditorController<TView extends TemplateProjectItemEditorView<TModel>,TModel extends TemplateProjectItemDefModel> extends NamedModelEditorController<TView, TModel>{

	@Inject
	public TemplateProjectItemEditorController(TView view) {
		super(view);
	}

	@Override
	public ImageView getImage() {
		if(getModel()!=null)
			return TemplateTreeDecorator.getModelImage(getModel());
		return null;
	}
}
