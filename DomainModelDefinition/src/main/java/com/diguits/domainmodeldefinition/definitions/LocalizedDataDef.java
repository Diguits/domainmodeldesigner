package com.diguits.domainmodeldefinition.definitions;

public class LocalizedDataDef extends BaseDef {
	private LocaleDef locale;
	private String caption;
	private String hint;
	private String placeHolder;
	private String format;

	public LocaleDef getLocale() {
		return locale;
	}

	public void setLocale(LocaleDef locale) {
		this.locale = locale;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		visitor.visitLocalizedDataDef(this, owner);
	}
}
