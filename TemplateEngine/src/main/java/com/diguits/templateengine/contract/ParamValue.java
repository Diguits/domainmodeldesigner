package com.diguits.templateengine.contract;

public class ParamValue {
	String name;
	Object value;

	public ParamValue(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}
