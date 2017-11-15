package com.diguits.domainmodeldesigner.services.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

public class ExportLocalizedDataModelToCSVTask extends TaskBase<Void> {

	private LocaleDefModel forLocale;
	private String path;
	private LocalizedDataToCSVSerializer serializer;
	private DomainModelClientService domainModelClientService;
	private ListAllDefModelsVisitor visitor;

	public ExportLocalizedDataModelToCSVTask(LocaleDefModel forLocale, String path,
			LocalizedDataToCSVSerializer serializer, DomainModelClientService domainModelClientService,
			ListAllDefModelsVisitor visitor) {
		super();
		this.forLocale = forLocale;
		this.path = path;
		this.serializer = serializer;
		this.domainModelClientService = domainModelClientService;
		this.visitor = visitor;
	}

	@Override
	protected Void call() throws Exception {
		List<LocalizedDataCSV> localizedDataCSVs = new ArrayList<LocalizedDataCSV>();
		DomainModelDefModel domainModelDefModel = domainModelClientService.getDomainModelDef();
		List<LocaleDefModel> locales = domainModelDefModel.getLocales();
		visitor.ignore(LocalizedDataDefModel.class);
		visitor.ignore(CustomFieldValueDefModel.class);
		visitor.ignore(RelationshipPartDefModel.class);
		List<BaseDefModel> allDefs = visitor.getAllDefModels(domainModelDefModel);
		allDefs.sort((v1, v2) -> {
			return v1.getClass().getSimpleName().compareTo(v2.getClass().getSimpleName());
		});
		int steepCount = (forLocale == null)?locales.size():1;
		int totalCount = allDefs.size()*steepCount;
		int position = 0;
		updateProgress(0, totalCount);
		for (BaseDefModel baseDef : allDefs) {
			updateMessage(baseDef.getName());
			List<LocalizedDataDefModel> localizedDatas = baseDef.getLocalizedDatas();
			if (forLocale == null) {
				for (LocaleDefModel locale : locales) {
					int i = 0;
					LocalizedDataDefModel localizedData = null;
					while (i < localizedDatas.size() && localizedData == null) {
						if (localizedDatas.get(i).getLocale() == locale)
							localizedData = localizedDatas.get(i);
						i++;
					}
					if (localizedData != null)
						localizedDataCSVs.add(createLocalizedDataCSV(baseDef, localizedData));
					else
						localizedDataCSVs.add(createLocalizedDataCSV(baseDef, locale));
				}
			}
			position+=steepCount;
			updateProgress(position, totalCount);
		}
		updateProgress(totalCount, totalCount);
		File targetFile = new File(path);
		if (targetFile.exists())
			targetFile.delete();
		serializer.serialize(targetFile, localizedDataCSVs);
		return null;
	}

	private LocalizedDataCSV createLocalizedDataCSV(BaseDefModel baseDef, LocalizedDataDefModel localizedData) {
		LocaleDefModel locale = localizedData.getLocale();
		LocalizedDataCSV result = createLocalizedDataCSV(baseDef, locale);
		result.setCaption(localizedData.getCaption());
		result.setHint(localizedData.getHint());
		result.setPlaceHolder(localizedData.getPlaceHolder());
		result.setFormat(localizedData.getFormat());
		return result;
	}

	private LocalizedDataCSV createLocalizedDataCSV(BaseDefModel baseDef, LocaleDefModel locale) {
		LocalizedDataCSV result = new LocalizedDataCSV();
		result.setDefId(baseDef.getId());
		result.setName(baseDef.getName());
		result.setPath(baseDef.getOwnerPath());
		result.setType(baseDef.getClass().getSimpleName());
		result.setLocale(locale.getName());
		return result;
	}

}
