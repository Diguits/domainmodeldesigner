package com.diguits.templatedefinition.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;
import com.diguits.templatedefinition.definitions.TemplateDef;
import com.diguits.templatedefinition.definitions.TemplateGroupDef;
import com.diguits.templatedefinition.definitions.TemplateProjectDef;
import com.diguits.templatedefinition.serialization.services.ITemplateProjectSerializer;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;

public class TemplateProjectService implements ITemplateProjectService {

    @InjectLogger
    Logger logger;

    @Inject
    private ITemplateProjectSerializer templateProjectSerializer;

    @Inject
    private ITemplateFileManager templateFileManager;

    @Inject
    public TemplateProjectService() {
        super();
    }

    @Override
    public Iterable<TemplateProjectDef> loadProjects(String directory) {
        File[] templateProjectFiles = templateFileManager.getTemplateProjectFiles(directory);
        if (templateProjectFiles != null) {
            List<TemplateProjectDef> result = new ArrayList<TemplateProjectDef>();
            for (File templateProjectFile : templateProjectFiles) {
                if (templateProjectFile.exists()) {
                    try {
                        TemplateProjectDef project = templateProjectSerializer
                                .deserialize(templateProjectFile.getAbsolutePath());
                        result.add(project);

                        loadTemplateSource(directory, project);
                    } catch (FileNotFoundException e) {
                        logger.error("Error loading Template Project, the file �{}� was not found",
                                templateProjectFile.getAbsolutePath());
                    } catch (IOException e) {
                        logger.error("Error loading Template Project while triying to deserialize the file �"
                                + templateProjectFile.getAbsolutePath() + "�", e);
                    }
                }
            }
            return result;
        }
        return null;
    }

    private void loadTemplateSource(String directory, TemplateProjectDef project) {
        File templateDir = templateFileManager.getTemplateSourceDir(directory, project.getId());
        for (TemplateGroupDef group : project.getGroups()) {
            for (TemplateDef template : group.getTemplates()) {
                loadTemplateSource(templateDir, template);
            }
        }
    }

    @Override
    public void saveProject(String directory, TemplateProjectDef project) {
        File targetfile = templateFileManager.getTemplateProjectFile(directory, project.getId());
        if (targetfile != null) {
            try {
                templateProjectSerializer.serialize(targetfile, project);
                templateFileManager.createTemplateProjectDirectory(directory, project.getId());

                saveTemplateSource(directory, project);
            } catch (FileNotFoundException e) {
                logger.error("Error saving Template Project, the file �{}� was not found", targetfile.getAbsolutePath());
            } catch (IOException e) {
                logger.error("Error saving Template Project while triying to serialize the project �"
                        + project.getName() + "� to file �" + targetfile.getAbsolutePath() + "�", e);
            }
        }
    }

    private void saveTemplateSource(String directory, TemplateProjectDef project) {
        File templateDir = templateFileManager.getTemplateSourceDir(directory, project.getId());
        for (TemplateGroupDef group : project.getGroups()) {
            for (TemplateDef template : group.getTemplates()) {
                saveTemplateSource(templateDir, template);
            }
        }
    }

    private void loadTemplateSource(File templateDir, TemplateDef template) {
        File templateFile = templateFileManager.getTemplateSourceFile(templateDir, template.getId());
        if (templateFile != null) {
            template.setSource(loadFile(templateFile));
            template.setPath(templateFile.getAbsolutePath());
        }
    }

    @Override
    public String loadTemplateSource(String directory, UUID projectId, UUID templateId) {
        File templateFile = templateFileManager.getTemplateSourceFile(directory, projectId, templateId);
        return loadFile(templateFile);
    }

    @Override
    public InputStream getTemplateSourceInputStream(String directory, UUID projectId, UUID templateId,
                                                    String templateName) {
        File templateFile = templateFileManager.getTemplateSourceFile(directory, projectId, templateId);
        if (templateFile != null)
            if (templateFile.exists()) {
                try {
                    return new FileInputStream(templateFile);
                } catch (FileNotFoundException e) {
                    logger.error("Error loading Source Code for template �{}({})�, the file �{}� was not found",
                            templateName, templateId, templateFile.getAbsolutePath());
                }
            }
        return null;
    }

