package com.diguits.domainmodeldesigner.domainmodel.views;

import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.javafx.container.views.TreeContainerView;

import javafx.scene.control.TreeItem;

public class DomainModelTreeView extends TreeContainerView<BaseDefModel>{

	@Override
	public void collapse() {
		TreeItem<BaseDefModel> root = treeView.getRoot();
		if(root!=null){
			for (TreeItem<BaseDefModel> item : root.getChildren()) {
				for (TreeItem<BaseDefModel> subitem : item.getChildren()) {
					subitem.setExpanded(false);
				}
			}
		}
	}
}
