package com.diguits.javafx.views;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;

import com.diguits.javafx.controls.TimeSpinner;
import com.diguits.javafx.views.helpers.AnchorPaneHelper;
import com.diguits.javafx.views.helpers.ButtonHelper;
import com.diguits.javafx.views.helpers.CheckBoxHelper;
import com.diguits.javafx.views.helpers.CheckComboBoxHelper;
import com.diguits.javafx.views.helpers.ChoiceBoxHelper;
import com.diguits.javafx.views.helpers.ComboBoxHelper;
import com.diguits.javafx.views.helpers.GridPaneHelper;
import com.diguits.javafx.views.helpers.LabelHelper;
import com.diguits.javafx.views.helpers.ListViewHelper;
import com.diguits.javafx.views.helpers.SpinnerHelper;
import com.diguits.javafx.views.helpers.TabPaneHelper;
import com.diguits.javafx.views.helpers.TableViewHelper;
import com.diguits.javafx.views.helpers.TextAreaHelper;
import com.diguits.javafx.views.helpers.TextFieldHelper;
import com.diguits.javafx.views.helpers.ToolBarHelper;
import com.diguits.javafx.views.helpers.TreeViewHelper;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.DirectoryChooser;

public class NodeFactoryHelperBase implements INodeFactoryHelper {

    protected ResourceBundle resources;

    @Override
    public GridPane createGridPaneForEdit() {
        return GridPaneHelper.createGridPaneForEdit();
    }

    @Override
    public GridPane createGridPaneForEditFourColumns() {
        return GridPaneHelper.createGridPaneForEditFourColumns();
    }

    @Override
    public RowConstraints createRowConstraints() {
        return GridPaneHelper.createRowConstraints();
    }

    @Override
    public RowConstraints createRowConstraints(double prefHeight) {
        return GridPaneHelper.createRowConstraints(prefHeight);
    }

    @Override
    public void addRowConstraints(GridPane gridPane, int count) {
        GridPaneHelper.addRowConstraints(gridPane, count);
    }

    @Override
    public GridPane createGridPaneForSwap() {
        return GridPaneHelper.createGridPaneForSwap();
    }

    @Override
    public GridPane createGridPaneTwoSameColumns() {
        return GridPaneHelper.createGridPaneTwoSameColumns();
    }

    @Override
    public AnchorPane createAnchorPane() {
        return AnchorPaneHelper.createAnchorPane();
    }

    @Override
    public void fitToAnchorPane(Node node) {
        AnchorPaneHelper.fitToAnchorPane(node);
    }

    @Override
    public AnchorPane addAndFitToAnchorPane(Node node) {
        return AnchorPaneHelper.addAndFitToAnchorPane(node);
    }

    @Override
    public TabPane createTabPane() {
        return TabPaneHelper.createTabPane();
    }

    @Override
    public Tab createTab(String resourceName) {
        return TabPaneHelper.createTab(resourceName);
    }

    @Override
    public Tab createAndAddTab(TabPane tabPane, String resourceName) {
        return TabPaneHelper.createAndAddTab(tabPane, getStringResource(resourceName));
    }

    @Override
    public Label createLabel(String resourceName) {
        return LabelHelper.createLabel(resourceName);
    }

    @Override
    public Label createLabelInsideGrid(GridPane gridPane, String resourceName, int columnIndex, int rowIndex) {
        return LabelHelper.createLabelInsideGrid(gridPane, getStringResource(resourceName) + ":", columnIndex,
                rowIndex);
    }

    @Override
    public Label createLabelInsideGrid(GridPane gridPane, String resourceName, int columnIndex, int rowIndex,
                                       String labelResourceName) {
        return LabelHelper.createLabelInsideGrid(gridPane, getStringResource(resourceName) + ":", columnIndex, rowIndex,
                getStringResource(labelResourceName) + ":");
    }

    @Override
    public Label createLabelInsideGrid(GridPane gridPane, String resourceName, String labelResourceName) {
        return LabelHelper.createLabelInsideGrid(gridPane, getStringResource(resourceName),
                getStringResource(labelResourceName) + ":");
    }

    @Override
    public Label createLabelInsideGrid(GridPane gridPane, String resourceName, int rowIndex) {
        return LabelHelper.createLabelInsideGrid(gridPane, getStringResource(resourceName) + ":", rowIndex);
    }

