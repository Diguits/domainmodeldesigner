package com.diguits.domainmodeldesigner.application;

import com.diguits.common.mapping.IMapperProvider;
import com.diguits.common.mapping.MapperProvider;
import com.diguits.domainmodeldefinition.serialization.mappers.IDomainModelDefMapper;
import com.diguits.domainmodeldefinition.serialization.mappers.DomainModelDefMapper;
import com.diguits.domainmodeldefinition.serialization.services.*;
import com.diguits.domainmodeldefinition.services.EntityDefinitionService;
import com.diguits.domainmodeldefinition.services.IEntityDefinitionService;
import com.diguits.domainmodeldefinition.services.ILocalizedDataToCSVService;
import com.diguits.domainmodeldefinition.services.ListAllDefsVisitor;
import com.diguits.domainmodeldefinition.services.LocalizedDataToCSVService;
import com.diguits.domainmodeldesigner.controllers.DecoratorProvider;
import com.diguits.domainmodeldesigner.controllers.EditorControllerProvider;
import com.diguits.domainmodeldesigner.editors.views.EditorByDataTypeFactory;
import com.diguits.domainmodeldesigner.editors.views.IEditorByDataTypeFactory;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.services.ListAllDefModelsVisitor;
import com.diguits.domainmodeldesigner.services.TemplateProjectApplyConfigClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectClientService;
import com.diguits.domainmodeldesigner.domainmodel.controllers.DomainModelTreeController;
import com.diguits.domainmodeldesigner.domainmodel.controllers.DomainModelTreeDecorator;
import com.diguits.domainmodeldesigner.domainmodel.modelmappers.IDomainModelModelMapper;
import com.diguits.domainmodeldesigner.domainmodel.modelmappers.DomainModelModelMapper;
import com.diguits.domainmodeldesigner.domainmodelstructure.controllers.DomainModelStructureTreeController;
import com.diguits.domainmodeldesigner.domainmodelstructure.controllers.DomainModelStructureTreeDecorator;
import com.diguits.domainmodeldesigner.template.controllers.TemplateTreeController;
import com.diguits.domainmodeldesigner.template.controllers.TemplateTreeDecorator;
import com.diguits.domainmodeldesigner.template.modelmappers.ITemplateProjectModelMapper;
import com.diguits.domainmodeldesigner.template.modelmappers.TemplateProjectModelMapper;
import com.diguits.domainmodeldesigner.templateapplyconfig.controllers.TemplateApplyConfigTreeController;
import com.diguits.domainmodeldesigner.templateapplyconfig.controllers.TemplateApplyConfigTreeDecorator;
import com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers.ITemplateProjectApplyConfigModelMapper;
import com.diguits.domainmodeldesigner.templateapplyconfig.modelmappers.TemplateProjectApplyConfigModelMapper;
import com.diguits.javafx.container.decorators.IDecoratorProvider;
import com.diguits.javafx.editor.controllers.IEditorControllerProvider;
import com.diguits.templatedefinition.serialization.mappers.ITemplateProjectMapper;
import com.diguits.templatedefinition.serialization.mappers.TemplateProjectMapper;
import com.diguits.templatedefinition.serialization.services.ITemplateProjectSerializer;
import com.diguits.templatedefinition.serialization.services.SimpleXMLTemplateProjectSerializer;
import com.diguits.templatedefinition.services.ITemplateFileManager;
import com.diguits.templatedefinition.services.ITemplateProjectService;
import com.diguits.templatedefinition.services.TemplateFileManager;
import com.diguits.templatedefinition.services.TemplateProjectService;
import com.diguits.templateengine.ExpressionCodeBuilder;
import com.diguits.templateengine.TemplateCodeBuilder;
import com.diguits.templateengine.TemplateEngine;
import com.diguits.templateengine.compiler.ExpressionClassLoader;
import com.diguits.templateengine.compiler.TemplateClassLoader;
import com.diguits.templateengine.compiler.TemplateCompiler;
import com.diguits.templateengine.contract.IExpressionClassLoader;
import com.diguits.templateengine.contract.IExpressionCodeBuilder;
import com.diguits.templateengine.contract.ITemplateClassLoader;
import com.diguits.templateengine.contract.ITemplateCodeBuilder;
import com.diguits.templateengine.contract.ITemplateCompiler;
import com.diguits.templateengine.contract.ITemplateEngine;
import com.diguits.templateengine.contract.ITemplateEngineService;
import com.diguits.templateengine.contract.ITemplateProjectApplyConfigService;
import com.diguits.templateengine.serialization.mappers.ITemplateProjectApplyConfigMapper;
import com.diguits.templateengine.serialization.mappers.TemplateProjectApplyConfigMapperMapper;
import com.diguits.templateengine.serialization.service.ITemplateProjectApplyConfigSerializer;
import com.diguits.templateengine.serialization.service.SimpleXMLTemplateProjectApplyConfigSerializer;
import com.diguits.templateengine.services.TemplateEngineService;
import com.diguits.templateengine.services.TemplateProjectApplyConfigService;
import com.google.inject.AbstractModule;

