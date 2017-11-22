package com.diguits.domainmodeldesigner.template.controllers;

import com.diguits.domainmodeldesigner.navigation.controllers.TemplateSelectorNavigationController;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectItemDefModel;
import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.OptionGroup;
import com.diguits.javafx.container.decorators.OptionListBuilder;
import com.diguits.javafx.container.decorators.TreeContainerDecorator;
import com.diguits.javafx.navigation.controllers.NavigationDecorator;
import com.diguits.javafx.system.controllers.SystemDecorator;
import com.google.inject.Inject;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

public class TemplateTreeDecorator extends TreeContainerDecorator<TemplateProjectItemDefModel, TemplateTreeController> {

	public static final String TEMPLATE = "template";

	@Inject
	DomainModelClientService domainModelClientService;

	Action templateMenuAction;
	// Project
	Action saveTemplateProjectsAction;
	Action addProjectAction;
	Action removeProjectAction;
	Action generateProjectCodeAction;
	Action compileProjectAction;
	Action applyProjectAction;
	// ModuleAction;
	Action addGroupAction;
	Action removeGroupAction;
	// EntityAction;
	Action addTemplateAction;
	Action removeTemplateAction;
	Action generateTemplateCodeAction;
	Action compileTemplateAction;
	Action applyTemplateAction;
	// Navigation
	Action navigateToTemplate;

	@Inject
	public TemplateTreeDecorator(TemplateTreeController controller) {
		super(controller);
	}

	@Inject
	TemplateSelectorNavigationController templateNavigationController;

	@Override
	protected String getMenuBundleName() {
		return "TemplateTreeBundle";
	}

	@Override
	protected void createActions() {
		super.createActions();
		BooleanBinding templateProjectNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(TemplateProjectDefModel.class);
		BooleanBinding templateGroupNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(TemplateGroupDefModel.class);
		BooleanBinding templateNotSelected = controller.globalSelectedModelClassProperty()
				.isNotEqualTo(TemplateDefModel.class);

		templateMenuAction = Action.builder(resources)
				.textKey("template")
				.toolTipKey("template_main_menu_tool_tip")
				.build();

		// Project
		saveTemplateProjectsAction = Action.builder(resources)
				.textKey("save_templates")
				.image("clipboard_save")
				.toolTipKey("save_template_tool_tip")
				.action(e -> controller.saveTemplateProjects())
				.build();

		addProjectAction = Action.builder(resources)
				.textKey("add_project")
				.image("clipboard_add")
				.toolTipKey("add_template_project_tool_tip")
				.action(e -> controller.addProject())
				.build();

		removeProjectAction = Action.builder(resources)
				.textKey("remove_project")
				.image("clipboard_delete")
				.toolTipKey("remove_template_project_tool_tip")
				.action(e -> controller.removeProject())
				.disableWhen(templateProjectNotSelected)
				.build();

		generateProjectCodeAction = Action.builder(resources).image("cog")
				.textKey("generate_code_template_project")
				.toolTipKey("generate_code_template_project_tool_tip")
				.disableWhen(templateProjectNotSelected
						.and(templateGroupNotSelected)
						.and(templateNotSelected))
				.action(e -> controller.generateProjectCode())
				.build();
		compileProjectAction = Action.builder(resources)
				.textKey("compile_template_project")
				.image("compile")
				.toolTipKey("compile_template_project_tool_tip")
				.disableWhen(templateProjectNotSelected
						.and(templateGroupNotSelected)
						.and(templateNotSelected))
				.action(e -> controller.compileProject())
				.build();
		applyProjectAction = Action.builder(resources)
				.textKey("apply_template_project")
				.image("play")
				.toolTipKey("apply_template_project_tool_tip")
				.disableWhen(domainModelClientService.domainModelDefProperty().isNull())
				.action(e -> controller.applyProject())
				.build();
		// Group
		addGroupAction = Action.builder(resources)
				.textKey("add_group")
				.image("folder_script_add")
				.toolTipKey("add_template_group_tool_tip")
				.action(e -> controller.addGroup())
				.disableWhen(templateProjectNotSelected.and(templateGroupNotSelected).and(templateNotSelected))
				.build();
		removeGroupAction = Action.builder(resources)
				.textKey("remove_group")
				.image("folder_script_delete")
				.toolTipKey("remove_template_group_tool_tip")
				.action(e -> controller.removeGroup())
				.disableWhen(templateGroupNotSelected)
				.build();
		// Template
		addTemplateAction = Action.builder(resources)
				.textKey("add_template")
				.image("script_add")
				.toolTipKey("add_template_tool_tip")
				.action(e -> controller.addTemplate())
				.disableWhen(templateGroupNotSelected.and(templateNotSelected))
				.build();
		removeTemplateAction = Action.builder(resources)
				.textKey("remove_template")
				.image("script_delete")
				.toolTipKey("remove_template_tool_tip")
				.action(e -> controller.removeTemplate())
				.disableWhen(templateNotSelected)
				.build();
		generateTemplateCodeAction = Action.builder(resources)
				.textKey("generate_code_template")
				.image("script_gear")
				.toolTipKey("generate_code_template_tool_tip")
				.action(e -> controller.generateTemplateCode())
				.disableWhen(templateNotSelected)
				.build();
		compileTemplateAction = Action.builder(resources)
				.textKey("compile_template")
				.image("script_binary")
				.toolTipKey("compile_template_tool_tip")
				.action(e -> controller.compileTemplate())
				.disableWhen(templateNotSelected)
				.build();
		applyTemplateAction = Action.builder(resources)
				.textKey("apply_template")
				.image("script_play")
				.toolTipKey("apply_template_tool_tip")
				.action(e -> controller.applyTemplate())
				.disableWhen(templateNotSelected.or(domainModelClientService.domainModelDefProperty().isNull()))
				.build();
		// Navigation
		navigateToTemplate = Action.builder(resources)
				.textKey("navigate_to_template")
				.image("script_compass")
				.toolTipKey("navigate_to_template_tool_tip")
				.keyCombination(KeyCode.T, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN)
				.action(e -> templateNavigationController.show())
				.build();

	}

