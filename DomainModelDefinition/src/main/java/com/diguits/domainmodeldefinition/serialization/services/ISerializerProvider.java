package com.diguits.domainmodeldefinition.serialization.services;

public interface ISerializerProvider {
    IEntityDefinitionSerializer getSerializer(String ext);
}
