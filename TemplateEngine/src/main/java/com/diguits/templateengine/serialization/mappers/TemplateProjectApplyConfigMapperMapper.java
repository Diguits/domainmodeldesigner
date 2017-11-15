package com.diguits.templateengine.serialization.mappers;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;
import com.diguits.templateengine.serialization.dtomodel.TemplateProjectApplyConfigDTO;
import com.google.inject.Inject;

public class TemplateProjectApplyConfigMapperMapper implements ITemplateProjectApplyConfigMapper{

	private IMapperProvider mapperProvider;

	@Inject
	public TemplateProjectApplyConfigMapperMapper(IMapperProvider mapperProvider) {
		super();
		this.mapperProvider = mapperProvider;
	}

	@Override
	public TemplateProjectApplyConfigDTO map(TemplateProjectApplyConfig source) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper(mapperProvider);
		TemplateProjectApplyConfigDTO target = projectMapper.map(source, null);
		return innerMap(source, target);
	}

	private TemplateProjectApplyConfigDTO innerMap(TemplateProjectApplyConfig source, TemplateProjectApplyConfigDTO target) {
		return target;
	}

	@Override
	public void map(TemplateProjectApplyConfig source, TemplateProjectApplyConfigDTO target) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper(mapperProvider);
		projectMapper.map(source, target, null);
		innerMap(source, target);
	}

	@Override
	public TemplateProjectApplyConfig mapBack(TemplateProjectApplyConfigDTO source) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper(mapperProvider);
		TemplateProjectApplyConfig target = projectMapper.mapBack(source, null);
		return innerMapBack(source, target);
	}

	private TemplateProjectApplyConfig innerMapBack(TemplateProjectApplyConfigDTO source, TemplateProjectApplyConfig target) {
		for (TemplateApplyConfig config : target.getTemplatesConfig()) {
			config.setProjectOwner(target);
		}
		return target;
	}

	@Override
	public void mapBack(TemplateProjectApplyConfigDTO source, TemplateProjectApplyConfig target) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigDTOMapper(mapperProvider);
		projectMapper.mapBack(source, target, null);
		innerMapBack(source, target);
	}
}
