package com.diguits.common.mapping;

public interface IMapper<TFrom, TTo> {

    TTo map(TFrom source);

    void map(TFrom source, TTo target);

    TFrom mapBack(TTo source);

    void mapBack(TTo source, TFrom target);
}
