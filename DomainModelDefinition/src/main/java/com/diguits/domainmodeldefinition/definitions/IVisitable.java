package com.diguits.domainmodeldefinition.definitions;

public interface IVisitable {
	void accept(IEntityDefinitionVisitor visitor, BaseDef owner);
}
