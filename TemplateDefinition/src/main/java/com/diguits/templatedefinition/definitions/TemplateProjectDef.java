package com.diguits.templatedefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class TemplateProjectDef extends TemplateProjectItemDef {

    private List<TemplateGroupDef> groups;

    public TemplateProjectDef() {
        super();
        groups = new ArrayList<TemplateGroupDef>();
    }

    public List<TemplateGroupDef> getGroups() {
        return groups;
    }

    public void setGroups(List<TemplateGroupDef> groups) {
        this.groups = groups;
    }

    @Override
    public List<? extends TemplateProjectItemDef> getItems() {
        return groups;
    }
}
