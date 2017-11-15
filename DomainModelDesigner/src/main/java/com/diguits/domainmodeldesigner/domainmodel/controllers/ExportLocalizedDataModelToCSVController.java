package com.diguits.domainmodeldesigner.domainmodel.controllers;

import com.diguits.domainmodeldesigner.domainmodel.views.ExportLocalizedDataModelToCSVView;
import com.diguits.domainmodeldesigner.services.tasks.ExportLocalizedDataModelToCSVTask;
import com.diguits.javafx.concurrent.controllers.ExecuteTaskController;
import com.google.inject.Inject;

public class ExportLocalizedDataModelToCSVController extends ExecuteTaskController<ExportLocalizedDataModelToCSVView, ExportLocalizedDataModelToCSVTask>{
	@Inject
	public ExportLocalizedDataModelToCSVController(ExportLocalizedDataModelToCSVView view) {
		super(view);
	}

	@Override
	protected void onCancel() {

	}

	@Override
	protected void onSucceeded() {

	}

	@Override
	protected void onCloseDialog() {

	}

	@Override
	protected String getOperationName(ExportLocalizedDataModelToCSVTask task) {
		return resources.getString("export_to_csv");
	}

	@Override
	protected String getBundleName() {
		return "ImportLocalizedDataCSVBundle";
	}
}
