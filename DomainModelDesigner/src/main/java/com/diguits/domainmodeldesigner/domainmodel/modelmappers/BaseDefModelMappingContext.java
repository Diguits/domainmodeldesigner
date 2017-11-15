package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;

public class BaseDefModelMappingContext extends MappingContext {
	private Map<UUID, LocaleDef> locales;
	private Map<UUID, LocaleDefModel> localeModels;

	private Map<UUID, CustomFieldDef> customFields;
	private Map<UUID, CustomFieldDefModel> customFieldModels;

	public Map<UUID, LocaleDef> getLocales() {
		if (locales == null)
			locales = new HashMap<UUID, LocaleDef>();
		return locales;
	}

	public Map<UUID, LocaleDefModel> getLocaleModels() {
		if (localeModels == null)
			localeModels = new HashMap<UUID, LocaleDefModel>();
		return localeModels;
	}

	void register(LocaleDef locale) {
		if (locale != null)
			getLocales().put(locale.getId(), locale);
	}

	void register(LocaleDefModel locale) {
		if (locale != null)
			getLocaleModels().put(locale.getId(), locale);
	}

	public List<LocaleDef> getLocaleList() {
		return new ArrayList<LocaleDef>(getLocales().values());
	}

	public List<LocaleDefModel> getLocaleModelList() {
		return new ArrayList<LocaleDefModel>(getLocaleModels().values());
	}

	public Map<UUID, CustomFieldDef> getCustomFields() {
		if (customFields == null)
			customFields = new HashMap<UUID, CustomFieldDef>();
		return customFields;
	}

	public Map<UUID, CustomFieldDefModel> getCustomFieldModels() {
		if (customFieldModels == null)
			customFieldModels = new HashMap<UUID, CustomFieldDefModel>();
		return customFieldModels;
	}

	void register(CustomFieldDef customField) {
		if (customField != null)
			getCustomFields().put(customField.getId(), customField);
	}

	void register(CustomFieldDefModel customField) {
		if (customField != null)
			getCustomFieldModels().put(customField.getId(), customField);
	}

	public List<CustomFieldDef> getCustomFieldList() {
		return new ArrayList<CustomFieldDef>(getCustomFields().values());
	}

	public List<CustomFieldDefModel> getCustomFieldModelList() {
		return new ArrayList<CustomFieldDefModel>(getCustomFieldModels().values());
	}
}
