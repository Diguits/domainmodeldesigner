package com.diguits.templateengine.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

/**
 * A JavaFileManager which manages Java source and classes. This FileManager
 * delegates to the JavaFileManager and the ClassLoaderImpl provided in the
 * constructor. The sources are all in memory CharSequence instances and the
 * classes are all in memory byte arrays.
 */
final class FileManagerImpl extends ForwardingJavaFileManager<JavaFileManager> {

	public InputStream reader;
	public OutputStream writer;
	public JavaFileObjectImpl inputFileObject;
	public JavaFileObjectImpl outputFileObject;

	/**
	 * Construct a new FileManager which forwards to the <var>fileManager</var>
	 * for source and to the <var>classLoader</var> for classes
	 *
	 * @param fileManager
	 *            another FileManager that this instance delegates to for
	 *            additional source.
	 * @param classLoader
	 *            a ClassLoader which contains dependent classes that the
	 *            compiled classes will require when compiling them.
	 */
	public FileManagerImpl(JavaFileManager fileManager, InputStream reader, OutputStream writer) {
		super(fileManager);
		this.reader = reader;
		this.writer = writer;
	}

	public JavaFileObjectImpl getInputFileObject(String baseName) {
		if(inputFileObject==null)
			inputFileObject = new JavaFileObjectImpl(baseName, reader);
		return inputFileObject;
	}

	public JavaFileObjectImpl getOutputFileObject(String baseName) {
		if(outputFileObject==null)
			outputFileObject = new JavaFileObjectImpl(baseName, writer);
		return outputFileObject;
	}

/**
    * For a given file <var>location</var>, return a FileObject from which the
    * compiler can obtain source or byte code.
    *
    * @param location
    *           an abstract file location
    * @param packageName
    *           the package name for the file
    * @param relativeName
    *           the file's relative name
    * @return a FileObject from this or the delegated FileManager
    * @see javax.tools.ForwardingJavaFileManager#getFileForInput(javax.tools.JavaFileManager.Location,
    *      java.lang.String, java.lang.String)
    */
   @Override
   public FileObject getFileForInput(Location location, String packageName,
         String relativeName) throws IOException {
      return getInputFileObject(relativeName);
   }

	/**
	 * Create a JavaFileImpl for an output class file and store it in the
	 * classloader.
	 *
	 * @see javax.tools.ForwardingJavaFileManager#getJavaFileForOutput(javax.tools.JavaFileManager.Location,
	 *      java.lang.String, javax.tools.JavaFileObject.Kind,
	 *      javax.tools.FileObject)
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(Location location, String qualifiedName, Kind kind,
			FileObject outputFile) throws IOException {
		return getOutputFileObject(qualifiedName);
	}

	@Override
	public String inferBinaryName(Location loc, JavaFileObject file) {
		String result;
		// For our JavaFileImpl instances, return the file's name, else
		// simply run the default implementation
		if (file instanceof JavaFileObjectImpl)
			result = file.getName();
		else
			result = super.inferBinaryName(loc, file);
		return result;
	}

	@Override
	public Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse)
			throws IOException {
		return super.list(location, packageName, kinds, recurse);//new ArrayList<JavaFileObject>();
	}
}