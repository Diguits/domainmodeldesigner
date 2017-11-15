package com.diguits.common.mapping;

public abstract class MapperBase<TFrom, TTo> implements IContextMapper<TFrom, TTo> {

    @Override
    public TTo map(TFrom source, MappingContext context) {
        if (source == null)
            return null;
        TTo target = createTo();
        map(source, target, context);
        return target;
    }

    protected TTo createTo() {
        Class<TTo> toClass = getToClass();
        try {
            return toClass != null ? toClass.newInstance() : null;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Class<TTo> getToClass();

    @Override
    public TFrom mapBack(TTo source, MappingContext context) {
        if (source == null) return null;
        TFrom target = createFrom();
        mapBack(source, target, context);
        return target;
    }

    protected TFrom createFrom() {
        Class<TFrom> fromClass = getFromClass();
        try {
            return fromClass != null ? fromClass.newInstance() : null;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Class<TFrom> getFromClass();
}
