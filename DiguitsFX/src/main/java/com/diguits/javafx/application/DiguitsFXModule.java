package com.diguits.javafx.application;

import com.diguits.common.log.SLF4JTypeListener;
import com.diguits.javafx.concurrent.TaskExecutor;
import com.diguits.javafx.concurrent.ThreadPoolTaskExecutor;
import com.diguits.javafx.container.controllers.ContainerManager;
import com.diguits.javafx.container.controllers.EditorsHoleController;
import com.diguits.javafx.container.controllers.GlobalSelectionManager;
import com.diguits.javafx.container.controllers.MainController;
import com.diguits.javafx.container.decorators.IOptionToNodeFactory;
import com.diguits.javafx.container.decorators.OptionToNodeFactory;
import com.diguits.javafx.controls.AlertHelper;
import com.diguits.javafx.editor.controllers.EditorControllerFactory;
import com.diguits.javafx.editor.controllers.IEditorControllerFactory;
import com.diguits.javafx.log.controllers.LogController;
import com.diguits.javafx.log.controllers.LogDecorator;
import com.diguits.javafx.problem.controllers.ProblemsController;
import com.diguits.javafx.undo.UndoManager;
import com.diguits.javafx.undo.impl.UndoManagerImpl;
import com.diguits.javafx.views.INodeFactoryHelper;
import com.diguits.javafx.views.NodeFactoryHelperBase;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class DiguitsFXModule extends AbstractModule {

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new SLF4JTypeListener());
		bind(IEditorControllerFactory.class).to(EditorControllerFactory.class).asEagerSingleton();
		bind(EditorsHoleController.class).asEagerSingleton();
		bind(AlertHelper.class).asEagerSingleton();
		bind(GlobalSelectionManager.class).asEagerSingleton();
		bind(ProblemsController.class).asEagerSingleton();
		bind(MainController.class).asEagerSingleton();
		bind(INodeFactoryHelper.class).to(NodeFactoryHelperBase.class);
		bind(ContainerManager.class).asEagerSingleton();
		bind(IOptionToNodeFactory.class).to(OptionToNodeFactory.class).asEagerSingleton();
		bind(TaskExecutor.class).to(ThreadPoolTaskExecutor.class).asEagerSingleton();
		bind(LogDecorator.class).asEagerSingleton();
		bind(LogController.class).asEagerSingleton();
		bind(UndoManager.class).to(UndoManagerImpl.class).asEagerSingleton();
	}

}
