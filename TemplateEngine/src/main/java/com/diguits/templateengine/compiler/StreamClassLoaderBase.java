package com.diguits.templateengine.compiler;

import java.io.InputStream;

import com.diguits.templateengine.contract.IStreamClassLoader;

public abstract class StreamClassLoaderBase<T> implements IStreamClassLoader<T> {

	ClassLoaderImpl classLoader;

	public StreamClassLoaderBase() {
		super();
		classLoader = new ClassLoaderImpl(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(String className, InputStream templateClassInput, boolean reload) {
		try {
			if(reload && classLoader.isLoadedClass(className))
				classLoader = new ClassLoaderImpl(null);
			classLoader.setClassStream(templateClassInput);
			Class<?> templateClass;
			templateClass = classLoader.loadClass(className);
			Class<?> parentClass = templateClass;
			while (parentClass != null) {
				Class<?>[] interfaces = parentClass.getInterfaces();
				for (Class<?> i : interfaces) {
					if (i == getIntefaceTypeBase())
						return (T) templateClass.newInstance();
				}
				parentClass=parentClass.getSuperclass();
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected abstract Class<? extends T> getIntefaceTypeBase();

}
