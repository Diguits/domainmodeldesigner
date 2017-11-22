package com.diguits.javafx.navigation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.diguits.javafx.container.controllers.EditorsHoleController;
import com.diguits.javafx.controllers.DialogHelper;
import com.diguits.javafx.controllers.ViewControllerBase;
import com.diguits.javafx.model.NamedModelBase;
import com.diguits.javafx.navigation.views.ModelSelectorNavigationView;
import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.stage.StageStyle;

public abstract class ModelSelectorNavigationController<TView extends ModelSelectorNavigationView>
		extends ViewControllerBase<TView> {

	@Inject
	EditorsHoleController editors;

	@Inject
	public ModelSelectorNavigationController(TView view) {
		super(view);
	}

	static public class Position {

		public Position() {
			super();
		}

		public Position(int begin, int end) {
			this();
			this.begin = begin;
			this.end = end;
		}

		int begin;
		int end;

		public int getBegin() {
			return begin;
		}

		public void setBegin(int begin) {
			this.begin = begin;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}
	}

	static public class ModelPositions implements Comparable<ModelPositions> {
		private List<Position> positions;
		private NamedModelBase model;

		public ModelPositions(List<Position> positions, NamedModelBase model) {
			super();
			this.positions = positions;
			this.model = model;
		}

		public List<Position> getPositions() {
			return positions;
		}

		public void setPositions(List<Position> positions) {
			this.positions = positions;
		}

		public NamedModelBase getModel() {
			return model;
		}

		public void setModel(NamedModelBase model) {
			this.model = model;
		}

		@Override
		public int compareTo(ModelPositions o) {
			if (((ModelPositions) o).getModel().getName() == null || ((ModelPositions) o).getModel().getName() == null)
				return 1;
			else
				return getModel().getName().compareTo(((ModelPositions) o).getModel().getName());
		}

	}

	private List<Position> match(String name, String filter) {
		List<Position> result = new ArrayList<Position>();
		if (filter == null || filter.isEmpty())
			return result;
		if (name != null && !name.isEmpty()) {
			int nameLength = name.length();
			int filterLength = filter.length();
			int i = 0;
			int j = 0;
			boolean looklower = false;
			Position position = null;
			while (i < nameLength && j < filterLength && nameLength - i >= filterLength - j) {
				if (name.charAt(i) == Character.toUpperCase(filter.charAt(j))) {
					looklower = true;
					if (position == null) {
						position = new Position(i, i+1);
					} else
						position.setEnd(i+1);
					j++;
				} else {
					if (looklower) {
						if (name.charAt(i) == Character.toLowerCase(filter.charAt(j))) {
							position.setEnd(i+1);
							j++;
						} else {
							looklower = false;
							if (position != null)
								result.add(position);
							position = null;
						}

					} else {
						if (position != null)
							result.add(position);
						position = null;
					}
				}
				i++;
			}
			if (position != null)
				result.add(position);
			if (j >= filter.length())
				return result;
		}
		return null;
	}

	@Override
	public void initialize() {
		super.initialize();

		getView().getTxfFilter().textProperty().addListener((v, o, n) -> {

			ObservableList<ModelPositions> positionModel = FXCollections.observableArrayList();
			if (n != null && !n.isEmpty()) {
				for (NamedModelBase model : getModels()) {
					List<Position> positions = match(model.getName(), n);
					if (positions != null)
						positionModel.add(new ModelPositions(positions, model));
				}
				setModelPositionsToView(positionModel.stream());

			} else {
				setModelsToView(getModels().stream());
			}
		});
	}

	public void show() {
		setModelsToView(getModels().stream());
		getView().getTxfFilter().setText("");
		if (DialogHelper.showNoButtonsDialog(this, resources.getString("navigate_to"), 400, 600,
				StageStyle.UNDECORATED)) {
			ModelPositions selectedModel = getView().getSelectedModel();
			if (selectedModel != null)
				editors.showEditor(selectedModel.getModel(), getTreeItem(selectedModel.getModel()));
		}
	}

	protected void setModelsToView(Stream<NamedModelBase> stream) {
		ObservableList<ModelPositions> positionModel = FXCollections.observableArrayList();
		stream.limit(40).forEach(m -> {
			positionModel.add(new ModelPositions(null, m));
		});
		getView().setModels(positionModel.sorted());
	}

	protected void setModelPositionsToView(Stream<ModelPositions> stream) {
		ObservableList<ModelPositions> positionModel = FXCollections.observableArrayList();
		stream.limit(40).forEach(m -> {
			positionModel.add(new ModelPositions(m.getPositions(), m.getModel()));
		});
		getView().setModels(positionModel.sorted());
	}

	protected abstract TreeItem<?> getTreeItem(NamedModelBase selectedModel);

	protected abstract List<NamedModelBase> getModels();

}
