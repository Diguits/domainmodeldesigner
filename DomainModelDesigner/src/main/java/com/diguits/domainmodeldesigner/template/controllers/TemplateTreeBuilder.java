package com.diguits.domainmodeldesigner.template.controllers;

import com.diguits.domainmodeldesigner.template.models.TemplateDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateGroupDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectDefModel;
import com.diguits.domainmodeldesigner.template.models.TemplateProjectItemDefModel;
import com.diguits.javafx.container.controllers.CustomTreeItem;
import com.diguits.javafx.container.controllers.ITreeContainerController;
import com.diguits.javafx.container.controllers.TreeBuilder;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

public class TemplateTreeBuilder
		extends TreeBuilder<ObservableList<TemplateProjectDefModel>, TemplateProjectItemDefModel> {

	public void fillTreeForEditor(ObservableList<TemplateProjectDefModel> projects,
			TreeView<TemplateProjectItemDefModel> treeView){
		CustomTreeItem<TemplateProjectItemDefModel> root = createAndConfigureRoot(treeView);
		for (TemplateProjectDefModel templateProject : projects) {
			TreeItem<TemplateProjectItemDefModel> projectItem = addModel(treeView, root, templateProject, TemplateTreeDecorator.getModelImage(templateProject));
			for (TemplateGroupDefModel templateGroup : templateProject.getGroups()) {
				TreeItem<TemplateProjectItemDefModel> groupItem = addModel(treeView, projectItem, templateGroup, TemplateTreeDecorator.getModelImage(templateGroup));
				for (TemplateDefModel template : templateGroup.getTemplates()) {
					addModel(treeView, groupItem, template, TemplateTreeDecorator.getModelImage(template));
				}
			}
		}
		treeView.setRoot(root);
	}

	@Override
	public void fillTree(ObservableList<TemplateProjectDefModel> projects,
			TreeView<TemplateProjectItemDefModel> treeView,
			ITreeContainerController<?, TemplateProjectItemDefModel> container) {
		if (projects != null && treeView != null) {
			projects.addListener(new ListChangeListener<TemplateProjectDefModel>() {

				@Override
				public void onChanged(Change<? extends TemplateProjectDefModel> c) {
					if (c.next()) {
						if(c.wasPermutated()||c.wasReplaced())
							return;
						for (TemplateProjectDefModel project : c.getAddedSubList()) {
							TreeItem<TemplateProjectItemDefModel> item = addTemplateProject(treeView, project, container);
							container.showEditor(project, item);
						}
						for (TemplateProjectDefModel project : c.getRemoved()) {
							removeTemplateProject(treeView, project);
							container.hideEditor(project);
						}
					}
				}
			});
			CustomTreeItem<TemplateProjectItemDefModel> root = createAndConfigureRoot(treeView);
			for (TemplateProjectDefModel templateProject : projects) {
				addTemplateProject(treeView, root, templateProject, container);
			}
			treeView.setRoot(root);
		}
	}

	private CustomTreeItem<TemplateProjectItemDefModel> createAndConfigureRoot(
			TreeView<TemplateProjectItemDefModel> treeView) {
		CustomTreeItem<TemplateProjectItemDefModel> root = new CustomTreeItem<TemplateProjectItemDefModel>(treeView);
		TemplateProjectItemDefModel rootModel = new TemplateProjectItemDefModel();
		rootModel.setName(resources.getString("template_projects"));
		root.setValue(rootModel);
		root.setExpanded(true);
		root.setGraphic(new ImageView("images/folder_clipboard.png"));
		return root;
	}

	private void removeTemplateProject(TreeView<TemplateProjectItemDefModel> treeView,
			TemplateProjectDefModel project) {
		removeModel(treeView.getRoot(), project);
	}

	private TreeItem<TemplateProjectItemDefModel> addTemplateProject(TreeView<TemplateProjectItemDefModel> treeView,
			TemplateProjectDefModel templateProject,
			ITreeContainerController<?, TemplateProjectItemDefModel> container) {
		return addTemplateProject(treeView, treeView.getRoot(), templateProject, container);
	}

	private TreeItem<TemplateProjectItemDefModel> addTemplateProject(TreeView<TemplateProjectItemDefModel> treeView,
			TreeItem<TemplateProjectItemDefModel> root, TemplateProjectDefModel templateProject,
			ITreeContainerController<?, TemplateProjectItemDefModel> container) {
		TreeItem<TemplateProjectItemDefModel> projectItem = addModel(treeView, root, templateProject, container);
		for (TemplateGroupDefModel templateGroup : templateProject.getGroups()) {
			addTemplateGroup(treeView, projectItem, templateGroup, container);
		}
		templateProject.getGroups().addListener(new ListChangeListener<TemplateGroupDefModel>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TemplateGroupDefModel> c) {
				if (c.next()) {
					if(c.wasPermutated()||c.wasReplaced())
						return;
					for (TemplateGroupDefModel group : c.getAddedSubList()) {
						TreeItem<TemplateProjectItemDefModel> item = addTemplateGroup(treeView, group, templateProject, container);
						container.showEditor(group, item);
					}
					for (TemplateGroupDefModel group : c.getRemoved()) {
						removeTemplateGroup(treeView, group, templateProject);
						container.hideEditor(group);
					}
				}
			}

		});
		return projectItem;
	}

	private void removeTemplateGroup(TreeView<TemplateProjectItemDefModel> treeView, TemplateGroupDefModel group,
			TemplateProjectDefModel templateProject) {
		TreeItem<TemplateProjectItemDefModel> projectItem = findItem(treeView.getRoot(), templateProject);
		removeModel(projectItem, group);
	}

	private TreeItem<TemplateProjectItemDefModel> addTemplateGroup(TreeView<TemplateProjectItemDefModel> treeView, TemplateGroupDefModel group,
			TemplateProjectDefModel templateProject,
			ITreeContainerController<?, TemplateProjectItemDefModel> container) {
		TreeItem<TemplateProjectItemDefModel> projectItem = findItem(treeView.getRoot(), templateProject);
		if (projectItem != null) {
			return addTemplateGroup(treeView, projectItem, group, container);
		}
		return null;
	}

	private TreeItem<TemplateProjectItemDefModel> addTemplateGroup(TreeView<TemplateProjectItemDefModel> treeView,
			TreeItem<TemplateProjectItemDefModel> projectItem, TemplateGroupDefModel templateGroup,
			ITreeContainerController<?, TemplateProjectItemDefModel> container) {
		TreeItem<TemplateProjectItemDefModel> groupItem = addModel(treeView, projectItem, templateGroup, container);
		for (TemplateDefModel template : templateGroup.getTemplates()) {
			addTemplate(treeView, template, container, groupItem);
		}
		templateGroup.getTemplates().addListener(new ListChangeListener<TemplateDefModel>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TemplateDefModel> c) {
				if (c.next()) {
					if(c.wasPermutated()||c.wasReplaced())
						return;
					for (TemplateDefModel template : c.getAddedSubList()) {
						TreeItem<TemplateProjectItemDefModel> item = addTemplate(treeView, template, templateGroup, projectItem, container);
						container.showEditor(template, item);
					}
					for (TemplateDefModel template : c.getRemoved()) {
						removeTemplate(treeView, template, templateGroup, projectItem);
						container.hideEditor(template);
					}
				}
			}

		});
		return groupItem;
	}

	protected void removeTemplate(TreeView<TemplateProjectItemDefModel> treeView, TemplateDefModel template,
			TemplateGroupDefModel templateGroup, TreeItem<TemplateProjectItemDefModel> projectItem) {
		TreeItem<TemplateProjectItemDefModel> groupItem = findItem(projectItem, templateGroup);
		removeModel(groupItem, template);
	}

	protected TreeItem<TemplateProjectItemDefModel> addTemplate(TreeView<TemplateProjectItemDefModel> treeView, TemplateDefModel template,
			TemplateGroupDefModel templateGroup, TreeItem<TemplateProjectItemDefModel> projectItem,
			ITreeContainerController<?, TemplateProjectItemDefModel> container) {
		TreeItem<TemplateProjectItemDefModel> groupItem = findItem(projectItem, templateGroup);
		if (groupItem != null) {
			return addTemplate(treeView, template, container, groupItem);
		}
		return null;
	}

	private TreeItem<TemplateProjectItemDefModel> addTemplate(TreeView<TemplateProjectItemDefModel> treeView,
			TemplateDefModel template, ITreeContainerController<?, TemplateProjectItemDefModel> container,
			TreeItem<TemplateProjectItemDefModel> groupItem) {
		TreeItem<TemplateProjectItemDefModel> item = addModel(treeView, groupItem, template, container);
		template.activeProperty().addListener((v, o, n)->{
			ImageView imageForModel = container.getDecorator().getImageForModel(template);
			item.setGraphic(imageForModel);
		});
		return item;
	}

	@Override
	protected String getBundleName() {
		return "TemplateTreeBundle";
	}
}
