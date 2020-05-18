package com.corona.insights.controller;

import com.corona.insights.model.HardestHitDO;
import com.corona.insights.scheduler.FileProcessScheduler;
import com.corona.insights.scheduler.GitScheduler;
import com.corona.insights.scheduler.WebCrawlScheduler;
import com.corona.insights.service.CoronaDataEnrichmentService;
import com.corona.insights.service.CoronaETLProcessingService;
import com.corona.insights.service.CoronaHardestHitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rest/corona")
@Slf4j
@AllArgsConstructor
public class CoronaInsights {

   private FileProcessScheduler fileProcessScheduler;

    private GitScheduler gitScheduler;

    private WebCrawlScheduler webCrawlScheduler;

    private CoronaETLProcessingService coronaETLProcessingService;

    private CoronaDataEnrichmentService coronaDataEnrichmentService;

    private CoronaHardestHitService coronaHardestHitService;

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

    @RequestMapping(value="/insights/processRealtime" , method= RequestMethod.GET)
    public ResponseEntity processRealtime() {
        log.info("Executing ETL Job");
        webCrawlScheduler.crawlForData();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/insights/enrich" , method= RequestMethod.GET)
    public ResponseEntity enrichData() {
        log.info("Executing ETL Job");
        coronaDataEnrichmentService.enrich();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/insights/hardestHit" , method= RequestMethod.GET)
    public ResponseEntity<List<HardestHitDO>> hardestHit(@RequestParam BigDecimal latitude,
                                                         @RequestParam BigDecimal longitude,
                                                         @RequestParam int radius) {
        return ResponseEntity.ok(coronaHardestHitService.getHardestHitDistricts(latitude, longitude, radius));
    }

    @RequestMapping(value="/insights/hardestHit/country/{country}/state/{state}/district/{district}" , method= RequestMethod.GET)
    public ResponseEntity<List<HardestHitDO>> hardestHit(@PathVariable String country,
                                                         @PathVariable String state,
                                                         @PathVariable String district,
                                                         @RequestParam int radius) {
        return ResponseEntity.ok(coronaHardestHitService.getHardestHitDistricts(country, state, district, radius));
    }

    @RequestMapping(value="/insights/hardestHit/zip/{code}" , method= RequestMethod.GET)
    public ResponseEntity<List<HardestHitDO>> hardestHitByZipCode(@PathVariable int code,
                                                                  @RequestParam int radius) {
        return ResponseEntity.ok(coronaHardestHitService.getHardestHitDistricts(code, radius));
    }
}