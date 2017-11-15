package com.diguits.domainmodeldesigner.controllers;

import com.diguits.domainmodeldesigner.domainmodel.controllers.DomainModelTreeDecorator;
import com.diguits.domainmodeldesigner.domainmodelstructure.controllers.DomainModelStructureTreeDecorator;
import com.diguits.domainmodeldesigner.template.controllers.TemplateTreeDecorator;
import com.diguits.domainmodeldesigner.templateapplyconfig.controllers.TemplateApplyConfigTreeDecorator;
import com.diguits.javafx.container.decorators.DecoratorProviderBase;
import com.diguits.javafx.log.controllers.LogDecorator;
import com.diguits.javafx.navigation.controllers.NavigationDecorator;
import com.diguits.javafx.problem.controllers.ProblemsDecorator;
import com.diguits.javafx.settings.controllers.SettingsDecorator;
import com.diguits.javafx.system.controllers.SystemDecorator;
import com.diguits.javafx.undo.controllers.UndoDecorator;

public class DecoratorProvider extends DecoratorProviderBase{

	@Override
	protected void registerDecorators() {
		registerDecorator(SystemDecorator.class);
		registerDecorator(NavigationDecorator.class);
		registerDecorator(UndoDecorator.class);
		registerDecorator(DomainModelTreeDecorator.class);
		registerDecorator(TemplateTreeDecorator.class);
		registerDecorator(ProblemsDecorator.class);
		registerDecorator(LogDecorator.class);
		registerDecorator(DomainModelStructureTreeDecorator.class);
		registerDecorator(TemplateApplyConfigTreeDecorator.class);
		registerDecorator(SettingsDecorator.class);
	}

}
