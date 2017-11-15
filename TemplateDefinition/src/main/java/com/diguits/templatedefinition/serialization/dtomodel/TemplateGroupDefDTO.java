package com.diguits.templatedefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

public class TemplateGroupDefDTO extends TemplateProjectItemDefDTO {

    public List<TemplateDefDTO> templates;

    public TemplateGroupDefDTO() {
        templates = new ArrayList<TemplateDefDTO>();
    }

    public List<? extends TemplateProjectItemDefDTO> getItems() {
        return templates;
    }

    public List<TemplateDefDTO> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateDefDTO> templates) {
        this.templates = templates;
    }
}
