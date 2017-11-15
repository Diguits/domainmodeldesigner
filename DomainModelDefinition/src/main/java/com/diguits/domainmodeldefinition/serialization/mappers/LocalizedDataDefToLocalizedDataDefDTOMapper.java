package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.LocalizedDataDefDTO;
import com.diguits.domainmodeldefinition.definitions.LocalizedDataDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class LocalizedDataDefToLocalizedDataDefDTOMapper extends BaseDefToBaseDefDTOMapper<LocalizedDataDef, LocalizedDataDefDTO> {

	public LocalizedDataDefToLocalizedDataDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(LocalizedDataDef source, LocalizedDataDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		if(source.getLocale()!=null)
			target.setLocaleId(source.getLocale().getId());
		target.setHint(source.getHint());
		target.setCaption(source.getCaption());
		target.setPlaceHolder(source.getPlaceHolder());
		target.setFormat(source.getFormat());
		super.map(source, target, context);
	}

	public void mapBack(LocalizedDataDefDTO source, LocalizedDataDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setHint(source.getHint());
		target.setCaption(source.getCaption());
		target.setPlaceHolder(source.getPlaceHolder());
		target.setFormat(source.getFormat());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<LocalizedDataDefDTO> getToClass() {
		return LocalizedDataDefDTO.class;
	}

	@Override
	protected Class<LocalizedDataDef> getFromClass() {
		return LocalizedDataDef.class;
	}
}
