package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class LocaleDefToLocaleDefModelMapper extends BaseDefMapperToBaseDefModelMapper<LocaleDef, LocaleDefModel> {

	public LocaleDefToLocaleDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public void  map(LocaleDef source, LocaleDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setLanguage(source.getLanguage());
		target.setCountry(source.getCountry());
		target.setIsDefault(source.getIsDefault());
		super.map(source, target, context);
	}

	public void mapBack(LocaleDefModel source, LocaleDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setLanguage(source.getLanguage());
		target.setCountry(source.getCountry());
		target.setIsDefault(source.getIsDefault());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<LocaleDefModel> getToClass() {
		return LocaleDefModel.class;
	}

	@Override
	protected Class<LocaleDef> getFromClass() {
		return LocaleDef.class;
	}
}
