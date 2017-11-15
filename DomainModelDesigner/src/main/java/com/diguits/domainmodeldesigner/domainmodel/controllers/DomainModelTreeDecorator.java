package com.diguits.domainmodeldesigner.domainmodel.controllers;

import com.diguits.domainmodeldesigner.navigation.controllers.EntitySelectorNavigationController;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.OptionGroup;
import com.diguits.javafx.container.decorators.OptionListBilder;
import com.diguits.javafx.container.decorators.TreeContainerDecorator;
import com.diguits.javafx.navigation.controllers.NavigationDecorator;
import com.diguits.javafx.system.controllers.SystemDecorator;
import com.google.inject.Inject;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

public class DomainModelTreeDecorator extends TreeContainerDecorator<BaseDefModel, DomainModelTreeController> {

	public static final String SUITE = "domainModel";

	@Inject
	DomainModelClientService domainModelClientService;

	@Inject
	EntitySelectorNavigationController entityNavigationController;

	private Action domainModelAction;

	private Action createDomainModelAction;

	private Action loadDomainModelAction;

	private Action saveDomainModelAction;

	private Action saveDomainModelAsAction;

	private Action addBoundedContextAction;

	private Action removeBoundedContextAction;

	private Action addModuleAction;

	private Action removeModuleAction;

	private Action addEntityAction;

	private Action removeEntityAction;

	private Action addValueObjectAction;

	private Action removeValueObjectAction;

	private Action addEnumAction;

	private Action removeEnumAction;

	private Action addApplicationAction;

	private Action removeApplicationAction;

	private Action navigateToEntity;

	private Action exportLocalizedDataAsCSV;

	private Action importLocalizedDataAsCSV;



	@Inject
	public DomainModelTreeDecorator(DomainModelTreeController controller) {
		super(controller);
	}

