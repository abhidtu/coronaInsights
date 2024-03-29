package com.corona.insights.scheduler;

import com.corona.insights.client.FileContainerClient;
import com.corona.insights.dao.DaoUtils;
import com.corona.insights.service.CoronaDataEnrichmentService;
import com.corona.insights.service.CoronaETLProcessingService;
import com.corona.insights.service.CoronaFileProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Date;
import java.util.List;

@Slf4j
@Component
public class FileProcessScheduler {

    private FileContainerClient fileContainerClient;
    private CoronaFileProcessingService coronaFileProcessingService;
    private CoronaETLProcessingService coronaETLProcessingService;
    private CoronaDataEnrichmentService coronaDataEnrichmentService;
    private DaoUtils daoUtils;

    public FileProcessScheduler(FileContainerClient fileContainerClient, CoronaFileProcessingService coronaFileProcessingService, CoronaETLProcessingService coronaETLProcessingService, CoronaDataEnrichmentService coronaDataEnrichmentService, DaoUtils daoUtils) {
        this.fileContainerClient = fileContainerClient;
        this.coronaFileProcessingService = coronaFileProcessingService;
        this.coronaETLProcessingService = coronaETLProcessingService;
        this.coronaDataEnrichmentService = coronaDataEnrichmentService;
        this.daoUtils = daoUtils;
    }

    //@Scheduled(cron = "0 0 */2 ? * *")
    public void pollForFiles() {
        if(!daoUtils.isUpdateLocked()) {
            daoUtils.acquireUpdateLock();
            log.info("Executing FileProcessScheduler, fetching files to process");
            List<File> filesToProcess = fileContainerClient.getFilesToProcess();
            log.info("Fetched = {} files for processing", filesToProcess.size());

            List<File> newFiles = coronaFileProcessingService.getNewFilesToProcess(filesToProcess);
            log.info("Found = {} new files to process", newFiles.size());

            if (!newFiles.isEmpty()) {
                for (File file : newFiles) {
                    try {
                        File fileToProcess = fileContainerClient.markFileForProcessing(file);
                        coronaFileProcessingService.processFile(fileToProcess);
                        fileContainerClient.deleteFile(fileToProcess);
                    } catch (Exception e) {
                        log.error("Exception while processing file = {}, exception = {}", file.getName(), e.getMessage());
                    }
                }
                //coronaDataEnrichmentService.enrich();
                daoUtils.releaseUpdateLock();
                coronaETLProcessingService.process();
            }
        }
    }

}