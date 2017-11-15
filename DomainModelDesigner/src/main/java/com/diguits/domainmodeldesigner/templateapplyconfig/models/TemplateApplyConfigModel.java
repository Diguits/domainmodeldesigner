package com.diguits.domainmodeldesigner.templateapplyconfig.models;

import java.util.UUID;

import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class TemplateApplyConfigModel extends TemplateProjectApplyConfigItemModel {

	public TemplateApplyConfigModel() {
		super();
		templateProperty().addListener((v, o, n) -> {
			if (n != null) {
				this.setName(n.getName());
				this.setDescription(n.getDescription());
			} else {
				this.setName("");
				this.setDescription("");
			}
		});

		//For the time being it is just needed two apply class name
				modelIdProperty().addListener((v, o, n) -> {
					if (getModelIds().size() < 1)
						getModelIds().add(getModelId());
					else if(!getModelIds().get(0).equals(getModelId()))
						getModelIds().set(0, getModelId());
				});
				modelId1Property().addListener((v, o, n) -> {
					if (getModelIds().size() < 1)
						getModelIds().add(getModelId());
					if (getModelIds().size() < 2)
						getModelIds().add(getModelId1());
					else if(!getModelIds().get(1).equals(getModelId1()))
						getModelIds().set(1, getModelId1());
				});
				getModelIds().addListener(new ListChangeListener<UUID>(){

					@Override
					public void onChanged(javafx.collections.ListChangeListener.Change<? extends UUID> c) {
						if(c.next()){
							if(c.getAddedSize()>0 || c.getRemovedSize()>0){
								if(getModelIds().size()>0)
									setModelId(getModelIds().get(0));
								if(getModelIds().size()>1)
									setModelId1(getModelIds().get(1));
							}
						}
					}
				});
	}

	private StringProperty conditionExpression;
	private StringProperty outputFilenameExpression;
	private BooleanProperty inMemory;
	private BooleanProperty waitForBefore;
	private StringProperty rootOutputDir;
	private StringProperty outputPathExpression;
	private ObjectProperty<TemplateDefModel> template;
	private ListProperty<UUID> modelIds;
	private ObjectProperty<UUID> modelId;
	private ObjectProperty<UUID> modelId1;

	private ListProperty<TemplateParamValueModel> templateParamValues;

	public String getConditionExpression() {
		if (conditionExpression != null)
			return conditionExpression.get();
		return "";
	}

	public void setConditionExpression(String conditionExpression) {
		if (this.conditionExpression != null || conditionExpression == null || !conditionExpression.equals("")) {
			conditionExpressionProperty().set(conditionExpression);
		}
	}

	public StringProperty conditionExpressionProperty() {
		if (conditionExpression == null) {
			conditionExpression = new SimpleStringProperty(this, "conditionExpression", "");
		}
		return conditionExpression;
	}

	public String getOutputFilenameExpression() {
		if (outputFilenameExpression != null)
			return outputFilenameExpression.get();
		return "";
	}

	public void setOutputFilenameExpression(String outputFilenameExpression) {
		if (this.outputFilenameExpression != null || outputFilenameExpression == null || !outputFilenameExpression.equals("")) {
			outputFilenameExpressionProperty().set(outputFilenameExpression);
		}
	}

	public StringProperty outputFilenameExpressionProperty() {
		if (outputFilenameExpression == null) {
			outputFilenameExpression = new SimpleStringProperty(this, "outputFilenameExpression", "");
		}
		return outputFilenameExpression;
	}

	public boolean getInMemory() {
		if (inMemory != null)
			return inMemory.get();
		return false;
	}

	public void setInMemory(boolean inMemory) {
		if (this.inMemory != null || inMemory != false) {
			inMemoryProperty().set(inMemory);
		}
	}

	public BooleanProperty inMemoryProperty() {
		if (inMemory == null) {
			inMemory = new SimpleBooleanProperty(this, "inMemory", false);
		}
		return inMemory;
	}

	public boolean getWaitForBefore() {
		if (waitForBefore != null)
			return waitForBefore.get();
		return false;
	}

	public void setWaitForBefore(boolean waitForBefore) {
		if (this.waitForBefore != null || waitForBefore != false) {
			waitForBeforeProperty().set(waitForBefore);
		}
	}

	public BooleanProperty waitForBeforeProperty() {
		if (waitForBefore == null) {
			waitForBefore = new SimpleBooleanProperty(this, "waitForBefore", false);
		}
		return waitForBefore;
	}

	public String getRootOutputDir() {
		if (rootOutputDir != null)
			return rootOutputDir.get();
		return "";
	}

	public void setRootOutputDir(String rootOutputDir) {
		if (this.rootOutputDir != null || rootOutputDir == null || !rootOutputDir.equals("")) {
			rootOutputDirProperty().set(rootOutputDir);
		}
	}

	public StringProperty rootOutputDirProperty() {
		if (rootOutputDir == null) {
			rootOutputDir = new SimpleStringProperty(this, "rootOutputDir", "");
		}
		return rootOutputDir;
	}

	public String getOutputPathExpression() {
		if (outputPathExpression != null)
			return outputPathExpression.get();
		return "";
	}

	public void setOutputPathExpression(String outputPathExpression) {
		if (this.outputPathExpression != null || outputPathExpression == null || !outputPathExpression.equals("")) {
			outputPathExpressionProperty().set(outputPathExpression);
		}
	}

	public StringProperty outputPathExpressionProperty() {
		if (outputPathExpression == null) {
			outputPathExpression = new SimpleStringProperty(this, "outputPathExpression", "");
		}
		return outputPathExpression;
	}

	public TemplateDefModel getTemplate() {
		if (template != null)
			return template.get();
		return null;
	}

	public void setTemplate(TemplateDefModel template) {
		if (this.template != null || template != null) {
			templateProperty().set(template);
		}
	}

	public ObjectProperty<TemplateDefModel> templateProperty() {
		if (template == null) {
			template = new SimpleObjectProperty<TemplateDefModel>(this, "template", null);
		}
		return template;
	}

	public UUID getModelId() {
		if (modelId != null)
			return modelId.get();
		return null;
	}

	public void setModelId(UUID modelId) {
		if (this.modelId != null || modelId != null) {
			modelIdProperty().set(modelId);
		}
	}

	public ObjectProperty<UUID> modelIdProperty() {
		if (modelId == null) {
			modelId = new SimpleObjectProperty<UUID>(this, "modelId", null);
		}
		return modelId;
	}

	public UUID getModelId1() {
		if (modelId1 != null)
			return modelId1.get();
		return null;
	}

	public void setModelId1(UUID modelId1) {
		if (this.modelId1 != null || modelId1 != null) {
			modelId1Property().set(modelId1);
		}
	}

	public ObjectProperty<UUID> modelId1Property() {
		if (modelId1 == null) {
			modelId1 = new SimpleObjectProperty<UUID>(this, "modelId1", null);
		}
		return modelId1;
	}

	public ObservableList<UUID> getModelIds() {
		return modelIdsProperty().get();
	}

	public void setModelIds(ObservableList<UUID> modelIds) {
		if (this.modelIds != null || modelIds != null) {
			modelIdsProperty().set(modelIds);
		}
	}

	public ListProperty<UUID> modelIdsProperty() {
		if (modelIds == null) {
			modelIds = new SimpleListProperty<UUID>(this, "modelIds", null);
			modelIds.set(FXCollections.observableArrayList());
		}
		return modelIds;
	}

	public ObservableList<TemplateParamValueModel> getTemplateParamValues() {
		return templateParamValuesProperty().get();
	}

	public void setTemplateParamValues(ObservableList<TemplateParamValueModel> templateParamValues) {
		if (this.templateParamValues!= null || templateParamValues != null) {
			templateParamValuesProperty().set(templateParamValues);
		}
	}

	public TemplateProjectApplyConfigModel getProjectOwner() {
		if (owner != null)
			return (TemplateProjectApplyConfigModel) owner.get();
		return null;
	}

	public void setProjectOwner(TemplateProjectApplyConfigModel owner) {
		if (this.owner != null || owner != null) {
			ownerProperty().set(owner);
		}
	}

	public ListProperty<TemplateParamValueModel> templateParamValuesProperty() {
		if(templateParamValues == null){
			templateParamValues = new SimpleListProperty<TemplateParamValueModel>(this, "templateParamValues", null);
		templateParamValues.set(FXCollections.observableArrayList());
		}
		return templateParamValues;
	}
}
