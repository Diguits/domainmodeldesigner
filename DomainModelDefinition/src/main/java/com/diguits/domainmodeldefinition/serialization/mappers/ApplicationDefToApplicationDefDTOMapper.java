package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.serialization.dtomodel.NamedDefDTO;
import com.diguits.domainmodeldefinition.serialization.dtomodel.ApplicationDefDTO;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class ApplicationDefToApplicationDefDTOMapper extends BaseDefToBaseDefDTOMapper<ApplicationDef, ApplicationDefDTO> {

	BoundedContextDefToNamedDefDTOMapper boundedContextDefToNamedDefDTOMapper;

	public ApplicationDefToApplicationDefDTOMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public BoundedContextDefToNamedDefDTOMapper getBoundedContextDefToNamedDefDTOMapper() {
		if(boundedContextDefToNamedDefDTOMapper==null)
			boundedContextDefToNamedDefDTOMapper = mapperProvider.getMapper(BoundedContextDefToNamedDefDTOMapper.class);
		return boundedContextDefToNamedDefDTOMapper;
	}
	public ApplicationDefDTO map(ApplicationDef source, MappingContext context) {
		if(source==null) return null;
		ApplicationDefDTO target = new ApplicationDefDTO();
		map(source, target, context);
		return target;
	}

	public void  map(ApplicationDef source, ApplicationDefDTO target, MappingContext context) {
		if(source==null || target == null) return;
		List<NamedDefDTO> BoundedContextsList = target.getBoundedContexts();
		for (BoundedContextDef item : source.getBoundedContexts()) {
			BoundedContextsList.add( getBoundedContextDefToNamedDefDTOMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	public void mapBack(ApplicationDefDTO source, ApplicationDef target, MappingContext context) {
		if(source == null || target == null) return;
		List<BoundedContextDef> BoundedContextsList = target.getBoundedContexts();
		for (NamedDefDTO item : source.getBoundedContexts()) {
			BoundedContextsList.add( getBoundedContextDefToNamedDefDTOMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<ApplicationDefDTO> getToClass() {
		return ApplicationDefDTO.class;
	}

	@Override
	protected Class<ApplicationDef> getFromClass() {
		return ApplicationDef.class;
	}
}
