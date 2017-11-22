package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;

public abstract class PathDefBase extends BaseDef {
	protected String path;
	protected List<FieldDef> pathFieldChain;

	protected PathDefBase(BaseDef owner) {
		super(owner);
	}

	protected PathDefBase() {
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if (this.path == path)
			return;
		this.path = path;
		pathFieldChain = null;
	}

	public List<FieldDef> getPathFieldChain() {
		if (pathFieldChain == null && path != null && !path.trim().isEmpty()) {
			pathFieldChain = new ArrayList<FieldDef>();
			DomainObjectDef domainObject = null;
			if(owner instanceof DomainObjectDef){
				domainObject = (DomainObjectDef) owner;
			} else  if(owner instanceof FieldRelationshipDataDef){
				FieldRelationshipDataDef relationshipDataDef = (FieldRelationshipDataDef) this.owner;
				domainObject = relationshipDataDef.getRelationshipPart().getToRelationshipPart().getDomainObject();
			}
			if (domainObject != null) {
				String[] fields = path.split("[.]");

				List<FieldDef> entityFields = domainObject.getFields();
				int j = 0;
				while (j < entityFields.size() && !entityFields.get(j).getName().equals(fields[0]))
					j++;

				FieldDef selected = j < entityFields.size() ? entityFields.get(j) : null;
				if (selected != null) {
					pathFieldChain.add(selected);
				}
				int i = 1;
				while (i < fields.length && selected != null && selected.getRelatedEntity() != null) {
					entityFields = selected.getRelatedEntity().getFields();
					j = 0;
					while (j < entityFields.size() && !entityFields.get(j).getName().equals(fields[i]))
						j++;

					selected = j < entityFields.size() ? entityFields.get(j) : null;

					if (selected != null) {
						pathFieldChain.add(selected);
					}
					i++;
				}
			}
		}
		return pathFieldChain;
	}

	public FieldDef getField() {
		List<FieldDef> tempPathFieldChain = getPathFieldChain();
		return (tempPathFieldChain.size() > 0) ? tempPathFieldChain.get(tempPathFieldChain.size() - 1) : null;
	}

	public String getPathName() {
		return path.replace(".", "");
	}
}
