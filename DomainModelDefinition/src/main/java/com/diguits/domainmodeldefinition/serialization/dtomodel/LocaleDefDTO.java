package com.diguits.domainmodeldefinition.serialization.dtomodel;

import org.simpleframework.xml.Default;

@Default(required = false)
public class LocaleDefDTO extends BaseDefDTO {
	private String language;
	private String country;
	private boolean isDefault;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
