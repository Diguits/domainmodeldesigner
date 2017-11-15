package com.diguits.templateengine.contract;

public interface ITemplateCodeBuilder extends ICodeBuilder{

	String getClassName(String templateName);

	String getFullClassName(String templateName);

}
