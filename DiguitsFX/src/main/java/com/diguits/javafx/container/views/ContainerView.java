package com.diguits.javafx.container.views;

import java.util.List;

import com.diguits.javafx.views.ModelView;

import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public abstract class ContainerView<TModel> extends ModelView<BorderPane, TModel>
		implements IContainerView<BorderPane, TModel> {

	protected ToolBar toolBar;
	protected List<Node> toolBarOptions;
	protected boolean useToolBar;
	protected BorderPane borderPane;

	@Override
	protected BorderPane buildView() {
		borderPane = new BorderPane();
		;
		updateToolBar();
		borderPane.setStyle("-fx-border-style: none;");
		borderPane.setCenter(buildContentView());
		afterContentViewBuilt();
		return borderPane;
	}

	private void updateToolBar() {
		if (getUseToolBar() && toolBarOptions != null && toolBarOptions.size() > 0) {
			if (toolBar == null) {
				toolBar = new ToolBar();
				toolBar.getItems().addAll(toolBarOptions);
			}

			borderPane.setTop(toolBar);
		} else
			borderPane.setTop(null);

	}

	protected void afterContentViewBuilt() {

	}

	public void setToolBarOptions(List<Node> toolBarOptions) {
		this.toolBarOptions = toolBarOptions;
		updateToolBar();
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public boolean getUseToolBar() {
		return useToolBar;
	}

	public void setUseToolBar(boolean useToolBar) {
		this.useToolBar = useToolBar;
	}

	protected abstract Node buildContentView();
}
