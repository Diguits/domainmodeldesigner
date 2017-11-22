package com.diguits.templatedefinition.definitions;

import java.util.List;
import java.util.UUID;

public abstract class TemplateProjectItemDef {
    protected UUID id;
    protected String name;
    protected String description;
    protected boolean active = true;
    protected TemplateProjectItemDef owner;

    public TemplateProjectItemDef() {
        super();
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TemplateProjectItemDef getOwner() {
        return owner;
    }

    public abstract List<? extends TemplateProjectItemDef> getItems();
}
