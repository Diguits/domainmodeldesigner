package com.diguits.templateengine;

import com.diguits.templateengine.antlr.TemplateCodeBuilderVisitor;
import com.diguits.templateengine.contract.Diagnostic.Operation;
import com.diguits.templateengine.contract.ITemplateCodeBuilder;

public class TemplateCodeBuilder extends CodeBuilderBase implements ITemplateCodeBuilder {

	@Override
	protected void createVisitor(String templateClassName, String[] modelClassNames, String[] defaultImports) {
		visitor = new TemplateCodeBuilderVisitor(PACKAGENAME, defaultImports, templateClassName, "TemplateBase",
				modelClassNames);
	}

	@Override
	protected Operation getOperation() {
		return Operation.GENERATING;
	}

	@Override
	public String getFullClassName(String templateName) {
		String className = getClassName(templateName);
		return PACKAGENAME + "." + className;
	}
}
