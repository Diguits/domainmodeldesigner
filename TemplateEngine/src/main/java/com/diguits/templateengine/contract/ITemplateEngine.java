package com.diguits.templateengine.contract;

import java.util.List;
import java.util.UUID;

import com.diguits.templateengine.contract.model.TemplateApplyConfig;

public interface ITemplateEngine {
	void generateCode(String directory, UUID projectId, UUID templateId, String templateName,
			 String[] modelClassNames, IDiagnosticCollector diagnosticCollector);

	void compileCode(String directory, UUID projectId, UUID templateId, String templateName,
			String[] modelClassNames, IDiagnosticCollector diagnosticCollector);

	 TemplateApplyOutput applyTemplate(String directory, UUID projectId, UUID templateId, String templateName,
			 String[] modelClassNames, Object[] models, String outputRootDir, TemplateApplyConfig config, List<ParamValue> paramValues, IDiagnosticCollector diagnosticCollector);

	 EvalExpressionResult evalExpression(String expression, String[] modelClassNames, Object[] models, boolean isStringExpresion, IDiagnosticCollector diagnosticCollector, String context);
}
