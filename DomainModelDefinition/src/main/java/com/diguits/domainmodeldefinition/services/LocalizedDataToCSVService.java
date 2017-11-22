package com.diguits.domainmodeldefinition.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.domainmodeldefinition.definitions.BaseDef;
import com.diguits.domainmodeldefinition.definitions.CustomFieldValueDef;
import com.diguits.domainmodeldefinition.definitions.DomainModelDef;
import com.diguits.domainmodeldefinition.definitions.LocaleDef;
import com.diguits.domainmodeldefinition.definitions.LocalizedDataDef;
import com.diguits.domainmodeldefinition.definitions.RelationshipPartDef;
import com.diguits.domainmodeldefinition.serialization.services.LocalizedDataCSV;
import com.diguits.domainmodeldefinition.serialization.services.LocalizedDataToCSVSerializer;
import com.google.inject.Inject;

public class LocalizedDataToCSVService implements ILocalizedDataToCSVService {

	@InjectLogger
	Logger logger;

	@Inject
	LocalizedDataToCSVSerializer serializer;

	@Inject
	ListAllDefsVisitor visitor;

	@Override
	public void exportLocalizedDataAsCSV(DomainModelDef domainModelDef, String path)
			throws FileNotFoundException, IOException {
		exportLocalizedDataAsCSV(domainModelDef, path, null);
	}

	@Override
	public void exportLocalizedDataAsCSV(DomainModelDef domainModelDef, String path, UUID forLocale)
			throws FileNotFoundException, IOException {
		List<LocalizedDataCSV> localizedDataCSVs = new ArrayList<LocalizedDataCSV>();
		List<LocaleDef> locales = domainModelDef.getLocales();
		visitor.ignore(LocalizedDataDef.class);
		visitor.ignore(CustomFieldValueDef.class);
		visitor.ignore(RelationshipPartDef.class);
		List<BaseDef> allDefs = visitor.getAllDefs(domainModelDef);
		allDefs.sort((v1, v2) -> {
			return v1.getClass().getSimpleName().compareTo(v2.getClass().getSimpleName());
		});
		for (BaseDef baseDef : allDefs) {
			List<LocalizedDataDef> localizedDatas = baseDef.getLocalizedDataList();
			if (forLocale == null) {
				for (LocaleDef locale : locales) {
					int i = 0;
					LocalizedDataDef localizedData = null;
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

		}
		File targetFile = new File(path);
		if (targetFile.exists())
			targetFile.delete();
		serializer.serialize(targetFile, localizedDataCSVs);
	}

	private LocalizedDataCSV createLocalizedDataCSV(BaseDef baseDef, LocalizedDataDef localizedData) {
		LocaleDef locale = localizedData.getLocale();
		LocalizedDataCSV result = createLocalizedDataCSV(baseDef, locale);
		result.setCaption(localizedData.getCaption());
		result.setHint(localizedData.getHint());
		result.setPlaceHolder(localizedData.getPlaceHolder());
		result.setFormat(localizedData.getFormat());
		return result;
	}

	private LocalizedDataCSV createLocalizedDataCSV(BaseDef baseDef, LocaleDef locale) {
		LocalizedDataCSV result = new LocalizedDataCSV();
		result.setDefId(baseDef.getId());
		result.setName(baseDef.getName());
		result.setPath(baseDef.getOwnerPath());
		result.setType(baseDef.getClass().getSimpleName());
		result.setLocale(locale.getName());
		return result;
	}

	@Override
	public void importLocalizedDataAsCSV(DomainModelDef domainModelDef, String path)
			throws FileNotFoundException, IOException {
		List<LocalizedDataCSV> localizedDataCSVs = serializer.deserialize(path);
		visitor.ignore(LocalizedDataDef.class);
		visitor.ignore(CustomFieldValueDef.class);
		visitor.ignore(RelationshipPartDef.class);
		List<BaseDef> allDefs = visitor.getAllDefs(domainModelDef);
		List<LocaleDef> locales = domainModelDef.getLocales();
		Map<String, LocaleDef> localeMap = new HashMap<String, LocaleDef>();
		for (LocaleDef locale : locales) {
			localeMap.put(locale.getName(), locale);
		}
		for (LocalizedDataCSV localizedDataCSV : localizedDataCSVs) {
			BaseDef model = findModelById(allDefs, localizedDataCSV.getDefId());
			if (model != null) {
				LocaleDef locale = localeMap.get(localizedDataCSV.getLocale());
				if (locale != null) {
					LocalizedDataDef localizedData = model.getLocalizedData(locale);
					if(localizedData==null){
						localizedData = new LocalizedDataDef();
						localizedData.setLocale(locale);
						localizedData.setOwner(model);
						model.getLocalizedDataList().add(localizedData);
					}
					localizedData.setCaption(localizedDataCSV.getCaption());
					localizedData.setHint(localizedDataCSV.getHint());
					localizedData.setPlaceHolder(localizedDataCSV.getPlaceHolder());
					localizedData.setFormat(localizedDataCSV.getFormat());
					localizedData.setCaption(localizedDataCSV.getCaption());

				} else
					logger.error("Could not find the locale «" + localizedDataCSV.getLocale() + "»");
			} else {
				logger.error("Could not find the model «" + localizedDataCSV.getDefId() + "»");
			}
		}
	}

	BaseDef findModelById(List<BaseDef> allDefs, UUID id) {
		for (BaseDef baseDef : allDefs) {
			if(id!=null && baseDef.getId().equals(id))
				return baseDef;
		}
		return null;
	}
}