    private void saveTemplateSource(File templateDir, TemplateDef template) {
        File templateFile = templateFileManager.getTemplateSourceFile(templateDir, template.getId());
        if (templateFile != null)
            try {
                File parentDir = templateFile.getParentFile();
                if (!parentDir.exists())
                    parentDir.mkdirs();
                templateFile.createNewFile();
                FileWriter writer = new FileWriter(templateFile);
                String source = template.getSource();
                writer.append(source == null ? "" : source);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                logger.error("Error saving Source Code for template �{}({})�, the file �{}� was not found" + e.getMessage(),
                        template.getName(), template.getId(), templateFile.getAbsolutePath());
            }
    }

    @Override
    public String loadTemplateCode(String directory, UUID projectId, UUID templateId) {
        File templateCodeFile = templateFileManager.getTemplateCodeFile(directory, projectId, templateId);
        return loadFile(templateCodeFile);
    }

    @Override
    public InputStream getTemplateCodeInputStream(String directory, UUID projectId, UUID templateId,
                                                  String templateName) {
        File templateCodeFile = templateFileManager.getTemplateCodeFile(directory, projectId, templateId);
        if (templateCodeFile != null)
            if (templateCodeFile.exists()) {
                try {
                    return new FileInputStream(templateCodeFile);
                } catch (FileNotFoundException e) {
                    logger.error("Error loading Generated Code for template �{}({})�, the file �{}� was not found",
                            templateName, templateId, templateCodeFile.getAbsolutePath());
                }
            }
        return null;
    }

    @Override
    public OutputStream getTemplateCodeOutputStream(String directory, UUID projectId, UUID templateId,
                                                    String templateName) {
        File templateCodeFile = templateFileManager.getTemplateCodeFile(directory, projectId, templateId);
        if (templateCodeFile != null) {
            if (!templateCodeFile.exists())
                try {
                    File parentDir = templateCodeFile.getParentFile();
                    if (!parentDir.exists())
                        parentDir.mkdirs();
                    templateCodeFile.createNewFile();
                } catch (IOException e1) {
                    logger.error(
                            "Error saving Generated Code for template �{}({})� while trying to create the file �{}�",
                            templateName, templateId, templateCodeFile.getAbsolutePath());
                }
            if (templateCodeFile.exists())

                try {
                    return new FileOutputStream(templateCodeFile);
                } catch (FileNotFoundException e) {
                    logger.error("Error saving Generated Code for template �{}({})�, the file �{}� was not found",
                            templateName, templateId, templateCodeFile.getAbsolutePath());
                }
        }
        return null;
    }

    private String loadFile(File templateFile) {
        if (templateFile != null)
            try {
                if (templateFile.exists()) {
                    FileInputStream stream = new FileInputStream(templateFile);
                    String result = CharStreams.toString(new InputStreamReader(stream, "UTF-8"));
                    stream.close();
                    return result;
                }
            } catch (IOException e) {
                logger.error("Error loading file �" + templateFile.getAbsolutePath() + "�", e);
            }
        return null;
    }

    @Override
    public OutputStream getTemplateClassOutputStream(String directory, UUID projectId, UUID templateId,
                                                     String templateName) {
        File templateClassFile = templateFileManager.getTemplateClassFile(directory, projectId, templateId);
        if (templateClassFile != null) {
            if (!templateClassFile.exists())
                try {
                    File parentDir = templateClassFile.getParentFile();
                    if (!parentDir.exists())
                        parentDir.mkdirs();
                    templateClassFile.createNewFile();
                } catch (IOException e1) {
                    logger.error(
                            "Error saving Compiled Class for template �{}({})� while trying to create the file �{}�",
                            templateName, templateId, templateClassFile.getAbsolutePath());
                }
            if (templateClassFile.exists())
                try {
                    return new FileOutputStream(templateClassFile);
                } catch (FileNotFoundException e) {
                    logger.error("Error saving Compiled Class for template �{}({})�, the file �{}� was not found",
                            templateName, templateId, templateClassFile.getAbsolutePath());
                }
        }
        return null;
    }

    @Override
    public InputStream getTemplateClassInputStream(String directory, UUID projectId, UUID templateId,
                                                   String templateName) {
        File templateClassFile = templateFileManager.getTemplateClassFile(directory, projectId, templateId);
        if (templateClassFile != null)
            if (templateClassFile.exists()) {
                try {
                    return new FileInputStream(templateClassFile);
                } catch (FileNotFoundException e) {
                    logger.error("Error loading Compiled Class for template �{}({})�, the file �{}� was not found",
                            templateName, templateId, templateClassFile.getAbsolutePath());
                }
            }
        return null;
    }
}
