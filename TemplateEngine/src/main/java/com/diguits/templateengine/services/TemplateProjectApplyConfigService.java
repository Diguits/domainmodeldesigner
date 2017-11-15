package com.diguits.templateengine.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.templatedefinition.services.ITemplateFileManager;
import com.diguits.templateengine.contract.ITemplateProjectApplyConfigService;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;
import com.diguits.templateengine.serialization.service.ITemplateProjectApplyConfigSerializer;
import com.google.inject.Inject;

public class TemplateProjectApplyConfigService implements ITemplateProjectApplyConfigService {

	@InjectLogger
	Logger logger;

	@Inject
	ITemplateFileManager fileManager;

	@Inject
	ITemplateProjectApplyConfigSerializer templateProjectApplyConfigSerializer;

	@Override
	public List<TemplateProjectApplyConfig> loadConfig(String rootDir) {
		File[] files = fileManager.getTemplateProjectApplyConfigs(rootDir);
		if (files != null) {
			List<TemplateProjectApplyConfig> result = new ArrayList<TemplateProjectApplyConfig>();
			for (File file : files) {
				if (file.exists()) {
					try {
						TemplateProjectApplyConfig config = templateProjectApplyConfigSerializer
								.deserialize(file.getAbsolutePath());
						result.add(config);
					} catch (FileNotFoundException e) {
						logger.error("Error loading Template Project Apply Configuration, the file »{}« was not found",
								file.getAbsolutePath());
					} catch (IOException e) {
						logger.error("Error loading Template Project Apply Configuration while triying to deserialize the file »"
								+ file.getAbsolutePath() + "«", e);
					}
				}
			}
			return result;
		}
		return null;
	}

	@Override
	public void saveConfig(String rootDir, TemplateProjectApplyConfig config) {
		File targetfile = fileManager.getTemplateProjectApplyConfigFile(rootDir, config.getId());
		if (targetfile != null) {
			try {
				templateProjectApplyConfigSerializer.serialize(targetfile, config);
			} catch (FileNotFoundException e) {
				logger.error("Error saving Template Project Apply Configuration, the file »{}« was not found", targetfile.getAbsolutePath());
			} catch (IOException e) {
				logger.error("Error saving Template Project Apply Configuration while triying to serialize the project »"
						+ config.getName() + "« to file »" + targetfile.getAbsolutePath() + "«", e);
			}
		}
	}
}
