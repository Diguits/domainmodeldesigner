package com.diguits.templateengine.compiler;

import java.io.IOException;
import java.io.InputStream;

import com.google.common.io.ByteStreams;

public class ClassLoaderImpl extends ClassLoader {


	private InputStream classStream;

	public ClassLoaderImpl(InputStream classStream) {
		super();
		this.classStream = classStream;
	}

	public InputStream getClassStream() {
		return classStream;
	}

	public void setClassStream(InputStream classStream) {
		this.classStream = classStream;
	}

	@Override
	protected Class<?> findClass(final String qualifiedClassName) throws ClassNotFoundException {
		if (classStream != null) {
			byte[] bytes;
			try {
				bytes = ByteStreams.toByteArray(classStream);
				return defineClass(qualifiedClassName, bytes, 0, bytes.length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return super.findClass(qualifiedClassName);
	}

	public boolean isLoadedClass(String className){
		return findLoadedClass(className)!=null;
	}

	@Override
	public InputStream getResourceAsStream(String name) {
		return classStream;
	}
}
