package com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers;

import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.templateengine.contract.model.TemplateApplyConfig;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper extends MapperBase<TemplateProjectApplyConfig, TemplateProjectApplyConfigModel> {
	IMapperProvider mapperProvider;
	TemplateApplyConfigToTemplateApplyConfigModelMapper templateApplyConfigToTemplateApplyConfigModelMapper;

	public TemplateProjectApplyConfigToTemplateProjectApplyConfigModelMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public TemplateApplyConfigToTemplateApplyConfigModelMapper getTemplateApplyConfigToTemplateApplyConfigModelMapper() {
		if(templateApplyConfigToTemplateApplyConfigModelMapper==null)
			templateApplyConfigToTemplateApplyConfigModelMapper = mapperProvider.getMapper(TemplateApplyConfigToTemplateApplyConfigModelMapper.class);
		return templateApplyConfigToTemplateApplyConfigModelMapper;
	}

	public void  map(TemplateProjectApplyConfig source, TemplateProjectApplyConfigModel target, MappingContext context) {
		if(source==null || target == null) return;
		List<TemplateApplyConfigModel> TemplatesConfigList = target.getTemplatesConfig();
		for (TemplateApplyConfig item : source.getTemplatesConfig()) {
			TemplatesConfigList.add( getTemplateApplyConfigToTemplateApplyConfigModelMapper().map(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());

		target.setIsDefault(source.getIsDefault());

		target.setOutputPath(source.getOutputPath());
	}

	public void mapBack(TemplateProjectApplyConfigModel source, TemplateProjectApplyConfig target, MappingContext context) {
		if(source == null || target == null) return;
		List<TemplateApplyConfig> TemplatesConfigList = target.getTemplatesConfig();
		for (TemplateApplyConfigModel item : source.getTemplatesConfig()) {
			TemplatesConfigList.add( getTemplateApplyConfigToTemplateApplyConfigModelMapper().mapBack(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());

		target.setIsDefault(source.getIsDefault());

		target.setOutputPath(source.getOutputPath());
	}

	@Override
	protected Class<TemplateProjectApplyConfigModel> getToClass() {
		return TemplateProjectApplyConfigModel.class;
	}

	@Override
	protected Class<TemplateProjectApplyConfig> getFromClass() {
		return TemplateProjectApplyConfig.class;
	}
}
