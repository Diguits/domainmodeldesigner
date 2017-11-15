package com.diguits.templatedefinition.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.diguits.templatedefinition.definitions.TemplateProjectDef;

public interface ITemplateProjectService {
    public Iterable<TemplateProjectDef> loadProjects(String directory);

    public void saveProject(String directory, TemplateProjectDef project);

    public String loadTemplateSource(String directory, UUID projectId, UUID templateId);

    public InputStream getTemplateSourceInputStream(String directory, UUID projectId, UUID templateId, String templateName);

    public String loadTemplateCode(String directory, UUID projectId, UUID templateId);

    public InputStream getTemplateCodeInputStream(String directory, UUID projectId, UUID templateId, String templateName);

    public OutputStream getTemplateCodeOutputStream(String directory, UUID projectId, UUID templateId, String templateName);

    public OutputStream getTemplateClassOutputStream(String directory, UUID projectId, UUID templateId, String templateName);

    public InputStream getTemplateClassInputStream(String directory, UUID projectId, UUID templateId, String templateName);


}
