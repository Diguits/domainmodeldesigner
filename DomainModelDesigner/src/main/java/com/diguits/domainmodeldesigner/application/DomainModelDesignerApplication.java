package com.diguits.domainmodeldesigner.application;

import com.diguits.javafx.application.DiguitsFXApplicationBase;
import com.diguits.javafx.application.InjectorHelper;

public class DomainModelDesignerApplication extends DiguitsFXApplicationBase {

	@Override
	protected String getApplicationIcon() {
		return "/images/relationships.png";
	}

	@Override
	protected String getApplicationTitle() {
		return "Domain Model Designer";
	}

	@Override
	protected String getDefaultCSS() {
		return "/css/default.css";
	}

	@Override
	protected void addModules() {
		super.addModules();
		InjectorHelper.getInstance().getModules().add(new DomainModelDesignerModule(getHostServices()));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
