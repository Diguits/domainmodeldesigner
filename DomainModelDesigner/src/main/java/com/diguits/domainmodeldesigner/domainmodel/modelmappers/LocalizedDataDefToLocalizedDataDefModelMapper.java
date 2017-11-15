package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.LocalizedDataDef;
import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;

public class LocalizedDataDefToLocalizedDataDefModelMapper extends BaseDefMapperToBaseDefModelMapper<LocalizedDataDef, LocalizedDataDefModel> {

	public LocalizedDataDefToLocalizedDataDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	public void  map(LocalizedDataDef source, LocalizedDataDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setHint(source.getHint());
		target.setCaption(source.getCaption());
		target.setPlaceHolder(source.getPlaceHolder());
		target.setFormat(source.getFormat());
		super.map(source, target, context);
	}

	@Override
	public void mapBack(LocalizedDataDefModel source, LocalizedDataDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setHint(source.getHint());
		target.setCaption(source.getCaption());
		target.setPlaceHolder(source.getPlaceHolder());
		target.setFormat(source.getFormat());
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<LocalizedDataDefModel> getToClass() {
		return LocalizedDataDefModel.class;
	}

	@Override
	protected Class<LocalizedDataDef> getFromClass() {
		return LocalizedDataDef.class;
	}
}
