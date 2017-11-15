package com.diguits.domainmodeldesigner.template.modelmappers;

import com.diguits.templatedefinition.definitions.TemplateParameterDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;

public class TemplateParameterDefToTemplateParameterDefModelMapper extends MapperBase<TemplateParameterDef, TemplateParameterDefModel> {
	IMapperProvider mapperProvider;
	TemplateDefToTemplateDefModelMapper templateDefToTemplateDefModelMapper;

	public TemplateParameterDefToTemplateParameterDefModelMapper(IMapperProvider mapperProvider) {
		this.mapperProvider = mapperProvider;
	}

	public TemplateDefToTemplateDefModelMapper getTemplateDefToTemplateDefModelMapper() {
		if(templateDefToTemplateDefModelMapper==null)
			templateDefToTemplateDefModelMapper = mapperProvider.getMapper(TemplateDefToTemplateDefModelMapper.class);
		return templateDefToTemplateDefModelMapper;
	}

	public void map(TemplateParameterDef source, TemplateParameterDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDataType(source.getDataType());
		target.setReference(source.getReference());
		target.setData(source.getData());
		target.setMultiline(source.getMultiline());
	}

	public void mapBack(TemplateParameterDefModel source, TemplateParameterDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setActive(source.getActive());
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDataType(source.getDataType());
		target.setReference(source.getReference());
		target.setData(source.getData());
		target.setMultiline(source.getMultiline());
	}

	@Override
	protected Class<TemplateParameterDefModel> getToClass() {
		return TemplateParameterDefModel.class;
	}

	@Override
	protected Class<TemplateParameterDef> getFromClass() {
		return TemplateParameterDef.class;
	}
}
