package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.DataType;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

public interface IEditorByDataTypeFactory {

	Node create(DataType dataType, Object minValue, Object maxValue, ObjectProperty<Object> binding);
}