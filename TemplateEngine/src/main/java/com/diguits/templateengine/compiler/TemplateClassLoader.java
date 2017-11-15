package com.diguits.templateengine.compiler;

import com.diguits.templateengine.contract.ITemplate;
import com.diguits.templateengine.contract.ITemplateClassLoader;

public class TemplateClassLoader extends StreamClassLoaderBase<ITemplate> implements ITemplateClassLoader {

	@Override
	protected Class<? extends ITemplate> getIntefaceTypeBase() {
		return ITemplate.class;
	}

}
