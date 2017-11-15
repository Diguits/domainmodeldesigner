package com.diguits.javafx.log.controllers;

import javafx.application.Platform;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * LogControllerAppender for Log4j 2
 */
@Plugin(name = "LogControllerAppender", category = "Core", elementType = "appender", printObject = true)
public final class LogControllerAppender extends AbstractAppender {

	/**
	 *
	 */
	private static final long serialVersionUID = 4456949163840610603L;

	private static LogController logController;

	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final Lock readLock = rwLock.readLock();

	protected LogControllerAppender(String name, Filter filter, Layout<? extends Serializable> layout,
			final boolean ignoreExceptions) {
		super(name, filter, layout, ignoreExceptions);
	}

	/**
	 * This method is where the appender does the work.
	 *
	 * @param event
	 *            Log event with log data
	 */
	@Override
	public void append(LogEvent event) {
		readLock.lock();
		//final String message = new String(getLayout().toByteArray(event));

		// append log text to LogController
		try {
			Platform.runLater(() -> {
				try {
					logController.log(new LogItem(event));
				} catch (final Throwable t) {
					System.out.println("Error while append to LogController: " + t.getMessage());
				}
			});
		} catch (final IllegalStateException ex) {
			ex.printStackTrace();

		} finally {
			readLock.unlock();
		}
	}

	/**
	 * Factory method. Log4j will parse the configuration and call this factory
	 * method to construct the appender with the configured attributes.
	 *
	 * @param name
	 *            Name of appender
	 * @param layout
	 *            Log layout of appender
	 * @param filter
	 *            Filter for appender
	 * @return The LogControllerAppender
	 */
	@PluginFactory
	public static LogControllerAppender createAppender(@PluginAttribute("name") String name,
			@PluginElement("Layout") Layout<? extends Serializable> layout,
			@PluginElement("Filter") final Filter filter) {
		if (name == null) {
			// LOGGER.error("No name provided for LogControllerAppender");
			return null;
		}
		if (layout == null) {
			layout = PatternLayout.createDefaultLayout();
		}
		return new LogControllerAppender(name, filter, layout, true);
	}

	/**
	 * Set LogController to append
	 *
	 * @param logController
	 *            LogController to append
	 */
	public static void setLogController(LogController logController) {
		LogControllerAppender.logController = logController;
	}
}