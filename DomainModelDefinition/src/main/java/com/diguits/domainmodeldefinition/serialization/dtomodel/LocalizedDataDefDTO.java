package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class LocalizedDataDefDTO extends BaseDefDTO {
	protected UUID localeId;
	protected String caption;
	protected String hint;
	private String placeHolder;
	private String format;

	public UUID getLocaleId() {
		return localeId;
	}

	public void setLocaleId(UUID localeId) {
		this.localeId = localeId;
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
}
