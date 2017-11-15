package com.diguits.domainmodeldesigner.services.tasks;

import java.util.List;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.templateengine.contract.Diagnostic;
import com.diguits.templateengine.contract.DiagnosticCollector;
import com.diguits.templateengine.contract.ITemplateEngineService;
import javafx.concurrent.Task;

public class TemplateCompileTask extends Task<List<Diagnostic>> {

	private TemplateDefModel template;
	private ITemplateEngineService templateEngineService;
	private String absolutePath;

	public TemplateCompileTask(TemplateDefModel template, String absolutePath, ITemplateEngineService templateEngineService) {
		super();
		this.template = template;
		this.absolutePath = absolutePath;
		this.templateEngineService = templateEngineService;
	}

	@Override
	protected List<Diagnostic> call() throws Exception {
		DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
		templateEngineService.compileCode(absolutePath, template.getProject().getId(), template.getId(),
		template.getName(), template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]), diagnosticCollector);
		return diagnosticCollector.getDiagnostics();
	}

	public TemplateDefModel getTemplate() {
		return template;
	}

	public ITemplateEngineService getTemplateEngineService() {
		return templateEngineService;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}
}
