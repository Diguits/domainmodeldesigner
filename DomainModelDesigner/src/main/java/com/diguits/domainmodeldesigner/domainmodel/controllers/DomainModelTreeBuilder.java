package com.diguits.domainmodeldesigner.domainmodel.controllers;

import com.diguits.domainmodeldesigner.domainmodel.models.ApplicationDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EntityDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ValueObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.EnumDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.BoundedContextDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.ModuleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.SimpleDomainObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.javafx.container.controllers.CustomTreeItem;
import com.diguits.javafx.container.controllers.ITreeContainerController;
import com.diguits.javafx.container.controllers.TreeBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

public class DomainModelTreeBuilder extends TreeBuilder<DomainModelDefModel, BaseDefModel> {

	public static final String ENUM = "Enum";
	public static final String VALUE_OBJECT = "ValueObject";
	public static final String ENTITY = "Entity";

	@Override
	public void fillTree(DomainModelDefModel domainModel, TreeView<BaseDefModel> treeView,
			ITreeContainerController<?, BaseDefModel> container) {
		if (domainModel != null && treeView != null) {
			CustomTreeItem<BaseDefModel> root = new CustomTreeItem<BaseDefModel>(treeView);
			root.setValue(domainModel);
			root.setExpanded(true);
			root.setGraphic(container.getDecorator().getImageForModel(domainModel));

			BaseDefModel boundedContextList = new BaseDefModel();
			boundedContextList.setName(resources.getString("boundedContexts"));
			TreeItem<BaseDefModel> boundedContextListItem = new CustomTreeItem<BaseDefModel>(treeView);
			boundedContextListItem.setValue(boundedContextList);
			boundedContextListItem.setExpanded(true);
			boundedContextListItem.setGraphic(new ImageView("images/folder_package.png"));
			root.getChildren().add(boundedContextListItem);

			for (BoundedContextDefModel boundedContext : domainModel.getBoundedContexts()) {
				addBoundedContext(treeView, boundedContextListItem, boundedContext, domainModel, container);
			}
			domainModel.getBoundedContexts().addListener(new ListChangeListener<BoundedContextDefModel>() {
				@Override
				public void onChanged(
						javafx.collections.ListChangeListener.Change<? extends BoundedContextDefModel> c) {
					if (c.next()) {
						if (c.wasPermutated() || c.wasReplaced())
							return;
						for (BoundedContextDefModel boundedContext : c.getAddedSubList()) {
							TreeItem<BaseDefModel> item = addBoundedContext(treeView, boundedContextListItem,
									boundedContext, domainModel, container);
							container.showEditor(boundedContext, item);
						}
						for (BoundedContextDefModel boundedContext : c.getRemoved()) {
							removeBoundedContext(treeView, boundedContextListItem, boundedContext);
							container.hideEditor(boundedContext);
						}
					}
				}
			});

			domainModel.getEntities().addListener(new ListChangeListener<EntityDefModel>() {
				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends EntityDefModel> c) {
					if (c.next()) {
						if (c.wasPermutated() || c.wasReplaced())
							return;
						for (EntityDefModel entity : c.getAddedSubList()) {
							TreeItem<BaseDefModel> moduleItem = findModuleItem(treeView, entity.getModule());
							TreeItem<BaseDefModel> moduleListItem = findOrCreateModuleItemList(treeView, moduleItem,
									ENTITY);

							TreeItem<BaseDefModel> item = addObjectModel(treeView, moduleListItem, entity, container);
							container.showEditor(entity, item);
						}
						for (EntityDefModel entity : c.getRemoved()) {
							TreeItem<BaseDefModel> moduleItem = findModuleItem(treeView, entity.getModule());
							TreeItem<BaseDefModel> moduleListItem = findModuleItemList(moduleItem,
									ENTITY);
							removeObjectModel(moduleListItem, entity);
							container.hideEditor(entity);
						}
					}
				}
			});

			domainModel.getValueObjects().addListener(new ListChangeListener<ValueObjectDefModel>() {
				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends ValueObjectDefModel> c) {
					if (c.next()) {
						if (c.wasPermutated() || c.wasReplaced())
							return;
						for (ValueObjectDefModel valueObject : c.getAddedSubList()) {
							TreeItem<BaseDefModel> moduleItem = findModuleItem(treeView, valueObject.getModule());
							TreeItem<BaseDefModel> moduleListItem = findOrCreateModuleItemList(treeView, moduleItem,
									VALUE_OBJECT);

							TreeItem<BaseDefModel> item = addObjectModel(treeView, moduleListItem, valueObject,
									container);
							container.showEditor(valueObject, item);
						}
						for (ValueObjectDefModel valueObject : c.getRemoved()) {
							TreeItem<BaseDefModel> moduleItem = findModuleItem(treeView, valueObject.getModule());
							TreeItem<BaseDefModel> moduleListItem = findModuleItemList(moduleItem,
									VALUE_OBJECT);
							removeObjectModel(moduleListItem, valueObject);
							container.hideEditor(valueObject);
						}
					}
				}
			});

