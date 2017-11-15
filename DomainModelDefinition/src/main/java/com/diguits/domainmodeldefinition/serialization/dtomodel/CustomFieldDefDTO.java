package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.UUID;

import org.simpleframework.xml.Default;

import com.diguits.domainmodeldefinition.definitions.DataType;

@Default(required = false)
public class CustomFieldDefDTO extends BaseDefDTO {

	private String overDefClass;
	private DataType dataType;
	private UUID reference;
	private String data;
	private boolean multiline;


	public String getOverDefClass() {
		return overDefClass;
	}

	public void setOverDefClass(String overDefClass) {
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
}
