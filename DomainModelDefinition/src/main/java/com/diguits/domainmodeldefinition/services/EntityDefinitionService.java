package com.diguits.domainmodeldefinition.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.DomainObjectDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.serialization.services.IEntityDefinitionSerializer;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.inject.Inject;

public class EntityDefinitionService implements IEntityDefinitionService {
	private IEntityDefinitionSerializer entityDefinitionSerializer;
	protected Map<String, Class<?>> enumTypes;
	protected Map<String, Class<?>> entityTypes;
	protected Map<String, Class<?>> entityDTOTypes;
	private DomainModelDef domainModelDef;
	private ILocalizedDataToCSVService localizedDataToCSVService;

	@Inject
	public EntityDefinitionService(
			IEntityDefinitionSerializer entityDefinitionSerializer,
			ILocalizedDataToCSVService localizedDataToCSVService) {
		this.entityDefinitionSerializer = entityDefinitionSerializer;
		this.enumTypes = new HashMap<String, Class<?>>();
		this.entityTypes = new HashMap<String, Class<?>>();
		this.entityDTOTypes = new HashMap<String, Class<?>>();
		this.localizedDataToCSVService = localizedDataToCSVService;
	}

	@Override
	public DomainModelDef getDomainModel() {
		return domainModelDef;
	}

	@Override
	public void setDomainModelDef(DomainModelDef domainModelDef) {
		this.domainModelDef = domainModelDef;
	}

	@Override
	public String DTONameToEntityName(String dtoName) {
		return dtoName.replace("DTO", "");
	}

	@Override
	public List<BoundedContextDef> getBoundedContextsOrdered() throws Exception {
		return getBoundedContextsOrderedInner(domainModelDef.getBoundedContexts());
	}

	@Override
	public List<BoundedContextDef> getBoundedContextsOrdered(ApplicationDef application) throws Exception {
		return getBoundedContextsOrderedInner(application.getBoundedContexts());
	}

	private static List<BoundedContextDef> getBoundedContextsOrderedInner(
			List<BoundedContextDef> boundedContextsSource) throws Exception {
		List<BoundedContextDef> boundedContexts = new ArrayList<>(FluentIterable.from(
				boundedContextsSource).toSortedList(new Comparator<BoundedContextDef>() {

			@Override
			public int compare(BoundedContextDef from, BoundedContextDef to) {
				return Integer.compare(from.getDependencies().size(), to
						.getDependencies().size());
			}
		}));

		ArrayList<BoundedContextDef> boundedContextsOrderedList = new ArrayList<BoundedContextDef>();
		int lastCount = 0;
		while (boundedContexts.size() > 0 && boundedContexts.size() != lastCount) {
			lastCount = boundedContexts.size();
			for (int i = boundedContexts.size() - 1; i >= 0; i--) {
				BoundedContextDef boundedContext = boundedContexts.get(i);

				boolean flag = true;
				for (final BoundedContextDef boundedContextDef : boundedContext.getDependencies()) {
					if (!FluentIterable.from(boundedContextsOrderedList).anyMatch(new Predicate<BoundedContextDef>() {
						public boolean apply(BoundedContextDef boundedContext) {
							return boundedContext == boundedContextDef;
						};
					})) {
						flag = false;
						break;
					}
				}

				if (boundedContext.getDependencies().size() == 0 || flag) {
					boundedContextsOrderedList.add(boundedContext);
					boundedContexts.remove(i);
				}
			}
		}
		if (boundedContexts.size() > 0) {
			// var boundedContextsStr = boundedContexts.Aggregate("", (current, boundedContext) =>
			// current + (boundedContext.Caption + ", "));
			// boundedContextsStr = boundedContextsStr.Substring(0, boundedContextsStr.Length - 2);
			StringBuilder boundedContextsStrBuilder = new StringBuilder();
			boundedContextsStrBuilder.append(boundedContexts.get(0).getDefaultCaption());
			for (int i = 1; i < boundedContexts.size() - 1; i++) {
				boundedContextsStrBuilder.append(", ");
				boundedContextsStrBuilder.append(boundedContexts.get(0).getDefaultCaption());
			}
			throw new Exception(String.format(String.format("Exists dependency between boundedContexts %s",
					boundedContextsStrBuilder.toString())));
		}
		return boundedContextsOrderedList;
	}

	@Override
	public void registerEntity(Class<?> entityType) {
		entityTypes.put(entityType.getName(), entityType);
	}

	@Override
	public void registerEntityDTO(Class<?> entityDTOType) {
		entityDTOTypes.put(DTONameToEntityName(entityDTOType.getName()),
				entityDTOType);
	}

	@Override
	public void registerEnum(Class<?> enumType) {
		enumTypes.put(enumType.getName(), enumType);
	}

	@Override
	public Class<?> getEnumType(String name) {
		if (enumTypes.containsKey(name))
			return enumTypes.get(name);
		return null;
	}

	@Override
	public Class<?> getEntityType(String name) {
		if (entityTypes.containsKey(name))
			return entityTypes.get(name);
		return null;
	}

