package com.diguits.templateengine.contract;

import java.io.Writer;
import java.util.List;

import org.slf4j.Logger;

public interface ITemplate {
	public void write(Object value);
	public void apply(Writer writer, Object[] models, List<ParamValue> paramValues, Logger logger);
}
