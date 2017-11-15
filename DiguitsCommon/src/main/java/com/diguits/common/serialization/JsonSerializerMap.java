package com.diguits.common.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.common.mapping.IMapper;

public abstract class JsonSerializerMap<T, TDTO> extends SerializerMapBase<T, TDTO> {

    @InjectLogger
    Logger logger;

    public JsonSerializerMap(IMapper<T, TDTO> mapper) {
        super(mapper);
    }

    @Override
    protected void serializeDTO(OutputStream targetStream, TDTO dto) throws IOException {
        SimpleXMLSerializerHelper.serialize(targetStream, dto, logger);
    }

    @Override
    protected TDTO deserializeDTO(InputStream sourceStream, Class<TDTO> dtoClass) {
        return SimpleXMLSerializerHelper.deserialize(sourceStream, dtoClass, logger);
    }

}
