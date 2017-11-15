package com.diguits.templatedefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class TemplateGroupDef extends TemplateProjectItemDef {

    public List<TemplateDef> templates;

    public TemplateGroupDef() {
        templates = new ArrayList<TemplateDef>();
    }

    @Override
    public List<? extends TemplateProjectItemDef> getItems() {
        return templates;
    }

    public List<TemplateDef> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateDef> templates) {
        this.templates = templates;
    }

    public TemplateProjectDef getProjectOwner() {
        return (TemplateProjectDef) owner;
    }

    public void setProjectOwner(TemplateProjectDef owner) {
        this.owner = owner;
    }
}
