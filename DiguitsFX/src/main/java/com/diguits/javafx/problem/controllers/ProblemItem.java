package com.diguits.javafx.problem.controllers;

import com.diguits.javafx.model.ModelBase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProblemItem extends ModelBase {
	ObjectProperty<Position> position;
	StringProperty message;
	ObjectProperty<Kind> kind;

	public enum Kind {
		ERROR, WARN, INFO
	}

	public ProblemItem() {
		super();
	}

	public ProblemItem(Kind kind, String message, Position position) {
		super();
		setKind(kind);
		setMessage(message);
		setPosition(position);
	}

	public Position getPosition() {
		if (position != null)
			return position.get();
		return null;
	}

	public void setPosition(Position position) {
		if (this.position != null || position != null) {
			positionProperty().set(position);
		}
	}

	public ObjectProperty<Position> positionProperty() {
		if (position == null) {
			position = new SimpleObjectProperty<Position>(this, "position", null);
		}
		return position;
	}

	public String getMessage() {
		if (message != null)
			return message.get();
		return null;
	}

	public void setMessage(String message) {
		if (this.message != null || message != null) {
			messageProperty().set(message);
		}
	}

	public StringProperty messageProperty() {
		if (message == null) {
			message = new SimpleStringProperty(this, "message", null);
		}
		return message;
	}

	public Kind getKind() {
		if (kind != null)
			return kind.get();
		return null;
	}

	public void setKind(Kind kind) {
		if (this.kind != null || kind != null) {
			kindProperty().set(kind);
		}
	}

	public ObjectProperty<Kind> kindProperty() {
		if (kind == null) {
			kind = new SimpleObjectProperty<Kind>(this, "kind", null);
		}
		return kind;
	}

	@Override
	public String toString() {
		String result = "";
		Kind kind = getKind();
		if (kind != null)
			result += getKind().toString() + " : ";
		Position position = getPosition();
		if (position != null) {
			if (position.getLineNumber() >= 0)
				result += "Line : " + position.getLineNumber() + " ";
			if (position.getLineNumber() >= 0)
				result += "Column : " + position.getColumnNumber() + " ";

		}
		if (getMessage() != null)
			result += getMessage();
		return result;
	}
}
