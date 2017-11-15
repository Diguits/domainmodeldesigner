package com.diguits.common.mapping;

public class FalseMapper<T> implements IContextMapper<T, T> {

    @Override
    public T map(T source, MappingContext context) {
        return source;
    }

    @Override
    public void map(T source, T target, MappingContext context) {
    }

    @Override
    public T mapBack(T source, MappingContext context) {
        return source;
    }

    @Override
    public void mapBack(T source, T target, MappingContext context) {
    }
}
