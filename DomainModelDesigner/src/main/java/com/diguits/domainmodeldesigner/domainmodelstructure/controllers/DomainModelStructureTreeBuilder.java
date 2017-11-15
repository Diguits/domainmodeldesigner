package com.diguits.domainmodeldesigner.domainmodelstructure.controllers;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.diguits.domainmodeldesigner.domainmodelstructure.models.ClassStructureItem;
import com.diguits.domainmodeldesigner.domainmodelstructure.models.MethodStructureItem;
import com.diguits.domainmodeldesigner.domainmodelstructure.models.PropertyStructureItem;
import com.diguits.domainmodeldesigner.domainmodelstructure.models.DomainModelStructureItem;
import com.diguits.javafx.container.controllers.CustomTreeItem;
import com.diguits.javafx.container.controllers.ITreeContainerController;
import com.diguits.javafx.container.controllers.TreeBuilder;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;

public class DomainModelStructureTreeBuilder extends TreeBuilder<Class<?>[], DomainModelStructureItem> {

	@Override
	public void fillTree(Class<?>[] model, TreeView<DomainModelStructureItem> treeView,
			ITreeContainerController<?, DomainModelStructureItem> container) {

		CustomTreeItem<DomainModelStructureItem> root = new CustomTreeItem<DomainModelStructureItem>(treeView, null);
		treeView.setRoot(root);
		for (Class<?> clazz : model) {
			root.getChildren()
					.add(new DomainModelStructureTreeItem(treeView, new ClassStructureItem(clazz.getSimpleName(), "", null, clazz)));
		}
		treeView.setShowRoot(false);

		treeView.setOnDragDetected(e -> {
			TreeItem<DomainModelStructureItem> selectedItem = treeView.getSelectionModel().getSelectedItem();
			if (selectedItem == null || (selectedItem.getValue().getClass() == DomainModelStructureItem.class)) {
				e.consume();
				return;
			}
			// Initiate a drag-and-drop gesture
			Dragboard dragboard = treeView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			// Add the source text to the Dragboard
			ClipboardContent content = new ClipboardContent();
			content.putString(selectedItem.getValue().getCode());
			dragboard.setContent(content);
			e.consume();
		});

		treeView.setOnKeyPressed(e -> {
			if (e.isControlDown() && e.getCode() == KeyCode.C) {
				((DomainModelStructureTreeController)container).copySelectedName();
			}
		});
	}

	@Override
	protected String getBundleName() {
		return "DomainModelStructureTreeBundle";
	}

	public class DomainModelStructureTreeItem extends CustomTreeItem<DomainModelStructureItem> {
		private boolean childrenLoaded = false;
		private boolean leafPropertyComputed = false;
		private boolean leafNode = false;

		public DomainModelStructureTreeItem(TreeView<DomainModelStructureItem> treeView, DomainModelStructureItem structureItem) {
			super(treeView, structureItem);

			if (structureItem instanceof ClassStructureItem)
				if (!((ClassStructureItem) structureItem).isList())
					if (((ClassStructureItem) structureItem).isPrimitive())
						this.setGraphic(getImage("blue_circle"));
					else
						this.setGraphic(getImage("class"));
				else
					this.setGraphic(getImage("class_list"));

			else if (structureItem instanceof MethodStructureItem) {
				if (((MethodStructureItem) structureItem).getIsGetter())
					this.setGraphic(getImage("property_get"));
				else if (((MethodStructureItem) structureItem).getIsSetter())
					this.setGraphic(getImage("property_set"));
				else
					this.setGraphic(getImage("gear_in"));
			}
		}

		@Override
		public ObservableList<TreeItem<DomainModelStructureItem>> getChildren() {
			if (!childrenLoaded) {
				childrenLoaded = true;
				populateChildren(this);
			}
			return super.getChildren();
		}

