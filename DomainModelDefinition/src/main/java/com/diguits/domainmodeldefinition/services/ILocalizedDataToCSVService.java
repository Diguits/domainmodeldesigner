package com.diguits.domainmodeldefinition.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DomainModelDef;

public interface ILocalizedDataToCSVService {

	void importLocalizedDataAsCSV(DomainModelDef domainModelDef, String path) throws FileNotFoundException, IOException;

	void exportLocalizedDataAsCSV(DomainModelDef domainModelDef, String path, UUID forLocale) throws FileNotFoundException, IOException;

	void exportLocalizedDataAsCSV(DomainModelDef domainModelDef, String path) throws FileNotFoundException, IOException;

}