	@Override
	protected void createActions() {
		super.createActions();

		BooleanBinding boundedContextNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(BoundedContextDefModel.class);
		BooleanBinding moduleNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(ModuleDefModel.class);
		BooleanBinding entityNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(EntityDefModel.class);
		BooleanBinding valueObjectNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(ValueObjectDefModel.class);
		BooleanBinding enumNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(EnumDefModel.class);
		BooleanBinding applicationNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(ApplicationDefModel.class);

		BooleanBinding entityListSelected = controller.globalSelectedModelProperty().isNotNull().and(
				Bindings.selectString(controller.globalSelectedModelProperty(), "tag")
						.isEqualTo(DomainModelTreeBuilder.ENTITY));

		BooleanBinding valueObjectListSelected = controller.globalSelectedModelProperty().isNotNull().and(
				Bindings.selectString(controller.globalSelectedModelProperty(), "tag")
						.isEqualTo(DomainModelTreeBuilder.VALUE_OBJECT));

		BooleanBinding enumListSelected = controller.globalSelectedModelProperty().isNotNull().and(
				Bindings.selectString(controller.globalSelectedModelProperty(), "tag")
						.isEqualTo(DomainModelTreeBuilder.ENUM));

		domainModelAction = Action.builder(resources)
				.textKey("domainModel")
				.build();

		createDomainModelAction = Action.builder(resources)
				.textKey("new")
				.image("page_white")
				.toolTipKey("create_domainModel_tool_tip")
				.action(e -> controller.createDomainModel())
				.build();
		loadDomainModelAction = Action.builder(resources)
				.textKey("open")
				.image("folder")
				.toolTipKey("open_domainModel_tool_tip")
				.action(e -> controller.loadDomainModel())
				.build();
		saveDomainModelAction = Action.builder(resources)
				.textKey("save")
				.image("disk")
				.toolTipKey("save_tool_tip")
				.action(e -> controller.saveDomainModel())
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.build();
		saveDomainModelAsAction = Action.builder(resources)
				.textKey("save_as")
				.image("disk_multiple")
				.toolTipKey("save_as_tool_tip")
				.action(e -> controller.saveDomainModelAs())
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.build();
		// DomainModel
		// BoundedContext
		addBoundedContextAction = Action.builder(resources)
				.textKey("add_boundedContext")
				.image("package_add")
				.toolTipKey("add_boundedContext_tool_tip")
				.action(e -> controller.addBoundedContext())
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.build();
		removeBoundedContextAction = Action.builder(resources).image("package_delete")
				.textKey("remove_boundedContext")
				.toolTipKey("remove_boundedContext_tool_tip")
				.action(e -> controller.removeBoundedContext())
				.disableWhen(boundedContextNotSelected)
				.build();
		// Module
		addModuleAction = Action.builder(resources)
				.textKey("add_module")
				.image("folder_page_add")
				.toolTipKey("add_module_tool_tip")
				.action(e -> controller.addModule())
				.disableWhen(boundedContextNotSelected.and(moduleNotSelected).and(entityNotSelected))
				.build();
		removeModuleAction = Action.builder(resources)
				.textKey("remove_module")
				.image("folder_page_delete")
				.toolTipKey("remove_module_tool_tip")
				.action(e -> controller.removeModule())
				.disableWhen(moduleNotSelected)
				.build();
		// Entity
		addEntityAction = Action.builder(resources)
				.textKey("add_entity")
				.image("file_extension_xpi_add")
				.toolTipKey("add_entity_tool_tip")
				.action(e -> controller.addEntity())
				.disableWhen(moduleNotSelected
						.and(entityNotSelected).and(entityListSelected.not()))
				.build();
		removeEntityAction = Action.builder(resources)
				.textKey("remove_entity")
				.image("file_extension_xpi_delete")
				.toolTipKey("remove_entity_tool_tip")
				.action(e -> controller.removeEntity())
				.disableWhen(entityNotSelected)
				.build();

		// ValueObject
		addValueObjectAction = Action.builder(resources)
				.textKey("add_value_object")
				.image("file_extension_qxd_add")
				.toolTipKey("add_value_object_tool_tip")
				.action(e -> controller.addValueObject())
				.disableWhen(moduleNotSelected
						.and(valueObjectNotSelected).and(valueObjectListSelected.not()))
				.build();
		removeValueObjectAction = Action.builder(resources)
				.textKey("remove_value_object")
				.image("file_extension_xpi_delete")
				.toolTipKey("remove_value_object_tool_tip")
				.action(e -> controller.removeValueObject())
				.disableWhen(valueObjectNotSelected)
				.build();

		// Enum
		addEnumAction = Action.builder(resources)
				.textKey("add_enum")
				.image("file_extension_list_add")
				.toolTipKey("add_enum_tool_tip")
				.action(e -> controller.addEnum())
				.disableWhen(moduleNotSelected
						.and(enumNotSelected).and(enumListSelected.not()))
				.build();
		removeEnumAction = Action.builder(resources)
				.textKey("remove_enum")
				.image("file_extension_list_delete")
				.toolTipKey("remove_enum_tool_tip")
				.action(e -> controller.removeEnum())
				.disableWhen(enumNotSelected)
				.build();
		// Application
		addApplicationAction = Action.builder(resources)
				.textKey("add_application")
				.image("application_add")
				.toolTipKey("add_application_tool_tip")
				.action(e -> controller.addApplication())
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.build();
		removeApplicationAction = Action.builder(resources)
				.textKey("remove_application")
				.image("application_delete")
				.toolTipKey("remove_application_tool_tip")
				.action(e -> controller.removeApplication())
				.disableWhen(applicationNotSelected)
				.build();

		//Export-Import Localized Data As CSV
		exportLocalizedDataAsCSV = Action.builder(resources)
				.textKey("export_localized_data_csv")
				.image("locale_import")
				.toolTipKey("export_localized_data_csv_tool_tip")
				.action(e -> controller.exportLocalizedDataAsCSV())
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.build();

		importLocalizedDataAsCSV = Action.builder(resources)
				.textKey("import_localized_data_csv")
				.image("locale_xml")
				.toolTipKey("import_localized_data_csv_tool_tip")
				.action(e -> controller.importLocalizedDataAsCSV())
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.build();

		// Navigation
		navigateToEntity = Action.builder(resources)
				.textKey("remove_application")
				.textKey("navigate_to_entity")
				.image("page_compass")
				.toolTipKey("remove_application_tool_tip")
				.keyCombination(KeyCode.E, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN)
				.action(e -> entityNavigationController.show())
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.build();
	}