		private void populateChildren(DomainModelStructureTreeItem item) {
			item.getChildren().clear();
			DomainModelStructureItem structureItem = item.getValue();
			// Class
			if (structureItem instanceof ClassStructureItem
					&& !((ClassStructureItem) structureItem).getStructureClass().isPrimitive()) {
				ClassStructureItem classStructureItem = ((ClassStructureItem) structureItem);
				Class<?> clazz = classStructureItem.getStructureClass();

				// Enum Values
				if (classStructureItem.isEnum()) {
					DomainModelStructureItem enumValuesItem = new DomainModelStructureItem(resources.getString("values"), null, structureItem);
					CustomTreeItem<DomainModelStructureItem> enumValuesTreeItem = new CustomTreeItem<DomainModelStructureItem>(getTreeView(),
							enumValuesItem);
					enumValuesTreeItem.setGraphic(getImage("text_list_bullets"));
					for (int i = 0; i < clazz.getEnumConstants().length; i++) {
						CustomTreeItem<DomainModelStructureItem> enumValueTreeItem = new CustomTreeItem<DomainModelStructureItem>(getTreeView(),
								new DomainModelStructureItem(clazz.getEnumConstants()[i].toString(), null, enumValuesItem));
						enumValueTreeItem.setGraphic(getImage("bullet_blue"));
						enumValuesTreeItem.getChildren().add(enumValueTreeItem);
					}
					item.getChildren().add(enumValuesTreeItem);
				}
				// Containers
				DomainModelStructureItem propertiesItem = new DomainModelStructureItem(resources.getString("properties"), null, structureItem);
				CustomTreeItem<DomainModelStructureItem> propertiesTreeItem = new CustomTreeItem<DomainModelStructureItem>(getTreeView(),
						propertiesItem);
				propertiesTreeItem.setGraphic(getImage("property_folder"));
				// propertiesTreeItem.setExpanded(true);
				DomainModelStructureItem methodsItem = new DomainModelStructureItem(resources.getString("methods"), null, structureItem);
				CustomTreeItem<DomainModelStructureItem> methodsTreeItem = new CustomTreeItem<DomainModelStructureItem>(getTreeView(),
						methodsItem);
				methodsTreeItem.setGraphic(getImage("gear_folder"));

				// Methods sorting
				Method[] methods = clazz.getMethods();
				List<Method> methodList = new ArrayList<Method>();
				Collections.addAll(methodList, methods);
				methodList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

				for (Method method : methodList) {
					String methodName = method.getName();
					// Getters
					if (MethodStructureItem.getIsGetter(method)) {
						String propertyName = methodName.startsWith("get") ? methodName.substring(3)
								: methodName.substring(2);
						// Find Setter method
						Optional<Method> setter = methodList.stream()
								.filter(m -> m.getName().equals("set" + propertyName)).findAny();
						Method setterMethod = null;
						if (setter.isPresent()) {
							setterMethod = setter.get();
							if (setterMethod.getParameterCount() != 1
									|| setterMethod.getParameters()[0].getType() != method.getReturnType())
								setterMethod = null;
						}
						// Property Container

						DomainModelStructureTreeItem propertyTreeItem = new DomainModelStructureTreeItem(
								getTreeView(), new PropertyStructureItem(propertyName, "", propertiesItem, method, setterMethod));
						propertyTreeItem.setGraphic(getImage("hand_property"));
						propertiesTreeItem.getChildren().add(propertyTreeItem);

					} else if (methodName.startsWith("set") && method.getParameterCount() == 1) {
					} else {
						DomainModelStructureTreeItem methodTreeItem = new DomainModelStructureTreeItem(
								getTreeView(), new MethodStructureItem(methodName, "", methodsItem, method));
						methodsTreeItem.getChildren().add(methodTreeItem);
					}
				}
				if (propertiesTreeItem.getChildren().size() > 0)
					item.getChildren().add(propertiesTreeItem);
				if (methodsTreeItem.getChildren().size() > 0)
					item.getChildren().add(methodsTreeItem);

			} else if ((structureItem instanceof PropertyStructureItem)) {
				Method method = ((PropertyStructureItem) structureItem).getMethod();
				// List
				Class<?> listGenericClass = null;
				if (method.getReturnType().getName().endsWith(".List")) {
					Type returnType = method.getGenericReturnType();
					if (returnType instanceof ParameterizedType) {
						ParameterizedType paramReturnType = (ParameterizedType) returnType;
						if (paramReturnType.getActualTypeArguments()[0] instanceof Class)
							listGenericClass = (Class<?>) paramReturnType.getActualTypeArguments()[0];
					}
				}
				// Property Type
				DomainModelStructureTreeItem propertyType = new DomainModelStructureTreeItem(getTreeView(),
						new ClassStructureItem(method.getReturnType().getSimpleName(), "", structureItem, method.getReturnType()));
				item.getChildren().add(propertyType);

				// If it is a list Generic Type
				if (listGenericClass != null) {
					DomainModelStructureTreeItem clazzTreeItem = new DomainModelStructureTreeItem(getTreeView(),
							new ClassStructureItem(listGenericClass.getSimpleName(), "", structureItem, listGenericClass));
					item.getChildren().add(clazzTreeItem);
					clazzTreeItem.setGraphic(getImage("class"));
				}
				// Getter method
				DomainModelStructureTreeItem getterMethodItem = new DomainModelStructureTreeItem(getTreeView(),
						new MethodStructureItem(method.getName(), "", structureItem, method));
				item.getChildren().add(getterMethodItem);

				// Setter method
				Method setterMethod = ((PropertyStructureItem) structureItem).getSetterMethod();
				if (setterMethod != null) {
					DomainModelStructureTreeItem clazzTreeItem = new DomainModelStructureTreeItem(getTreeView(),
							new MethodStructureItem(setterMethod.getName(), "", structureItem, setterMethod));
					item.getChildren().add(clazzTreeItem);
				}
			} else if ((structureItem instanceof MethodStructureItem)) {
				Method method = ((MethodStructureItem) structureItem).getMethod();
				DomainModelStructureTreeItem resultTreeItem = new DomainModelStructureTreeItem(getTreeView(),
						new ClassStructureItem(resources.getString("result"), "", structureItem, method.getReturnType()));

				DomainModelStructureItem paramsItem = new DomainModelStructureItem(resources.getString("params"), null, structureItem);
				CustomTreeItem<DomainModelStructureItem> paramsTreeItem = new CustomTreeItem<DomainModelStructureItem>(getTreeView(),
						paramsItem);
				paramsTreeItem.setGraphic(getImage("params"));
				item.getChildren().add(resultTreeItem);

				for (Parameter parameter : method.getParameters()) {
					paramsTreeItem.getChildren().add(new DomainModelStructureTreeItem(getTreeView(),
							new ClassStructureItem(parameter.getName(), "", paramsItem, parameter.getType())));
				}

				if (paramsTreeItem.getChildren().size() > 0)
					item.getChildren().add(paramsTreeItem);

			}
		}

		@Override
		public boolean isLeaf() {
			if (!leafPropertyComputed) {
				leafPropertyComputed = true;
				leafNode = true;
				DomainModelStructureItem structureItem = this.getValue();
				if (structureItem instanceof ClassStructureItem
						&& !((ClassStructureItem) structureItem).getStructureClass().isPrimitive())
					leafNode = false;
				if ((structureItem instanceof MethodStructureItem))
					leafNode = false;
				if ((structureItem instanceof PropertyStructureItem))
					leafNode = false;
			}
			return leafNode;
		}
	}

}
