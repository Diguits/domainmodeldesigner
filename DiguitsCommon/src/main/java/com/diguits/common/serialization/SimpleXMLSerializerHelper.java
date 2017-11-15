package com.diguits.common.serialization;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.CamelCaseStyle;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;
import org.slf4j.Logger;

public class SimpleXMLSerializerHelper {

    public static <T> void serialize(OutputStream targetStream, T model, Logger logger) {
        Format format = new Format(new CamelCaseStyle(true, true));
        Persister persister = new Persister(new UUIDMatcher(), format);
        try {
            persister.write(model, targetStream);
        } catch (Exception e) {
            String dtoStr = "";
            if (model != null)
                dtoStr = model.toString();
            logger.error("Error wrinting DTO Object �" + dtoStr + "� to xml using Simple XML Persister", e);
        }

    }

    public static <T> T deserialize(InputStream sourceStream, Class<T> modelClass, Logger logger) {
        Persister persister = new Persister(new UUIDMatcher(), new Format(new CamelCaseStyle(true, true)));
        try {
            return persister.read(modelClass, sourceStream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public static class UUIDMatcher implements Matcher {
        @SuppressWarnings("rawtypes")
        @Override
        public Transform match(Class type) throws Exception {
            if (type.equals(UUID.class))
                return new UUIDTransform();
            return null;
        }
    }

    public static class UUIDTransform implements Transform<UUID> {
        @Override
        public UUID read(String value) throws Exception {
            return UUID.fromString(value);
        }

        @Override
        public String write(UUID value) throws Exception {
            return value.toString();
        }
    }
}
