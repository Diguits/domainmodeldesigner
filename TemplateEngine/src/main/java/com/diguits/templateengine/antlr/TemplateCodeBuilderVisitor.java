package com.diguits.templateengine.antlr;

import com.diguits.templateengine.antlr.TemplateParser.CodeExpressionContext;
import com.diguits.templateengine.antlr.TemplateParser.CodeStatementContext;
import com.diguits.templateengine.antlr.TemplateParser.ConstantExpressionContext;
import com.diguits.templateengine.antlr.TemplateParser.EvalExpressionContext;
import com.diguits.templateengine.antlr.TemplateParser.InlineConstantContext;
import com.diguits.templateengine.antlr.TemplateParser.InlineConstantExpressionContext;

public class TemplateCodeBuilderVisitor extends CodeBuilderVisitorBase {

	public TemplateCodeBuilderVisitor(String packageName, String[] defaultImports, String className,
			String parentClassName, String[] modelClassNames) {
		super(packageName, defaultImports, className, parentClassName, modelClassNames);
	}

	@Override
	protected void buildMethods() {
		super.buildMethods();
		append("\t@Override\r\n");
		append("\tprotected void apply(Object[] models){\r\n ");
		appendModelVariables();
	}

	@Override
	public Void visitConstantExpression(ConstantExpressionContext ctx) {
		String ctxText = getText(ctx);
		visistConstantExpression(ctxText);
		return super.visitConstantExpression(ctx);
	}

	private void visistConstantExpression(String ctxText) {
		if (!ctxText.replace("\r", "").replace("\n", "").isEmpty()) {
			String text = ctxText;
			if(text.startsWith("\n"))
				text = text.substring(1);
			append("\t\twrite(\"");
			append(text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r"));
			append("\");");
			append("\r\n");
		}
	}

	@Override
	public Void visitInlineConstantExpression(InlineConstantExpressionContext ctx) {
		String ctxText = getText(ctx);
		visistConstantExpression(ctxText);
		return super.visitInlineConstantExpression(ctx);
	}

	@Override
	public Void visitCodeStatement(CodeStatementContext ctx) {
		Void visitCodeStatement = super.visitCodeStatement(ctx);
		return visitCodeStatement;
	}

	@Override
	public Void visitEvalExpression(EvalExpressionContext ctx) {
		append("\t\twrite(");
		append(getText(ctx));
		append(");");
		append("\r\n");
		return super.visitEvalExpression(ctx);
	}

	@Override
	public Void visitInlineConstant(InlineConstantContext ctx) {
		Void visitInlineConstant = super.visitInlineConstant(ctx);
		append("\t\twrite(\"\\r\\n\");\r\n");
		return visitInlineConstant;
	}


	@Override
	public Void visitCodeExpression(CodeExpressionContext ctx) {
		append(getText(ctx));
		return super.visitCodeExpression(ctx);
	}
}