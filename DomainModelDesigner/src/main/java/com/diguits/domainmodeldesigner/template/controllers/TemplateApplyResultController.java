package com.diguits.domainmodeldesigner.template.controllers;

import java.util.List;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService.ApplyResult;
import com.diguits.domainmodeldesigner.template.views.TemplateApplyResultView;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.diguits.templateengine.contract.TemplateApplyOutput;
import com.google.inject.Inject;

import javafx.beans.property.SimpleStringProperty;

public class TemplateApplyResultController extends ViewControllerBase<TemplateApplyResultView> {

	@Inject
	public TemplateApplyResultController(TemplateApplyResultView view) {
		super(view);
	}

	public void showResult(ApplyResult result) {
		getView().clearTabs();
		List<TemplateApplyOutput> applyOutputs = result.getApplyOutputs();
		if ((result.getDiagnostics() == null || result.getDiagnostics().size() == 0) && applyOutputs != null
				&& applyOutputs.size() > 0) {
			for (TemplateApplyOutput applyOutput : applyOutputs) {
				getView().addTab(applyOutput.getModelsName() == null || applyOutput.getModelsName().length == 0
						? applyOutput.getTemplateName() : String.join("-", applyOutput.getModelsName()), applyOutput.getOutput());
			}
			getView().selectFirstTab();
			DialogHelper.showOKCancelDialog(this, new SimpleStringProperty(resources.getString("apply_result")));
		}

	}
}
