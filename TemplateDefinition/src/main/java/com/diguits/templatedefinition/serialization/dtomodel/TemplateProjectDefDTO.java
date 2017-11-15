package com.diguits.templatedefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

public class TemplateProjectDefDTO extends TemplateProjectItemDefDTO {

    private List<TemplateGroupDefDTO> groups;

    public TemplateProjectDefDTO() {
        super();
        groups = new ArrayList<TemplateGroupDefDTO>();
    }

    public List<? extends TemplateProjectItemDefDTO> getItems() {
        return groups;
    }

    public List<TemplateGroupDefDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<TemplateGroupDefDTO> groups) {
        this.groups = groups;
    }
}
