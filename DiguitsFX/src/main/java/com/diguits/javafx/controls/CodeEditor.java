package com.diguits.javafx.controls;

import java.net.URL;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * A syntax highlighting code editor for JavaFX created by wrapping a CodeMirror
 * code editor in a WebView.
 *
 * See http://codemirror.net for more information on using the codemirror
 * editor.
 */
public class CodeEditor extends BorderPane {
	/** a webview used to encapsulate the CodeMirror JavaScript. */
	final WebView webview = new WebView();
	final ToolBar toolBar = new ToolBar();
	/**
	 * a snapshot of the code to be edited kept for easy initialization and
	 * reversion of editable code.
	 */
	private StringProperty editingCode;
	private BooleanProperty readOnly;
	private boolean ready;

	/**
	 * a template for editing code - this can be changed to any template derived
	 * from the supported modes at http://codemirror.net to allow syntax
	 * highlighted editing of a wide variety of languages.
	 */
	private final String editingTemplate() {
		URL codemirrorcssUrl = this.getClass().getClassLoader().getResource("css/codemirror.css");
		URL eclipsecssUrl = this.getClass().getClassLoader().getResource("css/eclipse.css");
		URL dialogcssUrl = this.getClass().getClassLoader().getResource("css/dialog.css");
		URL matchesonscrollbarcssUrl = this.getClass().getClassLoader().getResource("css/matchesonscrollbar.css");
		URL codemirrorUrl = this.getClass().getClassLoader().getResource("js/codemirror.js");
		URL clikeUrl = this.getClass().getClassLoader().getResource("js/clike.js");
		URL dialogUrl = this.getClass().getClassLoader().getResource("js/dialog.js");
		URL matchesonscrollbarUrl = this.getClass().getClassLoader().getResource("js/matchesonscrollbar.js");
		URL match_highlighterUrl = this.getClass().getClassLoader().getResource("js/match-highlighter.js");
		URL searchUrl = this.getClass().getClassLoader().getResource("js/search.js");
		URL annotatescrollbarUrl = this.getClass().getClassLoader().getResource("js/annotatescrollbar.js");
		URL searchcursorUrl = this.getClass().getClassLoader().getResource("js/searchcursor.js");
		URL activelineUrl = this.getClass().getClassLoader().getResource("js/active-line.js");
		return "<!doctype html>"
				+ "<html>"
				+ "<head>"
				+ "  <link rel=\"stylesheet\" href=\"" + codemirrorcssUrl.toExternalForm() + "\">"
				+ "  <link rel=\"stylesheet\" href=\"" + eclipsecssUrl.toExternalForm() + "\">" + "  "
				+ "  <link rel=\"stylesheet\" href=\"" + dialogcssUrl.toExternalForm() + "\">"
				+ "  <link rel=\"stylesheet\" href=\"" + matchesonscrollbarcssUrl.toExternalForm() + "\">"
				+ "  <script src=\"" + codemirrorUrl.toExternalForm() + "\"></script>"
				+ "  <script src=\"" + clikeUrl.toExternalForm() + "\"/></script>"
				+ "  <script src=\"" + dialogUrl.toExternalForm() + "\"/></script>"
				+ "  <script src=\"" + searchcursorUrl.toExternalForm() + "\"/></script>"
				+ "  <script src=\"" + searchUrl.toExternalForm() + "\"/> </script>"
				+ "  <script src=\"" + annotatescrollbarUrl.toExternalForm() + "\"/></script>"
				+ "  <script src=\"" + matchesonscrollbarUrl.toExternalForm() + "\"/></script>"
				+ "  <script src=\"" + match_highlighterUrl.toExternalForm() + "\"/></script>"
				+ "  <script src=\"" + activelineUrl.toExternalForm() + "\"/></script>"
				+ "  <style type=\"text/css\">"
				+ "	  .CodeMirror {"
				+ "		border: 1px solid #eee;"
				+ " 	]}"
				+ "	  .CodeMirror-focused .cm-matchhighlight {"
				+ "		background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAYAAABytg0kAAAAFklEQVQI12NgYGBgkKzc8x9CMDAwAAAmhwSbidEoSQAAAABJRU5ErkJggg==);"
				+ "		background-color: #FFE7AB;"
				+ "		background-position: bottom;"
				+ "		background-repeat: repeat-x;}"
				+ "	 </style>"
				+ "</head>"
				+ ""
				+ "<body style=\"margin-top: 0px; margin-left: 0px; margin-bottom: 0px;\">"
				+ "	<form>"
				+ "		<textarea id=\"code\" name=\"code\">\n"
				+ "${code}"
				+ "		</textarea>"
				+ "	</form>"
				+ "<script>"
				+ "  var updatingJS = false;"
				+ "  function updateJS() {"
				+ " 	updatingJS=true; "
				+ "		editor.setValue(codeEditor.getEditingCode()); "
				+ "		updatingJS=false;"
				+ "  };"
				+ "  var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), {"
				+ "    lineNumbers: true,"
				+ "	   styleActiveLine: true,"
				+ "    viewportMargin: Infinity, "
				+ "    matchBrackets: true,"
				+ "    theme: \"eclipse\","
				+ "	   readOnly:" + getReadOnly() + ","
				+ "    mode: \"text/x-java\","
				+ "	   styleActiveLine: true,"
				+ "	   highlightSelectionMatches: {showToken: /\\w/}"
				+ "  });"
				+ "   var pending;"
				+ "  editor.on(\"change\", function() { "
				+ "	   if (!updatingJS){"
				+ "	   		clearTimeout(pending);"
				+ "    		pending = setTimeout(updateFX, 400);"
				+ "	   }"
				+ "  });"
				+ "	 function updateFX() {"
				+ "  	codeEditor.setEditingCodeFromEditor(editor.getValue().trim());"
				+ "  };"
				+ "  function paste() {"
				+ "  	editor.replaceSelection(codeEditor.getContentToPaste());"
				+ "  };"
				+ "  window.onload = function(){"
				+ "		updateJS();"
				+ "	 };"
				+ "</script>"
				+ "</body>"
				+ "</html>";
	}

