package com.corona.insights.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
public class BashFileContainerClient implements FileContainerClient {

    private String sourceFolderPath;
    private String fileContainerBasePath;
    private String incomingFolder;
    private String processingFolder;
    private String archiveFolder;

    public BashFileContainerClient(String sourceFolderPath, String fileContainerBasePath, String incomingFolder, String processingFolder, String archiveFolder) {
        this.sourceFolderPath = sourceFolderPath;
        this.fileContainerBasePath = fileContainerBasePath;
        this.incomingFolder = incomingFolder;
        this.processingFolder = processingFolder;
        this.archiveFolder = archiveFolder;
    }

    @Override
    public List<File> getFilesToProcess() {
        List<File> results = new ArrayList<>();
        File[] files = new File(sourceFolderPath).listFiles();
        if(files != null) {
            for (File file : files) {
                if (file.isFile() && !file.getName().equals(".gitignore") && !file.getName().equals("README.md")) {
                    results.add(file);
                }
            }
        }
        return results;
    }

    @Override
    public File markFileForProcessing(File file) throws IOException {
        String destFilePath = fileContainerBasePath + processingFolder + file.getName();
        log.info("Copying File = {} to {}", file.toPath(), destFilePath);
        Files.copy(file.toPath(), Paths.get(destFilePath), REPLACE_EXISTING);
        return new File(destFilePath);
    }

    @Override
    public void deleteFile(File file) {
        log.info("deleting file = {}", file.getName());
        file.delete();
    }

}
