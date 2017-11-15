package com.diguits.common.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class SerializerBase<T> implements ISerializer<T> {

    public SerializerBase() {
        super();
    }

    @Override
    public void serialize(File targetFile, T model) throws FileNotFoundException, IOException {
        serialize(targetFile.getAbsolutePath(), model);
    }

    @Override
    public T deserialize(File sourceFile) throws FileNotFoundException, IOException {
        return deserialize(sourceFile.getAbsolutePath());
    }

    @Override
    public void serialize(String targetPath, T model) throws FileNotFoundException, IOException {
        if (targetPath == null)
            throw new IllegalArgumentException("targetPath");
        FileOutputStream fileStream;
        fileStream = new FileOutputStream(targetPath);
        serialize(fileStream, model);
        fileStream.close();
    }

    @Override
    public T deserialize(String sourcePath) throws FileNotFoundException, IOException {
        if (sourcePath == null)
            throw new IllegalArgumentException("sourcePath");
        FileInputStream fileStream = new FileInputStream(sourcePath);
        T model = deserialize(fileStream);
        fileStream.close();
        return model;
    }

    @Override
    public void serialize(OutputStream targetStream, T model) throws IOException {
        if (targetStream == null)
            throw new IllegalArgumentException("targetStream");
        if (model == null)
            throw new IllegalArgumentException("model");
        innerSerialize(targetStream, model);

    }

    protected abstract void innerSerialize(OutputStream targetStream, T model) throws IOException;

    protected abstract Class<T> getModeClass();

    @Override
    public T deserialize(InputStream sourceStream) throws IOException {
        if (sourceStream == null)
            throw new IllegalArgumentException("sourceStream");
        return innerDeserialize(sourceStream);
    }

    protected abstract T innerDeserialize(InputStream sourceStream) throws IOException;

}