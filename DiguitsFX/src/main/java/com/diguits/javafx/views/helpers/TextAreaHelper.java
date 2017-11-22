package com.diguits.javafx.views.helpers;

import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import com.sun.javafx.scene.control.skin.TextAreaSkin;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class TextAreaHelper {

	public static TextArea createTextArea() {
		TextArea textArea = new TextArea();
		textArea.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				TextAreaSkin skin = (TextAreaSkin) textArea.getSkin();
				if (skin.getBehavior() instanceof TextAreaBehavior) {
					TextAreaBehavior behavior = skin.getBehavior();
					if (ke.getCode() == KeyCode.TAB && !ke.isShiftDown()) {
					// traverse focus if pressing TAB and insert tab if pressing CONTROL+TAB
						if (ke.isControlDown()) {
							behavior.callAction("InsertTab");
						} else {
							behavior.callAction("TraverseNext");
						}
						ke.consume();
					}
					if(ke.getCode() == KeyCode.ENTER && ke.isControlDown()){
						behavior.callAction("InsertNewLine");
						ke.consume();
					}
				}

			}
		});
		EventsHelper.setMoveNextOnEnter(textArea);
		return textArea;
	}

	public static TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex) {
		TextArea textArea = createTextArea();
		GridPane.setMargin(textArea, new Insets(2.0, 2.0, 2.0, 2.0));
		if (gridPane.getRowConstraints().size() <= rowIndex)
			GridPaneHelper.addRowConstraints(gridPane, rowIndex - gridPane.getRowConstraints().size(), 100);
		gridPane.add(textArea, columnIndex, rowIndex);
		return textArea;
	}

	public static TextArea createTextAreaInsideGrid(GridPane gridPane, int columnIndex, int rowIndex, String text) {
		TextArea result = createTextAreaInsideGrid(gridPane, columnIndex, rowIndex);
		LabelHelper.createLabelInsideGrid(gridPane, text, columnIndex - 1, rowIndex);
		return result;
	}

	public static TextArea createTextAreaInsideGrid(GridPane gridPane, String text) {
		return createTextAreaInsideGrid(gridPane, 1, gridPane.getRowConstraints().size(), text);
	}

}
