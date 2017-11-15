package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.CustomFieldDefDTO;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class CustomFieldDefToCustomFieldDefDTOMapper extends BaseDefToBaseDefDTOMapper<CustomFieldDef, CustomFieldDefDTO> {

	public CustomFieldDefToCustomFieldDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(CustomFieldDef source, CustomFieldDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setDataType(source.getDataType());
		target.setData(source.getData());
		target.setReference(source.getReference());
		target.setMultiline(source.getMultiline());
		super.map(source, target, context);
	}

	public void mapBack(CustomFieldDefDTO source, CustomFieldDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setDataType(source.getDataType());
		target.setData(source.getData());
		target.setReference(source.getReference());
		target.setMultiline(source.getMultiline());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<CustomFieldDefDTO> getToClass() {
		return CustomFieldDefDTO.class;
	}

	@Override
	protected Class<CustomFieldDef> getFromClass() {
		return CustomFieldDef.class;
	}
}
