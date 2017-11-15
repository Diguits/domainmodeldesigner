package com.diguits.templateengine.contract;

import java.util.List;
import java.util.UUID;

import com.diguits.templateengine.contract.model.TemplateApplyConfig;

public interface ITemplateEngineService {
	public void generateCode(String directory, UUID projectId, UUID templateId, String templateName, String[] modelClassNames, IDiagnosticCollector diagnosticCollector);
	public void compileCode(String directory, UUID projectId, UUID templateId, String templateName, String[] modelClassNames, IDiagnosticCollector diagnosticCollector);
	public TemplateApplyOutput applyTemplate(String directory, UUID projectId, UUID templateId, String templateName, String[] modelClassNames,
			Object[] models, String outputRootDir, TemplateApplyConfig config, List<ParamValue> paramValues, IDiagnosticCollector diagnosticCollector);
}
