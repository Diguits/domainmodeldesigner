package com.diguits.domainmodeldesigner.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.prefs.Preferences;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.ColumnDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldValueDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.definitions.RelationshipDef;
import com.diguits.domainmodeldefinition.serialization.services.LocalizedDataToCSVSerializer;
import com.diguits.domainmodeldefinition.definitions.EnumDef;
import com.diguits.domainmodeldefinition.definitions.EnumValueDef;
import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldefinition.definitions.FieldGroupDef;
import com.diguits.domainmodeldefinition.definitions.FieldSubgroupDef;
import com.diguits.domainmodeldefinition.definitions.FilterDef;
import com.diguits.domainmodeldefinition.definitions.IndexDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.definitions.LocalizedDataDef;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.RelationOverrideDef;
import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.ApplicationDefProvider;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.BoundedContextDefProvider;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.DomainModelDefProvider;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.EntityDefProvider;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.EnumDefProvider;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.LocaleDefProvider;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.ModelProvider;
import com.diguits.domainmodeldesigner.domainmodel.modeldescriptors.ModuleDefProvider;
import com.diguits.domainmodeldesigner.domainmodel.modelmappers.IDomainModelModelMapper;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FindModelVisitor;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.services.tasks.ExportLocalizedDataModelToCSVTask;
import com.diguits.domainmodeldesigner.services.tasks.ImportLocalizedDataModelFromCSVTask;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.google.inject.Inject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.FilteredList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DomainModelClientService extends ClientServiceBase {
    private static final String LASTSUITEPATH = "LastDomainModelPath";

    private ObjectProperty<DomainModelDefModel> domainModelDefModel;
    private BooleanProperty dirty;
    private String domainModelPath;
    private Map<String, ModelProvider<? extends BaseDef, ? extends BaseDefModel>> modelProviders;
    private List<Class<? extends BaseDef>> defClasses;

    @Inject
    private IEntityDefinitionService entityDefinitionService;

    @Inject
    private IDomainModelModelMapper domainModelDefMapper;

    @Inject
    LocalizedDataToCSVSerializer serializer;

    @Inject
    ListAllDefModelsVisitor listAllDefModelsVisitor;

    @InjectLogger
    Logger logger;

    @Inject
    public DomainModelClientService() {
        super();
    }


    private Map<String, ModelProvider<? extends BaseDef, ? extends BaseDefModel>> getModelProviders() {
        if (modelProviders == null) {
            modelProviders = new HashMap<>();
            modelProviders.put(ApplicationDef.class.getName(), new ApplicationDefProvider(entityDefinitionService));
            modelProviders.put(BoundedContextDef.class.getName(), new BoundedContextDefProvider(entityDefinitionService));
            modelProviders.put(DomainModelDef.class.getName(), new DomainModelDefProvider(entityDefinitionService));
            modelProviders.put(EntityDef.class.getName(), new EntityDefProvider(entityDefinitionService));
            modelProviders.put(EnumDef.class.getName(), new EnumDefProvider(entityDefinitionService));
            modelProviders.put(LocaleDef.class.getName(), new LocaleDefProvider(entityDefinitionService));
            modelProviders.put(ModuleDef.class.getName(), new ModuleDefProvider(entityDefinitionService));
        }
        return modelProviders;
    }


    public void create() {
        DomainModelDefModel domainModel = new DomainModelDefModel();
        domainModel.getLocales().add(creteDefaultLocale());
        domainModel.setName("DomainModel1");
        setDomainModelDef(domainModel);
        logger.info("New domainModel was created.");
        // TODO si el domainModel anterior es dirty preguntar si se desea salvar
    }

    private LocaleDefModel creteDefaultLocale() {
        LocaleDefModel result = new LocaleDefModel();
        result.setLanguage("en");
        result.setCountry("US");
        return result;
    }

    public void load() {
        FileChooser fileChooser = createFileChooser();
        fileChooser.setTitle("Open a DomainModel File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("JSON Domain Model Definition", "*.dmj"),
                new ExtensionFilter("XLM Domain Model Definition", "*.dmx"),
                new ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                setDomainModelPath(selectedFile.getPath());
                DomainModelDef domainModelDef = innerLoad();
                logger.info("The domainModel {} was loaded.", domainModelDef.getName());

            } catch (IOException e) {
                logger.error("Error loading DomainModel from file �" + selectedFile.getAbsolutePath() + "�", e);
            }
        }
    }

    public void reload() throws FileNotFoundException, IOException {
        if (getDomainModelPath() != null) {
            if (new File(getDomainModelPath()).exists()) {
                innerLoad();
            }
            logger.error("Error reloading DomainModel the file »" + getDomainModelPath() + "« does not exist");
        }
        logger.error("Error reloading DomainModel the path is null");
    }

    private DomainModelDef innerLoad() throws FileNotFoundException, IOException {
        entityDefinitionService.deserialize(getDomainModelPath());
        DomainModelDef domainModelDef = entityDefinitionService.getDomainModel();
        setDomainModelDef(domainModelDefMapper.map(domainModelDef));
        return domainModelDef;
    }

    public void saveAs() {
        FileChooser fileChooser = createFileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);
        fileChooser.setTitle("Save the DomainModel to file");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("JSON Domain Model Definition", "*.dmj"),
                new ExtensionFilter("XML Domain Model Definition", "*.dmx"));
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            if (!path.toUpperCase().endsWith(".DMJ") && !path.toUpperCase().endsWith(".DMX")) {
                path = path + ".dmj";
            }
            try {
                setDomainModelPath(path);
                innerSave();
            } catch (IOException e) {
                logger.error("Error saving as DomainModel �" + getDomainModelDef().getName() + "� to file �"
                        + path + "�", e);
            }
        }
    }

    private void innerSave() throws IOException {
        entityDefinitionService.setDomainModelDef(domainModelDefMapper.mapBack(getDomainModelDef()));
        entityDefinitionService.serialize(getDomainModelPath());
    }

    public void save() {
        String path = getDomainModelPath();
        if (path != null && !path.isEmpty()) {
            File file = new File(path);
            if (file.exists())
                try {
                    innerSave();
                    return;
                } catch (IOException e) {
                    logger.error("Error saving DomainModel �" + getDomainModelDef().getName() + "� to file �"
                            + file.getAbsolutePath() + "�", e);
                }
        }
        saveAs();
    }

    public FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        if (domainModelPath != null) {
            String path = (domainModelPath != null) ? domainModelPath
                    : Preferences.systemNodeForPackage(this.getClass()).get(LASTSUITEPATH, "");
            File file = new File(path);
            if (file.exists())
                fileChooser.setInitialDirectory(file.getParentFile());
            fileChooser.setInitialFileName(file.getName());
        }

        // TODO Remove the line behind
        //fileChooser.setInitialDirectory(new File("D:\\Work\\Android\\TechnicalDesing"));
        return fileChooser;
    }

    protected void setDomainModelPath(String domainModelPath) {
        this.domainModelPath = domainModelPath;
        // Preferences.systemNodeForPackage(this.getClass()).put(LASTSUITEPATH,
        // domainModelPath);
    }

    public String getDomainModelPath() {
        return domainModelPath;
    }

    public DomainModelDefModel getDomainModelDef() {
        if (domainModelDefModel != null)
            return domainModelDefModel.get();
        return null;
    }

    public void setDomainModelDef(DomainModelDefModel domainModelDef) {
        if (this.domainModelDefModel != null || domainModelDef != null)
            domainModelDefProperty().set(domainModelDef);
    }

    public ObjectProperty<DomainModelDefModel> domainModelDefProperty() {
        if (domainModelDefModel == null)
            domainModelDefModel = new SimpleObjectProperty<DomainModelDefModel>(this, "domainModelDef", null);
        return domainModelDefModel;
    }

    public boolean getDirty() {
        if (dirty != null)
            return dirty.get();
        return false;
    }

    public void setDirty(boolean dirty) {
        if (this.dirty != null || dirty != false)
            dirtyProperty().set(dirty);
    }

    public BooleanProperty dirtyProperty() {
        if (dirty == null)
            dirty = new SimpleBooleanProperty(this, "dirty", false);
        return dirty;
    }

    public List<EntityDefModel> getInheritance(EntityDefModel entity) {
        List<EntityDefModel> result = new ArrayList<EntityDefModel>();
        getInheritanceR(entity, result);
        return result;
    }

    private void getInheritanceR(EntityDefModel entity, List<EntityDefModel> result) {
        FilteredList<EntityDefModel> children = getDomainModelDef().getEntities()
                .filtered(e -> e.getParentEntity() == entity);
        for (EntityDefModel child : children) {
            result.add(child);
            getInheritanceR(child, result);
        }
    }

    public void addBoundedContext() {
        BoundedContextDefModel boundedContext = new BoundedContextDefModel();
        boundedContext.setName(getNewNameForBoundedContext());
        getDomainModelDef().getBoundedContexts().add(boundedContext);
        boundedContext.setOwner(getDomainModelDef());
    }

    private String getNewNameForBoundedContext() {
        return "BoundedContext 1";
    }

    public void removeBoundedContext(BoundedContextDefModel boundedContext) {
        if (boundedContext != null) {
            int i = boundedContext.getModules().size() - 1;
            while (i >= 0) {
                ModuleDefModel module = boundedContext.getModules().get(i);
                innerRemoveModule(module, boundedContext);
                i--;
            }
            getDomainModelDef().getBoundedContexts().remove(boundedContext);
        }
    }

    public void addModule(BoundedContextDefModel boundedContext) {
        if (boundedContext != null) {
            ModuleDefModel module = new ModuleDefModel();
            module.setName(getNewNameForModule(boundedContext));
            boundedContext.getModules().add(module);
            module.setOwner(boundedContext);
        }
    }

    private String getNewNameForModule(BoundedContextDefModel boundedContext) {
        return "Module 1";
    }

    public void removeModule(ModuleDefModel module) {
        if (module != null) {
            BoundedContextDefModel boundedContext = (BoundedContextDefModel) module.getOwner();
            innerRemoveModule(module, boundedContext);
        }
    }

    private void innerRemoveModule(ModuleDefModel module, BoundedContextDefModel boundedContext) {
        if (boundedContext != null) {
            int i = getDomainModelDef().getEntities().size() - 1;
            while (i >= 0) {
                EntityDefModel entity = getDomainModelDef().getEntities().get(i);
                if (entity.getModule() == module)
                    innerRemoveEntity(entity);
                i--;
            }
            boundedContext.getModules().remove(module);
        }
    }

    public void addEntity(ModuleDefModel module) {
        if (module != null) {
            EntityDefModel entity = new EntityDefModel();
            entity.setName(getNewNameForEntity(module));
            entity.setModule(module);
            getDomainModelDef().getEntities().add(entity);
            entity.setOwner(getDomainModelDef());
        }
    }

    private String getNewNameForEntity(ModuleDefModel module) {
        return "Entity 1";
    }

    public void removeEntity(EntityDefModel entity) {
        innerRemoveEntity(entity);
    }

    private void innerRemoveEntity(EntityDefModel entity) {
        if (entity != null) {
            getDomainModelDef().getEntities().remove(entity);
        }
    }

    public void addValueObject(ModuleDefModel module) {
        if (module != null) {
            ValueObjectDefModel valueObject = new ValueObjectDefModel();
            valueObject.setName(getNewNameForValueObject(module));
            valueObject.setModule(module);
            getDomainModelDef().getValueObjects().add(valueObject);
            valueObject.setOwner(getDomainModelDef());
        }
    }

    private String getNewNameForValueObject(ModuleDefModel module) {
        return "ValueObject 1";
    }

    public void removeValueObject(ValueObjectDefModel valueObject) {
        innerRemoveValueObject(valueObject);
    }

    private void innerRemoveValueObject(ValueObjectDefModel valueObject) {
        if (valueObject != null) {
            getDomainModelDef().getValueObjects().remove(valueObject);
        }
    }

    public void addEnum(ModuleDefModel module) {
        if (module != null) {
            EnumDefModel enumDefModel = new EnumDefModel();
            enumDefModel.setName(getNewNameForEnum(module));
            enumDefModel.setModule(module);
            getDomainModelDef().getEnums().add(enumDefModel);
            enumDefModel.setOwner(getDomainModelDef());
        }
    }

    private String getNewNameForEnum(ModuleDefModel module) {
        return "Enum 1";
    }

    public void removeEnum(EnumDefModel enumDefModel) {
        innerRemoveEnum(enumDefModel);
    }

    private void innerRemoveEnum(EnumDefModel enumDefModel) {
        if (enumDefModel != null) {
            getDomainModelDef().getEnums().remove(enumDefModel);
        }
    }

    public void addApplication() {
        ApplicationDefModel application = new ApplicationDefModel();
        application.setName(getNewNameForApplication());
        getDomainModelDef().getApplications().add(application);
        application.setOwner(getDomainModelDef());
    }

    private String getNewNameForApplication() {
        return "Application 1";
    }

    public void removeApplication(ApplicationDefModel application) {
        if (application != null) {
            getDomainModelDef().getApplications().remove(application);
        }
    }

    public BaseDefModel findModel(UUID modelId) {
        DomainModelDefModel domainModelDef = getDomainModelDef();
        if (domainModelDef != null) {
            FindModelVisitor findModelVisitor = new FindModelVisitor(modelId);
            domainModelDef.accept(findModelVisitor, null);
            return findModelVisitor.getResult();
        }
        return null;
    }

    public List<List<? extends BaseDefModel>> getModelsByClass(String[] modelClassNames) {
        List<List<? extends BaseDefModel>> result = new ArrayList<List<? extends BaseDefModel>>();
        DomainModelDefModel domainModelDef = getDomainModelDef();
        if (modelClassNames != null && domainModelDef != null) {
            for (int i = 0; i < modelClassNames.length; i++) {
                String modelClassName = modelClassNames[i];
                List<? extends BaseDefModel> models = getModelsByClass(domainModelDef, modelClassName);
                result.add(models);
            }
        }
        return result;
    }

    private List<? extends BaseDefModel> getModelsByClass(DomainModelDefModel domainModelDef, String modelClassName) {
        ModelProvider<? extends BaseDef, ? extends BaseDefModel> modelDescriptor = getModelProviders().get(modelClassName);
        if (modelDescriptor != null) {
            return modelDescriptor.getModels(domainModelDef);
        }
        return null;
    }

    public BaseDef[] getDefFromId(UUID[] modelsId, String[] modelClassNames) {
        BaseDef[] result = new BaseDef[modelsId.length];
        if (domainModelDefModel != null) {
            for (int i = 0; i < modelsId.length; i++) {
                result[i] = getDefFromId(modelsId[i], modelClassNames[i]);
            }
        }
        return result;
    }

    private BaseDef getDefFromId(UUID modelId, String modelClassName) {
        ModelProvider<? extends BaseDef, ? extends BaseDefModel> modelDescriptor = modelProviders.get(modelClassName);
        if (modelDescriptor != null) {
            return modelDescriptor.getDefFromId(modelId);
        }
        return null;
    }

    public List<List<? extends BaseDef>> getModelDefsByClass(String[] modelClassNames) {
        List<List<? extends BaseDef>> result = new ArrayList<List<? extends BaseDef>>();
        DomainModelDef domainModelDef = entityDefinitionService.getDomainModel();
        if (modelClassNames != null && domainModelDef != null) {
            for (int i = 0; i < modelClassNames.length; i++) {
                String modelClassName = modelClassNames[i];
                if (modelClassName != null && !modelClassName.isEmpty()) {
                    List<? extends BaseDef> models = getModelDefsByClass(domainModelDef, modelClassName);
                    result.add(models);
                }
            }
        }
        return result;
    }

    private List<? extends BaseDef> getModelDefsByClass(DomainModelDef domainModelDef, String modelClassName) {
        ModelProvider<? extends BaseDef, ? extends BaseDefModel> modelDescriptor = getModelProviders().get(modelClassName);
        if (modelDescriptor != null) {
            return modelDescriptor.getModelDefs(domainModelDef);
        }
        return null;
    }

    public List<String> getModelClassesName() {
        ArrayList<String> result = new ArrayList<String>(getModelProviders().keySet());
        result.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return result;
    }

    public List<Class<? extends BaseDef>> getDefClasses() {

        if (defClasses == null) {
            defClasses = new ArrayList<Class<? extends BaseDef>>();
            defClasses.add(ApplicationDef.class);
            defClasses.add(BaseDef.class);
            defClasses.add(ColumnDef.class);
            defClasses.add(CustomFieldDef.class);
            defClasses.add(CustomFieldValueDef.class);
            defClasses.add(EntityDef.class);
            defClasses.add(RelationshipDef.class);
            defClasses.add(EnumDef.class);
            defClasses.add(EnumValueDef.class);
            defClasses.add(FieldDef.class);
            defClasses.add(FieldGroupDef.class);
            defClasses.add(FieldSubgroupDef.class);
            defClasses.add(FilterDef.class);
            defClasses.add(IndexDef.class);
            defClasses.add(BoundedContextDef.class);
            defClasses.add(LocaleDef.class);
            defClasses.add(LocalizedDataDef.class);
            defClasses.add(RelationOverrideDef.class);
            defClasses.add(ModuleDef.class);
            defClasses.add(DomainModelDef.class);
        }
        return defClasses;
    }

    public ExportLocalizedDataModelToCSVTask getExportLocalizedDataModelToCSVTask() {
        FileChooser fileChooser = createFileChooser();
        fileChooser.setTitle("Save Localized data to csv file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Domain Model Localiced", "*.csv"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            return new ExportLocalizedDataModelToCSVTask(null, selectedFile.getPath(), serializer, this,
                    listAllDefModelsVisitor);
        }
        return null;
    }

    public ImportLocalizedDataModelFromCSVTask getExportLocalizedDataModelFromCSVTask() {
        FileChooser fileChooser = createFileChooser();
        fileChooser.setTitle("Save Localized data to csv file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Domain Model Localiced", "*.csv"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            return new ImportLocalizedDataModelFromCSVTask(selectedFile.getPath(), serializer, this,
                    listAllDefModelsVisitor, logger);
        }
        return null;
    }

    public void exportLocalizedDataAsCSV() {
        FileChooser fileChooser = createFileChooser();
        fileChooser.setTitle("Save Localized data to csv file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Entity DomainModel Definition", "*.csv"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            try {
                entityDefinitionService.setDomainModelDef(domainModelDefMapper.mapBack(getDomainModelDef()));
                entityDefinitionService.exportLocalizedDataAsCSV(selectedFile.getPath());
                logger.info("The localized data was exported successfully.");
            } catch (IOException e) {
                logger.error("Error importing localized data from file �" + selectedFile.getPath() + "�", e);
            }
        }
    }

    public void importLocalizedDataAsCSV() {
        FileChooser fileChooser = createFileChooser();
        fileChooser.setTitle("Open a csv file with Localized data");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Entity DomainModel Definition", "*.csv"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                entityDefinitionService.setDomainModelDef(domainModelDefMapper.mapBack(getDomainModelDef()));
                entityDefinitionService.importLocalizedDataAsCSV(selectedFile.getPath());
                // TODO Pensar en el futuro el mapeo debe de tener en cuanta si el elemento esta o no
                DomainModelDef domainModelDef = entityDefinitionService.getDomainModel();
                setDomainModelDef(domainModelDefMapper.map(domainModelDef));
                logger.info("The localized data was imported successfully.");
            } catch (IOException e) {
                logger.error("Error loading DomainModel from file �" + selectedFile.getAbsolutePath() + "�", e);
            }
        }
    }
}
