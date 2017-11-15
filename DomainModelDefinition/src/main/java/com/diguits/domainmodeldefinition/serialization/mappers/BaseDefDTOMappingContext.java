package com.diguits.domainmodeldefinition.serialization.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;

public class BaseDefDTOMappingContext extends MappingContext {
	private Map<UUID, LocaleDef> locales;
	private Map<UUID, CustomFieldDef> customFields;

	public Map<UUID, LocaleDef> getLocales() {
		if (locales == null)
			locales = new HashMap<UUID, LocaleDef>();
		return locales;
	}

	public Map<UUID, CustomFieldDef> getCustomFields() {
		if (customFields == null)
			customFields = new HashMap<UUID, CustomFieldDef>();
		return customFields;
	}

	void register(LocaleDef locale) {
		if (locale != null)
			getLocales().put(locale.getId(), locale);
	}

	void register(CustomFieldDef customField) {
		if (customField != null)
			getCustomFields().put(customField.getId(), customField);
	}

	public List<LocaleDef> getLocaleList() {
		return new ArrayList<LocaleDef>(getLocales().values());
	}

	public List<CustomFieldDef> getCustomFieldList() {
		return new ArrayList<CustomFieldDef>(getCustomFields().values());
	}
}
