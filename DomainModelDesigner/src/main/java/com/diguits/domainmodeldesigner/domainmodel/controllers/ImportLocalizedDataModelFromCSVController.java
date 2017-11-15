package com.diguits.domainmodeldesigner.domainmodel.controllers;

import com.diguits.domainmodeldesigner.domainmodel.views.ImportLocalizedDataModelFromCSVView;
import com.diguits.domainmodeldesigner.services.tasks.ImportLocalizedDataModelFromCSVTask;
import com.diguits.javafx.concurrent.controllers.ExecuteTaskController;
import com.google.inject.Inject;

public class ImportLocalizedDataModelFromCSVController extends ExecuteTaskController<ImportLocalizedDataModelFromCSVView, ImportLocalizedDataModelFromCSVTask>{
	@Inject
	public ImportLocalizedDataModelFromCSVController(ImportLocalizedDataModelFromCSVView view) {
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
	protected String getOperationName(ImportLocalizedDataModelFromCSVTask task) {
		return resources.getString("import_to_csv");
	}

	@Override
	protected String getBundleName() {
		return "ImportLocalizedDataCSVBundle";
	}
}
