package com.diguits.templateengine.serialization.mappers;

import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;
import com.diguits.templateengine.contract.model.TemplateParamValue;
import com.diguits.templateengine.serialization.dtomodel.TemplateParamValueDTO;

public class TemplateParamValueToTemplateParamValueDTOMapper
		extends MapperBase<TemplateParamValue, TemplateParamValueDTO> {
	IMapperProvider mapperProvider;

	public TemplateParamValueToTemplateParamValueDTOMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public void map(TemplateParamValue source, TemplateParamValueDTO target, MappingContext context) {
		if (source == null || target == null)
			return;
		target.setTemplateParameter(source.getTemplateParameter());
		target.setValue(source.getValue());
	}

	public void mapBack(TemplateParamValueDTO source, TemplateParamValue target,
			MappingContext context) {
		if (source == null || target == null)
			return;
		target.setTemplateParameter(source.getTemplateParameter());
		target.setValue(source.getValue());
	}

	@Override
	protected Class<TemplateParamValueDTO> getToClass() {
		return TemplateParamValueDTO.class;
	}

	@Override
	protected Class<TemplateParamValue> getFromClass() {
		return TemplateParamValue.class;
	}
}
