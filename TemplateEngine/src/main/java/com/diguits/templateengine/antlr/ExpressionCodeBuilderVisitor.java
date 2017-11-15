package com.diguits.templateengine.antlr;

import com.diguits.templateengine.antlr.TemplateParser.ConstantExpressionContext;
import com.diguits.templateengine.antlr.TemplateParser.EvalExpressionContext;

public class ExpressionCodeBuilderVisitor extends CodeBuilderVisitorBase {

	public ExpressionCodeBuilderVisitor(String packageName, String[] defaultImports, String className,
			String parentClassName, String[] modelClassNames, boolean stringExpresion) {
		super(packageName, defaultImports, className, parentClassName, modelClassNames);
		this.stringExpresion = stringExpresion;
	}

	boolean first = true;
	boolean stringExpresion;

	@Override
	protected void buildMethods() {
		super.buildMethods();
		first = true;
		append("\t@Override\r\n");
		append("\tprotected Object innerEvaluate(Object[] models){\r\n ");
		appendModelVariables();
		append(" return ");
	}

	protected void buildClassFooter() {
		append(";\r\n");
		append("\t}\r\n");
		append("}\r\n");
	}

	@Override
	public Void visitConstantExpression(ConstantExpressionContext ctx) {
		if (!first && stringExpresion)
			append("+");
		if (stringExpresion)
			append("\"");
		append(getText(ctx));
		if (stringExpresion)
			append("\"");
		first = false;
		return super.visitConstantExpression(ctx);
	}

	@Override
	public Void visitEvalExpression(EvalExpressionContext ctx) {
		if (!first && stringExpresion)
			append("+");
		append(getText(ctx));
		first = false;
		return super.visitEvalExpression(ctx);
	}

	public boolean isStringExpresion() {
		return stringExpresion;
	}

	public void setStringExpresion(boolean stringExpresion) {
		this.stringExpresion = stringExpresion;
	}

}
