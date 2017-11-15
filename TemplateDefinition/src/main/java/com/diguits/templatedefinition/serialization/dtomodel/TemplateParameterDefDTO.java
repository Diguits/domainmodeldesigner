package com.diguits.templatedefinition.serialization.dtomodel;

import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DataType;

public class TemplateParameterDefDTO extends TemplateProjectItemDefDTO {

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
}
