package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.LocaleDefDTO;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class LocaleDefToLocaleDefDTOMapper extends BaseDefToBaseDefDTOMapper<LocaleDef, LocaleDefDTO> {

	public LocaleDefToLocaleDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(LocaleDef source, LocaleDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setLanguage(source.getLanguage());
		target.setCountry(source.getCountry());
		target.setIsDefault(source.getIsDefault());
		super.map(source, target, context);
	}

	public void mapBack(LocaleDefDTO source, LocaleDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setLanguage(source.getLanguage());
		target.setCountry(source.getCountry());
		target.setIsDefault(source.getIsDefault());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<LocaleDefDTO> getToClass() {
		return LocaleDefDTO.class;
	}

	@Override
	protected Class<LocaleDef> getFromClass() {
		return LocaleDef.class;
	}
}
