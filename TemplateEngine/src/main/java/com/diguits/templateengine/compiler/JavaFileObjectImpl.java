package com.diguits.templateengine.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javax.tools.SimpleJavaFileObject;

import com.diguits.templatedefinition.services.TemplateFileManager;
import com.google.common.io.CharStreams;

/**
 * A JavaFileObject which contains either the source text or the compiler
 * generated class. This class is used in two cases.
 * <ol>
 * <li>This instance uses it to store the source which is passed to the
 * compiler. This uses the
 * {@link SimpleJavaFileObject#JavaFileObjectImpl(String, CharSequence)}
 * constructor.
 * <li>The Java compiler also creates instances (indirectly through the
 * FileManagerImplFileManager) when it wants to create a JavaFileObject for the
 * .class output. This uses the
 * {@link SimpleJavaFileObject#JavaFileObjectImpl(String, JavaFileObject.Kind)}
 * constructor.
 * </ol>
 * This class does not attempt to reuse instances (there does not seem to be a
 * need, as it would require adding a Map for the purpose, and this would also
 * prevent garbage collection of class byte code.)
 */
final class JavaFileObjectImpl extends SimpleJavaFileObject {
	// If kind == CLASS, this stores byte code from openOutputStream
	private OutputStream classStream;

	// if kind == SOURCE, this contains the source text
	private InputStream sourceStream;

	private String charContent;

	/**
	 * Construct a new instance which stores source
	 *
	 * @param baseName
	 *            the base name
	 * @param reader
	 *            the reader of the code
	 */
	JavaFileObjectImpl(final String baseName, final InputStream sourceStream) {
		super(toURI(baseName + TemplateFileManager.TEMPLATES_CODE_EXT), Kind.SOURCE);
		this.sourceStream = sourceStream;
	}

	/**
	 * Construct a new instance which stores source
	 *
	 * @param baseName
	 *            the base name
	 * @param source
	 *            the source code
	 */
	JavaFileObjectImpl(final String baseName, final OutputStream classStream) {
		super(toURI(baseName + TemplateFileManager.TEMPLATES_CLASS_EXT), Kind.CLASS);
		this.classStream = classStream;
	}

	static URI toURI(String name) {
		try {
			return new URI(name);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		try {
			if(charContent==null)
				charContent = CharStreams.toString(new InputStreamReader(sourceStream, "UTF-8"));
			return charContent;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InputStream openInputStream() throws IOException {
		return null;// Return an input stream for reading the byte code
	}

	@Override
	public OutputStream openOutputStream() throws IOException {
		return classStream;
	}



}