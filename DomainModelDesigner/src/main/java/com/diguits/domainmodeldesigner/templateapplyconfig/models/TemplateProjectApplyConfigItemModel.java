package com.diguits.domainmodeldesigner.templateapplyconfig.models;

import com.diguits.javafx.model.NamedModelBase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TemplateProjectApplyConfigItemModel extends NamedModelBase{
	protected ObjectProperty<TemplateProjectApplyConfigItemModel> owner;

	public TemplateProjectApplyConfigItemModel getOwner() {
		if (owner != null)
			return owner.get();
		return null;
	}

	public ObjectProperty<TemplateProjectApplyConfigItemModel> ownerProperty() {
		if (owner == null) {
			owner = new SimpleObjectProperty<TemplateProjectApplyConfigItemModel>(this, "owner", null);
		}
		return owner;
	}
}
