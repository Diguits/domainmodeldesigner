package com.diguits.domainmodeldefinition.serialization.services;

import com.diguits.common.serialization.SimpleXMLSerializerMap;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.DomainModelDefDTO;
import com.diguits.domainmodeldefinition.serialization.mappers.IDomainModelDefMapper;
import com.google.inject.Inject;

public class SimpleXMLEntityDefinitionSerializer extends
		SimpleXMLSerializerMap<DomainModelDef, DomainModelDefDTO> implements IEntityDefinitionSerializer{

	@Inject
	public SimpleXMLEntityDefinitionSerializer(IDomainModelDefMapper domainModelDefMapper) {
		super(domainModelDefMapper);
	}

	@Override
	protected Class<DomainModelDefDTO> getDTOClass() {
		return DomainModelDefDTO.class;
	}
}