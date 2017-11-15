package com.diguits.templateengine.contract;

import java.io.InputStream;

public interface IStreamClassLoader<T> {
	public T load(String className, InputStream templateClassInput, boolean reload);
}