	/**
	 * applies the editing template to the editing code to create the
	 * html+javascript source for a code editor.
	 */
	private String applyEditingTemplate() {
		String editingTemplate = editingTemplate();
		String code = getEditingCode();
		return editingTemplate.replace("${code}", code == null ? "" : code);

	}

	/**
	 * Create a new code editor.
	 *
	 * @param editingCode
	 *            the initial code to be edited in the code editor.
	 */
	public CodeEditor(String editingCode) {
		initialize(editingCode, false);
	}

	/**
	 * Create a new code editor.
	 *
	 * @param editingCode
	 *            the initial code to be edited in the code editor.
	 */
	public CodeEditor(String editingCode, Boolean readOnly) {
		initialize(editingCode, readOnly);
	}

	private void initialize(String editingCode, Boolean readOnly) {
		editingCode = editingCode.trim();
		setEditingCode(editingCode);
		setReadOnly(readOnly);
		initializeWebView();
		initializeToolBar();
		this.setCenter(webview);
		this.setTop(toolBar);
	}

	private void initializeToolBar() {
		Button button = new Button(null, new ImageView("/images/page_white_copy.png"));
		button.setOnAction(e -> copy());
		button.disableProperty().bind(readOnlyProperty());
		toolBar.getItems().add(button);

		button = new Button(null, new ImageView("/images/cut.png"));
		button.setOnAction(e -> cut());
		button.disableProperty().bind(readOnlyProperty());
		toolBar.getItems().add(button);

		button = new Button(null, new ImageView("/images/page_white_paste.png"));
		button.setOnAction(e -> paste());
		button.disableProperty().bind(readOnlyProperty());
		toolBar.getItems().add(button);

		Separator separator = new Separator(Orientation.VERTICAL);
		separator.setPrefWidth(4);
		toolBar.getItems().add(separator);

		button = new Button(null, new ImageView("/images/undo.png"));
		button.setOnAction(e -> undo());
		button.disableProperty().bind(readOnlyProperty());
		toolBar.getItems().add(button);

		button = new Button(null, new ImageView("/images/redo.png"));
		button.setOnAction(e -> redo());
		button.disableProperty().bind(readOnlyProperty());
		toolBar.getItems().add(button);

		separator = new Separator(Orientation.VERTICAL);
		separator.setPrefWidth(4);
		toolBar.getItems().add(separator);

		button = new Button(null, new ImageView("/images/find.png"));
		button.setOnAction(e -> find());
		toolBar.getItems().add(button);

		button = new Button(null, new ImageView("/images/find_next.png"));
		button.setOnAction(e -> findNext());
		toolBar.getItems().add(button);

		button = new Button(null, new ImageView("/images/find_previous.png"));
		button.setOnAction(e -> findPrevious());
		toolBar.getItems().add(button);

		separator = new Separator(Orientation.VERTICAL);
		separator.setPrefWidth(4);
		toolBar.getItems().add(separator);

		button = new Button(null, new ImageView("/images/text_replace.png"));
		button.setOnAction(e -> replase());
		toolBar.getItems().add(button);

		separator = new Separator(Orientation.VERTICAL);
		separator.setPrefWidth(4);
		toolBar.getItems().add(separator);

	}

