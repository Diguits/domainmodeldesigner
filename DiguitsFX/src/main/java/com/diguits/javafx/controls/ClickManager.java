package com.diguits.javafx.controls;

import javafx.animation.Animation.Status;

import java.awt.Toolkit;

import com.sun.javafx.event.EventHandlerManager;

import javafx.animation.PauseTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

@SuppressWarnings("restriction")
public class ClickManager implements EventTarget{
	private final EventHandlerManager eventHandlerManager = new EventHandlerManager(this);

	private Duration maxTimeBetweenSequentialClicks = null;
	private PauseTransition clickTimer;
	private SimpleIntegerProperty sequentialClickCount;
	private MouseEvent event;
	public static final EventType<MouseEvent> MOUSE_SINGLE_CLICKED = new EventType<MouseEvent>(MouseEvent.ANY,
			"MOUSE_SINGLE_CLICKED");
	public static final EventType<MouseEvent> MOUSE_DOUBLE_CLICKED = new EventType<MouseEvent>(MouseEvent.ANY,
			"MOUSE_DOBLE_CLICKED");

	public ClickManager() {
		super();
		try {
			maxTimeBetweenSequentialClicks = new Duration(((Integer)Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval")).intValue());
		} catch (Exception e) {
			maxTimeBetweenSequentialClicks = Duration.millis(200);
		}
		clickTimer = new PauseTransition(maxTimeBetweenSequentialClicks);
		sequentialClickCount = new SimpleIntegerProperty(0);
		clickTimer.setOnFinished(e -> {
			int count = sequentialClickCount.get();
			if (count == 1) {
				MouseEvent mouseEvent = new MouseEvent(MOUSE_SINGLE_CLICKED, event.getX(), event.getY(),
						event.getScreenX(), event.getScreenY(), event.getButton(), event.getClickCount(),
						event.isShiftDown(), event.isControlDown(), event.isAltDown(), event.isMetaDown(),
						event.isPrimaryButtonDown(), event.isMiddleButtonDown(), event.isSecondaryButtonDown(),
						event.isSynthesized(), event.isPopupTrigger(), event.isStillSincePress(),
						event.getPickResult());
				Event.fireEvent(this, mouseEvent);
			}
			if (count == 2) {
				MouseEvent mouseEvent = new MouseEvent(MOUSE_DOUBLE_CLICKED, event.getX(), event.getY(),
						event.getScreenX(), event.getScreenY(), event.getButton(), event.getClickCount(),
						event.isShiftDown(), event.isControlDown(), event.isAltDown(), event.isMetaDown(),
						event.isPrimaryButtonDown(), event.isMiddleButtonDown(), event.isSecondaryButtonDown(),
						event.isSynthesized(), event.isPopupTrigger(), event.isStillSincePress(),
						event.getPickResult());
				Event.fireEvent(this, mouseEvent);
			}
			sequentialClickCount.set(0);
		});
	}

	public void click(MouseEvent e) {
		this.event = e;
		if (clickTimer.getStatus() != Status.RUNNING)
			clickTimer.playFromStart();
		sequentialClickCount.set(sequentialClickCount.get() + 1);
	}

	private ObjectProperty<EventHandler<MouseEvent>> onSingleClick = new ObjectPropertyBase<EventHandler<MouseEvent>>() {
		@SuppressWarnings("unchecked")
		@Override
		protected void invalidated() {
			eventHandlerManager.setEventHandler(MOUSE_SINGLE_CLICKED, (EventHandler<MouseEvent>) (Object) get());
		}

		@Override
		public Object getBean() {
			return ClickManager.this;
		}

		@Override
		public String getName() {
			return "onSingleClick"; //$NON-NLS-1$
		}
	};

	// --- onSingleClick
	/**
	 * @return an ObjectProperty representing the crumbAction EventHandler being
	 *         used.
	 */
	public final ObjectProperty<EventHandler<MouseEvent>> onSingleClickProperty() {
		return onSingleClick;
	}

	/**
	 * Set a new EventHandler for when a user selects a crumb.
	 *
	 * @param value
	 */
	public final void setOnSingleClick(EventHandler<MouseEvent> value) {
		onSingleClickProperty().set(value);
	}

	/**
	 * Return the EventHandler currently used when a user selects a crumb.
	 *
	 * @return the EventHandler currently used when a user selects a crumb.
	 */
	public final EventHandler<MouseEvent> getOnSingleClick() {
		return onSingleClickProperty().get();
	}

	private ObjectProperty<EventHandler<MouseEvent>> onDoubleClick = new ObjectPropertyBase<EventHandler<MouseEvent>>() {
		@SuppressWarnings("unchecked")
		@Override
		protected void invalidated() {
			eventHandlerManager.setEventHandler(MOUSE_DOUBLE_CLICKED, (EventHandler<MouseEvent>) (Object) get());
		}

		@Override
		public Object getBean() {
			return ClickManager.this;
		}

		@Override
		public String getName() {
			return "onDoubleClick"; //$NON-NLS-1$
		}
	};

	// --- onDoubleClick
	/**
	 * @return an ObjectProperty representing the crumbAction EventHandler being
	 *         used.
	 */
	public final ObjectProperty<EventHandler<MouseEvent>> onDoubleClickProperty() {
		return onDoubleClick;
	}

	/**
	 * Set a new EventHandler for when a user selects a crumb.
	 *
	 * @param value
	 */
	public final void setOnDoubleClick(EventHandler<MouseEvent> value) {
		onDoubleClickProperty().set(value);
	}

	/**
	 * Return the EventHandler currently used when a user selects a crumb.
	 *
	 * @return the EventHandler currently used when a user selects a crumb.
	 */
	public final EventHandler<MouseEvent> getOnDoubleClick() {
		return onDoubleClickProperty().get();
	}

	@Override
	public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail) {
		return tail.prepend(eventHandlerManager);
	}

}
