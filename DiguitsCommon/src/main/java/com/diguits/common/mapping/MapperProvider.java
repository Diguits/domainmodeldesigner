package com.diguits.common.mapping;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;

public class MapperProvider implements IMapperProvider {

    @InjectLogger
    Logger logger;

    Map<Class<? extends IContextMapper<?, ?>>, IContextMapper<?, ?>> mappers;

    public MapperProvider() {
        super();
        mappers = new HashMap<Class<? extends IContextMapper<?, ?>>, IContextMapper<?, ?>>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <TMapper extends IContextMapper<?, ?>> TMapper getMapper(
            Class<TMapper> mapperClass) {
        if (!mappers.containsKey(mapperClass)) {
            IContextMapper<?, ?> mapper;
            try {
                mapper = ((IContextMapper<?, ?>) mapperClass.getConstructors()[0].newInstance(this));
                mappers.put(mapperClass, mapper);
            } catch (InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException | SecurityException e) {
                logger.error("Error creating mapper, system couldn't create mapper for class �" + mapperClass.getName()
                        + "�", e);
            }
        }
        return (TMapper) mappers.get(mapperClass);
    }

}
