package com.diguits.templateengine.serialization.service;

import com.diguits.common.serialization.SimpleXMLSerializerMap;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;
import com.diguits.templateengine.serialization.dtomodel.TemplateProjectApplyConfigDTO;
import com.diguits.templateengine.serialization.mappers.ITemplateProjectApplyConfigMapper;
import com.google.inject.Inject;

public class SimpleXMLTemplateProjectApplyConfigSerializer extends SimpleXMLSerializerMap<TemplateProjectApplyConfig, TemplateProjectApplyConfigDTO> implements ITemplateProjectApplyConfigSerializer{

	@Inject
	public SimpleXMLTemplateProjectApplyConfigSerializer(ITemplateProjectApplyConfigMapper mapper) {
		super(mapper);
	}

	@Override
	protected Class<TemplateProjectApplyConfigDTO> getDTOClass() {
		return TemplateProjectApplyConfigDTO.class;
	}
}
