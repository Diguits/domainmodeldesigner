package com.diguits.domainmodeldesigner.domainmodel.models;

public interface IVisitable {
	void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner);
}
