package com.diguits.domainmodeldesigner.services.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;

import com.diguits.domainmodeldefinition.serialization.services.LocalizedDataCSV;
import com.diguits.domainmodeldefinition.serialization.services.LocalizedDataToCSVSerializer;
import com.diguits.domainmodeldesigner.domainmodel.models.BaseDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.CustomFieldValueDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.DomainModelDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocaleDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.LocalizedDataDefModel;
import com.diguits.domainmodeldesigner.domainmodel.models.RelationshipPartDefModel;
import com.diguits.domainmodeldesigner.services.DomainModelClientService;
import com.diguits.domainmodeldesigner.services.ListAllDefModelsVisitor;
import com.diguits.javafx.concurrent.TaskBase;

public class ImportLocalizedDataModelFromCSVTask extends TaskBase<Void> {

	private String path;
	private LocalizedDataToCSVSerializer serializer;
	private DomainModelClientService domainModelClientService;
	private ListAllDefModelsVisitor visitor;
	private Logger logger;

	public ImportLocalizedDataModelFromCSVTask(String path,
			LocalizedDataToCSVSerializer serializer, DomainModelClientService domainModelClientService,
			ListAllDefModelsVisitor visitor, Logger logger) {
		super();
		this.path = path;
		this.serializer = serializer;
		this.domainModelClientService = domainModelClientService;
		this.visitor = visitor;
		this.logger = logger;
	}

	@Override
	protected Void call() throws Exception {
		List<LocalizedDataCSV> localizedDataCSVs = serializer.deserialize(path);
		DomainModelDefModel domainModelDefModel = domainModelClientService.getDomainModelDef();
		visitor.ignore(LocalizedDataDefModel.class);
		visitor.ignore(CustomFieldValueDefModel.class);
		visitor.ignore(RelationshipPartDefModel.class);
		List<BaseDefModel> allDefModels = visitor.getAllDefModels(domainModelDefModel);
		List<LocaleDefModel> locales = domainModelDefModel.getLocales();
		Map<String, LocaleDefModel> localeMap = new HashMap<String, LocaleDefModel>();
		for (LocaleDefModel locale : locales) {
			localeMap.put(locale.getName(), locale);
		}
		int totalCount = localizedDataCSVs.size();
		int position = 0;
		updateProgress(0, totalCount);
		for (LocalizedDataCSV localizedDataCSV : localizedDataCSVs) {
			BaseDefModel model = findModelById(allDefModels, localizedDataCSV.getDefId());
			if (model != null) {
				updateMessage(model.getName());
				LocaleDefModel locale = localeMap.get(localizedDataCSV.getLocale());
				if (locale != null) {
					LocalizedDataDefModel localizedData = model.getLocalizedData(locale);
					if(localizedData==null){
						localizedData = new LocalizedDataDefModel();
						localizedData.setLocale(locale);
						localizedData.setOwner(model);
						model.getLocalizedDatas().add(localizedData);
					}
					localizedData.setCaption(localizedDataCSV.getCaption());
					localizedData.setHint(localizedDataCSV.getHint());
					localizedData.setPlaceHolder(localizedDataCSV.getPlaceHolder());
					localizedData.setFormat(localizedDataCSV.getFormat());
					localizedData.setCaption(localizedDataCSV.getCaption());

				} else
					logger.error("Could not find the locale »" + localizedDataCSV.getLocale() + "«");
			} else {
				logger.error("Could not find the model »" + localizedDataCSV.getDefId() + "«");
			}
			position++;
			updateProgress(position, totalCount);
		}
		updateProgress(totalCount, totalCount);
		return null;
	}

	BaseDefModel findModelById(List<BaseDefModel> allDefModels, UUID id) {
		for (BaseDefModel baseDefModel : allDefModels) {
			if(id!=null && baseDefModel.getId().equals(id))
				return baseDefModel;
		}
		return null;
	}
}
