package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.CustomFieldValueDefDTO;
import com.diguits.domainmodeldefinition.definitions.CustomFieldValueDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class CustomFieldValueDefToCustomFieldValueDefDTOMapper extends BaseDefToBaseDefDTOMapper<CustomFieldValueDef, CustomFieldValueDefDTO> {
	public CustomFieldValueDefToCustomFieldValueDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(CustomFieldValueDef source, CustomFieldValueDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		if(source.getCustomField()!=null)
			target.setCustomFieldId(source.getCustomField().getId());
		target.setValue(source.getValue());
		super.map(source, target, context);
	}

	public void mapBack(CustomFieldValueDefDTO source, CustomFieldValueDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setValue(source.getValue());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<CustomFieldValueDefDTO> getToClass() {
		return CustomFieldValueDefDTO.class;
	}

	@Override
	protected Class<CustomFieldValueDef> getFromClass() {
		return CustomFieldValueDef.class;
	}
}
