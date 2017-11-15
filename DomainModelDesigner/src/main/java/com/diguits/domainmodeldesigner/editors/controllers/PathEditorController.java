package com.diguits.domainmodeldesigner.editors.controllers;

import java.util.Optional;

import com.diguits.domainmodeldesigner.editors.views.PathEditorView;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainObjectDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.FieldDefModel;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.google.inject.Inject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PathEditorController extends ViewControllerBase<PathEditorView> {

	private DomainObjectDefModel domainObject;

	private StringProperty path;

	boolean settingPathFromTree = false;

	@Inject
	public PathEditorController(PathEditorView view) {
		super(view);
	}

	@Override
	public void initialize() {
		super.initialize();
		PathTreeItem rootNode = new PathTreeItem(null);
		TreeView<FieldDefModel> pathTree = getView().getPathTree();
		pathTree.setRoot(rootNode);
		pathTree.setShowRoot(false);
		pathTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<FieldDefModel>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<FieldDefModel>> observable,
					TreeItem<FieldDefModel> oldValue, TreeItem<FieldDefModel> newValue) {
				settingPathFromTree = true;
				try {
					setPath(getPathFromTreeItem(newValue));
				} catch (Exception e) {
					settingPathFromTree = false;
				}
			}
		});
		getView().getPathEditor().textProperty().bind(pathProperty());
		selectTreeItem();
		path.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!settingPathFromTree)
					selectTreeItem();
			}
		});
		rootNode.setExpanded(true);
	}

	private void selectTreeItem() {
		if (!getPath().isEmpty()) {
			TreeView<FieldDefModel> pathTree = getView().getPathTree();
			String path = getPath();
			String[] fields = path.split("[.]");
			TreeItem<FieldDefModel> item = pathTree.getRoot();
			for (String field : fields) {
				Optional<TreeItem<FieldDefModel>> first = item.getChildren().stream()
						.filter(i -> i.getValue().getName().equals(field)).findFirst();
				if (first.isPresent()) {
					item = first.get();
					item.setExpanded(true);
				} else
					break;
			}
			if (item != pathTree.getRoot()) {
				pathTree.getSelectionModel().select(item);
				pathTree.scrollTo(pathTree.getRow(item));
			}
		}
	}

	private String getPathFromTreeItem(TreeItem<FieldDefModel> item) {
		if (item != null && item.getParent() != null) {
			StringBuilder sb = new StringBuilder();
			sb.insert(0, item.getValue().getName());
			item = item.getParent();
			while (item.getParent() != null) {
				sb.insert(0, ".");
				sb.insert(0, item.getValue().getName());
				item = item.getParent();
			}
			return sb.toString();
		}
		return "";
	}

	public String getPath() {
		if (path != null)
			return path.get();
		return "";
	}

	public void setPath(String path) {
		if (this.path != null || path == null || !path.equals("")) {
			pathProperty().set(path);
		}
	}

	public StringProperty pathProperty() {
		if (path == null) {
			path = new SimpleStringProperty(this, "path", "");
		}
		return path;
	}

	protected DomainObjectDefModel getDomainObject() {
		return domainObject;
	}

	protected void setDomainObject(DomainObjectDefModel domainObject) {
		this.domainObject = domainObject;
	}

	public class PathTreeItem extends TreeItem<FieldDefModel> {
		private boolean childrenLoaded = false;
		private boolean leafPropertyComputed = false;
		private boolean leafNode = false;

		public PathTreeItem(FieldDefModel field) {
			super(field);
			if (field != null) {
				ImageView icon = null;
				if (!isDomainObjectField(field)) {
					icon = getFolderIcon("hand_property.png");
				} else {
					icon = getFolderIcon("page.png");
				}
				this.setGraphic(icon);
			}
		}

		@Override
		public ObservableList<TreeItem<FieldDefModel>> getChildren() {
			if (!childrenLoaded) {
				childrenLoaded = true;
				populateChildren(this);
			}
			return super.getChildren();
		}

		@Override
		public boolean isLeaf() {
			if (!leafPropertyComputed) {
				leafPropertyComputed = true;
				FieldDefModel field = this.getValue();
				leafNode = getDomainObject() ==null|| ((field == null || !isDomainObjectField(field))&&(getParent()!=null));
			}
			return leafNode;
		}

		private boolean isDomainObjectField(FieldDefModel field) {
			return field.getRelationshipData() != null && field.getRelationshipData().getRelationshipPart() != null
					&& field.getRelationshipData().getRelationshipPart().getToRelationshipPart().getDomainObject() != null;
		}

		private void populateChildren(TreeItem<FieldDefModel> item) {
			item.getChildren().clear();
			DomainObjectDefModel itemDomainObject = null;
			if (item.getParent() == null) {
				// Add root directories
				itemDomainObject = domainObject;

			} else {
				FieldDefModel field = item.getValue();
				// Populate sub-directories and files
				if (isDomainObjectField(field)) {
					itemDomainObject = field.getRelationshipData().getRelationshipPart().getToRelationshipPart().getDomainObject();
				}
			}
			if (itemDomainObject != null)
				for (FieldDefModel field : itemDomainObject.getFields()) {
					item.getChildren().add(new PathTreeItem(field));
				}
		}

		private ImageView getFolderIcon(String fileName) {
			ImageView imgView = null;
			try {
				String imagePath = "/images/" + fileName;
				Image img = new Image(imagePath);
				imgView = new ImageView(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return imgView;
		}
	}

	public String editPath(DomainObjectDefModel domainObject, String path) {
		if (domainObject != null) {
			setDomainObject(domainObject);
			setPath(path);
			if (DialogHelper.showOKCancelDialog(this, new SimpleStringProperty("Select path")))
				return getPath();
		}
		return null;
	}

	@Override
	protected String getBundleName() {
		return "EditorsBundle";
	}
}
