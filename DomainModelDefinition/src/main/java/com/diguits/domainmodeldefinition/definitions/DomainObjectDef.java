package com.diguits.domainmodeldefinition.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DomainObjectDef extends SimpleDomainObjectDef {
	protected String tableName;

	protected List<FieldDef> fields;
	protected List<IndexDef> indexes;
	protected List<FieldDef> primaryKey;

	protected List<FieldGroupDef> fieldGroups;


	public DomainObjectDef(DomainModelDef owner) {
		super(owner);
		initialize();
	}

	public DomainObjectDef() {
		super();
		initialize();
	}

	private void initialize() {
		fields = new ArrayList<FieldDef>();
		primaryKey = new ArrayList<FieldDef>();
		indexes = new ArrayList<IndexDef>();
		fieldGroups = new ArrayList<FieldGroupDef>();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<FieldDef> getFields() {
		return fields;
	}

	public void setFields(List<FieldDef> fields) {
		this.fields = fields;
	}

	public List<IndexDef> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<IndexDef> indexes) {
		this.indexes = indexes;
	}

	public List<FieldDef> getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(List<FieldDef> primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<FieldGroupDef> getFieldGroups() {
		return fieldGroups;
	}

	public void setFieldGroups(List<FieldGroupDef> fieldGroups) {
		this.fieldGroups = fieldGroups;
	}

	public DomainModelDef getOwnerDomainModel() {
		return (DomainModelDef) getOwner();
	}

	public void setOwnerDomainModel(DomainModelDef domainModelDef) {
		setOwner(domainModelDef);
	}

	public FieldDef getFieldByName(String name) {
		int i = 0;
		while (i < fields.size() && !fields.get(i).getName().equals(name))
			i++;
		return i < fields.size() ? fields.get(i) : null;
	}

	public FieldDef getFieldById(UUID fieldId) {
		int i = 0;
		while (i < fields.size() && !fields.get(i).getId().equals(fieldId))
			i++;
		return i < fields.size() ? fields.get(i) : null;
	}

	public IndexDef getIndexById(UUID indexId) {
		int i = 0;
		while (i < indexes.size() && !indexes.get(i).getId().equals(indexId))
			i++;
		return i < indexes.size() ? indexes.get(i) : null;
	}

	public FieldGroupDef getFieldGroupById(UUID fieldGroupId) {
		int i = 0;
		while (i < fieldGroups.size() && !fieldGroups.get(i).getId().equals(fieldGroupId))
			i++;
		return i < fieldGroups.size() ? fieldGroups.get(i) : null;
	}

	@Override
	public void accept(IEntityDefinitionVisitor visitor, BaseDef owner) {
		super.accept(visitor, owner);
		for (FieldDef fieldDef : fields) {
			fieldDef.accept(visitor, this);
		}
		for (IndexDef index : indexes) {
			index.accept(visitor, this);
		}
		for (FieldGroupDef fieldGroupDef : fieldGroups) {
			fieldGroupDef.accept(visitor, this);
		}
	}
}