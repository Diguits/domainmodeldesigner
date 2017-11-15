package com.diguits.javafx.views;

import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import com.diguits.javafx.controls.TimeSpinner;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.DirectoryChooser;

public interface INodeFactoryHelper {

	// GridPane
	GridPane createGridPaneForEdit();

	GridPane createGridPaneForEditFourColumns();

	RowConstraints createRowConstraints();

	RowConstraints createRowConstraints(double prefHeight);

	void addRowConstraints(GridPane gridPane, int count);

	GridPane createGridPaneForSwap();

	GridPane createGridPaneTwoSameColumns();

	// AnchorPane
	AnchorPane createAnchorPane();

	void fitToAnchorPane(Node node);

	AnchorPane addAndFitToAnchorPane(Node node);

	// TabPane
	TabPane createTabPane();

	Tab createTab(String resourceName);

	Tab createAndAddTab(TabPane tabPane, String resourceName);

	// Label
	Label createLabel(String resourceName);

	Label createLabelInsideGrid(GridPane gridPane, String resourceName, int columnIndex, int rowIndex);

	Label createLabelInsideGrid(GridPane gridPane, String resourceName, int columnIndex, int rowIndex,
			String labelResourceName);

	Label createLabelInsideGrid(GridPane gridPane, String resourceName, String labelResourceName);

	Label createLabelInsideGrid(GridPane gridPane, String resourceName, int rowIndex);

	Label createLabelInsideGrid(GridPane gridPane, String resourceName);

	// TextField
	TextField createTextField();

	TextField createTextField(int maxLength);

	TextField createTextFieldInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	TextField createTextFieldInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName);

	TextField createTextFieldInsideGrid(GridPane gridPane, String resourceName);

	// TextArea
	TextArea createTextArea();

	TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName);

	TextArea createTextAreaInsideGrid(GridPane gridPane, String resourceName);

	// CheckBox
	CheckBox createCheckBox();

	CheckBox createCheckBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	CheckBox createCheckBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName);

	CheckBox createCheckBoxInsideGrid(GridPane gridPane, String resourceName);

	// ComboBox
	<T> ComboBox<T> createComboBox();

	<T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	<T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName);

	<T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, String resourceName);

	// ChoiceBox
	<T> ChoiceBox<T> createChoiceBox();

	<T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	<T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName);

	<T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, String resourceName);

	// CheckComboBox
	<T> CheckComboBox<T> createCheckComboBox();

	<T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	<T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
			String resourceName);

	<T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, String resourceName);

	// Spinner
	<T> Spinner<T> createSpinner();

	Spinner<Integer> createIntSpinner();

	Spinner<Integer> createIntSpinner(int minValue, int maxValue);

	Spinner<Long> createLongSpinner();

	Spinner<Long> createLongSpinner(long minValue, long maxValue);

	Spinner<Double> createDoubleSpinner();

	Spinner<Double> createDoubleSpinner(Double minValue, Double maxValue);

	<T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	<T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName);

	<T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, String resourceName);

	TimeSpinner createTimeSpinner();

	DatePicker createDatePicker();

	// TableView
	<T> TableView<T> createTableView();

	<T, V> TableColumn<T, V> createAndAddTableColumn(TableView<T> tableView, String resourceName, double width);

	<T, V> TableColumn<T, V> createTableColumn(String resourceName, double width);

	// TreeTableView
	<T> TreeTableView<T> createTreeTableView();

	<T, V> TreeTableColumn<T, V> createAndAddTreeTableColumn(TreeTableView<T> treeTableView, String resourceName,
			double width);

	<T, V> TreeTableColumn<T, V> createTreeTableColumn(String resourceName, double width);

	<T> TreeView<T> createTree();

	// ListView
	<T> ListView<T> createListView();

	<T> ListView<T> createListViewInsideGrid(GridPane gridPane, int columnIndex, int rowIndex);

	// ToolBar
	ToolBar createToolBar();

	// Button
	Button createButton16x16(String imageUrl);

	ImageView createImageView16x16(String imageUrl);

	// Chooser
	DirectoryChooser createDirectoryChooser(String path);

	void setResource(ResourceBundle resources);
}
