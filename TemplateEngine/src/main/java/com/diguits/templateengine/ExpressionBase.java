package com.diguits.templateengine;

import com.diguits.templateengine.contract.IExpression;

public abstract class ExpressionBase implements IExpression{

	@Override
	public Object evaluate(Object[] models) {
		return innerEvaluate(models);
	}

	protected abstract Object innerEvaluate(Object[] models);

}
