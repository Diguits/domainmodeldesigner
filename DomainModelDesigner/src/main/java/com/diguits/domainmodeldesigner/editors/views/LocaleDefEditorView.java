package com.diguits.domainmodeldesigner.editors.views;

import java.util.Locale;

import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

public class LocaleDefEditorView extends BaseDefEditorView<LocaleDefModel> {

	private ComboBox<Locale> language;
	private ComboBox<Locale> country;
	private CheckBox isDefault;
	private Label locale;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%locale");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		language = nodeFactory.createComboBoxInsideGrid(gridPane, "%language");
		country = nodeFactory.createComboBoxInsideGrid(gridPane, "%country");
		isDefault = nodeFactory.createCheckBoxInsideGrid(gridPane, "%is_default");
		locale = nodeFactory.createLabelInsideGrid(gridPane, "", "%locale");
		tab.setContent(nodeFactory.addAndFitToAnchorPane(gridPane));
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			language.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
				getModel().setLanguage(n.getLanguage());
				// country.getItems().addAll(localesByLanguage.get(n.getLanguage()));
				locale.setText(getModel().toString());
			});

			country.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
				getModel().setCountry(n.getCountry());
				locale.setText(getModel().toString());
			});

			String[] languages = Locale.getISOLanguages();
			Locale[] languagesLocales = new Locale[languages.length];
			int selectedIndex = 0;
			for (int i = 0; i < languages.length; i++) {
				languagesLocales[i] = new Locale(languages[i]);
				if (languages[i].equals(model.getLanguage())) {
					selectedIndex = i;
				}
			}
			language.getItems().addAll(languagesLocales);
			language.setConverter(new StringLocaleConverter());
			language.getSelectionModel().select(selectedIndex);

			/*
			 * localesByLanguage = new HashMap<String, List<Locale>>(); Locale[] availableLocales =
			 * Locale.getAvailableLocales(); for (Locale locale : availableLocales) { if
			 * (!localesByLanguage.containsKey(locale.getLanguage())) localesByLanguage.put(locale.getLanguage(), new
			 * ArrayList<Locale>()); localesByLanguage.get(locale.getLanguage()).add(locale); }
			 */

			String[] countries = Locale.getISOCountries();
			Locale[] countriesLocales = new Locale[countries.length];
			selectedIndex = 0;
			for (int i = 0; i < countries.length; i++) {
				countriesLocales[i] = new Locale("", countries[i]);
				if (countries[i].equals(model.getCountry())) {
					selectedIndex = i;
				}
			}
			country.getItems().addAll(countriesLocales);
			country.setConverter(new StringLocaleConverter());
			country.getSelectionModel().select(selectedIndex);

			bindBidirectional(isDefault.selectedProperty(), model.isDefaultProperty());
		}
	}

	public class StringLocaleConverter extends StringConverter<Locale> {

		@Override
		public String toString(Locale locale) {
			return locale.getCountry() != null && !locale.getCountry().isEmpty()
					? locale.getCountry() + " " + locale.getDisplayCountry()
					: (locale.getLanguage() != null ? locale.getLanguage() + " " + locale.getDisplayLanguage() : "");
		}

		@Override
		public Locale fromString(String string) {
			return null;
		}

	}
}
