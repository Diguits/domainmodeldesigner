package com.diguits.javafx.concurrent;

import javafx.concurrent.Task;

public interface TaskExecutor {

	Thread executeTask(Task<?> task);
}