    @Override
    public Label createLabelInsideGrid(GridPane gridPane, String resourceName) {
        return LabelHelper.createLabelInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public TextField createTextField() {
        return TextFieldHelper.createTextField();
    }

    @Override
    public TextField createTextField(int maxLength) {
        return TextFieldHelper.createTextField();
    }

    @Override
    public TextField createTextFieldInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return TextFieldHelper.createTextFieldInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public TextField createTextFieldInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName) {
        return TextFieldHelper.createTextFieldInsideGrid(gridPane, columnIndex, rowIndex,
                getStringResource(resourceName) + ":");
    }

    @Override
    public TextField createTextFieldInsideGrid(GridPane gridPane, String resourceName) {
        return TextFieldHelper.createTextFieldInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public TextArea createTextArea() {
        return TextAreaHelper.createTextArea();
    }

    @Override
    public TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return TextAreaHelper.createTextAreaInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName) {
        return TextAreaHelper.createTextAreaInsideGrid(gridPane, columnIndex, rowIndex,
                getStringResource(resourceName) + ":");
    }

    @Override
    public TextArea createTextAreaInsideGrid(GridPane gridPane, String resourceName) {
        return TextAreaHelper.createTextAreaInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public CheckBox createCheckBox() {
        return CheckBoxHelper.createCheckBox();
    }

    @Override
    public CheckBox createCheckBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return CheckBoxHelper.createCheckBoxInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public CheckBox createCheckBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String resourceName) {
        return CheckBoxHelper.createCheckBoxInsideGrid(gridPane, columnIndex, rowIndex,
                getStringResource(resourceName) + ":");
    }

    @Override
    public CheckBox createCheckBoxInsideGrid(GridPane gridPane, String resourceName) {
        return CheckBoxHelper.createCheckBoxInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public <T> ComboBox<T> createComboBox() {
        return ComboBoxHelper.createComboBox();
    }

    @Override
    public <T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return ComboBoxHelper.createComboBoxInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public <T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
                                                    String resourceName) {
        return ComboBoxHelper.createComboBoxInsideGrid(gridPane, columnIndex, rowIndex,
                getStringResource(resourceName) + ":");
    }

    @Override
    public <T> ComboBox<T> createComboBoxInsideGrid(GridPane gridPane, String resourceName) {
        return ComboBoxHelper.createComboBoxInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public <T> ChoiceBox<T> createChoiceBox() {
        return ChoiceBoxHelper.createChoiceBox();
    }

    @Override
    public <T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return ChoiceBoxHelper.createChoiceBoxInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public <T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
                                                      String resourceName) {
        return ChoiceBoxHelper.createChoiceBoxInsideGrid(gridPane, columnIndex, rowIndex,
                getStringResource(resourceName) + ":");
    }

    @Override
    public <T> ChoiceBox<T> createChoiceBoxInsideGrid(GridPane gridPane, String resourceName) {
        return ChoiceBoxHelper.createChoiceBoxInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public <T> CheckComboBox<T> createCheckComboBox() {
        return CheckComboBoxHelper.createCheckComboBox();
    }

    @Override
    public <T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return CheckComboBoxHelper.createCheckComboBoxInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public <T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
                                                              String resourceName) {
        return CheckComboBoxHelper.createCheckComboBoxInsideGrid(gridPane, columnIndex, rowIndex,
                getStringResource(resourceName) + ":");
    }

