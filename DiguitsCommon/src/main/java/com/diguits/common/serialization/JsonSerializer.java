package com.diguits.common.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;

public abstract class JsonSerializer<T> extends SerializerBase<T> {

    @InjectLogger
    Logger logger;

    public JsonSerializer() {
    }

    @Override
    protected void innerSerialize(OutputStream targetStream, T model) throws IOException {
        SimpleXMLSerializerHelper.serialize(targetStream, model, logger);
    }

    @Override
    protected T innerDeserialize(InputStream sourceStream) {
        return SimpleXMLSerializerHelper.deserialize(sourceStream, getModeClass(), logger);
    }

}
