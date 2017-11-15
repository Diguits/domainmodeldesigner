package com.diguits.templateengine.contract;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

public interface ICodeBuilder {
	List<Diagnostic> build(UUID templateId, String templateName, String[] modelClassNames, InputStream templateData, OutputStream templateCode,
			String context, String contextErrorMessage);
}
