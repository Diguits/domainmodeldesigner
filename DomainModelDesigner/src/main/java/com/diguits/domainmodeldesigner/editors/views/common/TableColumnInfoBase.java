package com.diguits.domainmodeldesigner.editors.views.common;

public class TableColumnInfoBase<TModel, TDataType> {

	protected String captionResource;
	protected int width;

	protected boolean useDefaultCellValueFactory;
	protected Class<TDataType> dataType;

	public TableColumnInfoBase(Class<TDataType> dataType, String captionResource) {
		super();
		this.dataType = dataType;
		this.captionResource = captionResource;
	}

	public TableColumnInfoBase(Class<TDataType> clazz, String captionResource, int width) {
		this(clazz, captionResource);
		this.width = width;
	}

	public String getCaptionResource() {
		return captionResource;
	}

	public void setCaptionResource(String captionResource) {
		this.captionResource = captionResource;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isUseDefaultCellValueFactory() {
		return useDefaultCellValueFactory;
	}

	public void setUseDefaultCellValueFactory(boolean useDefaultCellValueFactory) {
		this.useDefaultCellValueFactory = useDefaultCellValueFactory;

	}

	public Class<TDataType> getDataType() {
		return dataType;
	}

	public void setDataType(Class<TDataType> dataType) {
		this.dataType = dataType;
	}
}