	@Override
	protected String getMenuBundleName() {
		return "DomainModelTreeBundle";
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBilder builder) {
		builder.addOption()
				.action(createDomainModelAction)
				.priority(100);
		builder.addOption()
				.action(loadDomainModelAction)
				.priority(200);
		builder.addSeparator(210);
		builder.addOption()
				.action(saveDomainModelAction)
				.priority(300);
		builder.addOption()
				.action(saveDomainModelAsAction)
				.priority(400);
		builder.addSeparator(410);
		// DomainModel
		// BoundedContext
		builder.addOption()
				.action(addBoundedContextAction)
				.priority(500);
		/*
		 * builder.addOption() .action(removeBoundedContextAction) .priority(600); builder.addSeparator(610);
		 */
		// Module
		builder.addOption()
				.action(addModuleAction)
				.priority(700);
		/*
		 * builder.addOption() .action(removeModuleAction) .priority(800); builder.addSeparator(810);
		 */
		// Entity
		builder.addOption()
				.action(addEntityAction)
				.priority(900);
		/*
		 * builder.addOption() .action(removeEntityAction) .priority(930);
		 */
		// ValueObject
		builder.addOption()
				.action(addValueObjectAction)
				.priority(960);
		/*
		 * builder.addOption() .action(removeValueObjectAction) .priority(990);
		 */
		// Enum
		builder.addOption()
				.action(addEnumAction)
				.priority(1000);
		/*
		 * builder.addOption() .action(removeEnumAction) .priority(1030);
		 */
		builder.addSeparator(1050);
		// Application
		builder.addOption()
				.action(addApplicationAction)
				.priority(1100);
		/*
		 * builder.addOption() .action(removeApplicationAction) .priority(1200); builder.addSeparator(1210);
		 */
	}

	@Override
	protected void buildMainMenuOptions(OptionListBilder builder) {
		builder.addOption(SystemDecorator.FILE)
				.action(createDomainModelAction)
				.priority(100);
		builder.addOption(SystemDecorator.FILE)
				.action(loadDomainModelAction)
				.priority(200);
		builder.addSeparator(SystemDecorator.FILE)
				.priority(210);
		builder.addOption(SystemDecorator.FILE)
				.action(saveDomainModelAction)
				.priority(300);
		builder.addOption(SystemDecorator.FILE)
				.action(saveDomainModelAsAction)
				.priority(300);

		OptionGroup domainModelOptionGroup = builder.addOptionGroup(SUITE)
				.action(domainModelAction)
				.priority(300)
				.get();
		// BoundedContext
		domainModelOptionGroup.addOption()
				.action(addBoundedContextAction)
				.priority(100);
		/*domainModelOptionGroup.addOption()
				.action(removeBoundedContextAction)
				.priority(200);
		domainModelOptionGroup.addSeparator(210);*/
		// Module
		domainModelOptionGroup.addOption()
				.action(addModuleAction)
				.priority(300);
		/*domainModelOptionGroup.addOption()
				.action(removeModuleAction)
				.priority(400);
		domainModelOptionGroup.addSeparator(410);*/
		// Entity
		domainModelOptionGroup.addOption()
				.action(addEntityAction)
				.priority(500);
		/*domainModelOptionGroup.addOption()
				.action(removeEntityAction)
				.priority(600);
		domainModelOptionGroup.addSeparator(610);*/
		// ValueObject
		domainModelOptionGroup.addOption()
				.action(addValueObjectAction)
				.priority(620);
		/*domainModelOptionGroup.addOption()
				.action(removeValueObjectAction)
				.priority(630);
		domainModelOptionGroup.addSeparator(640);*/
		// Enum
		domainModelOptionGroup.addOption()
				.action(addEnumAction)
				.priority(650);
		/*domainModelOptionGroup.addOption()
				.action(removeEnumAction)
				.priority(660);
		domainModelOptionGroup.addSeparator(670);*/
		// Application
		domainModelOptionGroup.addOption()
				.action(addApplicationAction)
				.priority(700);
		/*domainModelOptionGroup.addOption()
				.action(removeApplicationAction)
				.priority(800);*/

		domainModelOptionGroup.addSeparator(900);

		domainModelOptionGroup.addOption()
				.action(exportLocalizedDataAsCSV)
				.priority(1000);

		domainModelOptionGroup.addOption()
				.action(importLocalizedDataAsCSV)
				.priority(1100);

		builder.addOption(NavigationDecorator.NAVIGATE)
				.action(navigateToEntity)
				.priority(400);

	}

