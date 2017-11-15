package com.diguits.javafx.concurrent.controllers;

import java.util.Optional;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.javafx.concurrent.TaskBase;
import com.diguits.javafx.concurrent.TaskExecutor;
import com.diguits.javafx.concurrent.views.ExecuteTaskView;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ModelViewControllerBase;
import com.google.inject.Inject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Labeled;

public abstract class ExecuteTaskController<TView extends ExecuteTaskView<TModel>, TModel extends TaskBase<?>>
		extends ModelViewControllerBase<TView, TModel> {

	@InjectLogger
	Logger logger;

	@Inject
	TaskExecutor taskExecutor;

	private StringProperty dialogTitle;
	private StringProperty operationName;

	@Inject
	public ExecuteTaskController(TView view) {
		super(view);
	}

	public void startModal(TModel task, String dialogTitle) {
		setDialogTitle(dialogTitle);
		startModal(task);
	}

	public void startModal(TModel task) {
		innerStart(task);
		final Dialog<?> dialog = DialogHelper.createDialog(this, dialogTitle, 400, 0);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		final Node cancelButtom = dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		task.setOnSucceeded(e -> {
			if (cancelButtom instanceof Labeled)
				((Labeled) cancelButtom).setText(resources.getString("close"));
			onSucceeded();
		});
		task.setOnFailed(e -> {
			if (cancelButtom instanceof Labeled)
				((Labeled) cancelButtom).setText(resources.getString("close"));
		});
		Optional<?> dialogResult = dialog.showAndWait();
		if (dialogResult.isPresent() && dialogResult.get() == ButtonType.CANCEL && task.isRunning()) {
			task.cancel();
			onCancel();
		} else {
			onCloseDialog();
		}
	}

	protected abstract void onCancel();

	protected abstract void onSucceeded();

	protected abstract void onCloseDialog();

	protected abstract String getOperationName(TModel task);

	public void start(TModel task) {
		innerStart(task);
		task.setOnSucceeded(e -> {
			onSucceeded();
		});
	}

	private void innerStart(TModel task) {
		this.setModel(task);
		this.setOperationName(getOperationName(task));
		getView().setOperationName(operationNameProperty());
		taskExecutor.executeTask(task);
	}

	public String getOperationName() {
		if (operationName != null)
			return operationName.get();
		return "";
	}

	public void setOperationName(String operationName) {
		if (this.operationName != null || operationName == null || !operationName.equals("")) {
			operationNameProperty().set(operationName);
		}
	}

	public StringProperty operationNameProperty() {
		if (operationName == null) {
			operationName = new SimpleStringProperty(this, "operationName", "");
		}
		return operationName;
	}

	public String getDialogTitle() {
		if (dialogTitle != null)
			return dialogTitle.get();
		return "";
	}

	public void setDialogTitle(String dialogTitle) {
		if (this.dialogTitle != null || dialogTitle == null || !dialogTitle.equals("")) {
			dialogTitleProperty().set(dialogTitle);
		}
	}

	public StringProperty dialogTitleProperty() {
		if (dialogTitle == null) {
			dialogTitle = new SimpleStringProperty(this, "dialogTitle", "");
		}
		return dialogTitle;
	}

}
