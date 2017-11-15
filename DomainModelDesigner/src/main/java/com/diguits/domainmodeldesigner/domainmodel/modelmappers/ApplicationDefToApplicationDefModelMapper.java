package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.common.mapping.IMapperProvider;

public class ApplicationDefToApplicationDefModelMapper extends BaseDefMapperToBaseDefModelMapper<ApplicationDef, ApplicationDefModel> {

	public ApplicationDefToApplicationDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	@Override
	protected Class<ApplicationDefModel> getToClass() {
		return ApplicationDefModel.class;
	}

	@Override
	protected Class<ApplicationDef> getFromClass() {
		return ApplicationDef.class;
	}

}
