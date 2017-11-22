package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BaseDef implements IVisitable {
	protected UUID id;
	protected String name;
	protected String description;
	protected BaseDef owner;
	protected List<LocalizedDataDef> localizedDataList;
	protected List<CustomFieldValueDef> customFieldValues;

	public BaseDef(BaseDef owner) {
		this();
		this.owner = owner;
	}

	public BaseDef() {
		super();
		setId(UUID.randomUUID());
		localizedDataList = new ArrayList<LocalizedDataDef>();
		customFieldValues = new ArrayList<CustomFieldValueDef>();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			name = name.trim();
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description != null)
			description = description.trim();
		this.description = description;
	}

	public List<LocalizedDataDef> getLocalizedDataList() {
		return localizedDataList;
	}

	public void setLocalizedDataList(List<LocalizedDataDef> localizedDataList) {
		this.localizedDataList = localizedDataList;
	}

	public List<CustomFieldValueDef> getCustomFieldValues() {
		return customFieldValues;
	}

	public void setCustomFieldValues(List<CustomFieldValueDef> customFieldValues) {
		this.customFieldValues = customFieldValues;
	}

	public String getDefaultCaption() {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(false);
		if (defaultValue != null)
			return defaultValue.getCaption();
		return null;
	}

	public void setDefaultCaption(String caption) {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(true);
		if (defaultValue != null)
			defaultValue.setCaption(caption);
	}

	public String getDefaultHint() {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(false);
		if (defaultValue != null)
			return defaultValue.getHint();
		return null;
	}

	public void setDefaultHint(String hint) {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(true);
		if (defaultValue != null)
			defaultValue.setHint(hint);
	}

	public String getDefaultPlaceHolder() {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(false);
		if (defaultValue != null)
			return defaultValue.getPlaceHolder();
		return null;
	}

	public void setDefaultPlaceHolder(String placeHolder) {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(true);
		if (defaultValue != null)
			defaultValue.setPlaceHolder(placeHolder);
	}

	public String getDefaultFormat() {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(false);
		if (defaultValue != null)
			return defaultValue.getFormat();
		return null;
	}

	public void setDefaultFormat(String format) {
		LocalizedDataDef defaultValue = getDefaultLocalizedData(true);
		if (defaultValue != null)
			defaultValue.setFormat(format);
	}

	public String getCaption(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getCaption();
		return getDefaultCaption();
	}

	public String getHint(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getHint();
		return getDefaultHint();
	}

	public String getPlaceHolder(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getPlaceHolder();
		return getDefaultPlaceHolder();
	}

	public String getFormat(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getFormat();
		return getDefaultFormat();
	}

	protected LocalizedDataDef getLocalizedDataByLocale(LocaleDef locale) {
		for (LocalizedDataDef localizedData : localizedDataList) {
			if (localizedData.getLocale() == locale)
				return localizedData;
		}
		return null;
	}

	protected LocalizedDataDef getDefaultLocalizedData(boolean create) {
		DomainModelDef domainModel = getDomainModel();
		if (domainModel != null) {
			LocaleDef defaultLocale = domainModel.getDefaultLocale();
			if (defaultLocale != null) {
				for (LocalizedDataDef localizedData : localizedDataList) {
					if (localizedData.getLocale() == defaultLocale)
						return localizedData;
				}
				if (create) {
					LocalizedDataDef newLocalizedData = new LocalizedDataDef();
					newLocalizedData.setLocale(defaultLocale);
					localizedDataList.add(newLocalizedData);
				}
			}
		}
		return null;
	}

	public BaseDef getOwner() {
		return owner;
	}

	public void setOwner(BaseDef owner) {
		this.owner = owner;
	}

	protected String getUniqueName() {
		String className = getClass().getName();
		return className.substring(0, className.length() - 3) + name;
	}

	public DomainModelDef getDomainModel() {
		BaseDef parent = this;
		while (parent != null && !(parent instanceof DomainModelDef)) {
			parent = parent.getOwner();
		}
		return (DomainModelDef) parent;
	}

	public Object getCustomFieldValue(String name) {
		int i = 0;
		List<CustomFieldValueDef> values = getCustomFieldValues();
		if (values != null) {
			while (i < values.size() && (values.get(i) == null || values.get(i).getName() == null
					|| !values.get(i).getName().equals(name)))
				i++;
			if (i < values.size())
				return values.get(i).getValue();
		}
		return null;
	}

	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		for (CustomFieldValueDef customFieldValue : customFieldValues) {
			customFieldValue.accept(visitor, this);
		}
		for (LocalizedDataDef localizedData : localizedDataList) {
			localizedData.accept(visitor, this);
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " : " + getName();
	}

	public String getOwnerPath() {
		if (getOwner() != null)
			return getOwner().getPath();
		return "";
	}

	private String getPath() {
		String result = getName();
		BaseDef current = this.getOwner();
		while (current != null) {
			result = current.getName() + "/" + result;
			current = current.getOwner();
		}
		return result;
	}

	public LocalizedDataDef getLocalizedData(LocaleDef locale) {
		for (LocalizedDataDef localizedDataDef : localizedDataList) {
			if (localizedDataDef.getLocale() == locale)
				return localizedDataDef;
		}
		return null;
	}
}
