package com.diguits.domainmodeldesigner.template.modelmappers;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.templatedefinition.definitions.TemplateParameterDef;
import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.google.inject.Inject;

public class TemplateProjectModelMapper implements ITemplateProjectModelMapper{

	private IMapperProvider mapperProvider;

	@Inject
	public TemplateProjectModelMapper(IMapperProvider mapperProvider) {
		super();
		this.mapperProvider = mapperProvider;
	}

	@Override
	public TemplateProjectDefModel map(TemplateProjectDef source) {
		TemplateProjectDefToTemplateProjectDefModelMapper projectMapper = new TemplateProjectDefToTemplateProjectDefModelMapper(mapperProvider);
		TemplateProjectDefModel target = projectMapper.map(source, null);
		return innerMap(source, target);
	}

	protected TemplateProjectDefModel innerMap(TemplateProjectDef source, TemplateProjectDefModel target) {
		for (TemplateGroupDefModel group : target.getGroups()) {
			for (TemplateDefModel template : group.getTemplates()) {
				for (TemplateParameterDefModel param : template.getParameters()) {
					param.setTemplateOwner(template);
				}
				template.setGroupOwner(group);
			}
			group.setProjectOwner(target);
		}
		return target;
	}

	@Override
	public void map(TemplateProjectDef source, TemplateProjectDefModel target) {
		TemplateProjectDefToTemplateProjectDefModelMapper projectMapper = new TemplateProjectDefToTemplateProjectDefModelMapper(mapperProvider);
		projectMapper.map(source, target, null);
		innerMap(source, target);
	}

	@Override
	public TemplateProjectDef mapBack(TemplateProjectDefModel source) {
		TemplateProjectDefToTemplateProjectDefModelMapper projectMapper = new TemplateProjectDefToTemplateProjectDefModelMapper(mapperProvider);
		TemplateProjectDef target = projectMapper.mapBack(source, null);
		return innerMapBack(source, target);
	}

	protected TemplateProjectDef innerMapBack(TemplateProjectDefModel source, TemplateProjectDef target) {
		for (TemplateGroupDef group : target.getGroups()) {
			for (TemplateDef template : group.getTemplates()) {
				for (TemplateParameterDef param : template.getParameters()) {
					param.setTemplateOwner(template);
				}
				template.setGroupOwner(group);
			}
			group.setProjectOwner(target);
		}
		return target;
	}

	@Override
	public void mapBack(TemplateProjectDefModel source, TemplateProjectDef target) {
		TemplateProjectDefToTemplateProjectDefModelMapper projectMapper = new TemplateProjectDefToTemplateProjectDefModelMapper(mapperProvider);
		projectMapper.mapBack(source, target, null);
		innerMapBack(source, target);
	}
}
