package com.diguits.templatedefinition.serialization.mappers;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.templatedefinition.definitions.TemplateParameterDef;
import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateProjectDefDTO;
import com.google.inject.Inject;

public class TemplateProjectMapper implements ITemplateProjectMapper {

    private IMapperProvider mapperProvider;

    @Inject
    public TemplateProjectMapper(IMapperProvider mapperProvider) {
        super();
        this.mapperProvider = mapperProvider;
    }

    @Override
    public TemplateProjectDefDTO map(TemplateProjectDef source) {
        TemplateProjectDefToTemplateProjectDefDTOMapper projectMapper = new TemplateProjectDefToTemplateProjectDefDTOMapper(mapperProvider);
        TemplateProjectDefDTO target = projectMapper.map(source, null);
        return innerMap(source, target);
    }

    private TemplateProjectDefDTO innerMap(TemplateProjectDef source, TemplateProjectDefDTO target) {
        return target;
    }

    @Override
    public void map(TemplateProjectDef source, TemplateProjectDefDTO target) {
        TemplateProjectDefToTemplateProjectDefDTOMapper projectMapper = new TemplateProjectDefToTemplateProjectDefDTOMapper(mapperProvider);
        projectMapper.map(source, target, null);
        innerMap(source, target);
    }

    @Override
    public TemplateProjectDef mapBack(TemplateProjectDefDTO source) {
        TemplateProjectDefToTemplateProjectDefDTOMapper projectMapper = new TemplateProjectDefToTemplateProjectDefDTOMapper(mapperProvider);
        TemplateProjectDef target = projectMapper.mapBack(source, null);
        return innerMapBack(source, target);
    }

    private TemplateProjectDef innerMapBack(TemplateProjectDefDTO source, TemplateProjectDef target) {
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
    public void mapBack(TemplateProjectDefDTO source, TemplateProjectDef target) {
        TemplateProjectDefToTemplateProjectDefDTOMapper projectMapper = new TemplateProjectDefToTemplateProjectDefDTOMapper(mapperProvider);
        projectMapper.mapBack(source, target, null);
        innerMapBack(source, target);
    }
}
