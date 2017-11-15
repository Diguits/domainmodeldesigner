package com.diguits.domainmodeldesigner.template.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class TemplateDefModel extends TemplateProjectItemDefModel {

	public TemplateDefModel() {
		super();

		// For the time being it is just needed two apply class name
		applyClassNameProperty().addListener((v, o, n) -> {
			if(n==null || n.isEmpty())
				getClassNamesToApplyOver().clear();
			else if (getClassNamesToApplyOver().size() < 1)
				getClassNamesToApplyOver().add(getApplyClassName());
			else if (!getClassNamesToApplyOver().get(0).equals(getApplyClassName()))
				getClassNamesToApplyOver().set(0, getApplyClassName());
		});
		applyClassName1Property().addListener((v, o, n) -> {
			if((n==null || n.isEmpty()) && getClassNamesToApplyOver().size() > 1)
				getClassNamesToApplyOver().remove(1);
			else if (getClassNamesToApplyOver().size() < 1)
				getClassNamesToApplyOver().add(getApplyClassName());
			if (getClassNamesToApplyOver().size() < 2)
				getClassNamesToApplyOver().add(getApplyClassName1());
			else if (!getClassNamesToApplyOver().get(1).equals(getApplyClassName1()))
				getClassNamesToApplyOver().set(1, getApplyClassName1());
		});
		getClassNamesToApplyOver().addListener(new ListChangeListener<String>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
				if (c.next()) {
					if (c.getAddedSize() > 0 || c.getRemovedSize() > 0) {
						if (getClassNamesToApplyOver().size() > 0)
							setApplyClassName(getClassNamesToApplyOver().get(0));
						if (getClassNamesToApplyOver().size() > 1)
							setApplyClassName1(getClassNamesToApplyOver().get(1));
					}
				}
			}
		});
	}

	private StringProperty outputFileNameExpression;
	private ListProperty<String> classNamesToApplyOver;
	private StringProperty path;
	private StringProperty source;
	private StringProperty applyClassName;
	private StringProperty applyClassName1;

	private ListProperty<TemplateParameterDefModel> parameters;

	public String getPath() {
		if (path != null)
			return path.get();
		return "";
	}

	public void setPath(String path) {
		if (this.path != null || path == null || !path.equals("")) {
			pathProperty().set(path);
		}
	}

	public StringProperty pathProperty() {
		if (path == null) {
			path = new SimpleStringProperty(this, "path", "");
		}
		return path;
	}

	public String getOutputFileNameExpression() {
		if (outputFileNameExpression != null)
			return outputFileNameExpression.get();
		return "";
	}

	public void setOutputFileNameExpression(String outputFileNameExpression) {
		if (this.outputFileNameExpression != null || outputFileNameExpression == null
				|| !outputFileNameExpression.equals("")) {
			outputFileNameExpressionProperty().set(outputFileNameExpression);
		}
	}

	public StringProperty outputFileNameExpressionProperty() {
		if (outputFileNameExpression == null) {
			outputFileNameExpression = new SimpleStringProperty(this, "outputFileNameExpression", "");
		}
		return outputFileNameExpression;
	}

	public ObservableList<String> getClassNamesToApplyOver() {
		return classNamesToApplyOverProperty().get();
	}

	public void setClassNamesToApplyOver(ObservableList<String> classNamesToApplyOver) {
		if (this.classNamesToApplyOver != null || classNamesToApplyOver != null) {
			classNamesToApplyOverProperty().set(classNamesToApplyOver);
		}
	}

	public ListProperty<String> classNamesToApplyOverProperty() {
		if (classNamesToApplyOver == null) {
			classNamesToApplyOver = new SimpleListProperty<String>(this, "classNamesToApplyOver", null);
			classNamesToApplyOver.set(FXCollections.observableArrayList());
		}
		return classNamesToApplyOver;
	}

	public String getSource() {
		if (source != null)
			return source.get();
		return "";
	}

	public void setSource(String source) {
		if (this.source != null || source == null || !source.equals("")) {
			sourceProperty().set(source);
		}
	}

	public StringProperty sourceProperty() {
		if (source == null) {
			source = new SimpleStringProperty(this, "source", "");
		}
		return source;
	}

	public String getApplyClassName() {
		if (applyClassName != null)
			return applyClassName.get();
		return "";
	}

	public void setApplyClassName(String applyClassName) {
		if (this.applyClassName != null || applyClassName == null || !applyClassName.equals("")) {
			applyClassNameProperty().set(applyClassName);
		}
	}

	public StringProperty applyClassNameProperty() {
		if (applyClassName == null) {
			applyClassName = new SimpleStringProperty(this, "applyClassName", "");
		}
		return applyClassName;
	}

	public String getApplyClassName1() {
		if (applyClassName1 != null)
			return applyClassName1.get();
		return "";
	}

	public void setApplyClassName1(String applyClassName1) {
		if (this.applyClassName1 != null || applyClassName1 == null || !applyClassName1.equals("")) {
			applyClassName1Property().set(applyClassName1);
		}
	}

	public StringProperty applyClassName1Property() {
		if (applyClassName1 == null) {
			applyClassName1 = new SimpleStringProperty(this, "applyClassName1", "");
		}
		return applyClassName1;
	}

	public ObservableList<TemplateParameterDefModel> getParameters() {
		return parametersProperty().get();
	}

	public void setParameters(ObservableList<TemplateParameterDefModel> templateParameters) {
		if (this.parameters != null || templateParameters != null) {
			parametersProperty().set(templateParameters);
		}
	}

	public TemplateGroupDefModel getGroupOwner() {
		if (owner != null)
			return (TemplateGroupDefModel) owner.get();
		return null;
	}

	public void setGroupOwner(TemplateGroupDefModel owner) {
		if (this.owner != null || owner != null) {
			ownerProperty().set(owner);
		}
	}

	public TemplateProjectDefModel getProject() {
		if (getGroupOwner() != null)
			return getGroupOwner().getProjectOwner();
		return null;
	}

	public ListProperty<TemplateParameterDefModel> parametersProperty() {
		if (parameters == null) {
			parameters = new SimpleListProperty<TemplateParameterDefModel>(this, "templateParameters", null);
			parameters.set(FXCollections.observableArrayList());
		}
		return parameters;
	}
}
