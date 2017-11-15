package com.diguits.domainmodeldesigner.template.modelmappers;

import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class TemplateProjectDefToTemplateProjectDefModelMapper extends MapperBase<TemplateProjectDef, TemplateProjectDefModel> {
	IMapperProvider mapperProvider;
	TemplateGroupDefToTemplateGroupDefModelMapper templateGroupDefToTemplateGroupDefModelMapper;

	public TemplateProjectDefToTemplateProjectDefModelMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public TemplateGroupDefToTemplateGroupDefModelMapper getTemplateGroupDefToTemplateGroupDefModelMapper() {
		if(templateGroupDefToTemplateGroupDefModelMapper==null)
			templateGroupDefToTemplateGroupDefModelMapper = mapperProvider.getMapper(TemplateGroupDefToTemplateGroupDefModelMapper.class);
		return templateGroupDefToTemplateGroupDefModelMapper;
	}

	public void  map(TemplateProjectDef source, TemplateProjectDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		List<TemplateGroupDefModel> GroupsList = target.getGroups();
		for (TemplateGroupDef item : source.getGroups()) {
			GroupsList.add( getTemplateGroupDefToTemplateGroupDefModelMapper().map(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
	}

	public void mapBack(TemplateProjectDefModel source, TemplateProjectDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		List<TemplateGroupDef> GroupsList = target.getGroups();
		for (TemplateGroupDefModel item : source.getGroups()) {
			GroupsList.add( getTemplateGroupDefToTemplateGroupDefModelMapper().mapBack(item, context));
		}
		target.setId(source.getId());
		target.setName(source.getName());
	}

	@Override
	protected Class<TemplateProjectDefModel> getToClass() {
		return TemplateProjectDefModel.class;
	}

	@Override
	protected Class<TemplateProjectDef> getFromClass() {
		return TemplateProjectDef.class;
	}
}
