package com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers;

import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateParamValueModel;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;
import com.diguits.templateengine.contract.model.TemplateParamValue;

public class TemplateParamValueToTemplateParamValueModelMapper
		extends MapperBase<TemplateParamValue, TemplateParamValueModel> {
	IMapperProvider mapperProvider;

	public TemplateParamValueToTemplateParamValueModelMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public void map(TemplateParamValue source, TemplateParamValueModel target, MappingContext context) {
		if (source == null || target == null)
			return;
		target.setValue(source.getValue());
	}

	public void mapBack(TemplateParamValueModel source, TemplateParamValue target, MappingContext context) {
		if (source == null || target == null)
			return;
		if (source.getTemplateParameter() != null)
			target.setTemplateParameter(source.getTemplateParameter().getId());
		target.setValue(source.getValue());
	}

	@Override
	protected Class<TemplateParamValueModel> getToClass() {
		return TemplateParamValueModel.class;
	}

	@Override
	protected Class<TemplateParamValue> getFromClass() {
		return TemplateParamValue.class;
	}
}
