package com.diguits.common.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;

import com.google.gson.Gson;

public class JSsonSerializerHelper {

    public static <T> void serialize(OutputStream targetStream, T model, Logger logger) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(targetStream, StandardCharsets.UTF_8);
        new Gson().toJson(model, writer);
        writer.flush();
    }

    protected static <T> T deserialize(InputStream sourceStream, Class<T> clazz, Logger logger) {
        InputStreamReader reader = new InputStreamReader(sourceStream, StandardCharsets.UTF_8);
        return new Gson().fromJson(reader, clazz);
    }
}
