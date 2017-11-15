package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.FieldSubgroupDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldSubgroupDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldGroupDefDTO;
import com.diguits.domainmodeldefinition.definitions.FieldGroupDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FieldGroupDefToFieldGroupDefDTOMapper extends BaseDefToBaseDefDTOMapper<FieldGroupDef, FieldGroupDefDTO> {

	FieldSubgroupDefToFieldSubgroupDefDTOMapper fieldSubgroupDefToFieldSubgroupDefDTOMapper;

	public FieldGroupDefToFieldGroupDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FieldSubgroupDefToFieldSubgroupDefDTOMapper getFieldSubgroupDefToFieldSubgroupDefDTOMapper() {
		if(fieldSubgroupDefToFieldSubgroupDefDTOMapper==null)
			fieldSubgroupDefToFieldSubgroupDefDTOMapper = mapperProvider.getMapper(FieldSubgroupDefToFieldSubgroupDefDTOMapper.class);
		return fieldSubgroupDefToFieldSubgroupDefDTOMapper;
	}

	public void  map(FieldGroupDef source, FieldGroupDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		List<FieldSubgroupDefDTO> SubgroupsList = target.getSubgroups();
		for (FieldSubgroupDef item : source.getSubgroups()) {
			SubgroupsList.add( getFieldSubgroupDefToFieldSubgroupDefDTOMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	public void mapBack(FieldGroupDefDTO source, FieldGroupDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		List<FieldSubgroupDef> SubgroupsList = target.getSubgroups();
		for (FieldSubgroupDefDTO item : source.getSubgroups()) {
			SubgroupsList.add( getFieldSubgroupDefToFieldSubgroupDefDTOMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldGroupDefDTO> getToClass() {
		return FieldGroupDefDTO.class;
	}

	@Override
	protected Class<FieldGroupDef> getFromClass() {
		return FieldGroupDef.class;
	}
}
