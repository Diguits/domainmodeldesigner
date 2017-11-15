package com.diguits.common.mapping;

public interface IMapperProvider {

    <TMapper extends IContextMapper<?, ?>> TMapper getMapper(Class<TMapper> mapperClass);
}
