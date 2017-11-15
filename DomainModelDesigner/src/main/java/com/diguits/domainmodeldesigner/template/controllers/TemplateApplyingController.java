package com.diguits.domainmodeldesigner.template.controllers;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService.ApplyResult;
import com.diguits.domainmodeldesigner.services.tasks.TemplateApplyTask;
import com.diguits.domainmodeldesigner.template.views.TemplateApplyingView;
import com.diguits.javafx.concurrent.controllers.ExecuteTaskController;
import com.diguits.javafx.problem.controllers.ProblemsController;
import com.google.inject.Inject;

import javafx.beans.property.StringProperty;

public class TemplateApplyingController extends ExecuteTaskController<TemplateApplyingView, TemplateApplyTask> {

	@InjectLogger
	Logger logger;

	@Inject
	private ProblemsController problemsController;

	@Inject
	TemplateApplyResultController resultController;

	StringProperty templateName;

	@Inject
	public TemplateApplyingController(TemplateApplyingView view) {
		super(view);
	}

	@Override
	protected void onSucceeded() {
		addProblem(getModel());
	}

	@Override
	protected void onCloseDialog() {
		TemplateApplyTask task = getModel();
		if (task.isDone() && task.getConfig().getInMemory())
			try {
				resultController.showResult(task.get());
			} catch (InterruptedException e) {
				logger.error("Error applying template »{}({})«, the process was interrupted by the user", templateName,
						task.getTemplate().getId());
			} catch (CancellationException e) {
				logger.error("Error applying template »{}({})«, the process was canceled by the user", templateName,
						task.getTemplate().getId());
			} catch (ExecutionException e) {
				logger.error("Error applying template »" + templateName + "(" + task.getTemplate().getId()
						+ ")«, execution error", e);
			}
	}

	protected String getOperationName(TemplateApplyTask task) {
		return task.getTemplate().getName();
	}

	private void addProblem(TemplateApplyTask task) {
		ApplyResult result;
		try {
			result = task.get();
			TemplateTreeController.addToProblems(problemsController, result.getDiagnostics());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	protected void onCancel() {

	}

}
