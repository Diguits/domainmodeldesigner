package com.diguits.javafx.views;

import java.util.ArrayList;
import java.util.List;

import com.diguits.javafx.undo.changes.PropertyChange;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public abstract class ModelView<TNode extends Node, TModel> extends ViewBase<TNode>
		implements IModelView<TNode, TModel> {

	protected PropertyChangeEventListener propertyChangeEventListener;

	protected static class PropertyPair<T> {
		Property<T> from;
		Property<T> to;

		public PropertyPair(Property<T> from, Property<T> to) {
			super();
			this.from = from;
			this.to = to;
		}

		public Property<T> getFrom() {
			return from;
		}

		public void setFrom(Property<T> from) {
			this.from = from;
		}

		public Property<T> getTo() {
			return to;
		}

		public void setTo(Property<T> to) {
			this.to = to;
		}

		public void bindBidirectional() {
			from.bindBidirectional(to);
		}

		public void unbindBidirectional() {
			from.unbindBidirectional(to);
		}
	}

	List<PropertyPair<?>> bidirectionalBindigns;
	List<Property<?>> unidirectionalBindigns;

	protected TModel model;

	public TModel getModel() {
		return model;
	}

	public void setModel(TModel model) {
		unBindFieldsToModel();
		this.model = model;
		initialize();
		bindFieldsToModel();
	}

	protected <T> void bindBidirectional(Property<T> from, Property<T> to) {
		if (from != null && to != null) {
			from.bindBidirectional(to);
			addOnPropertyChangeListener(from);
			PropertyPair<?> pair = new PropertyPair<>(from, to);
			getBidirectionalBindigns().add(pair);
		}
	}

	private <T> void addOnPropertyChangeListener(Property<T> from) {
		ChangeListener<T> listener = new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				onPropertyChange(new PropertyChange<T>(oldValue, newValue, from));
			}
		};
		from.addListener(listener);
	}

	protected <TValue> void onPropertyChange(PropertyChange<TValue> propertyChange) {
		if (propertyChangeEventListener != null) {
			propertyChangeEventListener.onPropertyChange(propertyChange);
		}

	}

	protected <T> void bind(Property<T> from, ObservableValue<? extends T> to) {
		if (from != null && to != null) {
			from.bind(to);
			getUnidirectionalBindigns().add(from);
		}
	}

	protected abstract void bindFieldsToModel();

	protected void unBindFieldsToModel() {
		if (bidirectionalBindigns != null)
			for (PropertyPair<?> pair : bidirectionalBindigns) {
				pair.unbindBidirectional();
			}

		if (unidirectionalBindigns != null)
			for (Property<?> property : unidirectionalBindigns) {
				property.unbind();
			}
	}

	protected List<PropertyPair<?>> getBidirectionalBindigns() {
		if (bidirectionalBindigns == null)
			bidirectionalBindigns = new ArrayList<PropertyPair<?>>();
		return bidirectionalBindigns;
	}

	protected List<Property<?>> getUnidirectionalBindigns() {
		if (unidirectionalBindigns == null) {
			unidirectionalBindigns = new ArrayList<Property<?>>();
		}
		return unidirectionalBindigns;
	}

	@Override
	public PropertyChangeEventListener getPropertyChangeEventListener() {
		return propertyChangeEventListener;
	}

	@Override
	public void setPropertyChangeEventListener(PropertyChangeEventListener propertyChangeEventListener) {
		this.propertyChangeEventListener = propertyChangeEventListener;
	}
}
