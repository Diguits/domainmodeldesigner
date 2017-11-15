package com.diguits.domainmodeldesigner.services;

import java.util.ArrayList;
import java.util.List;

import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefinitionModelVisitorBase;
import com.google.inject.Inject;

public class ListAllDefModelsVisitor extends DomainModelDefinitionModelVisitorBase {
	List<BaseDefModel> allDefs;
	List<Class<? extends BaseDefModel>> ignoreClasses;

	@Inject
	public ListAllDefModelsVisitor() {
		super();
		allDefs = new ArrayList<BaseDefModel>();
		ignoreClasses = new ArrayList<Class<? extends BaseDefModel>>();
	}

	public void ignore(Class<? extends BaseDefModel> clazz) {
		if (!ignoreClasses.contains(clazz))
			ignoreClasses.add(clazz);
	}

	@Override
	protected void visitBaseDefModel(BaseDefModel baseDef, BaseDefModel owner) {
		if (!(ignoreClasses.contains(baseDef.getClass())))
			allDefs.add(baseDef);
		super.visitBaseDefModel(baseDef, owner);
	}

	public List<BaseDefModel> getAllDefModels(DomainModelDefModel domainModel) {
		allDefs.clear();
		domainModel.accept(this);
		return allDefs;
	}
}
