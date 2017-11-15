package com.diguits.templatedefinition.services;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;

import com.diguits.common.log.InjectLogger;

public class TemplateFileManager implements ITemplateFileManager {
    public static final String TEMPLATE_PROJECTS_DIR = "TemplatesProject";
    public static final String TEMPLATES_PROJECT_EXT = ".tpx";
    public static final String TEMPLATES_SOURCE_EXT = ".dtp";
    public static final String TEMPLATES_CODE_EXT = ".java";
    public static final String TEMPLATES_CLASS_EXT = ".class";
    public static final String TEMPLATES_DIR = "Templates";
    public static final String TEMPLATES_CODE_DIR = "TemplatesCode";
    public static final String TEMPLATES_CLASEES_DIR = "TemplatesClasses";
    public static final String TEMPLATES_PROJECT_APPLY_CONFIG_DIR = "ApplyConfigurations";
    public static final String TEMPLATES_PROJECT_APPLY_CONFIG_EXT = "tac";

    @InjectLogger
    Logger logger;

    @Override
    public File[] getTemplateProjectFiles(String rootDir) {
        String directory = getTemplateProjectsDir(rootDir);
        return getFilesByExtension(directory, TEMPLATES_PROJECT_EXT);
    }

    private File[] getFilesByExtension(String directory, String extension) {
        File directoryFile = new File(directory);
        if (directoryFile.exists() && directoryFile.isDirectory()) {
            return directoryFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(extension);
                }
            });
        }
        return null;
    }

    @Override
    public void createTemplateProjectDirectory(String rootDir, UUID projectId) {
        String directory = getTemplateProjectsDir(rootDir);
        File projectDir = getTemplateProjectDir(directory, projectId);
        if (projectDir.mkdir()) {
            File dir = getTemplateSourceDir(projectDir);
            dir.mkdir();
            dir = getTemplateCodeDir(projectDir);
            dir.mkdir();
            dir = getTemplateClassesDir(projectDir);
            dir.mkdir();
        }
    }

    // Directory
    @Override
    public File getTemplateProjectDir(String rootDir, UUID projectId) {
        String directory = getTemplateProjectsDir(rootDir);
        File projectDir = new File(directory, projectId.toString());
        return projectDir;
    }

    @Override
    public File getTemplateSourceDir(String rootDir, UUID projectId) {
        File projectDir = getTemplateProjectDir(rootDir, projectId);
        return getTemplateSourceDir(projectDir);
    }

    @Override
    public File getTemplateCodeDir(String rootDir, UUID projectId) {
        File projectDir = getTemplateProjectDir(rootDir, projectId);
        return getTemplateCodeDir(projectDir);
    }

    @Override
    public File getTemplateClassesDir(String rootDir, UUID projectId) {
        File projectDir = getTemplateProjectDir(rootDir, projectId);
        return getTemplateClassesDir(projectDir);
    }

    @Override
    public File getTemplateSourceDir(File projectDir) {
        return new File(projectDir, TEMPLATES_DIR);
    }

    @Override
    public File getTemplateCodeDir(File projectDir) {
        return new File(projectDir, TEMPLATES_CODE_DIR);
    }

    @Override
    public File getTemplateClassesDir(File projectDir) {
        return new File(projectDir, TEMPLATES_CLASEES_DIR);
    }

    // Files
    @Override
    public File getTemplateProjectFile(String rootDir, UUID projectId) {
        String directory = getTemplateProjectsDir(rootDir);
        File directoryFile = new File(directory);
        if (directoryFile.exists() && directoryFile.isDirectory())
            return new File(directory, projectId.toString() + TEMPLATES_PROJECT_EXT);
        return null;
    }

    @Override
    public File getTemplateSourceFile(File templateDir, UUID templateId) {
        return new File(templateDir, templateId.toString() + TEMPLATES_SOURCE_EXT);
    }

    @Override
    public File getTemplateCodeFile(File templateCodeDir, UUID templateId) {
        return new File(templateCodeDir, templateId.toString() + TEMPLATES_CODE_EXT);
    }

    @Override
    public File getTemplateClassFile(File templateClassDir, UUID templateId) {
        return new File(templateClassDir, templateId.toString() + TEMPLATES_CLASS_EXT);
    }

    @Override
    public File getTemplateSourceFile(String rootDir, UUID projectId, UUID templateId) {
        File templateSourceDir = getTemplateSourceDir(rootDir, projectId);
        if (templateSourceDir != null)
            return getTemplateSourceFile(templateSourceDir, templateId);
        return null;
    }

    @Override
    public File getTemplateCodeFile(String rootDir, UUID projectId, UUID templateId) {
        File templateCodeDir = getTemplateCodeDir(rootDir, projectId);
        if (templateCodeDir != null)
            return getTemplateCodeFile(templateCodeDir, templateId);
        return null;
    }

    @Override
    public File getTemplateClassFile(String rootDir, UUID projectId, UUID templateId) {
        File templateClassDir = getTemplateClassesDir(rootDir, projectId);
        if (templateClassDir != null)
            return getTemplateClassFile(templateClassDir, templateId);
        return null;
    }

    @Override
    public boolean templateNeedCodeGeneration(String rootDir, UUID projectId, UUID templateId) {
        File templateSourceFile = getTemplateSourceFile(rootDir, projectId, templateId);
        if (templateSourceFile.exists()) {
            File templateCodeFile = getTemplateCodeFile(rootDir, projectId, templateId);
            return !templateCodeFile.exists() || templateCodeFile.lastModified() < templateSourceFile.lastModified();
        }
        return false;
    }

    @Override
    public boolean templateNeedCompilation(String rootDir, UUID projectId, UUID templateId) {
        if (templateNeedCodeGeneration(rootDir, projectId, templateId))
            return true;
        File templateCodeFile = getTemplateCodeFile(rootDir, projectId, templateId);
        if (templateCodeFile.exists()) {
            File templateClassFile = getTemplateClassFile(rootDir, projectId, templateId);
            return !templateCodeFile.exists() || templateClassFile.lastModified() < templateCodeFile.lastModified();
        }
        return false;
    }

    @Override
    public String getTemplateProjectsDir(String rootDir) {
        return getDirectoryOnRoot(rootDir, TEMPLATE_PROJECTS_DIR);
    }

    @Override
    public String getTemplateProjectApplyConfigsDir(String rootDir) {
        return getDirectoryOnRoot(rootDir, TEMPLATES_PROJECT_APPLY_CONFIG_DIR);
    }

    private String getDirectoryOnRoot(String rootDir, String dirName) {
        File directoryFile = new File(rootDir, dirName);
        String result = directoryFile.getAbsolutePath();
        if (!directoryFile.exists())
            try {
                Path dir = Paths.get(result);
                Files.createDirectory(dir);
            } catch (IOException e) {
                logger.error("Error creating directory  �" + result + "�", e);
            }
        return result;
    }

    @Override
    public File[] getTemplateProjectApplyConfigs(String rootDir) {
        String directory = getTemplateProjectApplyConfigsDir(rootDir);
        File directoryFile = new File(directory);
        if (directoryFile.exists() && directoryFile.isDirectory()) {
            return directoryFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    String fileName = pathname.toPath().getFileName().toString();
                    return fileName.endsWith(TEMPLATES_PROJECT_APPLY_CONFIG_EXT);
                }
            });
        }
        return null;
    }

    @Override
    public File getTemplateProjectApplyConfigFile(String rootDir, UUID applyConfigId) {
        String directory = getTemplateProjectApplyConfigsDir(rootDir);
        File directoryFile = new File(directory);
        if (directoryFile.exists() && directoryFile.isDirectory())
            return new File(directory,
                    applyConfigId.toString() + "." + TEMPLATES_PROJECT_APPLY_CONFIG_EXT);
        return null;
    }
}
