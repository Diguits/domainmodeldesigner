package com.diguits.common.mapping;

public interface IContextMapper<TFrom, TTo> {

    TTo map(TFrom source, MappingContext context);

    void map(TFrom source, TTo target, MappingContext context);

    TFrom mapBack(TTo source, MappingContext context);

    void mapBack(TTo source, TFrom target, MappingContext context);
}
