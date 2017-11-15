package com.diguits.domainmodeldesigner.templateapplyconfig.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.services.TemplateProjectApplyConfigClientService;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigItemModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.models.TemplateProjectApplyConfigModel;
import com.diguits.domainmodeldesigner.templateapplyconfig.views.TemplateApplyConfigTreeView;
import com.diguits.javafx.container.controllers.TreeContainerController;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TemplateApplyConfigTreeController
		extends TreeContainerController<TemplateApplyConfigTreeView, TemplateProjectApplyConfigItemModel> {

	public class CustomCellFactory extends DefaultCellFactory {

		public CustomCellFactory(TreeView<TemplateProjectApplyConfigItemModel> treeView) {
			super(treeView);
		}

		@Override
		public TreeCell<TemplateProjectApplyConfigItemModel> call(TreeView<TemplateProjectApplyConfigItemModel> tv) {
			TextFieldTreeCell<TemplateProjectApplyConfigItemModel> cell = (TextFieldTreeCell<TemplateProjectApplyConfigItemModel>)super.call(tv);
			cell.itemProperty().addListener(new ChangeListener<TemplateProjectApplyConfigItemModel>() {

				private ChangeListener<? super Boolean> defaultChangeListener = new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						if(newValue){
							Font font = cell.getFont();
							cell.setFont(Font.font(font.getFamily(), FontWeight.BOLD, font.getSize()));
						} else {
							Font font = cell.getFont();
							cell.setFont(Font.font(font.getFamily(), FontWeight.NORMAL, font.getSize()));
						}
					}
				};

				@Override
				public void changed(ObservableValue<? extends TemplateProjectApplyConfigItemModel> observable, TemplateProjectApplyConfigItemModel oldValue,
						TemplateProjectApplyConfigItemModel newValue) {
					if (oldValue != null && oldValue != newValue && oldValue instanceof TemplateProjectApplyConfigModel)
						((TemplateProjectApplyConfigModel)oldValue).isDefaultProperty().removeListener(defaultChangeListener);
					if (newValue != null && newValue instanceof TemplateProjectApplyConfigModel) {
						((TemplateProjectApplyConfigModel)newValue).isDefaultProperty().addListener(defaultChangeListener);
					}
				}
			});

			return cell;
		}


	}

	@Inject
	TemplateProjectApplyConfigClientService templateApplyConfigClientService;

	@Inject
	TemplateApplyConfigTreeBuilder treeBuilder;

	@Inject
	DomainModelClientService domainModelService;

	@Inject
	public TemplateApplyConfigTreeController(TemplateApplyConfigTreeView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		loadConfis();
	}

	public void loadConfis() {
		ObservableList<TemplateProjectApplyConfigModel> configs = templateApplyConfigClientService.loadConfigs();
		fill(treeBuilder, configs);
	}

	@Override
	protected ContainerPos getDefaultPosition() {
		return ContainerPos.RIGTH;
	}

	@Override
	public boolean isTreeItemContainer(TemplateProjectApplyConfigItemModel item) {
		return item.getClass() == TemplateProjectApplyConfigItemModel.class;
	}

	@Override
	protected NamedModelBase findModel(UUID modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Class<?>> getModelClasses() {
		List<Class<?>> result = new ArrayList<Class<?>>();
		result.add(TemplateApplyConfigModel.class);
		result.add(TemplateProjectApplyConfigModel.class);
		return result;
	}

	public void setDefault() {
		TemplateProjectApplyConfigItemModel selectedModel = getSelectedModel();
		if (selectedModel != null && selectedModel instanceof TemplateProjectApplyConfigModel)
			templateApplyConfigClientService.setDefault((TemplateProjectApplyConfigModel) selectedModel);
	}

	public void addTemplateProjectApplyConfig() {
		templateApplyConfigClientService.addTemplateProjectApplyCongig();
	}

	public void removeTemplateProjectApplyConfig() {
		TemplateProjectApplyConfigItemModel selectedModel = getSelectedModel();
		if (selectedModel != null && selectedModel instanceof TemplateProjectApplyConfigModel)
			if (alertHelper.confirmDelete("Template Project Apply Configuration", selectedModel.getName()))
				templateApplyConfigClientService
						.removeTemplateProjectApplyConfig((TemplateProjectApplyConfigModel) selectedModel);
	}

	@Override
	protected void configureTreeCell(TextFieldTreeCell<TemplateProjectApplyConfigItemModel> treeCell,
			TemplateProjectApplyConfigItemModel newValue) {
		if (newValue != null && newValue instanceof TemplateProjectApplyConfigModel
				&& ((TemplateProjectApplyConfigModel) newValue).getIsDefault()) {
			Font font = treeCell.getFont();
			treeCell.setFont(Font.font(font.getFamily(), FontWeight.BOLD, font.getSize()));
		} else
			super.configureTreeCell(treeCell, newValue);
	}

	@Override
	protected TreeContainerController<TemplateApplyConfigTreeView, TemplateProjectApplyConfigItemModel>.DefaultCellFactory getCellFactory(
			TreeView<TemplateProjectApplyConfigItemModel> treeView) {
		return new CustomCellFactory(treeView);
	}
}
