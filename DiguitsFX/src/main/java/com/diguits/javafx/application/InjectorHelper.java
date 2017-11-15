package com.diguits.javafx.application;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class InjectorHelper {

	private static InjectorHelper instance;
	private Injector injector;
	List<Module> modules;

	private InjectorHelper() {
		modules = new ArrayList<Module>();
	}

	public static InjectorHelper getInstance() {
		if (instance == null)
			instance = new InjectorHelper();
		return instance;
	}

	public Injector getInjector() {
		if (injector == null)
			injector = Guice.createInjector(modules);
		return injector;
	}

	public List<Module> getModules() {
		return modules;
	}
}
