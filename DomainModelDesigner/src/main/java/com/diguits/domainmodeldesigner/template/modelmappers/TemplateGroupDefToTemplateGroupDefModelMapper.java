package com.diguits.domainmodeldesigner.template.modelmappers;

import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class TemplateGroupDefToTemplateGroupDefModelMapper extends MapperBase<TemplateGroupDef, TemplateGroupDefModel> {
	IMapperProvider mapperProvider;
	TemplateDefToTemplateDefModelMapper templateDefToTemplateDefModelMapper;

	public TemplateGroupDefToTemplateGroupDefModelMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public TemplateDefToTemplateDefModelMapper getTemplateDefToTemplateDefModelMapper() {
		if(templateDefToTemplateDefModelMapper==null)
			templateDefToTemplateDefModelMapper = mapperProvider.getMapper(TemplateDefToTemplateDefModelMapper.class);
		return templateDefToTemplateDefModelMapper;
	}

	public void  map(TemplateGroupDef source, TemplateGroupDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		List<TemplateDefModel> TemplatesList = target.getTemplates();
		for (TemplateDef item : source.getTemplates()) {
			TemplatesList.add( getTemplateDefToTemplateDefModelMapper().map(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
	}

	public void mapBack(TemplateGroupDefModel source, TemplateGroupDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		List<TemplateDef> TemplatesList = target.getTemplates();
		for (TemplateDefModel item : source.getTemplates()) {
			TemplatesList.add( getTemplateDefToTemplateDefModelMapper().mapBack(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
	}

	@Override
	protected Class<TemplateGroupDefModel> getToClass() {
		return TemplateGroupDefModel.class;
	}

	@Override
	protected Class<TemplateGroupDef> getFromClass() {
		return TemplateGroupDef.class;
	}
}
