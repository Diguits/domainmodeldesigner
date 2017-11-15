package com.diguits.domainmodeldesigner.domainmodelstructure.views;

import com.diguits.domainmodeldesigner.domainmodelstructure.models.DomainModelStructureItem;
import com.diguits.javafx.container.views.TreeContainerView;

import javafx.scene.control.TreeItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class DomainModelStructureTreeView extends TreeContainerView<DomainModelStructureItem>{

	@Override
	public void expand() {
		TreeItem<DomainModelStructureItem> root = treeView.getRoot();
		if(root!=null){
			for (TreeItem<DomainModelStructureItem> def : root.getChildren()) {
				def.setExpanded(true);
				for (TreeItem<DomainModelStructureItem> cont : def.getChildren()) {
					cont.setExpanded(true);
				}
			}
		}
	}

	public void copySelectedName() {
		if (treeView.getSelectionModel().getSelectedItem() != null) {
			final Clipboard clipboard = Clipboard.getSystemClipboard();
			final ClipboardContent content = new ClipboardContent();
			content.putString(treeView.getSelectionModel().getSelectedItem().getValue().getName());
			clipboard.setContent(content);
		}
	}
}
