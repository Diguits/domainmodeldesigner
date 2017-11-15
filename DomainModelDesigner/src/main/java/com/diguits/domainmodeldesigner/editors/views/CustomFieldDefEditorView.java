package com.diguits.domainmodeldesigner.editors.views;

import java.util.ArrayList;
import java.util.List;

import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.google.inject.Inject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

public class CustomFieldDefEditorView extends BaseDefEditorView<CustomFieldDefModel> {

	private ComboBox<Class<? extends BaseDef>> cbxOverDefClass;
	private ChoiceBox<DataType> choDataType;
	private TextArea txaData;
	@SuppressWarnings("rawtypes")
	private ComboBox cbxReference;
	private CheckBox chbMultiline;

	@Inject
	DomainModelClientService domainModelClientService;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%custom_field");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();

		cbxOverDefClass = nodeFactory.createComboBoxInsideGrid(gridPane, "%over_model");
		choDataType = nodeFactory.createChoiceBoxInsideGrid(gridPane, "%data_type");
		cbxReference = nodeFactory.createComboBoxInsideGrid(gridPane, "%reference");
		txaData = nodeFactory.createTextAreaInsideGrid(gridPane, "%data");
		chbMultiline = nodeFactory.createCheckBoxInsideGrid(gridPane, "%multiline");
		tab.setContent(nodeFactory.addAndFitToAnchorPane(gridPane));
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(choDataType.valueProperty(),getModel().dataTypeProperty());

			bindBidirectional(txaData.textProperty(),getModel().dataProperty());
			bindBidirectional(chbMultiline.selectedProperty(),getModel().multilineProperty());
			updateReferenceCombobox();
			getModel().dataTypeProperty().addListener((v, o, n) -> {
				updateReferenceCombobox();
			});
		}
	}

	@SuppressWarnings("rawtypes")
	ChangeListener enumReferenceListener = new ChangeListener() {
		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue) {
			if(newValue != null)
				getModel().setReference(((EnumDefModel)newValue).getId());
		}
	};

	@SuppressWarnings("unchecked")
	private void updateReferenceCombobox() {
		if (getModel().getDataType() == DataType.Enum) {
			ObservableList<EnumDefModel> allEnums = domainModelClientService.getDomainModelDef().getAllEnums();
			cbxReference.setItems(allEnums);
			for (EnumDefModel enumDefModel : allEnums) {
				if(enumDefModel.getId().equals(getModel().getReference())){
					cbxReference.getSelectionModel().select(enumDefModel);
					break;
				}
			}
			cbxReference.getSelectionModel().selectedItemProperty().addListener(enumReferenceListener);
		} else if (cbxReference.getItems() != null) {
			cbxReference.getSelectionModel().selectedItemProperty().removeListener(enumReferenceListener);
			cbxReference.setItems(FXCollections.observableArrayList());
		}
	}

	public void setDefClasses(List<Class<? extends BaseDef>> classes) {
		cbxOverDefClass.getItems().addAll(classes);
		cbxOverDefClass.setConverter(new StringBaseDefClassConverter());
		bindBidirectional(cbxOverDefClass.valueProperty(), getModel().overDefClassProperty());
	}

	public class StringBaseDefClassConverter extends StringConverter<Class<? extends BaseDef>> {

		@Override
		public String toString(Class<? extends BaseDef> clazz) {
			return clazz.getSimpleName().replace("Def", "");
		}

		@Override
		public Class<? extends BaseDef> fromString(String string) {
			return null;
		}

	}

	public void setDataTypes(ArrayList<DataType> validDataTypes) {
		choDataType.setItems(FXCollections.observableArrayList(validDataTypes));
	}
}
