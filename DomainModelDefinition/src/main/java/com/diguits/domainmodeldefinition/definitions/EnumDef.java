package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public class EnumDef extends SimpleDomainObjectDef {
    private List<EnumValueDef> values;
    private boolean useIcon;
    private boolean useTitle;
    private boolean useColor;

    public EnumDef(DomainModelDef owner) {
        super(owner);
    }

    @Override
    protected void initialize() {
        values = new ArrayList<EnumValueDef>();
    }

    public EnumDef() {
        super();;
    }

    public List<EnumValueDef> getValues() {
        return values;
    }

    public void setValues(List<EnumValueDef> values) {
        this.values = values;
    }

    public boolean getUseIcon() {
        return useIcon;
    }

    public void setUseIcon(boolean useIcon) {
        this.useIcon = useIcon;
    }

    public boolean getUseTitle() {
        return useTitle;
    }

    public void setUseTitle(boolean useTitle) {
        this.useTitle = useTitle;
    }

    public boolean getUseColor() {
        return useColor;
    }

    public void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }

    @Override
    public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
        super.accept(visitor, owner);
        for (EnumValueDef enumValueDef : values) {
            enumValueDef.accept(visitor, this);
        }
        visitor.visitEnumDef(this, owner);
    }
}