package com.diguits.common.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.diguits.common.mapping.IMapper;

public abstract class SerializerMapBase<T, TDTO> extends SerializerBase<T> {

    protected IMapper<T, TDTO> mapper;

    public SerializerMapBase(IMapper<T, TDTO> mapper) {
        super();
        this.mapper = mapper;
    }

    protected void innerSerialize(OutputStream targetStream,
                                  T model) throws IOException {
        TDTO dto = mapToDTO(model);
        serializeDTO(targetStream, dto);
    }

    protected abstract void serializeDTO(OutputStream targetStream, TDTO dto) throws IOException;

    protected T innerDeserialize(InputStream sourceStream) {
        TDTO dto = deserializeDTO(sourceStream, getDTOClass());
        return mapToModel(dto);
    }

    @Override
    protected Class<T> getModeClass() {
        //In this case is not needed because the object serialized is the DTO
        return null;
    }

    protected abstract TDTO deserializeDTO(InputStream sourceStream, Class<TDTO> dtoClass);

    protected abstract Class<TDTO> getDTOClass();

    protected TDTO mapToDTO(T model) {
        return mapper.map(model);
    }

    protected T mapToModel(TDTO dto) {
        return mapper.mapBack(dto);
    }
}
