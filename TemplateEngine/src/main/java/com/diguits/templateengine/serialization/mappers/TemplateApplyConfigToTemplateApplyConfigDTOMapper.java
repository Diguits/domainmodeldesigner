package com.diguits.templateengine.serialization.mappers;

import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.MapperBase;

import java.util.List;
import java.util.UUID;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.diguits.templateengine.contract.model.TemplateParamValue;
import com.diguits.templateengine.serialization.dtomodel.TemplateApplyConfigDTO;
import com.diguits.templateengine.serialization.dtomodel.TemplateParamValueDTO;
import com.google.inject.Inject;

public class TemplateApplyConfigToTemplateApplyConfigDTOMapper
		extends MapperBase<TemplateApplyConfig, TemplateApplyConfigDTO> {
	IMapperProvider mapperProvider;
	TemplateParamValueToTemplateParamValueDTOMapper templateParamValueToTemplateParamValueDTOMapper;

	@Inject
	public TemplateApplyConfigToTemplateApplyConfigDTOMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public TemplateParamValueToTemplateParamValueDTOMapper getTemplateParamValueToTemplateParamValueDTOMapper() {
		if (templateParamValueToTemplateParamValueDTOMapper == null)
			templateParamValueToTemplateParamValueDTOMapper = mapperProvider
					.getMapper(TemplateParamValueToTemplateParamValueDTOMapper.class);
		return templateParamValueToTemplateParamValueDTOMapper;
	}

	public TemplateApplyConfigDTO map(TemplateApplyConfig source, MappingContext context) {
		if (source == null)
			return null;
		TemplateApplyConfigDTO target = new TemplateApplyConfigDTO();
		map(source, target, context);
		return target;
	}

	public void map(TemplateApplyConfig source, TemplateApplyConfigDTO target, MappingContext context) {
		if (source == null || target == null)
			return;
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());

		target.setRootOutputDir(source.getRootOutputDir());
		target.setWaitForBefore(source.getWaitForBefore());
		target.setOutputPathExpression(source.getOutputPathExpression());
		target.setInMemory(source.getInMemory());
		target.setConditionExpression(source.getConditionExpression());
		target.setOutputFilenameExpression(source.getOutputFilenameExpression());
		if (source.getModelIds() != null) {
			target.getModelIds().clear();
			for (UUID modelId : source.getModelIds()) {
				target.getModelIds().add(modelId);
			}
		}
		target.setTemplateId(source.getTemplateId());
		if (source.getOwner() != null)
			target.setOwnerId(source.getOwner().getId());
		List<TemplateParamValueDTO> TemplatesConfigList = target.getParamValues();
		for (TemplateParamValue item : source.getParamValues()) {
			TemplatesConfigList.add(getTemplateParamValueToTemplateParamValueDTOMapper().map(item, context));
		}
	}

	public TemplateApplyConfig mapBack(TemplateApplyConfigDTO source, MappingContext context) {
		if (source == null)
			return null;
		TemplateApplyConfig target = new TemplateApplyConfig();
		mapBack(source, target, context);
		return target;
	}

	public void mapBack(TemplateApplyConfigDTO source, TemplateApplyConfig target, MappingContext context) {
		if (source == null || target == null)
			return;
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());

		target.setRootOutputDir(source.getRootOutputDir());
		target.setWaitForBefore(source.getWaitForBefore());
		target.setOutputPathExpression(source.getOutputPathExpression());
		target.setInMemory(source.getInMemory());
		target.setTemplateId(source.getTemplateId());
		target.setConditionExpression(source.getConditionExpression());
		target.setOutputFilenameExpression(source.getOutputFilenameExpression());
		if (source.getModelIds() != null) {
			target.getModelIds().clear();
			for (UUID modelId : source.getModelIds()) {
				target.getModelIds().add(modelId);
			}
		}
		target.setTemplateId(source.getTemplateId());

		List<TemplateParamValue> TemplatesConfigList = target.getParamValues();
		for (TemplateParamValueDTO item : source.getParamValues()) {
			TemplatesConfigList.add(getTemplateParamValueToTemplateParamValueDTOMapper().mapBack(item, context));
		}
	}

	@Override
	protected Class<TemplateApplyConfigDTO> getToClass() {
		return TemplateApplyConfigDTO.class;
	}

	@Override
	protected Class<TemplateApplyConfig> getFromClass() {
		return TemplateApplyConfig.class;
	}
}
