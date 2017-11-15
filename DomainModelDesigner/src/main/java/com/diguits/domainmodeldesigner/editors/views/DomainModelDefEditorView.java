package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;

import javafx.beans.binding.When;
import javafx.scene.Node;
import javafx.util.StringConverter;

public class DomainModelDefEditorView extends BaseDefEditorView<DomainModelDefModel>{

	TableModelEditorView<LocaleDefModel> localesView;
	private TableModelEditorView<CustomFieldDefModel> customFieldsView;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();

		localesView  = createTableEditorViewInNewTab(tabPane, "%locales", LocaleDefModel.class, tvf-> tvf
				.add(String.class, "%language").width(90).valueFactory(cd -> cd.getValue().languageProperty()).buildColumn()
				.add(String.class, "%country").width(75).valueFactory(cd -> cd.getValue().countryProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%locale").width(75).valueFactory(cd -> cd.getValue().languageProperty().concat(new When(cd.getValue().countryProperty().isNotNull()).then("_").otherwise("")).concat(cd.getValue().countryProperty())).buildColumn()
				.add(Boolean.class, "%is_default").width(75).valueFactory(cd -> cd.getValue().isDefaultProperty()).useDefaultCellValueFactory().buildColumn()
				.buildTable());

		customFieldsView  = createTableEditorViewInNewTab(tabPane, "%custom_fields", CustomFieldDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%over_model").width(75).valueFactory(cd -> cd.getValue().overDefClassNameProperty()).buildColumn()
				.add(DataType.class, "%data_type").width(90).valueFactory(cd -> cd.getValue().dataTypeProperty()).buildColumn()
				.buildTable());

		return contentView;
	}

	public class StringBaseDefClassConverter extends StringConverter<Class<? extends BaseDef>> {

		@Override
		public String toString(Class<? extends BaseDef> clazz) {
			return clazz.getSimpleName().replace("Def", "");
		}

		@Override
		public Class<? extends BaseDef> fromString(String string) {
			return null;
		}

	}
	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		localesView.setModel(getModel().localesProperty());
		customFieldsView.setModel(getModel().customFieldsProperty());
	}

	public TableModelEditorView<LocaleDefModel> getLocalesView() {
		return localesView;
	}

	public TableModelEditorView<CustomFieldDefModel> getCustomFieldsView() {
		return customFieldsView;
	}
}
