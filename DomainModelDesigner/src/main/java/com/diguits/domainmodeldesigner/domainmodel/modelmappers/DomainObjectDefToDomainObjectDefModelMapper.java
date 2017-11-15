package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.DomainObjectDef;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;
import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldefinition.definitions.FieldGroupDef;
import com.diguits.domainmodeldefinition.definitions.IndexDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public abstract class DomainObjectDefToDomainObjectDefModelMapper<TFrom extends DomainObjectDef, TTo extends DomainObjectDefModel> extends SimpleDomainObjectDefToSimpleDomainObjectDefModelMapper<TFrom, TTo> {

	IndexDefToIndexDefModelMapper indexDefToIndexDefModelMapper;
	FieldGroupDefToFieldGroupDefModelMapper fieldGroupDefToFieldGroupDefModelMapper;
	FieldDefToFieldDefModelMapper fieldDefToFieldDefModelMapper;

	public DomainObjectDefToDomainObjectDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public IndexDefToIndexDefModelMapper getIndexDefToIndexDefModelMapper() {
		if(indexDefToIndexDefModelMapper==null)
			indexDefToIndexDefModelMapper = mapperProvider.getMapper(IndexDefToIndexDefModelMapper.class);
		return indexDefToIndexDefModelMapper;
	}
	public FieldDefToFieldDefModelMapper getFieldDefToFieldDefModelMapper() {
		if(fieldDefToFieldDefModelMapper==null)
			fieldDefToFieldDefModelMapper = mapperProvider.getMapper(FieldDefToFieldDefModelMapper.class);
		return fieldDefToFieldDefModelMapper;
	}
	public FieldGroupDefToFieldGroupDefModelMapper getFieldGroupDefToFieldGroupDefModelMapper() {
		if(fieldGroupDefToFieldGroupDefModelMapper==null)
			fieldGroupDefToFieldGroupDefModelMapper = mapperProvider.getMapper(FieldGroupDefToFieldGroupDefModelMapper.class);
		return fieldGroupDefToFieldGroupDefModelMapper;
	}

	@Override
	public void map(TFrom source, TTo target, MappingContext context) {
		if(source==null || target == null) return;
		target.setTableName(source.getTableName());
		List<IndexDefModel> IndexesList = target.getIndexes();
		for (IndexDef item : source.getIndexes()) {
			IndexesList.add( getIndexDefToIndexDefModelMapper().map(item, context));
		}
		List<FieldDefModel> FieldsList = target.getFields();
		for (FieldDef item : source.getFields()) {
			FieldsList.add( getFieldDefToFieldDefModelMapper().map(item, context));
		}
		List<FieldGroupDefModel> FieldGroupsList = target.getFieldGroups();
		for (FieldGroupDef item : source.getFieldGroups()) {
			FieldGroupsList.add( getFieldGroupDefToFieldGroupDefModelMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	@Override
	public void mapBack(TTo source, TFrom target, MappingContext context) {
		if(source == null || target == null) return;
		target.setTableName(source.getTableName());
		List<IndexDef> IndexesList = target.getIndexes();
		for (IndexDefModel item : source.getIndexes()) {
			IndexesList.add( getIndexDefToIndexDefModelMapper().mapBack(item, context));
		}
		List<FieldDef> FieldsList = target.getFields();
		for (FieldDefModel item : source.getFields()) {
			FieldsList.add( getFieldDefToFieldDefModelMapper().mapBack(item, context));
		}
		List<FieldGroupDef> FieldGroupsList = target.getFieldGroups();
		for (FieldGroupDefModel item : source.getFieldGroups()) {
			FieldGroupsList.add( getFieldGroupDefToFieldGroupDefModelMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}
}
