package com.diguits.javafx.container.controllers;

import com.diguits.javafx.problem.controllers.Position;
import com.diguits.javafx.container.controllers.ContainerControllerBase.ContainerPos;
import com.diguits.javafx.container.views.MainView;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.diguits.javafx.controls.ClickManager;
import com.diguits.javafx.controls.CustomBreadCrumbButton;
import com.diguits.javafx.model.NamedModelBase;
import com.google.inject.Inject;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class MainController extends ViewControllerBase<MainView> {

	@Inject
	public MainController(MainView view) {
		super(view);
	}

	@Inject
	EditorsHoleController centerHoleController;

	@Inject
	HoleController leftHoleController;

	@Inject
	HoleController rightHoleController;

	@Inject
	HoleController bottomHoleController;

	@Inject
	ContainerManager containerManager;

	@Inject
	GlobalSelectionManager globalSelectionManager;

	public void initialize() {
		super.initialize();
		MainView view = getView();
		if (view != null) {
			view.putHole(centerHoleController.getView(), ContainerPos.CENTER);
			view.putHole(leftHoleController.getView(), ContainerPos.LEFT);
			view.putHole(rightHoleController.getView(), ContainerPos.RIGTH);
			view.putHole(bottomHoleController.getView(), ContainerPos.BOTTOM);
		}
		containerManager.loadContainers(getView(), leftHoleController, centerHoleController, rightHoleController, bottomHoleController);

		leftHoleController.selectFirst();
		centerHoleController.selectFirst();
		rightHoleController.selectFirst();
		bottomHoleController.selectFirst();
		configureBreadCrumBar();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void configureBreadCrumBar() {
		getView().getBreadCrumbBar().setCrumbFactory(i -> {
			final TreeItem item = i;
			CustomBreadCrumbButton result = new CustomBreadCrumbButton(item,
					item.getValue() != null ? item.getValue().toString() : "");
			if(item.getValue()!=null&&item.getValue() instanceof NamedModelBase)
				((NamedModelBase)item.getValue()).nameProperty().addListener((v, o, n)->{
					result.setText(n);
				});
			result.setUserData(item);
			if (item.getGraphic() != null && item.getGraphic() instanceof ImageView)
				result.setGraphic(new ImageView(((ImageView) item.getGraphic()).getImage()));
			ClickManager clickManager = new ClickManager();
			clickManager.setOnSingleClick(e -> {
				showChildrenMenu(result ,item);
			});
			clickManager.setOnDoubleClick(e -> {
				if (item instanceof CustomTreeItem<?>) {
					((CustomTreeItem) item).getTreeView().getSelectionModel().select(item);
					((CustomTreeItem) item).getTreeView().requestFocus();
				}
			});

			result.setOnMouseClicked(e -> {
				clickManager.click(e);
			});
			return result;
		});
		getView().getBreadCrumbBar().setOnCrumbAction(e -> {

		});
		globalSelectionManager.selectedTreeItemProperty().addListener((v, o, n) -> {
			getView().getBreadCrumbBar().setSelectedCrumb((TreeItem) n);
		});
	}

	@SuppressWarnings("unchecked")
	private void showChildrenMenu(CustomBreadCrumbButton breadCrumbButton, TreeItem<?> selectedCrumb) {
		selectedCrumb.getChildren();
		if (breadCrumbButton!=null && selectedCrumb instanceof CustomTreeItem) {
			ContextMenu childrenMenu = new ContextMenu();
			for (TreeItem<?> child : selectedCrumb.getChildren()) {
				MenuItem menuItem = new MenuItem(child.getValue().toString(), child.getGraphic());
				menuItem.setUserData(child);
				if (child.getGraphic() != null && child.getGraphic() instanceof ImageView)
					menuItem.setGraphic(new ImageView(((ImageView) child.getGraphic()).getImage()));
				menuItem.setOnAction(e -> {
					@SuppressWarnings("rawtypes")
					TreeItem crumbItem = (TreeItem<?>) ((MenuItem) e.getSource()).getUserData();
					if (crumbItem instanceof CustomTreeItem<?>)
						((CustomTreeItem<?>) crumbItem).getTreeView().getSelectionModel().select(crumbItem);
					((CustomTreeItem<?>) crumbItem).getTreeView().requestFocus();
				});
				childrenMenu.getItems().add(menuItem);
				((CustomTreeItem<?>) selectedCrumb).getTreeView().requestFocus();
			}
			childrenMenu.show(breadCrumbButton, Side.BOTTOM, 0, 0);
		}
	}

	/*private CustomBreadCrumbButton findBreadCrumbButton(TreeItem<?> selectedCrumb){
		for(Object button : ((BreadCrumbBarSkin<?>)getView().getBreadCrumbBar().getSkin()).getChildren()){
			if(button instanceof CustomBreadCrumbButton){
				if(((CustomBreadCrumbButton)button).getCrum()==selectedCrumb)
					return (CustomBreadCrumbButton)button;
			}
		}
		return null;
	}*/



	public boolean goToPosition(Position position) {
		if (leftHoleController.goToPosition(position))
			return true;
		else if (rightHoleController.goToPosition(position))
			return true;
		else if (bottomHoleController.goToPosition(position))
			return true;
		return false;
	}

}
