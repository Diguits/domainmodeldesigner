package com.diguits.common.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ISerializer<T> {

    void serialize(File targetFile, T model) throws FileNotFoundException, IOException;

    T deserialize(File sourceFile) throws FileNotFoundException, IOException;

    void serialize(String targetPath, T model) throws FileNotFoundException, IOException;

    T deserialize(String sourcePath) throws FileNotFoundException, IOException;

    void serialize(OutputStream targetStream, T model) throws IOException;

    T deserialize(InputStream sourceStream) throws IOException;
}
