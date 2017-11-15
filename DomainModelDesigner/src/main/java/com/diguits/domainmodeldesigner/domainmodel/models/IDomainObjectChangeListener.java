package com.diguits.domainmodeldesigner.domainmodel.models;

public interface IDomainObjectChangeListener {
	void Change(DomainObjectDefModel oldDomainObject, DomainObjectDefModel newDomainObject);
}
