package com.diguits.domainmodeldefinition.definitions;

import java.util.UUID;

public class CustomFieldDef extends BaseDef {

	private Class<? extends BaseDef> overDefClass;
	private DataType dataType;
	private UUID reference;
	private String data;
	private boolean multiline;


	public Class<? extends BaseDef> getOverDefClass() {
		return overDefClass;
	}

	public void setOverDefClass(Class<? extends BaseDef> overDefClass) {
		this.overDefClass = overDefClass;
	}

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
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitCustomFieldDef(this, owner);
	}
}
