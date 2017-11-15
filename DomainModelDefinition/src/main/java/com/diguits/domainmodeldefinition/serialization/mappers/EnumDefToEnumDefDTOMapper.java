package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.EnumValueDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EnumDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.EnumValueDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class EnumDefToEnumDefDTOMapper extends SimpleDomainObjectDefToSimpleDomainObjectDefDTOMapper<EnumDef, EnumDefDTO> {

	EnumValueDefToEnumValueDefDTOMapper enumValueDefToEnumValueDefDTOMapper;

	public EnumDefToEnumDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public EnumValueDefToEnumValueDefDTOMapper getEnumValueDefToEnumValueDefDTOMapper() {
		if(enumValueDefToEnumValueDefDTOMapper==null)
			enumValueDefToEnumValueDefDTOMapper = mapperProvider.getMapper(EnumValueDefToEnumValueDefDTOMapper.class);
		return enumValueDefToEnumValueDefDTOMapper;
	}

	public void  map(EnumDef source, EnumDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setUseIcon(source.getUseIcon());
		target.setUseColor(source.getUseColor());
		List<EnumValueDefDTO> ValuesList = target.getValues();
		for (EnumValueDef item : source.getValues()) {
			ValuesList.add( getEnumValueDefToEnumValueDefDTOMapper().map(item, context));
		}
		target.setUseTitle(source.getUseTitle());
		super.map(source, target, context);
	}

	public void mapBack(EnumDefDTO source, EnumDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setUseIcon(source.getUseIcon());
		target.setUseColor(source.getUseColor());
		List<EnumValueDef> ValuesList = target.getValues();
		for (EnumValueDefDTO item : source.getValues()) {
			ValuesList.add( getEnumValueDefToEnumValueDefDTOMapper().mapBack(item, context));
		}
		target.setUseTitle(source.getUseTitle());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<EnumDefDTO> getToClass() {
		return EnumDefDTO.class;
	}

	@Override
	protected Class<EnumDef> getFromClass() {
		return EnumDef.class;
	}
}
