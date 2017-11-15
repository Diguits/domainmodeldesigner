package com.diguits.domainmodeldefinition.definitions;

public class CustomFieldValueDef extends BaseDef {
	private CustomFieldDef customField;
	private Object value;

	public CustomFieldDef getCustomField() {
		return customField;
	}

	public void setCustomField(CustomFieldDef customField) {
		this.customField = customField;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitCustomFieldValueDef(this, owner);
	}
}
