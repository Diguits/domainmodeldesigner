package com.diguits.domainmodeldesigner.template.modelmappers;

import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateParameterDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.common.mapping.MapperBase;

import java.util.List;

import com.diguits.common.mapping.IMapperProvider;

public class TemplateDefToTemplateDefModelMapper extends MapperBase<TemplateDef, TemplateDefModel> {
	IMapperProvider mapperProvider;
	TemplateParameterDefToTemplateParameterDefModelMapper templateParameterDefToTemplateParameterDefModelMapper;

	public TemplateDefToTemplateDefModelMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public TemplateParameterDefToTemplateParameterDefModelMapper getTemplateParameterDefToTemplateParameterDefModelMapper() {
		if(templateParameterDefToTemplateParameterDefModelMapper==null)
			templateParameterDefToTemplateParameterDefModelMapper = mapperProvider.getMapper(TemplateParameterDefToTemplateParameterDefModelMapper.class);
		return templateParameterDefToTemplateParameterDefModelMapper;
	}

	public void map(TemplateDef source, TemplateDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setPath(source.getPath());
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		target.setOutputFileNameExpression(source.getOutputFileNameExpression());
		if (source.getClassNamesToApplyOver() != null) {
			target.getClassNamesToApplyOver().clear();
			for (String applyClassName : source.getClassNamesToApplyOver()) {
				target.getClassNamesToApplyOver().add(applyClassName);
			}
		}
		target.setId(source.getId());
		target.setSource(source.getSource());
		target.setName(source.getName());
		List<TemplateParameterDefModel> parametersList = target.getParameters();
		for (TemplateParameterDef item : source.getParameters()) {
			parametersList.add( getTemplateParameterDefToTemplateParameterDefModelMapper().map(item, context));
		}
	}

	public void mapBack(TemplateDefModel source, TemplateDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setPath(source.getPath());
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		target.setOutputFileNameExpression(source.getOutputFileNameExpression());
		if (source.getClassNamesToApplyOver() != null) {
			target.getClassNamesToApplyOver().clear();
			for (String applyClassName : source.getClassNamesToApplyOver()) {
				target.getClassNamesToApplyOver().add(applyClassName);
			}
		}
		target.setId(source.getId());
		target.setSource(source.getSource());
		target.setName(source.getName());
		target.setName(source.getName());
		List<TemplateParameterDef> parametersList = target.getParameters();
		for (TemplateParameterDefModel item : source.getParameters()) {
			parametersList.add( getTemplateParameterDefToTemplateParameterDefModelMapper().mapBack(item, context));
		}
	}

	@Override
	protected Class<TemplateDefModel> getToClass() {
		return TemplateDefModel.class;
	}

	@Override
	protected Class<TemplateDef> getFromClass() {
		return TemplateDef.class;
	}
}
