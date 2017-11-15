package com.diguits.javafx.concurrent;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;

public class ThreadPoolTaskExecutor implements TaskExecutor{

	ExecutorService executorService;

	public ThreadPoolTaskExecutor() {
		super();
		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public Thread executeTask(Task<?> task) {
		Thread backgroundThread = new Thread(task);
		backgroundThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				e.printStackTrace();
			}
		});
		backgroundThread.setDaemon(true);
		executorService.submit(backgroundThread);
		return backgroundThread;
	}

}