	private void initializeWebView() {
		WebEngine webEngine = webview.getEngine();

		// Set the member for the window object after the document loads
		webEngine.getLoadWorker().stateProperty().addListener((prop, oldState, newState) -> {
			if (newState == Worker.State.SUCCEEDED) {
				JSObject jsWindow = (JSObject) webEngine.executeScript("window");
				jsWindow.setMember("codeEditor", this);
				editingCodeProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						if (!editingFromEditor) {
							Platform.runLater(() -> webview.getEngine().executeScript("updateJS();"));
						}
					}
				});
				try {
					webview.getEngine().executeScript("updateJS();");
					webview.getEngine().executeScript(
							"editor.setSize(" + (webview.getWidth() - 2) + "," + (webview.getHeight() - 2) + ");");
				} catch (Exception e) {
				}
				readOnlyProperty().addListener(new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						refreshReadOnly();
					}
				});
				refreshReadOnly();
				webview.heightProperty().addListener((v, o, n) -> {
					try {
						webview.getEngine().executeScript(
								"editor.setSize(" + (webview.getWidth() - 2) + "," + (n.intValue() - 2) + ");");
					} catch (Exception e) {
					}
				});
				webview.widthProperty().addListener((v, o, n) -> {
					try {
						webview.getEngine().executeScript(
								"editor.setSize(" + (n.intValue() - 2) + "," + (webview.getHeight() - 2) + ");");
					} catch (Exception e) {
					}
				});
				if (webview.getScene() != null)
					webview.getScene().focusOwnerProperty().addListener((v, o, n) -> {
						try {
							webview.getEngine().executeScript("editor.setSize(" + (webview.getWidth() - 2) + ","
									+ (webview.getHeight() - 2) + ");");
						} catch (Exception e) {
						}
					});

				ready = true;
			}
		});
		webEngine.loadContent(applyEditingTemplate());

	}

	public void addCustomToolBarItems(Node[] items) {
		toolBar.getItems().addAll(items);
	}

	public String getEditingCode() {
		if (editingCode != null)
			return editingCode.get();
		return "";
	}

	private boolean editingFromEditor = false;

	public void setEditingCodeFromEditor(String editingCode) {
		if (ready) {
			editingFromEditor = true;
			setEditingCode(editingCode);
			editingFromEditor = false;
		}
	}

	public void setEditingCode(String editingCode) {
		if (this.editingCode != null || editingCode == null || !editingCode.equals("")) {
			editingCodeProperty().set(editingCode);
		}
	}

	public StringProperty editingCodeProperty() {
		if (editingCode == null) {
			editingCode = new SimpleStringProperty(this, "editingCode", "");
		}
		return editingCode;
	}

	public Boolean getReadOnly() {
		if (readOnly != null)
			return readOnly.get();
		return false;
	}

	public void setReadOnly(Boolean readOnly) {
		if (this.readOnly != null || readOnly == null || !readOnly.equals("")) {
			readOnlyProperty().set(readOnly);
		}
	}

	public BooleanProperty readOnlyProperty() {
		if (readOnly == null) {
			readOnly = new SimpleBooleanProperty(this, "readOnly", false);
		}
		return readOnly;
	}

	@Override
	public void requestFocus() {
		super.requestFocus();
		webview.requestFocus();
	}

	public void cut() {
		String selectedText = (String) webview.getEngine().executeScript("editor.getSelection();");
		webview.getEngine().executeScript("editor.replaceSelection(\"\");");
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(selectedText);
		clipboard.setContent(content);

	}

	public void copy() {
		String selectedText = (String) webview.getEngine().executeScript("editor.getSelection();");
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(selectedText);
		clipboard.setContent(content);
	}

	public String getContentToPaste() {
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		return (String) clipboard.getContent(DataFormat.PLAIN_TEXT);
	}

	public void paste() {
		webview.getEngine().executeScript("paste()");
	}

	public void goTo(long lineNumber, long columnNumber) {
		if (ready) {
			webview.getEngine()
					.executeScript("editor.focus();editor.setCursor(" + (lineNumber - 1) + ", " + columnNumber + ");");
		} else {
			webview.getEngine().getLoadWorker().stateProperty().addListener((prop, oldState, newState) -> {
				if (newState == Worker.State.SUCCEEDED) {
					webview.getEngine().executeScript(
							"editor.focus();"
									+ "editor.setCursor(" + (lineNumber - 1) + ", " + columnNumber + ");");
				}
			});
		}

	}

	public void redo() {
		webview.getEngine().executeScript("editor.redo();");
	}

	private void undo() {
		webview.getEngine().executeScript("editor.undo();");
	}

	private void find() {
		webview.getEngine().executeScript("editor.execCommand(\"find\");");
	}

	private void findNext() {
		webview.getEngine().executeScript("editor.execCommand(\"findNext\");");
	}

	private void findPrevious() {
		webview.getEngine().executeScript("editor.execCommand(\"findPrev\");");
	}

	private void replase() {
		webview.getEngine().executeScript("editor.execCommand(\"replace\");");
	}

	private void refreshReadOnly() {
		if (ready)
			webview.getEngine().executeScript("editor.readOnly=" + getReadOnly() + ";");
	}

}
