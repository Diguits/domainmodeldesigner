package com.diguits.domainmodeldesigner.editors.controllers;

import java.util.List;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.editors.views.BaseDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.controllers.DomainModelTreeDecorator;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;

import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public abstract class BaseDefEditorController<TView extends BaseDefEditorView<TModel>, TModel extends BaseDefModel>
		extends NamedModelEditorController<TView, TModel> {

	@Inject
	DomainModelClientService domainModelService;

	public BaseDefEditorController(TView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		DomainModelDefModel domainModelDef = domainModelService.getDomainModelDef();
		if (domainModelDef != null) {
			fillLocales(getModel(), domainModelDef.getLocales());
			fillCustomFields(getModel(), domainModelDef.getCustomFields());
		}

		configureTableModelViewActions(getView().getLocalizedDataView(), LocalizedDataDefModel.class);

		domainModelDef.getLocales().addListener(new ListChangeListener<LocaleDefModel>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends LocaleDefModel> c) {
				while (c.next()) {
					List<? extends LocaleDefModel> added = c.getAddedSubList();
					ObservableList<LocalizedDataDefModel> localizedDatas = getModel().getLocalizedDatas();
					for (LocaleDefModel localeDefModel : added) {
						LocalizedDataDefModel localizedData = new LocalizedDataDefModel();
						localizedData.setLocale(localeDefModel);
						localizedDatas.add(localizedData);
					}
					List<? extends LocaleDefModel> removed = c.getRemoved();
					for (LocaleDefModel localeDefModel : removed) {

						LocalizedDataDefModel toDelete = null;
						for (LocalizedDataDefModel localizedData : localizedDatas) {
							if (localizedData.getLocale() == localeDefModel)
								toDelete = localizedData;
						}
						if (toDelete != null)
							localizedDatas.remove(toDelete);
					}
				}

			}
		});

		domainModelDef.getCustomFields().addListener(new ListChangeListener<CustomFieldDefModel>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends CustomFieldDefModel> c) {
				while (c.next()) {
					List<? extends CustomFieldDefModel> added = c.getAddedSubList();
					for (CustomFieldDefModel customField : added) {
						addRemoveCustomFieldValue(getModel(), customField);
					}
					List<? extends CustomFieldDefModel> removed = c.getRemoved();
					for (CustomFieldDefModel customField : removed) {
						removeCustomFieldValue(customField);
					}
				}
			}
		});

	}

	ChangeListener<? super DataType> dataTypeListener = (v, o, n)->{
		getView().updateItemList();
	};

	protected void fillCustomFields(TModel model, ObservableList<CustomFieldDefModel> customFields) {
		for (CustomFieldDefModel customField : customFields) {
			addRemoveCustomFieldValue(model, customField);
		}
		for (CustomFieldValueDefModel customFieldValue : getModel().getCustomFieldValues()) {
			customFieldValue.getCustomField().dataTypeProperty().addListener(dataTypeListener);
		}
	}

	protected void addRemoveCustomFieldValue(TModel model, CustomFieldDefModel customField) {
		customField.overDefClassProperty().addListener((v, o, n) -> {
			if (applyCustomField(getModel(), o) && !applyCustomField(getModel(), n)) {
				removeCustomFieldValue(customField);
			}
			if (!applyCustomField(getModel(), o) && applyCustomField(getModel(), n)) {
				innerAddCustomFieldValue(model, customField);
			}
		});

		if (!model.getCustomFieldValues().stream().anyMatch(cf -> cf.getCustomField() == customField)) {
			if (applyCustomField(model, customField.getOverDefClass())) {
				innerAddCustomFieldValue(model, customField);
			}
		}
		if (model.getCustomFieldValues().stream().anyMatch(cf -> cf.getCustomField() == customField)) {
			if (!applyCustomField(model, customField.getOverDefClass())) {
				removeCustomFieldValue(customField);
			}
		}
	}

	private void innerAddCustomFieldValue(TModel model, CustomFieldDefModel customField) {
		CustomFieldValueDefModel customFieldValue = new CustomFieldValueDefModel();
		customFieldValue.setCustomField(customField);
		model.getCustomFieldValues().add(customFieldValue);
	}

	protected void removeCustomFieldValue(CustomFieldDefModel customField) {

		if(customField!=null)
			customField.dataTypeProperty().removeListener(dataTypeListener);

		CustomFieldValueDefModel toDelete = null;
		for (CustomFieldValueDefModel customFieldValue : getModel().getCustomFieldValues()) {
			if (customFieldValue.getCustomField() == customField)
				toDelete = customFieldValue;
		}
		if (toDelete != null)
			getModel().getCustomFieldValues().remove(toDelete);
	}

	protected boolean applyCustomField(TModel model, Class<? extends BaseDef> overDefClass) {
		if (overDefClass != null && model != null)
			return (overDefClass.getSimpleName() + "Model").equals(model.getClass().getSimpleName())
					|| overDefClass.getSimpleName().equals(BaseDef.class.getSimpleName());
		return false;
	}

	private void fillLocales(BaseDefModel model, ObservableList<LocaleDefModel> locales) {
		for (LocaleDefModel locale : locales) {
			if (!model.getLocalizedDatas().stream().anyMatch(l -> l.getLocale() == locale)) {
				LocalizedDataDefModel localizedData = new LocalizedDataDefModel();
				localizedData.setLocale(locale);
				model.getLocalizedDatas().add(localizedData);
			}
		}
	}

	protected <M extends BaseDefModel> M add(M model, ObservableList<M> list) {
		model.setOwner(this.getModel());
		return super.add(model, list);
	}

	@Override
	public ImageView getImage() {
		TModel model = getModel();
		if (model != null)
			return DomainModelTreeDecorator.getImageForModel(model.getClass());
		return null;
	}

	@Override
	protected <M extends NamedModelBase> M innerAdd(M model, ObservableList<M> list) {
		if (model != null && model instanceof BaseDefModel)
			((BaseDefModel) model).setOwner(getAddingModelOwner(model));
		return super.innerAdd(model, list);
	}

	protected <M extends NamedModelBase> BaseDefModel getAddingModelOwner(M model) {
		return getModel();
	}
}
