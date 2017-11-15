package com.diguits.templatedefinition.serialization.mappers;

import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateProjectDefDTO;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateGroupDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;

import java.util.List;

public class TemplateProjectDefToTemplateProjectDefDTOMapper extends MapperBase<TemplateProjectDef, TemplateProjectDefDTO> {
    IMapperProvider mapperProvider;
    TemplateGroupDefToTemplateGroupDefDTOMapper templateGroupDefToTemplateGroupDefDTOMapper;

    public TemplateProjectDefToTemplateProjectDefDTOMapper(IMapperProvider mapperProvider) {
        this.mapperProvider = mapperProvider;
    }

    public TemplateGroupDefToTemplateGroupDefDTOMapper getTemplateGroupDefToTemplateGroupDefDTOMapper() {
        if (templateGroupDefToTemplateGroupDefDTOMapper == null)
            templateGroupDefToTemplateGroupDefDTOMapper = mapperProvider.getMapper(TemplateGroupDefToTemplateGroupDefDTOMapper.class);
        return templateGroupDefToTemplateGroupDefDTOMapper;
    }

    public void map(TemplateProjectDef source, TemplateProjectDefDTO target, MappingContext context) {
        if (source == null || target == null) return;
        target.setActive(source.getActive());
        target.setDescription(source.getDescription());
        List<TemplateGroupDefDTO> GroupsList = target.getGroups();
        for (TemplateGroupDef item : source.getGroups()) {
            GroupsList.add(getTemplateGroupDefToTemplateGroupDefDTOMapper().map(item, context));
        }
        target.setId(source.getId());
        target.setName(source.getName());
        if (source.getOwner() != null)
            target.setOwnerId(source.getOwner().getId());
    }

    public void mapBack(TemplateProjectDefDTO source, TemplateProjectDef target, MappingContext context) {
        if (source == null || target == null) return;
        target.setActive(source.getActive());
        target.setDescription(source.getDescription());
        List<TemplateGroupDef> GroupsList = target.getGroups();
        for (TemplateGroupDefDTO item : source.getGroups()) {
            GroupsList.add(getTemplateGroupDefToTemplateGroupDefDTOMapper().mapBack(item, context));
        }
        target.setId(source.getId());
        target.setName(source.getName());
    }

    @Override
    protected Class<TemplateProjectDefDTO> getToClass() {
        return TemplateProjectDefDTO.class;
    }

    @Override
    protected Class<TemplateProjectDef> getFromClass() {
        return TemplateProjectDef.class;
    }
}
