package com.diguits.domainmodeldesigner.domainmodel.modelmappers;

import com.diguits.domainmodeldefinition.definitions.FieldSubgroupDef;
import com.diguits.domainmodeldefinition.definitions.FieldGroupDef;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import java.util.List;

public class FieldGroupDefToFieldGroupDefModelMapper extends BaseDefMapperToBaseDefModelMapper<FieldGroupDef, FieldGroupDefModel> {

	FieldSubgroupDefToFieldSubgroupDefModelMapper fieldSubgroupDefToFieldSubgroupDefModelMapper;

	public FieldGroupDefToFieldGroupDefModelMapper(IMapperProvider mapperProvider) {
		super(mapperProvider);
	}

	public FieldSubgroupDefToFieldSubgroupDefModelMapper getFieldSubgroupDefToFieldSubgroupDefModelMapper() {
		if(fieldSubgroupDefToFieldSubgroupDefModelMapper==null)
			fieldSubgroupDefToFieldSubgroupDefModelMapper = mapperProvider.getMapper(FieldSubgroupDefToFieldSubgroupDefModelMapper.class);
		return fieldSubgroupDefToFieldSubgroupDefModelMapper;
	}

	public void map(FieldGroupDef source, FieldGroupDefModel target, MappingContext context) {
		if(source==null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		List<FieldSubgroupDefModel> SubgroupsList = target.getSubgroups();
		for (FieldSubgroupDef item : source.getSubgroups()) {
			SubgroupsList.add( getFieldSubgroupDefToFieldSubgroupDefModelMapper().map(item, context));
		}
		super.map(source, target, context);
	}

	public void mapBack(FieldGroupDefModel source, FieldGroupDef target, MappingContext context) {
		if(source == null || target == null) return;
		target.setHasVisualRepresentation(source.getHasVisualRepresentation());
		List<FieldSubgroupDef> SubgroupsList = target.getSubgroups();
		for (FieldSubgroupDefModel item : source.getSubgroups()) {
			SubgroupsList.add( getFieldSubgroupDefToFieldSubgroupDefModelMapper().mapBack(item, context));
		}
		super.mapBack(source, target, context);
	}

	@Override
	protected Class<FieldGroupDefModel> getToClass() {
		return FieldGroupDefModel.class;
	}

	@Override
	protected Class<FieldGroupDef> getFromClass() {
		return FieldGroupDef.class;
	}
}
