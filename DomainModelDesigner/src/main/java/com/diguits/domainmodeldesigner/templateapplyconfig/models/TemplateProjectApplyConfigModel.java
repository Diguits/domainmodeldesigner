package com.diguits.domainmodeldesigner.templateapplyconfig.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleListProperty;

public class TemplateProjectApplyConfigModel extends TemplateProjectApplyConfigItemModel{

	public TemplateProjectApplyConfigModel() {
		super();
	}
	private ListProperty<TemplateApplyConfigModel> templatesConfig;
	private StringProperty outputPath;
	private BooleanProperty isDefault;


	public ObservableList<TemplateApplyConfigModel> getTemplatesConfig() {
		return templatesConfigProperty().get();
	}

	public void setTemplatesConfig(ObservableList<TemplateApplyConfigModel> templatesConfig) {
		if (this.templatesConfig!= null || templatesConfig != null) {
			templatesConfigProperty().set(templatesConfig);
		}
	}

	public ListProperty<TemplateApplyConfigModel> templatesConfigProperty() {
		if(templatesConfig == null){
			templatesConfig = new SimpleListProperty<TemplateApplyConfigModel>(this, "templatesConfig", null);
		templatesConfig.set(FXCollections.observableArrayList());
		}
		return templatesConfig;
	}

	public String getOutputPath() {
		if (outputPath!= null)
			return outputPath.get();
		return "";
	}

	public void setOutputPath(String outputPath) {
		if (this.outputPath!= null || outputPath == null || !outputPath.equals("")) {
			outputPathProperty().set(outputPath);
		}
	}

	public StringProperty outputPathProperty() {
		if(outputPath == null){
			outputPath = new SimpleStringProperty(this, "outputPath", "");
		}
		return outputPath;
	}

	public boolean getIsDefault() {
		if (isDefault != null)
			return isDefault.get();
		return false;
	}

	public void setIsDefault(boolean isDefault) {
		if (this.isDefault != null || isDefault != false) {
			isDefaultProperty().set(isDefault);
		}
	}

	public BooleanProperty isDefaultProperty() {
		if (isDefault == null) {
			isDefault = new SimpleBooleanProperty(this, "isDefault", false);
		}
		return isDefault;
	}

}
