package com.diguits.domainmodeldefinition.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.definitions.DomainObjectDef;

public interface IEntityDefinitionService {
	String DTONameToEntityName(String dtoName);
    DomainModelDef getDomainModel();
    List<BoundedContextDef> getBoundedContextsOrdered() throws Exception;
    List<BoundedContextDef> getBoundedContextsOrdered(ApplicationDef application) throws Exception;

    void registerEntity(Class<?> entityClass);
    void registerEntityDTO(Class<?> entityDTOClass);
    void registerEnum(Class<?> enumClass);

    Class<?> getEnumType(String name);

    Class<?> getEntityType(String name);
    Class<?> getEntityDTOType(String name);
    Class<?> getEntityType(Class<?> entityDTOType);
    Class<?> getEntityDTOType(Class<?> entityType);

    EnumDef getEnum(UUID id);
    EnumDef getEnum(String name);
    EnumDef getEnum(Class<?> enumType);

    EntityDef getEntity(UUID id);
    EntityDef getEntity(String name);
    EntityDef getEntity(Class<?> entityType);

    BoundedContextDef getBoundedContext(UUID id);
    BoundedContextDef getBoundedContext(String name);

    ModuleDef getModule(UUID id);
    ModuleDef getModule(String name);
    List<ModuleDef> getAllModules();


    ApplicationDef getApplication(UUID id);
    ApplicationDef getApplication(String name);

    LocaleDef getLocale(UUID id);
    LocaleDef getLocale(String name);

    EntityDef getEntityFromDTO(String name);
    EntityDef getEntityFromDTO(Class<?> entityDTOClass);

    List<RelationshipDef> getRelations(DomainObjectDef entity);
    List<RelationshipDef> getRelations(String entityName);

    void serialize(String targetPath) throws FileNotFoundException, IOException;
    void deserialize(String sourcePath) throws FileNotFoundException, IOException;
    void serialize(OutputStream stream) throws IOException;
    void deserialize(InputStream stream) throws IOException;
    void setDomainModelDef(DomainModelDef domainModelDef);

	void importLocalizedDataAsCSV(String path) throws FileNotFoundException, IOException;;
	void exportLocalizedDataAsCSV(String path) throws FileNotFoundException, IOException;;
	void exportLocalizedDataAsCSV(String path, UUID forLocale) throws FileNotFoundException, IOException;
}
