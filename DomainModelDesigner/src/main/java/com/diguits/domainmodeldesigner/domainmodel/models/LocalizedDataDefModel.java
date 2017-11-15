package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LocalizedDataDefModel extends BaseDefModel  {

	public LocalizedDataDefModel() {
		super();
	}
	private StringProperty hint;
	private StringProperty caption;
	private StringProperty placeHolder;
	private StringProperty format;
	private ObjectProperty<LocaleDefModel> locale;

	public String getHint() {
		if (hint!= null)
			return hint.get();
		return "";
	}

	public void setHint(String hint) {
		if (this.hint!= null || hint == null || !hint.equals("")) {
			hintProperty().set(hint);
		}
	}

	public StringProperty hintProperty() {
		if(hint == null){
			hint = new SimpleStringProperty(this, "hint", "");
		}
		return hint;
	}

	public String getCaption() {
		if (caption!= null)
			return caption.get();
		return "";
	}

	public void setCaption(String caption) {
		if (this.caption!= null || caption == null || !caption.equals("")) {
			captionProperty().set(caption);
		}
	}

	public StringProperty captionProperty() {
		if(caption == null){
			caption = new SimpleStringProperty(this, "caption", "");
		}
		return caption;
	}

	public String getPlaceHolder() {
		if (placeHolder!= null)
			return placeHolder.get();
		return "";
	}

	public void setPlaceHolder(String placeHolder) {
		if (this.placeHolder!= null || placeHolder == null || !placeHolder.equals("")) {
			placeHolderProperty().set(placeHolder);
		}
	}

	public StringProperty placeHolderProperty() {
		if(placeHolder == null){
			placeHolder = new SimpleStringProperty(this, "placeHolder", "");
		}
		return placeHolder;
	}

	public String getFormat() {
		if (format!= null)
			return format.get();
		return "";
	}

	public void setFormat(String format) {
		if (this.format!= null || format == null || !format.equals("")) {
			formatProperty().set(format);
		}
	}

	public StringProperty formatProperty() {
		if(format == null){
			format = new SimpleStringProperty(this, "format", "");
		}
		return format;
	}

	public LocaleDefModel getLocale() {
		if (locale!= null)
			return locale.get();
		return null;
	}

	public void setLocale(LocaleDefModel locale) {
		if (this.locale!= null || locale != null) {
			localeProperty().set(locale);
		}
	}

	public ObjectProperty<LocaleDefModel> localeProperty() {
		if(locale == null){
			locale = new SimpleObjectProperty<LocaleDefModel>(this, "locale", null);
		}
		return locale;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitLocalizedDataDefModel(this, owner);
	}

}
