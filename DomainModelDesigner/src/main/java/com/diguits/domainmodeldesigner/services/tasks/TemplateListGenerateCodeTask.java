package com.diguits.domainmodeldesigner.services.tasks;

import java.util.List;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.templateengine.contract.DiagnosticCollector;
import com.diguits.templateengine.contract.ITemplateEngineService;

public class TemplateListGenerateCodeTask extends TemplateListOperationTask {

	public TemplateListGenerateCodeTask(List<TemplateDefModel> templates, String absolutePath,
			ITemplateEngineService templateEngineService) {
		super(templates, absolutePath, templateEngineService);
	}

	@Override
	protected void innerCall(DiagnosticCollector diagnosticCollector, TemplateDefModel template) {
		templateEngineService.generateCode(absolutePath, template.getProject().getId(), template.getId(),
				template.getName(), template.getClassNamesToApplyOver().toArray(new String[template.getClassNamesToApplyOver().size()]), diagnosticCollector);
	}
}
