package com.diguits.domainmodeldesigner.domainmodelstructure.models;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MethodStructureItem extends DomainModelStructureItem {
	protected ObjectProperty<Method> method;

	public MethodStructureItem(String name, String description, DomainModelStructureItem parent, Method method) {
		super(name, description, parent);
		setMethod(method);
	}

	public Method getMethod() {
		if (method != null)
			return method.get();
		return null;
	}

	public void setMethod(Method class1) {
		if (this.method != null || class1 != null) {
			((SimpleObjectProperty<Method>) methodProperty()).set(class1);
		}
	}

	public ObjectProperty<Method> methodProperty() {
		if (method == null) {
			method = new SimpleObjectProperty<Method>(this, "method", null);
		}
		return method;
	}

	public boolean getIsGetter() {
		Method method = getMethod();
		return getIsGetter(method);
	}

	public static boolean getIsGetter(Method method) {
		return method != null && method.getName().startsWith("get") && method.getParameterCount() == 0
				&& method.getReturnType().getSimpleName() != "void";
	}

	public boolean getIsSetter() {
		Method method = getMethod();
		return getIsSetter(method);
	}

	public static boolean getIsSetter(Method method) {
		return method != null && method.getName().startsWith("set") && method.getParameterCount() == 1
				&& method.getReturnType().getSimpleName() == "void";
	}

	@Override
	protected String getInnerCode() {
		Method method = getMethod();
		String result = method.getName();
		result += "(";
		if (method.getParameterCount() > 0) {
			Parameter[] parameters = method.getParameters();
			int i = 0;
			for (Parameter parameter : parameters) {
				if (i > 0)
					result += ", ";
				result += parameter.getName();
				i++;
			}
		}
		result += ")";
		return result;
	}

}
