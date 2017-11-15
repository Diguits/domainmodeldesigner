package com.diguits.templateengine.compiler;

import com.diguits.templateengine.contract.IExpressionClassLoader;
import com.diguits.templateengine.contract.IExpression;

public class ExpressionClassLoader extends StreamClassLoaderBase<IExpression> implements IExpressionClassLoader {

	@Override
	protected Class<?  extends IExpression> getIntefaceTypeBase() {
		return IExpression.class;
	}

}
