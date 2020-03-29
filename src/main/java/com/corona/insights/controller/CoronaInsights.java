package com.corona.insights.controller;

import com.corona.insights.scheduler.FileProcessScheduler;
import com.corona.insights.scheduler.GitScheduler;
import com.corona.insights.service.CoronaETLProcessingService;
import com.corona.insights.service.CoronaFileProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/corona")
@Slf4j
public class CoronaInsights {

   private FileProcessScheduler fileProcessScheduler;

    private GitScheduler gitScheduler;

    private CoronaETLProcessingService coronaETLProcessingService;

    @Autowired
    public void setFileProcessScheduler(FileProcessScheduler fileProcessScheduler) {
        this.fileProcessScheduler = fileProcessScheduler;
    }

    @Autowired
    public void setGitScheduler(GitScheduler gitScheduler) {
        this.gitScheduler = gitScheduler;
    }

    @Autowired
    public void setCoronaETLProcessingService(CoronaETLProcessingService coronaETLProcessingService) {
        this.coronaETLProcessingService = coronaETLProcessingService;
    }

    @RequestMapping(value="/insights/processFiles" , method= RequestMethod.GET)
    public ResponseEntity processCoronaInsights() {
        log.info("Processing Files");
        fileProcessScheduler.pollForFiles();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/insights/pullUpdates" , method= RequestMethod.GET)
    public ResponseEntity pullUpdates() {
        log.info("Processing Files");
        gitScheduler.pollForRepo();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/insights/etl" , method= RequestMethod.GET)
    public ResponseEntity runETLJob() {
        log.info("Executing ETL Job");
        coronaETLProcessingService.process();
        return ResponseEntity.ok().build();
    }

}
