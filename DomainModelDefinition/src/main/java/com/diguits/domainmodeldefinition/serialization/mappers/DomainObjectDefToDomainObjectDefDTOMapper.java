package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.IndexDefDTO;
import com.diguits.domainmodeldefinition.definitions.DomainObjectDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FieldGroupDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.DomainObjectDefDTO;
import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldefinition.definitions.FieldGroupDef;
import com.diguits.domainmodeldefinition.definitions.IndexDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public abstract class DomainObjectDefToDomainObjectDefDTOMapper<TFrom extends DomainObjectDef, TTo extends DomainObjectDefDTO> extends SimpleDomainObjectDefToSimpleDomainObjectDefDTOMapper<TFrom, TTo> {

	IndexDefToIndexDefDTOMapper indexDefToIndexDefDTOMapper;
	FieldDefToFieldDefDTOMapper fieldDefToFieldDefDTOMapper;
	FieldGroupDefToFieldGroupDefDTOMapper fieldGroupDefToFieldGroupDefDTOMapper;

	public DomainObjectDefToDomainObjectDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public IndexDefToIndexDefDTOMapper getIndexDefToIndexDefDTOMapper() {
		if(indexDefToIndexDefDTOMapper==null)
			indexDefToIndexDefDTOMapper = mapperProvider.getMapper(IndexDefToIndexDefDTOMapper.class);
		return indexDefToIndexDefDTOMapper;
	}
	public FieldDefToFieldDefDTOMapper getFieldDefToFieldDefDTOMapper() {
		if(fieldDefToFieldDefDTOMapper==null)
			fieldDefToFieldDefDTOMapper = mapperProvider.getMapper(FieldDefToFieldDefDTOMapper.class);
		return fieldDefToFieldDefDTOMapper;
	}
	public FieldGroupDefToFieldGroupDefDTOMapper getFieldGroupDefToFieldGroupDefDTOMapper() {
		if(fieldGroupDefToFieldGroupDefDTOMapper==null)
			fieldGroupDefToFieldGroupDefDTOMapper = mapperProvider.getMapper(FieldGroupDefToFieldGroupDefDTOMapper.class);
		return fieldGroupDefToFieldGroupDefDTOMapper;
	}
	public void  map(TFrom source, TTo target, MappingContext context) {
		if(source==null || target == null) return;
		target.setTableName(source.getTableName());
		if(source.getModule()!=null)
			target.setModuleName(source.getModule().getName());
		if(source.getModule()!=null)
			target.setModuleId(source.getModule().getId());
		List<IndexDefDTO> IndexesList = target.getIndexes();
		for (IndexDef item : source.getIndexes()) {
			IndexesList.add( getIndexDefToIndexDefDTOMapper().map(item, context));
		}
		List<FieldDefDTO> FieldsList = target.getFields();
		for (FieldDef item : source.getFields()) {
			FieldsList.add( getFieldDefToFieldDefDTOMapper().map(item, context));
		}
		List<FieldGroupDefDTO> FieldGroupsList = target.getFieldGroups();
		for (FieldGroupDef item : source.getFieldGroups()) {
			FieldGroupsList.add( getFieldGroupDefToFieldGroupDefDTOMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	public void mapBack(TTo source, TFrom target, MappingContext context) {
		if(source == null || target == null) return;
		target.setTableName(source.getTableName());
		List<IndexDef> IndexesList = target.getIndexes();
		for (IndexDefDTO item : source.getIndexes()) {
			IndexesList.add( getIndexDefToIndexDefDTOMapper().mapBack(item, context));
		}
		List<FieldDef> FieldsList = target.getFields();
		for (FieldDefDTO item : source.getFields()) {
			FieldsList.add( getFieldDefToFieldDefDTOMapper().mapBack(item, context));
		}
		List<FieldGroupDef> FieldGroupsList = target.getFieldGroups();
		for (FieldGroupDefDTO item : source.getFieldGroups()) {
			FieldGroupsList.add( getFieldGroupDefToFieldGroupDefDTOMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}
}

