package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class LocaleDefModel extends BaseDefModel {

	public LocaleDefModel() {
		super();
		nameProperty().bind(languageProperty().concat(new When(countryProperty().isNotNull()).then("_").otherwise(""))
				.concat(countryProperty()));
		isDefaultProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					DomainModelDefModel domainModel = getDomainModelOwner();
					if (domainModel != null) {
						for (LocaleDefModel locale : domainModel.getLocales()) {
							if (locale != LocaleDefModel.this)
								locale.setIsDefault(false);
						}
					}
				}
			}
		});
	}

	private StringProperty country;
	private StringProperty language;
	private BooleanProperty isDefault;

	public String getCountry() {
		if (country != null)
			return country.get();
		return "";
	}

	public void setCountry(String country) {
		if (this.country != null || country == null || !country.equals("")) {
			countryProperty().set(country);
		}
	}

	public StringProperty countryProperty() {
		if (country == null) {
			country = new SimpleStringProperty(this, "country", "");
		}
		return country;
	}

	public String getLanguage() {
		if (language != null)
			return language.get();
		return "";
	}

	public void setLanguage(String language) {
		if (this.language != null || language == null || !language.equals("")) {
			languageProperty().set(language);
		}
	}

	public StringProperty languageProperty() {
		if (language == null) {
			language = new SimpleStringProperty(this, "language", "");
		}
		return language;
	}

	public boolean getIsDefault() {
		if (isDefault != null)
			return isDefault.get();
		return false;
	}

	public void setIsDefault(boolean isDefault) {
		if (this.isDefault != null || isDefault != false) {
			isDefaultProperty().set(isDefault);
		}
	}

	public BooleanProperty isDefaultProperty() {
		if (isDefault == null) {
			isDefault = new SimpleBooleanProperty(this, "isDefault", false);
		}
		return isDefault;
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		visitor.visitLocaleDefModel(this, owner);
	}

	public DomainModelDefModel getDomainModelOwner() {
		return (DomainModelDefModel) getOwner();
	}
}
