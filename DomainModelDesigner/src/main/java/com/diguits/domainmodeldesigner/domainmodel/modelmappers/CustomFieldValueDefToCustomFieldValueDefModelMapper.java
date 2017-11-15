package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.CustomFieldValueDef;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class CustomFieldValueDefToCustomFieldValueDefModelMapper
		extends BaseDefMapperToBaseDefModelMapper<CustomFieldValueDef, CustomFieldValueDefModel> {

	public CustomFieldValueDefToCustomFieldValueDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void map(CustomFieldValueDef source, CustomFieldValueDefModel target, MappingContext context) {
		if (source == null || target == null)
			return;
		target.setValue(source.getValue());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(CustomFieldValueDefModel source, CustomFieldValueDef target, MappingContext context) {
		if (source == null || target == null)
			return;
		if (source.getValue() instanceof EnumValueDefModel)
			target.setValue(((EnumValueDefModel) source.getValue()).getId());
		else
			target.setValue(source.getValue());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<CustomFieldValueDefModel> getToClass() {
		return CustomFieldValueDefModel.class;
	}

	@Override
	protected Class<CustomFieldValueDef> getFromClass() {
		return CustomFieldValueDef.class;
	}
}
