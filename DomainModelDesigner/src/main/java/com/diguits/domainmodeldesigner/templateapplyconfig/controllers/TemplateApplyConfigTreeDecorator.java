package com.diguits.domainmodeldesigner.templateapplyconfig.controllers;

import com.diguits.domainmodeldesigner.template.controllers.TemplateTreeDecorator;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigItemModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.javafx.container.decorators.Action;
import com.diguits.javafx.container.decorators.OptionListBuilder;
import com.diguits.javafx.container.decorators.TreeContainerDecorator;
import com.google.inject.Inject;

import javafx.scene.image.ImageView;

public class TemplateApplyConfigTreeDecorator
		extends TreeContainerDecorator<TemplateProjectApplyConfigItemModel, TemplateApplyConfigTreeController> {

	Action setDefaultAction;
	Action addProjctApplyConfigAction;
	Action removeProjctApplyConfigAction;

	@Inject
	TemplateApplyConfigTreeController templateApplyConfigTreeController;

	@Inject
	public TemplateApplyConfigTreeDecorator(TemplateApplyConfigTreeController controller) {
		super(controller);
	}

	@Override
	protected String getMenuBundleName() {
		return "TemplateApplyConfigTreeBundle";
	}

	@Override
	protected void createActions() {
		super.createActions();
		setDefaultAction = Action.builder(resources)
				.textKey("set_default")
				.image("clipboard_valid")
				.action(e -> controller.setDefault())
				.build();

		addProjctApplyConfigAction = Action.builder(resources)
				.textKey("add_project_apply_config")
				.image("clipboard_setting_add")
				.action(e -> controller.addTemplateProjectApplyConfig())
				.build();

		removeProjctApplyConfigAction = Action.builder(resources)
				.textKey("remove_project_apply_config")
				.image("clipboard_setting_delete")
				.action(e -> controller.removeTemplateProjectApplyConfig())
				.build();
	}

	@Override
	protected void buildContextMenuOptions(TemplateProjectApplyConfigItemModel model, OptionListBuilder builder) {
		if (model != null) {
			Class<? extends TemplateProjectApplyConfigItemModel> modelClass = model.getClass();
			if (modelClass == TemplateProjectApplyConfigModel.class) {
				super.buildContextMenuOptions(model, builder);
				builder.addOption().action(setDefaultAction).priority(100);
				builder.addSeparator(110);
				builder.addOption().action(addProjctApplyConfigAction).priority(200);
				builder.addOption().action(removeProjctApplyConfigAction).priority(300);
			}
		}
	}

	@Override
	protected void buildMainToolBarOptions(OptionListBuilder builder) {
		builder.addOption()
				.action(addProjctApplyConfigAction)
				.priority(1275);

	}

	@Override
	protected void buildMainMenuOptions(OptionListBuilder builder) {
		builder.addSeparator(TemplateTreeDecorator.TEMPLATE)
		.priority(630);
		builder.addOption(TemplateTreeDecorator.TEMPLATE)
		.action(addProjctApplyConfigAction)
		.priority(670);

	}

	@Override
	public String getImageName() {
		return "script_setting";
	}

	@Override
	public ImageView getImageForModel(TemplateProjectApplyConfigItemModel item) {
		if (item != null) {
			if (item instanceof TemplateProjectApplyConfigModel
					&& ((TemplateProjectApplyConfigModel) item).getIsDefault())
				return new ImageView("images/clipboard_valid.png");
			return getImageForModel(item.getClass());
		}
		return null;
	}

	public static ImageView getImageForModel(Class<? extends TemplateProjectApplyConfigItemModel> modelClass) {
		String imageName = null;
		if (modelClass == TemplateApplyConfigModel.class)
			imageName = "script_setting";
		else if (modelClass == TemplateProjectApplyConfigModel.class)
			imageName = "clipboard_setting";
		if (imageName != null && !imageName.isEmpty())
			return new ImageView("images/" + imageName + ".png");
		return null;
	}

	@Override
	public String getTitleResource() {
		return "apply_configs";
	}

}
