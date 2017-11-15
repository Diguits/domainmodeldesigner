package com.diguits.templateengine;

import com.diguits.templateengine.antlr.ExpressionCodeBuilderVisitor;
import com.diguits.templateengine.contract.IExpressionCodeBuilder;
import com.diguits.templateengine.contract.Diagnostic.Operation;

public class ExpressionCodeBuilder extends CodeBuilderBase implements IExpressionCodeBuilder {

	private boolean stringExpresion;

	@Override
	protected void createVisitor(String templateClassName, String[] modelClassNames, String[] defaultImports) {
		visitor = new ExpressionCodeBuilderVisitor(PACKAGENAME, defaultImports, templateClassName, "ExpressionBase",
				modelClassNames, stringExpresion);
	}

	@Override
	protected Operation getOperation() {
		return Operation.GENERATING;
	}

	public boolean isStringExpresion() {
		return stringExpresion;
	}

	public void setStringExpresion(boolean stringExpresion) {
		this.stringExpresion = stringExpresion;
		if(visitor instanceof ExpressionCodeBuilderVisitor)
			((ExpressionCodeBuilderVisitor)visitor).setStringExpresion(stringExpresion);
	}


}
