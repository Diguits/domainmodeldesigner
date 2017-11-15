package com.diguits.domainmodeldesigner.editors.views;

import java.util.UUID;

import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.DefaultPropertyEditorFactory;
import org.controlsfx.property.editor.PropertyEditor;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.javafx.controls.PropertySheetEditors;
import com.google.inject.Inject;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class CustomPropertyEditorFactory implements Callback<PropertySheet.Item, PropertyEditor<?>>{

	@Inject
	DomainModelClientService domainModelClientService;

	SimpleObjectProperty<Callback<PropertySheet.Item, PropertyEditor<?>>> propertyEditorFactory = new SimpleObjectProperty<>(
			this, "propertyEditor", new DefaultPropertyEditorFactory());

	@Override
	public PropertyEditor<?> call(PropertySheet.Item param) {
		CustomDataValueItem customParam;
		if (param instanceof CustomDataValueItem) {
			customParam = (CustomDataValueItem) param;

			DataType dataType = customParam.getDataType();
			UUID reference = customParam.getReference();
			Object value = customParam.getValue();
			boolean multiline = customParam.getMultiline();

			if (dataType == DataType.Date || dataType == DataType.DateTime) {
				return PropertySheetEditors.createDateEditor(param);
			}
			if (dataType == DataType.Time) {
				return PropertySheetEditors.createTimeEditor(param);
			}
			if (dataType == DataType.String && multiline) {
				return PropertySheetEditors.createTextAreaEditor(param);
			}
			if (dataType == DataType.Guid) {
				return PropertySheetEditors.createUUIDEditor(param);
			}
			if (dataType == DataType.Enum) {
				if (value instanceof UUID) {
					UUID enumId = (UUID) value;
					customParam.setValue(getEnumValue(reference, enumId));
				}
				return PropertySheetEditors.createComboBoxEditor(param, getEnumValues(reference));
			}
		}
		return propertyEditorFactory.get().call(param);
	}

	private EnumValueDefModel getEnumValue(UUID reference, UUID value) {
		ObservableList<EnumValueDefModel> enumValues = getEnumValues(reference);
		for (EnumValueDefModel enumValueDefModel : enumValues) {
			if (enumValueDefModel.getId().equals(value)) {
				return enumValueDefModel;
			}
		}
		return null;
	}

	protected ObservableList<EnumValueDefModel> getEnumValues(UUID reference) {
		EnumDefModel enumDefModel = getEnum(reference);
		if (enumDefModel != null)
			return enumDefModel.getValues();
		return FXCollections.observableArrayList();
	}

	protected EnumDefModel getEnum(UUID reference) {
		if (reference != null) {
			ObservableList<EnumDefModel> allEnums = domainModelClientService.getDomainModelDef().getAllEnums();
			for (EnumDefModel enumDefModel : allEnums) {
				if (enumDefModel.getId().equals(reference))
					return enumDefModel;
			}
		}
		return null;
	}
}
