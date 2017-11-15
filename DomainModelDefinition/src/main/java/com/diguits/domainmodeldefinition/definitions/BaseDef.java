package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BaseDef implements IVisitable {
	protected UUID id;
	protected String name;
	protected String description;
	protected BaseDef owner;
	protected List<LocalizedDataDef> localizedDatas;
	protected List<CustomFieldValueDef> customFieldValues;

	public BaseDef(BaseDef owner) {
		this();
		this.owner = owner;
	}

	public BaseDef() {
		super();
		setId(UUID.randomUUID());
		localizedDatas = new ArrayList<LocalizedDataDef>();
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

	public List<LocalizedDataDef> getLocalizedDatas() {
		return localizedDatas;
	}

	public void setLocalizedDatas(List<LocalizedDataDef> localizedDatas) {
		this.localizedDatas = localizedDatas;
	}

	public List<CustomFieldValueDef> getCustomFieldValues() {
		return customFieldValues;
	}

	public void setCustomFieldValues(List<CustomFieldValueDef> customFieldValues) {
		this.customFieldValues = customFieldValues;
	}

	public String getDefaultCaption() {
		LocalizedDataDef defualt = getDefaultLocalizedData(false);
		if (defualt != null)
			return defualt.getCaption();
		return null;
	}

	public void setDefaultCaption(String caption) {
		LocalizedDataDef defualt = getDefaultLocalizedData(true);
		if (defualt != null)
			defualt.setCaption(caption);
	}

	public String getDefaultHint() {
		LocalizedDataDef defualt = getDefaultLocalizedData(false);
		if (defualt != null)
			return defualt.getHint();
		return null;
	}

	public void setDefaultHint(String hint) {
		LocalizedDataDef defualt = getDefaultLocalizedData(true);
		if (defualt != null)
			defualt.setHint(hint);
	}

	public String getDefaultPlaceHolder() {
		LocalizedDataDef defualt = getDefaultLocalizedData(false);
		if (defualt != null)
			return defualt.getPlaceHolder();
		return null;
	}

	public void setDefaultPlaceHolder(String placeHolder) {
		LocalizedDataDef defualt = getDefaultLocalizedData(true);
		if (defualt != null)
			defualt.setPlaceHolder(placeHolder);
	}

	public String getDefaultFormat() {
		LocalizedDataDef defualt = getDefaultLocalizedData(false);
		if (defualt != null)
			return defualt.getFormat();
		return null;
	}

	public void setDefaultFormat(String format) {
		LocalizedDataDef defualt = getDefaultLocalizedData(true);
		if (defualt != null)
			defualt.setFormat(format);
	}

	public String getCaption(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getCaption();
		return null;
	}

	public String getHint(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getHint();
		return null;
	}

	public String getPlaceHolder(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getPlaceHolder();
		return null;
	}

	public String getFormat(LocaleDef locale) {
		LocalizedDataDef data = getLocalizedDataByLocale(locale);
		if (data != null)
			return data.getFormat();
		return null;
	}

	protected LocalizedDataDef getLocalizedDataByLocale(LocaleDef locale) {
		for (LocalizedDataDef localizedData : localizedDatas) {
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
				for (LocalizedDataDef localizedData : localizedDatas) {
					if (localizedData.getLocale() == defaultLocale)
						return localizedData;
				}
				if (create) {
					LocalizedDataDef newlocalizedData = new LocalizedDataDef();
					newlocalizedData.setLocale(defaultLocale);
					localizedDatas.add(newlocalizedData);
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
			parent = owner.getOwner();
		}
		if (parent instanceof DomainModelDef)
			return (DomainModelDef) parent;
		return null;
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
		for (LocalizedDataDef localizedData : localizedDatas) {
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
		for (LocalizedDataDef localizedDataDef : localizedDatas) {
			if (localizedDataDef.getLocale() == locale)
				return localizedDataDef;
		}
		return null;
	}
}
