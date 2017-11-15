package com.diguits.domainmodeldesigner.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.google.inject.Inject;

import javafx.application.HostServices;

public class ClientServiceBase {

	@Inject
	HostServices hostServices;

	@InjectLogger
	Logger logger;

	protected String rootDir;

	protected String getRootDir() {
		if (rootDir == null || rootDir.isEmpty()) {
			URI uri;
			String documentBasePath = hostServices.getDocumentBase();
			try {
				uri = new URI(documentBasePath);
				rootDir = uri.getPath();
			} catch (URISyntaxException e) {
				logger.error("Error getting app roor directory, the hostService return »"+ documentBasePath +"«", e);
			}
		}
		return rootDir;
	}
}
