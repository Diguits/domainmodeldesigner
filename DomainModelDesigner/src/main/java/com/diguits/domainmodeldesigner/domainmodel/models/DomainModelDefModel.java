package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class DomainModelDefModel extends BaseDefModel {

	public DomainModelDefModel() {
		super();
	}

	private ObjectProperty<LocaleDefModel> defaultLocale;
	private BooleanProperty requireLogin;
	private ListProperty<LocaleDefModel> locales;
	private ListProperty<CustomFieldDefModel> customFields;
	private ListProperty<EntityDefModel> entities;
	private ListProperty<ValueObjectDefModel> valueObjects;
	private ListProperty<EnumDefModel> enums;
	private ListProperty<RelationshipDefModel> relations;
	private ListProperty<BoundedContextDefModel> boundedContexts;
	private ListProperty<ApplicationDefModel> applications;

	public LocaleDefModel getDefaultLocale() {
		if (defaultLocale != null)
			return defaultLocale.get();
		return null;
	}

	public void setDefaultLocale(LocaleDefModel defaultLocale) {
		if (this.defaultLocale != null || defaultLocale != null) {
			defaultLocaleProperty().set(defaultLocale);
		}
	}

	public ObjectProperty<LocaleDefModel> defaultLocaleProperty() {
		if (defaultLocale == null) {
			defaultLocale = new SimpleObjectProperty<LocaleDefModel>(this, "defaultLocale", null);
		}
		return defaultLocale;
	}

	public boolean getRequireLogin() {
		if (requireLogin != null)
			return requireLogin.get();
		return false;
	}

	public void setRequireLogin(boolean requireLogin) {
		if (this.requireLogin != null || requireLogin != false) {
			requireLoginProperty().set(requireLogin);
		}
	}

	public BooleanProperty requireLoginProperty() {
		if (requireLogin == null) {
			requireLogin = new SimpleBooleanProperty(this, "requireLogin", false);
		}
		return requireLogin;
	}

	public ObservableList<LocaleDefModel> getLocales() {
		return localesProperty().get();
	}

	public void setLocales(ObservableList<LocaleDefModel> locales) {
		if (this.locales != null || locales != null) {
			localesProperty().set(locales);
		}
	}

	public ListProperty<LocaleDefModel> localesProperty() {
		if (locales == null) {
			locales = new SimpleListProperty<LocaleDefModel>(this, "locales", null);
			locales.set(FXCollections.observableArrayList());
		}
		return locales;
	}

	public ObservableList<CustomFieldDefModel> getCustomFields() {
		return customFieldsProperty().get();
	}

	public void setCustomFields(ObservableList<CustomFieldDefModel> customFields) {
		if (this.customFields != null || customFields != null) {
			customFieldsProperty().set(customFields);
		}
	}

	public ListProperty<CustomFieldDefModel> customFieldsProperty() {
		if (customFields == null) {
			customFields = new SimpleListProperty<CustomFieldDefModel>(this, "customFields", null);
			customFields.set(FXCollections.observableArrayList());
		}
		return customFields;
	}

	public ObservableList<EntityDefModel> getEntities() {
		return entitiesProperty().get();
	}

	public ObservableList<DomainObjectDefModel> getDomainObjects() {
		//TODO This List will not change when entities or valueObjects lists change
		ObservableList<DomainObjectDefModel> result = FXCollections.observableArrayList(entitiesProperty().get());
		result.addAll(valueObjectsProperty().get());
		return result;
	}

	public void setEntities(ObservableList<EntityDefModel> entities) {
		if (this.entities != null || entities != null) {
			entitiesProperty().set(entities);
		}
	}

	public ListProperty<EntityDefModel> entitiesProperty() {
		if (entities == null) {
			entities = new SimpleListProperty<EntityDefModel>(this, "entities", null);
			entities.set(FXCollections.observableArrayList());
		}
		return entities;
	}

	public ObservableList<ValueObjectDefModel> getValueObjects() {
		return valueObjectsProperty().get();
	}

	public void setValueObjects(ObservableList<ValueObjectDefModel> valueObjects) {
		if (this.valueObjects != null || valueObjects != null) {
			valueObjectsProperty().set(valueObjects);
		}
	}

	public ListProperty<ValueObjectDefModel> valueObjectsProperty() {
		if (valueObjects == null) {
			valueObjects = new SimpleListProperty<ValueObjectDefModel>(this, "valueObjects", null);
			valueObjects.set(FXCollections.observableArrayList());
		}
		return valueObjects;
	}

	public ObservableList<EnumDefModel> getEnums() {
		return enumsProperty().get();
	}

	public void setEnums(ObservableList<EnumDefModel> enums) {
		if (this.enums!= null || enums != null) {
			enumsProperty().set(enums);
		}
	}

	public ListProperty<EnumDefModel> enumsProperty() {
		if(enums == null){
			enums = new SimpleListProperty<EnumDefModel>(this, "enums", null);
		enums.set(FXCollections.observableArrayList());
		}
		return enums;
	}

	public ObservableList<RelationshipDefModel> getRelations() {
		return relationsProperty().get();
	}

	public void setRelations(ObservableList<RelationshipDefModel> relations) {
		if (this.relations != null || relations != null) {
			relationsProperty().set(relations);
		}
	}

	public ListProperty<RelationshipDefModel> relationsProperty() {
		if (relations == null) {
			relations = new SimpleListProperty<RelationshipDefModel>(this, "relations", null);
			relations.set(FXCollections.observableArrayList());
		}
		return relations;
	}

	public ObservableList<BoundedContextDefModel> getBoundedContexts() {
		return boundedContextsProperty().get();
	}

	public void setBoundedContexts(ObservableList<BoundedContextDefModel> boundedContexts) {
		if (this.boundedContexts != null || boundedContexts != null) {
			boundedContextsProperty().set(boundedContexts);
		}
	}

	public ListProperty<BoundedContextDefModel> boundedContextsProperty() {
		if (boundedContexts == null) {
			boundedContexts = new SimpleListProperty<BoundedContextDefModel>(this, "boundedContexts", null);
			boundedContexts.set(FXCollections.observableArrayList());
		}
		return boundedContexts;
	}

	public List<ModuleDefModel> getAllModules() {
		List<ModuleDefModel> result = new ArrayList<ModuleDefModel>();
		ObservableList<BoundedContextDefModel> boundedContexts = getBoundedContexts();
		for (BoundedContextDefModel boundedContext : boundedContexts)
			result.addAll(boundedContext.getModules());
		return result;
	}

	public ObservableList<ApplicationDefModel> getApplications() {
		return applicationsProperty().get();
	}

	public void setApplications(ObservableList<ApplicationDefModel> applications) {
		if (this.applications != null || applications != null) {
			applicationsProperty().set(applications);
		}
	}

	public ListProperty<ApplicationDefModel> applicationsProperty() {
		if (applications == null) {
			applications = new SimpleListProperty<ApplicationDefModel>(this, "applications", null);
			applications.set(FXCollections.observableArrayList());
		}
		return applications;
	}

	public ObservableList<EnumDefModel> getAllEnums() {
		return allEnumsProperty().get();
	}

	public ReadOnlyListProperty<EnumDefModel> allEnumsProperty() {
		return enums;
	}

	private List<IDomainObjectChangeListener> relationshipDomainObjectChangeListeners = new ArrayList<IDomainObjectChangeListener>();
	public void registerRelationshipDomainObjectChangeListener(IDomainObjectChangeListener listener){
		if(listener!=null && !relationshipDomainObjectChangeListeners.contains(listener))
			relationshipDomainObjectChangeListeners.add(listener);
	}

	public void unregisterRelationshipDomainObjectChangeListener(IDomainObjectChangeListener listener){
		relationshipDomainObjectChangeListeners.remove(listener);
	}

	public void notifyRelationshipDomainObjectChange(DomainObjectDefModel oldDomainObject, DomainObjectDefModel newDomainObject){
		for (IDomainObjectChangeListener listener : relationshipDomainObjectChangeListeners) {
			listener.Change(oldDomainObject, newDomainObject);
		}
	}

	public void accept(IEntityDefinitionModelVisitor visitor){
		accept(visitor, null);
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for (ApplicationDefModel applicationDef : getApplications()) {
			applicationDef.accept(visitor, this);
		}
		for (BoundedContextDefModel boundedContextDef : getBoundedContexts()) {
			boundedContextDef.accept(visitor, this);
		}
		for (EntityDefModel entityDef : getEntities()) {
			entityDef.accept(visitor, this);
		}
		for (RelationshipDefModel RelationshipDef : getRelations()) {
			RelationshipDef.accept(visitor, this);
		}
		for (ValueObjectDefModel valueObjectDef : getValueObjects()) {
			valueObjectDef.accept(visitor, this);
		}
		for (EnumDefModel enumDef : getEnums()){
            enumDef.accept(visitor, this);
        }
        for (LocaleDefModel locale : getLocales()) {
			locale.accept(visitor, this);
		}
		visitor.visitDomainModelDefModel(this);
	}

}
