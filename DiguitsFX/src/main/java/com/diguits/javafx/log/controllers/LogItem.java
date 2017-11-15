package com.diguits.javafx.log.controllers;

import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;

import com.diguits.javafx.model.ModelBase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogItem extends ModelBase {
	ObjectProperty<Date> date;
	StringProperty message;
	StringProperty className;
	StringProperty methodName;
	StringProperty thread;
	ObjectProperty<Integer> line;
	ObjectProperty<LogLevel> level;

	public enum LogLevel {
		FATAL, ERROR, WARN, INFO, DEBUG, TRACE
	}

	public LogItem() {
		super();
	}

	public LogItem(LogLevel level, String message, Date date) {
		super();
		setLevel(level);
		setMessage(message);
		setDate(date);
	}

	public LogItem(LogEvent event) {
		if (event.getLevel() == Level.DEBUG)
			setLevel(LogLevel.DEBUG);
		else if (event.getLevel() == Level.ERROR)
			setLevel(LogLevel.ERROR);
		else if (event.getLevel() == Level.FATAL)
			setLevel(LogLevel.FATAL);
		else if (event.getLevel() == Level.INFO)
			setLevel(LogLevel.INFO);
		else if (event.getLevel() == Level.TRACE)
			setLevel(LogLevel.TRACE);
		else if (event.getLevel() == Level.WARN)
			setLevel(LogLevel.WARN);
		setMessage(event.getMessage().getFormattedMessage());
		setDate(new Date(event.getTimeMillis()));
		setThread(event.getThreadName());
		StackTraceElement source = event.getSource();
		if (source != null) {
			setClassName(source.getClassName());
			setMethodName(source.getMethodName());
			setLine(source.getLineNumber());
		}
	}

	public Date getDate() {
		if (date != null)
			return date.get();
		return null;
	}

	public void setDate(Date date) {
		if (this.date != null || date != null) {
			dateProperty().set(date);
		}
	}

	public ObjectProperty<Date> dateProperty() {
		if (date == null) {
			date = new SimpleObjectProperty<Date>(this, "date", null);
		}
		return date;
	}

	public Integer getLine() {
		if (line != null)
			return line.get();
		return null;
	}

	public void setLine(Integer clazz) {
		if (this.line != null || clazz != null) {
			lineProperty().set(clazz);
		}
	}

	public ObjectProperty<Integer> lineProperty() {
		if (line == null) {
			line = new SimpleObjectProperty<Integer>(this, "line", null);
		}
		return line;
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

	public String getThread() {
		if (thread != null)
			return thread.get();
		return null;
	}

	public void setThread(String thread) {
		if (this.thread != null || thread != null) {
			threadProperty().set(thread);
		}
	}

	public StringProperty threadProperty() {
		if (thread == null) {
			thread = new SimpleStringProperty(this, "thread", null);
		}
		return thread;
	}

	public String getClassName() {
		if (className != null)
			return className.get();
		return null;
	}

	public void setClassName(String className) {
		if (this.className != null || className != null) {
			classNameProperty().set(className);
		}
	}

	public StringProperty classNameProperty() {
		if (className == null) {
			className = new SimpleStringProperty(this, "className", null);
		}
		return className;
	}

	public String getMethodName() {
		if (methodName != null)
			return methodName.get();
		return null;
	}

	public void setMethodName(String methodName) {
		if (this.methodName != null || methodName != null) {
			methodNameProperty().set(methodName);
		}
	}

	public StringProperty methodNameProperty() {
		if (methodName == null) {
			methodName = new SimpleStringProperty(this, "methodName", null);
		}
		return methodName;
	}

	public LogLevel getLevel() {
		if (level != null)
			return level.get();
		return null;
	}

	public void setLevel(LogLevel level) {
		if (this.level != null || level != null) {
			levelProperty().set(level);
		}
	}

	public ObjectProperty<LogLevel> levelProperty() {
		if (level == null) {
			level = new SimpleObjectProperty<LogLevel>(this, "level", null);
		}
		return level;
	}

	@Override
	public String toString() {
		String result = "";
		LogLevel level = getLevel();
		if (level != null)
			result += getLevel().toString() + " : ";
		result += getDate().toString() + " : ";
		if (getMessage() != null)
			result += getMessage();
		return result;
	}
}
