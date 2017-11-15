package com.diguits.domainmodeldefinition.services;

import java.util.ArrayList;
import java.util.List;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDefinitionVisitorBase;

public class ListAllDefsVisitor extends DomainModelDefinitionVisitorBase {
	List<BaseDef> allDefs;
	List<Class<? extends BaseDef>> ignoreClasses;

	public ListAllDefsVisitor() {
		super();
		allDefs = new ArrayList<BaseDef>();
		ignoreClasses = new ArrayList<Class<? extends BaseDef>>();
	}

	public void ignore(Class<? extends BaseDef> clazz) {
		if (!ignoreClasses.contains(clazz))
			ignoreClasses.add(clazz);
	}

	@Override
	protected void visitBaseDef(BaseDef baseDef, BaseDef owner) {
		if (!(ignoreClasses.contains(baseDef.getClass())))
			allDefs.add(baseDef);
		super.visitBaseDef(baseDef, owner);
	}

	public List<BaseDef> getAllDefs(DomainModelDef domainModel) {
		allDefs.clear();
		domainModel.accept(this);
		return allDefs;
	}
}
