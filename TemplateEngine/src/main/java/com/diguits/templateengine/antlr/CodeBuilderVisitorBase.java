package com.diguits.templateengine.antlr;

import java.io.IOException;
import java.io.Writer;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import com.diguits.templateengine.antlr.TemplateParser.ImportDeclarationsContext;
import com.diguits.templateengine.antlr.TemplateParser.TemplateFunctionsContext;
import com.diguits.templateengine.antlr.TemplateParser.TemplateStatementsContext;

public class CodeBuilderVisitorBase extends TemplateBaseVisitor<Void> {

	protected Writer writer;
	protected String packageName;
	protected String[] defaultImports;
	protected String className;
	protected String parentClassName;
	protected String[] modelClassNames;
	private boolean classHeaderAdded;
	private boolean insideTemplateFunctions;

	public CodeBuilderVisitorBase(String packageName, String[] defaultImports, String className,
			String parentClassName, String[] modelClassNames) {
		super();
		this.packageName = packageName;
		this.defaultImports = defaultImports;
		this.className = className;
		this.parentClassName = parentClassName;
		this.modelClassNames = modelClassNames == null || modelClassNames.length==0 ? new String[]{"Object"} : modelClassNames;
	}

	protected String getText(ParserRuleContext ctx){
		return ctx.getText().replace("(@)", "@");
	}

	@Override
	public Void visit(ParseTree tree) {
		buildHeader();
		this.classHeaderAdded = false;
		this.insideTemplateFunctions = false;
		return super.visit(tree);
	}

	protected void append(String str) {
		if (str != null) {
			try {
				writer.write(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void buildHeader() {
		append("package ");
		append(packageName);
		append(";\r\n");
		append("\r\n");
		for (String importName : defaultImports) {
			append("import ");
			append(importName);
			append(";\r\n");
		}
	}

	protected void appendModelVariables() {
		for (int i = 0; i < modelClassNames.length; i++) {
			String modelClassName = modelClassNames[i];
			append("\t\t\t");
			append(modelClassName);
			if(i==0)
				append(" model");
			else
				append(" model"+i);
			append(" = ("+modelClassName+") models["+i+"];\r\n");
		}
	}

	@Override
	public Void visitImportDeclarations(ImportDeclarationsContext ctx) {
		String importsStr = getText(ctx);
		String[] imports = importsStr.split("\n");
		for (String imp : imports) {
			imp = imp.replace("\r", "").trim();
			if (!imp.isEmpty())
				append("import " + imp + ";\r\n");
		}
		return super.visitImportDeclarations(ctx);
	}

	@Override
	public Void visitTemplateFunctions(TemplateFunctionsContext ctx) {
		buildClassHeader();
		classHeaderAdded = true;
		insideTemplateFunctions = true;
		Void visitTemplateFunctions = super.visitTemplateFunctions(ctx);
		insideTemplateFunctions = false;
		return visitTemplateFunctions;
	}

	@Override
	public Void visitTemplateStatements(TemplateStatementsContext ctx) {
		if(!classHeaderAdded)
			buildClassHeader();
		if(!insideTemplateFunctions)
			buildMethods();
		Void visitTemplateStatements = super.visitTemplateStatements(ctx);
		if(!insideTemplateFunctions)
			buildClassFooter();
		return visitTemplateStatements;
	}

	protected void buildMethods() {

	}

	private void buildClassHeader() {
		append("\r\n");
		append("public class ");
		append(className);
		append(" extends ");
		append(parentClassName);
		/*append("<");
		append(modelClassName);
		append(">");*/
		append(" {\r\n");
	}

	protected void buildClassFooter() {
		append("\t}\r\n");
		append("}\r\n");
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	protected String getPackageName() {
		return packageName;
	}

	public String[] getDefaultImports() {
		return defaultImports;
	}

	public void setDefaultImports(String[] defaultImports) {
		this.defaultImports = defaultImports;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String[] getModelClassNames() {
		return modelClassNames;
	}

	public void setModelClassNames(String[] modelClassNames) {
		this.modelClassNames = modelClassNames;
	}
}
