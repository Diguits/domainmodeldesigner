package com.diguits.domainmodeldesigner.services.tasks;

import java.util.List;
import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService.ApplyResult;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.javafx.concurrent.TaskBase;
import com.diguits.templateengine.contract.DiagnosticCollector;
import com.diguits.templateengine.contract.ITemplateEngineService;
import com.diguits.templateengine.contract.ParamValue;
import com.diguits.templateengine.contract.TemplateApplyOutput;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;

public class TemplateApplyTask extends TaskBase<ApplyResult> {

	private TemplateDefModel template;
	private ITemplateEngineService templateEngineService;
	private List<List<? extends BaseDef>> models;
	private String absolutePath;
	private TemplateApplyConfig config;
	String outputRootDir;

	public TemplateApplyTask(TemplateDefModel template, List<List<? extends BaseDef>> models, String absolutePath,
			String outputRootDir, TemplateApplyConfig config, ITemplateEngineService templateEngineService) {
		super();
		this.template = template;
		this.models = models;
		this.absolutePath = absolutePath;
		this.config = config;
		this.templateEngineService = templateEngineService;
		this.outputRootDir = outputRootDir;
	}

	@Override
	protected ApplyResult call() throws Exception {
		ApplyResult result = new ApplyResult();
		DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
		TemplateApplyOutput output = null;
		int[] indexes = new int[models.size()];
		int totalCount = getTotalModels();
		int position = 0;
		updateProgress(0, totalCount);
		if(!template.getActive())
			return result;
		BaseDef[] currentModels = new BaseDef[indexes.length];
		List<ParamValue> paramValues = TemplateProjectClientService.getParamValues(template, config);
		String[] classNames = template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]);
		while (indexes[0] < models.get(0).size()) {
			fillCurrent(currentModels, indexes);
			updateMessage(getCurrentMessage(currentModels));
			DiagnosticCollector taskDiagnosticCollector = new DiagnosticCollector();
			output = templateEngineService.applyTemplate(absolutePath, template.getProject().getId(), template.getId(),
					template.getName(), classNames, currentModels,
					outputRootDir, config, paramValues, taskDiagnosticCollector);
			incrementIndexes(indexes);
			position++;
			if (taskDiagnosticCollector.getDiagnostics().size() > 0) {
				diagnosticCollector.report(taskDiagnosticCollector.getDiagnostics());
				break;
			}
			updateProgress(position, totalCount);
			result.getApplyOutputs().add(output);
		}
		updateProgress(totalCount, totalCount);
		result.setDiagnostics(diagnosticCollector.getDiagnostics());
		return result;
	}

	private String getCurrentMessage(BaseDef[] currentModels) {
		String result = "";
		result = currentModels[0].getName();
		for (int i = 1; i < currentModels.length; i++) {
			result += ", " + currentModels[0].getName();
		}
		return result;
	}

	private void fillCurrent(BaseDef[] currentModels, int[] indexes) {
		for (int i = 0; i < indexes.length; i++) {
			currentModels[i] = models.get(i).get(indexes[i]);
		}
	}

	private void incrementIndexes(int[] indexes) {
		for (int i = indexes.length - 1; i >= 0; i--) {
			indexes[i]++;
			if (i>0 && indexes[i] >= models.get(i).size())
				indexes[i] = 0;
			else
				return;
		}
	}

	public int getTotalModels() {
		int result = 1;
		for (int i = 0; i < models.size(); i++)
			result *= models.get(i).size();
		return result;
	}

	public TemplateDefModel getTemplate() {
		return template;
	}

	public ITemplateEngineService getTemplateEngineService() {
		return templateEngineService;
	}

	public List<List<? extends BaseDef>> getModels() {
		return models;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public TemplateApplyConfig getConfig() {
		return config;
	}
}
