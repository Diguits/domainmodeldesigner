package com.diguits.domainmodeldesigner.services.tasks;

import java.util.List;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.javafx.concurrent.TaskBase;
import com.diguits.templateengine.contract.Diagnostic;
import com.diguits.templateengine.contract.DiagnosticCollector;
import com.diguits.templateengine.contract.ITemplateEngineService;

public class TemplateListOperationTask extends TaskBase<List<Diagnostic>> {

	protected List<TemplateDefModel> templates;
	protected ITemplateEngineService templateEngineService;
	protected String absolutePath;

	public TemplateListOperationTask(List<TemplateDefModel> templates, String absolutePath,
			ITemplateEngineService templateEngineService) {
		super();
		this.templates = templates;
		this.absolutePath = absolutePath;
		this.templateEngineService = templateEngineService;
	}

	@Override
	protected List<Diagnostic> call() throws Exception {
		DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
		int i = 0;
		int size = templates.size();
		updateProgress(0, size);
		while (i < size) {
			TemplateDefModel template = templates.get(i);
			updateMessage(template.getName());
			try {

			innerCall(diagnosticCollector, template);

			} catch (Exception e) {
				e.printStackTrace();
			}

			i++;
			updateProgress(i, size);
		}
		updateProgress(size, size);

		return diagnosticCollector.getDiagnostics();
	}

	protected void innerCall(DiagnosticCollector diagnosticCollector, TemplateDefModel template) {
		templateEngineService.compileCode(absolutePath, template.getProject().getId(), template.getId(),
				template.getName(), template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]), diagnosticCollector);
	}

	public List<TemplateDefModel> getTemplates() {
		return templates;
	}

	public ITemplateEngineService getTemplateEngineService() {
		return templateEngineService;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}
}
