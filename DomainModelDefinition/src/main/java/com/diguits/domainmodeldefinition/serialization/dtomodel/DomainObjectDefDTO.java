package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Default;

@Default(required = false)
public class DomainObjectDefDTO extends SimpleDomainObjectDefDTO{
	protected String tableName;
	protected List<FieldDefDTO> fields;
	protected List<IndexDefDTO> indexes;
	protected List<UUID> primaryKey;
	private List<FieldGroupDefDTO> fieldGroups;

	public DomainObjectDefDTO() {
		fields = new ArrayList<FieldDefDTO>();
		primaryKey = new ArrayList<UUID>();
		indexes = new ArrayList<IndexDefDTO>();
		fieldGroups = new ArrayList<FieldGroupDefDTO>();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<FieldDefDTO> getFields() {
		return fields;
	}

	public void setFields(List<FieldDefDTO> fields) {
		this.fields = fields;
	}

	public List<IndexDefDTO> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<IndexDefDTO> indexes) {
		this.indexes = indexes;
	}

	public List<UUID> getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(List<UUID> primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<FieldGroupDefDTO> getFieldGroups() {
		return fieldGroups;
	}

	public void setFieldGroups(List<FieldGroupDefDTO> fieldGroups) {
		this.fieldGroups = fieldGroups;
	}
}