	@Override
	protected void buildMainToolBarOptions(OptionListBuilder builder) {
		// Project
		builder.addOption()
				.action(saveTemplateProjectsAction)
				.priority(1200);

		builder.addOption()
				.action(addProjectAction)
				.priority(1250);

		/*
		 * builder.addOption() .action(removeProjectAction) .priority(1300);
		 */

		builder.addOption()
				.action(generateProjectCodeAction)
				.priority(1400);

		builder.addOption()
				.action(compileProjectAction)
				.priority(1500);

		builder.addSeparator(1550);

		builder.addOption()
				.action(applyProjectAction)
				.priority(1600);

		builder.addSeparator(1610);

		// Group
		builder.addOption()
				.action(addGroupAction)
				.priority(1700);

		/*
		 * builder.addOption() .action(removeGroupAction) .priority(1800);
		 */

		builder.addSeparator(1810);

		// Template

		builder.addOption()
				.action(addTemplateAction)
				.priority(1900);

		/*
		 * builder.addOption() .action(removeTemplateAction) .priority(2000);
		 */

		builder.addOption()
				.action(generateTemplateCodeAction)
				.priority(2100);

		builder.addOption()
				.action(compileTemplateAction)
				.priority(2200);

		builder.addOption()
				.action(applyTemplateAction)
				.priority(2300);

		builder.addSeparator(2310);
	}