    @Override
    public <T> CheckComboBox<T> createCheckComboBoxInsideGrid(GridPane gridPane, String resourceName) {
        return CheckComboBoxHelper.createCheckComboBoxInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public <T> Spinner<T> createSpinner() {
        return SpinnerHelper.createSpinner();
    }

    @Override
    public Spinner<Integer> createIntSpinner() {
        return SpinnerHelper.createIntSpinner();
    }

    @Override
    public Spinner<Integer> createIntSpinner(int minValue, int maxValue) {
        return SpinnerHelper.createIntSpinner(minValue, maxValue);
    }

    @Override
    public Spinner<Long> createLongSpinner() {
        return SpinnerHelper.createLongSpinner();
    }

    @Override
    public Spinner<Long> createLongSpinner(long minValue, long maxValue) {
        return SpinnerHelper.createLongSpinner(minValue, maxValue);
    }

    @Override
    public Spinner<Double> createDoubleSpinner() {
        return SpinnerHelper.createDoubleSpinner();
    }

    @Override
    public Spinner<Double> createDoubleSpinner(Double minValue, Double maxValue) {
        return SpinnerHelper.createDoubleSpinner(minValue, maxValue);
    }

    @Override
    public <T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return SpinnerHelper.createSpinnerInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public <T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, int columnIndex, int rowIndex,
                                                  String resourceName) {
        return SpinnerHelper.createSpinnerInsideGrid(gridPane, columnIndex, rowIndex,
                getStringResource(resourceName) + ":");
    }

    @Override
    public <T> Spinner<T> createSpinnerInsideGrid(GridPane gridPane, String resourceName) {
        return SpinnerHelper.createSpinnerInsideGrid(gridPane, getStringResource(resourceName) + ":");
    }

    @Override
    public TimeSpinner createTimeSpinner() {
        return SpinnerHelper.createTimeSpinner();
    }

    @Override
    public DatePicker createDatePicker() {
        return SpinnerHelper.createDatePicker();
    }

    @Override
    public <T> TableView<T> createTableView() {
        return TableViewHelper.createTableView();
    }

    @Override
    public <T, V> TableColumn<T, V> createAndAddTableColumn(TableView<T> tableView, String resourceName, double width) {
        return TableViewHelper.createAndAddTableColumn(tableView, getStringResource(resourceName), width);
    }

    @Override
    public <T, V> TableColumn<T, V> createTableColumn(String resourceName, double width) {
        return TableViewHelper.createTableColumn(resourceName, width);
    }

    @Override
    public <T> TreeTableView<T> createTreeTableView() {
        return TreeViewHelper.createTreeTableView();
    }

    @Override
    public <T, V> TreeTableColumn<T, V> createAndAddTreeTableColumn(TreeTableView<T> treeTableView, String resourceName,
                                                                    double width) {
        return TreeViewHelper.createAndAddTreeTableColumn(treeTableView, getStringResource(resourceName), width);
    }

    @Override
    public <T, V> TreeTableColumn<T, V> createTreeTableColumn(String resourceName, double width) {
        return TreeViewHelper.createTreeTableColumn(resourceName, width);
    }

    @Override
    public <T> TreeView<T> createTree() {
        return TreeViewHelper.createTree();
    }

    @Override
    public <T> ListView<T> createListView() {
        return ListViewHelper.createListView();
    }

    @Override
    public <T> ListView<T> createListViewInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
        return ListViewHelper.createListViewInsideGrid(gridPane, columnIndex, rowIndex);
    }

    @Override
    public ToolBar createToolBar() {
        return ToolBarHelper.createToolBar();
    }

    @Override
    public Button createButton16x16(String imageUrl) {
        return ButtonHelper.createButton16x16(imageUrl);
    }

    @Override
    public ImageView createImageView16x16(String imageUrl) {
        return ButtonHelper.createImageView16x16(imageUrl);
    }

    @Override
    public DirectoryChooser createDirectoryChooser(String path) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = new File(path);
        if (file.exists())
            directoryChooser.setInitialDirectory(file);
        return directoryChooser;
    }

    protected String getStringResource(String resourceName) {
        if (resourceName != null && resourceName.startsWith("%") && !resourceName.startsWith("%%"))
            try {
                return resources.getString(resourceName.substring(1));
            } catch (Exception e) {
                e.printStackTrace();
                return resourceName.replace("%", " ").replace("_", " ");
            }
        return resourceName.replace("%%", "%");
    }

    @Override
    public void setResource(ResourceBundle resources) {
        this.resources = resources;
    }

    @Override
    public ScrollPane createScrollPane() {
        return createScrollPane(true);
    }

    @Override
    public ScrollPane createScrollPane(boolean fitToWidth) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(fitToWidth);
        return scrollPane;
    }

    @Override
    public ScrollPane wrapInScrollPane(Node node) {
        ScrollPane scrollPane = createScrollPane();
        scrollPane.setContent(addAndFitToAnchorPane(node));
        return scrollPane;
    }
}
