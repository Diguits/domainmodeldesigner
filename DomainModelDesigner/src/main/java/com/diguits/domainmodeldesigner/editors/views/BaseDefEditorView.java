package com.diguits.domainmodeldesigner.editors.views;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import org.controlsfx.control.PropertySheet;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.google.inject.Inject;

public class BaseDefEditorView<TModel extends BaseDefModel> extends NamedModelEditorView<TModel> {

	@Inject
	IEditorByDataTypeFactory editorByDataTypeFactory;

	@Inject
	DomainModelClientService domainModelClientService;

	@Inject
	CustomPropertyEditorFactory customPropertyEditorFactory;

	protected TableModelEditorView<LocalizedDataDefModel> localizedDataView;
	protected TableColumn<LocalizedDataDefModel, String> colLocalesLocale;
	protected TableColumn<LocalizedDataDefModel, String> colLocalesCaption;
	protected TableColumn<LocalizedDataDefModel, String> colLocalesHint;
	protected TableColumn<LocalizedDataDefModel, String> colLocalesPlaceHolder;
	protected TableColumn<LocalizedDataDefModel, String> colLocalesFormat;

	private Tab customFieldValuesTab;
	private PropertySheet pshCustomFieldValues;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		RowConstraints rowConstraints = nodeFactory.createRowConstraints();
		rowConstraints.setVgrow(Priority.SOMETIMES);
		generalGridPane.getRowConstraints().add(rowConstraints);
		GridPane.setValignment(nodeFactory.createLabelInsideGrid(generalGridPane, "%locales", 3), VPos.TOP);

		localizedDataView = createTableEditorView(LocalizedDataDefModel.class, tvf -> tvf
				.add(String.class, "%locale").width(50).valueFactory(cd -> {
					if (cd.getValue() != null && cd.getValue().getLocale() != null)
						return cd.getValue().getLocale().nameProperty();
					return null;
				}).buildColumn()
				.add(String.class, "%caption").width(100).valueFactory(cd -> cd.getValue().captionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%hint").width(100).valueFactory(cd -> cd.getValue().hintProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%place_holder").width(350).valueFactory(cd -> cd.getValue().placeHolderProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%format").width(100).valueFactory(cd -> cd.getValue().formatProperty()).useDefaultCellValueFactory().buildColumn()
				.buildTable());

		localizedDataView.setCanAdd(false);
		localizedDataView.setCanOrder(false);
		localizedDataView.setCanDelete(false);


		generalGridPane.add(localizedDataView.getNodeView(), 1, 3);
		localizedDataView.setShowToolBar(false);
		customFieldValuesTab = nodeFactory.createAndAddTab(tabPane, "%custom_field_values");
		pshCustomFieldValues = new PropertySheet();
		pshCustomFieldValues.setPropertyEditorFactory(customPropertyEditorFactory);
		customFieldValuesTab.setContent(pshCustomFieldValues);
		return contentView;
	}

	@Override
	protected void afterContentViewBuilt() {
		super.afterContentViewBuilt();
		tabPane.getTabs().remove(customFieldValuesTab);
		tabPane.getTabs().add(customFieldValuesTab);
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			localizedDataView.setModel(getModel().localizedDatasProperty());
			updateItemList();
			getModel().getCustomFieldValues().addListener((ListChangeListener<CustomFieldValueDefModel>) c -> {
				updateItemList();
			});
		}
	}

	public void updateItemList() {
		ObservableList<CustomDataValueItem> customFieldValueItems = FXCollections.observableArrayList();
		for (CustomFieldValueDefModel customFieldValue : getModel().getCustomFieldValues()) {
			if (customFieldValue.getCustomField() != null)
				customFieldValueItems.add(new CustomDataValueItem(customFieldValue.getCustomField().getName(),
						customFieldValue.getCustomField().getDescription(),
						customFieldValue.getCustomField().getDataType(),
						customFieldValue.getCustomField().getReference(),
						customFieldValue.getCustomField().getMultiline(), customFieldValue.valueProperty()));
		}
		pshCustomFieldValues.getItems().clear();
		pshCustomFieldValues.getItems().addAll(customFieldValueItems);
	}

    public TableModelEditorView<LocalizedDataDefModel> getLocalizedDataView() {
		return localizedDataView;
	}

}