	@Override
	protected void buildContextMenuOptions(TemplateProjectItemDefModel model, OptionListBuilder builder) {
		super.buildContextMenuOptions(model, builder);
		if (model != null) {
			Class<? extends TemplateProjectItemDefModel> modelClass = model.getClass();
			if (modelClass == TemplateProjectDefModel.class) {
				builder.addSeparator(90);
				builder.addOption()
						.action(addGroupAction)
						.priority(100);
				builder.addSeparator(110);
				builder.addOption()
						.action(removeProjectAction)
						.priority(200);
			} else if (modelClass == TemplateGroupDefModel.class) {
				builder.addSeparator(90);
				builder.addOption()
						.action(addTemplateAction)
						.priority(100);
				builder.addSeparator(110);
				builder.addOption()
						.action(removeGroupAction)
						.priority(200);
			} else if (modelClass == TemplateDefModel.class) {
				builder.addSeparator(90);
				builder.addOption()
						.action(removeTemplateAction)
						.priority(100);
				builder.addSeparator(110);
				builder.addOption()
						.action(generateTemplateCodeAction)
						.priority(120);

				builder.addOption()
						.action(compileTemplateAction)
						.priority(130);

				builder.addOption()
						.action(applyTemplateAction)
						.priority(140);

			}
		}
	}

	@Override
	public String getImageName() {
		return "folder_clipboard";
	}

	@Override
	public String getTitleResource() {
		return "template_projects";
	}

	@Override
	public ImageView getImageForModel(TemplateProjectItemDefModel item) {
		if (item != null)
			return getModelImage(item);
		return null;
	}

	public static ImageView getModelImage(TemplateProjectItemDefModel item) {
		String imageName = null;
		Class<?> modelClass = item.getClass();
		if (modelClass == TemplateProjectDefModel.class)
			imageName = "clipboard_empty";
		else if (modelClass == TemplateGroupDefModel.class)
			imageName = "folder_script";
		else if (modelClass == TemplateDefModel.class) {
			if (((TemplateDefModel) item).getActive())
				imageName = "script";
			else
				imageName = "script_error";
		}
		if (imageName != null && !imageName.isEmpty())
			return new ImageView("images/" + imageName + ".png");

		return null;
	}

	@Override
	protected void buildMainMenuOptions(OptionListBuilder builder) {
		// File
		builder.addSeparator(SystemDecorator.FILE)
				.priority(410);
		builder.addOption(SystemDecorator.FILE)
				.action(saveTemplateProjectsAction)
				.priority(500);

		OptionGroup templeteOptionGroup = builder.addOptionGroup(TEMPLATE)
				.action(templateMenuAction)
				.priority(400)
				.get();

		// Project
		templeteOptionGroup.addOption()
				.action(addProjectAction)
				.priority(200);
		/*
		 * templeteOptionGroup.addOption() .action(removeProjectAction) .priority(300);
		 * templeteOptionGroup.addSeparator(310);
		 */
		// Group
		templeteOptionGroup.addOption()
				.action(addGroupAction)
				.priority(400);
		/*
		 * templeteOptionGroup.addOption() .action(removeGroupAction) .priority(500);
		 * templeteOptionGroup.addSeparator(510);
		 */
		// Template

		templeteOptionGroup.addOption()
				.action(addTemplateAction)
				.priority(600);
		/*
		 * templeteOptionGroup.addOption() .action(removeTemplateAction) .priority(700);
		 */

		templeteOptionGroup.addSeparator(700);

		templeteOptionGroup.addOption()
				.action(generateProjectCodeAction)
				.priority(800);

		templeteOptionGroup.addOption()
				.action(compileProjectAction)
				.priority(900);

		templeteOptionGroup.addOption()
				.action(applyProjectAction)
				.priority(1000);

		templeteOptionGroup.addSeparator(1050);

		templeteOptionGroup.addOption()
				.action(generateTemplateCodeAction)
				.priority(1100);

		templeteOptionGroup.addOption()
				.action(compileTemplateAction)
				.priority(1200);

		templeteOptionGroup.addOption()
				.action(applyTemplateAction)
				.priority(1300);

		// Navigate
		builder.addOption(NavigationDecorator.NAVIGATE)
				.action(navigateToTemplate)
				.priority(450);
	}
}
