package com.diguits.domainmodeldesigner.domainmodel.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.ApplicationDef;
import com.diguits.domainmodeldefinition.definitions.EntityDef;
import com.diguits.domainmodeldefinition.definitions.BoundedContextDef;
import com.diguits.domainmodeldefinition.definitions.ModuleDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.services.tasks.ExportLocalizedDataModelToCSVTask;
import com.diguits.domainmodeldesigner.services.tasks.ImportLocalizedDataModelFromCSVTask;
import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.domainmodeldesigner.domainmodel.views.DomainModelTreeView;
import com.diguits.javafx.container.controllers.TreeContainerController;
import com.diguits.javafx.container.views.TreeContainerView;
import com.diguits.javafx.model.ModelBase;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;

public class DomainModelTreeController extends TreeContainerController<TreeContainerView<BaseDefModel>, BaseDefModel> {

	@Inject
	private DomainModelClientService domainModelClientService;

	@Inject
	private DomainModelTreeBuilder treeBuilder;

	@Inject
	private ExportLocalizedDataModelToCSVController exportLocalizedDataModelToCSVController;

	@Inject
	private ImportLocalizedDataModelFromCSVController importLocalizedDataModelFromCSVController;

	@Inject
	public DomainModelTreeController(DomainModelTreeView view) {
		super(view);
	}

	@Override
	protected ContainerPos getDefaultPosition() {
		return ContainerPos.LEFT;
	}

	public void createDomainModel() {
		domainModelClientService.create();
		reload();
	}

	private void reload() {
		editorsController.hideAllOfModelClass(BaseDefModel.class);
		fill(treeBuilder, domainModelClientService.getDomainModelDef());
	}

	public void loadDomainModel() {
		domainModelClientService.load();
		reload();
	}

	public void saveDomainModel() {
		domainModelClientService.save();
	}

	public void saveDomainModelAs() {
		domainModelClientService.saveAs();
	}

	public void addBoundedContext() {
		domainModelClientService.addBoundedContext();
	}

	public void removeBoundedContext() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof BoundedContextDefModel) {
			BoundedContextDefModel boundedContext = (BoundedContextDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("BoundedContext", boundedContext.getName()))
				domainModelClientService.removeBoundedContext(boundedContext);
		}
	}

	public void addModule() {
		BoundedContextDefModel boundedContext = null;
		ModelBase globalSelectedModel = getGlobalSelectedModel();
		if (globalSelectedModel != null) {
			if (globalSelectedModel instanceof BoundedContextDefModel) {
				boundedContext = (BoundedContextDefModel) globalSelectedModel;
			} else if (globalSelectedModel instanceof ModuleDefModel) {
				boundedContext = (BoundedContextDefModel) ((BaseDefModel) globalSelectedModel).getOwner();
			} else if (globalSelectedModel instanceof EntityDefModel
					&& ((EntityDefModel) globalSelectedModel).getModule() != null) {
				boundedContext = (BoundedContextDefModel) ((EntityDefModel) globalSelectedModel).getModule().getOwner();
			}
			if (boundedContext != null)
				domainModelClientService.addModule(boundedContext);
		}
	}

	public void removeModule() {
		if (getSelectedModel() != null && getGlobalSelectedModel() instanceof ModuleDefModel) {
			ModuleDefModel module = (ModuleDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("Module", module.getName()))
				domainModelClientService.removeModule(module);
		}
	}

	public void addEntity() {
		ModuleDefModel module = null;
		if (getSelectedModel() != null) {
			if (getSelectedModel() instanceof ModuleDefModel) {
				module = (ModuleDefModel) getSelectedModel();
			} else if (getSelectedModel() instanceof EntityDefModel) {
				module = ((EntityDefModel) getSelectedModel()).getModule();
			} else if (getSelectedModel().getOwner() instanceof ModuleDefModel) {
				module = (ModuleDefModel) getSelectedModel().getOwner();
			}
			if (module != null)
				domainModelClientService.addEntity(module);
		}
	}

	public void removeEntity() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof EntityDefModel) {
			EntityDefModel entity = (EntityDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("Entity", entity.getName()))
				domainModelClientService.removeEntity(entity);
		}
	}

	public void addValueObject() {
		ModuleDefModel module = null;
		if (getSelectedModel() != null) {
			if (getSelectedModel() instanceof ModuleDefModel) {
				module = (ModuleDefModel) getSelectedModel();
			} else if (getSelectedModel() instanceof ValueObjectDefModel) {
				module = ((ValueObjectDefModel) getSelectedModel()).getModule();
			} else if (getSelectedModel().getOwner() instanceof ModuleDefModel) {
				module = (ModuleDefModel) getSelectedModel().getOwner();
			}

			if (module != null)
				domainModelClientService.addValueObject(module);
		}
	}

	public void removeValueObject() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof ValueObjectDefModel) {
			ValueObjectDefModel valueObject = (ValueObjectDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("ValueObject", valueObject.getName()))
				domainModelClientService.removeValueObject(valueObject);
		}
	}

	public void addEnum() {
		ModuleDefModel module = null;
		if (getSelectedModel() != null) {
			if (getSelectedModel() instanceof ModuleDefModel) {
				module = (ModuleDefModel) getSelectedModel();
			} else if (getSelectedModel() instanceof EnumDefModel) {
				module = ((EnumDefModel) getSelectedModel()).getModule();
			} else if (getSelectedModel().getOwner() instanceof ModuleDefModel) {
				module = (ModuleDefModel) getSelectedModel().getOwner();
			}

			if (module != null)
				domainModelClientService.addEnum(module);
		}
	}

	public void removeEnum() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof EnumDefModel) {
			EnumDefModel enumDefModel = (EnumDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("Enum", enumDefModel.getName()))
				domainModelClientService.removeEnum(enumDefModel);
		}
	}

	public void addApplication() {
		domainModelClientService.addApplication();
	}

	public void removeApplication() {
		if (getGlobalSelectedModel() != null && getGlobalSelectedModel() instanceof ApplicationDefModel) {
			ApplicationDefModel application = (ApplicationDefModel) getGlobalSelectedModel();
			if (alertHelper.confirmDelete("Application", application.getName()))
				domainModelClientService.removeApplication(application);
		}
	}

	@Override
	public boolean isTreeItemContainer(BaseDefModel item) {
		return item.getClass() == BaseDefModel.class;
	}

	@Override
	protected NamedModelBase findModel(UUID modelId) {
		return domainModelClientService.findModel(modelId);
	}

	@Override
	protected List<Class<?>> getModelClasses() {
		List<Class<?>> result = new ArrayList<Class<?>>();
		result.add(DomainModelDefModel.class);
		result.add(BoundedContextDefModel.class);
		result.add(ModuleDefModel.class);
		result.add(EntityDefModel.class);
		result.add(ApplicationDefModel.class);

		result.add(DomainModelDef.class);
		result.add(BoundedContextDef.class);
		result.add(ModuleDef.class);
		result.add(EntityDef.class);
		result.add(ApplicationDef.class);
		return result;
	}

	public void exportLocalizedDataAsCSV() {
		ExportLocalizedDataModelToCSVTask task = domainModelClientService.getExportLocalizedDataModelToCSVTask();
		if (task != null) {
			exportLocalizedDataModelToCSVController.startModal(task);
		}
	}

	public void importLocalizedDataAsCSV() {
		ImportLocalizedDataModelFromCSVTask task = domainModelClientService.getExportLocalizedDataModelFromCSVTask();
		if (task != null) {
			importLocalizedDataModelFromCSVController.startModal(task);
		}
	}
}
