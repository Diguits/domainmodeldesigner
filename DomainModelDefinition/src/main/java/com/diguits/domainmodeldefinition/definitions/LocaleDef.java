package com.diguits.domainmodeldefinition.definitions;

public class LocaleDef extends BaseDef {
	private boolean isDefault;
	private String language;
	private String country;

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

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitLocaleDef(this, owner);
	}

}
