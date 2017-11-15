package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.NamedDefDTO;
import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.IndexDefDTO;
import com.diguits.domainmodeldefinition.definitions.IndexDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class IndexDefToIndexDefDTOMapper extends BaseDefToBaseDefDTOMapper<IndexDef, IndexDefDTO> {
	FieldDefToNamedDefDTOMapper fieldDefToNamedDefDTOMapper;

	public IndexDefToIndexDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FieldDefToNamedDefDTOMapper getFieldDefToNamedDefDTOMapper() {
		if(fieldDefToNamedDefDTOMapper==null)
			fieldDefToNamedDefDTOMapper = mapperProvider.getMapper(FieldDefToNamedDefDTOMapper.class);
		return fieldDefToNamedDefDTOMapper;
	}

	public void  map(IndexDef source, IndexDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		List<NamedDefDTO> FieldsList = target.getFields();
		for (FieldDef item : source.getFields()) {
			FieldsList.add( getFieldDefToNamedDefDTOMapper().map(item, context));
		}
		target.setUnique(source.getUnique());
		super.map(source, target, context);
	}

	public void mapBack(IndexDefDTO source, IndexDef target, MappingContext context) {
		if(source == null || target == null) return;
		List<FieldDef> FieldsList = target.getFields();
		for (NamedDefDTO item : source.getFields()) {
			FieldsList.add( getFieldDefToNamedDefDTOMapper().mapBack(item, context));
		}
		target.setUnique(source.getUnique());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<IndexDefDTO> getToClass() {
		return IndexDefDTO.class;
	}

	@Override
	protected Class<IndexDef> getFromClass() {
		return IndexDef.class;
	}
}
