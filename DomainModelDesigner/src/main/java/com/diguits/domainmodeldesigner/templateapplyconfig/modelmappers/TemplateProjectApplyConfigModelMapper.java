package com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers;

import java.util.UUID;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.diguits.templateengine.contract.model.TemplateParamValue;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;
import com.google.inject.Inject;

public class TemplateProjectApplyConfigModelMapper implements ITemplateProjectApplyConfigModelMapper {

	private IMapperProvider mapperProvider;

	@Inject
	private TemplateProjectClientService projectClientService;

	@Inject
	public TemplateProjectApplyConfigModelMapper(IMapperProvider mapperProvider) {
		super();
		this.mapperProvider = mapperProvider;
	}

	@Override
	public TemplateProjectApplyConfigModel map(TemplateProjectApplyConfig source) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper(
				mapperProvider);
		TemplateProjectApplyConfigModel target = projectMapper.map(source, null);
		return innerMap(source, target, null);
	}

	protected TemplateProjectApplyConfigModel innerMap(TemplateProjectApplyConfig source,
			TemplateProjectApplyConfigModel target, MappingContext context) {
		for (int i = 0; i < source.getTemplatesConfig().size(); i++) {
			TemplateApplyConfig sourceTemplateApplyConfig = source.getTemplatesConfig().get(i);
			TemplateApplyConfigModel targetTemplateApplyConfigModel = target.getTemplatesConfig().get(i);
			targetTemplateApplyConfigModel.setProjectOwner(target);
			if (sourceTemplateApplyConfig.getTemplateId() != null) {
				TemplateDefModel template = projectClientService
						.findTemplate(sourceTemplateApplyConfig.getTemplateId());
				targetTemplateApplyConfigModel.setTemplate(template);
				if (template != null) {
					int j = sourceTemplateApplyConfig.getParamValues().size() - 1;
					while (j > 0) {
						TemplateParamValue paramValue = sourceTemplateApplyConfig.getParamValues().get(j);
						if (paramValue != null) {
							TemplateParameterDefModel templateParameter = findTemplateParameter(
									paramValue.getTemplateParameter(), template);
							if (templateParameter != null)
								targetTemplateApplyConfigModel.getTemplateParamValues().get(j)
										.setTemplateParameter(templateParameter);
							else
								targetTemplateApplyConfigModel.getTemplateParamValues().remove(j);
							break;
						}
						j--;
					}
				}
			}
		}
		return target;
	}

	protected TemplateParameterDefModel findTemplateParameter(UUID id, TemplateDefModel template) {
		for (TemplateParameterDefModel parameter : template.getParameters()) {
			if (parameter.getId() == id)
				return parameter;
		}
		return null;
	}

	@Override
	public void map(TemplateProjectApplyConfig source, TemplateProjectApplyConfigModel target) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper(
				mapperProvider);
		projectMapper.map(source, target, null);
		innerMap(source, target, null);
	}

	@Override
	public TemplateProjectApplyConfig mapBack(TemplateProjectApplyConfigModel source) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper(
				mapperProvider);
		TemplateProjectApplyConfig target = projectMapper.mapBack(source, null);
		return innerMapBack(source, target, null);
	}

	protected TemplateProjectApplyConfig innerMapBack(TemplateProjectApplyConfigModel source,
			TemplateProjectApplyConfig target, MappingContext context) {
		for (int i = 0; i < source.getTemplatesConfig().size(); i++) {
			TemplateApplyConfigModel sourceTemplateApplyConfigModel = source.getTemplatesConfig().get(i);
			TemplateApplyConfig targetTemplateApplyConfig = target.getTemplatesConfig().get(i);
			targetTemplateApplyConfig.setProjectOwner(target);
			if (sourceTemplateApplyConfigModel.getTemplate() != null)
				targetTemplateApplyConfig.setTemplateId(sourceTemplateApplyConfigModel.getTemplate().getId());
		}
		return target;
	}

	@Override
	public void mapBack(TemplateProjectApplyConfigModel source, TemplateProjectApplyConfig target) {
		TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper projectMapper = new TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper(
				mapperProvider);
		projectMapper.mapBack(source, target, null);
		innerMapBack(source, target, null);
	}
}