	@Override
	protected Object getKeyForContextMenuCache(BaseDefModel model) {
		if (model instanceof BaseDefModel) {
			return model;
		}
		return model.getClass();
	}

	@Override
	protected void buildContextMenuOptions(BaseDefModel model, OptionListBilder builder) {
		super.buildContextMenuOptions(model, builder);
		if (model != null) {
			Class<? extends BaseDefModel> modelClass = model.getClass();
			if (modelClass == DomainModelDefModel.class) {
				builder.addOption()
						.action(addBoundedContextAction)
						.priority(100);
				builder.addOption()
						.action(addApplicationAction)
						.priority(200);
			} else if (modelClass == BoundedContextDefModel.class) {
				builder.addOption()
						.action(addModuleAction)
						.priority(100);
				builder.addSeparator(150);
				builder.addOption()
						.action(removeBoundedContextAction)
						.priority(200);
			} else if (modelClass == ModuleDefModel.class) {
				builder.addOption()
						.action(addEntityAction)
						.priority(100);
				builder.addSeparator(110);
				builder.addOption()
						.action(addValueObjectAction)
						.priority(120);
				builder.addSeparator(130);
				builder.addOption()
						.action(addEnumAction)
						.priority(140);
				builder.addSeparator(150);
				builder.addOption()
						.action(removeModuleAction)
						.priority(200);
			} else if (modelClass == EntityDefModel.class || model.getTag().equals(DomainModelTreeBuilder.ENTITY)) {
				builder.addOption()
						.action(addEntityAction)
						.priority(120);
				builder.addSeparator(125);

				builder.addOption()
						.action(removeEntityAction)
						.priority(130);
			} else if (modelClass == ValueObjectDefModel.class
					|| model.getTag().equals(DomainModelTreeBuilder.VALUE_OBJECT)) {
				builder.addOption()
						.action(addValueObjectAction)
						.priority(120);
				builder.addSeparator(125);

				builder.addOption()
						.action(removeValueObjectAction)
						.priority(130);
			} else if (modelClass == EnumDefModel.class || model.getTag().equals(DomainModelTreeBuilder.ENUM)) {
				builder.addOption()
						.action(addEnumAction)
						.priority(120);
				builder.addSeparator(125);

				builder.addOption()
						.action(removeEnumAction)
						.priority(130);
			} else if (modelClass == ApplicationDefModel.class) {
				builder.addOption()
						.action(addApplicationAction)
						.priority(120);
				builder.addSeparator(125);

				builder.addOption()
						.action(removeApplicationAction)
						.priority(130);
			}
		}
	}

	@Override
	public String getImageName() {
		return "folder_stand";
	}

	@Override
	public String getTitleResource() {
		return "entity_domainModel_explorer";
	}

	@Override
	public ImageView getImageForModel(BaseDefModel item) {
		if (item != null)
			return getImageForModel(item.getClass());
		return null;
	}

	public static ImageView getImageForModel(Class<? extends BaseDefModel> modelClass) {
		String imageName = null;
		if (modelClass == DomainModelDefModel.class)
			imageName = "folder_stand";
		else if (modelClass == BoundedContextDefModel.class)
			imageName = "package";
		else if (modelClass == ModuleDefModel.class)
			imageName = "folder_page";
		else if (modelClass == EntityDefModel.class)
			imageName = "file_extension_xpi";
		else if (modelClass == ValueObjectDefModel.class)
			imageName = "file_extension_qxd";
		else if (modelClass == EnumDefModel.class)
			imageName = "file_extension_list";
		else if (modelClass == ApplicationDefModel.class)
			imageName = "application";
		if (imageName != null && !imageName.isEmpty())
			return new ImageView("images/" + imageName + ".png");

		return null;
	}
}