			domainModel.getEnums().addListener(new ListChangeListener<EnumDefModel>() {
				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends EnumDefModel> c) {
					if (c.next()) {
						if (c.wasPermutated() || c.wasReplaced())
							return;
						for (EnumDefModel enumDefModel : c.getAddedSubList()) {
							TreeItem<BaseDefModel> moduleItem = findModuleItem(treeView, enumDefModel.getModule());
							TreeItem<BaseDefModel> moduleListItem = findOrCreateModuleItemList(treeView, moduleItem, ENUM);

							TreeItem<BaseDefModel> item = addObjectModel(treeView, moduleListItem, enumDefModel,
									container);
							container.showEditor(enumDefModel, item);
						}
						for (EnumDefModel enumDefModel : c.getRemoved()) {
							TreeItem<BaseDefModel> moduleItem = findModuleItem(treeView, enumDefModel.getModule());
							TreeItem<BaseDefModel> moduleListItem = findModuleItemList(moduleItem,
									ENUM);
							removeObjectModel(moduleListItem, enumDefModel);
							container.hideEditor(enumDefModel);
						}
					}
				}
			});

			addApplications(treeView, root, domainModel, container);
			treeView.setRoot(root);
		}
	}

	protected void removeBoundedContext(TreeView<BaseDefModel> treeView, TreeItem<BaseDefModel> root,
			BoundedContextDefModel boundedContext) {
		removeModel(root, boundedContext);
	}

	private TreeItem<BaseDefModel> addBoundedContext(TreeView<BaseDefModel> treeView, TreeItem<BaseDefModel> root,
			BoundedContextDefModel boundedContext, DomainModelDefModel domainModel,
			ITreeContainerController<?, BaseDefModel> container) {
		TreeItem<BaseDefModel> boundedContextItem = addModel(treeView, root, boundedContext, container);
		for (ModuleDefModel module : boundedContext.getModules()) {
			addModule(treeView, boundedContextItem, module, domainModel, container);
		}

		boundedContext.getModules().addListener(new ListChangeListener<ModuleDefModel>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends ModuleDefModel> c) {
				if (c.next()) {
					if (c.wasPermutated() || c.wasReplaced())
						return;
					for (ModuleDefModel module : c.getAddedSubList()) {
						TreeItem<BaseDefModel> item = addModule(treeView, boundedContextItem, module, domainModel,
								container);
						container.showEditor(module, item);
					}
					for (ModuleDefModel module : c.getRemoved()) {
						removeModule(boundedContextItem, module);
						container.hideEditor(module);
					}
				}
			}

		});
		return boundedContextItem;
	}

	private TreeItem<BaseDefModel> addModule(final TreeView<BaseDefModel> treeView,
			final TreeItem<BaseDefModel> boundedContextItem,
			final ModuleDefModel module, final DomainModelDefModel domainModel,
			final ITreeContainerController<?, BaseDefModel> container) {
		final TreeItem<BaseDefModel> moduleItem = addModel(treeView, boundedContextItem, module, container);

		final TreeItem<BaseDefModel> entityListItem = (domainModel.getEntities().stream()
				.anyMatch(e -> e.getModule() == module))
						? createEntityListItem(treeView, moduleItem)
						: null;
		if (entityListItem != null) {
			moduleItem.getChildren().add(entityListItem);
		}

		final TreeItem<BaseDefModel> valueObjectListItem = (domainModel.getValueObjects().stream()
				.anyMatch(e -> e.getModule() == module))
						? createValueObjectListItem(treeView, moduleItem)
						: null;
		if (valueObjectListItem != null) {
			moduleItem.getChildren().add(valueObjectListItem);
		}

		final TreeItem<BaseDefModel> enumListItem = (domainModel.getEnums().stream()
				.anyMatch(e -> e.getModule() == module))
						? createEnumListItem(treeView, moduleItem)
						: null;
		if (enumListItem != null) {
			moduleItem.getChildren().add(enumListItem);
		}

		domainModel.getEntities().stream().filter(e -> e.getModule() == module).forEach(e -> {
			addObjectModel(treeView, entityListItem, e, container);
		});

		domainModel.getValueObjects().stream().filter(e -> e.getModule() == module).forEach(e -> {
			addObjectModel(treeView, valueObjectListItem, e, container);
		});

		domainModel.getEnums().stream().filter(e -> e.getModule() == module).forEach(e -> {
			addObjectModel(treeView, enumListItem, e, container);
		});
		return moduleItem;
	}

	private TreeItem<BaseDefModel> findOrCreateModuleItemList(TreeView<BaseDefModel> treeView,
			TreeItem<BaseDefModel> moduleItem, String tagName) {
		TreeItem<BaseDefModel> result = findModuleItemList(moduleItem, tagName);
		if (result == null)
			result = createModuleListItem(treeView, moduleItem, tagName);
		return result;
	}

	private TreeItem<BaseDefModel> findModuleItemList(TreeItem<BaseDefModel> moduleItem, String tagName) {
		int i = 0;
		while (i < moduleItem.getChildren().size() && moduleItem.getChildren().get(i).getValue().getTag() != tagName)
			i++;
		if (i < moduleItem.getChildren().size())
			return moduleItem.getChildren().get(i);
		return null;

	}

	private TreeItem<BaseDefModel> createModuleListItem(TreeView<BaseDefModel> treeView,
			TreeItem<BaseDefModel> moduleItem,
			String tagName) {
		TreeItem<BaseDefModel> result = null;
		if (tagName == ENTITY) {
			result = createEntityListItem(treeView, moduleItem);
			moduleItem.getChildren().add(0, result);
		} else if (tagName == VALUE_OBJECT) {
			result = createValueObjectListItem(treeView, moduleItem);
			if (moduleItem.getChildren().size() == 0 || moduleItem.getChildren().get(0).getValue().getTag() != ENTITY)
				moduleItem.getChildren().add(0, result);
			else
				moduleItem.getChildren().add(1, result);
		} else if (tagName == ENUM) {
			result = createEnumListItem(treeView, moduleItem);
			moduleItem.getChildren().add(result);
		}

		return result;
	}

	private TreeItem<BaseDefModel> createEnumListItem(TreeView<BaseDefModel> treeView,
			TreeItem<BaseDefModel> moduleItem) {
		return createObjectModelListItem(treeView, moduleItem, "enums", "file_extension_list_stack", ENUM);
	}

	private TreeItem<BaseDefModel> createValueObjectListItem(TreeView<BaseDefModel> treeView,
			TreeItem<BaseDefModel> moduleItem) {
		return createObjectModelListItem(treeView, moduleItem, "valueObjects", "file_extension_qxd_stack",
				VALUE_OBJECT);
	}

	private TreeItem<BaseDefModel> createEntityListItem(TreeView<BaseDefModel> treeView,
			TreeItem<BaseDefModel> moduleItem) {
		return createObjectModelListItem(treeView, moduleItem, "entities", "file_extension_xpi_stack", ENTITY);
	}

	private TreeItem<BaseDefModel> createObjectModelListItem(TreeView<BaseDefModel> treeView,
			TreeItem<BaseDefModel> moduleItem, String nameKey, String imageName, String tag) {
		BaseDefModel list = new BaseDefModel();
		list.setName(resources.getString(nameKey));
		list.setTag(tag);
		TreeItem<BaseDefModel> listItem = new CustomTreeItem<BaseDefModel>(treeView);
		listItem.setValue(list);
		listItem.setExpanded(true);
		listItem.setGraphic(new ImageView("images/"
				+ imageName
				+ ".png"));
		list.setOwner(moduleItem.getValue());
		return listItem;
	}

	protected TreeItem<BaseDefModel> findModuleItem(TreeView<BaseDefModel> treeView, ModuleDefModel module) {
		if (module != null) {
			BoundedContextDefModel boundedContext = (BoundedContextDefModel) module.getOwner();
			if (boundedContext != null) {
				TreeItem<BaseDefModel> boundedContextItem = findItem(treeView.getRoot().getChildren().get(0),
						boundedContext);
				if (boundedContextItem != null) {
					return findItem(boundedContextItem, module);

				}
			}
		}
		return null;
	}

	private String getTagFromModel(SimpleDomainObjectDefModel objectModel) {
		if (objectModel instanceof EntityDefModel)
			return ENTITY;
		else if (objectModel instanceof ValueObjectDefModel)
			return VALUE_OBJECT;
		else
			return ENUM;
	}

	private TreeItem<BaseDefModel> addObjectModel(TreeView<BaseDefModel> treeView, TreeItem<BaseDefModel> containerItem,
			SimpleDomainObjectDefModel objectModel, ITreeContainerController<?, BaseDefModel> container) {
		TreeItem<BaseDefModel> item = addModel(treeView, containerItem, objectModel, container);
		objectModel.moduleProperty().addListener(new ChangeListener<ModuleDefModel>() {
			@Override
			public void changed(ObservableValue<? extends ModuleDefModel> observable, ModuleDefModel oldValue,
					ModuleDefModel newValue) {
				if (oldValue != newValue) {
					TreeItem<BaseDefModel> moduleItem = findModuleItem(treeView, oldValue);
					if (moduleItem != null)
						removeObjectModel(moduleItem, objectModel);
					moduleItem = findModuleItem(treeView, newValue);
					String tag = getTagFromModel(objectModel);
					TreeItem<BaseDefModel> moduleListItem = findOrCreateModuleItemList(treeView, moduleItem, tag);
					if (moduleItem != null)
						addModel(treeView, moduleListItem, objectModel, container);
				}
			}
		});
		return item;
	}

	protected void removeObjectModel(TreeItem<BaseDefModel> moduleItem, SimpleDomainObjectDefModel objectModel) {
		removeModel(moduleItem, objectModel);
	}

	private void removeModule(TreeItem<BaseDefModel> boundedContextItem, ModuleDefModel module) {
		removeModel(boundedContextItem, module);
	}

	private void addApplications(TreeView<BaseDefModel> treeView, TreeItem<BaseDefModel> root,
			DomainModelDefModel domainModel,
			ITreeContainerController<?, BaseDefModel> container) {
		BaseDefModel applicationList = new BaseDefModel();
		applicationList.setName(resources.getString("applications"));
		CustomTreeItem<BaseDefModel> applicationListItem = new CustomTreeItem<BaseDefModel>(treeView);
		applicationListItem.setValue(applicationList);
		applicationListItem.setExpanded(true);
		applicationListItem.setGraphic(new ImageView("images/folder_application.png"));
		root.getChildren().add(applicationListItem);

		for (ApplicationDefModel application : domainModel.getApplications()) {
			addApplication(treeView, applicationListItem, application, container);
		}
		domainModel.getApplications().addListener(new ListChangeListener<ApplicationDefModel>() {

			@Override
			public void onChanged(Change<? extends ApplicationDefModel> c) {
				if (c.next()) {
					if (c.wasPermutated() || c.wasReplaced())
						return;
					for (ApplicationDefModel application : c.getAddedSubList()) {
						TreeItem<BaseDefModel> item = addApplication(treeView, applicationListItem, application,
								container);
						container.showEditor(application, item);
					}
					for (ApplicationDefModel application : c.getRemoved()) {
						removeApplication(applicationListItem, application);
						container.hideEditor(application);
					}
				}
			}
		});
	}

	protected TreeItem<BaseDefModel> addApplication(TreeView<BaseDefModel> treeView,
			TreeItem<BaseDefModel> applicationListItem, ApplicationDefModel application,
			ITreeContainerController<?, BaseDefModel> container) {
		return addModel(treeView, applicationListItem, application, container);
	}

	protected void removeApplication(TreeItem<BaseDefModel> applicationListItem, ApplicationDefModel application) {
		removeModel(applicationListItem, application);
	}

	@Override
	protected String getBundleName() {
		return "DomainModelTreeBundle";
	}
}
