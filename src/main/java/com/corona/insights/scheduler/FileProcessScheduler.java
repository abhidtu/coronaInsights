package com.corona.insights.scheduler;

import com.corona.insights.client.FileContainerClient;
import com.corona.insights.service.CoronaFileProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class FileProcessScheduler {

    private FileContainerClient fileContainerClient;
    private CoronaFileProcessingService coronaFileProcessingService;

    public FileProcessScheduler(FileContainerClient fileContainerClient, CoronaFileProcessingService coronaFileProcessingService) {
        this.fileContainerClient = fileContainerClient;
        this.coronaFileProcessingService = coronaFileProcessingService;
    }

    //@Scheduled(cron = "0 * * ? * *")
    public void pollForFiles() {
        log.info("Executing FileProcessScheduler, fetching files to process");
        List<File> filesToProcess = fileContainerClient.getFilesToProcess();
        log.info("Fetched = {} files for processing", filesToProcess.size());

        for (File file : filesToProcess) {
            try {
                File fileToProcess = fileContainerClient.markFileForProcessing(file);
                coronaFileProcessingService.processFile(fileToProcess);
                fileContainerClient.deleteFile(fileToProcess);
            } catch (Exception e) {
                log.error("Exception while processing file = {}, exception = {}", file.getName(), e.getMessage());
                e.printStackTrace();
            }
        }
    }

}