package com.diguits.domainmodeldesigner.editors.views;

import java.util.ArrayList;
import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
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

public class TemplateParameterDefEditorView extends TemplateProjectItemEditorView<TemplateParameterDefModel> {

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
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%parameter");
		GridPane gridPane = nodeFactory.createGridPaneForEdit();
		choDataType = nodeFactory.createChoiceBoxInsideGrid(gridPane, "%data_type");
		cbxReference = nodeFactory.createComboBoxInsideGrid(gridPane, "%reference");
		txaData = nodeFactory.createTextAreaInsideGrid(gridPane, "%data");
		chbMultiline = nodeFactory.createCheckBoxInsideGrid(gridPane, "%multiline");
		tab.setContent(nodeFactory.wrapInScrollPane(gridPane));
		return contentView;
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			ArrayList<DataType> validDataTypes = new ArrayList<DataType>();
			for (DataType dataType : DataType.values()) {
				if (dataType != DataType.None && dataType != DataType.ByteArray && dataType != DataType.Decimal
						&& dataType != DataType.Entity && dataType != DataType.EntityList && dataType != DataType.Image)
					validDataTypes.add(dataType);
			}
			choDataType.setItems(FXCollections.observableArrayList(validDataTypes));
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
}
