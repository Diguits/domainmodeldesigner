package com.diguits.javafx.views.helpers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EventsHelper {

    public static void setMoveNextOnEnter(Node node) {
        node.addEventFilter( KeyEvent.KEY_PRESSED, EventsHelper.getMoveNextOnEnterEventHandler());
    }

    static EventHandler<KeyEvent> getMoveNextOnEnterEventHandler() {
        return (KeyEvent event ) ->
        {
            if ( event.getCode() == KeyCode.ENTER && !event.isControlDown())
            {
                KeyEvent newEvent
                        = new KeyEvent(
                        null,
                        null,
                        KeyEvent.KEY_PRESSED,
                        "",
                        "\t",
                        KeyCode.TAB,
                        event.isShiftDown(),
                        event.isControlDown(),
                        event.isAltDown(),
                        event.isMetaDown()
                );

                Event.fireEvent( event.getTarget(), newEvent );
                event.consume();
            }
        };
    }
}
