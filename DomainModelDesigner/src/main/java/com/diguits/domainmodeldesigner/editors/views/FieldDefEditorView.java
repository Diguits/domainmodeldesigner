package com.diguits.domainmodeldesigner.editors.views;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldefinition.definitions.FilterLogicalOperator;
import com.diguits.domainmodeldefinition.definitions.FilterOperator;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.editors.views.common.TableModelEditorView;
import com.diguits.domainmodeldesigner.editors.views.common.TreeTableModelEditorView;
import com.diguits.javafx.controls.IntegerSpinnerValueFactory;
import com.google.inject.Inject;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FieldDefEditorView extends BaseDefEditorView<FieldDefModel> {

	@Inject
	IEditorByDataTypeFactory editorByDataTypeFactory;

	private TextField txfDatabaseName;
	private ChoiceBox<DataType> choDataType;
	private ComboBox<FieldGroupDefModel> cbxFieldGroup;
	private ComboBox<FieldSubgroupDefModel> cbxFieldSubgroup;
	private CheckBox chbAllowNull;
	private CheckBox chbReadOnly;
	private Spinner<Integer> spnMaxLength;
	private Spinner<Integer> spnMinLength;
	private Spinner<Integer> spnPrecision;
	private Spinner<Integer> spnScale;
	private CheckBox chbIsCurrency;
	private CheckBox chbIsRepresentative;
	private CheckBox chbIncludeMinValue;
	private CheckBox chbIncludeMaxValue;
	private ComboBox<EnumDefModel> cbxEnumDef;
	private ComboBox<EntityDefModel> cbxRelatedEntity;
	private ComboBox<FieldDefModel> cbxRelatedField;
	private TextField txfRegularExpression;
	private CheckBox chbMapToDatabase;
	private CheckBox chbDateShowTime;
	private CheckBox chbHasVisualRepresentation;
	private CheckBox chbMultiline;
	private ComboBox<ValueObjectDefModel> cbxValueObject;
	private TextField txfValueObjectTableName;

	private TreeTableModelEditorView<FilterValueDefModel> filterValuesView;
	private TableModelEditorView<ColumnDefModel> extraColumnsView;
	private TableModelEditorView<RelationOverrideDefModel> overridesView;

	private GridPane gridPane;

	private Label lblTypeTitle;

	private ObservableList<EnumDefModel> enums;

	private ObservableList<EntityDefModel> entities;
	private ObservableList<ValueObjectDefModel> valueObjects;

	ObjectProperty<EntityDefModel> auxiliarToEntityProperty;
	ObjectProperty<FieldDefModel> auxiliarToFieldProperty;

	private ComboBox<EnumValueDefModel> enumDefaultValueNode;

	@Override
	protected Node buildContentView() {
		Node contentView = super.buildContentView();
		Tab tab = nodeFactory.createAndAddTab(tabPane, "%field");
		gridPane = nodeFactory.createGridPaneForEdit();

		txfDatabaseName = nodeFactory.createTextFieldInsideGrid(gridPane, "%database_name");
		choDataType = nodeFactory.createChoiceBoxInsideGrid(gridPane, "%data_type");
		cbxFieldGroup = nodeFactory.createComboBoxInsideGrid(gridPane, "%field_group");
		cbxFieldSubgroup = nodeFactory.createComboBoxInsideGrid(gridPane, "%field_sub_group");
		chbAllowNull = nodeFactory.createCheckBoxInsideGrid(gridPane, "%allow_null");
		chbReadOnly = nodeFactory.createCheckBoxInsideGrid(gridPane, "%read_only");
		chbMapToDatabase = nodeFactory.createCheckBoxInsideGrid(gridPane, "%map_to_database");
		chbHasVisualRepresentation = nodeFactory.createCheckBoxInsideGrid(gridPane, "%has_visual_representation");
		chbIsRepresentative = nodeFactory.createCheckBoxInsideGrid(gridPane, "%is_representative");
		lblTypeTitle = nodeFactory.createLabelInsideGrid(gridPane, "");
		lblTypeTitle.setFont(
				Font.font(lblTypeTitle.getFont().getFamily(), FontWeight.BOLD, lblTypeTitle.getFont().getSize()));
		gridPane.getRowConstraints().get(9).setValignment(VPos.BOTTOM);
		gridPane.getRowConstraints().get(9).setPrefHeight(25);

		Separator separator = new Separator(Orientation.HORIZONTAL);
		RowConstraints rowConstraints = nodeFactory.createRowConstraints();
		rowConstraints.setPrefHeight(4);
		gridPane.getRowConstraints().add(rowConstraints);
		gridPane.add(separator, 0, 10, 2, 1);
		choDataType.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
			updateDataTypeDetail(o, n);
		});
		tab.setContent(nodeFactory.addAndFitToAnchorPane(gridPane));

		filterValuesView = createTreeTableEditorViewInNewTab(tabPane, "%filter_values", FilterValueDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(new TreeItemPropertyValueFactory<>("name")).useDefaultCellValueFactory().buildColumn()
				.add(FilterLogicalOperator.class, "%logical_operator").width(90).valueFactory(new TreeItemPropertyValueFactory<>("logicalOperator")).cellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(FXCollections.observableArrayList(FilterLogicalOperator.values()))).buildColumn()
				.add(String.class, "%path").width(250).valueFactory(new TreeItemPropertyValueFactory<>("path")).useDefaultCellValueFactory().buildColumn()
				.add(FilterOperator.class, "%operator").width(90).valueFactory(new TreeItemPropertyValueFactory<>("operator")).cellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(FXCollections.observableArrayList(FilterOperator.values()))).buildColumn()
				.add(String.class, "%value").width(90).valueFactory(new TreeItemPropertyValueFactory<>("value")).buildColumn()
				.add(String.class, "%second_value").width(90).valueFactory(new TreeItemPropertyValueFactory<>("value2")).buildColumn()
				.buildTreeTable());

		extraColumnsView = createTableEditorViewInNewTab(tabPane, "%extra_columns", ColumnDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%path").width(250).valueFactory(cd -> cd.getValue().pathProperty()).useDefaultCellValueFactory().buildColumn()
				.add(Integer.class, "%width_weight").width(75).valueFactory(cd -> cd.getValue().widthWeightProperty().asObject()).useDefaultCellValueFactory().buildColumn()
				.buildTable());

		overridesView = createTableEditorViewInNewTab(tabPane, "%extra_columns", RelationOverrideDefModel.class, tvf-> tvf
				.add(String.class, "%name").width(90).valueFactory(cd -> cd.getValue().nameProperty()).useDefaultCellValueFactory().buildColumn()
				.add(String.class, "%description").width(250).valueFactory(cd -> cd.getValue().descriptionProperty()).useDefaultCellValueFactory().buildColumn()
				.add(EntityDefModel.class, "%from_entity").width(250).valueFactory(cd -> cd.getValue().forEntityProperty()).buildColumn()
				.add(EntityDefModel.class, "%to_entity").width(250).valueFactory(cd -> cd.getValue().relationshipEntityProperty()).buildColumn()
				.buildTable());

		return contentView;
	}

	@SuppressWarnings("unchecked")
	private void updateDataTypeDetail(DataType oldDataType, DataType newDataType) {
		int index = 11;

		int i = gridPane.getChildren().size() - 1;
		while (i >= 0) {
			Node node = gridPane.getChildren().get(i);
			if (GridPane.getRowIndex(node) >= index) {
				gridPane.getChildren().remove(node);
			}
			i--;
		}
		DataType dataType = getModel().getDataType();
		lblTypeTitle.setText(dataType.toString());
		// String ByteArray Image
		if (dataType == DataType.String || dataType == DataType.ByteArray || dataType == DataType.Image) {
			spnMaxLength = nodeFactory.createSpinnerInsideGrid(gridPane, 1, index++, "%max_length");
			spnMinLength = nodeFactory.createSpinnerInsideGrid(gridPane, 1, index++, "%min_length");
			chbMultiline = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, index++, "%multiline");
		}
		// Single Double Decimal
		if (dataType == DataType.Single || dataType == DataType.Double || dataType == DataType.Decimal) {
			spnPrecision = nodeFactory.createSpinnerInsideGrid(gridPane, 1, index++, "%precision");
			spnScale = nodeFactory.createSpinnerInsideGrid(gridPane, 1, index++, "%scale");
			chbIsCurrency = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, index++, "%is_currency");
		}
		// Boolean Byte Decimal Double Int Long Short Single Date DateTime Time
		// String
		if (dataType == DataType.Boolean || dataType == DataType.Byte || dataType == DataType.Decimal
				|| dataType == DataType.Double || dataType == DataType.Int || dataType == DataType.Long
				|| dataType == DataType.Short || dataType == DataType.Single || dataType == DataType.Date
				|| dataType == DataType.DateTime || dataType == DataType.Time || dataType == DataType.String) {
			nodeFactory.createLabelInsideGrid(gridPane, "%default_value", index);
			Node defaultValueNode = editorByDataTypeFactory.create(getModel().getDataType(), null, null,
					getModel().defaultValueProperty());
			if (defaultValueNode != null) {
				GridPane.setMargin(defaultValueNode, new Insets(2, 2, 2, 2));
				gridPane.add(defaultValueNode, 1, index++);
			}
		}

		// Byte Decimal Double Int Long Short Single Date DateTime Time
		if (dataType == DataType.Byte || dataType == DataType.Decimal || dataType == DataType.Double
				|| dataType == DataType.Int || dataType == DataType.Long || dataType == DataType.Short
				|| dataType == DataType.Single || dataType == DataType.Date || dataType == DataType.DateTime
				|| dataType == DataType.Time) {
			nodeFactory.createLabelInsideGrid(gridPane, "%min_value", index);
			Node minValueNode = editorByDataTypeFactory.create(getModel().getDataType(), null, null,
					getModel().minValueProperty());
			if (minValueNode != null) {
				GridPane.setMargin(minValueNode, new Insets(2, 2, 2, 2));
				gridPane.add(minValueNode, 1, index++);
			}

			chbIncludeMinValue = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, index++, "%include_min_value");

			nodeFactory.createLabelInsideGrid(gridPane, "%max_value", index);
			Node maxValueNode = editorByDataTypeFactory.create(getModel().getDataType(), null, null,
					getModel().maxValueProperty());
			if (maxValueNode != null) {
				GridPane.setMargin(maxValueNode, new Insets(2, 2, 2, 2));
				gridPane.add(maxValueNode, 1, index++);
			}
			chbIncludeMaxValue = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, index++, "%include_max_value");

		}

		// Enum
		if (dataType == DataType.Enum) {
			cbxEnumDef = nodeFactory.createComboBoxInsideGrid(gridPane, 1, index++, "%enum");
			enumDefaultValueNode = (ComboBox<EnumValueDefModel>) editorByDataTypeFactory
					.create(getModel().getDataType(), null, null, getModel().defaultValueProperty());
			nodeFactory.createLabelInsideGrid(gridPane, "%default_value", index);
			if (enumDefaultValueNode != null) {
				GridPane.setMargin(enumDefaultValueNode, new Insets(2, 2, 2, 2));
				gridPane.add(enumDefaultValueNode, 1, index++);
			}

		}
		// Entity
		if (dataType == DataType.Entity || dataType == DataType.EntityList) {
			cbxRelatedEntity = nodeFactory.createComboBoxInsideGrid(gridPane, 1, index++, "%related_entity");
			cbxRelatedField = nodeFactory.createComboBoxInsideGrid(gridPane, 1, index++, "%related_field");
		}
		// ValueObject
		if (dataType == DataType.ValueObject || dataType == DataType.ValueObjectList) {
			cbxValueObject = nodeFactory.createComboBoxInsideGrid(gridPane, 1, index++, "%value_object");
			txfValueObjectTableName = nodeFactory.createTextFieldInsideGrid(gridPane, 1, index++, "%value_object_table_name");
		}

		// String
		if (dataType == DataType.String)
			txfRegularExpression = nodeFactory.createTextFieldInsideGrid(gridPane, 1, index++, "%regular_expression");

		// Date DateTime
		if (dataType == DataType.Date || dataType == DataType.DateTime)
			chbDateShowTime = nodeFactory.createCheckBoxInsideGrid(gridPane, 1, index++, "%date_show_time");

		bindFieldByDataType(dataType);

	}

	private void bindFieldByDataType(DataType dataType) {

		if (dataType == DataType.String || dataType == DataType.ByteArray) {
			IntegerSpinnerValueFactory integerValueFactory = new IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);

			bindBidirectional(integerValueFactory.valueProperty(), getModel().maxLengthProperty().asObject());
			spnMaxLength.setValueFactory(integerValueFactory);

			integerValueFactory = new IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
			bindBidirectional(integerValueFactory.valueProperty(), getModel().minLengthProperty().asObject());
			spnMinLength.setValueFactory(integerValueFactory);
			bindBidirectional(chbMultiline.selectedProperty(), getModel().multilineProperty());
		}

		if (dataType == DataType.Single || dataType == DataType.Double || dataType == DataType.Decimal) {
			IntegerSpinnerValueFactory integerValueFactory = new IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
			bindBidirectional(integerValueFactory.valueProperty(), getModel().precisionProperty().asObject());
			spnPrecision.setValueFactory(integerValueFactory);

			integerValueFactory = new IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
			bindBidirectional(integerValueFactory.valueProperty(), getModel().scaleProperty().asObject());
			spnScale.setValueFactory(integerValueFactory);
			bindBidirectional(chbIsCurrency.selectedProperty(), getModel().isCurrencyProperty());
		}

		if (dataType == DataType.Byte || dataType == DataType.Decimal || dataType == DataType.Double
				|| dataType == DataType.Int || dataType == DataType.Long || dataType == DataType.Short
				|| dataType == DataType.Single || dataType == DataType.Date || dataType == DataType.DateTime
				|| dataType == DataType.Time) {
			bindBidirectional(chbIncludeMinValue.selectedProperty(), getModel().includeMinValueProperty());
			bindBidirectional(chbIncludeMaxValue.selectedProperty(), getModel().includeMaxValueProperty());
		}

		if (dataType == DataType.Enum) {
			if (enums != null) {
				cbxEnumDef.setItems(enums);
				bindBidirectional(cbxEnumDef.valueProperty(), getModel().enumDefProperty());
				getModel().enumDefProperty().addListener((v, o, n) -> {
					updateEnumDefaultValue(n);
				});
				updateEnumDefaultValue(getModel().getEnumDef());
			}
		}

		if (dataType == DataType.Entity || dataType == DataType.EntityList) {
			if (entities != null) {
				cbxRelatedEntity.setItems(entities);
				cbxRelatedEntity.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
					if (n != null) {
						cbxRelatedField.setItems(new FilteredList<FieldDefModel>(n.getFields(),
								f -> f.getDataType() == DataType.Entity || f.getDataType() == DataType.EntityList));
					}
				});

				if (getModel().getRelationshipData() != null
						&& getModel().getRelationshipData().getRelationshipPart() != null) {
					if (getModel().getRelationshipData().getRelationshipPart().getToRelationshipPart()
							.getDomainObject() instanceof EntityDefModel) {
						auxiliarToEntityProperty.set(
								(EntityDefModel) getModel().getRelationshipData().getRelationshipPart()
										.getToRelationshipPart().getDomainObject());
						auxiliarToFieldProperty.set(
								getModel().getRelationshipData().getRelationshipPart().getToRelationshipPart()
										.getField());
					}
				}

				if (getModel().getRelationshipData() != null
						&& getModel().getRelationshipData().getRelationshipPart() != null) {
					bindBidirectional(cbxRelatedEntity.valueProperty(), auxiliarToEntityProperty);
				}

				if (getModel().getRelationshipData() != null
						&& getModel().getRelationshipData().getRelationshipPart() != null) {
					bindBidirectional(cbxRelatedField.valueProperty(), auxiliarToFieldProperty);
				}
			}
		}
		if (dataType == DataType.ValueObject || dataType == DataType.ValueObjectList) {
			if (valueObjects != null) {
				cbxValueObject.setItems(valueObjects);
				bindBidirectional(cbxValueObject.valueProperty(), getModel().valueObjectProperty());
				bindBidirectional(txfValueObjectTableName.textProperty(), getModel().valueObjectTableNameProperty());
			}
		}
		if (dataType == DataType.String)
			bindBidirectional(txfRegularExpression.textProperty(), getModel().regularExpressionProperty());

		if (dataType == DataType.Date || dataType == DataType.DateTime)
			bindBidirectional(chbDateShowTime.selectedProperty(), getModel().dateShowTimeProperty());

		if (getModel().getRelationshipData() != null) {
			filterValuesView.setModel(getModel().getRelationshipData().filterValuesProperty());
			extraColumnsView.setModel(getModel().getRelationshipData().extraColumnsProperty());
			overridesView.setModel(getModel().getRelationshipData().overridesProperty());
		}

	}

	private void updateEnumDefaultValue(EnumDefModel enumDefModel) {
		if (enumDefModel != null) {
			enumDefaultValueNode.setItems(enumDefModel.getValues());
			for (EnumValueDefModel value : enumDefModel.getValues()) {
				if (value.getId().equals(getModel().getDefaultValue()))
					enumDefaultValueNode.getSelectionModel().select(value);
			}
		}
	}

	@Override
	protected void bindFieldsToModel() {
		super.bindFieldsToModel();
		if (getModel() != null) {
			bindBidirectional(txfDatabaseName.textProperty(), getModel().dataBaseNameProperty());
			choDataType.setItems(FXCollections.observableArrayList(DataType.values()));
			bindBidirectional(choDataType.valueProperty(), getModel().dataTypeProperty());
			cbxFieldGroup.setItems(getModel().getDomainObjectOwner().getFieldGroups());
			bindBidirectional(cbxFieldGroup.valueProperty(), getModel().fieldGroupProperty());
			bind(cbxFieldSubgroup.itemsProperty(), Bindings.select(getModel().fieldGroupProperty(), "Subgroups"));
			bindBidirectional(cbxFieldSubgroup.valueProperty(), getModel().fieldSubgroupProperty());
			bindBidirectional(chbAllowNull.selectedProperty(), getModel().allowNullProperty());
			bindBidirectional(chbReadOnly.selectedProperty(), getModel().readOnlyProperty());
			bindBidirectional(chbMapToDatabase.selectedProperty(), getModel().mapToDatabaseProperty());
			bindBidirectional(chbHasVisualRepresentation.selectedProperty(),
					getModel().hasVisualRepresentationProperty());
			bindBidirectional(chbIsRepresentative.selectedProperty(), getModel().isRepresentativeProperty());

		}
	}

	public TreeTableModelEditorView<FilterValueDefModel> getFilterValuesView() {
		return filterValuesView;
	}

	public TableModelEditorView<ColumnDefModel> getExtraColumnsView() {
		return extraColumnsView;
	}

	public TableModelEditorView<RelationOverrideDefModel> getOverridesView() {
		return overridesView;
	}

	public void setEnums(ObservableList<EnumDefModel> enums) {
		this.enums = enums;
	}

	public void setEntities(ObservableList<EntityDefModel> entities) {
		this.entities = entities;
	}

	public void setValueObjects(ObservableList<ValueObjectDefModel> valueObjects) {
		this.valueObjects = valueObjects;
	}

	public ObjectProperty<EntityDefModel> getAuxiliarToEntityProperty() {
		return auxiliarToEntityProperty;
	}

	public void setAuxiliarToEntityProperty(ObjectProperty<EntityDefModel> auxiliarToEntityProperty) {
		this.auxiliarToEntityProperty = auxiliarToEntityProperty;
	}

	public ObjectProperty<FieldDefModel> getAuxiliarToFieldProperty() {
		return auxiliarToFieldProperty;
	}

	public void setAuxiliarToFieldProperty(ObjectProperty<FieldDefModel> auxiliarToFieldProperty) {
		this.auxiliarToFieldProperty = auxiliarToFieldProperty;
	}

}
