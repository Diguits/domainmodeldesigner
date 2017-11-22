package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import java.util.List;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldValueDef;
import com.diguits.domainmodeldefinition.definitions.LocalizedDataDef;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;

public abstract class BaseDefMapperToBaseDefModelMapper<TFrom extends BaseDef, TTo extends BaseDefModel>
		extends MapperBase<TFrom, TTo> {

	IMapperProvider mapperProvider;

	public BaseDefMapperToBaseDefModelMapper(IMapperProvider mapperProvider) {
		super();
		this.mapperProvider = mapperProvider;
	}

	protected LocalizedDataDefToLocalizedDataDefModelMapper localizedDataDefToLocalizedDataDefModelMapper;
	protected CustomFieldValueDefToCustomFieldValueDefModelMapper customFieldValueDefToCustomFieldValueDefModelMapper;

	public LocalizedDataDefToLocalizedDataDefModelMapper getLocalizedDataDefToLocalizedDataDefModelMapper() {
		if (localizedDataDefToLocalizedDataDefModelMapper == null)
			localizedDataDefToLocalizedDataDefModelMapper = mapperProvider
					.getMapper(LocalizedDataDefToLocalizedDataDefModelMapper.class);
		return localizedDataDefToLocalizedDataDefModelMapper;
	}

	public CustomFieldValueDefToCustomFieldValueDefModelMapper getCustomFieldValueDefToCustomFieldValueDefModelMapper() {
		if (customFieldValueDefToCustomFieldValueDefModelMapper == null)
			customFieldValueDefToCustomFieldValueDefModelMapper = mapperProvider
					.getMapper(CustomFieldValueDefToCustomFieldValueDefModelMapper.class);
		return customFieldValueDefToCustomFieldValueDefModelMapper;
	}

	@Override
	public void map(TFrom source, TTo target, MappingContext context) {
		if (source == null || target == null)
			return;
		target.setDescription(source.getDescription());
		List<LocalizedDataDefModel> LocalizedDatasList = target.getLocalizedDataList();
		for (LocalizedDataDef item : source.getLocalizedDataList()) {
			LocalizedDatasList.add(getLocalizedDataDefToLocalizedDataDefModelMapper().map(item, context));
		}
		List<CustomFieldValueDefModel> CustomFieldValuesList = target.getCustomFieldValues();
		for (CustomFieldValueDef item : source.getCustomFieldValues()) {
			CustomFieldValuesList.add(getCustomFieldValueDefToCustomFieldValueDefModelMapper().map(item, context));
		}

		target.setId(source.getId());
		target.setName(source.getName());

		if (context instanceof BaseDefModelMappingContext) {
			BaseDefModelMappingContext baseDefMappingContext = (BaseDefModelMappingContext) context;
			for (int i = 0; i < source.getLocalizedDataList().size(); i++) {
				if (target.getLocalizedDataList().get(i) != null && source.getLocalizedDataList().get(i) != null
						&& source.getLocalizedDataList().get(i).getLocale() != null)
					target.getLocalizedDataList().get(i).setLocale(baseDefMappingContext.getLocaleModels()
							.get(source.getLocalizedDataList().get(i).getLocale().getId()));
			}
			for (int i = 0; i < source.getCustomFieldValues().size(); i++) {
				if (target.getCustomFieldValues().get(i) != null && source.getCustomFieldValues().get(i) != null
						&& source.getCustomFieldValues().get(i).getCustomField() != null)
					target.getCustomFieldValues().get(i).setCustomField(baseDefMappingContext.getCustomFieldModels()
							.get(source.getCustomFieldValues().get(i).getCustomField().getId()));
			}
		}
	}

	@Override
	public void mapBack(TTo source, TFrom target, MappingContext context) {
		if (source == null || target == null)
			return;
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		List<LocalizedDataDef> LocalizedDatasList = target.getLocalizedDataList();
		for (LocalizedDataDefModel item : source.getLocalizedDataList()) {
			LocalizedDatasList.add(getLocalizedDataDefToLocalizedDataDefModelMapper().mapBack(item, context));
		}
		List<CustomFieldValueDef> CustomFieldValuesList = target.getCustomFieldValues();
		for (CustomFieldValueDefModel item : source.getCustomFieldValues()) {
			if (item != null && item.getCustomField() != null
					&& item.getCustomField().getOverDefClass() == target.getClass())
				CustomFieldValuesList
						.add(getCustomFieldValueDefToCustomFieldValueDefModelMapper().mapBack(item, context));
		}
		target.setName(source.getName());
		if (context instanceof BaseDefModelMappingContext) {
			BaseDefModelMappingContext baseDefMappingContext = (BaseDefModelMappingContext) context;
			for (int i = 0; i < source.getLocalizedDataList().size(); i++) {
				if (target.getLocalizedDataList().get(i) != null && source.getLocalizedDataList().get(i) != null
						&& source.getLocalizedDataList().get(i).getLocale() != null)
					target.getLocalizedDataList().get(i).setLocale(baseDefMappingContext.getLocales()
							.get(source.getLocalizedDataList().get(i).getLocale().getId()));
			}
			for (int i = 0; i < source.getCustomFieldValues().size(); i++) {
				if (target.getCustomFieldValues().get(i) != null && source.getCustomFieldValues().get(i) != null
						&& source.getCustomFieldValues().get(i).getCustomField() != null)
					target.getCustomFieldValues().get(i).setCustomField(baseDefMappingContext.getCustomFields()
							.get(source.getCustomFieldValues().get(i).getCustomField().getId()));
			}
		}
	}

}
