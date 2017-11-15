package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class CustomFieldDefToCustomFieldDefModelMapper extends BaseDefMapperToBaseDefModelMapper<CustomFieldDef, CustomFieldDefModel> {

	public CustomFieldDefToCustomFieldDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void map(CustomFieldDef source, CustomFieldDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setDataType(source.getDataType());
		target.setOverDefClass(source.getOverDefClass());
		target.setData(source.getData());
		target.setReference(source.getReference());
		target.setMultiline(source.getMultiline());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(CustomFieldDefModel source, CustomFieldDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setDataType(source.getDataType());
		target.setOverDefClass(source.getOverDefClass());
		target.setData(source.getData());
		target.setReference(source.getReference());
		target.setMultiline(source.getMultiline());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<CustomFieldDefModel> getToClass() {
		return CustomFieldDefModel.class;
	}

	@Override
	protected Class<CustomFieldDef> getFromClass() {
		return CustomFieldDef.class;
	}
}
