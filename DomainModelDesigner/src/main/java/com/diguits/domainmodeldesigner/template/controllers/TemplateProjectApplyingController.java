package com.diguits.domainmodeldesigner.template.controllers;

import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.services.tasks.TemplateApplyTask;
import com.diguits.domainmodeldesigner.template.views.TemplateApplyingView;
import com.diguits.domainmodeldesigner.template.views.TemplateProjectApplyingView;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.application.InjectorHelper;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.google.inject.Inject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class TemplateProjectApplyingController extends ViewControllerBase<TemplateProjectApplyingView> {

	@Inject
	TemplateProjectClientService templateClientService;

	private int index;

	private IntegerProperty taskExecutedCount;

	private TemplateProjectApplyConfigModel applyConfig;

	@Inject
	public TemplateProjectApplyingController(TemplateProjectApplyingView view) {
		super(view);
	}

	public void startApplying(TemplateProjectApplyConfigModel applyConfig) {
		if (applyConfig != null) {
				this.applyConfig = applyConfig;
				getView().initialize(applyConfig.getTemplatesConfig().size(), taskExecutedCountProperty());
				index = 0;
				setTaskExecutedCount(0);
				getView().updateProgress(0);
				apply();
				DialogHelper.showOKCancelDialog(this, new SimpleStringProperty(resources.getString("applying_template_project")+" »"+applyConfig.getName()+"«"));
			}
	}

	private void apply() {
		int i = index;
		while (i < applyConfig.getTemplatesConfig().size()) {
			TemplateApplyConfigModel templateApplyConfig = applyConfig.getTemplatesConfig().get(i);
			if (i > index && templateApplyConfig.getWaitForBefore()) {
				index = i;
				return;
			}
			TemplateApplyTask task = templateClientService.getApplyTemplateTask(templateApplyConfig);
			TemplateApplyingController templateApplyingController;
			templateApplyingController = InjectorHelper.getInstance().getInjector()
					.getInstance(TemplateApplyingController.class);
			templateApplyingController.start(task);
			EventHandler<WorkerStateEvent> onSucceeded = task.getOnSucceeded();
			task.setOnSucceeded(e -> {
				notifyTaskEnd(e);
				if (onSucceeded != null)
					onSucceeded.handle(e);
			});
			EventHandler<WorkerStateEvent> onFailed = task.getOnFailed();
			task.setOnFailed(e -> {
				notifyTaskEnd(e);
				if (onFailed != null)
					onFailed.handle(e);
			});
			EventHandler<WorkerStateEvent> onCancelled = task.getOnCancelled();
			task.setOnCancelled(e -> {
				notifyTaskEnd(e);
				if (onCancelled != null)
					onCancelled.handle(e);
			});
			TemplateApplyingView templateApplyingView = templateApplyingController.getView();
			if(templateApplyingView!=null){
				getView().addTemplateApplying(templateApplyingView.getNodeView());
			}
			i++;
		}
	}

	private void notifyTaskEnd(WorkerStateEvent e) {
		setTaskExecutedCount(getTaskExecutedCount() + 1);
		getView().updateProgress(getTaskExecutedCount() / applyConfig.getTemplatesConfig().size());
		if (index == getTaskExecutedCount())
			apply();

	}

	public int getTaskExecutedCount() {
		if (taskExecutedCount != null)
			return taskExecutedCount.get();
		return 0;
	}

	public void setTaskExecutedCount(int taskExecutedCount) {
		if (this.taskExecutedCount != null || taskExecutedCount != 0) {
			taskExecutedCountProperty().set(taskExecutedCount);
		}
	}

	public IntegerProperty taskExecutedCountProperty() {
		if (taskExecutedCount == null) {
			taskExecutedCount = new SimpleIntegerProperty(this, "taskExecutedCount", 0);
		}
		return taskExecutedCount;
	}
}
