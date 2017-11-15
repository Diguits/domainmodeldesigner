package com.diguits.domainmodeldesigner.domainmodelstructure.models;

import java.lang.reflect.Method;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PropertyStructureItem extends MethodStructureItem {
	protected ObjectProperty<Method> setterMethod;

	public PropertyStructureItem(String name, String description, DomainModelStructureItem parent, Method getterMethod, Method setterMethod) {
		super(name, description, parent, getterMethod);
		setSetterMethod(setterMethod);
	}


	public Method getSetterMethod() {
		if (setterMethod != null)
			return setterMethod.get();
		return null;
	}

	public void setSetterMethod(Method method) {
		if (this.setterMethod != null || method != null) {
			((SimpleObjectProperty<Method>) setterMethodProperty()).set(method);
		}
	}

	public ObjectProperty<Method> setterMethodProperty() {
		if (setterMethod == null) {
			setterMethod = new SimpleObjectProperty<Method>(this, "setterMethod", null);
		}
		return setterMethod;
	}

}
