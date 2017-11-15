package com.diguits.templatedefinition.serialization.mappers;

import com.diguits.templatedefinition.definitions.TemplateParameterDef;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateParameterDefDTO;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.MapperBase;
import com.diguits.common.mapping.IMapperProvider;

public class TemplateParameterDefToTemplateParameterDefDTOMapper extends MapperBase<TemplateParameterDef, TemplateParameterDefDTO> {
    IMapperProvider mapperProvider;
    TemplateDefToTemplateDefDTOMapper templateDefToTemplateDefDTOMapper;

    public TemplateParameterDefToTemplateParameterDefDTOMapper(IMapperProvider mapperProvider) {
        this.mapperProvider = mapperProvider;
    }

    public TemplateDefToTemplateDefDTOMapper getTemplateDefToTemplateDefDTOMapper() {
        if (templateDefToTemplateDefDTOMapper == null)
            templateDefToTemplateDefDTOMapper = mapperProvider.getMapper(TemplateDefToTemplateDefDTOMapper.class);
        return templateDefToTemplateDefDTOMapper;
    }

    public void map(TemplateParameterDef source, TemplateParameterDefDTO target, MappingContext context) {
        if (source == null || target == null) return;
        target.setActive(source.getActive());
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setName(source.getName());
        if (source.getOwner() != null)
            target.setOwnerId(source.getOwner().getId());
        target.setDataType(source.getDataType());
        target.setReference(source.getReference());
        target.setData(source.getData());
        target.setMultiline(source.getMultiline());
    }

    public void mapBack(TemplateParameterDefDTO source, TemplateParameterDef target, MappingContext context) {
        if (source == null || target == null) return;
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
    protected Class<TemplateParameterDefDTO> getToClass() {
        return TemplateParameterDefDTO.class;
    }

    @Override
    protected Class<TemplateParameterDef> getFromClass() {
        return TemplateParameterDef.class;
    }
}
