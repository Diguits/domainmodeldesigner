package com.diguits.templatedefinition.services;

import java.io.File;
import java.util.UUID;

public interface ITemplateFileManager {

    public File[] getTemplateProjectFiles(String rootDir);

    void createTemplateProjectDirectory(String rootDir, UUID projectId);

    //Directories
    //Project
    File getTemplateProjectDir(String rootDir, UUID projectId);

    //Templates
    File getTemplateSourceDir(File projectDir);

    File getTemplateCodeDir(File projectDir);

    File getTemplateClassesDir(File projectDir);

    File getTemplateSourceDir(String rootDir, UUID projectId);

    File getTemplateClassesDir(String rootDir, UUID projectId);

    File getTemplateCodeDir(String rootDir, UUID projectId);

    //Files
    //Project
    File getTemplateProjectFile(String rootDir, UUID projectId);

    //Templates
    File getTemplateSourceFile(File templateSourceDir, UUID templateId);

    File getTemplateCodeFile(File templateCodeDir, UUID templateId);

    File getTemplateClassFile(File templateClassDir, UUID templateId);

    File getTemplateSourceFile(String rootDir, UUID projectId, UUID templateId);

    File getTemplateCodeFile(String rootDir, UUID projectId, UUID templateId);

    File getTemplateClassFile(String rootDir, UUID projectId, UUID templateId);

    boolean templateNeedCodeGeneration(String rootDir, UUID projectId, UUID templateId);

    boolean templateNeedCompilation(String rootDir, UUID projectId, UUID templateId);

    String getTemplateProjectsDir(String rootDir);

    String getTemplateProjectApplyConfigsDir(String rootDir);

    File[] getTemplateProjectApplyConfigs(String rootDir);

    File getTemplateProjectApplyConfigFile(String rootDir, UUID applyConfigId);
}
