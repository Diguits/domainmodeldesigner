package com.diguits.domainmodeldesigner.domainmodelstructure.models;

import java.awt.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ClassStructureItem extends DomainModelStructureItem{

	protected ObjectProperty<Class<?>> structureClass;

	public ClassStructureItem(String name, String description, DomainModelStructureItem parent, Class<?> structureClass) {
		super(name, description, parent);
		setStructureClass(structureClass);
	}

	public Class<?> getStructureClass() {
		if (structureClass != null)
			return structureClass.get();
		return null;
	}

	public void setStructureClass(Class<?> class1) {
		if (this.structureClass != null || class1 != null) {
			((SimpleObjectProperty<Class<?>>) structureClassProperty()).set(class1);
		}
	}

	public ObjectProperty<Class<?>> structureClassProperty() {
		if (structureClass == null) {
			structureClass = new SimpleObjectProperty<Class<?>>(this, "structureClass", null);
		}
		return structureClass;
	}

	@Override
	public String getCode() {
		return getStructureClass().getSimpleName();
	}
	@Override
	public String toString() {
		String result = super.toString();
		if(getStructureClass()!=null)
			result = result+" ("+getStructureClass().getSimpleName()+")";
		return result;
	}

	public boolean isList(){
		return getStructureClass()!=null&&getStructureClass()==List.class;
	}

	public boolean isEnum(){
		return getStructureClass()!=null&&getStructureClass().isEnum();
	}

	public boolean isPrimitive(){
		return getStructureClass()!=null&&getStructureClass().isPrimitive();
	}
}