	@Override
	public Class<?> getEntityDTOType(String name) {
		if (entityDTOTypes.containsKey(name))
			return entityDTOTypes.get(name);
		return null;
	}

	@Override
	public Class<?> getEntityType(Class<?> entityDTOType) {
		return getEntityType(DTONameToEntityName(entityDTOType.getName()));
	}

	@Override
	public Class<?> getEntityDTOType(Class<?> entityType) {
		return getEntityDTOType(entityType.getName());
	}

	public EnumDef getEnum(UUID id) {
		return domainModelDef.getEnumById(id);
	}

	@Override
	public EnumDef getEnum(String name) {
		return domainModelDef.getEnumByName(name);
	}

	@Override
	public EnumDef getEnum(Class<?> enumType) {
		return getEnum(enumType.getName());
	}

	@Override
	public EntityDef getEntity(UUID id) {
		return domainModelDef.getEntityById(id);
	}

	@Override
	public EntityDef getEntity(String name) {
		return domainModelDef.getEntityByName(name);
	}

	@Override
	public EntityDef getEntity(Class<?> entityType) {
		return getEntity(entityType.getName());
	}

	@Override
	public EntityDef getEntityFromDTO(String name) {
		return getEntity(DTONameToEntityName(name));
	}

	@Override
	public EntityDef getEntityFromDTO(Class<?> entityDTOType) {
		return getEntity(entityDTOType.getName());
	}

	@Override
	public List<RelationshipDef> getRelations(DomainObjectDef domainObject) {
		List<RelationshipDef> result = new ArrayList<RelationshipDef>();
		do {
			final DomainObjectDef finalDomainObject = domainObject;
			result.addAll(FluentIterable.from(domainModelDef.getRelations())
					.filter(new Predicate<RelationshipDef>() {
						public boolean apply(RelationshipDef relation) {
							return relation.getFromEntity() == finalDomainObject
									|| relation.getToEntity() == finalDomainObject;
						};
					}).toList());
			if (domainObject instanceof EntityDef)
				domainObject = ((EntityDef) domainObject).getParentEntity();
		} while (domainObject != null);
		return result;
	}

	@Override
	public List<RelationshipDef> getRelations(String entityName) {
		return getRelations(getEntity(entityName));
	}

	@Override
	public void serialize(String targetPath) throws FileNotFoundException,
			IOException {
		if (targetPath == null)
			throw new IllegalArgumentException("targetPath");
		entityDefinitionSerializer.serialize(targetPath, domainModelDef);
	}

	@Override
	public void deserialize(String sourcePath) throws FileNotFoundException,
			IOException {
		if (sourcePath == null)
			throw new IllegalArgumentException("sourcePath");
		setDomainModelDef(entityDefinitionSerializer.deserialize(sourcePath));
	}

	@Override
	public void serialize(OutputStream targetStream) throws IOException {
		if (targetStream == null)
			throw new IllegalArgumentException("targetStream");
		entityDefinitionSerializer.serialize(targetStream, domainModelDef);
	}

	@Override
	public void deserialize(InputStream sourceStream) throws IOException {
		if (sourceStream == null)
			throw new IllegalArgumentException("sourceStream");
		setDomainModelDef(entityDefinitionSerializer.deserialize(sourceStream));
	}

	@Override
	public BoundedContextDef getBoundedContext(UUID boundedContextId) {
		return domainModelDef.getBoundedContextById(boundedContextId);
	}

	@Override
	public BoundedContextDef getBoundedContext(String name) {
		return domainModelDef.getBoundedContextById(name);
	}

	@Override
	public ModuleDef getModule(UUID id) {
		return domainModelDef.getModuleById(id);
	}

	@Override
	public ModuleDef getModule(String name) {
		return domainModelDef.getModuleByName(name);
	}

	@Override
	public ApplicationDef getApplication(UUID id) {
		return domainModelDef.getApplicationById(id);
	}

	@Override
	public ApplicationDef getApplication(String name) {
		return domainModelDef.getApplicationByName(name);
	}

	@Override
	public LocaleDef getLocale(UUID id) {
		return domainModelDef.getLocaleById(id);
	}

	@Override
	public LocaleDef getLocale(String name) {
		return domainModelDef.getLocaleByName(name);
	}

	@Override
	public List<ModuleDef> getAllModules() {
		return domainModelDef.getAllModules();
	}

	@Override
	public void exportLocalizedDataAsCSV(String path) throws FileNotFoundException, IOException {
		localizedDataToCSVService.exportLocalizedDataAsCSV(domainModelDef, path);

	}

	@Override
	public void importLocalizedDataAsCSV(String path) throws FileNotFoundException, IOException {
		localizedDataToCSVService.importLocalizedDataAsCSV(domainModelDef, path);

	}

	@Override
	public void exportLocalizedDataAsCSV(String path, UUID forLocale) throws FileNotFoundException, IOException {
		localizedDataToCSVService.exportLocalizedDataAsCSV(domainModelDef, path, forLocale);

	}
}
