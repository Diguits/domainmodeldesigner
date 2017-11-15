package com.diguits.templateengine.services;

import java.util.List;
import java.util.UUID;

import com.diguits.templateengine.contract.IDiagnosticCollector;
import com.diguits.templateengine.contract.ITemplateEngine;
import com.diguits.templateengine.contract.ITemplateEngineService;
import com.diguits.templateengine.contract.ParamValue;
import com.diguits.templateengine.contract.TemplateApplyOutput;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.google.inject.Inject;

public class TemplateEngineService implements ITemplateEngineService{

    @Inject
    ITemplateEngine templateEngine;

    @Override
    public void generateCode(String directory, UUID projectId, UUID templateId, String templateName, String[] modelClassNames, IDiagnosticCollector diagnosticCollector) {
        templateEngine.generateCode(directory, projectId, templateId, templateName, modelClassNames, diagnosticCollector);
    }

	@Override
	public void compileCode(String directory, UUID projectId, UUID templateId, String templateName, String[] modelClassNames,
			IDiagnosticCollector diagnosticCollector) {
		templateEngine.compileCode(directory, projectId, templateId, templateName, modelClassNames, diagnosticCollector);

	}

	@Override
	public TemplateApplyOutput applyTemplate(String directory, UUID projectId, UUID templateId, String templateName, String[] modelClassNames, Object[] models, String outputRootDir, TemplateApplyConfig config,
			List<ParamValue> paramValues, IDiagnosticCollector diagnosticCollector) {
		return templateEngine.applyTemplate(directory, projectId, templateId, templateName, modelClassNames, models, outputRootDir, config, paramValues, diagnosticCollector);
	}

}
