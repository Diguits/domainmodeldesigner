package com.diguits.domainmodeldesigner.domainmodel.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class EnumDefModel extends SimpleDomainObjectDefModel  {

	public EnumDefModel() {
		super();
	}
	private BooleanProperty useTitle;
	private ListProperty<EnumValueDefModel> values;
	private BooleanProperty useIcon;
	private BooleanProperty useColor;

	public boolean getUseTitle() {
		if (useTitle!= null)
			return useTitle.get();
		return false;
	}

	public void setUseTitle(boolean useTitle) {
		if (this.useTitle!= null || useTitle != false) {
			useTitleProperty().set(useTitle);
		}
	}

	public BooleanProperty useTitleProperty() {
		if(useTitle == null){
			useTitle = new SimpleBooleanProperty(this, "useTitle", false);
		}
		return useTitle;
	}

	public ObservableList<EnumValueDefModel> getValues() {
		return valuesProperty().get();
	}

	public void setValues(ObservableList<EnumValueDefModel> values) {
		if (this.values!= null || values != null) {
			valuesProperty().set(values);
		}
	}

	public ListProperty<EnumValueDefModel> valuesProperty() {
		if(values == null){
			values = new SimpleListProperty<EnumValueDefModel>(this, "values", null);
		values.set(FXCollections.observableArrayList());
		}
		return values;
	}

	public boolean getUseIcon() {
		if (useIcon!= null)
			return useIcon.get();
		return false;
	}

	public void setUseIcon(boolean useIcon) {
		if (this.useIcon!= null || useIcon != false) {
			useIconProperty().set(useIcon);
		}
	}

	public BooleanProperty useIconProperty() {
		if(useIcon == null){
			useIcon = new SimpleBooleanProperty(this, "useIcon", false);
		}
		return useIcon;
	}

	public boolean getUseColor() {
		if (useColor!= null)
			return useColor.get();
		return false;
	}

	public void setUseColor(boolean useColor) {
		if (this.useColor!= null || useColor != false) {
			useColorProperty().set(useColor);
		}
	}

	public BooleanProperty useColorProperty() {
		if(useColor == null){
			useColor = new SimpleBooleanProperty(this, "useColor", false);
		}
		return useColor;
	}

	@Override
	public void accept(IEntityDefinitionModelVisitor visitor, BaseDefModel owner) {
		super.accept(visitor, owner);
		for (EnumValueDefModel enumValueDefModel : values)
        {
            enumValueDefModel.accept(visitor, this);
        }
        visitor.visitEnumDefModel(this, owner);
	}

	public BoundedContextDefModel getBoundedContextOwner(){
		return (BoundedContextDefModel) getOwner();
	}
}
