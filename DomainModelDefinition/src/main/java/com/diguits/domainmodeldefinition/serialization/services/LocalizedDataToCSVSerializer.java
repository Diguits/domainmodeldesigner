package com.diguits.domainmodeldefinition.serialization.services;

import java.util.UUID;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.common.serialization.CSVSerializer;

public class LocalizedDataToCSVSerializer extends CSVSerializer<LocalizedDataCSV> {

	@InjectLogger
	Logger logger;

	@Override
	protected String[] fillEntriesFromModel(LocalizedDataCSV model, int index) {
		String[] entries = new String[9];
		entries[0] = model.getDefId().toString();
		entries[1] = model.getPath();
		entries[2] = model.getType();
		entries[3] = model.getName();
		entries[4] = model.getLocale();
		entries[5] = model.getCaption();
		entries[6] = model.getHint();
		entries[7] = model.getPlaceHolder();
		entries[8] = model.getFormat();
		return entries;
	}

	@Override
	protected void fillModelFromEntries(LocalizedDataCSV model, String[] entries, int index) {
		if ((entries.length == 9)) {
			try {
				model.setDefId(UUID.fromString(entries[0]));
			} catch (IllegalArgumentException e) {
				logger.error(
						"Error importing localized data in line »" + index + "«, the model Id is not a valid UUID");
			}
			model.setPath(entries[1]);
			model.setType(entries[2]);
			model.setName(entries[3]);
			model.setLocale(entries[4]);
			model.setCaption(entries[5]);
			model.setHint(entries[6]);
			model.setPlaceHolder(entries[7]);
			model.setFormat(entries[8]);

		} else
			logger.error("Error importing localized data in line »" + index
					+ "«, 9 columns expected but entries.length found.");
	}

	@Override
	protected Class<LocalizedDataCSV> getElementClass() {
		return LocalizedDataCSV.class;
	}


	@Override
	protected String[] getHeaders() {
		String[] entries = new String[9];
		entries[0] = "DefId";
		entries[1] = "Path";
		entries[2] = "Type";
		entries[3] = "Name";
		entries[4] = "Locale";
		entries[5] = "Caption";
		entries[6] = "Hint";
		entries[7] = "PlaceHolder";
		entries[8] = "Format";
		return entries;
	}

}
