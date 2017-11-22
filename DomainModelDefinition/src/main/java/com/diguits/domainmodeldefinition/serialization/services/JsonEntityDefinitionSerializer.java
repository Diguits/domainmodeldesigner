package com.diguits.domainmodeldefinition.serialization.services;

import com.diguits.common.mapping.IMapper;
import com.diguits.common.serialization.JsonSerializerMap;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.DomainModelDefDTO;
import com.diguits.domainmodeldefinition.serialization.mappers.IDomainModelDefMapper;
import com.google.inject.Inject;

public class JsonEntityDefinitionSerializer extends
        JsonSerializerMap<DomainModelDef, DomainModelDefDTO> implements IEntityDefinitionSerializer {
    @Inject
    public JsonEntityDefinitionSerializer(IDomainModelDefMapper domainModelDefMapper) {
        super(domainModelDefMapper);
    }

    @Override
    protected Class<DomainModelDefDTO> getDTOClass() {
        return DomainModelDefDTO.class;
    }
}
