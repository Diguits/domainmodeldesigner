package com.diguits.domainmodeldesigner.template.controllers;

import java.util.List;
import com.diguits.domainmodeldesigner.services.tasks.TemplateListOperationTask;
import com.diguits.domainmodeldesigner.template.views.TemplateListOperationView;
import com.diguits.javafx.concurrent.controllers.ExecuteTaskController;
import com.diguits.javafx.problem.controllers.ProblemsController;
import com.diguits.templateengine.contract.Diagnostic;
import com.google.inject.Inject;

public class TemplateListOperationController extends ExecuteTaskController<TemplateListOperationView, TemplateListOperationTask> {

	@Inject
	private ProblemsController problemsController;

	@Inject
	public TemplateListOperationController(TemplateListOperationView view) {
		super(view);
	}

	@Override
	protected void onSucceeded() {
		addProblem(getModel());
	}


	private void addProblem(TemplateListOperationTask task) {
		List<Diagnostic> result;
		try {
			result = task.get();
			TemplateTreeController.addToProblems(problemsController, result);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	protected void onCancel() {

	}

	@Override
	protected void onCloseDialog() {

	}

	@Override
	protected String getOperationName(TemplateListOperationTask task) {
		return "";
	}
}
