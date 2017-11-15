package com.diguits.templatedefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

public class TemplateDefDTO extends TemplateProjectItemDefDTO {
    private String outputFileNameExpression;
    private List<String> classNamesToApplyOver;
    private String path;
    private List<TemplateParameterDefDTO> parameters;


    public TemplateDefDTO() {
        super();
        parameters = new ArrayList<TemplateParameterDefDTO>();
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

    public List<TemplateParameterDefDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<TemplateParameterDefDTO> parameters) {
        this.parameters = parameters;
    }
}
