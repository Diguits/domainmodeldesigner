package com.diguits.templatedefinition.definitions;

import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DataType;

public class TemplateParameterDef extends TemplateProjectItemDef {

    private DataType dataType;
    private UUID reference;
    private String data;
    private boolean multiline;


    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public UUID getReference() {
        return reference;
    }

    public void setReference(UUID reference) {
        this.reference = reference;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean getMultiline() {
        return multiline;
    }

    public void setMultiline(boolean multiline) {
        this.multiline = multiline;
    }

    @Override
    public List<? extends TemplateProjectItemDef> getItems() {
        return null;
    }

    public TemplateDef getTemplateOwner() {
        return (TemplateDef) owner;
    }

    public void setTemplateOwner(TemplateDef owner) {
        this.owner = owner;
    }
}
