package com.diguits.domainmodeldesigner.template.views;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import com.diguits.javafx.controls.CodeEditor;
import com.diguits.javafx.views.ViewBase;

public final class TemplateApplyResultView  extends ViewBase<TabPane>{
	@Override
	protected TabPane buildView() {
		return nodeFactory.createTabPane();
	}

	public void clearTabs() {
		getNodeView().getTabs().clear();
	}

	public void addTab(String title, String outputText) {
		Tab outputTap = new Tab();
		outputTap.setClosable(false);
		outputTap.setText(title);
		getNodeView().getTabs().add(outputTap);

		CodeEditor codeCodeEditor = new CodeEditor(outputText, true);
		AnchorPane container = new AnchorPane();
		container.setPrefWidth(800);
		container.getChildren().add(codeCodeEditor);
		nodeFactory.fitToAnchorPane(codeCodeEditor);
		outputTap.setContent(container);
	}

	public void selectFirstTab() {
		getNodeView().getSelectionModel().select(0);
	}


}
