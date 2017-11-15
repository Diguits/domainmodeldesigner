package com.diguits.templateengine.serialization.mappers;

import java.util.List;

import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;
import com.diguits.templateengine.serialization.dtomodel.TemplateApplyConfigDTO;
import com.diguits.templateengine.serialization.dtomodel.TemplateProjectApplyConfigDTO;

public class TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper
		extends MapperBase<TemplateProjectApplyConfig, TemplateProjectApplyConfigDTO> {
	IMapperProvider mapperProvider;
	TemplateApplyConfigToTemplateApplyConfigDTOMapper templateApplyConfigToTemplateApplyConfigDTOMapper;

	public TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public TemplateApplyConfigToTemplateApplyConfigDTOMapper getTemplateApplyConfigToTemplateApplyConfigDTOMapper() {
		if (templateApplyConfigToTemplateApplyConfigDTOMapper == null)
			templateApplyConfigToTemplateApplyConfigDTOMapper = mapperProvider
					.getMapper(TemplateApplyConfigToTemplateApplyConfigDTOMapper.class);
		return templateApplyConfigToTemplateApplyConfigDTOMapper;
	}

	public void map(TemplateProjectApplyConfig source, TemplateProjectApplyConfigDTO target, MappingContext context) {
		if (source == null || target == null)
			return;
		List<TemplateApplyConfigDTO> TemplatesConfigList = target.getTemplatesConfig();
		for (TemplateApplyConfig item : source.getTemplatesConfig()) {
			TemplatesConfigList.add(getTemplateApplyConfigToTemplateApplyConfigDTOMapper().map(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());

		target.setIsDefault(source.getIsDefault());

		target.setOutputPath(source.getOutputPath());

	}

	public void mapBack(TemplateProjectApplyConfigDTO source, TemplateProjectApplyConfig target,
			MappingContext context) {
		if (source == null || target == null)
			return;
		List<TemplateApplyConfig> TemplatesConfigList = target.getTemplatesConfig();
		for (TemplateApplyConfigDTO item : source.getTemplatesConfig()) {
			TemplatesConfigList.add(getTemplateApplyConfigToTemplateApplyConfigDTOMapper().mapBack(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());

		target.setIsDefault(source.getIsDefault());

		target.setOutputPath(source.getOutputPath());
	}

	@Override
	protected Class<TemplateProjectApplyConfigDTO> getToClass() {
		return TemplateProjectApplyConfigDTO.class;
	}

	@Override
	protected Class<TemplateProjectApplyConfig> getFromClass() {
		return TemplateProjectApplyConfig.class;
	}
}
