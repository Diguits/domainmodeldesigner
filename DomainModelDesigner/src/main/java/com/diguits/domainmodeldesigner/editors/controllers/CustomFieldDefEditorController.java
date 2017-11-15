package com.diguits.domainmodeldesigner.editors.controllers;

import com.diguits.domainmodeldesigner.editors.views.CustomFieldDefEditorView;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;

import java.util.ArrayList;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.google.inject.Inject;

public class CustomFieldDefEditorController extends BaseDefEditorController<CustomFieldDefEditorView, CustomFieldDefModel> {

	@Inject
	DomainModelClientService domainModelClientService;

	@Inject
	public CustomFieldDefEditorController(CustomFieldDefEditorView view) {
		super(view);
	}

	@Override
	protected void setModelToView(CustomFieldDefModel n) {
		super.setModelToView(n);
		ArrayList<DataType> validDataTypes = new ArrayList<DataType>();
		for (DataType dataType : DataType.values()) {
			if (dataType != DataType.None
					&& dataType != DataType.ByteArray
					&& dataType != DataType.Decimal
					&& dataType != DataType.Entity
					&& dataType != DataType.EntityList
					&& dataType != DataType.Image
					&& dataType != DataType.ValueObject
					&& dataType != DataType.ValueObjectList)
				validDataTypes.add(dataType);
		}

		getView().setDataTypes(validDataTypes);
		getView().setDefClasses(domainModelClientService.getDefClasses());
	}
}
