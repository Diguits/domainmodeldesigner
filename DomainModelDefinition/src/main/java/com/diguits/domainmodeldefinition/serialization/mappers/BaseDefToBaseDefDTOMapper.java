package com.diguits.domainmodeldefinition.serialization.mappers;

import java.util.List;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldValueDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.definitions.LocalizedDataDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.BaseDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.CustomFieldValueDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.LocalizedDataDefDTO;

public abstract class BaseDefToBaseDefDTOMapper<TFrom extends BaseDef, TTo extends BaseDefDTO> extends MapperBase<TFrom, TTo> {

	IMapperProvider mapperProvider;
	LocalizedDataDefToLocalizedDataDefDTOMapper localizedDataDefToLocalizedDataDefDTOMapper;
	CustomFieldValueDefToCustomFieldValueDefDTOMapper customFieldValueDefToCustomFieldValueDefDTOMapper;

	public LocalizedDataDefToLocalizedDataDefDTOMapper getLocalizedDataDefToLocalizedDataDefDTOMapper() {
		if(localizedDataDefToLocalizedDataDefDTOMapper==null)
			localizedDataDefToLocalizedDataDefDTOMapper = mapperProvider.getMapper(LocalizedDataDefToLocalizedDataDefDTOMapper.class);
		return localizedDataDefToLocalizedDataDefDTOMapper;
	}

	public CustomFieldValueDefToCustomFieldValueDefDTOMapper getCustomFieldValueDefToCustomFieldValueDefDTOMapper() {
		if(customFieldValueDefToCustomFieldValueDefDTOMapper==null)
			customFieldValueDefToCustomFieldValueDefDTOMapper = mapperProvider.getMapper(CustomFieldValueDefToCustomFieldValueDefDTOMapper.class);
		return customFieldValueDefToCustomFieldValueDefDTOMapper;
	}

	public BaseDefToBaseDefDTOMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public void  map(TFrom source, TTo target, MappingContext context) {
		if(source==null || target == null) return;
		target.setDescription(source.getDescription());
		if(source.getOwner()!=null)
			target.setOwnerId(source.getOwner().getId());
		target.setId(source.getId());
		List<LocalizedDataDefDTO> LocalizedDatasList = target.getLocalizedDataList();
		for (LocalizedDataDef item : source.getLocalizedDataList()) {
			LocalizedDatasList.add( getLocalizedDataDefToLocalizedDataDefDTOMapper().map(item, context));
		}
		List<CustomFieldValueDefDTO> CustomFieldValuesList = target.getCustomFieldValues();
		for (CustomFieldValueDef item : source.getCustomFieldValues()) {
			CustomFieldValuesList.add( getCustomFieldValueDefToCustomFieldValueDefDTOMapper().map(item, context));
		}
		target.setName(source.getName());
	}

	@Override
	public void mapBack(TTo source, TFrom target, MappingContext context) {
		if(source == null || target == null) return;
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		List<LocalizedDataDef> LocalizedDatasList = target.getLocalizedDataList();
		for (LocalizedDataDefDTO item : source.getLocalizedDataList()) {
			LocalizedDatasList.add( getLocalizedDataDefToLocalizedDataDefDTOMapper().mapBack(item, context));
		}
		List<CustomFieldValueDef> CustomFieldValuesList = target.getCustomFieldValues();
		for (CustomFieldValueDefDTO item : source.getCustomFieldValues()) {
			CustomFieldValuesList.add( getCustomFieldValueDefToCustomFieldValueDefDTOMapper().mapBack(item, context));
		}
		target.setName(source.getName());
		if (context instanceof BaseDefDTOMappingContext) {
			BaseDefDTOMappingContext baseDefMappingContext = (BaseDefDTOMappingContext) context;
			if (source instanceof BaseDefDTO) {
				BaseDefDTO sourceDTO = (BaseDefDTO) source;
				int i = sourceDTO.getLocalizedDataList().size() - 1;
				while (i >= 0) {
					if (target.getLocalizedDataList().get(i) != null && sourceDTO.getLocalizedDataList().get(i) != null) {
						LocaleDef locale = baseDefMappingContext.getLocales()
								.get(sourceDTO.getLocalizedDataList().get(i).getLocaleId());
						if(locale == null)
							target.getLocalizedDataList().remove(i);
						else
							target.getLocalizedDataList().get(i).setLocale(locale);
					}
					i--;
				}
				i = sourceDTO.getCustomFieldValues().size() - 1;
				while (i >= 0) {
					if (target.getCustomFieldValues().get(i) != null && sourceDTO.getCustomFieldValues().get(i) != null) {
						CustomFieldDef customField = baseDefMappingContext.getCustomFields()
								.get(sourceDTO.getCustomFieldValues().get(i).getCustomFieldId());
						if(customField == null || customField.getOverDefClass()==target.getClass())
							target.getCustomFieldValues().remove(i);
						else
							target.getCustomFieldValues().get(i).setCustomField(customField);
					}
					i--;
				}
			}
		}
	}
}
