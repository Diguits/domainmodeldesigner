package com.diguits.templateengine.contract;

import java.util.List;
import com.diguits.templateengine.contract.model.TemplateProjectApplyConfig;

public interface ITemplateProjectApplyConfigService {

	void saveConfig(String rootDir, TemplateProjectApplyConfig config);

	List<TemplateProjectApplyConfig> loadConfig(String rootDir);

}
