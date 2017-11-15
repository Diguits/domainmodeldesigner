// Generated from Template.g4 by ANTLR 4.5.1
package com.diguits.templateengine.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TemplateParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TemplateVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TemplateParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(TemplateParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#templeteImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTempleteImportDeclaration(TemplateParser.TempleteImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#importDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclarations(TemplateParser.ImportDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#templateFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateFunctions(TemplateParser.TemplateFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#functions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctions(TemplateParser.FunctionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#templateStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateStatements(TemplateParser.TemplateStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#templateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateStatement(TemplateParser.TemplateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#constantStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantStatement(TemplateParser.ConstantStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#constantStatementParts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantStatementParts(TemplateParser.ConstantStatementPartsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#constantStatementPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantStatementPart(TemplateParser.ConstantStatementPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(TemplateParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#codeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeStatement(TemplateParser.CodeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#codeStatementParts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeStatementParts(TemplateParser.CodeStatementPartsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#codeStatementPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeStatementPart(TemplateParser.CodeStatementPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#codeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeExpression(TemplateParser.CodeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#inlineConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineConstant(TemplateParser.InlineConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#inlineConstantStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineConstantStatement(TemplateParser.InlineConstantStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#inlineConstantStatementParts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineConstantStatementParts(TemplateParser.InlineConstantStatementPartsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#inlineConstantStatementPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineConstantStatementPart(TemplateParser.InlineConstantStatementPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#inlineConstantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineConstantExpression(TemplateParser.InlineConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#evalValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvalValue(TemplateParser.EvalValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#evalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvalExpression(TemplateParser.EvalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#anything}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnything(TemplateParser.AnythingContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#anychar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnychar(TemplateParser.AnycharContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#emptyCharSecuence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyCharSecuence(TemplateParser.EmptyCharSecuenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#emptyChar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyChar(TemplateParser.EmptyCharContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#inlineAnything}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineAnything(TemplateParser.InlineAnythingContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateParser#inlineAnychar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineAnychar(TemplateParser.InlineAnycharContext ctx);
}