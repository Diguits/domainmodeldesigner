package com.diguits.domainmodeldesigner.controllers;

import com.diguits.domainmodeldesigner.editors.controllers.ApplicationDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.ColumnDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.CustomFieldDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.EntityDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.RelationshipDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.EnumDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.EnumValueDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.FieldDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.FieldGroupDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.FieldSubgroupDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.FilterDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.FilterValueDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.IndexDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.LocaleDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.LocalizedDataDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.BoundedContextDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.RelationOverrideDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.ModuleDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.DomainModelDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.TemplateDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.TemplateGroupDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.TemplateParameterDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.TemplateProjectApplyConfigEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.TemplateProjectDefEditorController;
import com.diguits.domainmodeldesigner.editors.controllers.ValueObjectDefEditorController;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ColumnDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldGroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldSubgroupDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FilterValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.IndexDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationOverrideDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateParameterDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.editor.controllers.EditorControllerProviderBase;

public class EditorControllerProvider extends EditorControllerProviderBase{

	@Override
	protected void registerControllers() {

		registerControler(EntityDefModel.class, EntityDefEditorController.class);
		registerControler(ValueObjectDefModel.class, ValueObjectDefEditorController.class);
		registerControler(FieldDefModel.class, FieldDefEditorController.class);
		registerControler(FieldGroupDefModel.class, FieldGroupDefEditorController.class);
		registerControler(FieldSubgroupDefModel.class, FieldSubgroupDefEditorController.class);
		registerControler(FilterDefModel.class, FilterDefEditorController.class);
		registerControler(FilterValueDefModel.class, FilterValueDefEditorController.class);
		registerControler(ColumnDefModel.class, ColumnDefEditorController.class);
		registerControler(IndexDefModel.class, IndexDefEditorController.class);
		registerControler(BoundedContextDefModel.class, BoundedContextDefEditorController.class);
		registerControler(ModuleDefModel.class, ModuleDefEditorController.class);
		registerControler(EnumDefModel.class, EnumDefEditorController.class);
		registerControler(EnumValueDefModel.class, EnumValueDefEditorController.class);
		registerControler(ApplicationDefModel.class, ApplicationDefEditorController.class);
		registerControler(RelationOverrideDefModel.class, RelationOverrideDefEditorController.class);
		registerControler(RelationshipDefModel.class, RelationshipDefEditorController.class);
		registerControler(DomainModelDefModel.class, DomainModelDefEditorController.class);
		registerControler(LocaleDefModel.class, LocaleDefEditorController.class);
		registerControler(CustomFieldDefModel.class, CustomFieldDefEditorController.class);
		registerControler(LocalizedDataDefModel.class, LocalizedDataDefEditorController.class);

		registerControler(TemplateProjectDefModel.class, TemplateProjectDefEditorController.class);
		registerControler(TemplateGroupDefModel.class, TemplateGroupDefEditorController.class);
		registerControler(TemplateDefModel.class, TemplateDefEditorController.class);
		registerControler(TemplateParameterDefModel.class, TemplateParameterDefEditorController.class);

		registerControler(TemplateProjectApplyConfigModel.class, TemplateProjectApplyConfigEditorController.class);
		//registerControler(TemplateApplyConfigModel.class, TemplateApplyConfigEditorController.class);
		}

}
