package com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers;

import com.google.inject.Inject;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.diguits.common.mapping.MapperBase;

import java.util.UUID;

import com.diguits.common.mapping.IMapperProvider;

public class TemplateApplyConfigToTemplateApplyConfigModelMapper extends MapperBase<TemplateApplyConfig, TemplateApplyConfigModel> {
	IMapperProvider mapperProvider;

	@Inject
	public TemplateApplyConfigToTemplateApplyConfigModelMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public void map(TemplateApplyConfig source, TemplateApplyConfigModel target, MappingContext context) {
		if(source==null || target == null) return;
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
	}

	public void mapBack(TemplateApplyConfigModel source, TemplateApplyConfig target, MappingContext context) {
		if(source == null || target == null) return;
		target.setRootOutputDir(source.getRootOutputDir());
		target.setWaitForBefore(source.getWaitForBefore());
		target.setOutputPathExpression(source.getOutputPathExpression());
		target.setInMemory(source.getInMemory());
		target.setTemplateId(source.getTemplate().getId());
		target.setConditionExpression(source.getConditionExpression());
		target.setOutputFilenameExpression(source.getOutputFilenameExpression());
		if (source.getModelIds() != null) {
			target.getModelIds().clear();
			for (UUID modelId : source.getModelIds()) {
				target.getModelIds().add(modelId);
			}
		}
	}

	@Override
	protected Class<TemplateApplyConfigModel> getToClass() {
		return TemplateApplyConfigModel.class;
	}

	@Override
	protected Class<TemplateApplyConfig> getFromClass() {
		return TemplateApplyConfig.class;
	}
}
