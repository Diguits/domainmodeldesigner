package com.diguits.templateengine.contract;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

public interface ITemplateCompiler {
	public List<Diagnostic> compile(UUID templateId, String templateClassName, InputStream templateCode,
			OutputStream classOutputStream, String context,
			String contextErrorMessage);
}
