package com.diguits.templatedefinition.serialization.mappers;

import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateParameterDef;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateDefDTO;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateParameterDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.MapperBase;

import java.util.List;

import com.diguits.common.mapping.IMapperProvider;

public class TemplateDefToTemplateDefDTOMapper extends MapperBase<TemplateDef, TemplateDefDTO> {
    IMapperProvider mapperProvider;
    TemplateParameterDefToTemplateParameterDefDTOMapper templateParameterDefToTemplateParameterDefDTOMapper;

    public TemplateDefToTemplateDefDTOMapper(IMapperProvider mapperProvider) {
        this.mapperProvider = mapperProvider;
    }

    public TemplateParameterDefToTemplateParameterDefDTOMapper getTemplateParameterDefToTemplateParameterDefDTOMapper() {
        if (templateParameterDefToTemplateParameterDefDTOMapper == null)
            templateParameterDefToTemplateParameterDefDTOMapper = mapperProvider
                    .getMapper(TemplateParameterDefToTemplateParameterDefDTOMapper.class);
        return templateParameterDefToTemplateParameterDefDTOMapper;
    }

    public void map(TemplateDef source, TemplateDefDTO target, MappingContext context) {
        if (source == null || target == null)
            return;
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
        target.setName(source.getName());
        if (source.getOwner() != null)
            target.setOwnerId(source.getOwner().getId());
        List<TemplateParameterDefDTO> parametersList = target.getParameters();
        for (TemplateParameterDef item : source.getParameters()) {
            parametersList.add(getTemplateParameterDefToTemplateParameterDefDTOMapper().map(item, context));
        }
    }

    public void mapBack(TemplateDefDTO source, TemplateDef target, MappingContext context) {
        if (source == null || target == null)
            return;
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
        target.setName(source.getName());
        List<TemplateParameterDef> parametersList = target.getParameters();
        for (TemplateParameterDefDTO item : source.getParameters()) {
            parametersList.add(getTemplateParameterDefToTemplateParameterDefDTOMapper().mapBack(item, context));
        }
    }

    @Override
    protected Class<TemplateDefDTO> getToClass() {
        return TemplateDefDTO.class;
    }

    @Override
    protected Class<TemplateDef> getFromClass() {
        return TemplateDef.class;
    }
}
