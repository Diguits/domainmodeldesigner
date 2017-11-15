package com.diguits.templatedefinition.serialization.mappers;

import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateGroupDefDTO;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;

import java.util.List;

public class TemplateGroupDefToTemplateGroupDefDTOMapper extends MapperBase<TemplateGroupDef, TemplateGroupDefDTO> {
    IMapperProvider mapperProvider;
    TemplateDefToTemplateDefDTOMapper templateDefToTemplateDefDTOMapper;

    public TemplateGroupDefToTemplateGroupDefDTOMapper(IMapperProvider mapperProvider) {
        this.mapperProvider = mapperProvider;
    }

    public TemplateDefToTemplateDefDTOMapper getTemplateDefToTemplateDefDTOMapper() {
        if (templateDefToTemplateDefDTOMapper == null)
            templateDefToTemplateDefDTOMapper = mapperProvider.getMapper(TemplateDefToTemplateDefDTOMapper.class);
        return templateDefToTemplateDefDTOMapper;
    }

    public void map(TemplateGroupDef source, TemplateGroupDefDTO target, MappingContext context) {
        if (source == null || target == null) return;
        target.setActive(source.getActive());
        target.setDescription(source.getDescription());
        List<TemplateDefDTO> TemplatesList = target.getTemplates();
        for (TemplateDef item : source.getTemplates()) {
            TemplatesList.add(getTemplateDefToTemplateDefDTOMapper().map(item, context));
        }
        target.setId(source.getId());
        target.setName(source.getName());
        if (source.getOwner() != null)
            target.setOwnerId(source.getOwner().getId());
    }

    public void mapBack(TemplateGroupDefDTO source, TemplateGroupDef target, MappingContext context) {
        if (source == null || target == null) return;
        target.setActive(source.getActive());
        target.setDescription(source.getDescription());
        List<TemplateDef> TemplatesList = target.getTemplates();
        for (TemplateDefDTO item : source.getTemplates()) {
            TemplatesList.add(getTemplateDefToTemplateDefDTOMapper().mapBack(item, context));
        }
        target.setId(source.getId());
        target.setName(source.getName());
    }

    @Override
    protected Class<TemplateGroupDefDTO> getToClass() {
        return TemplateGroupDefDTO.class;
    }

    @Override
    protected Class<TemplateGroupDef> getFromClass() {
        return TemplateGroupDef.class;
    }
}
