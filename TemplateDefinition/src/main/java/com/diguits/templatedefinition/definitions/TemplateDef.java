package com.diguits.templatedefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class TemplateDef extends TemplateProjectItemDef {

    private String outputFileNameExpression;
    private List<String> classNamesToApplyOver;
    private String path;
    private String source;
    private List<TemplateParameterDef> parameters;


    public TemplateDef() {
        super();
        parameters = new ArrayList<TemplateParameterDef>();
        classNamesToApplyOver = new ArrayList<String>();
    }

    public String getOutputFileNameExpression() {
        return outputFileNameExpression;
    }

    public void setOutputFileNameExpression(String outputFileNameExpression) {
        this.outputFileNameExpression = outputFileNameExpression;
    }

    public List<String> getClassNamesToApplyOver() {
        return classNamesToApplyOver;
    }

    public void setClassNamesToApplyOver(List<String> classNamesToApplyOver) {
        this.classNamesToApplyOver = classNamesToApplyOver;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<TemplateParameterDef> getParameters() {
        return parameters;
    }

    public void setParameters(List<TemplateParameterDef> parameters) {
        this.parameters = parameters;
    }

    @Override
    public List<? extends TemplateProjectItemDef> getItems() {
        return null;
    }

    public TemplateGroupDef getGroupOwner() {
        return (TemplateGroupDef) owner;
    }

    public void setGroupOwner(TemplateGroupDef owner) {
        this.owner = owner;
    }
}