import javafx.application.HostServices;

public class DomainModelDesignerModule extends AbstractModule {

    HostServices hostServices;

    public DomainModelDesignerModule(HostServices hostServices) {
        super();
        this.hostServices = hostServices;
    }

    @Override
    protected void configure() {
        bind(JsonEntityDefinitionSerializer.class);
        bind(SimpleXMLEntityDefinitionSerializer.class);
        bind(ISerializerProvider.class).to(SerializerProvider.class);
        bind(IEntityDefinitionService.class).to(EntityDefinitionService.class);
        bind(LocalizedDataToCSVSerializer.class);
        bind(ListAllDefsVisitor.class);
        bind(ILocalizedDataToCSVService.class).to(LocalizedDataToCSVService.class);
        bind(IDomainModelDefMapper.class).to(DomainModelDefMapper.class);
        bind(IDomainModelModelMapper.class).to(DomainModelModelMapper.class);
        bind(IMapperProvider.class).to(MapperProvider.class).asEagerSingleton();
        bind(IEditorControllerProvider.class).to(EditorControllerProvider.class).asEagerSingleton();

        //Holes
        bind(DomainModelClientService.class).asEagerSingleton();
        bind(ListAllDefModelsVisitor.class).asEagerSingleton();
        bind(com.diguits.domainmodeldesigner.services.TemplateProjectClientService.class).asEagerSingleton();
        bind(HostServices.class).toInstance(hostServices);

        //Containers
        bind(DomainModelTreeController.class).asEagerSingleton();
        bind(DomainModelTreeDecorator.class).asEagerSingleton();

        bind(TemplateApplyConfigTreeController.class).asEagerSingleton();
        bind(TemplateApplyConfigTreeDecorator.class).asEagerSingleton();

        bind(TemplateTreeController.class).asEagerSingleton();
        bind(TemplateTreeDecorator.class).asEagerSingleton();

        bind(DomainModelStructureTreeController.class).asEagerSingleton();
        bind(DomainModelStructureTreeDecorator.class).asEagerSingleton();

        //Template Mappers
        bind(ITemplateProjectMapper.class).to(TemplateProjectMapper.class).asEagerSingleton();
        bind(ITemplateProjectSerializer.class).to(SimpleXMLTemplateProjectSerializer.class).asEagerSingleton();
        bind(ITemplateProjectService.class).to(TemplateProjectService.class).asEagerSingleton();
        bind(ITemplateFileManager.class).to(TemplateFileManager.class).asEagerSingleton();
        bind(ITemplateProjectModelMapper.class).to(TemplateProjectModelMapper.class).asEagerSingleton();
        bind(ITemplateEngineService.class).to(TemplateEngineService.class).asEagerSingleton();
        bind(ITemplateCodeBuilder.class).to(TemplateCodeBuilder.class).asEagerSingleton();
        bind(ITemplateClassLoader.class).to(TemplateClassLoader.class).asEagerSingleton();
        bind(ITemplateCompiler.class).to(TemplateCompiler.class).asEagerSingleton();
        bind(ITemplateEngine.class).to(TemplateEngine.class).asEagerSingleton();
        bind(IEditorByDataTypeFactory.class).to(EditorByDataTypeFactory.class).asEagerSingleton();

        bind(ITemplateProjectApplyConfigModelMapper.class).to(TemplateProjectApplyConfigModelMapper.class).asEagerSingleton();
        bind(ITemplateProjectApplyConfigService.class).to(TemplateProjectApplyConfigService.class).asEagerSingleton();
        bind(ITemplateProjectApplyConfigSerializer.class).to(SimpleXMLTemplateProjectApplyConfigSerializer.class).asEagerSingleton();
        bind(ITemplateProjectApplyConfigMapper.class).to(TemplateProjectApplyConfigMapperMapper.class).asEagerSingleton();
        bind(TemplateProjectClientService.class).asEagerSingleton();
        bind(TemplateProjectApplyConfigClientService.class).asEagerSingleton();

        bind(IExpressionCodeBuilder.class).to(ExpressionCodeBuilder.class).asEagerSingleton();
        bind(IExpressionClassLoader.class).to(ExpressionClassLoader.class).asEagerSingleton();

        bind(ITemplateEngine.class).to(TemplateEngine.class).asEagerSingleton();
        bind(IDecoratorProvider.class).to(DecoratorProvider.class).asEagerSingleton();


    }
}
