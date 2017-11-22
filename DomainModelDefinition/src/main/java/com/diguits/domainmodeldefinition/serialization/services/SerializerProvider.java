package com.diguits.domainmodeldefinition.serialization.services;

import com.google.inject.Inject;

public class SerializerProvider implements ISerializerProvider {
    private JsonEntityDefinitionSerializer jsonEntityDefinitionSerializer;
    private SimpleXMLEntityDefinitionSerializer simpleXMLEntityDefinitionSerializer;

    @Inject
    public SerializerProvider(JsonEntityDefinitionSerializer jsonEntityDefinitionSerializer, SimpleXMLEntityDefinitionSerializer simpleXMLEntityDefinitionSerializer) {
        this.jsonEntityDefinitionSerializer = jsonEntityDefinitionSerializer;
        this.simpleXMLEntityDefinitionSerializer = simpleXMLEntityDefinitionSerializer;
    }

    @Override
    public IEntityDefinitionSerializer getSerializer(String ext) {
        if (ext.toUpperCase().equals("DMX")) {
            return simpleXMLEntityDefinitionSerializer;
        } else {
            return jsonEntityDefinitionSerializer;
        }
    }
}
