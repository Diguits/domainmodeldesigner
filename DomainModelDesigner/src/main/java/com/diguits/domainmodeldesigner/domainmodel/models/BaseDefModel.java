package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.diguits.javafx.model.NamedModelBase;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;

public class BaseDefModel extends NamedModelBase implements IVisitable {

    public BaseDefModel() {
        super();
    }

    private ObjectProperty<BaseDefModel> owner;
    private ListProperty<LocalizedDataDefModel> localizedDataList;
    private ListProperty<CustomFieldValueDefModel> customFieldValues;

    public BaseDefModel getOwner() {
        if (owner != null)
            return owner.get();
        return null;
    }

    public void setOwner(BaseDefModel owner) {
        if (this.owner != null || owner != null) {
            ownerProperty().set(owner);
        }
    }

    public ObjectProperty<BaseDefModel> ownerProperty() {
        if (owner == null) {
            owner = new SimpleObjectProperty<BaseDefModel>(this, "owner", null);
        }
        return owner;
    }

    public ObservableList<LocalizedDataDefModel> getLocalizedDataList() {
        return localizedDataListProperty().get();
    }

    public void setLocalizedDataList(ObservableList<LocalizedDataDefModel> localizedDataList) {
        if (this.localizedDataList != null || localizedDataList != null) {
            localizedDataListProperty().set(localizedDataList);
        }
    }

    public ListProperty<LocalizedDataDefModel> localizedDataListProperty() {
        if (localizedDataList == null) {
            localizedDataList = new SimpleListProperty<LocalizedDataDefModel>(this, "localizedDataList", null);
            localizedDataList.set(FXCollections.observableArrayList());
        }
        return localizedDataList;
    }

    public ObservableList<CustomFieldValueDefModel> getCustomFieldValues() {
        return customFieldValuesProperty().get();
    }

    public void setCustomFieldValues(ObservableList<CustomFieldValueDefModel> customFieldValues) {
        if (this.customFieldValues != null || customFieldValues != null) {
            customFieldValuesProperty().set(customFieldValues);
        }
    }

    public ListProperty<CustomFieldValueDefModel> customFieldValuesProperty() {
        if (customFieldValues == null) {
            customFieldValues = new SimpleListProperty<CustomFieldValueDefModel>(this, "customFieldValues", null);
            customFieldValues.set(FXCollections.observableArrayList());
        }
        return customFieldValues;
    }

    public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
        for (LocalizedDataDefModel localizedData : getLocalizedDataList()) {
            localizedData.accept(visitor, this);
        }
        for (CustomFieldValueDefModel customFieldValue : getCustomFieldValues()) {
            customFieldValue.accept(visitor, this);
        }
    }

    public String getOwnerPath() {
        if (getOwner() != null)
            return getOwner().getPath();
        return "";
    }

    private String getPath() {
        String result = getName();
        BaseDefModel current = this.getOwner();
        while (current != null) {
            result = current.getName() + "/" + result;
            current = current.getOwner();
        }
        return result;
    }

    public LocalizedDataDefModel getLocalizedData(LocaleDefModel locale) {
        for (LocalizedDataDefModel localizedDataDef : localizedDataList) {
            if (localizedDataDef.getLocale() == locale)
                return localizedDataDef;
        }
        return null;
    }
}
