package com.diguits.templatedefinition.serialization.services;

import com.diguits.common.serialization.SimpleXMLSerializerMap;
import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.diguits.templatedefinition.serialization.dtomodel.TemplateProjectDefDTO;
import com.diguits.templatedefinition.serialization.mappers.ITemplateProjectMapper;
import com.google.inject.Inject;

public class SimpleXMLTemplateProjectSerializer extends SimpleXMLSerializerMap<TemplateProjectDef, TemplateProjectDefDTO> implements ITemplateProjectSerializer {

    @Inject
    public SimpleXMLTemplateProjectSerializer(ITemplateProjectMapper mapper) {
        super(mapper);
    }

    @Override
    protected Class<TemplateProjectDefDTO> getDTOClass() {
        return TemplateProjectDefDTO.class;
    }
}
