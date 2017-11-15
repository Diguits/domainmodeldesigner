package com.diguits.common.serialization;

import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.common.mapping.IMapper;

public abstract class SimpleXMLSerializerMap<T, TDTO> extends SerializerMapBase<T, TDTO> {

    @InjectLogger
    Logger logger;

    public SimpleXMLSerializerMap(IMapper<T, TDTO> mapper) {
        super(mapper);
    }

    @Override
    protected void serializeDTO(OutputStream targetStream, TDTO dto) {
        SimpleXMLSerializerHelper.serialize(targetStream, dto, logger);

    }

    @Override
    protected TDTO deserializeDTO(InputStream sourceStream, Class<TDTO> dtoClass) {
        return SimpleXMLSerializerHelper.deserialize(sourceStream, dtoClass, logger);
    }
}
