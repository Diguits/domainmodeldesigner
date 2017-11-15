package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DomainObjectDefModel extends SimpleDomainObjectDefModel {

	public DomainObjectDefModel() {
		super();
	}
	protected StringProperty tableName;

	protected ListProperty<IndexDefModel> indexes;
	protected ListProperty<FieldDefModel> fields;
	protected ListProperty<FieldDefModel> primaryKey;
	protected ListProperty<FieldGroupDefModel> fieldGroups;

	public String getTableName() {
		if (tableName != null)
			return tableName.get();
		return "";
	}

	public void setTableName(String tableName) {
		if (this.tableName != null || tableName == null || !tableName.equals("")) {
			tableNameProperty().set(tableName);
		}
	}

	public StringProperty tableNameProperty() {
		if (tableName == null) {
			tableName = new SimpleStringProperty(this, "tableName", "");
		}
		return tableName;
	}

	public ObservableList<IndexDefModel> getIndexes() {
		return indexesProperty().get();
	}

	public ObservableList<FieldGroupDefModel> getFieldGroups() {
		return fieldGroupsProperty().get();
	}

	public void setFieldGroups(ObservableList<FieldGroupDefModel> fieldGroups) {
		if (this.fieldGroups != null || fieldGroups != null) {
			fieldGroupsProperty().set(fieldGroups);
		}
	}

	public ListProperty<FieldGroupDefModel> fieldGroupsProperty() {
		if (fieldGroups == null) {
			fieldGroups = new SimpleListProperty<FieldGroupDefModel>(this, "fieldGroups", null);
			fieldGroups.set(FXCollections.observableArrayList());
		}
		return fieldGroups;
	}

	public void setIndexes(ObservableList<IndexDefModel> indexes) {
		if (this.indexes != null || indexes != null) {
			indexesProperty().set(indexes);
		}
	}

	public ListProperty<IndexDefModel> indexesProperty() {
		if (indexes == null) {
			indexes = new SimpleListProperty<IndexDefModel>(this, "indexes", null);
			indexes.set(FXCollections.observableArrayList());
		}
		return indexes;
	}

	public ObservableList<FieldDefModel> getFields() {
		return fieldsProperty().get();
	}

	public void setFields(ObservableList<FieldDefModel> fields) {
		if (this.fields != null || fields != null) {
			fieldsProperty().set(fields);
		}
	}

	public ListProperty<FieldDefModel> fieldsProperty() {
		if (fields == null) {
			fields = new SimpleListProperty<FieldDefModel>(this, "fields", null);
			fields.set(FXCollections.observableArrayList());
		}
		return fields;
	}

	public ObservableList<FieldDefModel> getPrimaryKey() {
		return primaryKeyProperty().get();
	}

	public void setPrimaryKey(ObservableList<FieldDefModel> primaryKey) {
		if (this.primaryKey != null || primaryKey != null) {
			primaryKeyProperty().set(primaryKey);
		}
	}

	public ListProperty<FieldDefModel> primaryKeyProperty() {
		if (primaryKey == null) {
			primaryKey = new SimpleListProperty<FieldDefModel>(this, "primaryKey", null);
			primaryKey.set(FXCollections.observableArrayList());
		}
		return primaryKey;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);

		for (FieldDefModel fieldDef : getFields()) {
			fieldDef.accept(visitor, this);
		}

		for (IndexDefModel index : indexes) {
			index.accept(visitor, this);
		}
	}
}
